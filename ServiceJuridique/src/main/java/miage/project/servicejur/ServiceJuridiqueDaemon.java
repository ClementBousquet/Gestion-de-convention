/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.project.servicejur;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.MessageDrivenContext;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import miage.project.entities.Convention;
import miage.project.entities.Entreprise;
import miage.project.entities.Etudiant;
import miage.project.entities.Formation;

/**
 *
 * @author yannl
 */
public class ServiceJuridiqueDaemon implements MessageListener {

    @Resource
    private MessageDrivenContext mdc;

    @Override
    public void onMessage(Message message) {
       if (message instanceof MapMessage) {
           try {
               Object o=((MapMessage) message).getObject("id");
               
               if(o instanceof ServiceJuridiqueMessage){
              //Traitement!
              }
           } catch (JMSException ex) {
               Logger.getLogger(ServiceJuridiqueDaemon.class.getName()).log(Level.SEVERE, null, ex);
           }
          
       } else {

      }
    }
}

