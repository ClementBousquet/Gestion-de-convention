/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Entities.Convention;
import Repositories.ConventionFacadeLocal;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Quentin
 */
@Stateless
public class GestionConv implements GestionConvLocal {

    @EJB
    ConventionFacadeLocal convLocal;
    
    @Override
    public void createConvention(Long idConvention, Date dateDebut, Date dateFin, String statut, String resume, String intule, String niveau, String profref) {
        convLocal.create(new Convention(idConvention, dateDebut, dateFin, statut, resume, intule, niveau, profref));
    }

    @Override
    public List<Convention> getConventions() {
        return convLocal.findAll();
    }
    
    @Override
    public void setProfRef(Long idConv, String prof) {
        Convention conv = convLocal.find(idConv);
        conv.setProf_ref(prof);
        convLocal.edit(conv);
    }

    @Override
    public Convention getConventionById(Long idConv) {
        return convLocal.getConventionById(idConv);
    }

    @Override
    public Convention getConvention(Long id) {
        return convLocal.find(id);
    }
}
