package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;


public class VueMenuPrincipal extends Scene {

    protected GridPane grilleAvions;
    private controler.ControleurPrincipal controleur = null;
    private Button actionNaviguerStatistiques;
    private Button actionNaviguerRecherche;
    private Button actionNaviguerPopulation;
    private Button actionNaviguerPointsCulture;
    private Button actionNaviguerSatellites;
    private Button actionNaviguerGouverneurs;

    public VueMenuPrincipal() {
        super(new GridPane(), 400,400);
        grilleAvions = (GridPane) this.getRoot();
        this.actionNaviguerStatistiques = new Button("Statistiques");
        this.actionNaviguerRecherche = new Button("Recherche");
        this.actionNaviguerPopulation = new Button("Population");
        this.actionNaviguerPointsCulture = new Button("Culture");
        this.actionNaviguerSatellites = new Button("Satellites");
        this.actionNaviguerGouverneurs = new Button("Gouverneurs");
    }

    public void initialiserMenuPrincipal() {
        this.grilleAvions.getChildren().clear();
        /*int numero = 0;
        this.grilleAvions.add(new Label("Modele"), 0, numero);
        this.grilleAvions.add(new Label("Vitesse de croisiere"), 1, numero);
        numero++;
        this.grilleAvions.add(new Label("aaaaaaaaa"), 0, numero);
        this.grilleAvions.add(new Label("avion.getPrix()"), 1, numero);
        this.grilleAvions.add(new Button("haaaaa"), 0, 3);*/
        this.grilleAvions.add(this.actionNaviguerStatistiques, 0, 0);
        this.grilleAvions.add(this.actionNaviguerRecherche, 1, 0);
        this.grilleAvions.add(this.actionNaviguerPopulation, 2, 0);
        this.grilleAvions.add(this.actionNaviguerPointsCulture, 0, 1);
        this.grilleAvions.add(this.actionNaviguerSatellites, 1, 1);
        this.grilleAvions.add(this.actionNaviguerGouverneurs, 2, 1);

        this.actionNaviguerStatistiques.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.notifierNaviguerMenuStatistiques();
            }
        });

        this.actionNaviguerRecherche.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.notifierNaviguerMenuRecherche();
            }
        });

        this.actionNaviguerPopulation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.notifierNaviguerMenuPopulation();
            }
        });

        this.actionNaviguerPointsCulture.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.notifierNaviguerMenuCulture();
            }
        });

        this.actionNaviguerSatellites.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.notifierNaviguerMenuSatellites();
            }
        });

        this.actionNaviguerGouverneurs.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.notifierNaviguerMenuGouverneurs();
            }
        });
    }

 /*   *//*public void afficherListeAvion(List<Avion> listeAvions)
    {
        this.grilleAvions.getChildren().clear();

        int numero = 0;
        this.grilleAvions.add(new Label("Modele"), 0, numero);
        this.grilleAvions.add(new Label("Vitesse de croisiere"), 1, numero);
        *//**//*for(Avion avion : listeAvions)
        {
            Button actionEditerAvion = new Button("Editer");
            actionEditerAvion.setUserData(avion.getId());
            actionEditerAvion.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    controleur.notifierNaviguerEditerAvion((int)((Button)e.getSource()).getUserData());
                    // TODO ameliorer ceci pour respecter architecture cible = pas de parametre dans les notifications au controleur
                }});*//**//*
            numero++;*//**//*
            this.grilleAvions.add(new Label(avion.getModele()), 0, numero);
            this.grilleAvions.add(new Label(avion.getPrix()), 1, numero);
            this.grilleAvions.add(actionEditerAvion, 2, numero);*//**//*
        }*//*

        this.actionNaviguerAjouterAvion.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent arg0) {
                controleur.notifierNaviguerAjouterAvion();
            }
        });

        this.grilleAvions.add(this.actionNaviguerAjouterAvion, 1, ++numero);
    }*/

    public void setControleur(controler.ControleurPrincipal controleur) {
        this.controleur = controleur;
    }
}
