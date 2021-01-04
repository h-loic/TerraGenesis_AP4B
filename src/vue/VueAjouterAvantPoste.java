package vue;

import controler.ControleurPrincipal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import modele.AvantPoste;
import modele.Coordonnee;
import modele.Mine;

import java.util.ArrayList;
import java.util.Random;


/**
 * <b>
 *     Vue Permettant d'ajouter un avant-poste a la planete
 * </p>
 *
 * @see modele.AvantPoste
 * @author Zapolatero - Louis Pascuzzi
 * */

public class VueAjouterAvantPoste extends Scene {

    /**
     *  Constante definissant le style permettant de mettre du texte en valeur en le mettant en gras
     *
     * @see VueAjouterAvantPoste#VueAjouterAvantPoste()
     * @see VueAjouterAvantPoste#initialiserVueAjouterAvantPoste(Canvas)
     */
    public static final String STYLE_GRAS = "-fx-font-size: 13; -fx-font-weight: bold;";

    /**
     *  Constante definissant le style des titres de la vue
     *
     * @see VueAjouterAvantPoste#VueAjouterAvantPoste()
     * @see VueAjouterAvantPoste#initialiserVueAjouterAvantPoste(Canvas)
     */
    public static final String STYLE_TITRE = " -fx-font-size: 18; -fx-font-weight: bold; -fx-padding: 15px";

    /**
     *  Constante definissant le style du label des erreurs de la vue
     *
     * @see VueAjouterAvantPoste#VueAjouterAvantPoste()
     * @see VueAjouterAvantPoste#initialiserVueAjouterAvantPoste(Canvas)
     */
    public static final String STYLE_ERREUR = "-fx-text-fill: red; -fx-font-size: 13; -fx-font-weight: bold";

    /**
     *  Constante definissant le style des boutons de la vue
     *
     * @see VueAjouterAvantPoste#VueAjouterAvantPoste()
     * @see VueAjouterAvantPoste#initialiserVueAjouterAvantPoste(Canvas)
     */
    public static final String STYLE_BOUTONS = "-fx-background-color: #25467F; -fx-text-fill: white; -fx-font-size: 12; -fx-font-weight: bold;-fx-min-width: 80px";

    /**
     * Le controleur de l'application, permet a la vue d'interagir avec les modeles ou avec le navigateur des vues
     *
     * @see VueAjouterAvantPoste#VueAjouterAvantPoste()
     * @see VueAjouterAvantPoste#initialiserVueAjouterAvantPoste(Canvas)
     */
    private controler.ControleurPrincipal controleur = null;

    /**
     * La grille sur laquelle sont ajoutes les differents elements de la vue
     *
     * @see VueAjouterAvantPoste#VueAjouterAvantPoste()
     * @see VueAjouterAvantPoste#initialiserVueAjouterAvantPoste(Canvas)
     */
    protected GridPane grillePrincipale;
    protected GridPane grilleForm;
    protected HBox hBoxBoutons;

    /**
     * Canvas affichant la Carte de la planete, sur laquelle l'avant-poste sera positionne
     *
     * @see VueAjouterAvantPoste#VueAjouterAvantPoste()
     * @see VueAjouterAvantPoste#initialiserVueAjouterAvantPoste(Canvas)
     */
    private Canvas canvasCoords;

    /**
     * Label titre affichant le titre "nom"
     *
     * @see VueAjouterAvantPoste#VueAjouterAvantPoste()
     * @see VueAjouterAvantPoste#initialiserVueAjouterAvantPoste(Canvas)
     */
    private Label labelNom;

    /**
     * Label titre affichant le titre "X"
     *
     * @see VueAjouterAvantPoste#VueAjouterAvantPoste()
     * @see VueAjouterAvantPoste#initialiserVueAjouterAvantPoste(Canvas)
     */
    private Label labelX;

    /**
     * Label titre affichant le titre "Y"
     *
     * @see VueAjouterAvantPoste#VueAjouterAvantPoste()
     * @see VueAjouterAvantPoste#initialiserVueAjouterAvantPoste(Canvas)
     */
    private Label labelY;

