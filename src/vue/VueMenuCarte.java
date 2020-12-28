package vue;

import controler.ControleurPrincipal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import modele.Carte;
import modele.Coordonnee;
import modele.Mine;
import modele.Ressource;

/**
 * <b>Affiche la carte affichant les positions des avant-postes et des villes</b>
 * <p>
 * Cette vue est caractérisée par :
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
     * La grille sur laquelle sont ajoutés les différents éléments de la vue
     *
     * @see VueMenuCarte#VueMenuCarte()
     * @see VueMenuCarte#initialiserMenuCarte(Canvas)
     * */
    protected GridPane grillePrincipale;

    /**
     * Le controleur de l'application, permet à la vue d'intéragir avec les modèles ou avec le navigateur des vues
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
     * Canvas d'une instance du modele Carte sur lequel et déssinnée la carte de la planète
     *
     * @see VueMenuCarte#setControleur(ControleurPrincipal)
     * @see controler.ControleurPrincipal
     */
    private Canvas canvas;

    /**
     *  Constructeur de VueMenuCarte
     *  Créé les différents label et boutons de la vue
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
        this.canvas = canvasCarte;
        this.grillePrincipale.add(this.labelCarte, 0, 0);
        this.grillePrincipale.add(this.canvas, 0, 1);
        this.grillePrincipale.add(this.boutonRetour, 0, 2);

        this.boutonRetour.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.notifierNaviguerMenuPrincipal();
            }
        });
    }

    /**
     *  Permet d'affecter un controleur à la vue
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
