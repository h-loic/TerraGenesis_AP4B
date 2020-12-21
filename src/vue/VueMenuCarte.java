package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;


public class VueMenuCarte extends Scene {

    protected GridPane grillePrincipale;
    private controler.ControleurPrincipal controleur = null;
    private Label labelCarte;
    private Button boutonRetour;
    private Canvas canvas;

    public VueMenuCarte() {
        super(new GridPane(), 400,400);
        grillePrincipale = (GridPane) this.getRoot();
        this.labelCarte = new Label("Carte");
        this.boutonRetour = new Button("retour");
        canvas = new Canvas(500,250);
    }

    public void initialiserMenuCarte(Canvas canvasCarte) {
        this.grillePrincipale.getChildren().clear();
        this.canvas = canvasCarte;
        this.grillePrincipale.add(this.labelCarte, 0, 0);
        this.grillePrincipale.add(this.canvas, 0, 1);
        this.grillePrincipale.add(this.boutonRetour, 0, 2);

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
