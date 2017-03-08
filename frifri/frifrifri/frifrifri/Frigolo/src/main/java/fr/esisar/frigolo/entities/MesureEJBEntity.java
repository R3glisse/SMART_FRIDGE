package fr.esisar.frigolo.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class MesureEJBEntity implements Serializable {

    /**
     * Permit to the class to be serializable
     */
    private static final long serialVersionUID = -105035320067750523L;

    /**
     * Constant for hashcode
     */
    private static final int PRIME = 31;

    /**
     * identifier for the measures
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMesure;

    /**
     * date of the measures
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStamp;

    /**
     * constructor of the mesures's entity
     */
    public MesureEJBEntity() {
        this.timeStamp = new Date();
    }

    /**
     * getter for the identifier for the fridge
     *
     * @return the identifier of the measure
     */
    public Long getIdMesure() {
        return idMesure;
    }

    /**
     * getter for the date for the fridge
     *
     * @return the date at format TimeStamp
     */
    public Date getTimeStamp() {
        return timeStamp;
    }

    /**
     * setter for the date for the fridge
     *
     * @param timeStamp
     *            the date to set
     */
    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
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
        result = prime * result + (idMesure == null ? 0 : idMesure.hashCode());
        result = prime * result + (timeStamp == null ? 0 : timeStamp.hashCode());
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
        MesureEJBEntity other = (MesureEJBEntity) obj;
        if (idMesure == null) {
            if (other.idMesure != null) {
                return false;
            }
        } else if (!idMesure.equals(other.idMesure)) {
            return false;
        }
        if (timeStamp == null) {
            if (other.timeStamp != null) {
                return false;
            }
        } else if (!timeStamp.equals(other.timeStamp)) {
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
        return "MesureEJBEntity [idMesure=" + idMesure + ", timeStamp=" + timeStamp + "]";
    }

}
