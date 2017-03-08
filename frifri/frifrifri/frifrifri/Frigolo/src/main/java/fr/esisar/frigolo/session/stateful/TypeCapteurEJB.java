package fr.esisar.frigolo.session.stateful;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.esisar.frigolo.entities.TypeCapteurEJBEntity;
import fr.esisar.frigolo.session.stateless.local.TypeCapteurInterfaceLocal;

@Stateful
public class TypeCapteurEJB {

    /**
     * a stateless that is used to query database
     */
    @EJB
    private TypeCapteurInterfaceLocal typeCapteurEJBStateless;

    /**
     * find all the types of sensor from the database
     *
     * @return a list of types of sensors
     */
    public List<TypeCapteurEJBEntity> findTypesCapteurs() {
        return typeCapteurEJBStateless.findTypeCapteurEJBEntity();
    }

    /**
     * add a type of sensor according to its name
     *
     * @param typeCapteur
     *            : the name of the new type of sensor
     */
    public void ajouterTypeCapteur(String typeCapteur) {
        TypeCapteurEJBEntity typeCap = new TypeCapteurEJBEntity(typeCapteur);
        typeCapteurEJBStateless.createTypeCapteurEJBEntity(typeCap);
    }

    /**
     * delete a type of sensor
     *
     * @param typeCapteurEJBStateless
     *            : the entity to suppress
     */
    public void deleteTypeCapteur(TypeCapteurEJBEntity typeCapteurEJBStateless) {
        this.typeCapteurEJBStateless.deleteTypeCapteurEJBEntity(typeCapteurEJBStateless);
    }
}
