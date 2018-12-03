/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RepositoriesP;

import EntitiesP.ConventionR2;
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
public class ConventionR2Facade extends AbstractFacade<ConventionR2> implements ConventionR2FacadeLocal {

    @PersistenceContext(unitName = "miage.project_MiageServicePedagogique-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConventionR2Facade() {
        super(ConventionR2.class);
    }

    @Override
    public ConventionR2 getConventionById(Object id) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ConventionR2> cq = cb.createQuery(ConventionR2.class);
        Root<ConventionR2> root = cq.from(ConventionR2.class);
        cq.where(
                cb.and(
                        cb.equal(root.get("idConvRef").as(Long.class), (Long) id)
                )
        );
        return getEntityManager().createQuery(cq).getSingleResult();
    }
    
}
