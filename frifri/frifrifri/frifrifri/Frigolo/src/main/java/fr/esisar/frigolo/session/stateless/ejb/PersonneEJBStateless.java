package fr.esisar.frigolo.session.stateless.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.esisar.frigolo.entities.PersonneEJBEntity;
import fr.esisar.frigolo.session.stateless.local.PersonneInterfaceLocal;
import fr.esisar.frigolo.session.stateless.remote.PersonneInterfaceRemote;

@Stateless
public class PersonneEJBStateless implements PersonneInterfaceRemote, PersonneInterfaceLocal {
    /**
     *
     */
    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    /*
     * (non-Javadoc)
     *
     * @see fr.esisar.frigolo.session.stateless.remote.PersonneInterfaceRemote#
     * findPersonneEJBEntity()
     */
    public List<PersonneEJBEntity> findPersonneEJBEntity() {
        TypedQuery<PersonneEJBEntity> query = em.createNamedQuery("findPersonnes", PersonneEJBEntity.class);
        return query.getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.esisar.frigolo.session.stateless.remote.PersonneInterfaceRemote#
     * findPersonneEJBEntityById(java.lang.Long)
     */
    public PersonneEJBEntity findPersonneEJBEntityById(Long id) {
        TypedQuery<PersonneEJBEntity> query = em.createNamedQuery("findPersonneEJBEntityById", PersonneEJBEntity.class);
        query.setParameter("idPersonne", id);
        return query.getSingleResult();
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.esisar.frigolo.session.stateless.remote.PersonneInterfaceRemote#
     * createPersonneEJBEntity(fr.esisar.frigolo.entities.PersonneEJBEntity)
     */
    public PersonneEJBEntity createPersonneEJBEntity(PersonneEJBEntity personneEjb) {
        em.persist(personneEjb);
        return personneEjb;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.esisar.frigolo.session.stateless.remote.PersonneInterfaceRemote#
     * deletePersonneEJBEntity(fr.esisar.frigolo.entities.PersonneEJBEntity)
     */
    public void deletePersonneEJBEntity(PersonneEJBEntity personneEjb) {
        em.remove(em.merge(personneEjb));
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.esisar.frigolo.session.stateless.remote.PersonneInterfaceRemote#
     * updatePersonneEJBEntity(fr.esisar.frigolo.entities.PersonneEJBEntity)
     */
    public PersonneEJBEntity updatePersonneEJBEntity(PersonneEJBEntity personneEjb) {
        return em.merge(personneEjb);
    }

}
