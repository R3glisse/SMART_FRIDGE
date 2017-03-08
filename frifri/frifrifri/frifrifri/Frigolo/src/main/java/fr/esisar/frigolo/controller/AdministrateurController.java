package fr.esisar.frigolo.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;

import fr.esisar.frigolo.entities.CapteurLogiqueEJBEntity;
import fr.esisar.frigolo.entities.CapteurNumeriqueEJBEntity;
import fr.esisar.frigolo.entities.FrigidaireEJBEntity;
import fr.esisar.frigolo.entities.MesureLogiqueEJBEntity;
import fr.esisar.frigolo.entities.MesureNumeriqueEJBEntity;
import fr.esisar.frigolo.entities.TypeCapteurEJBEntity;
import fr.esisar.frigolo.entities.UniteEJBEntity;
import fr.esisar.frigolo.session.stateful.CapteurLogiqueEJB;
import fr.esisar.frigolo.session.stateful.CapteurNumeriqueEJB;
import fr.esisar.frigolo.session.stateful.FrigidaireEJB;
import fr.esisar.frigolo.session.stateful.MesureLogiqueEJB;
import fr.esisar.frigolo.session.stateful.MesureNumeriqueEJB;
import fr.esisar.frigolo.session.stateful.TypeCapteurEJB;
import fr.esisar.frigolo.session.stateful.UniteEJB;

@Named
@SessionScoped
public class AdministrateurController implements Serializable {

    /**
     * Key that makes the object serializable
     */
    private static final long serialVersionUID = 8730837296709654667L;

    /**
     * Constant
     */
    private static final long TEMPERATURE_TYPE_CAPTEUR_ID = 2L;

    /**
     * Constant
     */
    private static final int RANGE_OF_MEASURE = 10;

    /**
     * Constant
     */
    private static final int TICK_ANGLE = -45;

    /**
     * Constant
     */
    private static final int MIN_Y_AXIS_NUM = 15;

    /**
     * Constant
     */
    private static final int MAX_Y_AXIS_NUM = 30;

    /**
     * Constant
     */
    private static final int MIN_Y_AXIS_LOG = 0;

    /**
     * Constant
     */
    private static final int MAX_Y_AXIS_LOG = 2;

    /**
     * Constant
     */
    private static final String SENSOR_NOT_EXISTING = "This sensor doesn't exist";

    /**
     * Constant
     */
    private static final String REDIRECTION = "?faces-redirect=true";

    /*********************************** Stateful **********************/
    /**
     * Stateful of unite entity
     */
    @Inject
    private UniteEJB uniteEJB;

    /**
     * Stateful of numeric measure entity
     */
    @Inject
    private MesureNumeriqueEJB mesureNumeriqueEJB;

    /**
     * Stateful of logic measure entity
     */
    @Inject
    private MesureLogiqueEJB mesureLogiqueEJB;

    /**
     * Stateful of numeric sensor entity
     */
    @Inject
    private CapteurNumeriqueEJB capteurNumeriqueEJB;

    /**
     * Stateful of logic sensor entity
     */
    @Inject
    private CapteurLogiqueEJB capteurLogiqueEJB;

    /**
     * Stateful of the type of the sensor entity
     */
    @Inject
    private TypeCapteurEJB typeCapteurEJB;

    /**
     * Stateful of fridge entity
     */
    @Inject
    private FrigidaireEJB frigidaireEJB;

    /*********************************** Stateful **********************/
    /**************************** Operation message variables *********/
    /**
     * buffer to the view to display error messages
     */
    private String capteurNumeriqueOutput = null;
    /**
     * buffer to the view to display error messages
     */
    private String capteurLogiqueOutput = null;
    /**
     * buffer to the view to display error messages
     */
    private String mesuresNumeriquesOutput = null;
    /**
     * buffer to the view to display error messages
     */
    private String mesuresLogiquesOutput = null;
    /**
     * buffer to the view to display error messages
     */
    private String frigidaireOutput = null;

    /**************************** Operation message variables *********/

    /*********************************** beans variables **************/
    /**
     * buffer that makes link between view and controller
     */
    private Long mesureId = null;

    /**
     * buffer that makes link between view and controller
     */
    private Long capteurNumeriqueId = null;
    /**
     * buffer that makes link between view and controller
     */
    private String capteurNumeriqueNom = null;

