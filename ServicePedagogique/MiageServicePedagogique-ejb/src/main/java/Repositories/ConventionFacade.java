/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import Entities.Convention;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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

    @Override
    public Convention getConventionById(Long id) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Convention> cq = cb.createQuery(Convention.class);
        Root<Convention> root = cq.from(Convention.class);
        cq.where(
                cb.and(
                        cb.equal(root.get("idConvRef").as(Long.class), id)
                )
        );
        return getEntityManager().createQuery(cq).getSingleResult();
    }
    
}
