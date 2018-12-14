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
public class ServicePedagogiqueMessage implements Serializable {
    private Long idConvention;
    private String dateDebut;
    private String dateFin;
    private String statut;
    private String resume;
    private String intule;
    private String prof_ref;

    /**
     *
     * @param idConvention Id de la convention
     * @param dateDebut Date de debut du stage
     * @param dateFin Date de fin du stage
     * @param statut Statut de la convention
     * @param resume Resume du stage
     * @param intule Intitule de la formation
     * @param profRef Id du professeur référent
     */
    public ServicePedagogiqueMessage(Long idConvention, String dateDebut, String dateFin, String statut, String resume, String intule, String profRef) {
        this.idConvention = idConvention;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.statut = statut;
        this.resume = resume;
        this.intule = intule;
        
    }

    /**
     *
     * @param id Id de la convention
     * @param statut Statut de la convention
     * @param profRef Id du professeur référent
     */
    public ServicePedagogiqueMessage(Long id, String statut, String profRef){
         this.idConvention=id;
         this.statut=statut;
         this.prof_ref = profRef;
     }

    /**
     *
     * @return Id du professeur référent
     */
    public String getProf_ref() {
        return prof_ref;
    }

    /**
     *
     * @param prof_ref Id du professeur référent
     */
    public void setProf_ref(String prof_ref) {
        this.prof_ref = prof_ref;
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
     * @return Date de fin du stage
     */
    public String getDateFin() {
        return dateFin;
    }

    /**
     *
     * @param dateFin Date de fin du stage
     */
    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
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
     * @return Résumé du stage
     */
    public String getResume() {
        return resume;
    }

    /**
     *
     * @param resume Résumé du stage
     */
    public void setResume(String resume) {
        this.resume = resume;
    }

    /**
     *
     * @return Intitule de la formation
     */
    public String getIntule() {
        return intule;
    }

    /**
     *
     * @param intule Intitule de la formation
     */
    public void setIntule(String intule) {
        this.intule = intule;
    }
    
    
}
