/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntitiesP;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Quentin
 */
@Entity
public class ConventionR2 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Long idConvRef;
    
    
    private String dateDebut;
    private String dateFin;
    private String statut;
    private String resume;
    private String intule;
    private String niveau;
    private String prof_ref;
    
    private ConventionR2() {  
    }

    public ConventionR2(Long idConvRef, String dateDebut, String dateFin, String statut, String resume, String intule, String niveau, String prof_ref) {
        this.idConvRef = idConvRef;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.statut = statut;
        this.resume = resume;
        this.intule = intule;
        this.niveau = niveau;
        this.prof_ref = prof_ref;
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

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getProf_ref() {
        return prof_ref;
    }

    public void setProf_ref(String prof_ref) {
        this.prof_ref = prof_ref;
    }

    public Long getIdConvRef() {
        return idConvRef;
    }

    public void setIdConvRef(Long idConvRef) {
        this.idConvRef = idConvRef;
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConventionR2)) {
            return false;
        }
        ConventionR2 other = (ConventionR2) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Convention[ id=" + id + " ]";
    }
    
}
