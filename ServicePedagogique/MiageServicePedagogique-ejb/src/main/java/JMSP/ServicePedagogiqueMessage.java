/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JMSP;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author yannl
 */
public class ServicePedagogiqueMessage implements Serializable {
    private Long idConvention;
    private Date dateDebut;
    private Date dateFin;
    private String statut;
    private String resume;
    private String intule;
    private String prof_ref;

    public ServicePedagogiqueMessage(Long idConvention, Date dateDebut, Date dateFin, String statut, String resume, String intule, String profRef) {
        this.idConvention = idConvention;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.statut = statut;
        this.resume = resume;
        this.intule = intule;
        this.prof_ref = profRef;
    }
     public ServicePedagogiqueMessage(ServicePedagogiqueMessage spm,String statut){
         this.idConvention=spm.idConvention;
         this.statut=statut;
     }
    public String getProf_ref() {
        return prof_ref;
    }

    public void setProf_ref(String prof_ref) {
        this.prof_ref = prof_ref;
    }

    public Long getIdConvention() {
        return idConvention;
    }

    public void setIdConvention(Long idConvention) {
        this.idConvention = idConvention;
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

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getIntule() {
        return intule;
    }

    public void setIntule(String intule) {
        this.intule = intule;
    }
    
    
}
