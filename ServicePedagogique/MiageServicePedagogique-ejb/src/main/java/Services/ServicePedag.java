/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Business.GestionConvLocal;
import Entities.Convention;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Quentin
 */
@Stateless
public class ServicePedag implements ServicePedagLocal {

    @EJB
    GestionConvLocal gestionLocal;
    
    @Override
    public void createConvention(Long idConvention, Date dateDebut, Date dateFin, String statut, String resume, String intule, String niveau, String profref) {
        gestionLocal.createConvention(idConvention, dateDebut, dateFin, statut, resume, intule, niveau, profref);
    }

    @Override
    public void setProfRef(Long idConv, String prof) {
        gestionLocal.setProfRef(idConv, prof);
    }

    @Override
    public void annulerConvention(Long idConv) {
        //JMS : ENVOIE STATUT NON VALIDE 
    }

    @Override
    public void validerConvention(Long idConv) {
        //JMS : ENVOIE STATUT VALIDE
    }

    @Override
    public List<String> getConventions() {
        
        List<Convention> convs = gestionLocal.getConventions();
        
        List<String> list = new ArrayList();
        
        for (int i = 0; i < convs.size(); i++) {
            list.add(convs.get(i).getId().toString());
        }
        
        return list;
        
    }
    
    @Override
    public Map<String, String> getConvention(Long id) {
        
        Convention conv = gestionLocal.getConvention(id);
        
        Map<String, String> map = new HashMap();
        
        map.put("id", conv.getId().toString());
        map.put("resume",conv.getResume()); 
        map.put("intitule", conv.getIntule());
       
        return map;
    }
}
