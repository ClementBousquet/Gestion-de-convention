/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.project.serviceadm;

import miage.project.miageserviceshared.ServiceAdministratifMessage;
import java.util.Random;
import javax.annotation.Resource;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
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
                
                 ObjectMessage om = (ObjectMessage) message;
                
                if (om.getJMSType().equals("ServiceAdministratifMessage")) {
                    String statut="";
                    //Traitement
                   ServiceAdministratifMessage sam= (ServiceAdministratifMessage) om.getObject();
                   if(traitementServiceAdministratif(sam)){
                       statut="Valide";
                   }else{
                       statut="Non Valide";
                   }
                   
                   PubAdministratif pub=new PubAdministratif(new ServiceAdministratifMessage(sam.getIdConvention(),statut));
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
    
    
    public Boolean traitementServiceAdministratif(ServiceAdministratifMessage sam){
        String intituleFormation=sam.getIntitule();
        String niveau =sam.getNiveau();
        
        if(intituleFormation!=null && niveau!=null){
            //Si formation égale à miage et niveau égale M2 , les informations seront considérés comme correcte sinon 1 chance sur 2
            if(intituleFormation.equalsIgnoreCase("M2 MIAGE") && niveau.equals("Master")){
                return true;
            }else{
                Random random = new Random();
                return random.nextBoolean();
            }
        }
        return false;
    }
    }

