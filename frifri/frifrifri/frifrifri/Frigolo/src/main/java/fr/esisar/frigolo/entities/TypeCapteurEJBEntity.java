package fr.esisar.frigolo.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({ @NamedQuery(name = "findTypesCapteurs", query = "select m from TypeCapteurEJBEntity m") })

public class TypeCapteurEJBEntity implements Serializable {

    /**
     * Permit to the class to be serializable
     *
     */
    private static final long serialVersionUID = -7820316476135492012L;

    /**
     * Constant for hashcode
     */
    private static final int PRIME = 31;

    /**
     * identifier for the type of captor
     */
    @Id
    @GeneratedValue
    private Long idTypeCapteur;
    /**
     * name for the type of captor
     */
    private String type;

    /**
     * We have here a one to many relation with CapteurNumeriqueEJBEntity
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "typeCapteur", cascade = CascadeType.PERSIST)
    private List<CapteurNumeriqueEJBEntity> capteursNumeriques;

    /**
     * We have here a one to many relation with CapteurLogiqueEJBEntity
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "typeCapteur", cascade = CascadeType.PERSIST)
    private List<CapteurLogiqueEJBEntity> capteursLogiques;

    /**
     * empty constructor for the type of captor
     */
    public TypeCapteurEJBEntity() {
    }

    /**
     * constructor for the type of captor
     *
     * @param type
     *            the name of the type of sensor
     */
    public TypeCapteurEJBEntity(String type) {
        this.type = type;
    }

    /**
     * getter for the logical captors
     *
     * @return the type of sensor
     */
    public List<CapteurLogiqueEJBEntity> getCapteursLogiques() {
        return this.capteursLogiques;
    }

    /**
     * getter for numerical sensors
     *
     * @return a list of numeric sensors
     */
    public List<CapteurNumeriqueEJBEntity> getCapteursNumeriques() {
        return this.capteursNumeriques;
    }

    /**
     * getter for the id of the type of sensor
     *
     * @return the identifier of the type of sensor
     */
    public Long getIdTypeCapteur() {
        return idTypeCapteur;
    }

    /**
     * setter for the id of the type of sensor
     *
     * @param idTypeCapteur
     *            : the identifier of the sensor
     */
    public void setIdTypeCapteur(Long idTypeCapteur) {
        this.idTypeCapteur = idTypeCapteur;
    }

    /**
     * getter for the name of the type of sensor
     *
     * @return the type of sensor
     */
    public String getType() {
        return type;
    }

    /**
     * setter for the name of the type of sensor
     *
     * @param type
     *            the name of the type of sensor
     */
    public void setType(String type) {
        this.type = type;
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
        result = prime * result + (idTypeCapteur == null ? 0 : idTypeCapteur.hashCode());
        result = prime * result + (type == null ? 0 : type.hashCode());
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
        TypeCapteurEJBEntity other = (TypeCapteurEJBEntity) obj;
        if (idTypeCapteur == null) {
            if (other.idTypeCapteur != null) {
                return false;
            }
        } else if (!idTypeCapteur.equals(other.idTypeCapteur)) {
            return false;
        }
        if (type == null) {
            if (other.type != null) {
                return false;
            }
        } else if (!type.equals(other.type)) {
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
        return "typeCapteurEJBEntity [idTypeCapteur=" + idTypeCapteur + ", type=" + type + "]";
    }
}
