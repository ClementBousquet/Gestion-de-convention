/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import JMS.MessageSenderBean;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jms.JMSException;
import javax.naming.NamingException;
import Entities.Convention;
import Entities.Entreprise;
import Entities.Etudiant;
import Entities.Formation;
import org.apache.log4j.Logger;
import Repositories.ConventionFacadeLocal;
import Repositories.EntrepriseFacadeLocal;
import Repositories.EtudiantFacadeLocal;
import Repositories.FormationFacadeLocal;

/**
 *
 * @author Quentin
 */
@Stateless
public class GestionBDS implements GestionBDSLocal {

    final static Logger log4j = Logger.getLogger(GestionBDS.class);
    
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
        log4j.debug("creerEtudiant");
        etudiantFacade.create(new Etudiant(nom, prenom, password, num, formationFacade.find(idForm)));
    }

    @Override
    public void creerEntreprise(String nom, int siren) {
        log4j.debug("creerEntreprise");
        entrepriseFacade.create(new Entreprise(nom, siren));
    }

    @Override
    public void creerFormation(String intitule, String niv, String dep, String code) {
        log4j.debug("creerFormation");
        formationFacade.create(new Formation(intitule, niv, dep, code));
    }

    @Override
    public void creerConvention(int annee, Date datedeb, Date datefin, int gratification, String resume, int dureeEssai, int contrat, String nomE, int sirenE, Long idEtu) {
        log4j.debug("creerConvention");
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
        
        Convention  myConv = new Convention(annee, datedeb, datefin, gratification, resume, dureeEssai, contrat, entpComp ,etudiantFacade.find(idEtu) ,formationFacade.find(etudiantFacade.find(idEtu).getForm()));
        
        conventionFacade.create(myConv);
        
        Convention conv = conventionFacade.findByEtuAndYear(etudiantFacade.find(idEtu), datedeb, datefin);

        MessageSenderBean msb = new MessageSenderBean();
        try {
            msb.sendJMSMessageToMyTopic(conv);
        } catch (JMSException ex) {
            log4j.error("JMSException");
        } catch (NamingException ex) {
            log4j.error("NamingException");
        }
        
        Etudiant et = etudiantFacade.find(idEtu);
        List<Convention> updatedList = et.getConvs();
        updatedList.add(myConv);
        et.setConvs(updatedList);
        
        etudiantFacade.edit(et);

    }

    @Override
    public void modifierConvention(Long id, String prof) {
        log4j.debug("modifierConvention");
        Convention conv = conventionFacade.find(id);
        conv.setNomEnseignant(prof);
        conventionFacade.edit(conv);
    }

    @Override
    public void setStatutJuridique(Long id, String value) {
        log4j.debug("setStatutJuridique");
        Convention conv = conventionFacade.find(id);
        conv.setStatutJuridique(value);
        conventionFacade.edit(conv);
    }

    @Override
    public void setStatutAdministratif(Long id, String value) {
        log4j.debug("setStatutAdministratif");
        Convention conv = conventionFacade.find(id);
        conv.setStatutAdministratif(value);
        conventionFacade.edit(conv);
    }

    @Override
    public void setStatutPedagogique(Long id, String value) {
        log4j.debug("setStatutPedagogique");
        Convention conv = conventionFacade.find(id);
        conv.setStatutPedagogique(value);
        conventionFacade.edit(conv);
    }

    @Override
    public Etudiant getEtudiant(String pseudo, String pass) {
        log4j.debug("getEtudiant");
        List<Etudiant> etudiants = etudiantFacade.findAll();
        
        for (int i = 0; i < etudiants.size(); i++) {
            if (etudiants.get(i).getPseudo().equals(pseudo) && etudiants.get(i).getPassword().equals(pass))
                return etudiants.get(i);
        }
        
        return null;
        
    }

    @Override
    public List<Convention> getConventions(Long idEtu) {
        log4j.debug("getConventions");
        return etudiantFacade.find(idEtu).getConvs();
    }

    @Override
    public List<Formation> getFormation() {
        log4j.debug("getFormation");
        return formationFacade.findAll();
    }

    @Override
    public List<Etudiant> getEtudiants() {
        log4j.debug("getEtudiants");
        return etudiantFacade.findAll();
    }

    @Override
    public Convention getConvention(Long idConv) {
        log4j.debug("getConvention");
        return conventionFacade.find(idConv);
    }

}
