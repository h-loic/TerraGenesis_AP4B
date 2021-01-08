package vue;

import controler.ControleurPrincipal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import modele.*;

import java.util.ArrayList;

/**
 * <b>Affiche la carte affichant les positions des avant-postes et des villes</b>
 * <p>
 * Cette vue est caracterisee par :
 * <ul>
 * <li>Une instance de la classe Carte</li>
 * </ul>
 * </p>
 *
 * @see modele.Carte
 *
 * @author Zapolatero - lpascuzzi
 * */

public class VueMenuCarte extends Scene {

    /**
     *  Constante definissant le style des boutons de la vue
     *
     * @see VueMenuPrincipal#VueMenuPrincipal()
     * @see VueMenuPrincipal#initialiserMenuPrincipal()
     */
    public static final String STYLE_BOUTONS = "-fx-background-color: #25467F; -fx-text-fill: white; -fx-font-size: 15; -fx-font-weight: bold;-fx-min-width: 120px";

    /**
     *  Constante definissant le style des titres de la vue
     *
     * @see VueMenuPrincipal#VueMenuPrincipal()
     * @see VueMenuPrincipal#initialiserMenuPrincipal()
     */
    public static final String STYLE_TITRE = " -fx-font-size: 20; -fx-font-weight: bold; -fx-padding: 15px; -fx-text-fill : white;";

    /**
     *  Constante definissant le style du fond de la vue
     *
     * @see VueMenuPrincipal#VueMenuPrincipal()
     * @see VueMenuPrincipal#initialiserMenuPrincipal()
     */
    public static final String STYLE_FOND = "-fx-background-color: #7680AD;";

    /**
     * La grille sur laquelle sont ajoutes les differents elements de la vue
     *
     * @see VueMenuCarte#VueMenuCarte()
     * @see VueMenuCarte#initialiserMenuCarte(Canvas)
     * */
    private GridPane grillePrincipale;

    /**
     * HBox servant a centrer le titre de la page dans la grille
     *
     * @see VueMenuCarte#VueMenuCarte()
     * @see VueMenuCarte#initialiserMenuCarte(Canvas)
     * */
    private HBox hBoxTitre;

    /**
     * HBox contenant le bouton de retour
     *
     * @see VueMenuCarte#VueMenuCarte()
     * @see VueMenuCarte#initialiserMenuCarte(Canvas)
     * */
    private HBox hBoxRetour;

    /**
     * Le controleur de l'application, permet a la vue d'interagir avec les modeles ou avec le navigateur des vues
     *
     * @see VueMenuCarte#setControleur(ControleurPrincipal)
     * @see controler.ControleurPrincipal
     */
    private controler.ControleurPrincipal controleur = null;

    /**
     * Label affichant le titre "carte"
     *
     * @see VueMenuCarte#setControleur(ControleurPrincipal)
     * @see controler.ControleurPrincipal
     */
    private Label labelCarte;

    /**
     * Bouton permettant de retourner au menuPrincipal
     *
     * @see VueMenuCarte#setControleur(ControleurPrincipal)
     * @see controler.ControleurPrincipal
     */
    private Button boutonRetour;

    /**
     * Canvas d'une instance du modele Carte sur lequel et dessinnee la carte de la planete
     *
     * @see VueMenuCarte#setControleur(ControleurPrincipal)
     * @see controler.ControleurPrincipal
     */
    private Canvas canvas;

    /**
     *  Constructeur de VueMenuCarte
     *  Cree les differents label et boutons de la vue
     *
     * @see VueMenuCarte#grillePrincipale
     * @see VueMenuCarte#labelCarte
     * @see VueMenuCarte#boutonRetour
     * @see VueMenuCarte#canvas
     */
    public VueMenuCarte() {
        super(new GridPane(), 500,400);
        this.grillePrincipale = (GridPane) this.getRoot();
        this.labelCarte = new Label("Carte");
        this.boutonRetour = new Button("retour");
        this.canvas = new Canvas(500,250);
        this.hBoxRetour = new HBox();
        this.hBoxTitre = new HBox();
    }

    /**
     *  Initialise la vue
     *      affichage de la carte sur le canvas
     *
     * @param canvasCarte Canvas de la Carte
     *
     * @see VueMenuCarte#grillePrincipale
     * @see VueMenuCarte#canvas
     * @see VueMenuCarte#controleur
     * @see VueMenuCarte#boutonRetour*
     * @see Carte#getCanvas()
     */
    public void initialiserMenuCarte(Canvas canvasCarte) {
        this.grillePrincipale.getChildren().clear();
        this.hBoxTitre.getChildren().clear();
        this.hBoxRetour.getChildren().clear();
        this.canvas = canvasCarte;

        this.labelCarte.setStyle(STYLE_TITRE);

        this.hBoxTitre.getChildren().add(this.labelCarte);
        this.hBoxTitre.setAlignment(Pos.CENTER);

        this.hBoxRetour.getChildren().add(this.boutonRetour);
        this.hBoxRetour.setAlignment(Pos.CENTER_LEFT);
        this.hBoxRetour.setPadding(new Insets(15,0,0,0));

        this.grillePrincipale.add(this.hBoxTitre, 0, 0);
        this.grillePrincipale.add(this.canvas, 0, 1);
        this.grillePrincipale.add(this.hBoxRetour, 0, 2);

        this.boutonRetour.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.notifierNaviguerMenuPrincipal();
            }
        });
        this.boutonRetour.setStyle(STYLE_BOUTONS);

        this.grillePrincipale.setStyle(STYLE_FOND);
    }

    /**
     *  Permet d'affecter un controleur a la vue
     *
     * @param controleur
     *
     * @see ControleurPrincipal
     * @see VueMenuCarte#controleur
     */
    public void setControleur(controler.ControleurPrincipal controleur) {
        this.controleur = controleur;
    }
}
