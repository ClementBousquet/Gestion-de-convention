/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JMS;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import miage.project.entities.Convention;

/**
 *
 * @author Quentin
 */
public class MessageSenderBean {

    private Message createJMSMessageForjmsMyTopic(Session session, Convention conv, String dest) throws JMSException {
        // TODO create and populate message to send
        ObjectMessage tm = session.createObjectMessage(); 
        switch (dest) {
            case "ServiceJuridique" :
                tm.setJMSType("ServiceJuridique");
                //tm.setObject(new ServiceJuridiqueMessage());
                break;
            case "ServiceAdministratif" :
                tm.setJMSType("ServiceAdministratif");
                //tm.setObject(new ServiceJuridiqueMessage());
                break;
            case "ServicePedagogique" :
                tm.setJMSType("ServicePedagogique");
                //tm.setObject(new ServiceJuridiqueMessage());
                break;
        }
        
        return tm;
    }

    public void sendJMSMessageToMyTopic(Object messageData) throws JMSException, NamingException {
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
                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Cannot close session", e);
                }
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
    
}