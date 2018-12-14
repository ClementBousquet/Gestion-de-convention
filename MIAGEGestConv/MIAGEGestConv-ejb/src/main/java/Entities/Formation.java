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

/**
 * Represente une formation du système GestConv
 * @author Quentin, Clément
 */
@Entity
public class Formation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String intitule;
    @Column(nullable = false)
    private String niveau; // Master, Licence
    @Column(nullable = false)
    private String departement;
    @Column(nullable = false)
    private String code;
    
    /**
     * Constructeur vide de Formation
     */
    protected Formation() {
    }
    
    /**
     * Constructeur de formation
     * @param inti
     *  L'intitule de la formation
     * @param niv
     *  Le niveau de la formation (L1, L2, L3, M1 ou M2)
     * @param dep
     *  Le département de la formation
     * @param code
     *  Le code de la formation
     */
    public Formation (String inti, String niv, String dep, String code) {
        this.intitule = inti;
        this.niveau = niv;
        this.departement = dep;
        this.code = code;
    }

    /**
     * Retourne le code de la formation
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * Met à jour le code de la formation
     * @param code
     * Le code de la formation
     */
    public void setCode(String code) {
        this.code = code;
    }
    
    /**
     * Retourne l'id de la formation
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Met à jour l'ID de la formation
     * @param id
     *  L'ID de la formation
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retourne l'intitule de la formation
     * @return intitule
     */
    public String getIntitule() {
        return intitule;
    }

    /**
     * Met à jour l'intitule de la formation
     * @param intitule
     *  L'intitule de la formation
     */
    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    /**
     * Retourne le niveau de la formation
     * @return niveau
     */
    public String getNiveau() {
        return niveau;
    }

    /**
     *Met à jour le niveau de la formation
     * @param niveau
     *  Le niveau de la formation
     */
    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    /**
     * Retourne le departement de la formation
     * @return departement
     */
    public String getDepartement() {
        return departement;
    }

    /**
     * Met à jour le département de la formation
     * @param departement
     *  Le departement de la formation
     */
    public void setDepartement(String departement) {
        this.departement = departement;
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
        if (!(object instanceof Formation)) {
            return false;
        }
        Formation other = (Formation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "miage.project.entities.Formation[ id=" + id + " ]";
    }
    
}
