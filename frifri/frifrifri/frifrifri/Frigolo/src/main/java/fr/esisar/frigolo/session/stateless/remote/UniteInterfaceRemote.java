package fr.esisar.frigolo.session.stateless.remote;

import java.util.List;

import javax.ejb.Remote;

import fr.esisar.frigolo.entities.UniteEJBEntity;

@Remote
public interface UniteInterfaceRemote {

    /**
     * method which permit to find a unit in the database
     *
     * @return a list of units entities
     */
    public List<UniteEJBEntity> findUniteEJBEntity();

    /**
     * method which permit to create a unit in the database
     *
     * @param uniteEjb
     *            the unit entity to create
     * @return the unit entity created
     */
    public UniteEJBEntity createUniteEJBEntity(UniteEJBEntity uniteEjb);

    /**
     * method which permit to delete a unit in the database
     *
     * @param uniteEJB
     *            the unit entity to delete
     */
    public void deleteUniteEJBEntity(UniteEJBEntity uniteEJB);
}
