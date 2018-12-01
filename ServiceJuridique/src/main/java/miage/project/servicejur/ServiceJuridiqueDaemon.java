/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.project.servicejur;

import com.google.gson.Gson;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.naming.NamingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import miage.project.siret.SirenPOJO;

/**
 *
 * @author yannl
 */
public class ServiceJuridiqueDaemon implements MessageListener {
    
    final static org.apache.log4j.Logger log4j = org.apache.log4j.Logger.getLogger(ServiceJuridiqueDaemon.class);

    @Override
    public void onMessage(Message message) {
        if (message instanceof ObjectMessage) {
            try {
                Object o = ((ObjectMessage) message).getObject();

                if (o instanceof ServiceJuridiqueMessage) {
                    //Traitement

                    ServiceJuridiqueMessage sjm = (ServiceJuridiqueMessage) o;

                    String statut = "";
                    if (verifDate(sjm.getDateDebut(),sjm.getDateFin()) && verifExistenceJuridique(sjm.getGratification(), sjm.getNomEntreprise(),sjm.getSiren(),sjm.getNomEtudiant()) && verifContratAssurance(sjm.getDateDebut(), sjm.getDateFin(),sjm.getContratAssurance(),sjm.getNomEtudiant())) {
                        statut = "Validé";
                    } else {
                        statut = "Non validé";
                    }
                    PubJuridique pub = new PubJuridique(new ServiceJuridiqueMessage(sjm, statut));
                    try {
                        pub.main();
                    } catch (NamingException ex) {
                        Logger.getLogger(ServiceJuridiqueDaemon.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            } catch (JMSException ex) {
                Logger.getLogger(ServiceJuridiqueDaemon.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {

        }
    }

    public boolean verifDate(Date dateDeb, Date dateFin) {
        Calendar startCalendar = new GregorianCalendar();
        startCalendar.setTime(dateDeb);
        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(dateFin);

        int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        int diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
        
        if (diffMonth < 6) {
            return false;
        }
        else {
            return true;
        }
    }
    
    public boolean verifExistenceJuridique (float gratification, String nomEntreprise, int siren, String nomEtudiant){

        // I/O JSON
        Gson gson = new Gson();

        // TOKEN BEARER a récuperer sur INSEE
        String token = "Bearer 87faafeb-b34f-39d4-8cc0-cb9e7a15a8d9";
        // URI Service INSEE
        String uri = "http://data.opendatasoft.com/api/records/1.0/search/?dataset=sirene%40public";

        // a ajuster selon requete voir mode emploi INSEE
        String query = "&lang=fr&rows=1";

        // SIREN a chercher
        String sirenStr = Integer.toString(siren);
        // Pour info siren = "552100554"; //PIGEOT.

        Client client = ClientBuilder.newClient();
        WebTarget wt = client.target(uri + "&q=" + sirenStr + query);

        //WebResource webResource = client.resource(uri + siren + query);
        log4j.info("uri appel: " + uri + "&q=" + siren + query);

        Invocation.Builder invocationBuilder = wt.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        String reponse = response.readEntity(String.class);

        // Convertisseur JSON
        SirenPOJO model = gson.fromJson(reponse, SirenPOJO.class);

        log4j.info("Résultat: " + response.getStatus());
        log4j.info("Raison sociale : " + model.getRecords()[0].getFields().getL1_normalisee() + ", Date création entité : " + model.getRecords()[0].getFields().getDcren() + ", Activité : " + model.getRecords()[0].getFields().getActivite());
        return true;
    }
    
    public boolean verifContratAssurance (Date dateDeb, Date dateFin, int contratAssurance, String nomEtudiant) {
        return true;
    }
}
