package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;


public class VueMenuRecherche extends Scene {

    protected GridPane grillePrincipale;
    private controler.ControleurPrincipal controleur = null;
    private Label labelRecherche;
    private Button boutonRetour;

    public VueMenuRecherche() {
        super(new GridPane(), 400,400);
        grillePrincipale = (GridPane) this.getRoot();
        this.labelRecherche = new Label("Recherche");
        this.boutonRetour = new Button("retour");
    }

    public void initialiserMenuRecherche() {
        this.grillePrincipale.getChildren().clear();
        this.grillePrincipale.add(this.labelRecherche, 0, 0);
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
