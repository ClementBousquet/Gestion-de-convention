/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.project.miageserviceshared;

import java.io.Serializable;
/*
 * @author yannl
 */

/**
 *
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
    
    /**
     *
     * @param idConvention Id de la convention
     * @param idEtudiant Id de l'etudiant
     * @param nom Nom de l'etudiant
     * @param prenom Prenom de l'etudiant
     * @param code Code de la formation
     * @param intitule Intutile de la formation
     * @param niveau Niveau de la formation
     */
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
    
    /**
     *
     * @param id IdConvention
     * @param statut Statut de la convetion
     */
    public ServiceAdministratifMessage(Long id,String statut){
        this.idConvention= id;
        this.statut=statut;
    }

    /**
     *
     * @return Id de la convention
     */
    public Long getIdConvention() {
        return idConvention;
    }

    /**
     *
     * @param idConvention Id de la convention
     */
    public void setIdConvention(Long idConvention) {
        this.idConvention = idConvention;
    }

    /**
     *
     * @return Id de l'étudiant
     */
    public Long getIdEtudiant() {
        return idEtudiant;
    }

    /**
     *
     * @param idEtudiant Id de l'étudiant
     */
    public void setIdEtudiant(Long idEtudiant) {
        this.idEtudiant = idEtudiant;
    } 
    
    /**
     *
     * @param statut Statut de la convention
     */
    public void setStatut(String statut){
       this.statut=statut;
   }
   
    /**
     *
     * @return Statu de la convention
     */
    public String getStatut(){
       return this.statut;
   }

    /**
     *
     * @return Nom de l'étudiant
     */
    public String getNom() {
        return nom;
    }

    /**
     *
     * @param nom Nom de l'étudiant
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     *
     * @return Prenom de l'étudiant
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     *
     * @param prenom Prenom de l'étudiant
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     *
     * @return Code de l'entreprise
     */
    public String getCode() {
        return code;
    }

    /**
     *
     * @param code Code de l'entreprise
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     *
     * @return Intitule de la formation
     */
    public String getIntitule() {
        return intitule;
    }

    /**
     *
     * @param intitule Intitule de la formation
     */
    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    /**
     *
     * @return Niveau de la formation
     */
    public String getNiveau() {
        return niveau;
    }

    /**
     *
     * @param niveau Niveau de la formation
     */
    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }
    
}
