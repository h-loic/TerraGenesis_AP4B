package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;


public class VueMenuCulture extends Scene {

    private GridPane grillePrincipale;
    private controler.ControleurPrincipal controleur = null;
    private Label labelCulture;
    private Button boutonRetour;

    public VueMenuCulture() {
        super(new GridPane(), 400,400);
        grillePrincipale = (GridPane) this.getRoot();
        this.labelCulture = new Label("Culture");
        this.boutonRetour = new Button("retour");
    }

    public void initialiserMenuCulture() {
        this.grillePrincipale.getChildren().clear();
        this.grillePrincipale.add(this.labelCulture, 0, 0);
        this.grillePrincipale.add(this.boutonRetour, 1, 0);
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
