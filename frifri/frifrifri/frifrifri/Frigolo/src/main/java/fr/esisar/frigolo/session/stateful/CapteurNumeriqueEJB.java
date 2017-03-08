package fr.esisar.frigolo.session.stateful;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.esisar.frigolo.entities.CapteurNumeriqueEJBEntity;
import fr.esisar.frigolo.entities.FrigidaireEJBEntity;
import fr.esisar.frigolo.session.stateless.local.capteur.numerique.CapteurNumeriqueInterfaceLocal;

@Stateful
public class CapteurNumeriqueEJB {

    /**
     * a stateless that is used to query database
     */
    @EJB
    private CapteurNumeriqueInterfaceLocal capteurNumeriqueEJBStateless;

    /**
     * find all the numeric sensors
     *
     * @return a list of numeric sensors
     */
    public List<CapteurNumeriqueEJBEntity> findCapteursNumeriques() {
        return capteurNumeriqueEJBStateless.findCapteurNumeriqueEJBEntity();
    }

    /**
     * find a sensor by its identifier
     *
     * @param idCapteurNumerique
     *            : the identifier of the numeric sensor to find
     * @return a nuemric sensor entity
     */
    public CapteurNumeriqueEJBEntity findCapteurNumeriqueById(Long idCapteurNumerique) {
        return capteurNumeriqueEJBStateless.findCapteurNumeriqueEJBEntityById(idCapteurNumerique);
    }

    /**
     * add a numeric sensor
     *
     * @param nomCapteur
     *            : name of the sensor
     * @param idFrigidaire
     *            : the identifier of the fridge that contains the sensor
     * @param idTypeCapteur
     *            : the identifier of the type of sensor of the sensor
     */
    public void ajouterCapteurNumerique(String nomCapteur, Long idFrigidaire, Long idTypeCapteur) {
        CapteurNumeriqueEJBEntity capLog = new CapteurNumeriqueEJBEntity(nomCapteur);
        capteurNumeriqueEJBStateless.createCapteurNumeriqueEJBEntity(capLog, idFrigidaire, idTypeCapteur);
    }

    /**
     * delete a numeric sensor
     *
     * @param capteurNumeriqueEJBStatelessEntity
     *            : the entity to delete
     */
    public void deleteCapteurNumerique(CapteurNumeriqueEJBEntity capteurNumeriqueEJBStatelessEntity) {
        this.capteurNumeriqueEJBStateless.deleteCapteurNumeriqueEJBEntity(capteurNumeriqueEJBStatelessEntity);
    }

    /**
     * find all the numeric sensors from the type of sensor identifier
     *
     * @param typeCapteurId
     *            : the identifier of the type of sensor
     * @return a list of numeric sensors entities
     */
    public List<CapteurNumeriqueEJBEntity> findCapteursNumeriquesByTypeCapteurId(Long typeCapteurId) {
        return this.capteurNumeriqueEJBStateless.findCapteursNumeriquesByTypeCapteurId(typeCapteurId);
    }

    /**
     * find all the numeric sensors in a fridge
     *
     * @param frigidaireId
     *            : the identifier of the fridge
     * @return a list of numeric sensors
     */
    public List<CapteurNumeriqueEJBEntity> findCapteursNumeriquesByTypeFrigidaireId(Long frigidaireId) {
        return this.capteurNumeriqueEJBStateless.findCapteursNumeriquesByFrigidaireId(frigidaireId);
    }

    /**
     * find all the fridges in use
     *
     * @return a list of fridge entity
     */
    public List<FrigidaireEJBEntity> findFrigidairesUsed() {
        return capteurNumeriqueEJBStateless.findCapteursUsedInFrigidaireEJBEntity();
    }

}
