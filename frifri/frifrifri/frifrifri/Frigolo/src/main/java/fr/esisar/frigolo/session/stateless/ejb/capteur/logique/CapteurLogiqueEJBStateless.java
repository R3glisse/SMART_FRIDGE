
package fr.esisar.frigolo.session.stateless.ejb.capteur.logique;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.esisar.frigolo.entities.CapteurLogiqueEJBEntity;
import fr.esisar.frigolo.entities.FrigidaireEJBEntity;
import fr.esisar.frigolo.entities.TypeCapteurEJBEntity;
import fr.esisar.frigolo.session.stateless.local.capteur.logique.CapteurLogiqueInterfaceLocal;
import fr.esisar.frigolo.session.stateless.remote.capteur.logique.CapteurLogiqueInterfaceRemote;

@Stateless
public class CapteurLogiqueEJBStateless implements CapteurLogiqueInterfaceRemote, CapteurLogiqueInterfaceLocal {
    /**
     *
     */
    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    /*
     * (non-Javadoc)
     *
     * @see fr.esisar.frigolo.session.stateless.remote.capteur.logique.
     * CapteurLogiqueInterfaceRemote#findCapteurLogiqueEJBEntity()
     */
    public List<CapteurLogiqueEJBEntity> findCapteurLogiqueEJBEntity() {
        TypedQuery<CapteurLogiqueEJBEntity> query = em.createNamedQuery("findCapteursLogiques",
                CapteurLogiqueEJBEntity.class);
        return query.getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.esisar.frigolo.session.stateless.remote.capteur.logique.
     * CapteurLogiqueInterfaceRemote#findCapteurLogiqueEJBEntityById(java.lang.
     * Long)
     */
    public CapteurLogiqueEJBEntity findCapteurLogiqueEJBEntityById(Long id) {
        TypedQuery<CapteurLogiqueEJBEntity> query = em.createNamedQuery("findCapteurLogiqueEJBEntityById",
                CapteurLogiqueEJBEntity.class);
        query.setParameter("idCapteurLogique", id);
        return query.getSingleResult();
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.esisar.frigolo.session.stateless.remote.capteur.logique.
     * CapteurLogiqueInterfaceRemote#createCapteurLogiqueEJBEntity(fr.esisar.
     * frigolo.entities.CapteurLogiqueEJBEntity, java.lang.Long, java.lang.Long)
     */
    public void createCapteurLogiqueEJBEntity(CapteurLogiqueEJBEntity capteurLogiqueEjb, Long idFrigidaire,
            Long idTypeCapteur) {

        FrigidaireEJBEntity frigidaire = em.find(FrigidaireEJBEntity.class, idFrigidaire);
        TypeCapteurEJBEntity typeCapteur = em.find(TypeCapteurEJBEntity.class, idTypeCapteur);

        if (frigidaire != null && typeCapteur != null) {
            em.persist(capteurLogiqueEjb);
            frigidaire.getCapteursLogiques().add(capteurLogiqueEjb);
            capteurLogiqueEjb.setFrigidaire(frigidaire);

            typeCapteur.getCapteursLogiques().add(capteurLogiqueEjb);
            capteurLogiqueEjb.setTypeCapteur(typeCapteur);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.esisar.frigolo.session.stateless.remote.capteur.logique.
     * CapteurLogiqueInterfaceRemote#deleteCapteurLogiqueEJBEntity(fr.esisar.
     * frigolo.entities.CapteurLogiqueEJBEntity)
     */
    public void deleteCapteurLogiqueEJBEntity(CapteurLogiqueEJBEntity capteurLogiqueEjb) {
        em.remove(em.merge(capteurLogiqueEjb));
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.esisar.frigolo.session.stateless.remote.capteur.logique.
     * CapteurLogiqueInterfaceRemote#findCapteursLogiquesByTypeCapteurId(java.
     * lang.Long)
     */
    public List<CapteurLogiqueEJBEntity> findCapteursLogiquesByTypeCapteurId(Long typeCapteurId) {
        TypedQuery<CapteurLogiqueEJBEntity> query = em.createNamedQuery("findCapteursLogiquesByTypeCapteurId",
                CapteurLogiqueEJBEntity.class);
        query.setParameter("idTypeCapteur", typeCapteurId);
        return query.getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.esisar.frigolo.session.stateless.remote.capteur.logique.
     * CapteurLogiqueInterfaceRemote#findCapteursLogiquesByTypeFrigidaireId(java
     * .lang.Long)
     */
    public List<CapteurLogiqueEJBEntity> findCapteursLogiquesByTypeFrigidaireId(Long frigidaireId) {
        TypedQuery<CapteurLogiqueEJBEntity> query = em.createNamedQuery("findCapteursLogiquesByTypeFrigidaireId",
                CapteurLogiqueEJBEntity.class);
        query.setParameter("idFrigidaire", frigidaireId);
        return query.getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.esisar.frigolo.session.stateless.remote.capteur.logique.
     * CapteurLogiqueInterfaceRemote#findCapteursUsedInFrigidaireEJBEntity()
     */
    public List<FrigidaireEJBEntity> findCapteursUsedInFrigidaireEJBEntity() {
        TypedQuery<FrigidaireEJBEntity> query = em.createNamedQuery("findCapteursLogiquesUsedInFrigidaireEJBEntity",
                FrigidaireEJBEntity.class);
        return query.getResultList();
    }
}
