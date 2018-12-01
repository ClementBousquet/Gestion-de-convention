/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JMS;

import Service.ServiceBDS;
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
import miage.project.servicejur.ServiceJuridiqueMessage;
import org.apache.log4j.Logger;

/**
 *
 * @author Quentin
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/myQueue"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})


public class MessageDrivenBean implements MessageListener{
    
    final static Logger log4j = Logger.getLogger(MessageDrivenBean.class);
    
    @EJB
    ServiceBDSLocal ServiceBDS;
    
    public MessageDrivenBean() {
    }

    @Override
    public void onMessage(Message message) {
        log4j.debug("onMessage");
        
         if (message instanceof ObjectMessage) {
             
            ObjectMessage sm = (ObjectMessage) message;
            
            try {
                
               String id = sm.getStringProperty("id");
               
               
               switch(sm.getJMSType()) {
                   
                   case "ServiceAdministratifMessage" :
                        ServiceAdministratifMessage sam = (ServiceAdministratifMessage) sm.getObject();
                        ServiceBDS.setStatutAdministratif(parseLong(id), sam.getStatut());
                        break;
                        
                    case "ServicePedagogiqueMessage" :
                        ServicePedagogiqueMessage spm = (ServicePedagogiqueMessage) sm.getObject();
                        ServiceBDS.setStatutPedagogique(parseLong(id), spm.getStatut());
                        ServiceBDS.modifierConvention(parseLong(id), spm.getProf_ref());
                        break;
                        
                    case "ServiceJuridiqueMessage" :
                        ServiceJuridiqueMessage sjm = (ServiceJuridiqueMessage) sm.getObject();
                        ServiceBDS.setStatutJuridique(parseLong(id), sjm.getStatut());
                        break;
                        
               }
                
            } catch (JMSException ex) {
                log4j.error("Error while receiving message from queue" + ex.getMessage());
            }
        }
        
    }
    
}
