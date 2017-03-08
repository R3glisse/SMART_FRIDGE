package fr.esisar.frigolo.portail;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.PartitionManager;
import org.picketlink.idm.RelationshipManager;
import org.picketlink.idm.credential.Password;
import org.picketlink.idm.model.basic.BasicModel;
import org.picketlink.idm.model.basic.Role;
import org.picketlink.idm.model.basic.User;

@Singleton
@Startup
public class Portail {

    /**
     * partition manager to inject in the database
     */
    @Inject
    private PartitionManager partitionManager;

    /**
     * method which objective is to create the different users, roles and make
     * the differents attributions in picketlink
     */
    @PostConstruct
    public void create() {

        /**
         * entity which permit to manage the differents identities
         */
        IdentityManager identityManager = this.partitionManager.createIdentityManager();

        /**
         * entity which permit to manage the differents relationships
         */
        RelationshipManager relationshipManager = this.partitionManager.createRelationshipManager();

        /* Creation des users */

        /**
         * creation of an user called admin
         */
        User user1 = new User("admin");
        user1.setEmail("admin@frigolo.com");
        user1.setFirstName("ad");
        user1.setLastName("min");
        identityManager.add(user1);
        identityManager.updateCredential(user1, new Password("admin"));

        /**
         * creation of an user called fourmi
         */
        User user2 = new User("fourmi");
        user2.setEmail("stephanie.chollet@lcis.grenoble-inp.fr");
        user2.setFirstName("stephanie");
        user2.setLastName("chollet");
        identityManager.add(user2);
        identityManager.updateCredential(user2, new Password("user"));

        /**
         * creation of an user called pfoste
         */
        User user3 = new User("pfoste");
        user3.setEmail("david.hely@lcis.grenoble-inp.fr");
        user3.setFirstName("david");
        user3.setLastName("hely");
        identityManager.add(user3);
        identityManager.updateCredential(user3, new Password("user"));

        /* Attribution des rôles */

        /**
         * creation of a role called administrateur
         */
        Role administrateurRole = new Role("administrateur");

        /**
         * creation of an role called membreFamille
         */
        Role membreFamilleRole = new Role("membreFamille");

        identityManager.add(administrateurRole);
        identityManager.add(membreFamilleRole);

        BasicModel.grantRole(relationshipManager, user1, administrateurRole);
        BasicModel.grantRole(relationshipManager, user2, membreFamilleRole);
        BasicModel.grantRole(relationshipManager, user2, membreFamilleRole);

    }

}
