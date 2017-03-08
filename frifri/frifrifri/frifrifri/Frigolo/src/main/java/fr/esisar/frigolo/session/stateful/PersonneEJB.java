package fr.esisar.frigolo.session.stateful;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.esisar.frigolo.entities.PersonneEJBEntity;
import fr.esisar.frigolo.session.stateless.local.PersonneInterfaceLocal;

@Stateful
public class PersonneEJB {

    /**
     * a stateless that is used to query database
     */
    @EJB
    private PersonneInterfaceLocal personneEJBStateless;

    /**
     * get all the people in the database
     *
     * @return a list of the people in the database
     */
    public List<PersonneEJBEntity> findPersonnes() {
        return personneEJBStateless.findPersonneEJBEntity();
    }

    /**
     * add a person in the database
     *
     * @param login
     *            : the login of the person
     * @param password
     *            : the password of the person
     * @return the entity associated
     */
    public PersonneEJBEntity ajouterPersonne(String login, String password) {
        PersonneEJBEntity personne = new PersonneEJBEntity(login, password);
        return personneEJBStateless.createPersonneEJBEntity(personne);
    }

    /**
     * delete the person from the database
     *
     * @param personneEJBStatelessEntity
     *            : the entity to suppress
     */
    public void deletePersonne(PersonneEJBEntity personneEJBStatelessEntity) {
        this.personneEJBStateless.deletePersonneEJBEntity(personneEJBStatelessEntity);
    }

}
