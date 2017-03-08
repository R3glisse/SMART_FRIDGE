package fr.esisar.frigolo.session.stateful;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
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
import org.picketlink.idm.model.Account;
import org.picketlink.idm.model.AttributedType;
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

@RunWith(Arquillian.class)
public class capteursFromFrigidaireIdTest {

    private static final Logger log = Logger.getLogger(capteursFromFrigidaireIdTest.class.getName());

    @EJB
    private CapteurNumeriqueInterfaceLocal capteurNumEJB;
    @EJB
    private CapteurLogiqueInterfaceLocal capteurLogEJB;
    @EJB
    private MesureNumeriqueInterfaceLocal mesureNumEJB;
    @EJB
    private MesureLogiqueInterfaceLocal mesureLogEJB;
    @EJB
    private UniteInterfaceLocal uniteEJB;
    @EJB
    private AlerteInterfaceLocal alerteEJB;
    @EJB
    private FrigidaireInterfaceLocal frigidaireEJB;
    @Inject
    private FrigidaireEJB fridgeEJB;
    @EJB
    private PersonneInterfaceLocal personneEJB;
    @Inject
    private TypeCapteurEJB typeCaptEJB;
    @Inject
    private AdministrateurController Admin;

    @Deployment
    public static Archive createTestArchive() {
        return ShrinkWrap.create(WebArchive.class, "testcapteursFromFrigidaireIdCont.war")
                .addClasses(CapteurNumeriqueEJBEntity.class, CapteurNumeriqueEJB.class,
                        CapteurNumeriqueEJBStateless.class, CapteurNumeriqueInterfaceLocal.class,
                        CapteurNumeriqueInterfaceRemote.class, CapteurEJBEntity.class, CapteurLogiqueEJBEntity.class,
                        CapteurLogiqueEJB.class, CapteurLogiqueEJBStateless.class, CapteurLogiqueInterfaceLocal.class,
                        CapteurLogiqueInterfaceRemote.class, MesureEJBEntity.class, MesureNumeriqueEJBEntity.class,
                        MesureNumeriqueEJB.class, MesureNumeriqueEJBStateless.class,
                        MesureNumeriqueInterfaceLocal.class, MesureNumeriqueInterfaceRemote.class,
                        MesureLogiqueEJBEntity.class, MesureLogiqueEJB.class, MesureLogiqueEJBStateless.class,
                        MesureLogiqueInterfaceLocal.class, MesureLogiqueInterfaceRemote.class,
                        TypeCapteurEJBEntity.class, TypeCapteurEJB.class, TypeCapteurEJBStateless.class,
                        TypeCapteurInterfaceLocal.class, TypeCapteurInterfaceRemote.class, UniteEJBEntity.class,
                        UniteEJB.class, UniteEJBStateless.class, UniteInterfaceLocal.class, UniteInterfaceRemote.class,
                        FrigidaireEJBEntity.class, FrigidaireEJB.class, FrigidaireEJBStateless.class,
                        FrigidaireInterfaceLocal.class, FrigidaireInterfaceRemote.class, ConsigneEJBEntity.class,
                        AlerteEJBEntity.class, AlerteEJB.class, AlerteEJBStateless.class, AlerteInterfaceLocal.class,
                        AlerteInterfaceRemote.class, PersonneEJBEntity.class, PersonneEJB.class,
                        PersonneEJBStateless.class, PersonneInterfaceLocal.class, PersonneInterfaceRemote.class,
                        AdministrateurController.class, HelloWorldQueueMDB.class, HttpSecurityConfiguration.class,
                        Portail.class, LineChartModel.class, AttributedType.class, Account.class)
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml").addAsWebInfResource("test-ds.xml");
    }

    @Test
    public void testcapteursFromFrigidaireId() throws Exception {

        fridgeEJB.ajouterFrigidaire("TestFrigi");
        typeCaptEJB.ajouterTypeCapteur("TestTypeCapt");

        Long idFrigi = fridgeEJB.findFrigidaires().get(0).getIdFrigidaire();
        Long idTypeCapt = typeCaptEJB.findTypesCapteurs().get(0).getIdTypeCapteur();

        Admin.setCapteurLogiqueNom("Test");
        Admin.setFrigidaireId(idFrigi);
        Admin.setTypeCapteurId(idTypeCapt);

        Admin.ajouterCapteurLogique();

        Admin.setCapteurNumeriqueNom("Test");
        Admin.setFrigidaireId(idFrigi);
        Admin.setTypeCapteurId(idTypeCapt);

        Admin.ajouterCapteurNumerique();

        Admin.capteursFromFrigidaireId();

        List<CapteurLogiqueEJBEntity> TestCaptLog = Admin.getCapteursLogiques();
        Assert.assertFalse(TestCaptLog.isEmpty());
        List<CapteurNumeriqueEJBEntity> TestCaptNum = Admin.getCapteursNumeriques();
        Assert.assertFalse(TestCaptNum.isEmpty());

        log.info("\n\n\nidFrigi is " + idFrigi + "\nId of added CaptNum is " + TestCaptNum.get(0).getIdCapteur()
                + "\nId of added CaptLog is " + TestCaptLog.get(0).getIdCapteur() + "\n");

        log.info("\n\n\nTest capteursFromFrigidaireId() is Validated\n\n\n");

    }

}
