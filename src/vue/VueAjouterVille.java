package vue;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import modele.Coordonnee;
import modele.Ville;

import java.util.Random;

/**
 * <p>
 *     Vue permettant d'ajouter une ville a la planete.
 * </p>
 *
 * @see modele.Ville
 *
 * @author Antoine RICHARD - Antoine68
 * */
public class VueAjouterVille extends Scene {

    public static final String STYLE_BOUTONS = "-fx-background-color: #25467F; -fx-text-fill: white; -fx-font-size: 12; -fx-font-weight: bold;-fx-min-width: 50px";

    /**
     * Le controleur de l'application, permet a la vue d'interagir avec les modeles ou avec le navigateur des vues.
     */
    private controler.ControleurPrincipal controleur = null;

    /**
     * La grille sur laquelle sont ajoutes les differents elements de la vue.
     */
    protected GridPane grillePrincipale;

    /**
     * La grille sur laquelle sont ajoutes les differents elements du formulaire.
     */
    protected GridPane grilleForm;

    /**
     * La grille sur laquelle sont ajoutes les differents boutons.
     */
    protected GridPane grilleBoutons;

    /**
     * Label affichant "Nom:".
     */
    private Label labelNom;

    /**
     * Label affichant "X:".
     */
    private Label labelX;

    /**
     * Label affichant "Y:".
     */
    private Label labelY;

    /**
     * Label affichant "Z:".
     */
    private Label labelZ;

    /**
     * Label affichant les erreurs dans le formulaire.
     */
    private Label labelErreurs;

    /**
     * Permet a l'utilisateur d'entree le nom de la ville
     */
    private TextField textFieldNomVille;

    /**
     * Canvas affichant la Carte de la planete, sur laquelle la ville sera positionnee
     */
    private Canvas canvasCoords;
    private GraphicsContext gcCanva;

    /**
     * Label affichant la longitude du point selectionne par l'utilisateur
     */
    private Label labelXville;

    /**
     * Label affichant la latitude du point selectionne par l'utilisateur
     */
    private Label labelYville;

    /**
     * Label affichant l'altitude du point selectionne par l'utilisateur
     */
    private Label labelZville;

    /**
     * Bouton de retour vers le menu population.
     */
    private Button btnRetourMenuPopulation;

    /**
     * Bouton permettant de valider le formulaire.
     */
    private Button btnAjouterVille;

    /**
     * Contiendra le nom de la ville.
     */
    private String nomVille;

    /**
     * Contiendra la longitude de la ville.
     */
    private double xVille;

    /**
     * Contiendra la latitude de la ville.
     */
    private double yVille;

    /**
     * Contiendra l'altitude de la ville.
     */
    private double zVille;

    /**
     *
     * Constructeur VueAjouterVille. Creer les differents elements de la vue.
     *
     */
    public VueAjouterVille() {
        super(new GridPane(), 500,400);

        this.btnRetourMenuPopulation = new Button("Annuler");
        btnRetourMenuPopulation.setStyle(STYLE_BOUTONS);
        this.btnAjouterVille = new Button("Ajouter");
        btnAjouterVille.setStyle(STYLE_BOUTONS);

        this.canvasCoords = new Canvas(250,250);
        this.gcCanva = this.canvasCoords.getGraphicsContext2D();

        this.labelNom = new Label("Nom : ");
        this.textFieldNomVille = new TextField();
        this.labelX = new Label("X : ");
        this.labelY = new Label("Y : ");
        this.labelZ = new Label("Z : ");
        this.labelXville = new Label();
        this.labelYville = new Label();
        this.labelZville = new Label();
        this.labelErreurs = new Label("");


        this.grillePrincipale = (GridPane) this.getRoot();
        this.grilleForm = new GridPane();
        this.grilleBoutons = new GridPane();
    }


    /**
     * Initialise la vue.
     * @param carte la carte qui sera affichee
     */
    public void initialiserVueAjouterVille(Canvas carte) {
        this.grillePrincipale.getChildren().clear();
        this.grilleForm.getChildren().clear();
        this.grilleBoutons.getChildren().clear();
        this.canvasCoords = carte;
        this.canvasCoords.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dessineAvPoste(event.getX(), event.getY());
            }
        });

        labelXville.setText("");
        labelYville.setText("");
        labelZville.setText("");
        textFieldNomVille.setText("");
        labelErreurs.setText("");


        grilleForm.add(this.labelNom,0,0);
        grilleForm.add(this.textFieldNomVille,1,0);

        grilleForm.add(this.labelX,0,1);
        grilleForm.add(this.labelXville,1,1);

        grilleForm.add(this.labelY,0,2);
        grilleForm.add(this.labelYville,1,2);

        grilleForm.add(this.labelZ,0,3);
        grilleForm.add(this.labelZville,1,3);


        grilleForm.add(this.labelErreurs, 0, 4);

        btnRetourMenuPopulation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.notifierNaviguerMenuPopulation();
            }
        });

        btnAjouterVille.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                validerDonnees();
            }
        });

        grilleBoutons.add(this.btnAjouterVille, 0, 0);
        grilleBoutons.add(this.btnRetourMenuPopulation, 1, 0);

        grillePrincipale.add(this.grilleForm, 0, 0);
        grillePrincipale.add(this.canvasCoords, 0, 1);
        grillePrincipale.add(this.grilleBoutons, 0, 2);
    }

    private void dessineAvPoste(double x, double y){
        Random random = new Random();
        // l'altitude est generee aleatoirement
        double z = -1 + (50 - (-1)) * random.nextDouble();
        labelXville.setText("");
        labelYville.setText("");
        labelZville.setText("");
        labelErreurs.setText("");
        if (this.controleur.verifierCoordonnees(x, y)){
            labelXville.setText(Double.toString(x));
            labelYville.setText(Double.toString(y));
            labelZville.setText(Double.toString(z));
        }else{
            labelErreurs.setText("Position trop proche d'un avant-poste ou d'une ville");
        }
    }

    /**
     * Valider les donnees du formulaire rentrees par l'utilisateur.
     * Puis notifier le controleur de creer la ville.
     */
    private void validerDonnees() {
        boolean erreur = false;
        String messageErreurs = "";
        //verifie si un nom a ete saissie.
        if (textFieldNomVille.getText().isEmpty()){
            erreur = true;
            messageErreurs+="Veuillez entrer un nom";
            labelErreurs.setText(messageErreurs);
        }

        try {
            nomVille = textFieldNomVille.getText();
        }catch (Exception e){
            System.out.println(e.getMessage());
            erreur = true;
            messageErreurs+="Veuillez entrer un nom";
            labelErreurs.setText(messageErreurs);
        }
        try {
            //verifie si les coordonees ont ete selectionnees.
            xVille = Double.parseDouble(labelXville.getText());
            yVille = Double.parseDouble(labelYville.getText());
            zVille = Double.parseDouble(labelZville.getText());
        }catch (Exception e){
            System.out.println(e.getMessage());
            messageErreurs+=" Veuillez entrer des coordonnees valides";
            labelErreurs.setText(messageErreurs);
            erreur = true;
        }
        if (!erreur){
            this.controleur.notifierAjouterVille();
        }
    }


    /**
     * Creer une ville avec les donnees entrees par l'utilisateur et le retourne.
     * @return la ville cree
     */
    public Ville getVille()
    {
        Ville ville = new Ville(nomVille,  new Coordonnee(xVille, yVille, zVille));
        return ville;
    }

    /**
     * Affecter le controleur a la vue.
     * @param controleur le controleur affecte
     */
    public void setControleur(controler.ControleurPrincipal controleur) {
        this.controleur = controleur;
    }
}
