package fr.esisar.frigolo.session.stateful;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.picketlink.idm.AttributedTypeManager;
import org.picketlink.idm.PartitionManager;
import org.picketlink.idm.model.Account;
import org.picketlink.idm.model.AttributedType;
import org.picketlink.idm.model.IdentityType;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartModel;
import org.primefaces.model.chart.LineChartModel;

import fr.esisar.frigolo.controller.AdministrateurController;
import fr.esisar.frigolo.entities.AlerteEJBEntity;
import fr.esisar.frigolo.entities.CapteurEJBEntity;
import fr.esisar.frigolo.entities.CapteurLogiqueEJBEntity;
import fr.esisar.frigolo.entities.CapteurNumeriqueEJBEntity;
import fr.esisar.frigolo.entities.ConsigneEJBEntity;
import fr.esisar.frigolo.entities.FrigidaireEJBEntity;
import fr.esisar.frigolo.entities.MesureEJBEntity;
import fr.esisar.frigolo.entities.MesureLogiqueEJBEntity;
import fr.esisar.frigolo.entities.MesureNumeriqueEJBEntity;
import fr.esisar.frigolo.entities.PersonneEJBEntity;
import fr.esisar.frigolo.entities.TypeCapteurEJBEntity;
import fr.esisar.frigolo.entities.UniteEJBEntity;
import fr.esisar.frigolo.mdb.HelloWorldQueueMDB;
import fr.esisar.frigolo.portail.HttpSecurityConfiguration;
import fr.esisar.frigolo.portail.Portail;
import fr.esisar.frigolo.session.stateless.ejb.AlerteEJBStateless;
import fr.esisar.frigolo.session.stateless.ejb.FrigidaireEJBStateless;
import fr.esisar.frigolo.session.stateless.ejb.PersonneEJBStateless;
import fr.esisar.frigolo.session.stateless.ejb.TypeCapteurEJBStateless;
import fr.esisar.frigolo.session.stateless.ejb.UniteEJBStateless;
import fr.esisar.frigolo.session.stateless.ejb.capteur.logique.CapteurLogiqueEJBStateless;
import fr.esisar.frigolo.session.stateless.ejb.capteur.numerique.CapteurNumeriqueEJBStateless;
import fr.esisar.frigolo.session.stateless.ejb.mesure.logique.MesureLogiqueEJBStateless;
import fr.esisar.frigolo.session.stateless.ejb.mesure.numerique.MesureNumeriqueEJBStateless;
import fr.esisar.frigolo.session.stateless.local.AlerteInterfaceLocal;
import fr.esisar.frigolo.session.stateless.local.FrigidaireInterfaceLocal;
import fr.esisar.frigolo.session.stateless.local.PersonneInterfaceLocal;
import fr.esisar.frigolo.session.stateless.local.TypeCapteurInterfaceLocal;
import fr.esisar.frigolo.session.stateless.local.UniteInterfaceLocal;
import fr.esisar.frigolo.session.stateless.local.capteur.logique.CapteurLogiqueInterfaceLocal;
import fr.esisar.frigolo.session.stateless.local.capteur.numerique.CapteurNumeriqueInterfaceLocal;
import fr.esisar.frigolo.session.stateless.local.mesure.logique.MesureLogiqueInterfaceLocal;
import fr.esisar.frigolo.session.stateless.local.mesure.numerique.MesureNumeriqueInterfaceLocal;
import fr.esisar.frigolo.session.stateless.remote.AlerteInterfaceRemote;
import fr.esisar.frigolo.session.stateless.remote.FrigidaireInterfaceRemote;
import fr.esisar.frigolo.session.stateless.remote.PersonneInterfaceRemote;
import fr.esisar.frigolo.session.stateless.remote.TypeCapteurInterfaceRemote;
import fr.esisar.frigolo.session.stateless.remote.UniteInterfaceRemote;
import fr.esisar.frigolo.session.stateless.remote.capteur.logique.CapteurLogiqueInterfaceRemote;
import fr.esisar.frigolo.session.stateless.remote.capteur.numerique.CapteurNumeriqueInterfaceRemote;
import fr.esisar.frigolo.session.stateless.remote.mesure.logique.MesureLogiqueInterfaceRemote;
import fr.esisar.frigolo.session.stateless.remote.mesure.numerique.MesureNumeriqueInterfaceRemote;

