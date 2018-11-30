/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.project.serviceadm;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;
/**
 *
 * @author yannl
 */
public class PubAdministratif {
  private ServiceAdministratifMessage sam;
    
    public PubAdministratif(ServiceAdministratifMessage sam){
        this.sam=sam;
    }
    
    public void main() throws NamingException, JMSException{
        System.setProperty("java.naming.factory.initial",	
        "com.sun.enterprise.naming.SerialInitContextFactory");
        System.setProperty("org.omg.CORBA.ORBInitialHost",	"192.168.1.10");
        System.setProperty("org.omg.CORBA.ORBInitialPort",	"3700");
        InitialContext	context	=	new	InitialContext();
         Connection connexion = null;
        String factoryName = "ConnectionFactory";
        Session session = null;
        // note ce code peut générer des NamingException et JMSException
   
        // récupération de la ConnectionFactory
        ConnectionFactory cf = (ConnectionFactory) context.lookup(factoryName);
        // création de la connexion
        connexion = cf.createConnection();
        // avec login : connexion = cf.createConnection("login", "pass");
        // création de la session sans transaction et avec des acquittements automatiques
        session = connexion.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // avec transaction : session = connexion.createSession(true, 0);
       
        // récupération de la Destination
        Destination dest=null;
        dest = (Destination) context.lookup("SujetTest");
 
        MessageProducer sender = session.createProducer(dest);

            // start the connection, to enable message sends
            connexion.start();
            int count=0;
       
               
            ObjectMessage obj=(ObjectMessage) this.sam;
            
            sender.send(obj);
            System.out.println("Sent: "+sam.getIdConvention());
              
    }
}
