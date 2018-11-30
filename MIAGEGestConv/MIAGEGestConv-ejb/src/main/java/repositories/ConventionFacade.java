/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import miage.project.entities.Convention;
import miage.project.entities.Etudiant;

/**
 *
 * @author Quentin
 */
@Stateless
public class ConventionFacade extends AbstractFacade<Convention> implements ConventionFacadeLocal {

    @PersistenceContext(unitName = "miage.project_MIAGEGestConv-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConventionFacade() {
        super(Convention.class);
    }

    @Override
    public Convention findByEtuAndYear(Object etu, Date datedeb, Date datefin) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Convention> cq = cb.createQuery(Convention.class);
        Root<Convention> root = cq.from(Convention.class);
        cq.where(
                cb.and(
                        cb.equal(root.get("etu").as(Etudiant.class), etu),
                        cb.equal(root.get("dateDebut").as(Date.class), datedeb),
                        cb.equal(root.get("dateFin").as(Date.class), datefin)
                )
        );
        return getEntityManager().createQuery(cq).getSingleResult();
    }
    
}
