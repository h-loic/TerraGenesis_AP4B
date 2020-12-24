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
import java.util.Map;


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
    private Label labelPlace;
    private Label labelGouverneur;
    private Label labelEffetsGouverneur;
    private Label labelMessages;
    private int idVille;

    private Button btnRetour;
    private Button btnDetruire;
    private Button btnAjouterBatiment;
    private Button btnNaviguerGouverneurs;
    private Button btnNaviguerGouverneurAssigne;
    private Button btnRevoquerGouverneur;

    public VueVille() {
        super(new GridPane(), 400,400);
        this.grillePrincipale = (GridPane) this.getRoot();
        this.grilleBatiments = new GridPane();
        this.grilleVille = new GridPane();
        this.scrollPaneBatiments = new ScrollPane();
        btnRetour = new Button("Retour");
        btnDetruire = new Button("Détruire");
        btnAjouterBatiment = new Button("+ Batiment");
        btnNaviguerGouverneurs = new Button("Afficher liste gouverneurs");
        btnNaviguerGouverneurAssigne = new Button("Afficher");
        btnRevoquerGouverneur= new Button("Révoquer");
        labelMessages = new Label("");
    }

    public void initialiserVueVille(Ville ville) {
        idVille = ville.getId();

        this.grilleVille.getChildren().clear();
        this.grilleBatiments.getChildren().clear();
        this.grillePrincipale.getChildren().clear();

        this.btnAjouterBatiment.setDisable(false);

        int ligneBatiment=0;

        //affichage des listes et de leurs données
        for(Batiment batiment : ville.getBatiments()){
            Label labelNomBatiment = new Label(batiment.getTypeBatiment().getNom()+" : niv." + batiment.getNiveau() + " état." + (batiment.estDesactive() ? "Désactivé" : "Activé"));
            Label labelEffet = new Label("effets :");
            TypeDonnee typeDonnee;
            double valeur;
            for (Map.Entry effet : batiment.getEffets().entrySet()) {
                typeDonnee = (TypeDonnee) effet.getKey();
                valeur = (double) effet.getValue();
                labelEffet.setText(labelEffet.getText() + " | " + typeDonnee.name() + ": " + valeur);
            }

            Button btnAmeliorer = new Button("Améliorer");
            btnAmeliorer.setUserData(batiment.getId());
            Button btnActiverDesactiver = new Button(batiment.estDesactive() ? "Activer" : "Désactiver");
            Button btnDetruireBatiment = new Button("Detruire");

            GridPane grilleBatiment = new GridPane();
            grilleBatiment.add(labelNomBatiment, 0, 0);
            grilleBatiment.add(labelEffet, 0, 1);
            grilleBatiment.add(btnAmeliorer, 0, 2);
            grilleBatiment.add(btnActiverDesactiver, 1, 2);
            grilleBatiment.add(btnDetruireBatiment, 2, 2);

            if (batiment.getNiveau() >= batiment.getTypeBatiment().getNiveauMax()){
                btnAmeliorer.setDisable(true);
            }

            btnAmeliorer.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    //controleur.notifierNaviguerAfficherAvPoste((int)((Button)event.getSource()).getUserData());
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

            btnActiverDesactiver.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    controleur.notifierActiverDesactiverBatiment(batiment.getId());
                }
            });

            btnDetruireBatiment.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    controleur.notifierDetruireBatiment(batiment.getId());
                }
            });

            grilleBatiments.add(grilleBatiment, 0, ligneBatiment);
            ligneBatiment++;

        }

        this.labelNom = new Label(ville.getNom());
        this.labelCoordonnees = new Label("("+ ville.getCoordonnee().getX()+", "+ville.getCoordonnee().getY()+", "+ville.getCoordonnee().getZ()+")");
        this.labelPopulation = new Label("pop. " + ville.getPopulation());
        this.labelHabitation = new Label("hab. " + ville.getHabitation());
        this.labelPlace = new Label("Nombre de places batiments restantes : " + (ville.getNombrePlaceBatiment() - ville.getBatiments().size()) );
        this.labelGouverneur = new Label("Gouveneur de la ville : " + (ville.getGouverneur() != null  ? ville.getGouverneur().getNom() : "aucun" ));
        this.labelEffetsGouverneur = new Label("");
        if (ville.getGouverneur() != null) {
            labelEffetsGouverneur.setText("Effets du gouverneur :");
            for (Donnee donnee : ville.getGouverneur().getEffets().keySet()) {
                labelEffetsGouverneur.setText(labelEffetsGouverneur.getText() + "\n |> " +donnee.getTypeDonnee().name() + " : " + ville.getGouverneur().getEffets().get(donnee));
            }
            btnNaviguerGouverneurAssigne.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    controleur.notifierNaviguerAfficherGouverneur(ville.getGouverneur());
                }
            });
            btnRevoquerGouverneur.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    controleur.notifierRevoquerGouverneur(ville.getId());
                }
            });
        } else {
            btnNaviguerGouverneurAssigne.setDisable(true);
            btnRevoquerGouverneur.setDisable(true);
        }

        btnNaviguerGouverneurs.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.notifierNaviguerMenuGouverneurs();
            }
        });

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

        if (!ville.peutConstruire()){
            btnAjouterBatiment.setDisable(true);
        }

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
        grillePrincipale.add(labelPlace, 0, 3);
        grillePrincipale.add(labelGouverneur, 0, 4);
        grillePrincipale.add(btnNaviguerGouverneurs, 0, 5);
        grillePrincipale.add(btnNaviguerGouverneurAssigne, 1, 5);
        grillePrincipale.add(btnRevoquerGouverneur, 2, 5);
        grillePrincipale.add(labelEffetsGouverneur, 0, 6);
        grillePrincipale.add(labelMessages, 0, 7);
        this.labelMessages.setVisible(false);
    }

    public void setControleur(controler.ControleurPrincipal controleur) {
        this.controleur = controleur;
    }
}
