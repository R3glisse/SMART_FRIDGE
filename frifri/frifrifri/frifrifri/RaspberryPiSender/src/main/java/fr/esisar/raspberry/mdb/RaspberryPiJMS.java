package fr.esisar.raspberry.mdb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Logger;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class RaspberryPiJMS implements Runnable, SerialPortEventListener {
    /**
     * logger for the java message service sending
     */
    private static final Logger log = Logger.getLogger(RaspberryPiJMS.class.getName());

    /**
     * set up message for the queue
     */
    private static final String DEFAULT_MESSAGE = "alerte-12-16.0-1222";
    /**
     * set up message for the queue
     */
    private static String MESSAGE_LUMIERE = "lumiere-false-3";
    /**
     * set up message for the queue
     */
    private static String MESSAGE_TEMPERATURE = "temperature-8.0-2-12";
    /**
     * set up message for the queue
     */
    private static String MESSAGE_AIR = "air-2.5-1-11";
    /**
     * queue parameter
     */
    private static final String DEFAULT_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
    /**
     * queue parameter
     */
    private static final String DEFAULT_DESTINATION = "jms/queue/test";
    /**
     * queue parameter
     */
    private static final String DEFAULT_MESSAGE_COUNT = "1";
    /**
     * queue parameter
     */
    private static final String DEFAULT_USERNAME = "quickstartUser";
    /**
     * queue parameter
     */
    private static final String DEFAULT_PASSWORD = "quickstartPwd1!";
    /**
     * queue parameter
     */
    private static final String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
    /**
     * queue parameter
     */
    private static final String PROVIDER_URL = "http-remoting://192.168.0.1:9990";

    /**
     * process to send a message from the raspberry to the queue using jms
     * 
     * @param args
     */

    private SerialPort serialPort;

    /** The port we're normally going to use. */
    private static final String PORT_NAMES[] = { "/dev/tty.usbserial-A9007UX1", // Mac
                                                                                // OS
                                                                                // X
            "/dev/ttyACM0", // Raspberry Pi
            "/dev/ttyUSB0", // Linux
            "COM3", // Windows
    };

    /**
     * A BufferedReader which will be fed by a InputStreamReader converting the
     * bytes into characters making the displayed results codepage independent
     */
    private BufferedReader input;

    /** The output stream to the port */
    private OutputStream output;

    /** Milliseconds to block while waiting for port open */
    private static final int TIME_OUT = 2000;

    /** Default bits per second for COM port. */
    private static final int DATA_RATE = 9600;

    public void initialize() {
        // the next line is for Raspberry Pi and
        // gets us into the while loop and was suggested here was suggested
        // http://www.raspberrypi.org/phpBB3/viewtopic.php?f=81&t=32186
        System.setProperty("gnu.io.rxtx.SerialPorts", "/dev/ttyACM0");

        CommPortIdentifier portId = null;
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

        // First, Find an instance of serial port as set in PORT_NAMES.
        while (portEnum.hasMoreElements()) {
            CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
            for (String portName : PORT_NAMES) {
                if (currPortId.getName().equals(portName)) {
                    portId = currPortId;
                    break;
                }
            }
        }
        if (portId == null) {
            System.out.println("Could not find COM port.");
            return;
        }

        try {
            // open serial port, and use class name for the appName.
            serialPort = (SerialPort) portId.open(this.getClass().getName(), TIME_OUT);

            // set port parameters
            serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);

            // open the streams
            input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
            output = serialPort.getOutputStream();

            // add event listeners
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public synchronized void close() {
        if (serialPort != null) {
            serialPort.removeEventListener();
            serialPort.close();
        }
    }

    /* xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx */
    public synchronized void serialEvent(SerialPortEvent oEvent) {
        System.out.println("coucou from  serial event");
        if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            try {
                String inputLine = input.readLine();

                System.out.println(inputLine);

                System.out.println("--> inputLine :" + inputLine);

                String[] infos = inputLine.split(" ");

                System.out.println("infos[0] = " + infos[0]);

                if (infos[0].equals("Analogical")) {
                    System.out.println("Analogical");

                    System.out.println("coiucuou  temp");
                    String[] splitArray2 = MESSAGE_TEMPERATURE.split("-");
                    splitArray2[1] = String.valueOf(infos[2]);
                    String transi = splitArray2[0] + "-" + splitArray2[1] + "-" + splitArray2[2] + "-" + splitArray2[3];
                    setMESSAGE_TEMPERATURE(transi);
                    Send(1);

                } else {
                    if (infos[0].equals("Digital")) {
                        System.out.println("coiucuou  serial event");
                        System.out.println("Digital");
                        String[] splitArray2 = MESSAGE_LUMIERE.split("-");
                        splitArray2[1] = String.valueOf((booleanConverter(infos[2])));
                        String transi = splitArray2[0] + "-" + splitArray2[1] + "-" + splitArray2[2];
                        setMESSAGE_LUMIERE(transi);
                        Send(2);
                    }
                }

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

    /* xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx */

    public static void main(String[] args) throws Exception {
        RaspberryPiJMS main = new RaspberryPiJMS();
        main.initialize();
        Thread t = new Thread() {
            public void run() {
                // the following line will keep this app alive for 1000 seconds,
                // waiting for events to occur and responding to them (printing
                // incoming messages to console).
                try {
                    Thread.sleep(1000000);
                } catch (InterruptedException ie) {
                }
            }
        };
        t.start();
        System.out.println("Started");
    }

    public void Send(int i) {
        Context namingContext = null;

        try {

            System.out.println("coucou from  send");
            String userName = System.getProperty("username", DEFAULT_USERNAME);
            String password = System.getProperty("password", DEFAULT_PASSWORD);

            // Set up the namingContext for the JNDI lookup
            final Properties env = new Properties();
            env.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
            env.put(Context.PROVIDER_URL, System.getProperty(Context.PROVIDER_URL, PROVIDER_URL));
            env.put(Context.SECURITY_PRINCIPAL, userName);
            env.put(Context.SECURITY_CREDENTIALS, password);
            namingContext = new InitialContext(env);

            // Perform the JNDI lookups
            String connectionFactoryString = System.getProperty("connection.factory", DEFAULT_CONNECTION_FACTORY);
            log.info("Attempting to acquire connection factory \"" + connectionFactoryString + "\"");
            ConnectionFactory connectionFactory = (ConnectionFactory) namingContext.lookup(connectionFactoryString);
            log.info("Found connection factory \"" + connectionFactoryString + "\" in JNDI");

            String destinationString = System.getProperty("destination", DEFAULT_DESTINATION);
            log.info("Attempting to acquire destination \"" + destinationString + "\"");
            Destination destination = (Destination) namingContext.lookup(destinationString);
            log.info("Found destination \"" + destinationString + "\" in JNDI");

            int count = Integer.parseInt(System.getProperty("message.count", DEFAULT_MESSAGE_COUNT));

            String alerte = System.getProperty("message.content", DEFAULT_MESSAGE);
            String lumiere = System.getProperty("message.content", MESSAGE_LUMIERE);
            String temperature = System.getProperty("message.content", MESSAGE_TEMPERATURE);
            String air = System.getProperty("message.content", MESSAGE_AIR);

            try (JMSContext context = connectionFactory.createContext(userName, password)) {

                String[] splitArray = DEFAULT_MESSAGE.split("-");
                String[] splitArray2 = MESSAGE_LUMIERE.split("-");
                String[] splitArray3 = MESSAGE_TEMPERATURE.split("-");
                // String[] splitArray4 = MESSAGE_AIR.split("-");

                // System.out.println("************************envoi" + " " +
                // splitArray[0] + " " + splitArray[1]
                // + " *********************************** ");
                System.out.println("************************envoi 2" + "  " + splitArray2[0] + "  " + splitArray2[1]
                        + " " + splitArray2[2] + " *********************************** ");
                System.out.println("************************envoi 2" + "  " + splitArray3[0] + "  " + splitArray3[1]
                        + " " + splitArray3[2] + " *********************************** ");
                // System.out.println("************************envoi 2" + " " +
                // splitArray4[0] + " " + splitArray4[1]
                // + " " + splitArray4[2] + "
                // *********************************** ");
                // Send the specified number of messages

                if (i == 1) {
                    context.createProducer().send(destination, temperature);
                }
                // context.createProducer().send(destination, alerte);

                // context.createProducer().send(destination, air);
                if (i == 1) {
                    context.createProducer().send(destination, lumiere);
                }

            }

        } catch (NamingException e) {
            log.severe(e.getMessage());
        } finally {
            if (namingContext != null) {
                try {
                    namingContext.close();
                } catch (NamingException e) {
                    log.severe(e.getMessage());
                }
            }
        }

    }

    private Boolean booleanConverter(String b) {
        if (b.equals("1")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub

    }

    public static String getMESSAGE_LUMIERE() {
        return MESSAGE_LUMIERE;
    }

    public static void setMESSAGE_LUMIERE(String mESSAGE_LUMIERE) {
        MESSAGE_LUMIERE = mESSAGE_LUMIERE;
    }

    public static String getMESSAGE_TEMPERATURE() {
        return MESSAGE_TEMPERATURE;
    }

    public static void setMESSAGE_TEMPERATURE(String mESSAGE_TEMPERATURE) {
        MESSAGE_TEMPERATURE = mESSAGE_TEMPERATURE;
    }

    public static String getMESSAGE_AIR() {
        return MESSAGE_AIR;
    }

    public static void setMESSAGE_AIR(String mESSAGE_AIR) {
        MESSAGE_AIR = mESSAGE_AIR;
    }

}
