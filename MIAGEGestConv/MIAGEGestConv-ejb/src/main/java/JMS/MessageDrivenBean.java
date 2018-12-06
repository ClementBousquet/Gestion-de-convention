/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JMS;


import miage.project.miageserviceshared.ServicePedagogiqueMessage;
import static java.lang.Long.parseLong;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import Service.ServiceBDSLocal;
import miage.project.miageserviceshared.ServiceAdministratifMessage;
import miage.project.miageserviceshared.ServiceJuridiqueMessage;
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
    
    @EJB(beanName = "ServiceBDS") 
    ServiceBDSLocal ServiceBDS;
    
    public MessageDrivenBean() {
    }

    @Override
    public void onMessage(Message message) {
        log4j.debug("onMessage");
        
         if (message instanceof ObjectMessage) {
             
            ObjectMessage sm = (ObjectMessage) message;
            
            try {
               
               
               switch(sm.getJMSType()) {
                   
                   case "ServiceAdministratifMessage" :
                        ServiceAdministratifMessage sam = (ServiceAdministratifMessage) sm.getObject();
                        log4j.info("IDA : " + sam.getIdConvention());
                        ServiceBDS.setStatutAdministratif(sam.getIdConvention(), sam.getStatut());
                        break;
                        
                    case "ServicePedagogiqueMessage" :
                        ServicePedagogiqueMessage spm = (ServicePedagogiqueMessage) sm.getObject();
                        log4j.info("IDP : " + spm.getIdConvention());
                        ServiceBDS.setStatutPedagogique(spm.getIdConvention(), spm.getStatut());
                        ServiceBDS.modifierConvention(spm.getIdConvention(), spm.getProf_ref());
                        break;
                        
                    case "ServiceJuridiqueMessage" :
                        ServiceJuridiqueMessage sjm = (ServiceJuridiqueMessage) sm.getObject();
                        log4j.info("IDJ : " + sjm.getIdConvention());
                        ServiceBDS.setStatutJuridique(sjm.getIdConvention(), sjm.getStatut());
                        break;
                        
               }
                
            } catch (JMSException ex) {
                log4j.error("Error while receiving message from queue" + ex.getMessage());
            }
        }
        
    }
    
}
