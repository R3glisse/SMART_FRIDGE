
package fr.esisar.frigolo.session.stateless.ejb.mesure.numerique;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.esisar.frigolo.entities.CapteurNumeriqueEJBEntity;
import fr.esisar.frigolo.entities.MesureNumeriqueEJBEntity;
import fr.esisar.frigolo.entities.UniteEJBEntity;
import fr.esisar.frigolo.session.stateless.local.mesure.numerique.MesureNumeriqueInterfaceLocal;
import fr.esisar.frigolo.session.stateless.remote.mesure.numerique.MesureNumeriqueInterfaceRemote;

@Stateless
public class MesureNumeriqueEJBStateless implements MesureNumeriqueInterfaceRemote, MesureNumeriqueInterfaceLocal {
    /**
     *
     */
    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    /*
     * (non-Javadoc)
     *
     * @see fr.esisar.frigolo.session.stateless.remote.mesure.numerique.
     * MesureNumeriqueInterfaceRemote#findMesureNumeriqueEJBEntity()
     */
    public List<MesureNumeriqueEJBEntity> findMesureNumeriqueEJBEntity() {
        TypedQuery<MesureNumeriqueEJBEntity> query = em.createNamedQuery("findMesuresNumeriques",
                MesureNumeriqueEJBEntity.class);
        return query.getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.esisar.frigolo.session.stateless.remote.mesure.numerique.
     * MesureNumeriqueInterfaceRemote#findMesureNumeriqueEJBEntityFromCapteurId(
     * java.lang.Long)
     */
    public List<MesureNumeriqueEJBEntity> findMesureNumeriqueEJBEntityFromCapteurId(Long id) {
        TypedQuery<MesureNumeriqueEJBEntity> query = em.createNamedQuery("findMesuresNumeriquesEJBEntityByCapteurId",
                MesureNumeriqueEJBEntity.class);
        query.setParameter("idCapteurNumerique", id);
        return query.getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.esisar.frigolo.session.stateless.remote.mesure.numerique.
     * MesureNumeriqueInterfaceRemote#findCapteursUsedInMesureNumeriqueEJBEntity
     * ()
     */
    public List<CapteurNumeriqueEJBEntity> findCapteursUsedInMesureNumeriqueEJBEntity() {
        TypedQuery<CapteurNumeriqueEJBEntity> query = em.createNamedQuery("findCapteursUsedInMesureNumeriqueEJBEntity",
                CapteurNumeriqueEJBEntity.class);
        return query.getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.esisar.frigolo.session.stateless.remote.mesure.numerique.
     * MesureNumeriqueInterfaceRemote#findUniteByMesureEJBEntityId(java.lang.
     * Long)
     */
    public UniteEJBEntity findUniteByMesureEJBEntityId(Long id) {
        TypedQuery<UniteEJBEntity> query = em.createNamedQuery("findUniteByMesureNumeriqueEJBEntityId",
                UniteEJBEntity.class);
        query.setParameter("idMesureNumerique", id);
        return query.getSingleResult();
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.esisar.frigolo.session.stateless.remote.mesure.numerique.
     * MesureNumeriqueInterfaceRemote#deleteMesureNumeriqueEJBEntity(fr.esisar.
     * frigolo.entities.MesureNumeriqueEJBEntity)
     */
    public void deleteMesureNumeriqueEJBEntity(MesureNumeriqueEJBEntity mesureNumeriqueEjb) {
        em.remove(em.merge(mesureNumeriqueEjb));
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.esisar.frigolo.session.stateless.remote.mesure.numerique.
     * MesureNumeriqueInterfaceRemote#createMesureNumeriqueEJBEntity(fr.esisar.
     * frigolo.entities.MesureNumeriqueEJBEntity, java.lang.Long,
     * java.lang.Long)
     */
    public void createMesureNumeriqueEJBEntity(MesureNumeriqueEJBEntity mesureNumeriqueEjb, Long idCapteurNumerique,
            Long idUnite) {

        UniteEJBEntity unite = em.find(UniteEJBEntity.class, idUnite);
        CapteurNumeriqueEJBEntity capteurNumerique = em.find(CapteurNumeriqueEJBEntity.class, idCapteurNumerique);

        if (unite != null && capteurNumerique != null) {

            em.persist(mesureNumeriqueEjb);
            capteurNumerique.getMesuresNumeriques().add(mesureNumeriqueEjb);
            mesureNumeriqueEjb.setCapteurNumerique(capteurNumerique);
            mesureNumeriqueEjb.setUnite(unite);

        }
    }

}
