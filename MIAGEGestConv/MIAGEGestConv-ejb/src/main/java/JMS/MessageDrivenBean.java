/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JMS;

import static java.lang.Long.parseLong;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import Service.ServiceBDSLocal;
import miage.project.serviceadm.ServiceAdministratifMessage;

/**
 *
 * @author Quentin
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/myQueue"),
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
               
               
               switch(sm.getJMSType()) {
                   
                   case "ServiceAdministratifMessage" :
                        ServiceAdministratifMessage sam = (ServiceAdministratifMessage) sm.getObject();
                        //ServiceBDS.setStatutAdministratif(parseLong(id), sam.getStatut());
                        break;
                        
                    case "ServicePedagogiqueMessage" :
                        //ServicePedagogiqueMessage spm = (ServicePedagogiqueMessage) sm.getObject();
                        ServiceBDS.setStatutPedagogique(parseLong(id), sm.getStringProperty("statut"));
                        ServiceBDS.modifierConvention(parseLong(id), sm.getStringProperty("prof_ref"));
                        break;
                        
                    case "ServiceJuridiqueMessage" :
                        //Service
                        //ServiceBDS.setStatutJuridique(parseLong(id), sm.getStringProperty("statut"));
                        break;
                        
               }
                
            } catch (JMSException ex) {
                //LOGGER
            }
        }
        
    }
    
}
