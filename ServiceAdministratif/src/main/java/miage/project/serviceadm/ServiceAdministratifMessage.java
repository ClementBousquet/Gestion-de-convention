/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.project.serviceadm;

import java.io.Serializable;
import javax.jms.MapMessage;
/**
 *
 * @author yannl
 */
public class ServiceAdministratifMessage implements Serializable {
    String valeur;
    //MapMessage<int,Convention> map ;
    public ServiceAdministratifMessage(String valeur){
        this.valeur=valeur;
    }
    public String getText(){
        return this.valeur;
    }
    
   public void setText(String value){
       this.valeur=value;
   }
}
