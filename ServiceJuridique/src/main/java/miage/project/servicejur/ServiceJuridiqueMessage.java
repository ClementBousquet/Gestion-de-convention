/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.project.servicejur;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author yannl
 */
public class ServiceJuridiqueMessage implements Serializable {
    private int idConvention;

    public int getIdConvention() {
        return idConvention;
    }

    public void setIdConvention(int idConvention) {
        this.idConvention = idConvention;
    }
    private Date dateDebut;
    private Date dateFin;
    private float gratification;
    private String nomEntreprise;
    private int siren;
    private String nomEtudiant;
    private String statut;

    public String getNomEtudiant() {
        return nomEtudiant;
    }

    public void setNomEtudiant(String nomEtudiant) {
        this.nomEtudiant = nomEtudiant;
    }

    public int getContratAssurance() {
        return contratAssurance;
    }

    public void setContratAssurance(int contratAssurance) {
        this.contratAssurance = contratAssurance;
    }
    private int contratAssurance;
    
    public ServiceJuridiqueMessage(int idConvention,Date dateDebut, Date dateFin, float gratification, String nomEntreprise, int siren,int contratAssurance,String nomEtudiant) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.gratification = gratification;
        this.nomEntreprise = nomEntreprise;
        this.siren = siren;
        this.contratAssurance=contratAssurance;
        this.nomEtudiant=nomEtudiant;
        this.idConvention=idConvention;
        this.statut="En Cours";
    }
    
    public ServiceJuridiqueMessage(ServiceJuridiqueMessage sjm,String statut){
        this.idConvention=sjm.getIdConvention();
        this.statut=statut;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public float getGratification() {
        return gratification;
    }

    public void setGratification(float gratification) {
        this.gratification = gratification;
    }

    public String getNomEntreprise() {
        return nomEntreprise;
    }

    public void setNomEntreprise(String nomEntreprise) {
        this.nomEntreprise = nomEntreprise;
    }

    public int getSiren() {
        return siren;
    }

    public void setSiren(int siren) {
        this.siren = siren;
    }
    
  
    }