/**
 * 
 * @author BRESCH Cyril, DUHAMEL Quentin, LEONOR Maxime, MEYER Thomas
 * 
 * @brief
 *
 */
@RunWith(Arquillian.class)
public class AdministrateurControllerTest {

    private static final Logger log = Logger.getLogger(AdministrateurControllerTest.class.getName());

    @Inject
    private CapteurNumeriqueEJB CaptNumEJB;
    @Inject
    private CapteurLogiqueEJB CaptLogEJB;
    @Inject
    private UniteEJB unitEJB;
    @Inject
    private AlerteEJB alertEJB;
    @Inject
    private FrigidaireEJB fridgeEJB;
    @Inject
    private PersonneEJB persEJB;
    @Inject
    private TypeCapteurEJB typeCaptEJB;
    @Inject
    private AdministrateurController Admin;

    @Deployment
    public static Archive createTestArchive() {
        return ShrinkWrap.create(WebArchive.class, "testAdminCont.war").addClasses(CapteurNumeriqueEJBEntity.class,
                CapteurNumeriqueEJB.class, CapteurNumeriqueEJBStateless.class, CapteurNumeriqueInterfaceLocal.class,
                CapteurNumeriqueInterfaceRemote.class, CapteurEJBEntity.class, CapteurLogiqueEJBEntity.class,
                CapteurLogiqueEJB.class, CapteurLogiqueEJBStateless.class, CapteurLogiqueInterfaceLocal.class,
                CapteurLogiqueInterfaceRemote.class, MesureEJBEntity.class, MesureNumeriqueEJBEntity.class,
                MesureNumeriqueEJB.class, MesureNumeriqueEJBStateless.class, MesureNumeriqueInterfaceLocal.class,
                MesureNumeriqueInterfaceRemote.class, MesureLogiqueEJBEntity.class, MesureLogiqueEJB.class,
                MesureLogiqueEJBStateless.class, MesureLogiqueInterfaceLocal.class, MesureLogiqueInterfaceRemote.class,
                TypeCapteurEJBEntity.class, TypeCapteurEJB.class, TypeCapteurEJBStateless.class,
                TypeCapteurInterfaceLocal.class, TypeCapteurInterfaceRemote.class, UniteEJBEntity.class, UniteEJB.class,
                UniteEJBStateless.class, UniteInterfaceLocal.class, UniteInterfaceRemote.class,
                FrigidaireEJBEntity.class, FrigidaireEJB.class, FrigidaireEJBStateless.class,
                FrigidaireInterfaceLocal.class, FrigidaireInterfaceRemote.class, ConsigneEJBEntity.class,
                AlerteEJBEntity.class, AlerteEJB.class, AlerteEJBStateless.class, AlerteInterfaceLocal.class,
                AlerteInterfaceRemote.class, PersonneEJBEntity.class, PersonneEJB.class, PersonneEJBStateless.class,
                PersonneInterfaceLocal.class, PersonneInterfaceRemote.class, AdministrateurController.class,
                HelloWorldQueueMDB.class, HttpSecurityConfiguration.class, Portail.class, LineChartModel.class,
                CartesianChartModel.class, ChartModel.class, Axis.class, AttributedType.class, Account.class,
                IdentityType.class, PartitionManager.class, AttributedTypeManager.class)
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml").addAsWebInfResource("test-ds.xml");
    }

