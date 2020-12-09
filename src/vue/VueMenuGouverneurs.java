package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;


public class VueMenuGouverneurs extends Scene {

    protected GridPane grilleAvions;
    private controler.ControleurPrincipal controleur = null;
    private Label labelGouverneurs;
    private Button boutonRetour;

    public VueMenuGouverneurs() {
        super(new GridPane(), 400,400);
        grilleAvions = (GridPane) this.getRoot();
        this.labelGouverneurs = new Label("Gouverneurs");
        this.boutonRetour = new Button("retour");
    }

    public void initialiserMenuGouverneurs() {
        this.grilleAvions.getChildren().clear();
        this.grilleAvions.add(this.labelGouverneurs, 0, 0);
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
