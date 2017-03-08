
package fr.esisar.frigolo.session.stateless.ejb.mesure.logique;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.esisar.frigolo.entities.CapteurLogiqueEJBEntity;
import fr.esisar.frigolo.entities.MesureLogiqueEJBEntity;
import fr.esisar.frigolo.session.stateless.local.mesure.logique.MesureLogiqueInterfaceLocal;
import fr.esisar.frigolo.session.stateless.remote.mesure.logique.MesureLogiqueInterfaceRemote;

@Stateless
public class MesureLogiqueEJBStateless implements MesureLogiqueInterfaceRemote, MesureLogiqueInterfaceLocal {
    /**
     *
     */
    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    /*
     * (non-Javadoc)
     *
     * @see fr.esisar.frigolo.session.stateless.remote.mesure.logique.
     * MesureLogiqueInterfaceRemote#findCapteursUsedInMesureLogiqueEJBEntity()
     */
    public List<CapteurLogiqueEJBEntity> findCapteursUsedInMesureLogiqueEJBEntity() {
        TypedQuery<CapteurLogiqueEJBEntity> query = em.createNamedQuery("findCapteursUsedInMesureLogiqueEJBEntity",
                CapteurLogiqueEJBEntity.class);
        return query.getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.esisar.frigolo.session.stateless.remote.mesure.logique.
     * MesureLogiqueInterfaceRemote#deleteMesureLogiqueEJBEntity(fr.esisar.
     * frigolo.entities.MesureLogiqueEJBEntity)
     */
    public void deleteMesureLogiqueEJBEntity(MesureLogiqueEJBEntity mesureLogiqueEjb) {
        em.remove(em.merge(mesureLogiqueEjb));
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.esisar.frigolo.session.stateless.remote.mesure.logique.
     * MesureLogiqueInterfaceRemote#findMesureLogiqueEJBEntityFromCapteurId(java
     * .lang.Long)
     */
    public List<MesureLogiqueEJBEntity> findMesureLogiqueEJBEntityFromCapteurId(Long id) {
        TypedQuery<MesureLogiqueEJBEntity> query = em.createNamedQuery("findMesuresLogiquesEJBEntityByCapteurId",
                MesureLogiqueEJBEntity.class);
        query.setParameter("idCapteurLogique", id);
        return query.getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.esisar.frigolo.session.stateless.remote.mesure.logique.
     * MesureLogiqueInterfaceRemote#createMesureLogiqueEJBEntity(fr.esisar.
     * frigolo.entities.MesureLogiqueEJBEntity, java.lang.Long)
     */
    public void createMesureLogiqueEJBEntity(MesureLogiqueEJBEntity mesureLogiqueEjb, Long idCapteurLogique) {

        CapteurLogiqueEJBEntity capteur = em.find(CapteurLogiqueEJBEntity.class, idCapteurLogique);

        if (capteur != null) {
            em.persist(mesureLogiqueEjb);
            capteur.getMesuresLogiques().add(mesureLogiqueEjb);
            mesureLogiqueEjb.setCapteurLogique(capteur);
        }
    }
}
