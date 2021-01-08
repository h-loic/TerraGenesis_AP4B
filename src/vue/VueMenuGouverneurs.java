package vue;

import controler.ControleurPrincipal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import modele.Gouverneur;

import java.util.ArrayList;

/**
 * <b>
 *     Vue listant les differentes villes et avant-postes de la planete et permettant d'en ajouter
 * </p>
 *
 * @see modele.Planete
 * @see Gouverneur
 * @see controler.ControleurPrincipal
 * @see VueGouverneur
 * @author Loïc HUG - h-loic
 * */

public class VueMenuGouverneurs extends Scene {

    /**
     *  Constante definissant le style des titres de la vue
     *
     * @see VueMenuGouverneurs#VueMenuGouverneurs() 
     * @see VueMenuGouverneurs#initialiserMenuGouverneurs(ArrayList) 
     */
    public static final String STYLE_TITRE = " -fx-font-size: 18; -fx-font-weight: bold; -fx-padding: 15px";

    /**
     *  Constante definissant le style des boutons pour trier la vue
     *
     * @see VueMenuGouverneurs#VueMenuGouverneurs()
     * @see VueMenuGouverneurs#initialiserMenuGouverneurs(ArrayList)
     */
    public static final String STYLE_BOUTONS_TRIER = "-fx-background-color: #25467F; -fx-text-fill: white; -fx-font-size: 12; -fx-font-weight: bold;-fx-min-width: 80;" +
            "-fx-border-insets: 5px;-fx-background-insets: 5px;";

    /**
     *  Constante definissant le style des boutons de la vue
     *
     * @see VueMenuGouverneurs#VueMenuGouverneurs()
     * @see VueMenuGouverneurs#initialiserMenuGouverneurs(ArrayList)
     */
    public static final String STYLE_BOUTONS = "-fx-background-color: #25467F; -fx-text-fill: white; -fx-font-size: 12; -fx-font-weight: bold;-fx-min-width: 80;";

    /**
     * La grille sur laquelle sont ajoutes les differents elements de la vue
     *
     * @see VueMenuGouverneurs#VueMenuGouverneurs() ()
     * @see VueMenuGouverneurs#initialiserMenuGouverneurs(ArrayList)()
     */
    private GridPane grillePrincipale;

    /**
     * La grille sur laquelle sont listees les gouverneurs de la planete
     *
     * @see VueMenuGouverneurs#VueMenuGouverneurs() ()
     * @see VueMenuGouverneurs#initialiserMenuGouverneurs(ArrayList)()
     */
    private GridPane grilleGouverneur;

    /**
     * Le controleur de l'application, permet a la vue d'interagir avec les modeles ou avec le navigateur des vues
     *
     * @see VueMenuGouverneurs#setControleur(ControleurPrincipal)
     * @see controler.ControleurPrincipal
     */
    private controler.ControleurPrincipal controleur = null;

    /**
     * Titre de la liste des gouverneurs
     *
     * @see VueMenuGouverneurs#VueMenuGouverneurs()
     */
    private Label labelGouverneurs;

    /**
     * Bouton permettant de naviguer vers la VueMenuPrincial
     *
     * @see VueMenuGouverneurs#VueMenuGouverneurs()
     * @see VueMenuGouverneurs#initialiserMenuGouverneurs(ArrayList) ()
     * @see VueMenuPrincipal
     */
    private Button boutonRetour;

    /**
     * Bouton permettant de trier les gouverneurs en fonction de leurs noms
     *
     * @see VueMenuGouverneurs#VueMenuGouverneurs()
     * @see VueMenuGouverneurs#initialiserMenuGouverneurs(ArrayList) ()
     */
    private Button boutonTrierParNom;

    /**
     * Bouton permettant de trier les gouverneurs en fonction de leurs etat estDebloque
     *
     * @see VueMenuGouverneurs#VueMenuGouverneurs()
     * @see VueMenuGouverneurs#initialiserMenuGouverneurs(ArrayList) ()
     */
    private Button boutonTrierParDebloque;

