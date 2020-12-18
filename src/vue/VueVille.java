package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import modele.*;

import java.util.HashMap;


public class VueVille extends Scene {

    private GridPane grillePrincipale;
    private GridPane grilleVille;
    private GridPane grilleBatiments;
    private ScrollPane scrollPaneBatiments;

    private controler.ControleurPrincipal controleur = null;
    private Label labelNom;
    private Label labelCoordonnees;
    private Label labelPopulation;
    private Label labelHabitation;
    private Label labelMessages;
    private int idVille;
    private Button btnRetour;
    private Button btnDetruire;
    private Button btnAjouterBatiment;

    public VueVille() {
        super(new GridPane(), 400,400);
        this.grillePrincipale = (GridPane) this.getRoot();
        this.grilleBatiments = new GridPane();
        this.grilleVille = new GridPane();
        this.scrollPaneBatiments = new ScrollPane();
        btnRetour = new Button("Retour");
        btnDetruire = new Button("Détruire");
        btnAjouterBatiment = new Button("+ Batiment");
        labelMessages = new Label("");
    }

    public void initialiserVueVille(Ville ville) {
        idVille = ville.getId();

        this.grilleVille.getChildren().clear();
        this.grilleBatiments.getChildren().clear();
        this.grillePrincipale.getChildren().clear();

        int ligneBatiment=0;

        //affichage des listes et de leurs données
        /*for(Batiment batiment : ville.getBatiments()){
            Label labelNomBatiment = new Label(batiment.getNom()+" : ");
            Label labelBenefice = new Label(", benefice/min : "+Double.toString(batiment.getBenefice()));
            Label labelRendement = new Label("kg/min "+Double.toString(batiment.getRendement()));
            Label labelRessource = new Label(", ressource : " + batiment.getRessource().getSymbole());

            Button btnAmeliorer = new Button("Améliorer");
            btnAmeliorer.setUserData(batiment.getId());
            Button btnDetruire = new Button("Detruire");
            //btnAfficher.setUserData(batiment.getId());

            GridPane grilleBatiment = new GridPane();
            grilleBatiment.add(labelNomBatiment, 0, 0);
            grilleBatiment.add(labelRendement, 1, 0);
            grilleBatiment.add(labelBenefice, 2, 0);
            grilleBatiment.add(labelRessource, 3, 0);
            grilleBatiment.add(btnAmeliorer, 1, 1);
            grilleBatiment.add(btnDetruire, 2, 1);

            if (batiment.getNiveau()>=5){
                btnAmeliorer.setDisable(true);
            }

            btnAmeliorer.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    //controleur.notifierNaviguerAfficherAvPoste((int)((Button)event.getSource()).getUserData());
                    System.out.println("Amélioration Batiment");
                    try {
                        controleur.notifierAmeliorerBatiment((int)btnAmeliorer.getUserData(), ville.getId());
                        labelMessages.setVisible(false);
                        labelMessages.setText("");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        labelMessages.setText(e.getMessage());
                        labelMessages.setVisible(true);
                    }
                }
            });

            btnDetruire.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("Destruction Batiment");
                    controleur.notifierDetruireBatiment(batiment.getId());
                }
            });

            grilleBatiments.add(grilleBatiment, 0, ligneBatiment);
            ligneBatiment++;

        }
*/
        this.labelNom = new Label(ville.getNom());
        this.labelCoordonnees = new Label("("+ ville.getCoordonnee().getX()+", "+ville.getCoordonnee().getY()+", "+ville.getCoordonnee().getZ()+")");
        this.labelPopulation = new Label("pop. " + ville.getPopulation());
        this.labelHabitation = new Label("hab. " + ville.getHabitation());

        btnRetour.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.notifierNaviguerMenuPopulation();
            }
        });


        btnDetruire.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.notifierDetruireVille(idVille);
            }
        });
/*
        btnAjouterBatiment.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Ajouter batiment");
                try {
                    controleur.notifierNaviguerAjouterBatiment(idVille);
                    labelMessages.setVisible(false);
                    labelMessages.setText("");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    labelMessages.setText(e.getMessage());
                    labelMessages.setVisible(true);
                }
            }
        });
*/
        scrollPaneBatiments.setContent(grilleBatiments);
        scrollPaneBatiments.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPaneBatiments.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        grilleVille.add(labelNom,0,0);
        grilleVille.add(labelCoordonnees,1,0);
        grilleVille.add(labelPopulation,0,1);
        grilleVille.add(labelHabitation,1,1);
        grilleVille.add(btnRetour,0,2);
        grilleVille.add(btnDetruire,1,2);
        grillePrincipale.add(grilleVille, 0, 0);
        grillePrincipale.add(scrollPaneBatiments, 0, 1);
        grillePrincipale.add(btnAjouterBatiment, 0, 2);
        grillePrincipale.add(labelMessages, 0, 3);
        this.labelMessages.setVisible(false);
    }

    public void setControleur(controler.ControleurPrincipal controleur) {
        this.controleur = controleur;
    }
}
