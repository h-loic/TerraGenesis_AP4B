package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import modele.AvantPoste;
import modele.Ville;

import java.util.ArrayList;


public class VueMenuPopulation extends Scene {

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

    public VueMenuPopulation() {
        super(new GridPane(), 400,400);
        this.grillePrincipale = (GridPane) this.getRoot();

        this.grilleAvPostes = new GridPane();
        this.grilleVilles = new GridPane();
        this.scrollPaneAvPostes = new ScrollPane();
        this.scrollPaneVilles = new ScrollPane();

        this.labelVilles = new Label("Villes");
        this.labelAvPostes = new Label("Avant-postes");
        this.labelMessages = new Label("");

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
            Label labelNom = new Label(ville.getNom());
            Label labelPopulation = new Label(" pop. "+ville.getPopulation());

            Button btnAfficher = new Button("afficher");
            btnAfficher.setUserData(ville.getId());

            btnAfficher.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    controleur.notifierNaviguerAfficherAvPoste((int)((Button)event.getSource()).getUserData());
                }
            });
            lignesVilles++;

            this.grilleVilles.add(labelNom, 0, lignesVilles);
            this.grilleVilles.add(labelPopulation, 1, lignesVilles);
            this.grilleVilles.add(btnAfficher, 3, lignesVilles);
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

            this.grilleAvPostes.add(labelNom, 0, lignesAvPostes);
            this.grilleAvPostes.add(labelBenefices, 1, lignesAvPostes);
            this.grilleAvPostes.add(labelNbMines, 2, lignesAvPostes);
            this.grilleAvPostes.add(btnAfficher, 3, lignesAvPostes);
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
