
package fr.esisar.frigolo.session.stateless.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.esisar.frigolo.entities.UniteEJBEntity;
import fr.esisar.frigolo.session.stateless.local.UniteInterfaceLocal;
import fr.esisar.frigolo.session.stateless.remote.UniteInterfaceRemote;

@Stateless
public class UniteEJBStateless implements UniteInterfaceRemote, UniteInterfaceLocal {

    /**
     *
     */
    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    /*
     * (non-Javadoc)
     *
     * @see fr.esisar.frigolo.session.stateless.remote.UniteInterfaceRemote#
     * findUniteEJBEntity()
     */
    public List<UniteEJBEntity> findUniteEJBEntity() {
        TypedQuery<UniteEJBEntity> query = em.createNamedQuery("findUnites", UniteEJBEntity.class);
        return query.getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.esisar.frigolo.session.stateless.remote.UniteInterfaceRemote#
     * createUniteEJBEntity(fr.esisar.frigolo.entities.UniteEJBEntity)
     */
    public UniteEJBEntity createUniteEJBEntity(UniteEJBEntity uniteEjb) {
        em.persist(uniteEjb);
        return uniteEjb;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.esisar.frigolo.session.stateless.remote.UniteInterfaceRemote#
     * deleteUniteEJBEntity(fr.esisar.frigolo.entities.UniteEJBEntity)
     */
    public void deleteUniteEJBEntity(UniteEJBEntity uniteEJB) {
        em.remove(em.merge(uniteEJB));
    }
}
