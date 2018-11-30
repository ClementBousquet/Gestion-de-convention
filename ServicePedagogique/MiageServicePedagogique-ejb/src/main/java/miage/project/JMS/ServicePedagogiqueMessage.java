/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.project.JMS;

import java.io.Serializable;

/**
 *
 * @author yannl
 */
public class ServicePedagogiqueMessage implements Serializable {
    String valeur;
    //Convention convention;
    //String idEnseignement;
    
    public ServicePedagogiqueMessage(String valeur/*,Convention convention*/){
        this.valeur=valeur;
        //this.convention=convention;
        //this.idEnseignement=idEnseignement;
    }
    public String getText(){
        return this.valeur;
    }
    
   public void setText(String value){
       this.valeur=value;
   }
}