    /**
     * Label titre affichant le titre "Z"
     *
     * @see VueAjouterAvantPoste#VueAjouterAvantPoste()
     * @see VueAjouterAvantPoste#initialiserVueAjouterAvantPoste(Canvas)
     */
    private Label labelZ;

    /**
     * Label affichant les messages d'erreurs
     *
     * @see VueAjouterAvantPoste#VueAjouterAvantPoste()
     * @see VueAjouterAvantPoste#initialiserVueAjouterAvantPoste(Canvas)
     */
    private Label labelErreurs;

    /**
     * TextField permettant d'entrer le nom de l'avant-poste
     *
     * @see VueAjouterAvantPoste#VueAjouterAvantPoste()
     * @see VueAjouterAvantPoste#initialiserVueAjouterAvantPoste(Canvas)
     */
    private TextField textFieldNomAvantPoste;

    /**
     * Affiche la longitude de l'avant-poste
     *
     * @see VueAjouterAvantPoste#VueAjouterAvantPoste()
     * @see VueAjouterAvantPoste#initialiserVueAjouterAvantPoste(Canvas)
     */
    private Label labelXAvPoste;

    /**
     * Affiche la latitude de l'avant-poste
     *
     * @see VueAjouterAvantPoste#VueAjouterAvantPoste()
     * @see VueAjouterAvantPoste#initialiserVueAjouterAvantPoste(Canvas)
     */
    private Label labelYAvPoste;

    /**
     * Affiche la l'altitude de l'avant-poste
     *
     * @see VueAjouterAvantPoste#VueAjouterAvantPoste()
     * @see VueAjouterAvantPoste#initialiserVueAjouterAvantPoste(Canvas)
     */
    private Label labelZAvPoste;


    /**
     * Bouton permettant de retourner au MenuAvantPoste
     *
     * @see VueAjouterAvantPoste#VueAjouterAvantPoste()
     * @see VueAjouterAvantPoste#initialiserVueAjouterAvantPoste(Canvas)
     * @see VueAvantPoste
     */
    private Button btnRetourMenuAvantPoste;

    /**
     * Bouton permettant d'ajouter le nouvel avant-poste
     *
     * @see VueAjouterAvantPoste#VueAjouterAvantPoste()
     * @see VueAjouterAvantPoste#initialiserVueAjouterAvantPoste(Canvas)
     */
    private Button btnAjouterAvPoste;

    /**
     * Nom de l'avant-poste, entre par le joueur
     *
     * @see VueAjouterAvantPoste#VueAjouterAvantPoste()
     * @see VueAjouterAvantPoste#initialiserVueAjouterAvantPoste(Canvas)
     */
    private String nomAvanPoste;

    /**
     * Longitude de l'avant-poste
     *
     * @see VueAjouterAvantPoste#VueAjouterAvantPoste()
     * @see VueAjouterAvantPoste#initialiserVueAjouterAvantPoste(Canvas)
     */
    private double xAvanPoste;

    /**
     * latitutde de l'avant-poste
     *
     * @see VueAjouterAvantPoste#VueAjouterAvantPoste()
     * @see VueAjouterAvantPoste#initialiserVueAjouterAvantPoste(Canvas)
     */
    private double yAvanPoste;

    /**
     * l'altitude de l'avant-poste
     *
     * @see VueAjouterAvantPoste#VueAjouterAvantPoste()
     * @see VueAjouterAvantPoste#initialiserVueAjouterAvantPoste(Canvas)
     */
    private double zAvanPoste;

