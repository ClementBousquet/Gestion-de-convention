/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServicesP;

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
public interface ServicePedagLocal {
    
    void createConvention(Long idConvention, String dateDebut, String dateFin, String statut, String resume, String intule,  String niveau, String profref);
    void setProfRef(Long idConv, String prof);
    void annulerConvention(Long idConv);
    void validerConvention(Long idConv);
    List<String> getConventions();
    HashMapWrapper getConvention(Long id);
    
}
