package fr.esisar.frigolo.session.stateless.remote.mesure.numerique;

import java.util.List;

import javax.ejb.Remote;

import fr.esisar.frigolo.entities.CapteurNumeriqueEJBEntity;
import fr.esisar.frigolo.entities.MesureNumeriqueEJBEntity;
import fr.esisar.frigolo.entities.UniteEJBEntity;

@Remote
public interface MesureNumeriqueInterfaceRemote {

    /**
     * method which permit to find all numerical measures in the database
     *
     * @return a list of numeric sensors
     */
    public List<MesureNumeriqueEJBEntity> findMesureNumeriqueEJBEntity();

    /**
     * method which permit to delete a numerical measure in the database
     *
     * @param mesureNumeriqueEJBEntity
     *            the entity to delete
     */
    public void deleteMesureNumeriqueEJBEntity(MesureNumeriqueEJBEntity mesureNumeriqueEJBEntity);

    /**
     * method which permit to find all numerical measures in the database
     * corresponding to an id
     *
     * @param id
     *            the identifier of the sensor
     * @return a list of numerical measures
     */
    public List<MesureNumeriqueEJBEntity> findMesureNumeriqueEJBEntityFromCapteurId(Long id);

    /**
     * method which permit to find units looking by measure id
     *
     * @param id
     *            the identifier of the measure
     * @return the unit associated
     */
    public UniteEJBEntity findUniteByMesureEJBEntityId(Long id);

    /**
     * method which permit to find captors using numerical measures
     *
     * @return a list of numerical sensors
     */
    public List<CapteurNumeriqueEJBEntity> findCapteursUsedInMesureNumeriqueEJBEntity();

    /**
     * method which permit to create numerical measures
     *
     * @param mesureNumeriqueEjb
     *            the entity to create
     * @param idCapteurNumerique
     *            the identifier of the numerical sensor
     * @param idUnite
     *            the identifier of the unit
     */
    public void createMesureNumeriqueEJBEntity(MesureNumeriqueEJBEntity mesureNumeriqueEjb, Long idCapteurNumerique,
            Long idUnite);
}
