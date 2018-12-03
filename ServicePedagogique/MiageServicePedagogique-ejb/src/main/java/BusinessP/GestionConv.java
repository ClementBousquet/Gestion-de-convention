/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessP;

import EntitiesP.ConventionR2;
import RepositoriesP.ConventionR2FacadeLocal;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.apache.log4j.Logger;

/**
 *
 * @author Quentin
 */
@Stateless
public class GestionConv implements GestionConvLocal {

    final static Logger log4j = Logger.getLogger(GestionConv.class);
    
    @EJB(beanName = "ConventionR2Facade") 
    ConventionR2FacadeLocal convLocal;
    
    @Override
    public void createConvention(Long idConvention, Date dateDebut, Date dateFin, String statut, String resume, String intule, String niveau, String profref) {
        log4j.debug("createConvention");
        convLocal.create(new ConventionR2(idConvention, dateDebut, dateFin, statut, resume, intule, niveau, profref));
    }

    @Override
    public List<ConventionR2> getConventions() {
        log4j.debug("getConvention");
        return convLocal.findAll();
    }
    
    @Override
    public void setProfRef(Long idConv, String prof) {
        log4j.debug("setProfRef");
        ConventionR2 conv = convLocal.find(idConv);
        conv.setProf_ref(prof);
        convLocal.edit(conv);
    }

    @Override
    public ConventionR2 getConventionById(Long idConv) {
        log4j.debug("getConventionById");
        return convLocal.getConventionById(idConv);
    }

    @Override
    public ConventionR2 getConvention(Long id) {
        log4j.debug("getConvention");
        return convLocal.find(id);
    }

    @Override
    public void delete(Long idConv) {
        log4j.debug("delete");
        convLocal.remove(convLocal.find(idConv));
    }
}
