/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.project.serviceadm;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import miage.project.entities.Convention;
/**
 *
 * @author yannl
 */
public class ServiceAdministratifMessage implements Serializable {
    String valeur;
    int idEtudiant;
    
    public ServiceAdministratifMessage(String valeur,int idEtudiant){
        this.valeur=valeur;
        this.idEtudiant=idEtudiant;
    }
    public String getText(){
        return this.valeur;
    }
    
   public void setText(String value){
       this.valeur=value;
   }
}
