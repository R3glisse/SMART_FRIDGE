package fr.esisar.frigolo.session.stateless.remote.capteur.logique;

import java.util.List;

import javax.ejb.Remote;

import fr.esisar.frigolo.entities.CapteurLogiqueEJBEntity;
import fr.esisar.frigolo.entities.FrigidaireEJBEntity;

@Remote
public interface CapteurLogiqueInterfaceRemote {

    /**
     * find all the logic sensors
     *
     * @return a list of logic sensors
     */
    public List<CapteurLogiqueEJBEntity> findCapteurLogiqueEJBEntity();

    /**
     * find a logic sensor from its identifier
     *
     * @param id
     *            the identifier of the logic sensor
     *
     * @return a logic sensor entity
     */
    public CapteurLogiqueEJBEntity findCapteurLogiqueEJBEntityById(Long id);

    /**
     * create a logic sensor
     *
     * @param capteurLogiqueEJBEntity
     *            the entity to create
     *
     * @param idFrigidaire
     *            the identifier of the fridge containing the logic sensor
     *
     * @param idTypeCapteur
     *            the identifier of the type of sensor associated
     *
     */
    public void createCapteurLogiqueEJBEntity(CapteurLogiqueEJBEntity capteurLogiqueEJBEntity, Long idFrigidaire,
            Long idTypeCapteur);

    /**
     * delete a logic sensor
     *
     * @param capteurLogiqueEJBEntity
     *            the entity to delete
     *
     */
    public void deleteCapteurLogiqueEJBEntity(CapteurLogiqueEJBEntity capteurLogiqueEJBEntity);

    /**
     * find all the logic sensors by the type of sensor identifier
     *
     * @param typeCapteurId
     *            the type of sensor identifier
     *
     * @return a list of logic sensors
     */
    public List<CapteurLogiqueEJBEntity> findCapteursLogiquesByTypeCapteurId(Long typeCapteurId);

    /**
     * find all the logic sensors in a fridge
     *
     * @param frigidaireId
     *            the fridge identifier
     *
     * @return a list of logic sensors
     */
    public List<CapteurLogiqueEJBEntity> findCapteursLogiquesByTypeFrigidaireId(Long frigidaireId);

    /**
     * find all the sensors used in a fridge
     *
     * @return a list of logic sensors
     */
    public List<FrigidaireEJBEntity> findCapteursUsedInFrigidaireEJBEntity();
}
