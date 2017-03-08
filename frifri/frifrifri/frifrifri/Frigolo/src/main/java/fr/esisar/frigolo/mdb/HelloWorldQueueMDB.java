package fr.esisar.frigolo.mdb;

import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import fr.esisar.frigolo.session.stateful.AlerteEJB;
import fr.esisar.frigolo.session.stateful.MesureLogiqueEJB;
import fr.esisar.frigolo.session.stateful.MesureNumeriqueEJB;

@MessageDriven(name = "HelloWorldQueueMDB", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/test"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "user", propertyValue = "quickstartUser"),
        @ActivationConfigProperty(propertyName = "password", propertyValue = "quickstartPwd1!") })
public class HelloWorldQueueMDB implements MessageListener {

    /**
     * Constant
     */
    private static final int PARSE_ZERO = 0;

    /**
     * Constant
     */
    private static final int PARSE_ONE = 1;

    /**
     * Constant
     */
    private static final int PARSE_TWO = 2;

    /**
     * Constant
     */
    private static final int PARSE_THREE = 3;

    /**
     * alerte to inject in the database
     */
    @Inject
    private AlerteEJB alerteEJB;

    /**
     * numerical measure to inject in the database
     */
    @Inject
    private MesureNumeriqueEJB mesureNumeriqueEJB1;

    /**
     * numerical measure to inject in the database
     */
    @Inject
    private MesureNumeriqueEJB mesureNumeriqueEJB2;

    /**
     * logical measure to inject in the database
     */
    @Inject
    private MesureLogiqueEJB mesureLogiqueEJB;

    /**
     * logger for the hello world queue
     */
    private static final Logger LOGGER = Logger.getLogger(HelloWorldQueueMDB.class.toString());

    /*
     * (non-Javadoc)
     *
     * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
     */
    @Override
    public void onMessage(Message rcvMessage) {
        TextMessage msg = null;
        try {
            if (rcvMessage instanceof TextMessage) {

                msg = (TextMessage) rcvMessage;
                String[] splitArray = msg.getText().split("-");

                fromJMSToSQL(splitArray);

                LOGGER.info("Received Message from pfosst: " + msg.getText() + "  " + splitArray[PARSE_ZERO] + "  "
                        + splitArray[PARSE_ONE]);
            } else {
                LOGGER.warning("Message of wrong type: " + rcvMessage.getClass().getName());
            }
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param splitArray
     *            the array to split
     */
    public void fromJMSToSQL(String[] splitArray) {

        String type = splitArray[PARSE_ZERO];
        if ("alerte".equals(type)) {
            Float alerteValeur = Float.parseFloat(splitArray[PARSE_TWO]);
            Long idFrigidaire = Long.parseLong(splitArray[PARSE_THREE]);
            alerteEJB.ajouterAlerte(splitArray[PARSE_ONE], alerteValeur, idFrigidaire);
        } else if ("lumiere".equals(type)) {
            Boolean etat = Boolean.parseBoolean(splitArray[PARSE_ONE]);
            Long idCapteurLogique = Long.parseLong(splitArray[PARSE_TWO]);
            mesureLogiqueEJB.ajouterMesureLogique(etat, idCapteurLogique);
        } else if ("air".equals(type)) {
            Float valeur = Float.parseFloat(splitArray[PARSE_ONE]);
            Long idCapteurNumerique = Long.parseLong(splitArray[PARSE_TWO]);
            Long idUnite = Long.parseLong(splitArray[PARSE_THREE]);
            mesureNumeriqueEJB1.ajouterMesureNumerique(valeur, idCapteurNumerique, idUnite);
        } else if ("temperature".equals(type)) {
            Float valeurTemp = Float.parseFloat(splitArray[PARSE_ONE]);
            Long idCapteurNumeriqueTemp = Long.parseLong(splitArray[PARSE_TWO]);
            Long idUniteTemp = Long.parseLong(splitArray[PARSE_THREE]);
            mesureNumeriqueEJB2.ajouterMesureNumerique(valeurTemp, idCapteurNumeriqueTemp, idUniteTemp);
        }

    }

}
