
package fr.esisar.frigolo.session.stateless.ejb.capteur.numerique;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.esisar.frigolo.entities.CapteurNumeriqueEJBEntity;
import fr.esisar.frigolo.entities.FrigidaireEJBEntity;
import fr.esisar.frigolo.entities.TypeCapteurEJBEntity;
import fr.esisar.frigolo.session.stateless.local.capteur.numerique.CapteurNumeriqueInterfaceLocal;
import fr.esisar.frigolo.session.stateless.remote.capteur.numerique.CapteurNumeriqueInterfaceRemote;

@Stateless
public class CapteurNumeriqueEJBStateless implements CapteurNumeriqueInterfaceRemote, CapteurNumeriqueInterfaceLocal {

    /**
     *
     */
    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    /*
     * (non-Javadoc)
     *
     * @see fr.esisar.frigolo.session.stateless.remote.capteur.numerique.
     * CapteurNumeriqueInterfaceRemote#findCapteurNumeriqueEJBEntity()
     */
    public List<CapteurNumeriqueEJBEntity> findCapteurNumeriqueEJBEntity() {
        TypedQuery<CapteurNumeriqueEJBEntity> query = em.createNamedQuery("findCapteursNumeriques",
                CapteurNumeriqueEJBEntity.class);
        return query.getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.esisar.frigolo.session.stateless.remote.capteur.numerique.
     * CapteurNumeriqueInterfaceRemote#findCapteurNumeriqueEJBEntityById(java.
     * lang.Long)
     */
    public CapteurNumeriqueEJBEntity findCapteurNumeriqueEJBEntityById(Long id) {
        TypedQuery<CapteurNumeriqueEJBEntity> query = em.createNamedQuery("findCapteurNumeriqueEJBEntityById",
                CapteurNumeriqueEJBEntity.class);
        query.setParameter("idCapteurNumerique", id);
        if (query.getResultList().isEmpty()) {
            return null;
        }
        return query.getSingleResult();
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.esisar.frigolo.session.stateless.remote.capteur.numerique.
     * CapteurNumeriqueInterfaceRemote#createCapteurNumeriqueEJBEntity(fr.esisar
     * .frigolo.entities.CapteurNumeriqueEJBEntity, java.lang.Long,
     * java.lang.Long)
     */
    public void createCapteurNumeriqueEJBEntity(CapteurNumeriqueEJBEntity capteurNumeriqueEjb, Long idFrigidaire,
            Long idTypeCapteur) {

        FrigidaireEJBEntity frigidaire = em.find(FrigidaireEJBEntity.class, idFrigidaire);
        TypeCapteurEJBEntity typeCapteur = em.find(TypeCapteurEJBEntity.class, idTypeCapteur);

        if (frigidaire != null && typeCapteur != null) {
            em.persist(capteurNumeriqueEjb);
            frigidaire.getCapteursNumeriques().add(capteurNumeriqueEjb);
            capteurNumeriqueEjb.setFrigidaire(frigidaire);

            typeCapteur.getCapteursNumeriques().add(capteurNumeriqueEjb);
            capteurNumeriqueEjb.setTypeCapteur(typeCapteur);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.esisar.frigolo.session.stateless.remote.capteur.numerique.
     * CapteurNumeriqueInterfaceRemote#deleteCapteurNumeriqueEJBEntity(fr.esisar
     * .frigolo.entities.CapteurNumeriqueEJBEntity)
     */
    public void deleteCapteurNumeriqueEJBEntity(CapteurNumeriqueEJBEntity capteurNumeriqueEjb) {
        em.remove(em.merge(capteurNumeriqueEjb));
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.esisar.frigolo.session.stateless.remote.capteur.numerique.
     * CapteurNumeriqueInterfaceRemote#findCapteursNumeriquesByTypeCapteurId(
     * java.lang.Long)
     */
    public List<CapteurNumeriqueEJBEntity> findCapteursNumeriquesByTypeCapteurId(Long typeCapteurId) {
        TypedQuery<CapteurNumeriqueEJBEntity> query = em.createNamedQuery("findCapteursNumeriquesByTypeCapteurId",
                CapteurNumeriqueEJBEntity.class);
        query.setParameter("idTypeCapteur", typeCapteurId);
        return query.getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.esisar.frigolo.session.stateless.remote.capteur.numerique.
     * CapteurNumeriqueInterfaceRemote#findCapteursNumeriquesByFrigidaireId(java
     * .lang.Long)
     */
    public List<CapteurNumeriqueEJBEntity> findCapteursNumeriquesByFrigidaireId(Long frigidaireId) {
        TypedQuery<CapteurNumeriqueEJBEntity> query = em.createNamedQuery("findCapteursNumeriquesByFrigidaireId",
                CapteurNumeriqueEJBEntity.class);
        query.setParameter("idFrigidaire", frigidaireId);
        return query.getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.esisar.frigolo.session.stateless.remote.capteur.numerique.
     * CapteurNumeriqueInterfaceRemote#findCapteursUsedInFrigidaireEJBEntity()
     */
    public List<FrigidaireEJBEntity> findCapteursUsedInFrigidaireEJBEntity() {
        TypedQuery<FrigidaireEJBEntity> query = em.createNamedQuery("findCapteursNumeriquesUsedInFrigidaireEJBEntity",
                FrigidaireEJBEntity.class);
        return query.getResultList();
    }

}
