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
    private Long idConvention;
    private Long idEtudiant;
    private String code;
    private String intitule;
    private String nom;
    private String prenom;
    private String statut;
    private String niveau;
    
    public ServiceAdministratifMessage(Long idConvention,Long idEtudiant,String nom,String prenom,String code,String intitule,String niveau){
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
    public Long getIdConvention() {
        return idConvention;
    }

    public void setIdConvention(Long idConvention) {
        this.idConvention = idConvention;
    }

    public Long getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(Long idEtudiant) {
        this.idEtudiant = idEtudiant;
    } 
    
   public void setStatut(String statut){
       this.statut=statut;
   }
   
   public String getStatut(){
       return this.statut;
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
