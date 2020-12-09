package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;


public class VueMenuStatistiques extends Scene {

    protected GridPane grilleAvions;
    private controler.ControleurPrincipal controleur = null;
    private Label labelTemperature;
    private Button boutonRetour;

    public VueMenuStatistiques() {
        super(new GridPane(), 400,400);
        grilleAvions = (GridPane) this.getRoot();
        this.labelTemperature = new Label("Température");
        this.boutonRetour = new Button("retour");
    }

    public void initialiserMenuStatistiques() {
        this.grilleAvions.getChildren().clear();
        this.grilleAvions.add(this.labelTemperature, 0, 0);
        this.grilleAvions.add(this.boutonRetour, 1, 0);
        this.boutonRetour.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.notifierNaviguerMenuPrincipal();
            }
        });
    }

    public void setControleur(controler.ControleurPrincipal controleur) {
        this.controleur = controleur;
    }
}