    @Test
    public void testAdmin() throws Exception {

        fridgeEJB.ajouterFrigidaire("TestFrigi");
        typeCaptEJB.ajouterTypeCapteur("TestTypeCapt");

        Long idFrigi = fridgeEJB.findFrigidaires().get(0).getIdFrigidaire();
        Long idTypeCapt = typeCaptEJB.findTypesCapteurs().get(0).getIdTypeCapteur();

        /***********************
         * Test Unitaire CapteurNumerique
         ************************/
        CaptNumEJB.ajouterCapteurNumerique("TestCaptNum", idFrigi, idTypeCapt);
        List<CapteurNumeriqueEJBEntity> TestCapNum = CaptNumEJB.findCapteursNumeriques();

        Assert.assertFalse(CaptNumEJB.findCapteursNumeriques().isEmpty());
        log.info("\n\n\nTest : CapteurNumerique is persisted\n\n\n");

        CaptNumEJB.deleteCapteurNumerique(TestCapNum.get(0));
        Assert.assertTrue(CaptNumEJB.findCapteursNumeriques().isEmpty());
        log.info("\n\n\nTest : CapteurNumerique is deleted\n\n\n");

        log.info("\n\n\nTestCapteurNumerique is Validated\n\n\n");
        /***********************
         * Test Unitaire CapteurNumerique
         ************************/

        /***********************
         * Test Unitaire Alerte
         **********************************/
        alertEJB.ajouterAlerte("TestAlerte", new Float(3.0), idFrigi);
        List<AlerteEJBEntity> TestAlerte = alertEJB.findAlertes();

        Assert.assertFalse(alertEJB.findAlertes().isEmpty());
        log.info("\n\n\nTest : Alerte is persisted\n\n\n");

        alertEJB.deleteAlerte(TestAlerte.get(0));
        Assert.assertTrue(alertEJB.findAlertes().isEmpty());
        log.info("\n\n\nTest : Alerte is deleted\n\n\n");

        log.info("\n\n\nTestAlerte is Validated\n\n\n");
        /***********************
         * Test Unitaire Alerte
         **********************************/

        /***********************
         * Test Unitaire CapteurLogique
         **************************/
        CaptLogEJB.ajouterCapteurLogique("TestCaptLog", idFrigi, idTypeCapt);
        List<CapteurLogiqueEJBEntity> TestCapLog = CaptLogEJB.findCapteursLogiques();

        Assert.assertFalse(CaptLogEJB.findCapteursLogiques().isEmpty());
        log.info("\n\n\nTest : CapteurLogique is persisted\n\n\n");

        CaptLogEJB.deleteCapteurLogique(TestCapLog.get(0));
        Assert.assertTrue(CaptLogEJB.findCapteursLogiques().isEmpty());
        log.info("\n\n\nTest : CapteurLogique is deleted\n\n\n");

        log.info("\n\n\nTestCapteurLogique is Validated\n\n\n");
        /***********************
         * Test Unitaire CapteurLogique
         **************************/

        /***********************
         * Test Unitaire Frigidaire
         ******************************/
        fridgeEJB.deleteFrigidaire(fridgeEJB.findFrigidaires().get(0));

        fridgeEJB.ajouterFrigidaire("TestFrigidaire");
        List<FrigidaireEJBEntity> TestFridge = fridgeEJB.findFrigidaires();

        Assert.assertFalse(fridgeEJB.findFrigidaires().isEmpty());
        log.info("\n\n\nTest : Frigidaire is persisted\n\n\n");

        fridgeEJB.deleteFrigidaire(TestFridge.get(0));
        Assert.assertFalse(fridgeEJB.findFrigidaires().contains(TestFridge.get(0)));
        log.info("\n\n\nTest : Frigidaire is deleted\n\n\n");

        log.info("\n\n\nTestFrigidaire is Validated\n\n\n");
        /***********************
         * Test Unitaire Frigidaire
         ******************************/

        /***********************
         * Test Unitaire Personne
         ********************************/
        persEJB.ajouterPersonne("Test", "Test");
        List<PersonneEJBEntity> TestPers = persEJB.findPersonnes();

        Assert.assertFalse(persEJB.findPersonnes().isEmpty());
        log.info("\n\n\nTest : Personne is persisted\n\n\n");

        persEJB.deletePersonne(TestPers.get(0));
        Assert.assertTrue(persEJB.findPersonnes().isEmpty());
        log.info("\n\n\nTest : Personne is deleted\n\n\n");

        log.info("\n\n\nTestPersonne is Validated\n\n\n");
        /***********************
         * Test Unitaire Personne
         ********************************/

        /***********************
         * Test getAllUnites()
         ***********************************/
        unitEJB.ajouterUnite("V");
        List<UniteEJBEntity> TestUnit = unitEJB.findUnites();

        Assert.assertFalse(unitEJB.findUnites().isEmpty());
        log.info("\n\n\nTest : Unite is persisted\n\n\n");

        List<UniteEJBEntity> Test = Admin.getAllUnites();
        Assert.assertFalse(Test.isEmpty());
        log.info("\n\n\nTest : getAllUnits is functional\n\n\n");

        unitEJB.deleteUnite(TestUnit.get(0));
        Assert.assertTrue(unitEJB.findUnites().isEmpty());
        log.info("\n\n\nTest : Unite is deleted\n\n\n");

        log.info("\n\n\nTest getAllUnites() is Validated\n\n\n");
        /***********************
         * Test getAllUnites()
         ***********************************/

    }

}
