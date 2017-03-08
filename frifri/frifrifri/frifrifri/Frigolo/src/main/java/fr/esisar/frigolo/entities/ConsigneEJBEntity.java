package fr.esisar.frigolo.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Embeddable
@NamedQueries({ @NamedQuery(name = "findConsignes", query = "select m from ConsigneEJBEntity m") })
public class ConsigneEJBEntity implements Serializable {

    /**
     * Permit to the class to be serializable
     */
    private static final long serialVersionUID = -8706698602686846045L;

    /**
     * Constant for hashcode
     */
    private static final int PRIME = 31;

    /**
     * identifier for the rule
     */
    @GeneratedValue
    @Column
    private Long idConsigne;
    /**
     * rule for the air quality
     */
    @Column
    private int qualiteAir;

    /**
     * rule for the maximum temperature
     */
    @Column
    private int temperatureMax;
    /**
     * rule for the minimum temperature
     */
    @Column
    private int temperatureMin;

    /**
     * Constructor for the rule
     *
     * @param idConsigne
     *            the identifier of the rule
     * @param qualiteAir
     *            the air quality
     * @param temperatureMax
     *            the max temperature
     * @param temperatureMin
     *            the min temperature
     */
    public ConsigneEJBEntity(Long idConsigne, int qualiteAir, int temperatureMax, int temperatureMin) {
        this.idConsigne = idConsigne;
        this.qualiteAir = qualiteAir;
        this.temperatureMax = temperatureMax;
        this.temperatureMin = temperatureMin;
    }

    /**
     * Empty constructor for the rule
     */
    public ConsigneEJBEntity() {
    }

    /**
     * Getter for the id of the rule
     *
     * @return the identifier of the rule
     */
    public Long getIdConsigne() {
        return idConsigne;
    }

    /**
     * setter for the id of the rule
     *
     * @param idConsigne
     *            the identifier of the rule to set
     */
    public void setIdConsigne(Long idConsigne) {
        this.idConsigne = idConsigne;
    }

    /**
     * Getter for the air quality
     *
     * @return the air quality
     */
    public int getQualiteAir() {
        return qualiteAir;
    }

    /**
     * setter for the air quality
     *
     * @param qualiteAir
     *            the air quality to set
     */
    public void setQualiteAir(int qualiteAir) {
        this.qualiteAir = qualiteAir;
    }

    /**
     * Getter for the maximum temperature
     *
     * @return the max temperature
     */
    public int getTemperatureMax() {
        return temperatureMax;
    }

    /**
     * setter for the maximum temperature
     *
     * @param temperatureMax
     *            the max temperature to set
     */
    public void setTemperatureMax(int temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    /**
     * Getter for the minimum temperature
     *
     * @return the min temperature
     */
    public int getTemperatureMin() {
        return temperatureMin;
    }

    /**
     * setter for the minimum temperature
     *
     * @param temperatureMin
     *            the min temperature to set
     */
    public void setTemperatureMin(int temperatureMin) {
        this.temperatureMin = temperatureMin;
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
        result = prime * result + (idConsigne == null ? 0 : idConsigne.hashCode());
        result = prime * result + qualiteAir;
        result = prime * result + temperatureMax;
        result = prime * result + temperatureMin;
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
        ConsigneEJBEntity other = (ConsigneEJBEntity) obj;
        if (idConsigne == null) {
            if (other.idConsigne != null) {
                return false;
            }
        } else if (!idConsigne.equals(other.idConsigne)) {
            return false;
        }
        if (qualiteAir != other.qualiteAir) {
            return false;
        }
        if (temperatureMax != other.temperatureMax) {
            return false;
        }
        if (temperatureMin != other.temperatureMin) {
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
        return "ConsigneEJBEntity [idConsigne=" + idConsigne + ", qualiteAir=" + qualiteAir + ", temperatureMax="
                + temperatureMax + ", temperatureMin=" + temperatureMin + "]";
    }

}
