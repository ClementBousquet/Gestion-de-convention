/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Util.HashMapWrapper;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author Quentin
 */
@Local
public interface ServiceBDSLocal {
    
    void creerEtudiant(String nom, String prenom, String password, int num, Long idForm);
    void creerEntreprise(String nom, int siren);
    void creerFormation(String intitule, String niv, String dep, String code);
    void creerConvention(int annee, Date datedeb, Date datefin, int gratification, String resume, int dureeEssai, int contrat, String nomE, int sirenE, Long idEtu);

    void modifierConvention(Long id, String prof);
    
    void setStatutJuridique(Long id, String value);
    void setStatutAdministratif(Long id, String value);
    void setStatutPedagogique(Long id, String value);
    
    Long getEtudiant(String pseudo, String pass);
    List<String> getConventions(Long idEtu);
    HashMapWrapper getConvention(Long idConv);
    
}