    /**
     *  Constructeur de VueAjouterAvantPoste
     *  Cree les differents labels, layouts et boutons de la vue
     *
     * @see VueAjouterAvantPoste#btnRetourMenuAvantPoste
     * @see VueAjouterAvantPoste#btnAjouterAvPoste
     * @see VueAjouterAvantPoste#canvasCoords
     * @see VueAjouterAvantPoste#labelNom
     * @see VueAjouterAvantPoste#labelX
     * @see VueAjouterAvantPoste#labelY
     * @see VueAjouterAvantPoste#labelZ
     * @see VueAjouterAvantPoste#labelErreurs
     * @see VueAjouterAvantPoste#grillePrincipale
     * @see VueAjouterAvantPoste#grilleForm
     * @see VueAjouterAvantPoste#hBoxBoutons
     *
     */
    public VueAjouterAvantPoste() {
        super(new GridPane(), 500,400);

        this.btnRetourMenuAvantPoste = new Button("Annuler");
        this.btnAjouterAvPoste = new Button("Ajouter");

        this.canvasCoords = new Canvas(250,250);

        this.labelNom = new Label("Nom : ");
        this.labelX = new Label("X : ");
        this.labelY = new Label("Y : ");
        this.labelZ = new Label("Z : ");
        this.labelErreurs = new Label("");

        this.grillePrincipale = (GridPane) this.getRoot();
        this.grilleForm = new GridPane();
        this.hBoxBoutons = new HBox();
    }

