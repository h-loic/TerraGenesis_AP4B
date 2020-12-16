package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;


public class VueMenuStatistiques extends Scene {

    protected GridPane grillePrincipale;
    private controler.ControleurPrincipal controleur = null;

    private Label labelTemperature;
    private Label labelPression;
    private Label labelOxygene;
    private Label labelEau;
    private Label labelPopulation;
    private Label labelFinances;

    private Slider sliderTemperature;
    private Slider sliderPression;
    private Slider sliderOxygene;
    private Slider sliderEau;


    private Label labelFinancesVal;
    private Label labelPopVal;

    private Button boutonRetour;

    public VueMenuStatistiques() {
        super(new GridPane(), 400,400);

        grillePrincipale = (GridPane) this.getRoot();
        this.labelTemperature = new Label("Température : ");
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

        this.boutonRetour = new Button("Retour");
    }

    public void initialiserMenuStatistiques(double pression, double oxygene, double eau, double temperature, double population, double finances) {
        this.grillePrincipale.getChildren().clear();

        this.sliderPression.setMin(10);
        this.sliderPression.setMax(190);
        this.sliderPression.setValue(pression);
        this.sliderPression.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                sliderPression.setValue(pression);
            }
        });
        this.sliderPression.setShowTickLabels(true);

        this.sliderOxygene.setMin(100);
        this.sliderOxygene.setMax(320);
        this.sliderOxygene.setValue(oxygene);
        this.sliderOxygene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                sliderOxygene.setValue(oxygene);
            }
        });
        this.sliderOxygene.setShowTickLabels(true);

        this.sliderEau.setMin(150);
        this.sliderEau.setMax(1050);
        this.sliderEau.setValue(eau);
        this.sliderEau.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                sliderEau.setValue(eau);
            }
        });
        this.sliderEau.setShowTickLabels(true);

        this.sliderTemperature.setMin(200);
        this.sliderTemperature.setMax(374);
        this.sliderTemperature.setValue(temperature);
        this.sliderTemperature.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                sliderTemperature.setValue(temperature);
            }
        });
        this.sliderTemperature.setShowTickLabels(true);

        this.labelPopVal.setText(String.valueOf(population));
        this.labelFinancesVal.setText(String.valueOf(finances));

        this.boutonRetour.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.notifierNaviguerMenuPrincipal();
            }
        });


        this.grillePrincipale.add(this.labelTemperature, 0, 0);
        this.grillePrincipale.add(this.sliderTemperature, 1, 0);
        this.grillePrincipale.add(this.labelEau, 0, 1);
        this.grillePrincipale.add(this.sliderEau, 1, 1);
        this.grillePrincipale.add(this.labelOxygene, 0, 2);
        this.grillePrincipale.add(this.sliderOxygene, 1, 2);
        this.grillePrincipale.add(this.labelPression, 0, 3);
        this.grillePrincipale.add(this.sliderPression, 1, 3);
        this.grillePrincipale.add(this.labelPopulation, 0, 4);
        this.grillePrincipale.add(this.labelPopVal, 1, 4);
        this.grillePrincipale.add(this.labelFinances, 0, 5);
        this.grillePrincipale.add(this.labelFinancesVal, 1, 5);
        this.grillePrincipale.add(this.boutonRetour, 0, 6);
    }

    public void setControleur(controler.ControleurPrincipal controleur) {
        this.controleur = controleur;
    }
}
