/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.project.JMS;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author yannl
 */
public class ServicePedagogiqueDaemon implements MessageListener {

    @Resource
    private MessageDrivenContext mdc;

    @Override
    public void onMessage(Message message) {
       if (message instanceof MapMessage) {
           try {
               Object o=((MapMessage) message).getObject("id");
               if(o instanceof ServicePedagogiqueMessage){
              //Traitement!
              }
           } catch (JMSException ex) {
               Logger.getLogger(ServicePedagogiqueDaemon.class.getName()).log(Level.SEVERE, null, ex);
           }
          
       } else {

      }
    }
}