    /**
     * buffer that makes link between view and controller
     */
    private Long capteurLogiqueId = null;
    /**
     * buffer that makes link between view and controller
     */
    private String capteurLogiqueNom = null;

    /**
     * buffer that makes link between view and controller
     */
    private Long typeCapteurId = null;

    /**
     * buffer that makes link between view and controller
     */
    private Long frigidaireId = null;
    /**
     * buffer that makes link between view and controller
     */
    private String frigidaireNom = null;

    /**
     * buffer that makes link between view and controller
     */
    private Long uniteId = null;

    /**
     * buffer that makes link between view and controller
     */
    private String capteurNom = null;

    /**
     * list that is displayed by the view
     */
    private List<MesureNumeriqueEJBEntity> mesuresNumeriques = new ArrayList<MesureNumeriqueEJBEntity>();
    /**
     * list that is displayed by the view
     */
    private List<MesureLogiqueEJBEntity> mesuresLogiques = new ArrayList<MesureLogiqueEJBEntity>();
    /**
     * list that is displayed by the view
     */
    private List<CapteurNumeriqueEJBEntity> capteursNumeriques = new ArrayList<CapteurNumeriqueEJBEntity>();
    /**
     * list that is displayed by the view
     */
    private List<CapteurLogiqueEJBEntity> capteursLogiques = new ArrayList<CapteurLogiqueEJBEntity>();
    /**
     * list that is displayed by the view
     */
    private List<TypeCapteurEJBEntity> typesCapteurs = new ArrayList<TypeCapteurEJBEntity>();
    /**
     * list that is displayed by the view
     */
    private List<FrigidaireEJBEntity> frigidaires = new ArrayList<FrigidaireEJBEntity>();

    /*********************************** beans variables ***************/

    /**************************** Prime Faces Variables and getters ******/

    /**
     * line chart that is used to display the curves of numeric measures
     */
    private LineChartModel lineModelMesNum = new LineChartModel();
    /**
     * line chart that is used to display the curves of logic measures
     */
    private LineChartModel lineModelMesLog = new LineChartModel();

    /**
     * the X axis of the line charts that represent the date
     */
    DateAxis xAxis = new DateAxis("Dates");

    /**
     * the Y axis of the line charts
     */
    private Axis yAxis;

    /**
     * the getter of the line chart of numeric measures
     *
     * @return a line chart of numeric measures
     */
    public LineChartModel getLineModelMesNum() {
        return lineModelMesNum;
    }

    /**
     * the getter of the line chart of logic measures
     *
     * @return a line chart of logic measures
     */
    public LineChartModel getLineModelMesLog() {
        return lineModelMesLog;
    }

    /**************************** Prime Faces Variables and getters ******/

    /******************** getters used on messages ***********************/
    /**
     * getter of the buffer to the view to display error messages
     *
     * @return the buffer of the numeric sensor
     */
    public String getCapteurNumeriqueOutput() {
        return this.capteurNumeriqueOutput;
    }

    /**
     * getter of the buffer to the view to display error messages
     *
     * @return the buffer of the logic sensor
     */
    public String getCapteurLogiqueOutput() {
        return this.capteurLogiqueOutput;
    }

    /**
     * getter of the buffer to the view to display error messages
     *
     * @return the buffer of the numeric measures
     */
    public String getMesuresNumeriquesOutput() {
        return this.mesuresNumeriquesOutput;
    }

    /**
     * getter of the buffer to the view to display error messages
     *
     * @return the buffer of the logic measures
     */
    public String getMesuresLogiquesOutput() {
        return this.mesuresLogiquesOutput;
    }

    /**
     * getter of the buffer to the view
     *
     * @return the buffer of the fridge
     */
    public String getFrigidaireOutput() {
        return this.frigidaireOutput;
    }

    /******************** getters used on messages ***********************/

    /*************** getters and setters beans variables ***************/
    /**
     * setter of the buffer variable of the measure identifier
     *
     * @param id
     *            : identifier to set
     */
    public void setMesureId(Long id) {
        this.mesureId = id;
    }

    /**
     * getter of the buffer variable of the measure identifier
     *
     * @return the identifier of the measure set by the view
     */
    public Long getMesureId() {
        return this.mesureId;
    }

    /**
     * setter of the buffer variable of the numeric sensor identifier
     *
     * @param id
     *            : the identifier to set
     */
    public void setCapteurNumeriqueId(Long id) {
        this.capteurNumeriqueId = id;
    }

