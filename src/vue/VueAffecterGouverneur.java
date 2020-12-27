package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import modele.Donnee;
import modele.Gouverneur;

public class VueAffecterGouverneur extends Scene{

    protected GridPane grillePrincipale;
    private controler.ControleurPrincipal controleur = null;
    private Button btnRetour;

    public VueAffecterGouverneur() {
        super(new GridPane(), 400,400);
        grillePrincipale = (GridPane) this.getRoot();
        btnRetour = new Button("Retour");
    }

    public void initialiserVueAffecterGouverneur(Gouverneur gouverneur) {
        grillePrincipale.getChildren().clear();
        btnRetour.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.notifierNaviguerAfficherGouverneur(gouverneur);
            }
        });
        Label labelTitre = new Label("choisissez la ville dans laquelle affecter " + gouverneur.getNom());
        grillePrincipale.add(labelTitre,1,0);
        grillePrincipale.add(btnRetour,1,1);
    }

    public void setControleur(controler.ControleurPrincipal controleur) {
        this.controleur = controleur;
    }
}
