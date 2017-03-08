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
@NamedQueries({ @NamedQuery(name = "findCapteursLogiques", query = "select m from CapteurLogiqueEJBEntity m"),
        @NamedQuery(name = "findCapteursLogiquesByTypeCapteurId", query = "select m from CapteurLogiqueEJBEntity m where m.typeCapteur.idTypeCapteur= :idTypeCapteur"),
        @NamedQuery(name = "findCapteurLogiqueEJBEntityById", query = "select m from CapteurLogiqueEJBEntity m where m.idCapteur = :idCapteurLogique"),
        @NamedQuery(name = "findCapteursLogiquesByTypeFrigidaireId", query = "select m from CapteurLogiqueEJBEntity m where m.frigidaire.idFrigidaire = :idFrigidaire"),
        @NamedQuery(name = "findCapteursLogiquesUsedInFrigidaireEJBEntity", query = "select n from CapteurLogiqueEJBEntity m, FrigidaireEJBEntity n  where m.frigidaire.idFrigidaire = n.idFrigidaire") })
public class CapteurLogiqueEJBEntity extends CapteurEJBEntity implements Serializable {

    /**
     * Permit to the class to be serializable
     */
    private static final long serialVersionUID = -4286803720033967603L;

    /**
     * We have here a one to many relation with MesureLogiqueEJBEntity
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "capteurLogique", cascade = CascadeType.PERSIST)
    private List<MesureLogiqueEJBEntity> mesuresLogiques;

    /**
     * We have here a many to one relation with FrigidaireEJBEntity
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private FrigidaireEJBEntity frigidaire;

    /**
     * constructor of the captor
     */
    public CapteurLogiqueEJBEntity() {
        super();
    }

    /**
     * constructor for the mother class
     *
     * @param nomCapteur
     *            the name of the logical sensor
     */
    public CapteurLogiqueEJBEntity(String nomCapteur) {
        super(nomCapteur);
    }

    /**
     * Getter for logical measures
     *
     * @return a list of logical sensors
     */
    public List<MesureLogiqueEJBEntity> getMesuresLogiques() {
        return this.mesuresLogiques;
    }

    /**
     * Setter for the fridge
     *
     * @param frigidaire
     *            the fridge entity to set
     */
    public void setFrigidaire(FrigidaireEJBEntity frigidaire) {
        this.frigidaire = frigidaire;
    }

}
