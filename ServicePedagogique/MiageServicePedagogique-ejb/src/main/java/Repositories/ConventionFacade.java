/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import Entities.Convention;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Quentin
 */
@Stateless
public class ConventionFacade extends AbstractFacade<Convention> implements ConventionFacadeLocal {

    @PersistenceContext(unitName = "miage.project_MiageServicePedagogique-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConventionFacade() {
        super(Convention.class);
    }
    
}
