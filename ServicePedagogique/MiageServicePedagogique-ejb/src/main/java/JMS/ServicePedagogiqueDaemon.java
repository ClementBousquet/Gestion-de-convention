/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JMS;

import ServicesP.ServicePedagLocal;
import miage.project.miageserviceshared.ServicePedagogiqueMessage;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import org.apache.log4j.Logger;

@MessageDriven(mappedName = "jms/myTopic", activationConfig = {
    @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "jms/myTopic"),  
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})

/**
 *
 * @author yannl
 */
public class ServicePedagogiqueDaemon implements MessageListener {

    final static Logger log4j = Logger.getLogger(ServicePedagogiqueDaemon.class);

    @Resource
    private MessageDrivenContext mdc;

    @EJB(beanName="ServicePedag")
    private ServicePedagLocal sp;
    
    @Override
    public void onMessage(Message message) {
        log4j.debug("ServicePedag" + " onMessage");

        if (message instanceof ObjectMessage) {
            try {
                
                ObjectMessage o = (ObjectMessage) message;
                
                log4j.info(o.getObject().toString());

                if (o.getJMSType().equals("ServicePedagogiqueMessage")) {
                    //Traitement
                    ServicePedagogiqueMessage spm = (ServicePedagogiqueMessage) o.getObject();
                    log4j.info("This message is good : " + spm.getIdConvention());
                    sp.createConvention(spm.getIdConvention(), spm.getDateDebut(), spm.getDateFin(), "En cours", spm.getResume(), spm.getIntule(), spm.getIntule(), "");
                }
            } catch (JMSException ex) {
                log4j.error("error while creating the JMS Message" + ex.getMessage());
            }
        }
    }

    public Boolean traitementServicePedagogique(ServicePedagogiqueMessage spm) {
        String intitule = spm.getIntule();
        String resume = spm.getResume();
        String dateD = spm.getDateDebut();
        String dateF = spm.getDateFin();

        Boolean isSameFormation = false;
        /*for (Formation f : formation) {
             if ((intitule.equalsIgnoreCase(f.getIntitule())) && resume.contains("Informatique"))
                 isSameFormation = true;
        }*/
        DateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
        
        try {
            Date dateDebut = currentDate.parse(dateD);
            Date dateFin = currentDate.parse(dateF);
            Date date = new Date();
        if (isSameFormation) {
            if (dateDebut.after(date) == true && dateFin.getMonth() > 5) {
                return true;
            }
        }
        return false;
        } catch (ParseException ex) {
            log4j.error("error while parsing date " + ex.getMessage());
            return false;
        }
    }
}
