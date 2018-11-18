/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.project.entities;

import java.io.Serializable;
import java.util.List;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Column;
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
public class Etudiant implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(nullable = false)
    private int numeroEtu;
    @Column(nullable = false)
    private String nom;
    @Column(nullable = false)
    private String prenom;
    @Column(nullable = false)
    private String pseudo;
    @Column(nullable = false)
    private String password;
    
    private Formation form;
    
    @OneToMany(cascade=ALL, mappedBy="idConvention")
    private List<Convention> convs;

    protected Etudiant() {
    }
    
    public Etudiant(String nom, String prenom, String password, int numeroEtu, Formation f) {
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.numeroEtu = numeroEtu;
        this.pseudo = this.nom + "." + this.prenom;
        this.form = f;
    }

    public Formation getForm() {
        return form;
    }

    public void setForm(Formation form) {
        this.form = form;
    }
    
    public int getNumeroEtu() {
        return numeroEtu;
    }

    public void setNumeroEtu(int numeroEtu) {
        this.numeroEtu = numeroEtu;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Convention> getConvs() {
        return convs;
    }

    public void setConvs(List<Convention> convs) {
        this.convs = convs;
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
        if (!(object instanceof Etudiant)) {
            return false;
        }
        Etudiant other = (Etudiant) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "miage.project.entities.Etudiant[ id=" + id + " ]";
    }
    
}
