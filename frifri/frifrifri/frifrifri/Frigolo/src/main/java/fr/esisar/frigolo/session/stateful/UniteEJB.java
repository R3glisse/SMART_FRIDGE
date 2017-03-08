package fr.esisar.frigolo.session.stateful;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.esisar.frigolo.entities.UniteEJBEntity;
import fr.esisar.frigolo.session.stateless.local.UniteInterfaceLocal;

@Stateful
public class UniteEJB {

    /**
     * a stateless that is used to query database
     */
    @EJB
    private UniteInterfaceLocal uniteEJBStateless;

    /**
     * gets a list of units in database
     *
     * @return a list of units
     */
    public List<UniteEJBEntity> findUnites() {
        return uniteEJBStateless.findUniteEJBEntity();
    }

    /**
     * add unit to the database
     *
     * @param unite
     *            : the name of the unit to add
     */
    public void ajouterUnite(String unite) {
        UniteEJBEntity capLog = new UniteEJBEntity(unite);
        uniteEJBStateless.createUniteEJBEntity(capLog);
    }

    /**
     * delete a unit from the database
     *
     * @param uniteEJBStateless
     *            : the entity to suppress
     */
    public void deleteUnite(UniteEJBEntity uniteEJBStateless) {
        this.uniteEJBStateless.deleteUniteEJBEntity(uniteEJBStateless);
    }
}
