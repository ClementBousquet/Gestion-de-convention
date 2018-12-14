/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * Represente un etudiant du système GestConv
 * @author Quentin, Clément
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
    
    @JoinColumn
    private Formation form;

    private List<Convention> convs;

    /**
     * Constructeur vide de Etudiant
     */
    protected Etudiant() {
    }
    
    /**
     * Constructeur de Etudiant
     * 
     * @param nom
     *  Nom de l'etudiant
     * @param prenom
     *  Prenom de l'etudiant
     * @param password
     *  Password de l'étudiant
     * @param numeroEtu
     *  Numero de l'etudiant
     * @param f
     *  Formation de l'étudiant
     */
    public Etudiant(String nom, String prenom, String password, int numeroEtu, Formation f) {
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.numeroEtu = numeroEtu;
        this.pseudo = this.nom + "." + this.prenom;
        this.form = f;
        this.convs = new ArrayList<Convention>();
    }

    /**
     * Retourne la formation de l'etudiant
     * @return form
     */
    public Formation getForm() {
        return form;
    }

    /**
     * Met à jour la formation de l'étudiant
     * @param form
     *  Formation de l'etudiant
     */
    public void setForm(Formation form) {
        this.form = form;
    }
    
    /**
     * Retourne le numero de l'étudiant
     * @return numeroEtu
     */
    public int getNumeroEtu() {
        return numeroEtu;
    }

    /**
     * Met à jour le numero de l'étudiant
     * @param numeroEtu
     *  Le numero de l'étudiant
     */
    public void setNumeroEtu(int numeroEtu) {
        this.numeroEtu = numeroEtu;
    }

    /**
     * Retourne le nom de l'étudiant
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Met à jour le nom de l'etudiant
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Retourne le prenom de l'etudiant
     * @return prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Met à jour le prenom de l'etudiant
     * @param prenom
     *  Le prenom de l'etudiant
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Retourne le prenom de l'étudiant
     * @return pseudo
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * Met à jour le pseudo de l'étudiant
     * @param pseudo
     *  Le peuso de l'etudiant
     */
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    /**
     * Retourn le password de l'etudiant
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Met à jour le password de l'etudiant
     * @param password
     *  Le password de l'etudiant
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Retourne la liste des conventions de l'étudiant
     * @return convs
     */
    public List<Convention> getConvs() {
        return convs;
    }

    /**
     * Met à jour la liste des conventions de l'étudiant
     * @param convs
     *  La liste des conventions de l'étudiant
     */
    public void setConvs(List<Convention> convs) {
        this.convs = convs;
    }
    
    /**
     * Retourne l'id de l'étudiant
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Met à jour l'id de l'étudiant
     * @param id
     *  L'ID de l'etudiant
     */
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
