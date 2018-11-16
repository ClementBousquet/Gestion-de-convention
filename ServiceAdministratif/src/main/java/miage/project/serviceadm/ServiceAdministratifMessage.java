/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.project.serviceadm;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.jms.MapMessage;

/**
 *
 * @author yannl
 */
public class ServiceAdministratifMessage implements Serializable{
    String valeur;
    
    public ServiceAdministratifMessage(String valeur){
        this.valeur=valeur;
    }
    
}
