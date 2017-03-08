package fr.esisar.frigolo.session.stateful;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.esisar.frigolo.entities.CapteurLogiqueEJBEntity;
import fr.esisar.frigolo.entities.MesureLogiqueEJBEntity;
import fr.esisar.frigolo.session.stateless.local.mesure.logique.MesureLogiqueInterfaceLocal;

@Stateful
public class MesureLogiqueEJB {

    /**
     * a stateless that is used to query database
     */
    @EJB
    private MesureLogiqueInterfaceLocal mesureLogiqueEJBStateless;

    /**
     * fiond all the logic measures associated to a sensor
     *
     * @param id
     *            : the identifier of the logic sensor
     * @return a list of logic sensor
     */
    public List<MesureLogiqueEJBEntity> findMesuresLogiquesFromCapteurId(Long id) {
        return mesureLogiqueEJBStateless.findMesureLogiqueEJBEntityFromCapteurId(id);
    }

    /**
     * reset all the measures from a logic sensor
     *
     * @param mesuresLogiques
     *            : a list of measures to delete
     */
    public void deleteAllFromMesuresLogiquesList(List<MesureLogiqueEJBEntity> mesuresLogiques) {

        for (int i = 0; i < mesuresLogiques.size(); i++) {
            mesureLogiqueEJBStateless.deleteMesureLogiqueEJBEntity(mesuresLogiques.get(i));
        }
    }

    /**
     * find all the logic sensors in used
     *
     * @return a list of logic sensors
     */
    public List<CapteurLogiqueEJBEntity> findCapteursLogiquesUsed() {
        return mesureLogiqueEJBStateless.findCapteursUsedInMesureLogiqueEJBEntity();
    }

    /**
     * add logic measures
     *
     * @param etat
     *            : the state of the measure
     * @param idCapteurLogique
     *            : the identifier of the logic sensor
     */
    public void ajouterMesureLogique(Boolean etat, Long idCapteurLogique) {
        MesureLogiqueEJBEntity mesLog = new MesureLogiqueEJBEntity(etat);
        mesureLogiqueEJBStateless.createMesureLogiqueEJBEntity(mesLog, idCapteurLogique);
    }

}
