/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.project.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Quentin
 */
@Entity
public class Entreprise implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nom;
    private int siren;
    
    @OneToMany(cascade=ALL, mappedBy="idConvention")
    private List<Convention> convs;
    
    protected Entreprise(){};
    
    public Entreprise (String nom, int siren) {
        this.nom = nom;
        this.siren = siren;
        this.convs = new ArrayList<Convention>();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getSiren() {
        return siren;
    }

    public void setSiren(int siren) {
        this.siren = siren;
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
        if (!(object instanceof Entreprise)) {
            return false;
        }
        Entreprise other = (Entreprise) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "miage.project.entities.Entreprise[ id=" + id + " ]";
    }
    
}
