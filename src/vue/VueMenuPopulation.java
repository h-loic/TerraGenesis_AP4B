package vue;

import controler.ControleurPrincipal;
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

/**
 * <b>
 *     Vue listant les différentes villes et avant-postes de la planete et permettant d'en ajouter
 * </p>
 *
 * @see modele.Planete
 * @see AvantPoste
 * @see Ville
 * @see controler.ControleurPrincipal
 * @see VueAjouterAvantPoste
 * @see VueAjouterVille
 * @author Zapolatero - lpascuzzi
 * */

public class VueMenuPopulation extends Scene {

    /**
     *  Constante définissant le style des scrollpanes de la vue
     *
     * @see VueMenuPopulation#VueMenuPopulation()
     * @see VueMenuPopulation#initialiserMenuPopulation(ArrayList, ArrayList) ()
     */
    public static final String STYLE_SCROLLPANE = "-fx-background-color:transparent;";

    /**
     *  Constante définissant le style des titres de la vue
     *
     * @see VueMenuPopulation#VueMenuPopulation()
     * @see VueMenuPopulation#initialiserMenuPopulation(ArrayList, ArrayList) ()
     */
    public static final String STYLE_TITRE = " -fx-font-size: 18; -fx-font-weight: bold; -fx-padding: 15px";

    /**
     *  Constante définissant le style du label des erreurs de la vue
     *
     * @see VueMenuPopulation#VueMenuPopulation()
     * @see VueMenuPopulation#initialiserMenuPopulation(ArrayList, ArrayList) ()
     */
    public static final String STYLE_ERREUR = "-fx-text-fill: red; -fx-font-size: 13; -fx-font-weight: bold";

    /**
     *  Constante définissant le style des boutons de la vue
     *
     * @see VueMenuPopulation#VueMenuPopulation()
     * @see VueMenuPopulation#initialiserMenuPopulation(ArrayList, ArrayList) ()
     */
    public static final String STYLE_BOUTONS = "-fx-background-color: #25467F; -fx-text-fill: white; -fx-font-size: 12; -fx-font-weight: bold;-fx-min-width: 80px";



    /**
     * La grille sur laquelle sont ajoutés les différents éléments de la vue
     *
     * @see VueMenuPopulation#VueMenuPopulation()
     * @see VueMenuPopulation#initialiserMenuPopulation(ArrayList, ArrayList) ()
     */
    protected GridPane grillePrincipale;

    /**
     * La grille sur laquelle sont listées les villes de la planète
     *
     * @see VueMenuPopulation#VueMenuPopulation()
     * @see VueMenuPopulation#initialiserMenuPopulation(ArrayList, ArrayList) ()
     */
    protected GridPane grilleVilles;

    /**
     * La grille sur laquelle sont listées les avant-postes de la planète
     *
     * @see VueMenuPopulation#VueMenuPopulation()
     * @see VueMenuPopulation#initialiserMenuPopulation(ArrayList, ArrayList) ()
     */
    protected GridPane grilleAvPostes;

    /**
     * Scrollpane dans laquelle sera affiché la gridPane des avant-postes
     *
     * @see VueMenuPopulation#VueMenuPopulation()
     * @see VueMenuPopulation#initialiserMenuPopulation(ArrayList, ArrayList) ()
     * @see VueMenuPopulation#grilleAvPostes
     */
    private ScrollPane scrollPaneAvPostes;

    /**
     * Scrollpane dans laquelle sera affiché la gridPane des villes
     *
     * @see VueMenuPopulation#VueMenuPopulation()
     * @see VueMenuPopulation#initialiserMenuPopulation(ArrayList, ArrayList) ()
     * @see VueMenuPopulation#grilleVilles
     */
    private ScrollPane scrollPaneVilles;

    /**
     * Le controleur de l'application, permet à la vue d'intéragir avec les modèles ou avec le navigateur des vues
     *
     * @see VueMenuPopulation#setControleur(ControleurPrincipal)
     * @see controler.ControleurPrincipal
     */
    private controler.ControleurPrincipal controleur = null;

    /**
     * Titre de la liste des villes
     *
     * @see VueMenuPopulation#VueMenuPopulation()
     */
    private Label labelVilles;

    /**
     * Titre de la liste des avant-postes
     *
     * @see VueMenuPopulation#VueMenuPopulation()
     */
    private Label labelAvPostes;

    /**
     * Label affichant les messages d'erreures
     *
     * @see VueMenuPopulation#VueMenuPopulation()
     */
    private Label labelMessages;

    /**
     * Bouton permettant de naviguer vers la VueMenuPrincial
     *
     * @see VueMenuPopulation#VueMenuPopulation()
     * @see VueMenuPopulation#initialiserMenuPopulation(ArrayList, ArrayList) ()
     * @see VueMenuPrincipal
     */
    private Button btnRetour;

