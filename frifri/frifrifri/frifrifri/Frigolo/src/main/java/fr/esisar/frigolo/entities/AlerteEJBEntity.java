package fr.esisar.frigolo.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({ @NamedQuery(name = "findAlertes", query = "select m from AlerteEJBEntity m") })
public class AlerteEJBEntity implements Serializable {

    /**
     * Permit to the class to be serializable
     *
     */
    private static final long serialVersionUID = -6097771712986783556L;

    /**
     * Constant for hashcode
     */
    private static final int PRIME = 31;

    /**
     * identifier for the alerte.
     */
    @Id
    @GeneratedValue
    private Long idAlerte;
    /**
     * String which determines what type of alerte it is
     */
    private String alerteType;
    /**
     * Date of the alarm
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStamp;
    /**
     * Value of the alert
     */
    private Float alerteValeur;

    /**
     * We have here a many to one relation with FrigidaireEJBEntity
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    FrigidaireEJBEntity frigidaire;

    /**
     * Constructor
     *
     * @param alerteType
     *            the alert type
     * @param alerteValeur
     *            the alert value
     */
    public AlerteEJBEntity(String alerteType, Float alerteValeur) {
        this.alerteType = alerteType;
        this.timeStamp = new Date();
        this.alerteValeur = alerteValeur;
    }

    /**
     * Constructor of the mother class
     */
    public AlerteEJBEntity() {
        super();
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

    /**
     * Getter for the fridge
     *
     * @return the fridge entity
     */
    public FrigidaireEJBEntity getFrigidaire() {
        return this.frigidaire;
    }

    /**
     * Getter for the idenfiant
     *
     * @return the identifier of the alert to set
     */
    public Long getIdAlerte() {
        return idAlerte;
    }

    /**
     * Setter for the identifiant
     *
     * @param idAlerte
     *            the alert identifier
     */
    public void setIdAlerte(Long idAlerte) {
        this.idAlerte = idAlerte;
    }

    /**
     * Getter for the date
     *
     * @return the date
     */
    public Date getTimeStamp() {
        return timeStamp;
    }

    /**
     * Setter for the date
     *
     * @param timeStamp
     *            the date to set
     */
    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * Getter for the type of alert
     *
     * @return the type of the alert
     */
    public String getAlerteType() {
        return alerteType;
    }

    /**
     * Setter for the type of alert
     *
     * @param alerteType
     *            the alert type to set
     */
    public void setAlerteType(String alerteType) {
        this.alerteType = alerteType;
    }

    /**
     * Getter for the value of the alerte
     *
     * @return the value of the alert
     */
    public Float getAlerteValeur() {
        return alerteValeur;
    }

    /**
     * Setter for the value of the alerte
     *
     * @param alerteValeur
     *            the alert value
     */
    public void setAlerteValeur(Float alerteValeur) {
        this.alerteValeur = alerteValeur;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashcode(java.lang.Object)
     */
    @Override
    public int hashCode() {
        final int prime = PRIME;
        int result = 1;
        result = prime * result + (alerteType == null ? 0 : alerteType.hashCode());
        result = prime * result + (alerteValeur == null ? 0 : alerteValeur.hashCode());
        result = prime * result + (frigidaire == null ? 0 : frigidaire.hashCode());
        result = prime * result + (idAlerte == null ? 0 : idAlerte.hashCode());
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
        AlerteEJBEntity other = (AlerteEJBEntity) obj;
        if (alerteType == null) {
            if (other.alerteType != null) {
                return false;
            }
        } else if (!alerteType.equals(other.alerteType)) {
            return false;
        }
        if (alerteValeur == null) {
            if (other.alerteValeur != null) {
                return false;
            }
        } else if (!alerteValeur.equals(other.alerteValeur)) {
            return false;
        }
        if (frigidaire == null) {
            if (other.frigidaire != null) {
                return false;
            }
        } else if (!frigidaire.equals(other.frigidaire)) {
            return false;
        }
        if (idAlerte == null) {
            if (other.idAlerte != null) {
                return false;
            }
        } else if (!idAlerte.equals(other.idAlerte)) {
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
        return "AlerteEJBEntity [idAlerte=" + idAlerte + ", alerteType=" + alerteType + ", timeStamp=" + timeStamp
                + ", alerteValeur=" + alerteValeur + "]";
    }

}
