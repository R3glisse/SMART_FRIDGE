package fr.esisar.frigolo.session.stateless.remote.mesure.logique;

import java.util.List;

import javax.ejb.Remote;

import fr.esisar.frigolo.entities.CapteurLogiqueEJBEntity;
import fr.esisar.frigolo.entities.MesureLogiqueEJBEntity;

@Remote
public interface MesureLogiqueInterfaceRemote {

    /**
     * method which permit to delete a measure in the database
     *
     * @param mesureLogiqueEJBEntity
     *            the entity to delete
     */
    public void deleteMesureLogiqueEJBEntity(MesureLogiqueEJBEntity mesureLogiqueEJBEntity);

    /**
     * method which permit to find sensors using logical measures in the
     * database
     *
     * @return a list of logical sensors
     */
    public List<CapteurLogiqueEJBEntity> findCapteursUsedInMesureLogiqueEJBEntity();

    /**
     * method which permit to find measure in the database looking by id's
     * sensor
     *
     * @param id
     *            the identifier of the logical sensor
     * @return a list of logical measures
     */
    public List<MesureLogiqueEJBEntity> findMesureLogiqueEJBEntityFromCapteurId(Long id);

    /**
     * method which permit to create measure in the database
     *
     * @param mesureLogiqueEjb
     *            the entity to create
     * @param idCapteurLogique
     *            the identifier of the sensor
     */
    public void createMesureLogiqueEJBEntity(MesureLogiqueEJBEntity mesureLogiqueEjb, Long idCapteurLogique);
}