    /**
     * Constructeur de VueMenuGouverneur
     *
     * @see VueMenuGouverneurs#grillePrincipale
     * @see VueMenuGouverneurs#grilleGouverneur
     * @see VueMenuGouverneurs#labelGouverneurs
     * @see VueMenuGouverneurs#boutonRetour
     * @see VueMenuGouverneurs#boutonTrierParDebloque
     * @see VueMenuGouverneurs#boutonTrierParNom
     */
    public VueMenuGouverneurs() {
        super(new GridPane(), 500,400);
        grillePrincipale = (GridPane) this.getRoot();

        this.labelGouverneurs = new Label("Gouverneurs");
        this.labelGouverneurs.setStyle(STYLE_TITRE);
        this.boutonRetour = new Button("retour");
        this.boutonRetour.setStyle(STYLE_BOUTONS);
        this.boutonTrierParNom = new Button("Trier par nom");
        this.boutonTrierParNom.setStyle(STYLE_BOUTONS_TRIER);
        this.boutonTrierParDebloque = new Button("Trier par debloque");
        this.boutonTrierParDebloque.setStyle(STYLE_BOUTONS_TRIER);
    }

    /**
     * Initialise la Vue:
     *      ajout d'action listeners,
     *      ajout des elements aux differents layouts de la vue
     *      affichage des gouverneurs
     *
     * @param listeGouverneur
     *
     * @see VueMenuGouverneurs#grillePrincipale
     * @see VueMenuGouverneurs#grilleGouverneur
     * @see VueMenuGouverneurs#labelGouverneurs
     * @see VueMenuGouverneurs#boutonRetour
     * @see VueMenuGouverneurs#boutonTrierParDebloque
     * @see VueMenuGouverneurs#boutonTrierParNom
     */
    public void initialiserMenuGouverneurs(ArrayList<Gouverneur> listeGouverneur) {
        grilleGouverneur = new GridPane();
        int lignesGouverneur = 1;
        this.grillePrincipale.getChildren().clear();

        for (Gouverneur gouverneur : listeGouverneur){
            Label labelNom = new Label(gouverneur.getNom());
            lignesGouverneur+=1;
            this.grilleGouverneur.add(labelNom, 0, lignesGouverneur);
            if (gouverneur.estDebloque()){
                Button btnAfficher = new Button("afficher");
                btnAfficher.setStyle(STYLE_BOUTONS);
                btnAfficher.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        controleur.notifierNaviguerAfficherGouverneur(gouverneur);
                    }
                });
                this.grilleGouverneur.add(btnAfficher, 4, lignesGouverneur);
            }else{
                Button btnDebloque = new Button("debloquer");
                btnDebloque.setStyle(STYLE_BOUTONS);
                btnDebloque.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        controleur.notifierDebloquerGouverneur(gouverneur);
                        controleur.notifierNaviguerMenuGouverneurs();
                    }
                });
                this.grilleGouverneur.add(btnDebloque, 4, lignesGouverneur);

            }
        }

        lignesGouverneur+=2;
        this.grillePrincipale.add(this.labelGouverneurs, 0, 0);
        this.grillePrincipale.add(this.boutonTrierParNom, 1, 0);
        this.grillePrincipale.add(this.boutonTrierParDebloque, 2, 0);
        this.grillePrincipale.add(this.boutonRetour, 1, lignesGouverneur);
        this.grillePrincipale.add(this.grilleGouverneur, 0, 1);

        this.boutonRetour.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.notifierNaviguerMenuPrincipal();
            }
        });

        boutonTrierParNom.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.notifierTrierParNomListeGouverneur();
                controleur.notifierNaviguerMenuGouverneurs();
            }
        });

        boutonTrierParDebloque.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.notifierTrierParDebloqueListeGouverneur();
                controleur.notifierNaviguerMenuGouverneurs();
            }
        });
    }

    /**
     *  Permet d'affecter un controleur a la vue
     *
     * @param controleur
     *
     * @see ControleurPrincipal
     * @see VueMenuGouverneurs#controleur
     */
    public void setControleur(controler.ControleurPrincipal controleur) {
        this.controleur = controleur;
    }
}
