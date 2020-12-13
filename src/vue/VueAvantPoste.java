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


public class VueAvantPoste extends Scene {

    private GridPane grillePrincipale;
    private GridPane grilleAvantPoste;
    private GridPane grilleMines;
    private ScrollPane scrollPaneMines;

    private controler.ControleurPrincipal controleur = null;
    private Label labelNom;
    private Label labelCoordonnees;
    private Label labelBenefices;
    private int idAvantPoste;
    private Button btnRetour;
    private Button btnDetruire;
    private Button btnAjouterMine;

    public VueAvantPoste() {
        super(new GridPane(), 400,400);
        this.grillePrincipale = (GridPane) this.getRoot();
        this.grilleMines = new GridPane();
        this.grilleAvantPoste = new GridPane();
        this.scrollPaneMines = new ScrollPane();
        btnRetour = new Button("Retour");
        btnDetruire = new Button("Détruire");
        btnAjouterMine = new Button("+ Mine");
    }

    public void initialiserVueAvantPoste(AvantPoste avantPoste) {
        idAvantPoste = avantPoste.getId();

        this.grilleAvantPoste.getChildren().clear();
        this.grilleMines.getChildren().clear();
        this.grillePrincipale.getChildren().clear();

        int ligneMine=0;

        //affichage des listes et de leurs données
        for(Mine mine : avantPoste.getMines()){
            Label labelNomMine = new Label(mine.getNom()+" : ");
            Label labelBenefice = new Label(", benefice/min : "+Double.toString(mine.getBenefice()));
            Label labelRendement = new Label("kg/min "+Double.toString(mine.getRendement()));
            Label labelRessource = new Label(", ressource : " + mine.getRessource().getSymbole());

            Button btnAmeliorer = new Button("Améliorer");
            btnAmeliorer.setUserData(mine.getId());
            Button btnDetruire = new Button("Detruire");
            //btnAfficher.setUserData(mine.getId());

            GridPane grilleMine = new GridPane();
            grilleMine.add(labelNomMine, 0, 0);
            grilleMine.add(labelRendement, 1, 0);
            grilleMine.add(labelBenefice, 2, 0);
            grilleMine.add(labelRessource, 3, 0);
            grilleMine.add(btnAmeliorer, 1, 1);
            grilleMine.add(btnDetruire, 2, 1);

            if (mine.getNiveau()>=5){
                btnAmeliorer.setDisable(true);
            }

            btnAmeliorer.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    //controleur.notifierNaviguerAfficherAvPoste((int)((Button)event.getSource()).getUserData());
                    System.out.println("Amélioration Mine");
                    try {
                        controleur.notifierAmeliorerMine((int)btnAmeliorer.getUserData(), avantPoste.getId());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            });

            btnDetruire.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("Destruction Mine");
                    controleur.notifierDetruireMine(mine.getId());
                }
            });

            grilleMines.add(grilleMine, 0, ligneMine);
            ligneMine++;

        }

        this.labelNom = new Label(avantPoste.getNom());
        this.labelCoordonnees = new Label("("+Double.toString(avantPoste.getCoordonnee().getX())+", "+Double.toString(avantPoste.getCoordonnee().getY())+", "+Double.toString(avantPoste.getCoordonnee().getZ())+")");
        this.labelBenefices = new Label(", €/min : " + Double.toString(avantPoste.getBeneficesMines()));

        btnRetour.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.notifierNaviguerMenuPopulation();
            }
        });

        btnDetruire.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.notifierDetruireAvantPoste(idAvantPoste);
            }
        });

        btnAjouterMine.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Ajouter mine");
                controleur.notifierNaviguerAjouterMine(idAvantPoste);
            }
        });

        scrollPaneMines.setContent(grilleMines);
        scrollPaneMines.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPaneMines.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        grilleAvantPoste.add(labelNom,0,0);
        grilleAvantPoste.add(labelCoordonnees,1,0);
        grilleAvantPoste.add(labelBenefices,2,0);
        grilleAvantPoste.add(btnRetour,0,1);
        grilleAvantPoste.add(btnDetruire,1,1);
        grillePrincipale.add(grilleAvantPoste, 0, 0);
        grillePrincipale.add(scrollPaneMines, 0, 1);
        grillePrincipale.add(btnAjouterMine, 0, 2);
    }

    public void setControleur(controler.ControleurPrincipal controleur) {
        this.controleur = controleur;
    }
}
