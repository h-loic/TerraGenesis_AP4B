package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modele.Gouverneur;

import java.util.ArrayList;


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

    protected GridPane grillePrincipale;
    protected GridPane grilleGouverneur;

    private controler.ControleurPrincipal controleur = null;

    private Label labelGouverneurs;
    private Button boutonRetour;
    private Button boutonTrierParNom;
    private Button boutonTrierParDebloque;

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

    public void setControleur(controler.ControleurPrincipal controleur) {
        this.controleur = controleur;
    }
}
