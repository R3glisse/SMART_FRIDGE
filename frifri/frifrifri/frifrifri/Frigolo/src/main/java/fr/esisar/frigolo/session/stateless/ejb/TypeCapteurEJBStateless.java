
package fr.esisar.frigolo.session.stateless.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.esisar.frigolo.entities.TypeCapteurEJBEntity;
import fr.esisar.frigolo.session.stateless.local.TypeCapteurInterfaceLocal;
import fr.esisar.frigolo.session.stateless.remote.TypeCapteurInterfaceRemote;

@Stateless
public class TypeCapteurEJBStateless implements TypeCapteurInterfaceRemote, TypeCapteurInterfaceLocal {
    /**
     *
     */
    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    /*
     * (non-Javadoc)
     *
     * @see
     * fr.esisar.frigolo.session.stateless.remote.TypeCapteurInterfaceRemote#
     * findTypeCapteurEJBEntity()
     */
    public List<TypeCapteurEJBEntity> findTypeCapteurEJBEntity() {
        TypedQuery<TypeCapteurEJBEntity> query = em.createNamedQuery("findTypesCapteurs", TypeCapteurEJBEntity.class);
        return query.getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * fr.esisar.frigolo.session.stateless.remote.TypeCapteurInterfaceRemote#
     * createTypeCapteurEJBEntity(fr.esisar.frigolo.entities.
     * TypeCapteurEJBEntity)
     */
    public TypeCapteurEJBEntity createTypeCapteurEJBEntity(TypeCapteurEJBEntity typeCapteurEjb) {
        em.persist(typeCapteurEjb);
        return typeCapteurEjb;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * fr.esisar.frigolo.session.stateless.remote.TypeCapteurInterfaceRemote#
     * deleteTypeCapteurEJBEntity(fr.esisar.frigolo.entities.
     * TypeCapteurEJBEntity)
     */
    public void deleteTypeCapteurEJBEntity(TypeCapteurEJBEntity typeCapteurEJB) {
        em.remove(em.merge(typeCapteurEJB));
    }
}
