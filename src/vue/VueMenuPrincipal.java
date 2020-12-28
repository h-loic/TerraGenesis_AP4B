package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;


public class VueMenuPrincipal extends Scene {

    protected GridPane grillePrincipale;
    private controler.ControleurPrincipal controleur = null;
    private Button actionNaviguerStatistiques;
    private Button actionNaviguerRecherche;
    private Button actionNaviguerPopulation;
    private Button actionNaviguerPointsCulture;
    private Button actionNaviguerCarte;
    private Button actionNaviguerGouverneurs;
    private HBox hBoxLigne1;
    private HBox hBoxLigne2;
    public static final String STYLE_BOUTONS = "-fx-background-color: #25467F; -fx-text-fill: white; -fx-font-size: 15; -fx-font-weight: bold;-fx-min-width: 120px";


    public VueMenuPrincipal() {
        super(new GridPane(), 500,400);
        grillePrincipale = (GridPane) this.getRoot();
        this.actionNaviguerStatistiques = new Button("Statistiques");
        this.actionNaviguerRecherche = new Button("Recherche");
        this.actionNaviguerPopulation = new Button("Population");
        this.actionNaviguerPointsCulture = new Button("Culture");
        this.actionNaviguerCarte = new Button("Carte");
        this.actionNaviguerGouverneurs = new Button("Gouverneurs");
        hBoxLigne1 = new HBox();
        hBoxLigne2 = new HBox();
    }

    public void initialiserMenuPrincipal() {
        this.grillePrincipale.getChildren().clear();
        hBoxLigne1.getChildren().clear();
        hBoxLigne2.getChildren().clear();
        hBoxLigne1.getChildren().addAll(actionNaviguerStatistiques, actionNaviguerRecherche, actionNaviguerPopulation);
        hBoxLigne2.getChildren().addAll(actionNaviguerPointsCulture, actionNaviguerCarte, actionNaviguerGouverneurs);

        hBoxLigne1.setSpacing(30);
        hBoxLigne2.setSpacing(30);

        hBoxLigne1.setPadding(new Insets(15,0,15,0));
        hBoxLigne2.setPadding(new Insets(15,0,15,0));

        this.actionNaviguerStatistiques.setStyle(STYLE_BOUTONS);
        this.actionNaviguerRecherche.setStyle(STYLE_BOUTONS);
        this.actionNaviguerPopulation.setStyle(STYLE_BOUTONS);
        this.actionNaviguerPointsCulture.setStyle(STYLE_BOUTONS);
        this.actionNaviguerCarte.setStyle(STYLE_BOUTONS);
        this.actionNaviguerGouverneurs.setStyle(STYLE_BOUTONS);

        grillePrincipale.setAlignment(Pos.CENTER);
        grillePrincipale.add(hBoxLigne1, 0, 0);
        grillePrincipale.add(hBoxLigne2, 0, 1);
        grillePrincipale.setStyle("-fx-background-color: #7680AD;");
        /*this.grillePrincipale.add(this.actionNaviguerStatistiques, 0, 0);
        this.grillePrincipale.add(this.actionNaviguerRecherche, 1, 0);
        this.grillePrincipale.add(this.actionNaviguerPopulation, 2, 0);
        this.grillePrincipale.add(this.actionNaviguerPointsCulture, 0, 1);
        this.grillePrincipale.add(this.actionNaviguerCarte, 1, 1);
        this.grillePrincipale.add(this.actionNaviguerGouverneurs, 2, 1);*/

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
