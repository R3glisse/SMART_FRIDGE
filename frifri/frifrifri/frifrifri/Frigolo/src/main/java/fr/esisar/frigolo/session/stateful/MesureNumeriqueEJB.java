package fr.esisar.frigolo.session.stateful;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.esisar.frigolo.entities.CapteurNumeriqueEJBEntity;
import fr.esisar.frigolo.entities.MesureNumeriqueEJBEntity;
import fr.esisar.frigolo.entities.UniteEJBEntity;
import fr.esisar.frigolo.session.stateless.local.mesure.numerique.MesureNumeriqueInterfaceLocal;

@Stateful
public class MesureNumeriqueEJB {

    /**
     * a stateless that is used to query database
     */
    @EJB
    private MesureNumeriqueInterfaceLocal mesureNumeriqueEJBStateless;

    /**
     * find all the numeric measures
     *
     * @return a list of the numeric measures
     */
    public List<MesureNumeriqueEJBEntity> findMesuresNumeriques() {
        return mesureNumeriqueEJBStateless.findMesureNumeriqueEJBEntity();
    }

    /**
     * find all the measures associated to a sensor identifier
     *
     * @param id
     *            : the identifier of the sensor
     * @return a list of measures associated to numeric sensor
     */
    public List<MesureNumeriqueEJBEntity> findMesuresNumeriquesFromCapteurId(Long id) {
        return mesureNumeriqueEJBStateless.findMesureNumeriqueEJBEntityFromCapteurId(id);
    }

    /**
     * find all the numeric sensors used
     *
     * @return a list of numeric sensors used
     */
    public List<CapteurNumeriqueEJBEntity> findCapteursNumeriquesUsed() {
        return mesureNumeriqueEJBStateless.findCapteursUsedInMesureNumeriqueEJBEntity();
    }

    /**
     * reset all the measures associated to a list
     *
     * @param mesuresNumeriques
     *            : the list containing the measures to suppress
     */
    public void deleteAllFromMesuresNumeriquesList(List<MesureNumeriqueEJBEntity> mesuresNumeriques) {

        for (int i = 0; i < mesuresNumeriques.size(); i++) {
            mesureNumeriqueEJBStateless.deleteMesureNumeriqueEJBEntity(mesuresNumeriques.get(i));
        }
    }

    /**
     * find all the unit associated to a measure
     *
     * @param id
     *            : the identifier of the measure
     * @return the unit associated to the measure
     */
    public UniteEJBEntity findUniteFromMesureId(Long id) {
        return mesureNumeriqueEJBStateless.findUniteByMesureEJBEntityId(id);
    }

    /**
     * add a numeric measure
     *
     * @param valeur
     *            : the value of the numeric measure
     * @param idCapteurNumerique
     *            : the identifier of the sensor to which belongs the measure
     * @param idUnite
     *            : the identifier to the unit associated to the measure
     */
    public void ajouterMesureNumerique(Float valeur, Long idCapteurNumerique, Long idUnite) {
        MesureNumeriqueEJBEntity capLog = new MesureNumeriqueEJBEntity(valeur);
        mesureNumeriqueEJBStateless.createMesureNumeriqueEJBEntity(capLog, idCapteurNumerique, idUnite);
    }
}
