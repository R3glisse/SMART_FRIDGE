package fr.esisar.frigolo.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class CapteurEJBEntity implements Serializable {

    /**
     * Permit to the class to be serializable
     */
    private static final long serialVersionUID = 1234889628265331219L;

    /**
     * Constant for hashcode
     */
    private static final int PRIME = 31;

    /**
     * identifier for the captor
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCapteur;
    /**
     * name of the captor
     */
    private String nomCapteur;

    /**
     * We have here a many to one relation with FrigidaireEJBEntity
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private TypeCapteurEJBEntity typeCapteur;

    /**
     * empty constructor of the captor
     */
    public CapteurEJBEntity() {
    }

    /**
     * constructor of the captor
     * 
     * @param nomCapteur
     *            the name of the sensor to set
     */
    public CapteurEJBEntity(String nomCapteur) {
        this.nomCapteur = nomCapteur;
    }

    /**
     * Getter for the id
     * 
     * @return the identitifer of the sensor
     */
    public Long getIdCapteur() {
        return idCapteur;
    }

    /**
     * setter for the id
     * 
     * @param idCapteur
     *            the identifier to set
     */
    public void setIdCapteur(Long idCapteur) {
        this.idCapteur = idCapteur;
    }

    /**
     * Getter for the captor's name
     * 
     * @return the name of the sensor
     */
    public String getNomCapteur() {
        return nomCapteur;
    }

    /**
     * setter for the captor's name
     * 
     * @param nomCapteur
     *            the name to set
     */
    public void setNomCapteur(String nomCapteur) {
        this.nomCapteur = nomCapteur;
    }

    /**
     * setter for the captor's type
     * 
     * @param typeCapteur
     *            the type of sensor entity to set
     */
    public void setTypeCapteur(TypeCapteurEJBEntity typeCapteur) {
        this.typeCapteur = typeCapteur;
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
        result = (prime * result) + ((idCapteur == null) ? 0 : idCapteur.hashCode());
        result = (prime * result) + ((nomCapteur == null) ? 0 : nomCapteur.hashCode());
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
        CapteurEJBEntity other = (CapteurEJBEntity) obj;
        if (idCapteur == null) {
            if (other.idCapteur != null) {
                return false;
            }
        } else if (!idCapteur.equals(other.idCapteur)) {
            return false;
        }
        if (nomCapteur == null) {
            if (other.nomCapteur != null) {
                return false;
            }
        } else if (!nomCapteur.equals(other.nomCapteur)) {
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
        return "CapteurEJBEntity [idCapteur=" + idCapteur + ", nomCapteur=" + nomCapteur + "]";
    }
}
