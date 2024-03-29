/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Entities.Convention;
import Entities.Entreprise;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import Entities.Etudiant;
import Entities.Formation;

/**
 *
 * @author Quentin
 */
@Local
public interface GestionBDSLocal {
    
    void creerEtudiant(String nom, String prenom, String password, int num, Long idForm);
    void creerEntreprise(String nom, int siren);
    void creerFormation(String intitule, String niv, String dep, String code);
    void creerConvention(int annee, String datedeb, String datefin, int gratification, String resume, int dureeEssai, int contrat, String nomE, int sirenE, Long idEtu);
    void modifierConvention(Long id, String prof);
    
    void setStatutJuridique(Long id, String value);
    void setStatutAdministratif(Long id, String value);
    void setStatutPedagogique(Long id, String value);
    
    void genererDataTest();
    
    Long getEtudiant(String pseudo, String pass);
    Long getEntreprise(int siren);
    List<Convention> getConventionsEtp(Long idEntp);
    List<Convention> getConventionsEtu(Long idEtu);
    Convention getConvention(Long idConv);
    List<Formation> getFormation();
    List<Etudiant> getEtudiants();
}
