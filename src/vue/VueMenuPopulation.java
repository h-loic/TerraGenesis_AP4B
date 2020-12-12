package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import modele.AvantPoste;

import java.util.ArrayList;


public class VueMenuPopulation extends Scene {

    protected GridPane grillePrincipale;
    protected GridPane grilleVilles;
    protected GridPane grilleAvPostes;

    private controler.ControleurPrincipal controleur = null;

    private Label labelVilles;
    private Label labelAvPostes;

    private Button boutonRetour;
    private Button btnAjouterVille;
    private Button btnAjouterAvPoste;

    public VueMenuPopulation() {
        super(new GridPane(), 400,400);
        grillePrincipale = (GridPane) this.getRoot();

        grilleAvPostes = new GridPane();
        grilleVilles = new GridPane();

        this.labelVilles = new Label("Villes");
        this.labelAvPostes = new Label("Avant-postes");

        this.boutonRetour = new Button("retour");
        this.btnAjouterVille = new Button("+ Ville");
        this.btnAjouterAvPoste = new Button("+ Avant-poste");
    }

    public void initialiserMenuPopulation(ArrayList<AvantPoste> listeAvantPostes) {
        int lignesAvPostes = 1;
        this.grillePrincipale.getChildren().clear();
        this.grilleVilles.getChildren().clear();
        this.grilleAvPostes.getChildren().clear();

        this.grilleVilles.add(this.labelVilles, 0, 0);
        this.grilleVilles.add(this.btnAjouterVille, 0, 1);

        this.grilleAvPostes.add(this.labelAvPostes, 0, 0);

        for (AvantPoste avantPoste : listeAvantPostes){
            Label labelNom = new Label(avantPoste.getNom());
            Button btnAfficher = new Button("afficher");

            btnAfficher.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    controleur.notifierNaviguerAfficherAvPoste(avantPoste);
                }
            });
            lignesAvPostes++;

            this.grilleAvPostes.add(labelNom, 0, lignesAvPostes);
            this.grilleAvPostes.add(btnAfficher, 1, lignesAvPostes);
        }
        btnAjouterAvPoste.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.notifierNaviguerAjouterAvPoste();
            }
        });
        this.grilleAvPostes.add(this.btnAjouterAvPoste, 0, lignesAvPostes+1);

        this.grillePrincipale.add(this.grilleVilles, 0, 0);
        this.grillePrincipale.add(this.grilleAvPostes, 0, 1);

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
