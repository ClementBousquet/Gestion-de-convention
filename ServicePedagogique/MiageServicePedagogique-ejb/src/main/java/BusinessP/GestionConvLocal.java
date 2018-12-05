/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessP;

import EntitiesP.ConventionR2;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Quentin
 */
@Local
public interface GestionConvLocal {
    
    void createConvention(Long idConvention, String dateDebut, String dateFin, String statut, String resume, String intule,  String niveau, String profref);
    void setProfRef(Long idConv, String prof);
    void delete (Long idConv);
    ConventionR2 getConventionById(Long idConv);
    List<ConventionR2> getConventions();
    ConventionR2 getConvention(Long id);
    
}