    /**
     * getter of the buffer variable of the numeric sensor identifier
     *
     * @return the of the numeric sensor identifier
     */
    public Long getCapteurNumeriqueId() {
        return this.capteurNumeriqueId;
    }

    /**
     * setter of the buffer variable of the numeric sensor name
     *
     * @param nom
     *            : the name of the numeric sensor buffer
     */
    public void setCapteurNumeriqueNom(String nom) {
        this.capteurNumeriqueNom = nom;
    }

    /**
     * getter of the buffer variable of the numeric sensor name
     *
     * @return the name of the numeric sensor buffer
     */
    public String getCapteurNumeriqueNom() {
        return this.capteurNumeriqueNom;
    }

    /**
     * setter of the buffer variable of the logic sensor name
     *
     * @param id
     *            : the identifier to set
     */
    public void setCapteurLogiqueId(Long id) {
        this.capteurLogiqueId = id;
    }

    /**
     * getter of the buffer variable of the logic sensor name
     *
     * @return the identifier of the logic sensor
     */
    public Long getCapteurLogiqueId() {
        return this.capteurLogiqueId;
    }

    /**
     * setter of the buffer variable of the logic sensor name
     *
     * @param nom
     *            : the name of the logic sensor buffer
     */
    public void setCapteurLogiqueNom(String nom) {
        this.capteurLogiqueNom = nom;
    }

    /**
     * getter of the buffer variable of the logic sensor name
     *
     * @return the name of the logic sensor buffer
     */
    public String getCapteurLogiqueNom() {
        return this.capteurLogiqueNom;
    }

    /**
     * setter of the buffer variable of the type of sensor identifier
     *
     * @param id
     *            : the identifier to set
     */
    public void setTypeCapteurId(Long id) {
        this.typeCapteurId = id;
    }

    /**
     * getter of the buffer variable of the type of sensor identifier
     *
     * @return the identifier of the type of sensor buffer
     */
    public Long getTypeCapteurId() {
        return this.typeCapteurId;
    }

    /**
     * setter of the buffer variable of the fridge identifier
     *
     * @param id
     *            : the identifier to set
     */
    public void setFrigidaireId(Long id) {
        this.frigidaireId = id;
    }

    /**
     * getter of the buffer variable of the fridge identifier
     *
     * @return the identifier of the fridge buffer
     */
    public Long getFrigidaireId() {
        return this.frigidaireId;
    }

    /**
     * setter of the buffer variable of the fridge name
     *
     * @param nom
     *            : the name to set
     */
    public void setFrigidaireNom(String nom) {
        this.frigidaireNom = nom;
    }

    /**
     * getter of the buffer variable of the fridge name
     *
     * @return the name of the fridge buffer
     */
    public String getFrigidaireNom() {
        return this.frigidaireNom;
    }

    /**
     * setter of the buffer variable of the unit identifier
     *
     * @return the identifier of the unit buffer
     */
    public Long getIdUnite() {
        return this.uniteId;
    }

    /**
     * getter of the buffer variable of the unit identifier
     *
     * @param idUnite
     *            : the identifier of the unit buffer
     */
    public void setIdUnite(Long idUnite) {
        this.uniteId = idUnite;
    }

    /**
     * setter of the buffer variable of the sensor name
     *
     * @param capteurNom
     *            : the name to set
     */
    public void setCapteurNom(String capteurNom) {
        this.capteurNom = capteurNom;
    }

    /**
     * getter of the buffer variable of the sensor name
     *
     * @return the name of the sensor buffer
     */
    public String getCapteurNom() {
        return this.capteurNom;
    }

    /**
     * getter of the list buffer to display of numeric measures
     *
     * @return a list of numeric measures
     */
    public List<MesureNumeriqueEJBEntity> getMesuresNumeriques() {
        return this.mesuresNumeriques;
    }

    /**
     * getter of the list buffer to display of logic measures
     *
     * @return a list of logic measures
     */
    public List<MesureLogiqueEJBEntity> getMesuresLogiques() {
        return this.mesuresLogiques;
    }

    /**
     * getter of the list buffer to display of numeric sensor
     *
     * @return a list of numeric sensors
     */
    public List<CapteurNumeriqueEJBEntity> getCapteursNumeriques() {
        return this.capteursNumeriques;
    }