    /**
     *  Initialise les differents labels, boutons et layouts de la vue,
     *  remplace le Canvas de la vue par le Canvas de la Carte, pour pouvoir y positionner l'avant-poste en cliquant dessus
     *
     * @param carte Canvas de la Carte de la planete, permet de positionner l'avant-poste
     *
     * @see VueAjouterAvantPoste#btnRetourMenuAvantPoste
     * @see VueAjouterAvantPoste#btnAjouterAvPoste
     * @see VueAjouterAvantPoste#canvasCoords
     * @see VueAjouterAvantPoste#labelNom
     * @see VueAjouterAvantPoste#labelX
     * @see VueAjouterAvantPoste#labelY
     * @see VueAjouterAvantPoste#labelZ
     * @see VueAjouterAvantPoste#labelErreurs
     * @see VueAjouterAvantPoste#grillePrincipale
     * @see VueAjouterAvantPoste#grilleForm
     * @see VueAjouterAvantPoste#hBoxBoutons
     *
     */
    public void initialiserVueAjouterAvantPoste(Canvas carte) {
        this.grillePrincipale.getChildren().clear();
        this.grilleForm.getChildren().clear();
        this.hBoxBoutons.getChildren().clear();

        this.labelNom.setStyle(STYLE_GRAS);
        this.labelX.setStyle(STYLE_GRAS);
        this.labelY.setStyle(STYLE_GRAS);
        this.labelZ.setStyle(STYLE_GRAS);
        this.labelErreurs.setStyle(STYLE_ERREUR);

        this.textFieldNomAvantPoste = new TextField();
        this.labelXAvPoste = new Label();
        this.labelYAvPoste = new Label();
        this.labelZAvPoste = new Label();

        this.canvasCoords = carte;
        /*
            Cet EventHandler permet de dessiner la position du nouvel avant-poste sur la carte (si les coordonnees sont valides),
            pour que le joueur puisse visualiser la position du futur avant-poste
        */
        this.canvasCoords.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println(event.getX()+" "+event.getY()+" "+event.getZ());
                dessineAvPoste(event.getX(), event.getY());
            }
        });

        this.labelXAvPoste.setText("");
        this.labelYAvPoste.setText("");
        this.labelZAvPoste.setText("");

        grilleForm.add(this.labelNom,0,0);
        grilleForm.add(this.textFieldNomAvantPoste,1,0);

        grilleForm.add(this.labelX,0,1);
        grilleForm.add(this.labelXAvPoste,1,1);

        grilleForm.add(this.labelY,0,2);
        grilleForm.add(this.labelYAvPoste,1,2);

        grilleForm.add(this.labelZ,0,3);
        grilleForm.add(this.labelZAvPoste,1,3);
        grilleForm.add(this.labelErreurs, 0, 4);

        //retour a l'avant-poste
        btnRetourMenuAvantPoste.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.notifierNaviguerMenuPopulation();
            }
        });

        //verifie les donnees et ajoute l'avant-poste si il n'y a pas d'erreurs
        btnAjouterAvPoste.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                validerDonnees();
            }
        });

        this.btnAjouterAvPoste.setStyle(STYLE_BOUTONS);
        this.btnRetourMenuAvantPoste.setStyle(STYLE_BOUTONS);

        hBoxBoutons.getChildren().addAll(this.btnAjouterAvPoste, this.btnRetourMenuAvantPoste);
        hBoxBoutons.setPadding(new Insets(7,0,3,0));
        hBoxBoutons.setSpacing(15);

        grillePrincipale.add(this.grilleForm, 0, 0);
        grillePrincipale.add(this.canvasCoords, 0, 1);
        grillePrincipale.add(this.hBoxBoutons, 0, 2);
    }

    /**
     *  Verifier les coordonnees de la position choisie par le joueur,
     *  si les coordonnees sont valides, les coordonnees seront affiches sur des labels,
     *  et un point sera dessine a ces coordonnees
     *
     * @param x longitude de la position choisie par le joueur
     * @param y latitude de la position choisie par le joueur
     *
     */
    private void dessineAvPoste(double x, double y){
        Random random = new Random();
        //genere une altitude aleatoire entre -1 et 50
        double z = -1 + (50 - (-1)) * random.nextDouble();
        labelXAvPoste.setText("");
        labelYAvPoste.setText("");
        labelZAvPoste.setText("");
        labelErreurs.setText("");
        System.out.println(x+" "+y);
        if (this.controleur.verifierCoordonnees(x, y)){
            labelXAvPoste.setText(Double.toString(x));
            labelYAvPoste.setText(Double.toString(y));
            labelZAvPoste.setText(Double.toString(z));
        }else{
            labelErreurs.setText("Position trop proche d'un avant-poste ou d'une ville");
        }
    }

    /**
     *  Verifie que les donnees entrees sont valides
     *  puis appelle la fonction du controleur permettant d'ajouter un avant-poste si il n'y a aucune erreur
     *
     * @see VueAjouterAvantPoste#VueAjouterAvantPoste()
     * @see ControleurPrincipal#notifierAjouterAvantPoste() (int)
     *
     */
    private void validerDonnees() {
        boolean erreur = false;
        String messageErreurs = "";
        //verification du nom
        if (textFieldNomAvantPoste.getText().isEmpty()){
            erreur = true;
            messageErreurs+="Veuillez entrer un nom";
            labelErreurs.setText(messageErreurs);
        }

        try {
            nomAvanPoste = textFieldNomAvantPoste.getText();
        }catch (Exception e){
            System.out.println(e.getMessage());
            erreur = true;
            messageErreurs+="Veuillez entrer un nom";
            labelErreurs.setText(messageErreurs);
        }

        //verification des coordonnees
        try {
            xAvanPoste = Double.parseDouble(labelXAvPoste.getText());
            yAvanPoste = Double.parseDouble(labelYAvPoste.getText());
            zAvanPoste = Double.parseDouble(labelZAvPoste.getText());
        }catch (Exception e){
            System.out.println(e.getMessage());
            messageErreurs+=" Veuillez entrer des coordonnees valides";
            labelErreurs.setText(messageErreurs);
            erreur = true;
        }

        if (!erreur){
            System.out.println("Ajout avant-poste");
            this.controleur.notifierAjouterAvantPoste();
        }
    }

    /**
     *  Cree un avant-poste avec les donnees entrees et le retourne
     *
     * @returnun avant-poste cree avec les donnees entrees les donnees entrees par le joueur
     *
     * @see VueAjouterAvantPoste#VueAjouterAvantPoste()
     *
     */
    public AvantPoste getAvantPoste()
    {
        AvantPoste avantPoste = new AvantPoste(nomAvanPoste, new Coordonnee(xAvanPoste, yAvanPoste, zAvanPoste), new ArrayList<Mine>());
        return avantPoste;
    }

    /**
     *  Permet d'affecter un controleur a la vue
     *
     * @param controleur
     *
     * @see ControleurPrincipal
     * @see VueAjouterAvantPoste#controleur
     */
    public void setControleur(controler.ControleurPrincipal controleur) {
        this.controleur = controleur;
    }
}
