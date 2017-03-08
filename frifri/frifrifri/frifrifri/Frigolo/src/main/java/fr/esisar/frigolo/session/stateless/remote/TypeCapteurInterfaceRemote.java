package fr.esisar.frigolo.session.stateless.remote;

import java.util.List;

import javax.ejb.Remote;

import fr.esisar.frigolo.entities.TypeCapteurEJBEntity;

@Remote
public interface TypeCapteurInterfaceRemote {

    /**
     * method which permit to find sensor's type in the database
     *
     * @return a list of sensors
     */
    public List<TypeCapteurEJBEntity> findTypeCapteurEJBEntity();

    /**
     * method which permit to create sensor type in the database
     *
     * @param typeCapteurEjb
     *            the type of sensor entity to create
     * @return the created entity
     */
    public TypeCapteurEJBEntity createTypeCapteurEJBEntity(TypeCapteurEJBEntity typeCapteurEjb);

    /**
     * method which permit to delete a sensor type in the database
     *
     * @param typeCapteurEJB
     *            the type of sensor entity to delete
     */
    public void deleteTypeCapteurEJBEntity(TypeCapteurEJBEntity typeCapteurEJB);
}
