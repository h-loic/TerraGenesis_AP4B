package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import modele.*;

import java.util.Date;
import java.util.Map;

/**
 * <p>
 *     Vue permettant d'afficher une ville.
 * </p>
 *
 * @see modele.Ville
 *
 * @author Antoine RICHARD - Antoine68
 * */
public class VueVille extends Scene {

    public static final String STYLE_BOUTONS = "-fx-background-color: #25467F; -fx-text-fill: white; -fx-font-size: 12; -fx-font-weight: bold;-fx-min-width: 50px";

    public static final String STYLE_SCROLLPANE = "-fx-background-color:transparent; -fx-border-width: 2px; -fx-border-color: black;";

    /**
     * La grille sur laquelle sont ajoutés les différents éléments de la vue.
     */
    private GridPane grillePrincipale;

    /**
     * La grille sur laquelle sont ajoutés les différents éléments de la ville.
     */
    private GridPane grilleVille;

    /**
     * La grille sur laquelle sont ajoutés les différent batiments.
     */
    private GridPane grilleBatiments;

    /**
     * Affiche la liste des batiments de la ville
     */
    private ScrollPane scrollPaneBatiments;

    /**
     * Le controleur de l'application, permet à la vue d'intéragir avec les modèles ou avec le navigateur des vues.
     */
    private controler.ControleurPrincipal controleur = null;

    /**
     * Label affichant le nom de la ville.
     */
    private Label labelNom;

    /**
     * Label affichant les coordonnées de la ville.
     */
    private Label labelCoordonnees;

    /**
     * Label affichant la population de la ville.
     */
    private Label labelPopulation;

    /**
     * Label affichant le nombre d'habitation de la ville.
     */
    private Label labelHabitation;

    /**
     * Label affichant le nombre de place de batiments dans la ville.
     */
    private Label labelPlace;

    /**
     * Label affichant le nom du gouveneur assigné à la ville.
     */
    private Label labelGouverneur;

    /**
     * Label affichant les effets du gouveneur assigné à la ville.
     */
    private Label labelEffetsGouverneur;

    /**
     * Label s'affichant quand il n'y à plus de place de batiment. Il affiche le pallier de population à atteindre
     * pour avoir une nouvelle place.
     */
    private Label labelProchainePlace;

    /**
     * Label affichant les messages d'informations.
     */
    private Label labelMessages;

    /**
     * Id de la ville.
     */
    private int idVille;

    /**
     * Bouton de retour vers le menu population
     */
    private Button btnRetour;

    /**
     * Bouton pour détruire la ville.
     */
    private Button btnDetruire;

    /**
     * Bouton d'ajout de batiments dans la ville.
     */
    private Button btnAjouterBatiment;

    /**
     * Bouton de navigation vers la liste des gouverneurs.
     */
    private Button btnNaviguerGouverneurs;

    /**
     * Bouton de navigation vers le gouverneur assigné à la ville.
     */
    private Button btnNaviguerGouverneurAssigne;

    /**
     * Bouton permettant de révoquer le gouverneur assigné.
     */
    private Button btnRevoquerGouverneur;

    /**
     * Constructeur VueVille. Créer les différents éléments de la vue.
     */
    public VueVille() {
        super(new GridPane(), 400,400);
        this.grillePrincipale = (GridPane) this.getRoot();
        this.grilleBatiments = new GridPane();
        this.grilleVille = new GridPane();
        this.scrollPaneBatiments = new ScrollPane();
        scrollPaneBatiments.setMinWidth(400);
        scrollPaneBatiments.setStyle(STYLE_SCROLLPANE);
        btnRetour = new Button("Retour");
        btnRetour.setStyle(STYLE_BOUTONS);
        btnDetruire = new Button("Détruire");
        btnDetruire.setStyle(STYLE_BOUTONS);
        btnAjouterBatiment = new Button("+ Batiment");
        btnAjouterBatiment.setStyle(STYLE_BOUTONS);
        btnNaviguerGouverneurs = new Button("Afficher liste gouverneurs");
        btnNaviguerGouverneurs.setStyle(STYLE_BOUTONS);
        btnNaviguerGouverneurAssigne = new Button("Afficher");
        btnNaviguerGouverneurAssigne.setStyle(STYLE_BOUTONS);
        btnRevoquerGouverneur= new Button("Révoquer");
        btnRevoquerGouverneur.setStyle(STYLE_BOUTONS);
        labelMessages = new Label("");
    }

