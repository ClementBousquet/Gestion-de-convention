/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.project.serviceadm;

import java.io.Serializable;
/*
 * @author yannl
 */
public class ServiceAdministratifMessage implements Serializable {
    private String idConvention;
    private int idEtudiant;
    private String code;
    private String intitule;
    private String nom;
    private String prenom;
    private String statut;
    private String niveau;
    
    public ServiceAdministratifMessage(String idConvention,int idEtudiant,String nom,String prenom,String code,String intitule,String niveau){
        this.idConvention=idConvention;
        this.idEtudiant=idEtudiant;
        this.statut="En Cours";
        this.nom=nom;
        this.prenom=prenom;
        this.code=code;
        this.intitule=intitule;
        this.niveau=niveau;
    }
    
    public ServiceAdministratifMessage(ServiceAdministratifMessage sam,String statut){
        this.idConvention=sam.getIdConvention();
        this.statut=statut;     
    }
    
    public String getidConvention(){
        return this.idConvention;
    }
    
   public void setValeur(String idConvention){
       this.idConvention=idConvention;
   }
   
   public void setStatut(String statut){
       this.statut=statut;
   }
   
   public String getStatut(){
       return this.statut;
   }
    public String getIdConvention() {
        return idConvention;
    }

    public void setIdConvention(String idConvention) {
        this.idConvention = idConvention;
    }

    public int getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(int idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }
    
}
