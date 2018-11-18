/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.List;
import javax.ejb.Local;
import miage.project.entities.Convention;
import miage.project.entities.Etudiant;
import miage.project.entities.Formation;

/**
 *
 * @author Quentin
 */
@Local
public interface GestionBDSLocal {
    
    void creerEtudiant(String nom, String prenom, String password, int num, Long idForm);
    void creerEntreprise(String nom, int siren);
    void creerFormation(int intitule, String niv, String dep, String code);
    void creerConvention(int annee, int duree, int gratification, String resume, int dureeEssai, int contrat, String nomE, int sirenE, Long idEtu);
    void modifierConvention(Long id, String prof);
    
    void setStatutJuridique(Long id, String value);
    void setStatutAdministratif(Long id, String value);
    void setStatutPedagogique(Long id, String value);
    
    Etudiant getEtudiant(String pseudo, String pass);
    List<Convention> getConvention(Long idEtu);
    List<Formation> getFormation();
    List<Etudiant> getEtudiants();
}
