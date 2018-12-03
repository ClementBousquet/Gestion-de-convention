/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RepositoriesP;

import EntitiesP.ConventionR2;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Quentin
 */
@Local
public interface ConventionR2FacadeLocal {

    void create(ConventionR2 conventionR2);

    void edit(ConventionR2 conventionR2);

    void remove(ConventionR2 conventionR2);

    ConventionR2 find(Object id);
    
    ConventionR2 getConventionById(Object id);

    List<ConventionR2> findAll();

    List<ConventionR2> findRange(int[] range);

    int count();
    
}
