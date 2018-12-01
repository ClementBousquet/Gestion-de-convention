/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Business.GestionConvLocal;
import Entities.Convention;
import JMS.PubPedagogique;
import JMS.ServicePedagogiqueMessage;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jms.JMSException;
import javax.naming.NamingException;
import org.apache.log4j.Logger;

/**
 *
 * @author Quentin
 */
@Stateless
public class ServicePedag implements ServicePedagLocal {

    final static Logger log4j = Logger.getLogger(ServicePedag.class);
    
    @EJB
    GestionConvLocal gestionLocal;
    
    @Override
    public void createConvention(Long idConvention, Date dateDebut, Date dateFin, String statut, String resume, String intule, String niveau, String profref) {
        log4j.debug("createConvention");
        gestionLocal.createConvention(idConvention, dateDebut, dateFin, statut, resume, intule, niveau, profref);
    }

    @Override
    public void setProfRef(Long idConv, String prof) {
        log4j.debug("setProfRef");
        gestionLocal.setProfRef(idConv, prof);
    }

    @Override
    public void annulerConvention(Long idConv) {
        log4j.debug("annulerConvention");
        
        try {
            Convention conv = gestionLocal.getConvention(idConv);
            
            PubPedagogique p = new PubPedagogique(new ServicePedagogiqueMessage(conv.getId(), conv.getDateDebut(), conv.getDateFin(), "Non Valide", "", "", conv.getProf_ref()));
            p.main();
            
            gestionLocal.delete(idConv);
            
        } catch (NamingException ex) {
            log4j.error("Namingerror while sending message to queue" + ex.getMessage());
        } catch (JMSException ex) {
            log4j.error("error while sending message to queue" + ex.getMessage());
        }
        
    }

    @Override
    public void validerConvention(Long idConv) {
        log4j.debug("validerConvention");
        
        try {
            Convention conv = gestionLocal.getConvention(idConv);
            
            PubPedagogique p = new PubPedagogique(new ServicePedagogiqueMessage(conv.getId(), conv.getDateDebut(), conv.getDateFin(), "Valide", "", "", conv.getProf_ref()));
            p.main();
            
            gestionLocal.delete(idConv);
            
        } catch (NamingException ex) {
            log4j.error("NamingError while sending a message to queue" + ex.getMessage());
        } catch (JMSException ex) {
            log4j.error("error with JMS while sending message to queue" + ex.getMessage());
        }
    }

    @Override
    public List<String> getConventions() {
        log4j.debug("getConventions");
        
        List<Convention> convs = gestionLocal.getConventions();
        
        List<String> list = new ArrayList();
        
        for (int i = 0; i < convs.size(); i++) {
            list.add(convs.get(i).getId().toString());
        }
        
        return list;
        
    }
    
    @Override
    public Map<String, String> getConvention(Long id) {
        log4j.debug("getConvention");
        
        Convention conv = gestionLocal.getConvention(id);
        
        Map<String, String> map = new HashMap();
        
        map.put("id", conv.getId().toString());
        map.put("resume",conv.getResume()); 
        map.put("intitule", conv.getIntule());
       
        return map;
    }
}
