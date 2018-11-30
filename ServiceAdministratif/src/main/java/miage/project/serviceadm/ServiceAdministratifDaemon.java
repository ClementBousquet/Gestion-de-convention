/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.project.serviceadm;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.naming.NamingException;

/**
 *
 * @author yannl
 */
public class ServiceAdministratifDaemon implements MessageListener {

    @Resource
    private MessageDrivenContext mdc;

    @Override
    public void onMessage(Message message) {
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
                        Logger.getLogger(ServiceAdministratifDaemon.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (JMSException ex) {
                Logger.getLogger(ServiceAdministratifDaemon.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {

        }
    }
    }

