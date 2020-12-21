package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;


public class VueMenuSatellites extends Scene {

    protected GridPane grillePrincipale;
    private controler.ControleurPrincipal controleur = null;
    private Label labelSatellites;
    private Button boutonRetour;
    private Canvas canvas;

    public VueMenuSatellites() {
        super(new GridPane(), 400,400);
        grillePrincipale = (GridPane) this.getRoot();
        this.labelSatellites = new Label("Satellites");
        this.boutonRetour = new Button("retour");
        canvas = new Canvas(500,250);
    }

    public void initialiserMenuSatellites(Canvas canvasCarte) {
        this.grillePrincipale.getChildren().clear();
        this.canvas = canvasCarte;
        this.grillePrincipale.add(this.labelSatellites, 0, 0);
        this.grillePrincipale.add(this.boutonRetour, 1, 0);
        this.grillePrincipale.add(this.canvas, 0, 1);

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
