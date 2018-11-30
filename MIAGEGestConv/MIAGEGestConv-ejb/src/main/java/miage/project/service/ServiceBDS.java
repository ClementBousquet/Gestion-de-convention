/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.project.service;

import business.GestionBDSLocal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import miage.project.entities.Convention;

/**
 *
 * @author Quentin
 */
@Stateless
public class ServiceBDS implements ServiceBDSLocal {

    @EJB
    GestionBDSLocal gestionBDS;

    @Override
    public void creerEtudiant(String nom, String prenom, String password, int num, Long idForm) {
        gestionBDS.creerEtudiant(nom, prenom, password, num, idForm);
    }

    @Override
    public void creerEntreprise(String nom, int siren) {
        gestionBDS.creerEntreprise(nom, siren);
    }

    @Override
    public void creerFormation(int intitule, String niv, String dep, String code) {
        gestionBDS.creerFormation(intitule, niv, dep, code);
    }

    @Override
    public void modifierConvention(Long id, String prof) {
       gestionBDS.modifierConvention(id, prof);
    }

    @Override
    public void setStatutJuridique(Long id, String value) {
        gestionBDS.setStatutJuridique(id, value);
    }

    @Override
    public void setStatutAdministratif(Long id, String value) {
        gestionBDS.setStatutAdministratif(id, value);
    }

    @Override
    public void setStatutPedagogique(Long id, String value) {
        gestionBDS.setStatutPedagogique(id, value);
    }

    @Override
    public Long getEtudiant(String pseudo, String pass) {
        return gestionBDS.getEtudiant(pseudo, pass).getId();
    }

    @Override
    public List<String> getConventions(Long idEtu) {
        List<Convention> convs = gestionBDS.getConventions(idEtu);
        List<String> list = new ArrayList();
        
        for (int i = 0; i < convs.size(); i++) {
            list.add(convs.get(i).getId().toString());
        }
        
        return list;
        
    }

    @Override
    public Map<String, String> getConvention(Long idConv) {
        
        Convention conv = gestionBDS.getConvention(idConv);
        
        Map<String, String> map = new HashMap();
        
        map.put("id", conv.getId().toString());
        map.put("nomE", conv.getNomEnseignant());
        map.put("resume",conv.getResume()); 
        //TODO
       
        return map;
    }

    @Override
    public void creerConvention(int annee, Date datedeb, Date datefin, int gratification, String resume, int dureeEssai, int contrat, String nomE, int sirenE, Long idEtu) {
        gestionBDS.creerConvention(annee, datedeb, datefin, gratification, resume, dureeEssai, contrat, nomE, sirenE, idEtu);
    }
}
