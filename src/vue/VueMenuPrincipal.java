package vue;

import controler.ControleurPrincipal;
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
import modele.Coordonnee;
import modele.Mine;
import modele.Ressource;

/**
 * <b>VueMenuPrincipale est le menu principale qui permet de naviguer entre les différentes vues du jeu</b>
 * <p>
 * La VueMenuPrincipale est possède par :
 * <ul>
 * <li>Un controleur principal</li>
 * <li>Une GridPane à laquelle on ajoute les différents éléments de la vue</li>
 * <li>6 Boutons permettant d'accèder à différentes vues</li>
 * <li>deux HBox permettant d'afficher chacun trois des six boutons</li>
 * </ul>
 * </p>
 *
 *
 * @author Zapolatero - lpascuzzi
 * */

public class VueMenuPrincipal extends Scene {

    /**
     * La grille sur laquelle sont ajoutés les différents éléments de la vue
     *
     * @see VueMenuPrincipal#VueMenuPrincipal()
     * @see VueMenuPrincipal#initialiserMenuPrincipal()
     */
    protected GridPane grillePrincipale;

    /**
     * Le controleur de l'application, permet à la vue d'intéragir avec les modèles ou avec le navigateur des vues
     *
     * @see VueMenuPrincipal#setControleur(ControleurPrincipal)
     * @see controler.ControleurPrincipal
     */
    private controler.ControleurPrincipal controleur = null;

    /**
     * Bouton permettant de naviguer vers la VueMenuStatistiques
     *
     * @see VueMenuPrincipal#VueMenuPrincipal()
     * @see VueMenuPrincipal#initialiserMenuPrincipal()
     * @see VueMenuStatistiques
     */
    private Button actionNaviguerStatistiques;

    /**
     * Bouton permettant de naviguer vers la VueMenuRecherche
     *
     * @see VueMenuPrincipal#VueMenuPrincipal()
     * @see VueMenuPrincipal#initialiserMenuPrincipal()
     * @see VueMenuRecherche
     */
    private Button actionNaviguerRecherche;

    /**
     * Bouton permettant de naviguer vers la VueMenuPopulation
     *
     * @see VueMenuPrincipal#VueMenuPrincipal()
     * @see VueMenuPrincipal#initialiserMenuPrincipal()
     * @see VueMenuPopulation
     */
    private Button actionNaviguerPopulation;

    /**
     * Bouton permettant de naviguer vers la VueMenuCulture
     *
     * @see VueMenuPrincipal#VueMenuPrincipal()
     * @see VueMenuPrincipal#initialiserMenuPrincipal()
     * @see VueMenuCulture
     */
    private Button actionNaviguerPointsCulture;

    /**
     * Bouton permettant de naviguer vers la VueMenuCarte
     *
     * @see VueMenuPrincipal#VueMenuPrincipal()
     * @see VueMenuPrincipal#initialiserMenuPrincipal()
     * @see VueMenuCarte
     */
    private Button actionNaviguerCarte;

    /**
     * Bouton permettant de naviguer vers la VueMenuGouverneurs
     *
     * @see VueMenuPrincipal#VueMenuPrincipal()
     * @see VueMenuPrincipal#initialiserMenuPrincipal()
     * @see VueMenuGouverneurs
     */
    private Button actionNaviguerGouverneurs;

    /**
     * HBox Hbox contenant 3 boutons
     *
     * @see VueMenuPrincipal#VueMenuPrincipal()
     * @see VueMenuPrincipal#initialiserMenuPrincipal()
     */
    private HBox hBoxLigne1;

    /**
     *  HBox Hbox contenant 3 boutons autres boutons
     *
     * @see VueMenuPrincipal#VueMenuPrincipal()
     * @see VueMenuPrincipal#initialiserMenuPrincipal()
     */
    private HBox hBoxLigne2;

    /**
     *  Constante définissant le style des boutons de la vue
     *
     * @see VueMenuPrincipal#VueMenuPrincipal()
     * @see VueMenuPrincipal#initialiserMenuPrincipal()
     */
    public static final String STYLE_BOUTONS = "-fx-background-color: #25467F; -fx-text-fill: white; -fx-font-size: 15; -fx-font-weight: bold;-fx-min-width: 120px";



    /**
     *  Constructeur de VueMenuPrincipal
     *
     * @see VueMenuPrincipal#grillePrincipale
     * @see VueMenuPrincipal#actionNaviguerStatistiques
     * @see VueMenuPrincipal#actionNaviguerRecherche
     * @see VueMenuPrincipal#actionNaviguerPopulation
     * @see VueMenuPrincipal#actionNaviguerPointsCulture
     * @see VueMenuPrincipal#actionNaviguerCarte
     * @see VueMenuPrincipal#actionNaviguerGouverneurs
     * @see VueMenuPrincipal#hBoxLigne1
     * @see VueMenuPrincipal#hBoxLigne2
     */
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

    /**
     *  Initialise la Vue:
     *      ajout d'action listeners,
     *      ajout des éléments aux différents layouts de la vue
     *
     * @see VueMenuPrincipal#grillePrincipale
     * @see VueMenuPrincipal#actionNaviguerStatistiques
     * @see VueMenuPrincipal#actionNaviguerRecherche
     * @see VueMenuPrincipal#actionNaviguerPopulation
     * @see VueMenuPrincipal#actionNaviguerPointsCulture
     * @see VueMenuPrincipal#actionNaviguerCarte
     * @see VueMenuPrincipal#actionNaviguerGouverneurs
     * @see VueMenuPrincipal#hBoxLigne1
     * @see VueMenuPrincipal#hBoxLigne2
     */
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


        this.actionNaviguerCarte.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.notifierNaviguerMenuCarte();
            }
        });


        this.actionNaviguerGouverneurs.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.notifierNaviguerMenuGouverneurs();
                System.out.println(actionNaviguerGouverneurs.getWidth());
            }
        });

    }

    /**
     *  Permet d'affecter un controleur à la vue
     *
     * @param controleur
     *
     * @see ControleurPrincipal
     * @see VueMenuPrincipal#controleur
     */
    public void setControleur(controler.ControleurPrincipal controleur) {
        this.controleur = controleur;
    }
}
