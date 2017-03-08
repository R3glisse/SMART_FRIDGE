package fr.esisar.frigolo.session.stateful;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.esisar.frigolo.entities.CapteurLogiqueEJBEntity;
import fr.esisar.frigolo.entities.FrigidaireEJBEntity;
import fr.esisar.frigolo.session.stateless.local.capteur.logique.CapteurLogiqueInterfaceLocal;

@Stateful
public class CapteurLogiqueEJB {

    /**
     * a stateless that is used to query database
     */
    @EJB
    private CapteurLogiqueInterfaceLocal capteurLogiqueEJBStateless;

    /**
     * find all the logic sensors from the database
     *
     * @return a list of logic sensors
     */
    public List<CapteurLogiqueEJBEntity> findCapteursLogiques() {
        return capteurLogiqueEJBStateless.findCapteurLogiqueEJBEntity();
    }

    /**
     * find a logic sensor by its identifier
     *
     * @param idCapteurLogique
     *            : the identifier of the logic sensor
     * @return the entity of the logic sensor
     */
    public CapteurLogiqueEJBEntity findCapteurLogiqueById(Long idCapteurLogique) {
        return capteurLogiqueEJBStateless.findCapteurLogiqueEJBEntityById(idCapteurLogique);
    }

    /**
     * add a logic sensor to te database
     *
     * @param nomCapteur
     *            : the name of the sensor
     * @param idFrigidaire
     *            : the identifier of the fridge containing the sensor
     * @param idTypeCapteur
     *            : the identifier of the type of sensor
     */
    public void ajouterCapteurLogique(String nomCapteur, Long idFrigidaire, Long idTypeCapteur) {
        CapteurLogiqueEJBEntity capLog = new CapteurLogiqueEJBEntity(nomCapteur);
        capteurLogiqueEJBStateless.createCapteurLogiqueEJBEntity(capLog, idFrigidaire, idTypeCapteur);
    }

    /**
     * delete a logic sensor from the database
     *
     * @param capteurLogiqueEJBStatelessEntity
     *            : the entity to delete
     */
    public void deleteCapteurLogique(CapteurLogiqueEJBEntity capteurLogiqueEJBStatelessEntity) {
        this.capteurLogiqueEJBStateless.deleteCapteurLogiqueEJBEntity(capteurLogiqueEJBStatelessEntity);
    }

    /**
     * find all the logic sensors from the type of sensor identifier
     *
     * @param typeCapteurId
     *            : the type of sensor identifier
     * @return a list of logic sensors
     */
    public List<CapteurLogiqueEJBEntity> findCapteursLogiquesByTypeCapteurId(Long typeCapteurId) {
        return this.capteurLogiqueEJBStateless.findCapteursLogiquesByTypeCapteurId(typeCapteurId);
    }

    /**
     * find all the logic sensors belonging to a fridge
     *
     * @param typeCapteurId
     *            : the fridge identifier
     * @return a list logic sensors
     */
    public List<CapteurLogiqueEJBEntity> findCapteursLogiquesByTypeFrigidaireId(Long typeCapteurId) {
        return this.capteurLogiqueEJBStateless.findCapteursLogiquesByTypeFrigidaireId(typeCapteurId);
    }

    /**
     * find all the fridges used
     *
     * @return a list of fridges entities
     */
    public List<FrigidaireEJBEntity> findFrigidairesUsed() {
        return capteurLogiqueEJBStateless.findCapteursUsedInFrigidaireEJBEntity();
    }
}
