package fr.esisar.frigolo.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({ @NamedQuery(name = "findFrigidaires", query = "select m from FrigidaireEJBEntity m"),
        @NamedQuery(name = "findFrigidaireEJBEntityById", query = "select m from FrigidaireEJBEntity m where m.idFrigidaire = :idFrigidaire") })
public class FrigidaireEJBEntity implements Serializable {

    /**
     * Permit to the class to be serializable
     */
    private static final long serialVersionUID = 3176360938490941945L;

    /**
     * Constant for hashcode
     */
    private static final int PRIME = 31;

    /**
     * identifier for the fridge
     */
    @Id
    @GeneratedValue
    private Long idFrigidaire;

    /**
     * name of the fridge
     */
    private String nomFrigidaire;

    /**
     * the rule is embedded by the fridge, that means that if the fridge is
     * deleted, the rule will be too
     */
    @Embedded
    private ConsigneEJBEntity consigne;

    /**
     * We have here a one to many relation with CapteurLogiqueEJBEntity
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "frigidaire", cascade = CascadeType.PERSIST)
    private List<CapteurLogiqueEJBEntity> capteursLogiques;

    /**
     * We have here a one to many relation with CapteurNumeriqueEJBEntity
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "frigidaire", cascade = CascadeType.PERSIST)
    private List<CapteurNumeriqueEJBEntity> capteursNumeriques;

    /**
     * We have here a one to many relation with AlerteEJBEntity
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "frigidaire", cascade = CascadeType.PERSIST)
    private List<AlerteEJBEntity> alertes;

    /**
     * We have here a many to many relation with PersonneEJBEntity
     */
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    List<PersonneEJBEntity> personnes;

    /**
     * constructor of the fridge with just fridge's name
     *
     * @param frigidaireNom
     *            the name of the fridge to set
     */
    public FrigidaireEJBEntity(String frigidaireNom) {
        this.nomFrigidaire = frigidaireNom;
    }

    /**
     * constructor of the fridge with just fridge's rule
     *
     * @param consigne
     *            the rule entity to set
     */
    public FrigidaireEJBEntity(ConsigneEJBEntity consigne) {
        this.consigne = consigne;
    }

    /**
     * complete constructor of the fridge
     *
     * @param idFrigidaire
     *            the identifier of the fridge
     * @param idConsigne
     *            the identifier of the rule
     * @param qualiteAir
     *            the air quality
     * @param temperatureMax
     *            the max temperature
     * @param temperatureMin
     *            the min temperature
     */
    public FrigidaireEJBEntity(Long idFrigidaire, Long idConsigne, int qualiteAir, int temperatureMax,
            int temperatureMin) {

        this.idFrigidaire = idFrigidaire;
        this.consigne.setIdConsigne(idConsigne);
        this.consigne.setQualiteAir(qualiteAir);
        this.consigne.setTemperatureMax(temperatureMax);
        this.consigne.setTemperatureMin(temperatureMin);

    }

    /**
     * empty constructor of the fridge
     */
    public FrigidaireEJBEntity() {
    }

    /**
     * Getter for the alert
     *
     * @return a list of alerts
     */
    public List<AlerteEJBEntity> getAlertes() {
        return this.alertes;
    }

    /**
     * Getter for the numerical captors
     *
     * @return a list of numerical sensors
     */
    public List<CapteurNumeriqueEJBEntity> getCapteursNumeriques() {
        return this.capteursNumeriques;
    }

    /**
     * Getter for the logical captors
     *
     * @return a list of logical sensors
     */
    public List<CapteurLogiqueEJBEntity> getCapteursLogiques() {
        return this.capteursLogiques;
    }

    /**
     * Getter for the people
     *
     * @return a list of people
     */
    public List<PersonneEJBEntity> getPersonnesEJBEntity() {
        return this.personnes;
    }

    /**
     * Getter for the identifier of the fridge
     *
     * @return the identifier of the fridge
     */
    public Long getIdFrigidaire() {
        return idFrigidaire;
    }

    /**
     * setter for the identifier of the fridge
     *
     * @param idFrigidaire
     *            the identifier to set
     */
    public void setIdFrigidaire(Long idFrigidaire) {
        this.idFrigidaire = idFrigidaire;
    }

    /**
     * Getter for the name of the fridge
     *
     * @return the name of the fridge
     */
    public String getNomFrigidaire() {
        return this.nomFrigidaire;
    }

    /**
     * setter for the name of the fridge
     *
     * @param nom
     *            the name of the fridge
     */
    public void setNomFrigidaire(String nom) {
        this.nomFrigidaire = nom;
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
        result = prime * result + (alertes == null ? 0 : alertes.hashCode());
        result = prime * result + (capteursLogiques == null ? 0 : capteursLogiques.hashCode());
        result = prime * result + (capteursNumeriques == null ? 0 : capteursNumeriques.hashCode());
        result = prime * result + (consigne == null ? 0 : consigne.hashCode());
        result = prime * result + (idFrigidaire == null ? 0 : idFrigidaire.hashCode());
        result = prime * result + (nomFrigidaire == null ? 0 : nomFrigidaire.hashCode());
        result = prime * result + (personnes == null ? 0 : personnes.hashCode());
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
        FrigidaireEJBEntity other = (FrigidaireEJBEntity) obj;
        if (alertes == null) {
            if (other.alertes != null) {
                return false;
            }
        } else if (!alertes.equals(other.alertes)) {
            return false;
        }
        if (capteursLogiques == null) {
            if (other.capteursLogiques != null) {
                return false;
            }
        } else if (!capteursLogiques.equals(other.capteursLogiques)) {
            return false;
        }
        if (capteursNumeriques == null) {
            if (other.capteursNumeriques != null) {
                return false;
            }
        } else if (!capteursNumeriques.equals(other.capteursNumeriques)) {
            return false;
        }
        if (consigne == null) {
            if (other.consigne != null) {
                return false;
            }
        } else if (!consigne.equals(other.consigne)) {
            return false;
        }
        if (idFrigidaire == null) {
            if (other.idFrigidaire != null) {
                return false;
            }
        } else if (!idFrigidaire.equals(other.idFrigidaire)) {
            return false;
        }
        if (nomFrigidaire == null) {
            if (other.nomFrigidaire != null) {
                return false;
            }
        } else if (!nomFrigidaire.equals(other.nomFrigidaire)) {
            return false;
        }
        if (personnes == null) {
            if (other.personnes != null) {
                return false;
            }
        } else if (!personnes.equals(other.personnes)) {
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
        return "FrigidaireEJBEntity [idFrigidaire=" + idFrigidaire + ", consigne=" + consigne + "]";
    }

}
