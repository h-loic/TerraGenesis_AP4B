package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;


public class VueMenuSatellites extends Scene {

    protected GridPane grilleAvions;
    private controler.ControleurPrincipal controleur = null;
    private Label labelSatellites;
    private Button boutonRetour;

    public VueMenuSatellites() {
        super(new GridPane(), 400,400);
        grilleAvions = (GridPane) this.getRoot();
        this.labelSatellites = new Label("Satellites");
        this.boutonRetour = new Button("retour");
    }

    public void initialiserMenuSatellites() {
        this.grilleAvions.getChildren().clear();
        this.grilleAvions.add(this.labelSatellites, 0, 0);
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
