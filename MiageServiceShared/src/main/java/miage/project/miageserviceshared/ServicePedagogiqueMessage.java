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

    public ServicePedagogiqueMessage(Long idConvention, String dateDebut, String dateFin, String statut, String resume, String intule, String profRef) {
        this.idConvention = idConvention;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.statut = statut;
        this.resume = resume;
        this.intule = intule;
        
    }
     public ServicePedagogiqueMessage(ServicePedagogiqueMessage spm, String profRef,String statut){
         this.idConvention=spm.idConvention;
         this.statut=statut;
         this.prof_ref = profRef;
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

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
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
