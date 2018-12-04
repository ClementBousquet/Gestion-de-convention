/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.project.serviceadm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.log4j.Logger;
/**
 *
 * @author yannl
 */
public class SubPedagogique {
    
    final static Logger log4j = Logger.getLogger(SubAdministratif.class);
    
   public static void main(String[] args) throws NamingException, JMSException{
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
        dest = (Destination) context.lookup("SubjectTest");
 
        MessageConsumer consumer = session.createConsumer(dest);

             // register a listener
           consumer.setMessageListener(new ServiceAdministratifDaemon());

            // start the connection, to enable message receipt
            connexion.start();

            System.out.println("Waiting for messages...");
            System.out.println("Press [return] to quit");

            BufferedReader waiter = new BufferedReader(new InputStreamReader(System.in));
        try {  
            waiter.readLine();
        } catch (IOException ex) {
            log4j.error("error while reading new line on topic" + ex.getMessage());
        }
    }  
}
