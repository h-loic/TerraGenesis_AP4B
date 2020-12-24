package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;


public class VueMenuPrincipal extends Scene {

    protected GridPane grillePrincipale;
    private controler.ControleurPrincipal controleur = null;
    private Button actionNaviguerStatistiques;
    private Button actionNaviguerRecherche;
    private Button actionNaviguerPopulation;
    private Button actionNaviguerPointsCulture;
    private Button actionNaviguerCarte;
    private Button actionNaviguerGouverneurs;

    public VueMenuPrincipal() {
        super(new GridPane(), 400,400);
        grillePrincipale = (GridPane) this.getRoot();
        this.actionNaviguerStatistiques = new Button("Statistiques");
        this.actionNaviguerRecherche = new Button("Recherche");
        this.actionNaviguerPopulation = new Button("Population");
        this.actionNaviguerPointsCulture = new Button("Culture");
        this.actionNaviguerCarte = new Button("Carte");
        this.actionNaviguerGouverneurs = new Button("Gouverneurs");
    }

    public void initialiserMenuPrincipal() {
        this.grillePrincipale.getChildren().clear();
        this.grillePrincipale.add(this.actionNaviguerStatistiques, 0, 0);
        this.grillePrincipale.add(this.actionNaviguerRecherche, 1, 0);
        this.grillePrincipale.add(this.actionNaviguerPopulation, 2, 0);
        this.grillePrincipale.add(this.actionNaviguerPointsCulture, 0, 1);
        this.grillePrincipale.add(this.actionNaviguerCarte, 1, 1);
        this.grillePrincipale.add(this.actionNaviguerGouverneurs, 2, 1);

        this.actionNaviguerStatistiques.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.notifierNaviguerMenuStatistiques();
            }
        });
        this.actionNaviguerStatistiques.setMinWidth(85);

        this.actionNaviguerRecherche.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.notifierNaviguerMenuRecherche();
            }
        });
        this.actionNaviguerRecherche.setMinWidth(85);

        this.actionNaviguerPopulation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.notifierNaviguerMenuPopulation();
            }
        });
        this.actionNaviguerPopulation.setMinWidth(85);

        this.actionNaviguerPointsCulture.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.notifierNaviguerMenuCulture();
            }
        });
        this.actionNaviguerPointsCulture.setMinWidth(85);

        this.actionNaviguerCarte.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.notifierNaviguerMenuCarte();
            }
        });
        this.actionNaviguerCarte.setMinWidth(85);

        this.actionNaviguerGouverneurs.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.notifierNaviguerMenuGouverneurs();
                System.out.println(actionNaviguerGouverneurs.getWidth());
            }
        });
        this.actionNaviguerGouverneurs.setMinWidth(85);
    }

    public void setControleur(controler.ControleurPrincipal controleur) {
        this.controleur = controleur;
    }
}
