/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Business.GestionBDS;
import Business.GestionBDSLocal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import Entities.Convention;
import org.apache.log4j.Logger;

/**
 *
 * @author Quentin
 */
@Stateless
public class ServiceBDS implements ServiceBDSLocal {

    final static Logger log4j = Logger.getLogger(ServiceBDS.class);
    
    @EJB
    GestionBDSLocal gestionBDS;

    @Override
    public void creerEtudiant(String nom, String prenom, String password, int num, Long idForm) {
        log4j.debug("creerEtudiant");
        gestionBDS.creerEtudiant(nom, prenom, password, num, idForm);
    }

    @Override
    public void creerEntreprise(String nom, int siren) {
        log4j.debug("creerEntreprise");
        gestionBDS.creerEntreprise(nom, siren);
    }

    @Override
    public void creerFormation(String intitule, String niv, String dep, String code) {
        log4j.debug("creerFormation");
        gestionBDS.creerFormation(intitule, niv, dep, code);
    }

    @Override
    public void modifierConvention(Long id, String prof) {
        log4j.debug("modifierConvention");
       gestionBDS.modifierConvention(id, prof);
    }

    @Override
    public void setStatutJuridique(Long id, String value) {
        log4j.debug("setStatutJuridique");
        gestionBDS.setStatutJuridique(id, value);
    }

    @Override
    public void setStatutAdministratif(Long id, String value) {
        log4j.debug("setStatutAdministratif");
        gestionBDS.setStatutAdministratif(id, value);
    }

    @Override
    public void setStatutPedagogique(Long id, String value) {
        log4j.debug("setStatutPedagogique");
        gestionBDS.setStatutPedagogique(id, value);
    }

    @Override
    public Long getEtudiant(String pseudo, String pass) {
        log4j.debug("getEtudiant");
        return gestionBDS.getEtudiant(pseudo, pass).getId();
    }

    @Override
    public List<String> getConventions(Long idEtu) {
        log4j.debug("getConventions");
        List<Convention> convs = gestionBDS.getConventions(idEtu);
        List<String> list = new ArrayList();
        
        for (int i = 0; i < convs.size(); i++) {
            list.add(convs.get(i).getId().toString());
        }
        
        return list;
        
    }

    @Override
    public Map<String, String> getConvention(Long idConv) {
        log4j.debug("getConvention");
        
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
        log4j.debug("creerConvention");
        gestionBDS.creerConvention(annee, datedeb, datefin, gratification, resume, dureeEssai, contrat, nomE, sirenE, idEtu);
    }
}
