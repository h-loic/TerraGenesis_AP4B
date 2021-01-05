package vue;

import controler.ControleurPrincipal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import static modele.TypeDonnee.*;


/**
 * <b>
 *     Vue affichant les differentes donnees de la planete a l'aide de sliders et de labels
 * </p>
 *
 * @see modele.Planete
 * @see modele.Donnee
 * @author Zapolatero - lpascuzzi
 * */

public class VueMenuStatistiques extends Scene {

    private NumberFormat formatNombre;

    /**
     *  Constante definissant le style des titres de la vue
     *
     * @see VueMenuStatistiques#VueMenuStatistiques()
     * @see VueMenuStatistiques#initialiserMenuStatistiques(double, double, double, double, double, double)
     */
    public static final String STYLE_TITRE = " -fx-font-size: 18; -fx-font-weight: bold; -fx-padding: 15px";

    /**
     *  Constante definissant le style des boutons de la vue
     *
     * @see VueMenuStatistiques#VueMenuStatistiques()
     * @see VueMenuStatistiques#initialiserMenuStatistiques(double, double, double, double, double, double)
     */
    public static final String STYLE_BOUTONS = "-fx-background-color: #25467F; -fx-text-fill: white; -fx-font-size: 12; -fx-font-weight: bold;-fx-min-width: 80px";

    /**
     * La grille sur laquelle sont ajoutes les differents elements de la vue
     *
     * @see VueMenuStatistiques#VueMenuStatistiques()
     * @see VueMenuStatistiques#initialiserMenuStatistiques(double, double, double, double, double, double)
     */
    protected GridPane grillePrincipale;

    /**
     * Le controleur de l'application, permet a la vue d'interagir avec les modeles ou avec le navigateur des vues
     *
     * @see VueMenuStatistiques#setControleur(ControleurPrincipal)
     * @see controler.ControleurPrincipal
     */
    private controler.ControleurPrincipal controleur = null;

    /**
     * Labels affichant les noms des donnees affichees
     */
    private Label labelTemperature;
    private Label labelPression;
    private Label labelOxygene;
    private Label labelEau;
    private Label labelPopulation;
    private Label labelFinances;

    /**
     * Slider Affichant la temperature de la planete
     *
     * @see VueMenuStatistiques#VueMenuStatistiques()
     * @see VueMenuStatistiques#initialiserMenuStatistiques(double, double, double, double, double, double)
     */
    private Slider sliderTemperature;

    /**
     * Slider Affichant la pression de la planete
     *
     * @see VueMenuStatistiques#VueMenuStatistiques()
     * @see VueMenuStatistiques#initialiserMenuStatistiques(double, double, double, double, double, double)
     */
    private Slider sliderPression;

    /**
     * Slider Affichant l'oxygene de la planete
     *
     * @see VueMenuStatistiques#VueMenuStatistiques()
     * @see VueMenuStatistiques#initialiserMenuStatistiques(double, double, double, double, double, double)
     */
    private Slider sliderOxygene;

    /**
     * Slider Affichant la eau de la planete
     *
     * @see VueMenuStatistiques#VueMenuStatistiques()
     * @see VueMenuStatistiques#initialiserMenuStatistiques(double, double, double, double, double, double)
     */
    private Slider sliderEau;

    /**
     * Label affichant les finances de la planete
     *
     * @see VueMenuStatistiques#VueMenuStatistiques()
     * @see VueMenuStatistiques#initialiserMenuStatistiques(double, double, double, double, double, double)
     */
    private Label labelFinancesVal;

    /**
     * Label affichant la température de la planete
     *
     * @see VueMenuStatistiques#VueMenuStatistiques()
     * @see VueMenuStatistiques#initialiserMenuStatistiques(double, double, double, double, double, double)
     */
    private Label labelTemperatureVal;

    /**
     * Label affichant la pression de la planete
     *
     * @see VueMenuStatistiques#VueMenuStatistiques()
     * @see VueMenuStatistiques#initialiserMenuStatistiques(double, double, double, double, double, double)
     */
    private Label labelPressionVal;

    /**
     * Label affichant l'eau de la planete
     *
     * @see VueMenuStatistiques#VueMenuStatistiques()
     * @see VueMenuStatistiques#initialiserMenuStatistiques(double, double, double, double, double, double)
     */
    private Label labelEauVal;

