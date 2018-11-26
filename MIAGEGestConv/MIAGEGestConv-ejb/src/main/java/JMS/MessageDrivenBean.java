/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JMS;

import static java.lang.Float.parseFloat;
import static java.lang.Long.parseLong;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.StreamMessage;
import miage.project.entities.Convention;
import miage.project.service.ServiceBDSLocal;

/**
 *
 * @author Quentin
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/Bds"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class MessageDrivenBean implements MessageListener{
    
    @EJB
    ServiceBDSLocal ServiceBDS;
    
    public MessageDrivenBean() {
    }

    @Override
    public void onMessage(Message message) {
        
         if (message instanceof ObjectMessage) {
             
            ObjectMessage sm = (ObjectMessage) message;
            
            try {
               String id = sm.getStringProperty("id");
               switch(sm.getStringProperty("service")) {
                   //Changer les cases ids !!! par ds ids conventions, on envoit des servicesJuridique/AdministratifMessage !
                   case "adm" :
                        ServiceBDS.setStatutAdministratif(parseLong(id), sm.getStringProperty("statut"));
                        break;
                    case "ped" :
                        ServiceBDS.setStatutPedagogique(parseLong(id), sm.getStringProperty("statut"));
                        ServiceBDS.modifierConvention(parseLong(id), sm.getStringProperty("prof_ref"));
                        break;
                    case "jur" :
                        ServiceBDS.setStatutJuridique(parseLong(id), sm.getStringProperty("statut"));
                        break;
               }
                
            } catch (JMSException ex) {
                //LOGGER
            }
        }
        
    }
    
}
