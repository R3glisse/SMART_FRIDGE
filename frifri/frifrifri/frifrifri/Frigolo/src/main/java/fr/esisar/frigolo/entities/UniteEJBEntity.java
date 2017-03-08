package fr.esisar.frigolo.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({ @NamedQuery(name = "findUnites", query = "select m from UniteEJBEntity m") })
public class UniteEJBEntity implements Serializable {

    /**
     * Permit to the class to be serializable
     */
    private static final long serialVersionUID = -965210080175313774L;

    /**
     * Constant for hashcode
     */
    private static final int PRIME = 31;

    /**
     * identifier for the unit
     */
    @Id
    @GeneratedValue
    @Column
    private Long idUnite;

    /**
     * name of the unit
     */
    @Column
    private String unite;

    /**
     * empty constructor of the unit
     */
    public UniteEJBEntity() {
    }

    /**
     * constructor of the unit
     *
     * @param unite
     *            : the name of the unit
     */
    public UniteEJBEntity(String unite) {
        this.unite = unite;
    }

    /**
     * getter for the identifier of the unit
     *
     * @return the identifier of the unit
     */
    public Long getIdUnite() {
        return idUnite;
    }

    /**
     * setter for the identifier of the unit
     *
     * @param idUnite
     *            : the identifier to set
     */
    public void setIdUnite(Long idUnite) {
        this.idUnite = idUnite;
    }

    /**
     * getter for the name of the unit
     *
     * @return the name of the unit
     */
    public String getUnite() {
        return unite;
    }

    /**
     * setter for the name of the unit
     *
     * @param unite
     *            the name of the unit
     */
    public void setUnite(String unite) {
        this.unite = unite;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = PRIME;
        int result = 1;
        result = prime * result + (idUnite == null ? 0 : idUnite.hashCode());
        result = prime * result + (unite == null ? 0 : unite.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        UniteEJBEntity other = (UniteEJBEntity) obj;
        if (idUnite == null) {
            if (other.idUnite != null) {
                return false;
            }
        } else if (!idUnite.equals(other.idUnite)) {
            return false;
        }
        if (unite == null) {
            if (other.unite != null) {
                return false;
            }
        } else if (!unite.equals(other.unite)) {
            return false;
        }
        return true;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "UniteEJBEntity [idUnite=" + idUnite + ", unite=" + unite + "]";
    }

}
