package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
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

        this.sliderEau = new Slider();
        this.sliderOxygene = new Slider();
        this.sliderPression = new Slider();
        this.sliderTemperature = new Slider();

        this.boutonRetour = new Button("Retour");
    }

    public void initialiserMenuStatistiques() {
        this.grillePrincipale.getChildren().clear();

        this.sliderPression.setMin(10000);
        this.sliderPression.setMax(190000);
        this.sliderPression.setValue(10000);
        this.sliderPression.setValueChanging(false);

        this.sliderOxygene.setMin(100000);
        this.sliderOxygene.setMax(320000);
        this.sliderOxygene.setValue(100000);
        this.sliderOxygene.setDisable(true);

        this.sliderEau.setMin(150000);
        this.sliderEau.setMax(1050000);
        this.sliderEau.setValue(150000);

        this.sliderTemperature.setMin(200000);
        this.sliderTemperature.setMax(374000);
        this.sliderTemperature.setValue(200000);

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
        this.grillePrincipale.add(this.boutonRetour, 0, 4);
    }

    public void setControleur(controler.ControleurPrincipal controleur) {
        this.controleur = controleur;
    }
}
