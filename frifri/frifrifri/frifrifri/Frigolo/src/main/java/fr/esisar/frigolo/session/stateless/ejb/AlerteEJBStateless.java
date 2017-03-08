
package fr.esisar.frigolo.session.stateless.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.esisar.frigolo.entities.AlerteEJBEntity;
import fr.esisar.frigolo.entities.FrigidaireEJBEntity;
import fr.esisar.frigolo.session.stateless.local.AlerteInterfaceLocal;
import fr.esisar.frigolo.session.stateless.remote.AlerteInterfaceRemote;

@Stateless
public class AlerteEJBStateless implements AlerteInterfaceRemote, AlerteInterfaceLocal {
    /**
     *
     */
    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    /*
     * (non-Javadoc)
     *
     * @see fr.esisar.frigolo.session.stateless.remote.AlerteInterfaceRemote#
     * findAlerteEJBEntity()
     */
    public List<AlerteEJBEntity> findAlerteEJBEntity() {
        TypedQuery<AlerteEJBEntity> query = em.createNamedQuery("findAlertes", AlerteEJBEntity.class);
        return query.getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.esisar.frigolo.session.stateless.remote.AlerteInterfaceRemote#
     * findAlerteEJBEntityById(java.lang.Long)
     */
    public AlerteEJBEntity findAlerteEJBEntityById(Long id) {
        TypedQuery<AlerteEJBEntity> query = em.createNamedQuery("findAlerteEJBEntityById", AlerteEJBEntity.class);
        query.setParameter("idAlerte", id);
        return query.getSingleResult();
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.esisar.frigolo.session.stateless.remote.AlerteInterfaceRemote#
     * deleteAlerteEJBEntity(fr.esisar.frigolo.entities.AlerteEJBEntity)
     */
    public void deleteAlerteEJBEntity(AlerteEJBEntity alerteEjb) {
        em.remove(em.merge(alerteEjb));
    }

    /**
     * @param alerteEjb
     *            the entity to update
     * @return the updated entity
     */
    public AlerteEJBEntity updateAlerteEJBEntity(AlerteEJBEntity alerteEjb) {
        return em.merge(alerteEjb);
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.esisar.frigolo.session.stateless.remote.AlerteInterfaceRemote#
     * createAlerteEJBEntity(fr.esisar.frigolo.entities.AlerteEJBEntity,
     * java.lang.Long)
     */
    public void createAlerteEJBEntity(AlerteEJBEntity alerteEjb, Long id) {

        FrigidaireEJBEntity frigidaire = em.find(FrigidaireEJBEntity.class, id);
        if (frigidaire != null) {
            em.persist(alerteEjb);
            frigidaire.getAlertes().add(alerteEjb);
            alerteEjb.setFrigidaire(frigidaire);
        }
    }

}
