package fr.esisar.frigolo.session.stateful;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.esisar.frigolo.entities.FrigidaireEJBEntity;
import fr.esisar.frigolo.session.stateless.local.FrigidaireInterfaceLocal;

@Stateful
public class FrigidaireEJB {

    /**
     * a stateless that is used to query database
     */
    @EJB
    private FrigidaireInterfaceLocal frigidaireEJBStateless;

    /**
     * find all the fridges
     *
     * @return a list of friges
     */
    public List<FrigidaireEJBEntity> findFrigidaires() {
        return frigidaireEJBStateless.findFrigidaireEJBEntity();
    }

    /**
     * add a fridge to the database
     *
     * @param frigidaireNom
     *            : the name of the fridge to ass
     */
    public void ajouterFrigidaire(String frigidaireNom) {
        FrigidaireEJBEntity frigidaire = new FrigidaireEJBEntity(frigidaireNom);
        frigidaireEJBStateless.createFrigidaireEJBEntity(frigidaire);
    }

    /**
     * find a fridge by its identifier 1
     *
     * @param idFrigidaire
     *            : the identifier of the fridge to find
     * @return a fridge entity
     */
    public FrigidaireEJBEntity findFrigidaireById(Long idFrigidaire) {
        return frigidaireEJBStateless.findFrigidaireEJBEntityById(idFrigidaire);
    }

    /**
     * delete a fridge from the database
     *
     * @param frigidaireEJBStatelessEntity
     *            : the entity to suppress
     */
    public void deleteFrigidaire(FrigidaireEJBEntity frigidaireEJBStatelessEntity) {
        this.frigidaireEJBStateless.deleteFrigidaireEJBEntity(frigidaireEJBStatelessEntity);
    }
}
