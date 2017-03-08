package fr.esisar.frigolo.session.stateless.local;

import java.util.List;

import javax.ejb.Local;

import fr.esisar.frigolo.entities.FrigidaireEJBEntity;

@Local
public interface FrigidaireInterfaceLocal {

    /**
     * method which permit to find fridges in the database
     *
     * @return a list of fridge entities
     */
    public List<FrigidaireEJBEntity> findFrigidaireEJBEntity();

    /**
     * method which permit to find fridges in the database looking by id
     *
     * @param id
     *            the identifier of the fridge to find
     * @return a fridge entity
     */
    public FrigidaireEJBEntity findFrigidaireEJBEntityById(Long id);

    /**
     * method which permit to create fridges in the database
     *
     * @param frigidaireEJB
     *            the fridge entity tp create
     */
    public void createFrigidaireEJBEntity(FrigidaireEJBEntity frigidaireEJB);

    /**
     * method which permit to delete fridges in the database
     *
     * @param frigidaireEJBEntity
     *            the fridge entity to delete
     */
    public void deleteFrigidaireEJBEntity(FrigidaireEJBEntity frigidaireEJBEntity);

    /**
     * method which permit to update fridges in the database
     *
     * @param frigidaireEJBEntity
     *            the fridge entity to update
     * @return the updated entity
     */
    public FrigidaireEJBEntity updateFrigidaireEJBEntity(FrigidaireEJBEntity frigidaireEJBEntity);

}
