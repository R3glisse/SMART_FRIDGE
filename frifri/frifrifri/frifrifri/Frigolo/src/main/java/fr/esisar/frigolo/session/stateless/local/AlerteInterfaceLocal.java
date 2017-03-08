package fr.esisar.frigolo.session.stateless.local;

import java.util.List;

import javax.ejb.Local;

import fr.esisar.frigolo.entities.AlerteEJBEntity;

@Local
public interface AlerteInterfaceLocal {

    /**
     * method which permit to find all alerts in the database
     *
     * @return a list of alert entity
     */
    public List<AlerteEJBEntity> findAlerteEJBEntity();

    /**
     * method which permit to find alerts in the database looking by id
     *
     * @param id
     *            the identifier of the alert to find
     * @return an alert entity
     */
    public AlerteEJBEntity findAlerteEJBEntityById(Long id);

    /**
     * method which permit to delete an alert in the database
     *
     * @param alerteEJBEntity
     *            the alert entity to delete
     */
    public void deleteAlerteEJBEntity(AlerteEJBEntity alerteEJBEntity);

    /**
     * method which permit to create an alert in the database
     *
     * @param alerteEjb
     *            the alert entity to create
     * @param id
     *            the identifier of the fridge from which the alert comes
     */
    public void createAlerteEJBEntity(AlerteEJBEntity alerteEjb, Long id);

}
