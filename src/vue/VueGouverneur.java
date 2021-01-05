package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import modele.Donnee;
import modele.Gouverneur;

import java.util.ArrayList;

/**
 * <p>
 *     Vue permettant d'afficher un gouverneur.
 * </p>
 *
 * @see modele.Gouverneur
 *
 * @author Loïc HUG - h-loic
 * */
public class VueGouverneur extends Scene{

    /**
     *  Constante definissant le style des titres de la vue
     *
     * @see VueMenuGouverneurs#VueMenuGouverneurs()
     * @see VueMenuGouverneurs#initialiserMenuGouverneurs(ArrayList)
     */
    public static final String STYLE_TITRE = " -fx-font-size: 18; -fx-font-weight: bold; -fx-padding: 15px";

    /**
     *  Constante definissant le style des boutons de la vue
     *
     * @see VueMenuGouverneurs#VueMenuGouverneurs()
     * @see VueMenuGouverneurs#initialiserMenuGouverneurs(ArrayList)
     */
    public static final String STYLE_BOUTONS = "-fx-background-color: #25467F; -fx-text-fill: white; -fx-font-size: 12; -fx-font-weight: bold;-fx-min-width: 80;";

    /**
     * La grille sur laquelle sont ajoutés les différents éléments de la vue.
     */
    protected GridPane grillePrincipale;

    /**
     * Le controleur de l'application, permet à la vue d'intéragir avec les modèles ou avec le navigateur des vues.
     */
    private controler.ControleurPrincipal controleur = null;

    /**
     * Label affichant le nom du gouverneur.
     */
    private Label labelNom;

    /**
     * Label affichant le niveau du gouverneur.
     */
    private Label labelNiveau;

    /**
     * Label affichant la ville a laquelle le gouverneur est affecté.
     */
    private Label labelAffecter;

    /**
     * bouton permettant d'améliorer le gouverneur
     */
    private Button btnAmeliorer;

    /**
     * bouton de retour vers la liste des gouverneurs
     */
    private Button btnRetour;

    /**
     * bouton permettant d'affecter le gouverneur
     */
    private Button btnAffecter;

    /**
     * Constructeur VueVille. Créer les différents éléments de la vue.
     */
    public VueGouverneur() {
        super(new GridPane(), 500,400);
        grillePrincipale = (GridPane) this.getRoot();
        btnRetour = new Button("Retour");
        this.btnRetour.setStyle(STYLE_BOUTONS);
    }

    /**
     * Initialise la vue.
     * @param gouverneur la ville à afficher
     */
    public void initialiserVueGouverneur(Gouverneur gouverneur) {
        grillePrincipale.getChildren().clear();
        btnRetour.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.notifierNaviguerMenuGouverneurs();
            }
        });
        btnAmeliorer = new Button("ameliorer");
        this.btnAmeliorer.setStyle(STYLE_BOUTONS);
        btnAmeliorer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.notifierAmeliorerGouverneur(gouverneur);
                controleur.notifierNaviguerAfficherGouverneur(gouverneur);
            }
        });
        labelNom = new Label(gouverneur.getNom());
        this.labelNom.setStyle(STYLE_TITRE);
        labelNiveau = new Label(Integer.toString(gouverneur.getNiveau()));
        if (!gouverneur.estAffecte()){
            labelAffecter = new Label("non affecté");
            btnAffecter = new Button("affecter");
            this.btnAffecter.setStyle(STYLE_BOUTONS);
            btnAffecter.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    controleur.notifierNaviguerAffecterGouverneur(gouverneur);
                }
            });
        }else{
            System.out.println(gouverneur.getVilleAffecter());
            labelAffecter = new Label(gouverneur.getVilleAffecter().getNom());
            btnAffecter = new Button("révoquer");
            this.btnAffecter.setStyle(STYLE_BOUTONS);
            btnAffecter.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    controleur.notifierRevoquerGouverneurDepuisGouverneur(gouverneur.getVilleAffecter().getId(),gouverneur);
                }
            });
        }
        grillePrincipale.add(labelNom,1,0);
        grillePrincipale.add(new Label("niveau : "),0,1);
        grillePrincipale.add(labelNiveau,1,1);
        grillePrincipale.add(new Label("affecté : "),0,2);
        grillePrincipale.add(labelAffecter,1,2);
        grillePrincipale.add(new Label("effets : "),0,3);
        int compteurLigne = 3;
        for (Donnee donnee : gouverneur.getEffets().keySet()) {
            compteurLigne++;
            grillePrincipale.add(new Label(donnee.getTypeDonnee().name()),1,compteurLigne);
            grillePrincipale.add(new Label(Double.toString(gouverneur.getEffets().get(donnee))),2,compteurLigne);
        }
        grillePrincipale.add(btnAmeliorer,2,1);
        grillePrincipale.add(btnAffecter,1,compteurLigne+1);
        grillePrincipale.add(btnRetour,1,compteurLigne+2);
    }

    /**
     * Affecter le controleur à la vue.
     * @param controleur le controleur affecté
     */
    public void setControleur(controler.ControleurPrincipal controleur) {
        this.controleur = controleur;
    }
}
