/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JMSP;

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
public class ServicePedagogiqueDaemon implements MessageListener {

    final static Logger log4j = Logger.getLogger(ServicePedagogiqueDaemon.class);
    
    @Resource
    private MessageDrivenContext mdc;

    @Override
    public void onMessage(Message message) {
        log4j.debug("onMessage");
        
        if (message instanceof MapMessage) {
            try {
                Object o = ((ObjectMessage) message).getObject();

                if (o instanceof ServicePedagogiqueMessage) {
                    //Traitement
                    ServicePedagogiqueMessage spm = (ServicePedagogiqueMessage) o;

                    String statut = "";
                    PubPedagogique pub = new PubPedagogique(new ServicePedagogiqueMessage(spm, statut));
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
}
