package fr.esisar.frigolo.session.stateless.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.esisar.frigolo.entities.FrigidaireEJBEntity;
import fr.esisar.frigolo.session.stateless.local.FrigidaireInterfaceLocal;
import fr.esisar.frigolo.session.stateless.remote.FrigidaireInterfaceRemote;

@Stateless
public class FrigidaireEJBStateless implements FrigidaireInterfaceRemote, FrigidaireInterfaceLocal {
    /**
     *
     */
    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    /*
     * (non-Javadoc)
     *
     * @see
     * fr.esisar.frigolo.session.stateless.remote.FrigidaireInterfaceRemote#
     * findFrigidaireEJBEntity()
     */
    public List<FrigidaireEJBEntity> findFrigidaireEJBEntity() {
        TypedQuery<FrigidaireEJBEntity> query = em.createNamedQuery("findFrigidaires", FrigidaireEJBEntity.class);
        return query.getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * fr.esisar.frigolo.session.stateless.remote.FrigidaireInterfaceRemote#
     * findFrigidaireEJBEntityById(java.lang.Long)
     */
    public FrigidaireEJBEntity findFrigidaireEJBEntityById(Long id) {
        TypedQuery<FrigidaireEJBEntity> query = em.createNamedQuery("findFrigidaireEJBEntityById",
                FrigidaireEJBEntity.class);
        query.setParameter("idFrigidaire", id);
        return query.getSingleResult();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * fr.esisar.frigolo.session.stateless.remote.FrigidaireInterfaceRemote#
     * createFrigidaireEJBEntity(fr.esisar.frigolo.entities.FrigidaireEJBEntity)
     */
    public void createFrigidaireEJBEntity(FrigidaireEJBEntity frigidaireEJB) {
        em.persist(frigidaireEJB);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * fr.esisar.frigolo.session.stateless.remote.FrigidaireInterfaceRemote#
     * deleteFrigidaireEJBEntity(fr.esisar.frigolo.entities.FrigidaireEJBEntity)
     */
    public void deleteFrigidaireEJBEntity(FrigidaireEJBEntity frigidaireEJB) {
        em.remove(em.merge(frigidaireEJB));
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * fr.esisar.frigolo.session.stateless.remote.FrigidaireInterfaceRemote#
     * updateFrigidaireEJBEntity(fr.esisar.frigolo.entities.FrigidaireEJBEntity)
     */
    public FrigidaireEJBEntity updateFrigidaireEJBEntity(FrigidaireEJBEntity frigidaireEJB) {
        return em.merge(frigidaireEJB);
    }

}
