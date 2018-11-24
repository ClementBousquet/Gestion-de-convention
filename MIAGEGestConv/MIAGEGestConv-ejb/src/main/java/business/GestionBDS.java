/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import miage.project.entities.Convention;
import miage.project.entities.Entreprise;
import miage.project.entities.Etudiant;
import miage.project.entities.Formation;
import repositories.ConventionFacadeLocal;
import repositories.EntrepriseFacadeLocal;
import repositories.EtudiantFacadeLocal;
import repositories.FormationFacadeLocal;

/**
 *
 * @author Quentin
 */
@Stateless
public class GestionBDS implements GestionBDSLocal {

    @EJB
    private EtudiantFacadeLocal etudiantFacade;
    
    @EJB
    private EntrepriseFacadeLocal entrepriseFacade;
    
    @EJB
    private FormationFacadeLocal formationFacade;
    
    @EJB
    private ConventionFacadeLocal conventionFacade;
    
    @Override
    public void creerEtudiant(String nom, String prenom, String password, int num, Long idForm) {
        etudiantFacade.create(new Etudiant(nom, prenom, password, num, formationFacade.find(idForm)));
    }

    @Override
    public void creerEntreprise(String nom, int siren) {
        entrepriseFacade.create(new Entreprise(nom, siren));
    }

    @Override
    public void creerFormation(int intitule, String niv, String dep, String code) {
        formationFacade.create(new Formation(intitule, niv, dep, code));
    }

    @Override
    public void creerConvention(int annee, int duree, int gratification, String resume, int dureeEssai, int contrat, String nomE, int sirenE, Long idEtu) {
        List<Entreprise> entreprises = entrepriseFacade.findAll();
        Entreprise entpComp = new Entreprise(nomE, sirenE);
        
        int cpt = 0;
        Long idEntp = 0L;
        for (int i = 0; i < entreprises.size(); i++) {
            if (entreprises.get(i).equals(entpComp)) {
                cpt = 1;
                idEntp = entreprises.get(i).getId();
            }
        }
        
        if (cpt == 0)
            entrepriseFacade.create(entpComp);
        else 
           entpComp = entrepriseFacade.find(idEntp);
        
        Convention  myConv = new Convention(annee, duree, gratification, resume, dureeEssai, contrat, entpComp ,etudiantFacade.find(idEtu) ,formationFacade.find(etudiantFacade.find(idEtu).getForm()));
        
        conventionFacade.create(myConv);
        
        List<Convention> updatedList = etudiantFacade.find(idEtu).getConvs();
        updatedList.add(myConv);
        
        etudiantFacade.find(idEtu).setConvs(updatedList);
    }

    @Override
    public void modifierConvention(Long id, String prof) {
        Convention conv = conventionFacade.find(id);
        conv.setNomEnseignant(prof);
        conventionFacade.edit(conv);
    }

    @Override
    public void setStatutJuridique(Long id, String value) {
        Convention conv = conventionFacade.find(id);
        conv.setStatutJuridique(value);
        conventionFacade.edit(conv);
    }

    @Override
    public void setStatutAdministratif(Long id, String value) {
        Convention conv = conventionFacade.find(id);
        conv.setStatutAdministratif(value);
        conventionFacade.edit(conv);
    }

    @Override
    public void setStatutPedagogique(Long id, String value) {
        Convention conv = conventionFacade.find(id);
        conv.setStatutPedagogique(value);
        conventionFacade.edit(conv);
    }

    @Override
    public Etudiant getEtudiant(String pseudo, String pass) {
        List<Etudiant> etudiants = etudiantFacade.findAll();
        
        for (int i = 0; i < etudiants.size(); i++) {
            if (etudiants.get(i).getPseudo().equals(pseudo) && etudiants.get(i).getPassword().equals(pass))
                return etudiants.get(i);
        }
        
        return null;
        
    }

    @Override
    public List<Convention> getConventions(Long idEtu) {
        return etudiantFacade.find(idEtu).getConvs();
    }

    @Override
    public List<Formation> getFormation() {
        return formationFacade.findAll();
    }

    @Override
    public List<Etudiant> getEtudiants() {
        return etudiantFacade.findAll();
    }

    @Override
    public Convention getConvention(Long idConv) {
        return conventionFacade.find(idConv);
    }

}
