/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.project.servicejur;

import java.io.Serializable;

/**
 *
 * @author yannl
 */
public class ServiceJuridiqueMessage implements Serializable {
    String valeur;
    //Convention convention;
    
    public ServiceJuridiqueMessage(String valeur/*,Convention convention*/){
        this.valeur=valeur;
        //this.convention=convention;
    }
    public String getText(){
        return this.valeur;
    }
    
   public void setText(String value){
       this.valeur=value;
   }
}
