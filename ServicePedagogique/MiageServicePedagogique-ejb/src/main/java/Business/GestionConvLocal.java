/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Entities.Convention;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Quentin
 */
@Local
public interface GestionConvLocal {
    
    void CreateConvention(Long idConvention, Date dateDebut, Date dateFin, String statut, String resume, String intule,  String niveau, String profref);
    void SetProfRef(Long idConv, String prof);
    List<Convention> GetConventions();
    
}
