/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JMSP;

import miage.project.miageserviceshared.ServicePedagogiqueMessage;
import Entities.Formation;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.annotation.Resource;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.naming.NamingException;
import org.apache.log4j.Logger;

/**
 *
 * @author yannl
 */
public class ServicePedagogiqueDaemon implements MessageListener {

    private List<Formation> formation = new ArrayList<Formation>();
    private List<String> listeProfesseur = new ArrayList<String>();
    final static Logger log4j = Logger.getLogger(ServicePedagogiqueDaemon.class);

    @Resource
    private MessageDrivenContext mdc;

    @Override
    public void onMessage(Message message) {
        log4j.debug("onMessage");

        if (message instanceof ObjectMessage) {
            try {
                
                ObjectMessage o = (ObjectMessage) message;

                if (o.getJMSType().equals("ServicePedagogiqueMessage")) {
                    //Traitement
                    ServicePedagogiqueMessage spm = (ServicePedagogiqueMessage) o.getObject();
                    String statut = "";
                    String idProf = "";
                    if (traitementServicePedagogique(spm)) {
                        Random r = new Random();
                        int low = 0;
                        int high = 2;
                        int result = r.nextInt(high - low) + low;
                        idProf=listeProfesseur.get(result);
                        statut = "Valide";
                    } else {
                        statut = "Non Valide";
                    }

                    PubPedagogique pub = new PubPedagogique(new ServicePedagogiqueMessage(spm, idProf, statut));
                    try {
                        pub.main();
                    } catch (NamingException ex) {
                        log4j.error("error while publishing message to queue" + ex.getMessage());
                    }
                }
            } catch (JMSException ex) {
                log4j.error("error while creating the JMS Message" + ex.getMessage());
            }
        }
    }

    public Boolean traitementServicePedagogique(ServicePedagogiqueMessage spm) {
        formation.add(new Formation("Miage", "M1", "Maths-Informatique", "M1MIAGE"));
        formation.add(new Formation("Miage", "M2", "Maths-Informatique", "M2MIAGE"));
        formation.add(new Formation("Miage", "M2", "M2 ingénierie de la transformation numérique", "EIMIBE"));
        listeProfesseur.add("Jean Moulin");
        listeProfesseur.add("Jerome Duvié");
        listeProfesseur.add("Boubou Lasalle");
        String intitule = spm.getIntule();
        String resume = spm.getResume();
        String dateD = spm.getDateDebut();
        String dateF = spm.getDateFin();

        Boolean isSameFormation = false;
        for (Formation f : formation) {
            isSameFormation = isSameFormation || ((intitule.equalsIgnoreCase(f.getIntitule())) && resume.contains("Informatique"));
        }
        DateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
        
        try {
            Date dateDebut = currentDate.parse(dateD);
            Date date = new Date();
        if (isSameFormation) {
            if (dateDebut.after(date) == true && dateDebut.getMonth() > 5) {
                return true;
            }
        }
        return false;
        } catch (ParseException ex) {
            log4j.error("error while parsing date " + ex.getMessage());
            return false;
        }
    }
}
