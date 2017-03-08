package fr.esisar.frigolo.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({ @NamedQuery(name = "findPersonnes", query = "select m from PersonneEJBEntity m"),
        @NamedQuery(name = "findPersonneEJBEntityById", query = "select m from PersonneEJBEntity m where m.idPersonne = :idPersonne") })
public class PersonneEJBEntity implements Serializable {

    /**
     * Permit to the class to be serializable
     */
    private static final long serialVersionUID = 3489617394296712093L;

    /**
     * Constant for hashcode
     */
    private static final int PRIME = 31;

    /**
     * identifier for the person
     */
    @Id
    @GeneratedValue
    private Long idPersonne;
    /**
     * login for the person
     */
    private String login;
    /**
     * associate password for the login
     */
    private String password;

    /**
     * We have here a many to many relation with FrigidaireEJBEntity
     */
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    List<FrigidaireEJBEntity> frigidaires;

    /**
     * empty constructor for the person
     */
    public PersonneEJBEntity() {
    }

    /**
     * constructor for the person
     *
     * @param login
     *            the login of the person
     * @param password
     *            the password of the person
     */
    public PersonneEJBEntity(String login, String password) {
        this.login = login;
        this.password = password;
    }

    /**
     * getter for the id of the person
     *
     * @return the identifier of the person
     */
    public Long getIdPersonne() {
        return idPersonne;
    }

    /**
     * setter for the id of the person
     *
     * @param idPersonne
     *            the identifier to set
     */
    public void setIdPersonne(Long idPersonne) {
        this.idPersonne = idPersonne;
    }

    /**
     * getter for the login of the person
     *
     * @return the login of the person
     */
    public String getLogin() {
        return login;
    }

    /**
     * setter for the login of the person
     *
     * @param login
     *            the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * getter for the password of the person
     *
     * @return the passsword of the person
     */
    public String getPassword() {
        return password;
    }

    /**
     * setter for the password of the person
     *
     * @param password
     *            the password to set
     */
    public void setPassword(String password) {
        this.password = password;
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
        result = prime * result + (idPersonne == null ? 0 : idPersonne.hashCode());
        result = prime * result + (login == null ? 0 : login.hashCode());
        result = prime * result + (password == null ? 0 : password.hashCode());
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
        PersonneEJBEntity other = (PersonneEJBEntity) obj;
        if (idPersonne == null) {
            if (other.idPersonne != null) {
                return false;
            }
        } else if (!idPersonne.equals(other.idPersonne)) {
            return false;
        }
        if (login == null) {
            if (other.login != null) {
                return false;
            }
        } else if (!login.equals(other.login)) {
            return false;
        }
        if (password == null) {
            if (other.password != null) {
                return false;
            }
        } else if (!password.equals(other.password)) {
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
        return "PersonneEJBEntity [idPersonne=" + idPersonne + ", login=" + login + ", password=" + password + "]";
    }

}