    /**
     * Label affichant l'oxygène de la planete
     *
     * @see VueMenuStatistiques#VueMenuStatistiques()
     * @see VueMenuStatistiques#initialiserMenuStatistiques(double, double, double, double, double, double)
     */
    private Label labelOxygeneVal;

    /**
     * Label affichant la population de la planete
     *
     * @see VueMenuStatistiques#VueMenuStatistiques()
     * @see VueMenuStatistiques#initialiserMenuStatistiques(double, double, double, double, double, double)
     */
    private Label labelPopVal;

    /**
     * Bouton permettant de retourner au menu principal
     *
     * @see VueMenuStatistiques#VueMenuStatistiques()
     * @see VueMenuStatistiques#initialiserMenuStatistiques(double, double, double, double, double, double)
     */
    private Button btnRetour;

    /**
     * Bouton permettant d'actualiser les donnees affichees'
     *
     * @see VueMenuStatistiques#VueMenuStatistiques()
     * @see VueMenuStatistiques#initialiserMenuStatistiques(double, double, double, double, double, double)
     */
    private Button btnActualiser;

    /**
     *  Constructeur de VueMenuStatistiques
     *  Cree les differents labels, sliders et boutons de la vue
     *
     * @see VueMenuStatistiques#grillePrincipale
     * @see VueMenuStatistiques#labelTemperature
     * @see VueMenuStatistiques#labelPression
     * @see VueMenuStatistiques#labelOxygene
     * @see VueMenuStatistiques#labelEau
     * @see VueMenuStatistiques#labelPopulation
     * @see VueMenuStatistiques#labelFinances
     * @see VueMenuStatistiques#labelFinancesVal
     * @see VueMenuStatistiques#labelPopVal
     * @see VueMenuStatistiques#btnRetour
     * @see VueMenuStatistiques#btnActualiser
     */
    public VueMenuStatistiques() {
        super(new GridPane(), 650,400);

        grillePrincipale = (GridPane) this.getRoot();
        this.labelTemperature = new Label("Temperature : ");
        this.labelPression = new Label("Pression : ");
        this.labelOxygene = new Label("Oxygene : ");
        this.labelEau = new Label("Eau : ");
        this.labelPopulation = new Label("Population : ");
        this.labelFinances = new Label("Finances : ");

        this.labelFinancesVal = new Label("n/a");
        this.labelPopVal = new Label("n/a");

        this.sliderEau = new Slider();
        this.sliderOxygene = new Slider();
        this.sliderPression = new Slider();
        this.sliderTemperature = new Slider();

        this.btnRetour = new Button("Retour");
        this.btnActualiser = new Button("Actualiser");

        this.formatNombre = new DecimalFormat("0.##");
    }

    //TODO : creer fct actualisation controleur
    public void majStatistiques(double finances) {
        this.labelFinancesVal.setText(Double.toString(finances));
    }