    /**
     * Initialise la vue.
     * @param ville la ville à afficher
     */
    public void initialiserVueVille(Ville ville) {
        idVille = ville.getId();

        this.grilleVille.getChildren().clear();
        this.grilleBatiments.getChildren().clear();
        this.grillePrincipale.getChildren().clear();

        this.btnAjouterBatiment.setDisable(false);

        int ligneBatiment=0;

        //affichage des batiments en construction
        for (Map.Entry<Batiment, Date> batimentsEnConstruction : ville.getBatimentsEnConstruction().entrySet()) {
            Label labelNomBatiment = new Label(batimentsEnConstruction.getKey().getTypeBatiment().getNom()+" : en construction\ndurée des travaux : "
                    + batimentsEnConstruction.getKey().getTypeBatiment().getTempsConstructionParDefaut() + " min.\n");
            GridPane grilleBatiment = new GridPane();
            grilleBatiment.add(labelNomBatiment, 0, 0);
            grilleBatiment.add(new Text(""), 0, 1);
            grilleBatiments.add(grilleBatiment, 0, ligneBatiment);

            ligneBatiment++;
        }

        //affichage des listes et de leurs données
        for(Batiment batiment : ville.getBatiments()){
            Label labelNomBatiment = new Label(batiment.getTypeBatiment().getNom()+" : niv." + batiment.getNiveau() + (batiment.estEnCoursAmelioration() ? "+" : "")
                    + " état." + (batiment.estDesactive() ? "Désactivé" : "Activé"));
            Label labelEffet = new Label("effets :");
            TypeDonnee typeDonnee;
            double valeur;
            for (Map.Entry effet : batiment.getEffets().entrySet()) {
                typeDonnee = (TypeDonnee) effet.getKey();
                valeur = (double) effet.getValue();
                labelEffet.setText(labelEffet.getText() + " | " + typeDonnee.name() + ": " + valeur);
            }

            Button btnAmeliorer = new Button("Améliorer");
            btnAmeliorer.setStyle(STYLE_BOUTONS);
            btnAmeliorer.setUserData(batiment.getId());
            Button btnActiverDesactiver = new Button(batiment.estDesactive() ? "Activer" : "Désactiver");
            btnActiverDesactiver.setStyle(STYLE_BOUTONS);
            Button btnDetruireBatiment = new Button("Detruire");
            btnDetruireBatiment.setStyle(STYLE_BOUTONS);

            GridPane grilleBatiment = new GridPane();
            grilleBatiment.add(labelNomBatiment, 0, 0);
            grilleBatiment.add(labelEffet, 0, 1);
            grilleBatiment.add(btnAmeliorer, 0, 2);
            grilleBatiment.add(btnActiverDesactiver, 1, 2);
            grilleBatiment.add(btnDetruireBatiment, 2, 2);
            grilleBatiment.add(new Text(""), 0, 3);

            if (!batiment.peutAmeliorer()){
                btnAmeliorer.setDisable(true);
            }
            if (batiment.estEnCoursAmelioration()) {
                btnAmeliorer.setDisable(true);
                btnAmeliorer.setText("Amélioration en cours");
            }

            btnAmeliorer.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
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
        this.labelPopulation = new Label("pop. " + ville.getPopulation().getValeurActuelle());
        this.labelHabitation = new Label("hab. " + ville.getHabitation().getValeurActuelle());
        this.labelPlace = new Label("Nombre de places batiments restantes : " + ville.getNombrePlaceRestante() );
        this.labelProchainePlace = new Label(ville.getNombrePlaceRestante() != 0 ? "" : "Nouvelle place à pop. " + ville.getProchainPallierBatiment() );
        this.labelGouverneur = new Label("Gouveneur de la ville : " + (ville.getGouverneur() != null  ? ville.getGouverneur().getNom() : "aucun" ));
        this.labelEffetsGouverneur = new Label("");
        btnNaviguerGouverneurAssigne.setDisable(false);
        btnRevoquerGouverneur.setDisable(false);
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

        GridPane grilleBtnGouverneur = new GridPane();
        grilleBtnGouverneur.add(btnNaviguerGouverneurs, 0, 0);
        grilleBtnGouverneur.add(btnNaviguerGouverneurAssigne, 1, 0);
        grilleBtnGouverneur.add(btnRevoquerGouverneur, 2, 0);

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
        grillePrincipale.add(labelProchainePlace, 0, 4);
        grillePrincipale.add(labelGouverneur, 0, 5);
        grillePrincipale.add(grilleBtnGouverneur, 0, 6);
        grillePrincipale.add(labelEffetsGouverneur, 0, 7);
        grillePrincipale.add(labelMessages, 0, 8);
        this.labelMessages.setVisible(false);
    }

    /**
     * Affecter le controleur à la vue.
     * @param controleur le controleur affecté
     */
    public void setControleur(controler.ControleurPrincipal controleur) {
        this.controleur = controleur;
    }
}
