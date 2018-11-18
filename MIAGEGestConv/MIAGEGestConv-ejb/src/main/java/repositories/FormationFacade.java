/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import miage.project.entities.Formation;

/**
 *
 * @author Quentin
 */
@Stateless
public class FormationFacade extends AbstractFacade<Formation> implements FormationFacadeLocal {

    @PersistenceContext(unitName = "miage.project_MIAGEGestConv-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FormationFacade() {
        super(Formation.class);
    }
    
}
