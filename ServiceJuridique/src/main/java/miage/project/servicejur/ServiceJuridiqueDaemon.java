/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.project.servicejur;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.naming.NamingException;

/**
 *
 * @author yannl
 */
public class ServiceJuridiqueDaemon implements MessageListener {

    @Override
    public void onMessage(Message message) {
        if (message instanceof ObjectMessage) {
            try {
                Object o = ((ObjectMessage) message).getObject();

                if (o instanceof ServiceJuridiqueMessage) {
                    //Traitement
                   ServiceJuridiqueMessage sjm=(ServiceJuridiqueMessage) o;
                   
                   
                   String statut="";
                   PubJuridique pub=new PubJuridique(new ServiceJuridiqueMessage(sjm,statut));
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
}
