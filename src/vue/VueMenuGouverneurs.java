package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import modele.Gouverneur;

import java.util.ArrayList;


public class VueMenuGouverneurs extends Scene {

    protected GridPane grillePrincipale;
    protected GridPane grilleGouverneur;

    private controler.ControleurPrincipal controleur = null;

    private Label labelGouverneurs;
    private Button boutonRetour;

    public VueMenuGouverneurs() {
        super(new GridPane(), 400,400);
        grillePrincipale = (GridPane) this.getRoot();

        grilleGouverneur = new GridPane();

        this.labelGouverneurs = new Label("Gouverneurs");
        this.boutonRetour = new Button("retour");
    }

    public void initialiserMenuGouverneurs(ArrayList<Gouverneur> listeGouverneur) {
        int lignesGouverneur = 1;
        this.grillePrincipale.getChildren().clear();
        for (Gouverneur gouverneur : listeGouverneur){
            Label labelNom = new Label(gouverneur.getNom());
            Button btnAfficher = new Button("afficher");

            btnAfficher.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    controleur.notifierNaviguerAffichergouverneur(gouverneur);
                }
            });
            lignesGouverneur+=2;

            this.grilleGouverneur.add(labelNom, 0, lignesGouverneur);
            this.grilleGouverneur.add(btnAfficher, 4, lignesGouverneur);
        }
        lignesGouverneur+=2;
        this.grillePrincipale.add(this.labelGouverneurs, 0, 0);
        this.grillePrincipale.add(this.boutonRetour, 1, lignesGouverneur);
        this.grillePrincipale.add(this.grilleGouverneur, 0, 1);

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
