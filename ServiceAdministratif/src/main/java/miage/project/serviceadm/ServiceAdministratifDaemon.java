/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.project.serviceadm;

import javax.annotation.Resource;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.naming.NamingException;
import org.apache.log4j.Logger;

/**
 *
 * @author yannl
 */
public class ServiceAdministratifDaemon implements MessageListener {

    final static Logger log4j = Logger.getLogger(ServiceAdministratifDaemon.class);
    
    @Resource
    private MessageDrivenContext mdc;

    @Override
    public void onMessage(Message message) {
    log4j.debug("onMessage");
    
     if (message instanceof ObjectMessage) {
            try {
                Object o = ((ObjectMessage) message).getObject();

                if (o instanceof ServiceAdministratifMessage) {
                    //Traitement
                   ServiceAdministratifMessage sam=(ServiceAdministratifMessage) o;
                   
                   
                   String statut="";
                   PubAdministratif pub=new PubAdministratif(new ServiceAdministratifMessage(sam,statut));
                    try {
                        pub.main();
                    } catch (NamingException ex) {
                        log4j.error("error while publishing a message" + ex.getMessage());
                    }
                }
            } catch (JMSException ex) {
                log4j.error("error while creating a message to publish" + ex.getMessage());
            }

        } else {

        }
    }
    }