    /**
     * Bouton permettant de naviguer vers la VueAjouterVille pour ajouter une ville
     *
     * @see VueMenuPopulation#VueMenuPopulation()
     * @see VueMenuPopulation#initialiserMenuPopulation(ArrayList, ArrayList) ()
     * @see VueAjouterVille
     */
    private Button btnAjouterVille;

    /**
     * Bouton permettant de naviguer vers la VueAjouterAvantPoste pour ajouter un avant-poste
     *
     * @see VueMenuPopulation#VueMenuPopulation()
     * @see VueMenuPopulation#initialiserMenuPopulation(ArrayList, ArrayList) ()
     * @see VueAjouterAvantPoste
     */
    private Button btnAjouterAvPoste;

    /**
     *  Constructeur de VueMenuPopulation
     *
     * @see VueMenuPopulation#grillePrincipale
     * @see VueMenuPopulation#grilleAvPostes
     * @see VueMenuPopulation#grilleVilles
     * @see VueMenuPopulation#scrollPaneAvPostes
     * @see VueMenuPopulation#scrollPaneVilles
     * @see VueMenuPopulation#labelVilles
     * @see VueMenuPopulation#labelAvPostes
     * @see VueMenuPopulation#labelMessages
     * @see VueMenuPopulation#btnRetour
     * @see VueMenuPopulation#btnAjouterVille
     * @see VueMenuPopulation#btnAjouterAvPoste
     */
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

    /**
     *  Initialise la Vue:
     *      ajout d'action listeners,
     *      ajout des éléments aux différents layouts de la vue
     *      affichage des avantPostes et des Villes
     *
     * @param listeAvantPostes liste des AvantPostes de la planète
     * @param listeVilles liste des villes de la planète
     *
     * @see VueMenuPopulation#grillePrincipale
     * @see VueMenuPopulation#grilleAvPostes
     * @see VueMenuPopulation#grilleVilles
     * @see VueMenuPopulation#scrollPaneAvPostes
     * @see VueMenuPopulation#scrollPaneVilles
     * @see VueMenuPopulation#labelVilles
     * @see VueMenuPopulation#labelAvPostes
     * @see VueMenuPopulation#labelMessages
     * @see VueMenuPopulation#btnRetour
     * @see VueMenuPopulation#btnAjouterVille
     * @see VueMenuPopulation#btnAjouterAvPoste
     */
    public void initialiserMenuPopulation(ArrayList<Ville> listeVilles, ArrayList<AvantPoste> listeAvantPostes) {
        int lignesAvPostes = 1;
        int lignesVilles = 1;
        this.grillePrincipale.getChildren().clear();
        this.grilleVilles.getChildren().clear();
        this.grilleAvPostes.getChildren().clear();

        /* VILLES */
        //Liste les villes de la planète
        for (Ville ville : listeVilles){
            Label labelNom = new Label(ville.getNom()+" : ");
            Label labelPopulation = new Label(" pop. "+ville.getPopulation().getValeurActuelle());

            //Bouton permettant d'afficher la page de la ville
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
            //ajout de la ville à la liste des villes
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
                    //affichage des messages d'erreurs s il est impossible d'ajouter une ville
                    System.out.println(e.getMessage());
                    labelMessages.setText(e.getMessage());
                    labelMessages.setVisible(true);
                }
            }
        });

        this.scrollPaneVilles.setContent(this.grilleVilles);
        this.scrollPaneVilles.setMinWidth(500);
        this.scrollPaneVilles.setStyle(STYLE_SCROLLPANE);

        //affichage des bar de la scrollPane si besoin
        this.scrollPaneVilles.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        this.scrollPaneVilles.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        this.grillePrincipale.add(this.labelVilles, 0, 0);
        this.grillePrincipale.add(this.scrollPaneVilles, 0, 1);
        this.grillePrincipale.add(this.btnAjouterVille, 0, 2);


        /* AVANT-POSTES */
        for (AvantPoste avantPoste : listeAvantPostes){
            Label labelNom = new Label(avantPoste.getNom());
            Label labelBenefices = new Label(" €/min : "+avantPoste.getBeneficesMines());
            Label labelNbMines = new Label(avantPoste.getMines().size()+" mine(s)");

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
            ligne.getChildren().addAll(labelNom, labelNbMines, labelBenefices,  btnAfficher);

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
                    //gestion des erreurs si impossible d'ajouter un avantPoste
                    System.out.println(e.getMessage());
                    labelMessages.setText(e.getMessage());
                    labelMessages.setVisible(true);
                }
            }
        });

        //bouton pour retourner au menu principal
        this.btnRetour.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.notifierNaviguerMenuPrincipal();
            }
        });

        this.scrollPaneAvPostes.setContent(this.grilleAvPostes);
        //affichage des bars de la scrollpane si besoin
        this.scrollPaneAvPostes.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
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

    /**
     *  Permet d'affecter un controleur à la vue
     *
     * @param controleur
     *
     * @see ControleurPrincipal
     * @see VueMenuPopulation#controleur
     */
    public void setControleur(controler.ControleurPrincipal controleur) {
        this.controleur = controleur;
    }
}
