package fr.esisar.frigolo.session.stateless.local.capteur.numerique;

import java.util.List;

import javax.ejb.Local;

import fr.esisar.frigolo.entities.CapteurNumeriqueEJBEntity;
import fr.esisar.frigolo.entities.FrigidaireEJBEntity;

@Local
public interface CapteurNumeriqueInterfaceLocal {

    /**
     * find all the numeric sensors in database
     *
     * @return a list of numeric sensors
     */
    public List<CapteurNumeriqueEJBEntity> findCapteurNumeriqueEJBEntity();

    /**
     * find a numeric sensor form its identifier
     *
     * @param id
     *            the identifier of the numeric sensor
     *
     * @return a numeric sensor
     */
    public CapteurNumeriqueEJBEntity findCapteurNumeriqueEJBEntityById(Long id);

    /**
     * create a numeric sensor
     *
     * @param capteurNumeriqueEjb
     *            the numeric sensor to create
     *
     * @param idFrigidaire
     *            the identifier of the fridge containing the sensor
     *
     * @param idTypeCapteur
     *            the identifier of the type of sensor associated
     *
     */
    public void createCapteurNumeriqueEJBEntity(CapteurNumeriqueEJBEntity capteurNumeriqueEjb, Long idFrigidaire,
            Long idTypeCapteur);

    /**
     * delete a numeric sensor
     *
     * @param capteurNumeriqueEJBEntity
     *            the entity to delete
     *
     */
    public void deleteCapteurNumeriqueEJBEntity(CapteurNumeriqueEJBEntity capteurNumeriqueEJBEntity);

    /**
     * find numeric sensors from the identifier of a type of sensor
     *
     * @param typeCapteurId
     *            the identifier of the type of sensor
     *
     * @return a list of numeric sensors
     */
    public List<CapteurNumeriqueEJBEntity> findCapteursNumeriquesByTypeCapteurId(Long typeCapteurId);

    /**
     * find al the numeric sensors associated to a fridge
     *
     * @param frigidaireId
     *            the identifier of the fridge
     *
     * @return a list of numeric sensors
     */
    public List<CapteurNumeriqueEJBEntity> findCapteursNumeriquesByFrigidaireId(Long frigidaireId);

    /**
     * find all the fridges associated to logic sensors
     *
     * @return a list of fridges
     */
    public List<FrigidaireEJBEntity> findCapteursUsedInFrigidaireEJBEntity();

}
