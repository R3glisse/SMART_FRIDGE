package fr.esisar.frigolo.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NamedQueries({ @NamedQuery(name = "findCapteursNumeriques", query = "select m from CapteurNumeriqueEJBEntity m"),
        @NamedQuery(name = "findCapteursNumeriquesByTypeCapteurId", query = "select m from CapteurNumeriqueEJBEntity m where m.typeCapteur.idTypeCapteur= :idTypeCapteur"),
        @NamedQuery(name = "findCapteurNumeriqueEJBEntityById", query = "select m from CapteurNumeriqueEJBEntity m where m.idCapteur = :idCapteurNumerique"),
        @NamedQuery(name = "findCapteursNumeriquesByFrigidaireId", query = "select m from CapteurNumeriqueEJBEntity m where m.frigidaire.idFrigidaire = :idFrigidaire"),
        @NamedQuery(name = "findCapteursNumeriquesUsedInFrigidaireEJBEntity", query = "select n from CapteurNumeriqueEJBEntity m, FrigidaireEJBEntity n  where m.frigidaire.idFrigidaire = n.idFrigidaire")

})
public class CapteurNumeriqueEJBEntity extends CapteurEJBEntity implements Serializable {

    /**
     * Permit to the class to be serializable
     */
    private static final long serialVersionUID = -4286803720033967603L;

    /**
     * We have here a one to many relation with MesureNumeriqueEJBEntity
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "capteurNumerique", cascade = CascadeType.PERSIST)
    private List<MesureNumeriqueEJBEntity> mesuresNumeriques;

    /**
     * We have here a many to one relation with FrigidaireEJBEntity
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private FrigidaireEJBEntity frigidaire;

    /**
     * constructor of the captor
     */
    public CapteurNumeriqueEJBEntity() {
    }

    /**
     * constructor for the mother class
     *
     * @param nomCapteur
     *            the name of the sensor to set
     */
    public CapteurNumeriqueEJBEntity(String nomCapteur) {
        super(nomCapteur);
    }

    /**
     * Getter for numerical measures
     *
     * @return a list of numerical sensors
     */
    public List<MesureNumeriqueEJBEntity> getMesuresNumeriques() {
        return this.mesuresNumeriques;
    }

    /**
     * Setter for the fridge
     *
     * @param frigidaire
     *            the identifier of the fridge
     */
    public void setFrigidaire(FrigidaireEJBEntity frigidaire) {
        this.frigidaire = frigidaire;
    }
}
