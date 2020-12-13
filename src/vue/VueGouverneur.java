package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import modele.AvantPoste;
import modele.Gouverneur;

public class VueGouverneur extends Scene{

    protected GridPane grillePrincipale;
    private controler.ControleurPrincipal controleur = null;
    private Label labelNom;
    private Button btnRetour;

    public VueGouverneur() {
        super(new GridPane(), 400,400);
        grillePrincipale = (GridPane) this.getRoot();
        btnRetour = new Button("Retour");
    }

    public void initialiserVueGouverneur(Gouverneur gouverneur) {
        grillePrincipale.getChildren().clear();
        labelNom = new Label(gouverneur.getNom());
        btnRetour.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.notifierNaviguerMenuGouverneurs();
            }
        });

        grillePrincipale.add(labelNom,0,0);
        grillePrincipale.add(btnRetour,0,1);
    }

    public void setControleur(controler.ControleurPrincipal controleur) {
        this.controleur = controleur;
    }
}
