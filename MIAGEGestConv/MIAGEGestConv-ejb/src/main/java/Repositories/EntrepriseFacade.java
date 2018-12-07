/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import Entities.Convention;
import Entities.Entreprise;
import Entities.Etudiant;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Quentin
 */
@Stateless
public class EntrepriseFacade extends AbstractFacade<Entreprise> implements EntrepriseFacadeLocal {

    @PersistenceContext(unitName = "miage.project_MIAGEGestConv-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EntrepriseFacade() {
        super(Entreprise.class);
    }

    @Override
    public Entreprise findBySiren(int siren) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Entreprise> cq = cb.createQuery(Entreprise.class);
        Root<Entreprise> root = cq.from(Entreprise.class);
        cq.where(
                cb.and(
                        cb.equal(root.get("siren").as(Integer.class), (Integer) siren)
                )
        );
        try {
            return getEntityManager().createQuery(cq).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
}
