/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import Entities.Convention;

/**
 *
 * @author Quentin
 */
@Local
public interface ConventionFacadeLocal {

    void create(Convention convention);

    void edit(Convention convention);

    void remove(Convention convention);

    Convention find(Object id);
    
    Convention findByEtuAndYear(Object etu, Date datedeb, Date datefin);

    List<Convention> findAll();

    List<Convention> findRange(int[] range);

    int count();
    
}
