package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import modele.AvantPoste;
import modele.Ville;

import java.util.ArrayList;


public class VueMenuPopulation extends Scene {

    public static final String STYLE_SCROLLPANE = "-fx-background-color:transparent;";
    public static final String STYLE_TITRE = " -fx-font-size: 18; -fx-font-weight: bold; -fx-padding: 15px";
    public static final String STYLE_ERREUR = "-fx-text-fill: red; -fx-font-size: 13; -fx-font-weight: bold";
    protected GridPane grillePrincipale;
    protected GridPane grilleVilles;
    protected GridPane grilleAvPostes;
    private ScrollPane scrollPaneAvPostes;
    private ScrollPane scrollPaneVilles;

    private controler.ControleurPrincipal controleur = null;

    private Label labelVilles;
    private Label labelAvPostes;
    private Label labelMessages;

    private Button btnRetour;
    private Button btnAjouterVille;
    private Button btnAjouterAvPoste;
    public static final String STYLE_BOUTONS = "-fx-background-color: #25467F; -fx-text-fill: white; -fx-font-size: 12; -fx-font-weight: bold;-fx-min-width: 80px";

    public VueMenuPopulation() {
        super(new GridPane(), 500,400);
        this.grillePrincipale = (GridPane) this.getRoot();

        this.grilleAvPostes = new GridPane();
        this.grilleVilles = new GridPane();
        this.scrollPaneAvPostes = new ScrollPane();
        this.scrollPaneVilles = new ScrollPane();

        this.labelVilles = new Label("Villes");
        this.labelVilles.setStyle(STYLE_TITRE);
        this.labelAvPostes = new Label("Avant-postes");
        this.labelAvPostes.setStyle(STYLE_TITRE);
        this.labelMessages = new Label("");
        this.labelMessages.setStyle(STYLE_ERREUR);

        this.btnRetour = new Button("retour");
        this.btnAjouterVille = new Button("+ Ville");
        this.btnAjouterAvPoste = new Button("+ Avant-poste");
    }

    public void initialiserMenuPopulation(ArrayList<Ville> listeVilles, ArrayList<AvantPoste> listeAvantPostes) {
        int lignesAvPostes = 1;
        int lignesVilles = 1;
        this.grillePrincipale.getChildren().clear();
        this.grilleVilles.getChildren().clear();
        this.grilleAvPostes.getChildren().clear();

        /* VILLES */
        for (Ville ville : listeVilles){
            Label labelNom = new Label(ville.getNom()+" : ");
            Label labelPopulation = new Label(" pop. "+ville.getPopulation().getValeurActuelle());

            Button btnAfficher = new Button("afficher");
            btnAfficher.setUserData(ville.getId());

            btnAfficher.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    controleur.notifierNaviguerAfficherVille((int)((Button)event.getSource()).getUserData());
                }
            });

            btnAfficher.setStyle(STYLE_BOUTONS);

            lignesVilles++;
            HBox ligne = new HBox();
            ligne.setSpacing(25);
            ligne.getChildren().addAll(labelNom, labelPopulation, btnAfficher);

            this.grilleVilles.add(ligne, 0, lignesVilles);
        }
        btnAjouterVille.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    labelMessages.setText("");
                    labelMessages.setVisible(false);
                    controleur.notifierNaviguerAjouterVille();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    labelMessages.setText(e.getMessage());
                    labelMessages.setVisible(true);
                }
            }
        });
        this.scrollPaneVilles.setContent(this.grilleVilles);
        this.scrollPaneVilles.setMinWidth(500);
        this.scrollPaneVilles.setStyle(STYLE_SCROLLPANE);
        this.scrollPaneVilles.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        this.scrollPaneVilles.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        this.grillePrincipale.add(this.labelVilles, 0, 0);
        this.grillePrincipale.add(this.scrollPaneVilles, 0, 1);
        this.grillePrincipale.add(this.btnAjouterVille, 0, 2);


        /* AVANT-POSTES */
        for (AvantPoste avantPoste : listeAvantPostes){
            Label labelNom = new Label(avantPoste.getNom());
            Label labelBenefices = new Label(" €/min : "+avantPoste.getBeneficesMines());
            Label labelNbMines = new Label(", "+avantPoste.getMines().size()+" mine(s)");

            Button btnAfficher = new Button("afficher");
            btnAfficher.setUserData(avantPoste.getId());

            btnAfficher.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    controleur.notifierNaviguerAfficherAvPoste((int)((Button)event.getSource()).getUserData());
                }
            });
            lignesAvPostes++;
            btnAfficher.setStyle(STYLE_BOUTONS);

            HBox ligne = new HBox();
            ligne.setSpacing(25);
            ligne.getChildren().addAll(labelNom, labelNbMines, btnAfficher);

            this.grilleAvPostes.add(ligne, 0, lignesAvPostes);


        }
        btnAjouterAvPoste.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    labelMessages.setText("");
                    labelMessages.setVisible(false);
                    controleur.notifierNaviguerAjouterAvPoste();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    labelMessages.setText(e.getMessage());
                    labelMessages.setVisible(true);
                }
            }
        });

        this.btnRetour.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.notifierNaviguerMenuPrincipal();
            }
        });

        this.scrollPaneAvPostes.setContent(this.grilleAvPostes);
        this.scrollPaneAvPostes.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        this.scrollPaneAvPostes.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        this.scrollPaneAvPostes.setStyle(STYLE_SCROLLPANE);

        this.btnAjouterAvPoste.setStyle(STYLE_BOUTONS);
        this.btnAjouterVille.setStyle(STYLE_BOUTONS);
        this.btnRetour.setStyle(STYLE_BOUTONS);

        this.grillePrincipale.add(this.labelAvPostes, 0, 3);
        this.grillePrincipale.add(this.scrollPaneAvPostes, 0, 4);
        this.grillePrincipale.add(this.btnAjouterAvPoste, 0, 5);

        this.grillePrincipale.add(this.labelMessages, 0, 6);
        this.grillePrincipale.add(this.btnRetour, 0, 7);
        this.labelMessages.setVisible(false);



    }

    public void setControleur(controler.ControleurPrincipal controleur) {
        this.controleur = controleur;
    }
}
