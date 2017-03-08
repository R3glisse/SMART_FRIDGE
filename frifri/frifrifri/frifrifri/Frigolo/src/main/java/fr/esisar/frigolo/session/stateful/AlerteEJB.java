package fr.esisar.frigolo.session.stateful;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.esisar.frigolo.entities.AlerteEJBEntity;
import fr.esisar.frigolo.session.stateless.local.AlerteInterfaceLocal;

@Stateful
public class AlerteEJB {

    /**
     * a stateless that is used to query database
     */
    @EJB
    private AlerteInterfaceLocal alerteEJBStateless;

    /**
     * return all the warnings from the database
     *
     * @return a list of warnings
     */
    public List<AlerteEJBEntity> findAlertes() {
        return alerteEJBStateless.findAlerteEJBEntity();
    }

    /**
     * add warning in database
     *
     * @param alerteType
     *            : the name of the warning
     * @param alerteValeur
     *            : the value of the warning
     * @param idFrigidaire
     *            : the identifier of the fridge that contains that warning
     */
    public void ajouterAlerte(String alerteType, Float alerteValeur, Long idFrigidaire) {
        AlerteEJBEntity alerte = new AlerteEJBEntity(alerteType, alerteValeur);
        alerteEJBStateless.createAlerteEJBEntity(alerte, idFrigidaire);
    }

    /**
     * delete a warning from the database
     *
     * @param alerteEJBStatelessEntity
     *            : the entity to suppress
     */
    public void deleteAlerte(AlerteEJBEntity alerteEJBStatelessEntity) {
        this.alerteEJBStateless.deleteAlerteEJBEntity(alerteEJBStatelessEntity);
    }

}
