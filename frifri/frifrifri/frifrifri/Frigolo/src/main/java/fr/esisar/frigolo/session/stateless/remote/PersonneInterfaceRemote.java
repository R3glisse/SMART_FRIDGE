package fr.esisar.frigolo.session.stateless.remote;

import java.util.List;

import javax.ejb.Remote;

import fr.esisar.frigolo.entities.PersonneEJBEntity;

@Remote
public interface PersonneInterfaceRemote {

    /**
     * method which permit to find persons in the database
     *
     * @return a list of people
     */
    public List<PersonneEJBEntity> findPersonneEJBEntity();

    /**
     * method which permit to find persons in the database looking by id
     *
     * @param id
     *            the identifier of the person to find
     * @return a person entity
     */
    public PersonneEJBEntity findPersonneEJBEntityById(Long id);

    /**
     * method which permit to create a person in the database
     *
     * @param personneEJBEntity
     *            the person entity to create
     * @return the created person entity
     */
    public PersonneEJBEntity createPersonneEJBEntity(PersonneEJBEntity personneEJBEntity);

    /**
     * method which permit to delete a person in the database
     *
     * @param personneEJBEntity
     *            the entity to delete
     */
    public void deletePersonneEJBEntity(PersonneEJBEntity personneEJBEntity);

    /**
     * method which permit to update person's properties in the database
     *
     * @param personneEJBEntity
     *            the entity to update
     * @return the updated entity
     */
    public PersonneEJBEntity updatePersonneEJBEntity(PersonneEJBEntity personneEJBEntity);

}
