/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.project.miageserviceshared;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author yannl
 */
public class ServiceJuridiqueMessage implements Serializable {
    
    
    private Long idConvention;
    private String dateDebut;
    private String dateFin;
    private float gratification;
    private String nomEntreprise;
    private int siren;
    private String nomEtudiant;
    private String prenomEtudiant;
    private String statut;
    
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
     * @return Nom de l'étudiant
     */
    public String getNomEtudiant() {
        return nomEtudiant;
    }

    /**
     *
     * @param nomEtudiant Nom de l'étudiant
     */
    public void setNomEtudiant(String nomEtudiant) {
        this.nomEtudiant = nomEtudiant;
    }

    /**
     *
     * @return Id du contrat assurance
     */
    public int getContratAssurance() {
        return contratAssurance;
    }

    /**
     *
     * @param contratAssurance Id du contrat assurance
     */
    public void setContratAssurance(int contratAssurance) {
        this.contratAssurance = contratAssurance;
    }
    private int contratAssurance;
    
    /**
     *
     * @param idConvention Id de la convention
     * @param dateDebut Date du début du stage
     * @param dateFin Date de fin du stage
     * @param gratification Gratification de l'etudiant
     * @param nomEntreprise Nom de l'entreprise
     * @param siren Numéro de Siren de l'entreprise
     * @param contratAssurance Numéro de contrat de l'assurance
     * @param nomEtudiant Nom de l'étudiant
     * @param prenomEtudiant Prenom de l'étudiant
     */
    public ServiceJuridiqueMessage(Long idConvention,String dateDebut, String dateFin, float gratification, String nomEntreprise, int siren,int contratAssurance,String nomEtudiant, String prenomEtudiant) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.gratification = gratification;
        this.nomEntreprise = nomEntreprise;
        this.siren = siren;
        this.contratAssurance=contratAssurance;
        this.nomEtudiant=nomEtudiant;
        this.prenomEtudiant= prenomEtudiant;
        this.idConvention=idConvention;
        this.statut="En Cours";
    }
    
    /**
     *
     * @param id Id de la convention
     * @param statut Statut de la convention
     */
    public ServiceJuridiqueMessage(Long id,String statut){
        this.idConvention=id;
        this.statut=statut;
    }

    /**
     *
     * @return Prenom de l'étudiant
     */
    public String getPrenomEtudiant() {
        return prenomEtudiant;
    }

    /**
     *
     * @param prenomEtudiant Prenom de l'étudiant
     */
    public void setPrenomEtudiant(String prenomEtudiant) {
        this.prenomEtudiant = prenomEtudiant;
    }

    /**
     *
     * @return Statut de la convention
     */
    public String getStatut() {
        return statut;
    }

    /**
     *
     * @param statut Statut de la convention
     */
    public void setStatut(String statut) {
        this.statut = statut;
    }

    /**
     *
     * @return Date de début du stage
     */
    public String getDateDebut() {
        return dateDebut;
    }

    /**
     *
     * @param dateDebut Date de début du stage
     */
    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     *
     * @return Date de fin de stage
     */
    public String getDateFin() {
        return dateFin;
    }

    /**
     *
     * @param dateFin Date de fin de stage
     */
    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    /**
     *
     * @return Gratification de l'étudiant
     */
    public float getGratification() {
        return gratification;
    }

    /**
     *
     * @param gratification Gratification de l'étudiant
     */
    public void setGratification(float gratification) {
        this.gratification = gratification;
    }

    /**
     *
     * @return Nom de l'entreprise
     */
    public String getNomEntreprise() {
        return nomEntreprise;
    }

    /**
     *
     * @param nomEntreprise Nom de l'entreprise
     */
    public void setNomEntreprise(String nomEntreprise) {
        this.nomEntreprise = nomEntreprise;
    }

    /**
     *
     * @return Numero de Siren de l'entreprise
     */
    public int getSiren() {
        return siren;
    }

    /**
     *
     * @param siren Numéro de Siren de l'entreprise
     */
    public void setSiren(int siren) {
        this.siren = siren;
    }
    
  
    }