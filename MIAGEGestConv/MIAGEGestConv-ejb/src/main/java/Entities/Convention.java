/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

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
    @Column(nullable = false)
    private String dateDebut;
    @Column(nullable = false)
    private String dateFin;
    @Column(nullable = false)
    private float gratification;
    @Column(nullable = false)
    private String resume;
    @Column
    private String nomEnseignant;
    @Column(nullable = false)
    private int dureeEssai; // Exprimé en semaine
    @Column(nullable = false)
    private int contratAssurance;
    
    @JoinColumn
    private Etudiant etudiant;
    
    @JoinColumn
    private Formation formation;
    
    @JoinColumn
    private Entreprise entreprise;
    
    protected Convention(){};
    
    public Convention(int annee, String datedeb, String datef, int gratification, String resume, int dureeEssai, int contrat, Entreprise entp, Etudiant etu, Formation form) {
        
        this.annéeUniversitaire = annee;
        this.dateDebut = datedeb;
        this.dateFin = datef;
        this.gratification = gratification;
        this.resume = resume;
        this.dureeEssai = dureeEssai;
        this.contratAssurance = contrat;
        this.nomEnseignant = "";
        
        this.StatutPedagogique = "En Cours";
        this.StatutAdministratif = "En Cours";
        this.StatutJuridique = "En Cours";
        
        this.etudiant = etu;
        this.formation = form;
        this.entreprise = entp;
        
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
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
