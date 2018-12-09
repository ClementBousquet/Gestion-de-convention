/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Entities.Convention;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import Entities.Entreprise;
import Entities.Etudiant;
import Entities.Formation;
import JMS.MessageSenderBean;
import org.apache.log4j.Logger;
import Repositories.ConventionFacadeLocal;
import Repositories.EntrepriseFacadeLocal;
import Repositories.EtudiantFacadeLocal;
import Repositories.FormationFacadeLocal;
import javax.jms.JMSException;
import javax.naming.NamingException;
/**
 *
 * @author Quentin
 */
@Stateless
public class GestionBDS implements GestionBDSLocal {

    final static Logger log4j = Logger.getLogger(GestionBDS.class);
    
    @EJB(beanName = "EtudiantFacade") 
    private EtudiantFacadeLocal etudiantFacade;
    
    @EJB(beanName = "EntrepriseFacade") 
    private EntrepriseFacadeLocal entrepriseFacade;
    
    @EJB(beanName = "FormationFacade") 
    private FormationFacadeLocal formationFacade;
    
    @EJB(beanName = "ConventionFacade") 
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
    public void creerConvention(int annee, String datedeb, String datefin, int gratification, String resume, int dureeEssai, int contrat, String nomE, int sirenE, Long idEtu) {
        log4j.debug("creerConvention");
        List<Entreprise> entreprises = entrepriseFacade.findAll();
        
        Entreprise entpComp = new Entreprise(nomE, sirenE);
        
        int cpt = 0;
        Long idEntp = 0L;
        for (int i = 0; i < entreprises.size(); i++) {
            if (entreprises.get(i).getSiren() == sirenE) {
                cpt = 1;
                idEntp = entreprises.get(i).getId();
            }
        }
        
        if (cpt == 0) {
            entrepriseFacade.create(entpComp);
            entpComp = entrepriseFacade.findBySiren(sirenE);
        }
        else {
           entpComp = entrepriseFacade.find(idEntp);
        }  
            
        Convention  myConv = new Convention(annee, datedeb, datefin, gratification, resume, dureeEssai, contrat, entpComp ,etudiantFacade.find(idEtu) ,etudiantFacade.find(idEtu).getForm());
        
        conventionFacade.create(myConv);

        Convention conv = conventionFacade.findByEtuEntreprise(etudiantFacade.find(idEtu), entpComp);
        
        log4j.info(conv.getId());
        
        try {
            MessageSenderBean msb = new MessageSenderBean();
            msb.sendJMSMessageToMyTopic(conv);
        } catch (JMSException ex) {
            log4j.error("error sending JMS " + ex.getMessage());
        } catch (NamingException ex) {
            log4j.error("error taking Topic " + ex.getMessage());
        }
        
        Etudiant et = etudiantFacade.find(idEtu);
        List<Convention> updatedList = et.getConvs();
        updatedList.add(myConv);
        et.setConvs(updatedList);
        
        etudiantFacade.edit(et);
        
        updatedList = entpComp.getConvs();
        updatedList.add(myConv);
        entpComp.setConvs(updatedList);
        
        entrepriseFacade.edit(entpComp);
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
    public Long getEtudiant(String pseudo, String pass) {
        log4j.debug("getEtudiant");
        List<Etudiant> etudiants = etudiantFacade.findAll();
        
        for (int i = 0; i < etudiants.size(); i++) {
            if (etudiants.get(i).getPseudo().equals(pseudo) && etudiants.get(i).getPassword().equals(pass))
                return etudiants.get(i).getId();
        }
        
        return 0L;
        
    }

    @Override
    public List<Convention> getConventionsEtu(Long idEtu) {
        log4j.debug("getConventions");
        return etudiantFacade.find(idEtu).getConvs();
    }
    
    @Override
    public List<Convention> getConventionsEtp(Long idEtp) {
        log4j.debug("getConventions");
        return entrepriseFacade.find(idEtp).getConvs();
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

    @Override
    public void genererDataTest() {
        log4j.debug("genereDataTest");
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getEntreprise(int siren) {
        List<Entreprise> entreprises = entrepriseFacade.findAll();
        
        for (int i = 0; i < entreprises.size(); i++) {
            if (entreprises.get(i).getSiren() == siren)
                return entreprises.get(i).getId();
        }
        
        return 0L;
    }

}