    /**
     * getter of the list buffer to display of logic sensor
     *
     * @return a list of logic sensors
     */
    public List<CapteurLogiqueEJBEntity> getCapteursLogiques() {
        return this.capteursLogiques;
    }

    /**
     * getter of the list buffer to display of type of sensor
     *
     * @return a list of types of sensors
     */
    public List<TypeCapteurEJBEntity> getTypesCapteurs() {
        this.typesCapteurs = this.typeCapteurEJB.findTypesCapteurs();
        return this.typesCapteurs;
    }

    /**
     * getter of the list buffer to display of fridges
     *
     * @return a list of fridges
     */
    public List<FrigidaireEJBEntity> getFrigidaires() {
        this.frigidaires = this.frigidaireEJB.findFrigidaires();
        return this.frigidaires;
    }

    /*************** getters and setters beans variables ***************/

    /******************** advanced methods ********************************/

    /**
     * getter of the units available in database
     *
     * @return a list of units
     */
    public List<UniteEJBEntity> getAllUnites() {
        return this.uniteEJB.findUnites();
    }

    /**
     * getter of unit from a measure identifier
     *
     * @return the name of the unit
     */
    public String getUniteFromMesureId() {

        if (this.mesureId != null) {
            Boolean mesureIdInMesuresNumeriques = false;
            List<MesureNumeriqueEJBEntity> mesuresNums = mesureNumeriqueEJB.findMesuresNumeriques();

            for (int i = 0; i < mesuresNums.size(); i++) {
                if (this.mesureId.equals(mesuresNums.get(i).getIdMesure())) {
                    mesureIdInMesuresNumeriques = true;
                }
            }

            if (mesureIdInMesuresNumeriques) {
                return mesureNumeriqueEJB.findUniteFromMesureId(this.mesureId).getUnite();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * get a list of numeric measures from the numeric sensor identifier
     *
     * @return the url redirection
     */
    public String mesuresNumeriquesFromCapteurId() {

        if (this.capteurNumeriqueId != null) {

            List<CapteurNumeriqueEJBEntity> capteursNums = this.capteurNumeriqueEJB.findCapteursNumeriques();
            Boolean existingSensor = false;
            for (int i = 0; i < capteursNums.size(); i++) {
                if (capteursNums.get(i).getIdCapteur().equals(this.capteurNumeriqueId)) {
                    existingSensor = true;
                }
            }

            if (existingSensor) {
                this.mesuresNumeriques = mesureNumeriqueEJB.findMesuresNumeriquesFromCapteurId(this.capteurNumeriqueId);
                this.mesuresNumeriquesOutput = null;
                createLineModelMesNum();
            } else {
                this.mesuresNumeriquesOutput = SENSOR_NOT_EXISTING;
            }
        } else {
            this.mesuresNumeriquesOutput = "No measure associated to this sensor";
            this.mesuresNumeriques = null;
        }

        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId + REDIRECTION;
    }

    /**
     * reset all the measures in database from the numeric sensor identifier
     *
     * @return the url redirection
     */
    public String mesuresNumeriquesFromCapteurIdRAZ() {

        List<CapteurNumeriqueEJBEntity> capteursNums = this.capteurNumeriqueEJB.findCapteursNumeriques();
        Boolean existingSensor = false;

        for (int i = 0; i < capteursNums.size(); i++) {

            if (capteursNums.get(i).getIdCapteur().equals(this.capteurNumeriqueId)) {
                existingSensor = true;
            }

        }

        if (existingSensor) {
            mesuresNumeriquesFromCapteurId();

            if (this.mesuresNumeriques != null) {
                mesureNumeriqueEJB.deleteAllFromMesuresNumeriquesList(this.mesuresNumeriques);
                this.mesuresNumeriquesOutput = "Measures deleted";
                this.mesuresNumeriques = mesureNumeriqueEJB.findMesuresNumeriquesFromCapteurId(this.capteurNumeriqueId);
                createLineModelMesNum();
            } else {
                this.mesuresNumeriquesOutput = null;
            }

        } else {
            this.mesuresNumeriquesOutput = SENSOR_NOT_EXISTING;
        }

        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId + REDIRECTION;
    }

    /**
     * get the logic measures from the logic sensor identifier
     */
    public void mesuresLogiquesFromCapteurId() {

        if (this.capteurLogiqueId != null) {

            List<CapteurLogiqueEJBEntity> capteursLogs = this.capteurLogiqueEJB.findCapteursLogiques();
            Boolean existingSensor = false;
            for (int i = 0; i < capteursLogs.size(); i++) {
                if (capteursLogs.get(i).getIdCapteur().equals(this.capteurLogiqueId)) {
                    existingSensor = true;
                }
            }

            if (existingSensor) {
                this.mesuresLogiques = mesureLogiqueEJB.findMesuresLogiquesFromCapteurId(this.capteurLogiqueId);
                createLineModelMesLog();
                this.mesuresLogiquesOutput = null;
            } else {
                this.mesuresLogiquesOutput = SENSOR_NOT_EXISTING;
            }
        } else {
            this.mesuresLogiquesOutput = "No measure associated to this sensor";
            this.mesuresLogiques = null;
        }
    }

    /**
     * reset all the measures from the logic sensor
     */
    public void mesuresLogiquesFromCapteurIdRAZ() {

        List<CapteurLogiqueEJBEntity> capteursLogs = this.capteurLogiqueEJB.findCapteursLogiques();
        Boolean existingSensor = false;

        for (int i = 0; i < capteursLogs.size(); i++) {

            if (capteursLogs.get(i).getIdCapteur().equals(this.capteurLogiqueId)) {
                existingSensor = true;
            }

        }

        if (existingSensor) {
            mesuresLogiquesFromCapteurId();

            if (this.mesuresLogiques != null) {
                mesureLogiqueEJB.deleteAllFromMesuresLogiquesList(this.mesuresLogiques);
                this.mesuresLogiquesOutput = "Measures deleted";
                this.mesuresLogiques = mesureLogiqueEJB.findMesuresLogiquesFromCapteurId(this.capteurLogiqueId);
                createLineModelMesLog();
            } else {
                this.mesuresLogiquesOutput = null;
            }

        } else {
            this.mesuresLogiquesOutput = SENSOR_NOT_EXISTING;
        }
    }

    /**
     * add a new numeric sensor
     */
    public void ajouterCapteurNumerique() {

        if (!"".equals(this.capteurNumeriqueNom)) {
            this.capteurNumeriqueEJB.ajouterCapteurNumerique(this.capteurNumeriqueNom, this.frigidaireId,
                    this.typeCapteurId);
            this.capteurNumeriqueOutput = "New sensor registred";
        } else {
            this.capteurNumeriqueOutput = null;
        }
    }

    /**
     * delete a numeric sensor from its identifier
     *
     * @return the link to the redirection
     */
    public String suppressionCapteurNumerique() {

        if (this.capteurNumeriqueId != null) {
            List<CapteurNumeriqueEJBEntity> currentSensors = capteurNumeriqueEJB.findCapteursNumeriques();

            Boolean existingSensor = false;
            int i;

            for (i = 0; i < currentSensors.size(); i++) {
                if (this.capteurNumeriqueId.equals(currentSensors.get(i).getIdCapteur())) {
                    existingSensor = true;
                    break;
                }
            }

            if (existingSensor) {
                Boolean possibleToSuppress = true;
                List<CapteurNumeriqueEJBEntity> sensorsInUse = mesureNumeriqueEJB.findCapteursNumeriquesUsed();

                for (int j = 0; j < sensorsInUse.size(); j++) {

                    if (sensorsInUse.get(j).equals(currentSensors.get(i))) {
                        this.mesuresNumeriquesOutput = "Sensor relied to a measure, impossible to suppress now";
                        possibleToSuppress = false;
                        break;
                    }

                }
                if (possibleToSuppress) {
                    this.capteurNumeriqueEJB.deleteCapteurNumerique(
                            this.capteurNumeriqueEJB.findCapteurNumeriqueById(this.capteurNumeriqueId));
                    this.mesuresNumeriquesOutput = "Sensor suppressed";
                    createLineModelMesNum();
                }
            } else {
                this.mesuresNumeriquesOutput = SENSOR_NOT_EXISTING;
            }
        } else {
            this.mesuresNumeriquesOutput = null;
        }

        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId + REDIRECTION;

    }

    /**
     * add a logic sensor
     */
    public void ajouterCapteurLogique() {

        if (!"".equals(this.capteurLogiqueNom)) {
            this.capteurLogiqueEJB.ajouterCapteurLogique(this.capteurLogiqueNom, this.frigidaireId, this.typeCapteurId);
            this.capteurLogiqueOutput = "New sensor registred";
        } else {
            this.capteurLogiqueOutput = null;
        }
    }

    /**
     * delete the logic sensor specified by its identifier
     */
    public void suppressionCapteurLogique() {

        if (this.capteurLogiqueId != null) {
            List<CapteurLogiqueEJBEntity> currentSensors = capteurLogiqueEJB.findCapteursLogiques();

            Boolean existingUnit = false;
            int i;

            for (i = 0; i < currentSensors.size(); i++) {
                if (this.capteurLogiqueId.equals(currentSensors.get(i).getIdCapteur())) {
                    existingUnit = true;
                    break;
                }
            }

            if (existingUnit) {
                Boolean possibleToSuppress = true;
                List<CapteurLogiqueEJBEntity> sensorsInUse = mesureLogiqueEJB.findCapteursLogiquesUsed();

                for (int j = 0; j < sensorsInUse.size(); j++) {

                    if (sensorsInUse.get(j).equals(currentSensors.get(i))) {
                        this.mesuresLogiquesOutput = "Sensor relied to a measure, impossible to suppress now";
                        possibleToSuppress = false;
                        break;
                    }

                }
                if (possibleToSuppress) {
                    this.capteurLogiqueEJB
                            .deleteCapteurLogique(this.capteurLogiqueEJB.findCapteurLogiqueById(this.capteurLogiqueId));
                    this.mesuresLogiquesOutput = "Sensor suppressed";
                    capteursFromFrigidaireId();
                    createLineModelMesLog();
                }
            } else {
                this.mesuresLogiquesOutput = SENSOR_NOT_EXISTING;
            }
        } else {
            this.mesuresLogiquesOutput = null;
        }
    }

    /**
     * refresh the lists capteursNumeriques and capteursLogiques from the type
     * of sensor identifier
     */
    public void capteursFromTypeCapteurId() {

        if (this.typeCapteurId != null) {
            this.capteursNumeriques = capteurNumeriqueEJB.findCapteursNumeriquesByTypeCapteurId(this.typeCapteurId);
            this.capteursLogiques = capteurLogiqueEJB.findCapteursLogiquesByTypeCapteurId(this.typeCapteurId);
        }

    }

    /**
     * refresh the lists capteursNumeriques and capteursLogiques from the fridge
     * identifier
     */
    public void capteursFromFrigidaireId() {

        if (this.frigidaireId != null) {
            this.capteursNumeriques = capteurNumeriqueEJB.findCapteursNumeriquesByTypeFrigidaireId(this.frigidaireId);
            this.capteursLogiques = capteurLogiqueEJB.findCapteursLogiquesByTypeFrigidaireId(this.frigidaireId);
        }
    }

    /**
     * add a fridge from its name
     */
    public void ajouterFrigidaire() {

        if (!"".equals(this.frigidaireNom)) {
            this.frigidaireEJB.ajouterFrigidaire(this.frigidaireNom);
            this.frigidaireOutput = "New fridge registred";
        } else {
            this.frigidaireOutput = null;
        }
    }

    /**
     * delete of a fridge from ots identifier
     */
    public void suppressionFrigidaire() {

        if (this.frigidaireId != null) {
            List<FrigidaireEJBEntity> currentFriges = frigidaireEJB.findFrigidaires();

            Boolean existingFrige = false;
            int i;

            for (i = 0; i < currentFriges.size(); i++) {
                if (this.frigidaireId.equals(currentFriges.get(i).getIdFrigidaire())) {
                    existingFrige = true;
                    break;
                }
            }

            if (existingFrige) {
                Boolean possibleToSuppress = true;
                List<FrigidaireEJBEntity> fridgesInUse = capteurNumeriqueEJB.findFrigidairesUsed();
                fridgesInUse.addAll(capteurLogiqueEJB.findFrigidairesUsed());

                for (int j = 0; j < fridgesInUse.size(); j++) {

                    if (fridgesInUse.get(j).equals(currentFriges.get(i))) {
                        this.frigidaireOutput = "Frige relied to sensors, impossible to suppress now";
                        possibleToSuppress = false;
                        break;
                    }

                }
                if (possibleToSuppress) {
                    this.frigidaireEJB.deleteFrigidaire(this.frigidaireEJB.findFrigidaireById(this.frigidaireId));
                    this.frigidaireOutput = "Frige suppressed";
                }
            } else {
                this.frigidaireOutput = "This fridge doesn't exist";
            }
        } else {
            this.frigidaireOutput = null;
        }
    }

    /**
     * add a sensor, that method guesses if its numric or logic sensor by the
     * type of sensor identifier
     */
    public void ajouterCapteur() {

        List<TypeCapteurEJBEntity> typesCap = typeCapteurEJB.findTypesCapteurs();
        String typeCapteurNom = null;

        for (int i = 0; i < typesCap.size(); i++) {
            if (typesCap.get(i).getIdTypeCapteur().equals(this.typeCapteurId)) {
                typeCapteurNom = typesCap.get(i).getType();
            }
        }

        if (typeCapteurNom != null) {
            if ("Temperature".equals(typeCapteurNom) || "Qualite d'air".equals(typeCapteurNom)) {
                capteurNumeriqueEJB.ajouterCapteurNumerique(this.capteurNom, this.frigidaireId, this.typeCapteurId);
            } else if ("Lumiere".equals(typeCapteurNom)) {
                capteurLogiqueEJB.ajouterCapteurLogique(this.capteurNom, this.frigidaireId, this.typeCapteurId);
            }
        }

        capteursFromFrigidaireId();

    }

    /**
     * a method that is used to refresh the list of sensor available on the
     * temperature sensor page
     */
    public void goingToTemperature() {
        this.typeCapteurId = TEMPERATURE_TYPE_CAPTEUR_ID;

        if (this.frigidaireId != null && this.typeCapteurId != null) {

            capteursFromFrigidaireId();
            List<CapteurNumeriqueEJBEntity> capteursByFrigidaireId = this.capteursNumeriques;

            capteursFromTypeCapteurId();
            List<CapteurNumeriqueEJBEntity> capteursByTypeCapteurId = this.capteursNumeriques;

            List<CapteurNumeriqueEJBEntity> result = new ArrayList<CapteurNumeriqueEJBEntity>();
            for (int i = 0; i < capteursByTypeCapteurId.size(); i++) {
                for (int j = 0; j < capteursByFrigidaireId.size(); j++) {
                    if (capteursByTypeCapteurId.get(i).equals(capteursByFrigidaireId.get(j))) {
                        result.add(capteursByTypeCapteurId.get(i));
                    }
                }
            }
            this.capteursNumeriques = result;
        }
    }

    /**
     * a method that is used to refresh the list of sensor available on the air
     * quality sensor page
     */
    public void goingToAirQuality() {
        this.typeCapteurId = (long) 1;

        if (this.frigidaireId != null && this.typeCapteurId != null) {

            capteursFromFrigidaireId();
            List<CapteurNumeriqueEJBEntity> capteursByFrigidaireId = this.capteursNumeriques;

            capteursFromTypeCapteurId();
            List<CapteurNumeriqueEJBEntity> capteursByTypeCapteurId = this.capteursNumeriques;

            List<CapteurNumeriqueEJBEntity> result = new ArrayList<CapteurNumeriqueEJBEntity>();
            for (int i = 0; i < capteursByTypeCapteurId.size(); i++) {
                for (int j = 0; j < capteursByFrigidaireId.size(); j++) {
                    if (capteursByTypeCapteurId.get(i).equals(capteursByFrigidaireId.get(j))) {
                        result.add(capteursByTypeCapteurId.get(i));
                    }
                }
            }
            this.capteursNumeriques = result;
        }

    }

    /******************** advanced methods ********************************/

    /******************** PrimeFaces methods ********************************/

    /********* Ajout pour le Line model MesNum ****************/
    /**
     * create a line chart that is used to display curves of numeric measures
     */
    private void createLineModelMesNum() {
        if (capteurNumeriqueId != null) {
            lineModelMesNum = initCategoryModelMesNum();
            lineModelMesNum.setTitle("Mesure Numerique");
            lineModelMesNum.setZoom(true);
            xAxis.setTickAngle(TICK_ANGLE);
            xAxis.setTickFormat("%#d-%m-%y,%T");
            lineModelMesNum.getAxes().put(AxisType.X, xAxis);
            yAxis = lineModelMesNum.getAxis(AxisType.Y);
            if (!this.mesuresNumeriques.isEmpty()) {
                this.mesureId = this.mesuresNumeriques.get(0).getIdMesure();
                yAxis.setLabel("Valeur (" + getUniteFromMesureId() + ")");
            } else {
                yAxis.setLabel("Valeur");
            }
            yAxis.setMin(MIN_Y_AXIS_NUM);
            yAxis.setMax(MAX_Y_AXIS_NUM);
        }
    }

    /**
     * initialization of the line chart associated to numeric measures
     *
     * @return a line chart model of the numeric measures
     */
    private LineChartModel initCategoryModelMesNum() {
        LineChartModel model = new LineChartModel();
        ChartSeries tempGraph = new ChartSeries();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        int range = 0;
        if (this.mesuresNumeriques.size() < RANGE_OF_MEASURE) {
            range = this.mesuresLogiques.size();
        } else {
            range = RANGE_OF_MEASURE;
        }

        for (int i = this.mesuresNumeriques.size() - range; i < this.mesuresNumeriques.size(); i++) {
            tempGraph.set(format.format(this.mesuresNumeriques.get(i).getTimeStamp()),
                    this.mesuresNumeriques.get(i).getValeur());
        }

        xAxis.setMin(format.format(this.mesuresNumeriques.get(this.mesuresNumeriques.size() - range).getTimeStamp()));
        xAxis.setMax(format.format(this.mesuresNumeriques.get(this.mesuresNumeriques.size() - 1).getTimeStamp()));

        model.addSeries(tempGraph);
        return model;

    }

    /********* Fin Ajout pour le Line model MesNum ****************/

    /********* Fin Ajout pour le Line model MesLog ****************/
    /**
     * create a line chart that is used to display curves of logic measures
     */
    private void createLineModelMesLog() {
        if (this.capteurLogiqueId != null) {
            lineModelMesLog = initCategoryModelMesLog();
            lineModelMesLog.setTitle("Heures d'ouverture");
            lineModelMesLog.setZoom(false);

            xAxis.setTickAngle(TICK_ANGLE);
            xAxis.setTickFormat("%#d-%m-%y,%T");
            lineModelMesLog.getAxes().put(AxisType.X, xAxis);

            yAxis = lineModelMesLog.getAxis(AxisType.Y);
            yAxis.setLabel("Ouverture");
            yAxis.setMin(MIN_Y_AXIS_LOG);
            yAxis.setMax(MAX_Y_AXIS_LOG);
        }
    }

    /**
     * initialization of the line chart associated to numeric measures
     *
     * @return a line chart model of the logic measures
     */
    private LineChartModel initCategoryModelMesLog() {
        LineChartModel model = new LineChartModel();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        ChartSeries logGraph = new ChartSeries();
        Calendar cal = Calendar.getInstance();

        int range = 0;
        if (this.mesuresLogiques.size() < RANGE_OF_MEASURE) {
            range = this.mesuresLogiques.size();
        } else {
            range = RANGE_OF_MEASURE;
        }
        Boolean oldState = this.mesuresLogiques.get(this.mesuresLogiques.size() - range).getEtat();
        for (int i = this.mesuresLogiques.size() - range; i < this.mesuresLogiques.size(); i++) {

            cal.setTime(this.mesuresLogiques.get(i).getTimeStamp());

            if ((oldState != this.mesuresLogiques.get(i).getEtat()) && oldState) {

                cal.add(Calendar.MILLISECOND, -1);
                logGraph.set(format.format(cal.getTime()), getValeur(true));
            } else if ((oldState != this.mesuresLogiques.get(i).getEtat()) && !oldState) {

                cal.add(Calendar.MILLISECOND, -1);
                logGraph.set(format.format(cal.getTime()), getValeur(false));
            }

            cal.add(Calendar.MILLISECOND, 1);
            logGraph.set(format.format(cal.getTime()), getValeur(this.mesuresLogiques.get(i).getEtat()));
            oldState = this.mesuresLogiques.get(i).getEtat();
        }

        xAxis.setMin(format.format(this.mesuresLogiques.get(this.mesuresLogiques.size() - range).getTimeStamp()));
        xAxis.setMax(format.format(this.mesuresLogiques.get(this.mesuresLogiques.size() - 1).getTimeStamp()));

        model.addSeries(logGraph);

        return model;
    }

    /**
     * transforms a boolean to an int between 0 and 1
     *
     * @param etat
     *            the boolean to cast
     * @return the int value
     */
    public int getValeur(Boolean etat) {
        return etat ? 1 : 0;
    }
    /********* Fin Ajout pour le Line model MesLog ****************/
    /******************** PrimeFaces methods ********************************/
}
