package fr.esisar.frigolo.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
@NamedQueries({ @NamedQuery(name = "findMesuresNumeriques", query = "select m from MesureNumeriqueEJBEntity m"),
        @NamedQuery(name = "findMesuresNumeriquesEJBEntityByCapteurId", query = "select m from MesureNumeriqueEJBEntity m where m.capteurNumerique.idCapteur = :idCapteurNumerique"),
        @NamedQuery(name = "findUniteByMesureNumeriqueEJBEntityId", query = "select m.unite from MesureNumeriqueEJBEntity m where m.idMesure = :idMesureNumerique"),
        @NamedQuery(name = "findCapteursUsedInMesureNumeriqueEJBEntity", query = "select n from MesureNumeriqueEJBEntity m, CapteurNumeriqueEJBEntity n  where m.capteurNumerique.idCapteur = n.idCapteur") })
public class MesureNumeriqueEJBEntity extends MesureEJBEntity implements Serializable {

    /**
     * Permit to the class to be serializable
     */
    private static final long serialVersionUID = 2235226038212233248L;

    /**
     * Constant for hashcode
     */
    private static final int PRIME = 31;

    /**
     * We have here a many to one relation with CapteurNumeriqueEJBEntity
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private CapteurNumeriqueEJBEntity capteurNumerique;

    /**
     * We have here a one to one relation with UniteEJBEntity
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private UniteEJBEntity unite;

    /**
     * value of the measure
     */
    private Float valeur;

    /**
     * constructor for the mother class
     */
    public MesureNumeriqueEJBEntity() {
        super();
    }

    /**
     * constructor for the numerical measure
     *
     * @param valeur
     *            the value to set
     */
    public MesureNumeriqueEJBEntity(Float valeur) {
        super();
        this.valeur = valeur;
    }

    /**
     * getter for the value of the measure
     *
     * @return the value of the measure
     */
    public Float getValeur() {
        return valeur;
    }

    /**
     * setter for the value of the measure
     *
     * @param valeur
     *            the value to set
     */
    public void setValeur(Float valeur) {
        this.valeur = valeur;
    }

    /**
     * setter for the numerical captor
     *
     * @param capteurNumerique
     *            the numeric sensor entity to set
     */
    public void setCapteurNumerique(CapteurNumeriqueEJBEntity capteurNumerique) {
        this.capteurNumerique = capteurNumerique;
    }

    /**
     * setter for the value of the unit
     *
     * @param unite
     *            the unit entity to set
     */
    public void setUnite(UniteEJBEntity unite) {
        this.unite = unite;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.esisar.frigolo.entities.MesureEJBEntity#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = PRIME;
        int result = super.hashCode();
        result = prime * result + (valeur == null ? 0 : valeur.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.esisar.frigolo.entities.MesureEJBEntity#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        MesureNumeriqueEJBEntity other = (MesureNumeriqueEJBEntity) obj;
        if (valeur == null) {
            if (other.valeur != null) {
                return false;
            }
        } else if (!valeur.equals(other.valeur)) {
            return false;
        }
        return true;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.esisar.frigolo.entities.MesureEJBEntity#toString()
     */
    @Override
    public String toString() {
        return "MesureNumeriqueEJBEntity [valeur=" + valeur + "]";
    }
}
