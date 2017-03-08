package fr.esisar.frigolo.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
        @NamedQuery(name = "findMesuresLogiquesEJBEntityByCapteurId", query = "select m from MesureLogiqueEJBEntity m where m.capteurLogique.idCapteur = :idCapteurLogique"),
        @NamedQuery(name = "findCapteursUsedInMesureLogiqueEJBEntity", query = "select n from MesureLogiqueEJBEntity m, CapteurLogiqueEJBEntity n  where m.capteurLogique.idCapteur = n.idCapteur") })
public class MesureLogiqueEJBEntity extends MesureEJBEntity implements Serializable {

    /**
     * Permit to the class to be serializable
     */
    private static final long serialVersionUID = -6289616475239962855L;

    /**
     * We have here a many to one relation with CapteurLogiqueEJBEntity
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private CapteurLogiqueEJBEntity capteurLogique;

    /**
     * state of the logical measure
     */
    private Boolean etat;

    /**
     * constructor for mother class of the logical measure
     */
    public MesureLogiqueEJBEntity() {
        super();
    }

    /**
     * constructor for the logical measure
     *
     * @param etat
     *            the state of the logical measure
     */
    public MesureLogiqueEJBEntity(Boolean etat) {
        super();
        this.etat = etat;
    }

    /**
     * getter for the logical sensor
     *
     * @return the logical sensor
     */
    public CapteurLogiqueEJBEntity getCapteurLogique() {
        return this.capteurLogique;
    }

    /**
     * setter for the logical sensor
     *
     * @param capteurLogique
     *            the logical sensor to set
     */
    public void setCapteurLogique(CapteurLogiqueEJBEntity capteurLogique) {
        this.capteurLogique = capteurLogique;
    }

    /**
     * getter for the state
     *
     * @return the state of the measure
     */
    public Boolean getEtat() {
        return etat;
    }

    /**
     * setter for the state
     *
     * @param etat
     *            the state to set
     */
    public void setEtat(Boolean etat) {
        this.etat = etat;
    }
}
