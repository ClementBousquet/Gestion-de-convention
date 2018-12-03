/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JMS;

import JMSP.ServicePedagogiqueMessage;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import Entities.Convention;
import miage.project.serviceadm.ServiceAdministratifMessage;
import miage.project.servicejur.ServiceJuridiqueMessage;

/**
 *
 * @author Quentin
 */
public class MessageSenderBean {

    
    final static org.apache.log4j.Logger log4j = org.apache.log4j.Logger.getLogger(MessageSenderBean.class);
    
    private Message createJMSMessageForjmsMyTopic(Session session, Convention conv, String dest) throws JMSException {
        log4j.debug("createJMSMessageForjmsMyTopic");
        
        ObjectMessage tm = session.createObjectMessage(); 
        switch (dest) {
            case "ServiceJuridique" :
                tm.setJMSType("ServiceJuridique");
                tm.setObject(new ServiceJuridiqueMessage(conv.getId(),
                    conv.getDateDebut(),
                    conv.getDateFin(),
                    conv.getGratification(),
                    conv.getEntreprise().getNom(),
                    conv.getEntreprise().getSiren(),
                    conv.getContratAssurance(),
                    conv.getEtudiant().getNom(),
                    conv.getEtudiant().getPrenom()));
                break;
            case "ServiceAdministratif" :
                tm.setJMSType("ServiceAdministratif");
                tm.setObject(new ServiceAdministratifMessage(conv.getId(), 
                    conv.getEtudiant().getId(),
                    conv.getEtudiant().getNom(),
                    conv.getEtudiant().getPrenom(),
                    conv.getFormation().getCode(),
                    conv.getFormation().getIntitule(),
                    conv.getFormation().getNiveau()));
                break;
            case "ServicePedagogique" :
                tm.setJMSType("ServicePedagogique");
                tm.setObject(new ServicePedagogiqueMessage(conv.getId(),
                    conv.getDateDebut(),
                    conv.getDateFin(),
                    conv.getStatutPedagogique(),
                    conv.getResume(),
                    conv.getFormation().getIntitule(),
                    ""));
                break;
        }
        
        return tm;
    }

    public void sendJMSMessageToMyTopic(Object messageData) throws JMSException, NamingException {
        log4j.debug("sendJMSMessageToMyTopic");
        
        Context c = new InitialContext();
        ConnectionFactory cf = (ConnectionFactory) c.lookup("java:comp/env/jms/__defaultConnectionFactory");
        Connection conn = null;
        Session s = null;
        try {
            conn = cf.createConnection();
            s = conn.createSession(false, s.AUTO_ACKNOWLEDGE);
            Destination destination = (Destination) c.lookup("java:comp/env/jms/myTopic");
            MessageProducer mp = s.createProducer(destination);
            if (messageData instanceof Convention) {
                mp.send(createJMSMessageForjmsMyTopic(s, (Convention) messageData, "ServiceJuridique"));
                mp.send(createJMSMessageForjmsMyTopic(s, (Convention) messageData, "ServicePedagogique"));
                mp.send(createJMSMessageForjmsMyTopic(s, (Convention) messageData, "ServiceAdministratif"));
            }
        } finally {
            if (s != null) {
                try {
                    s.close();
                } catch (JMSException e) {
                    log4j.error("Error while sending message to Topic" + e.getMessage());
                }
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
    
}