    /**
     *  Initialise la vue
     *      Affichage des differentes donnees de la planete sur les sliders et les labels
     * @param pression pression de la planete
     * @param oxygene pression de la planete
     * @param eau nivrau d'eau de la planete
     * @param temperature pression de la planete
     * @param population pression de la planete
     * @param finances pression de la planete
     *
     * @see VueMenuStatistiques#grillePrincipale
     * @see VueMenuStatistiques#labelTemperature
     * @see VueMenuStatistiques#labelPression
     * @see VueMenuStatistiques#labelOxygene
     * @see VueMenuStatistiques#labelEau
     * @see VueMenuStatistiques#labelPopulation
     * @see VueMenuStatistiques#labelFinances
     * @see VueMenuStatistiques#labelFinancesVal
     * @see VueMenuStatistiques#labelPopVal
     * @see VueMenuStatistiques#btnRetour
     * @see VueMenuStatistiques#btnActualiser
     */
    public void initialiserMenuStatistiques(double pression, double oxygene, double eau, double temperature, double population, double finances) {
        this.grillePrincipale.getChildren().clear();

        //affichage de la pression
        this.sliderPression.setMin(0);
        this.sliderPression.setMax(500);
        this.sliderPression.setValue(pression/100);
        this.sliderPression.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                sliderPression.setValue(pression/100);
            }
        });
        this.sliderPression.setShowTickLabels(true);
        this.sliderPression.setShowTickMarks(true);
        this.sliderPression.setMinWidth(400);
        this.labelPressionVal = new Label(formatNombre.format(pression) + " " + PRESSION.getUnite());

        //affichage de l'oxygene
        this.sliderOxygene.setMin(0);
        this.sliderOxygene.setMax(500);
        this.sliderOxygene.setValue(oxygene/100);
        this.sliderOxygene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                sliderOxygene.setValue(oxygene/100);
            }
        });
        this.sliderOxygene.setShowTickLabels(true);
        this.sliderOxygene.setShowTickMarks(true);
        this.sliderOxygene.setMinWidth(250);
            this.labelOxygeneVal = new Label(formatNombre.format(oxygene)+ " " + OXYGENE.getUnite());

        //affichage du niveau d'eau
        this.sliderEau.setMin(0);
        this.sliderEau.setMax(500);
        this.sliderEau.setValue(eau/100);
        this.sliderEau.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                sliderEau.setValue(eau/100);
            }
        });
        this.sliderEau.setShowTickLabels(true);
        this.sliderEau.setShowTickMarks(true);
        this.sliderEau.setMinWidth(250);
        this.labelEauVal = new Label(formatNombre.format(eau) + " " + EAU.getUnite());

        //affichage de la temperature
        this.sliderTemperature.setMin(0);
        this.sliderTemperature.setMax(500);
        this.sliderTemperature.setValue(temperature/100);
        this.sliderTemperature.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                sliderTemperature.setValue(temperature/100);
            }
        });
        this.sliderTemperature.setShowTickLabels(true);
        this.sliderTemperature.setShowTickMarks(true);
        this.sliderTemperature.setMinWidth(250);
        this.labelTemperatureVal = new Label(formatNombre.format(temperature) + " " + TEMPERATURE.getUnite());

        //affichage de la population
        this.labelPopVal.setText(formatNombre.format(population));
        System.out.println("POPULATION : "+population);

        //affichage des finances
        this.labelFinancesVal.setText(formatNombre.format(finances) + " " + FINANCES.getUnite());
        this.labelFinancesVal.setMinWidth(100);


        this.labelTemperature.setStyle(STYLE_TITRE);
        this.labelPression.setStyle(STYLE_TITRE);
        this.labelOxygene.setStyle(STYLE_TITRE);
        this.labelEau.setStyle(STYLE_TITRE);
        this.labelPopulation.setStyle(STYLE_TITRE);
        this.labelFinances.setStyle(STYLE_TITRE);

        //permet de retourner au menu principal
        this.btnRetour.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.notifierNaviguerMenuPrincipal();
            }
        });
        this.btnRetour.setStyle(STYLE_BOUTONS);

        //permet d'actualiser les donnees affichees
        btnActualiser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.majDonneesVues();
            }
        });
        this.btnActualiser.setStyle(STYLE_BOUTONS);

        this.grillePrincipale.add(this.labelTemperature, 0, 0);
        this.grillePrincipale.add(this.sliderTemperature, 1, 0);
        this.grillePrincipale.add(this.labelTemperatureVal, 2, 0);

        this.grillePrincipale.add(this.labelEau, 0, 1);
        this.grillePrincipale.add(this.sliderEau, 1, 1);
        this.grillePrincipale.add(this.labelEauVal, 2, 1);

        this.grillePrincipale.add(this.labelOxygene, 0, 2);
        this.grillePrincipale.add(this.sliderOxygene, 1, 2);
        this.grillePrincipale.add(this.labelOxygeneVal, 2, 2);

        this.grillePrincipale.add(this.labelPression, 0, 3);
        this.grillePrincipale.add(this.sliderPression, 1, 3);
        this.grillePrincipale.add(this.labelPressionVal, 2, 3);

        this.grillePrincipale.add(this.labelPopulation, 0, 4);
        this.grillePrincipale.add(this.labelPopVal, 1, 4);

        this.grillePrincipale.add(this.labelFinances, 0, 5);
        this.grillePrincipale.add(this.labelFinancesVal, 1, 5);
        this.grillePrincipale.add(this.btnRetour, 0, 6);
        this.grillePrincipale.add(this.btnActualiser, 1, 6);
    }

    /**
     *  Permet d'affecter un controleur a la vue
     *
     * @param controleur
     *
     * @see ControleurPrincipal
     * @see VueMenuStatistiques#controleur
     */
    public void setControleur(controler.ControleurPrincipal controleur) {
        this.controleur = controleur;
    }
}
