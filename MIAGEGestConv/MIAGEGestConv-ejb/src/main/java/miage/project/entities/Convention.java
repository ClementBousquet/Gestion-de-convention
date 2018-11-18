/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.project.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 *
 * @author Quentin
 */
@Entity
public class Convention implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    /* EnCours / Validé / Non Validé */
    @Column(nullable = false)
    private String StatutJuridique;
    @Column(nullable = false)
    private String StatutAdministratif;
    @Column(nullable = false)
    private String StatutPedagogique;
    @Column(nullable = false)
    private int annéeUniversitaire;
    @OneToOne
    @JoinColumn
    private Formation formation;
    @OneToOne
    @JoinColumn
    private Entreprise entreprise;
    @Column(nullable = false)
    private int duree; //Exprimé en mois
    @Column(nullable = false)
    private float gratification;
    @Column(nullable = false)
    private String resume;
    @Column(nullable = false)
    private String nomEnseignant;
    @Column(nullable = false)
    private int dureeEssai; // Exprimé en jours
    @Column(nullable = false)
    private int contratAssurance;
    
    private Etudiant etu;
    private Entreprise entp;
    private Formation form;
    
    protected Convention(){};
    
    public Convention(int annee, int duree, int gratification, String resume, int dureeEssai, int contrat, Entreprise entp, Etudiant etu, Formation form) {
        
        this.annéeUniversitaire = annee;
        this.duree = duree;
        this.gratification = gratification;
        this.resume = resume;
        this.dureeEssai = dureeEssai;
        this.contratAssurance = contrat;
        
        this.StatutPedagogique = "EnCours";
        this.StatutAdministratif = "EnCours";
        this.StatutJuridique = "EnCours";
        
        this.etu = etu;
        this.form = form;
        this.entp = entp;
        
    }

    public String getStatutJuridique() {
        return StatutJuridique;
    }

    public void setStatutJuridique(String StatutJuridique) {
        this.StatutJuridique = StatutJuridique;
    }

    public String getStatutAdministratif() {
        return StatutAdministratif;
    }

    public void setStatutAdministratif(String StatutAdministratif) {
        this.StatutAdministratif = StatutAdministratif;
    }

    public String getStatutPedagogique() {
        return StatutPedagogique;
    }

    public void setStatutPedagogique(String StatutPedagogique) {
        this.StatutPedagogique = StatutPedagogique;
    }

    public int getAnnéeUniversitaire() {
        return annéeUniversitaire;
    }

    public void setAnnéeUniversitaire(int annéeUniversitaire) {
        this.annéeUniversitaire = annéeUniversitaire;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public float getGratification() {
        return gratification;
    }

    public void setGratification(float gratification) {
        this.gratification = gratification;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getNomEnseignant() {
        return nomEnseignant;
    }

    public void setNomEnseignant(String nomEnseignant) {
        this.nomEnseignant = nomEnseignant;
    }

    public int getDureeEssai() {
        return dureeEssai;
    }

    public void setDureeEssai(int dureeEssai) {
        this.dureeEssai = dureeEssai;
    }

    public int getContratAssurance() {
        return contratAssurance;
    }

    public void setContratAssurance(int contratAssurance) {
        this.contratAssurance = contratAssurance;
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
        if (!(object instanceof Convention)) {
            return false;
        }
        Convention other = (Convention) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "miage.project.entities.Convention[ id=" + id + " ]";
    }
    
}
