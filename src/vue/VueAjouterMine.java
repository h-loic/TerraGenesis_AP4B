package vue;

import controler.ControleurPrincipal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import modele.AvantPoste;
import modele.Mine;
import modele.Coordonnee;
import modele.Ressource;

import java.util.ArrayList;
import java.util.Random;

/**
 * <b>
 *     Vue Permettant d'ajouter une mine à un avant-poste
 * </p>
 *
 * @see modele.Mine
 * @author Zapolatero - Louis Pascuzzi
 * */

public class VueAjouterMine extends Scene {

    /**
     *  Constante definissant le style permettant de mettre du texte en valeur en le mettant en gras
     *
     * @see VueAjouterMine#VueAjouterMine()
     * @see VueAjouterMine#initialiserVueAjouterMine(int, ArrayList)
     */
    public static final String STYLE_GRAS = "-fx-font-size: 13; -fx-font-weight: bold;";

    /**
     *  Constante definissant le style des titres de la vue
     *
     * @see VueAjouterMine#VueAjouterMine()
     * @see VueAjouterMine#initialiserVueAjouterMine(int, ArrayList)
     */
    public static final String STYLE_TITRE = " -fx-font-size: 18; -fx-font-weight: bold; -fx-padding: 15px";

    /**
     *  Constante definissant le style du label des erreurs de la vue
     *
     * @see VueAjouterMine#VueAjouterMine()
     * @see VueAjouterMine#initialiserVueAjouterMine(int, ArrayList)
     */
    public static final String STYLE_ERREUR = "-fx-text-fill: red; -fx-font-size: 13; -fx-font-weight: bold";

    /**
     *  Constante definissant le style des boutons de la vue
     *
     * @see VueAjouterMine#VueAjouterMine()
     * @see VueAjouterMine#initialiserVueAjouterMine(int, ArrayList)
     */
    public static final String STYLE_BOUTONS = "-fx-background-color: #25467F; -fx-text-fill: white; -fx-font-size: 12; -fx-font-weight: bold;-fx-min-width: 80px";

    /**
     * constante indiquant la distance minimale entre deux mines
     *
     * @see VueAjouterMine#VueAjouterMine()
     * @see VueAjouterMine#initialiserVueAjouterMine(int, ArrayList)
     */
    public static final int DISTANCE_MINI = 30;

    /**
     * Le controleur de l'application, permet à la vue d'interagir avec les modeles ou avec le navigateur des vues
     *
     * @see VueAjouterMine#VueAjouterMine()
     * @see VueAjouterMine#initialiserVueAjouterMine(int, ArrayList)
     */
    private controler.ControleurPrincipal controleur = null;

    /**
     * La grille sur laquelle sont ajoutes les differents elements de la vue
     *
     * @see VueAjouterMine#VueAjouterMine()
     * @see VueAjouterMine#initialiserVueAjouterMine(int, ArrayList)
     */
    protected GridPane grillePrincipale;
    protected GridPane grilleForm;
    protected HBox hboxBoutons;

    /**
     * id de l'avant-poste auquel on ajoute la mine
     *
     * @see VueAjouterMine#VueAjouterMine()
     * @see VueAjouterMine#initialiserVueAjouterMine(int, ArrayList)
     */
    private int idAvantPoste;
    private Label labelX;
    private Label labelY;
    private Label labelZ;
    private Label labelRendement;
    private Label labelRessource;

    /**
     * Label sur lequel sont affiches les messages d'erreur
     *
     * @see VueAjouterMine#VueAjouterMine()
     * @see VueAjouterMine#initialiserVueAjouterMine(int, ArrayList)
     */
    private Label labelErreurs;


    /**
     * Canvas sur lequel sont affichees les mines dejà construites dans l'avant-poste
     * et permettant au joueur de positionner la nouvelle mine
     *
     * @see VueAjouterMine#VueAjouterMine()
     * @see VueAjouterMine#initialiserVueAjouterMine(int, ArrayList)
     */
    private final Canvas canvasCoords;

    /**
     * Context graphique du canvas
     *
     * @see VueAjouterMine#VueAjouterMine()
     * @see VueAjouterMine#initialiserVueAjouterMine(int, ArrayList)
     */
    private GraphicsContext gcCanva;

    /**
     * Label sur lequel est affichee la longitude choisie de la mine
     *
     * @see VueAjouterMine#VueAjouterMine()
     * @see VueAjouterMine#initialiserVueAjouterMine(int, ArrayList)
     */
    private Label labelXmine;

    /**
     * Label sur lequel est affichee la latitude choisie de la mine
     *
     * @see VueAjouterMine#VueAjouterMine()
     * @see VueAjouterMine#initialiserVueAjouterMine(int, ArrayList)
     */
    private Label labelYmine;

    /**
     * Label sur lequel est affichee la l'altitude choisie de la mine
     *
     * @see VueAjouterMine#VueAjouterMine()
     * @see VueAjouterMine#initialiserVueAjouterMine(int, ArrayList)
     */
    private Label labelZmine;

    /**
     * Label sur lequel est affichee le rendement la mine
     *
     * @see VueAjouterMine#VueAjouterMine()
     * @see VueAjouterMine#initialiserVueAjouterMine(int, ArrayList)
     */
    private Label labelRendementMine;

    /**
     * bouton permettant d'annuler l'ajout et de retourner à la page de l'avant-poste
     *
     * @see VueAjouterMine#VueAjouterMine()
     * @see VueAjouterMine#initialiserVueAjouterMine(int, ArrayList)
     * @see VueAvantPoste
     */
    private Button btnRetourMenuAvantPoste;

    /**
     * bouton permettant d'jouter la mine
     *
     * @see VueAjouterMine#VueAjouterMine()
     * @see VueAjouterMine#initialiserVueAjouterMine(int, ArrayList)
     *
     */
    private Button btnAjouterMine;

    /**
     * Liste deroulante permettant de choisir la ressource puisee par la mine
     *
     * @see VueAjouterMine#VueAjouterMine()
     * @see VueAjouterMine#initialiserVueAjouterMine(int, ArrayList)
     */
    private ComboBox comboBoxRessources;

    /**
     * Longitude de la mine
     *
     * @see VueAjouterMine#VueAjouterMine()
     * @see VueAjouterMine#initialiserVueAjouterMine(int, ArrayList)
     */
    private double xMine;

    /**
     * latitude de la mine
     *
     * @see VueAjouterMine#VueAjouterMine()
     * @see VueAjouterMine#initialiserVueAjouterMine(int, ArrayList)
     */
    private double yMine;

    /**
     * l'altitude de la mine
     *
     * @see VueAjouterMine#VueAjouterMine()
     * @see VueAjouterMine#initialiserVueAjouterMine(int, ArrayList)
     */
    private double zMine;

    /**
     *rendement de la mine
     *
     * @see VueAjouterMine#VueAjouterMine()
     * @see VueAjouterMine#initialiserVueAjouterMine(int, ArrayList)
     */
    private double rendementMine;

    /**
     * ressource puisee par la mine
     *
     * @see VueAjouterMine#VueAjouterMine()
     * @see VueAjouterMine#initialiserVueAjouterMine(int, ArrayList)
     * @see Ressource
     */
    private Ressource ressourceMine;

    /**
     *  Constructeur de VueAjouterMine
     *  Cree les differents labels, layouts et boutons de la vue
     *
     * @see VueAjouterMine#grillePrincipale
     * @see VueAjouterMine#btnRetourMenuAvantPoste
     * @see VueAjouterMine#btnAjouterMine
     * @see VueAjouterMine#canvasCoords
     * @see VueAjouterMine#labelErreurs
     * @see VueAjouterMine#comboBoxRessources
     * @see VueAjouterMine#grillePrincipale
     * @see VueAjouterMine#labelErreurs
     *
     */
    public VueAjouterMine() {
        super(new GridPane(), 550,450);

        this.btnRetourMenuAvantPoste = new Button("Annuler");
        this.btnAjouterMine = new Button("Ajouter");

        this.labelX = new Label("X : ");
        this.labelY = new Label("Y : ");
        this.labelZ = new Label("Z : ");
        this.labelRendement = new Label("Rendement (kg/min) : ");
        this.labelRessource = new Label("Resource : ");
        this.labelErreurs = new Label("");

        this.canvasCoords = new Canvas(250,250);

        //creation des options de la liste des ressources
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        Ressource.CARBONE.getSymbole(),
                        Ressource.FER.getSymbole(),
                        Ressource.ARGENT.getSymbole(),
                        Ressource.PALLADIUM.getSymbole(),
                        Ressource.RHODIUM.getSymbole()
                );
        this.comboBoxRessources = new ComboBox(options);

        this.grillePrincipale = (GridPane) this.getRoot();
        this.grilleForm = new GridPane();
        this.hboxBoutons = new HBox();
    }

    /**
     *  Initialise le canvas sur lequel sont affichees les coordonnees des mines de l'avant-poste auquel
     *  on souhaite ajouter une mine
     *
     * @param mines liste des mines à afficher
     *
     * @see VueAjouterMine#VueAjouterMine()
     * @see VueAjouterMine#canvasCoords
     */
    private void initCanvas(ArrayList<Mine> mines){
        //efface ce qui est dejà dessine sur le canvas
        gcCanva.clearRect(0, 0, canvasCoords.getWidth(), canvasCoords.getHeight());
        gcCanva.setFill(Color.ORANGERED);
        //redessine le fond orange
        gcCanva.fillRect(0, 0, canvasCoords.getWidth(), canvasCoords.getHeight());
        gcCanva.setFill(Color.GRAY);
        //dessin des mines de l'avant poste
        for (Mine mine : mines){
            dessinePoint(mine.getCoordonnee().getX(), mine.getCoordonnee().getY());
        }
        gcCanva.setFill(Color.BLUE);
    }

    /**
     *  dessine un point sur le canvas
     *
     * @see VueAjouterMine#VueAjouterMine()
     * @see VueAjouterMine#canvasCoords
     *
     */
    private void dessinePoint(double x, double y){
        gcCanva.fillOval(x, y, 10,10);
    }

    /**
     *  Sert à positionner la mine sur le canvans : verifie si elle n'est pas trop proche d'une autre mine,
     *  genere une altitude et un rendement aleatoires,
     *  affiche les coordonnees et le rendement de la mine sur des labels
     *
     * @see VueAjouterMine#VueAjouterMine()
     * @see VueAjouterMine#canvasCoords
     *
     */
    private void positionnerMine(double x, double y, ArrayList<Mine> mines){
        Random random = new Random();
        //altitude aleatoire
        double z = ( -1 + (50 - (-1)) * random.nextDouble());
        //rendement aleatoire
        double rendement = (100 + (450 - 100) * random.nextDouble());
        labelXmine.setText("");
        labelYmine.setText("");
        labelZmine.setText("");
        labelRendementMine.setText("");
        labelErreurs.setText("");
        System.out.println(x+" "+y);
        for (Mine mine : mines){
            //verifie si la mine n'est pas trop proche d'une autre
            if (Math.abs((mine.getCoordonnee().getX()-x))<= DISTANCE_MINI && Math.abs((mine.getCoordonnee().getY()-y))<=DISTANCE_MINI){
                System.out.println("trop proches");
                labelXmine.setText("");
                labelYmine.setText("");
                labelZmine.setText("");
                labelRendementMine.setText("");
                labelErreurs.setText("Position trop proche d'une autre mine");
                return;
            }
        }

        //dessine la poisition choisie sur le canvas
        gcCanva.fillOval(x, y, 10,10);
        //affichage des informations de la mine sur les labels
        labelXmine.setText(Double.toString(x));
        labelYmine.setText(Double.toString(y));
        labelZmine.setText(Double.toString(z));
        labelRendementMine.setText(Double.toString(rendement));
    }

    /**
     *  Initialise les differents labels, boutons et layouts de la vue
     * @param idAvantPoste id de l'avant-poste auquel on souhaite ajouter une mine
     * @param mines mines dejà ajoutees dans l'avant-poste
     *
     * @see VueAjouterMine#grillePrincipale
     * @see VueAjouterMine#btnRetourMenuAvantPoste
     * @see VueAjouterMine#btnAjouterMine
     * @see VueAjouterMine#canvasCoords
     * @see VueAjouterMine#labelErreurs
     * @see VueAjouterMine#comboBoxRessources
     * @see VueAjouterMine#grillePrincipale
     * @see VueAjouterMine#labelErreurs
     *
     */
    public void initialiserVueAjouterMine(int idAvantPoste, ArrayList<Mine> mines) {
        this.idAvantPoste = idAvantPoste;
        this.grillePrincipale.getChildren().clear();
        this.grilleForm.getChildren().clear();
        this.hboxBoutons.getChildren().clear();

        this.labelX.setStyle(STYLE_GRAS);
        this.labelY.setStyle(STYLE_GRAS);
        this.labelZ.setStyle(STYLE_GRAS);
        this.labelRendement.setStyle(STYLE_GRAS);
        this.labelRessource.setStyle(STYLE_GRAS);
        this.labelErreurs.setStyle(STYLE_ERREUR);

        this.gcCanva = this.canvasCoords.getGraphicsContext2D();
        this.initCanvas(mines);
        //event handler pour positionner une mine en cliquant sur un point du canvas
        this.canvasCoords.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println(event.getX()+" "+event.getY()+" "+event.getZ());
                initCanvas(mines);
                //event.getX/Y() coordonnees du click sur le canvas
                positionnerMine(event.getX(), event.getY(), mines);
            }
        });

        this.labelXmine = new Label();
        this.labelYmine = new Label();
        this.labelZmine = new Label();

        this.labelRendementMine = new Label();

        //met Carbone comme valeur par defaut dans la liste deroulante
        this.comboBoxRessources.setValue(Ressource.CARBONE.getSymbole());
        this.comboBoxRessources.setStyle(STYLE_BOUTONS);

        grilleForm.add(this.labelX,0,1);
        grilleForm.add(this.labelXmine,1,1);

        grilleForm.add(this.labelY,0,2);
        grilleForm.add(this.labelYmine,1,2);

        grilleForm.add(this.labelZ,0,3);
        grilleForm.add(this.labelZmine,1,3);

        grilleForm.add(this.labelRendement,0,4);
        grilleForm.add(this.labelRendementMine,1,4);

        grilleForm.add(this.labelRessource, 0, 5);
        grilleForm.add(this.comboBoxRessources, 1, 5);

        grilleForm.add(this.labelErreurs, 0, 6);

        btnRetourMenuAvantPoste.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.notifierNaviguerAfficherAvPoste(idAvantPoste);
            }
        });

        btnAjouterMine.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                validerDonnees();
            }
        });
        this.btnAjouterMine.setStyle(STYLE_BOUTONS);
        this.btnRetourMenuAvantPoste.setStyle(STYLE_BOUTONS);
        hboxBoutons.getChildren().addAll(this.btnAjouterMine, this.btnRetourMenuAvantPoste);
        hboxBoutons.setSpacing(15);
        hboxBoutons.setPadding(new Insets(10));

        grillePrincipale.add(this.grilleForm, 0, 0);
        grillePrincipale.add(this.canvasCoords, 0, 1);
        grillePrincipale.add(this.hboxBoutons, 0, 2);
    }

    /**
     *  Verifie que les donnees entrees sont valides
     *  puis appelle la fonction du controleur permettant d'ajouter une mine si il n'y a aucune erreur
     *
     * @see VueAjouterMine#VueAjouterMine()
     * @see ControleurPrincipal#notifierAjouterMine(int)
     *
     */
    private void validerDonnees() {
        boolean erreur = false;
        String messageErreurs = "";

        try {
            //conversion et stockage des donnees ecrites sur les labels
            xMine = Double.parseDouble(labelXmine.getText());
            yMine = Double.parseDouble(labelYmine.getText());
            zMine = Double.parseDouble(labelZmine.getText());
            rendementMine = Double.parseDouble(labelRendementMine.getText());
        }catch (Exception e){
            //exception si la conversion n'a pas fonctionne
            System.out.println(e.getMessage());
            messageErreurs+=" Veuillez entrer des coordonnees valides";
            labelErreurs.setText(messageErreurs);
            erreur = true;
        }

        try{
            //recuperation de la ressource
            String ressource = (String)comboBoxRessources.getValue();
            switch (ressource){
                case "C":
                    this.ressourceMine = Ressource.CARBONE;
                    break;
                case "Fe":
                    this.ressourceMine = Ressource.FER;
                    break;
                case "Ag":
                    this.ressourceMine = Ressource.ARGENT;
                    break;
                case "Pd":
                    this.ressourceMine = Ressource.PALLADIUM;
                    break;
                case "Rh":
                    this.ressourceMine = Ressource.RHODIUM;
                    break;
                default:
                    this.ressourceMine = Ressource.CARBONE;
                    break;
            }
        }catch (Exception e){
            System.out.println(e.getCause());
            messageErreurs+=" Veuillez choisir un type de ressource";
            labelErreurs.setText(messageErreurs);
            erreur = true;
        }
        //si il n'y a pas d'erreurs, ajout de la mine
        if (!erreur){
            this.controleur.notifierAjouterMine(idAvantPoste);
        }
    }

    /**
     *  Cree une mine avec les donnees entrees et la retourne
     *
     * @return mine creee avec les donnees entrees par le joueur
     *
     * @see VueAjouterMine#VueAjouterMine()
     * @see VueAjouterMine#canvasCoords
     *
     */
    public Mine getMine()
    {
        Mine mine = new Mine(ressourceMine, rendementMine, new Coordonnee(xMine, yMine, zMine));
        System.out.println(comboBoxRessources.getValue());
        return mine;
    }

    /**
     *  Permet d'affecter un controleur à la vue
     *
     * @param controleur
     *
     * @see ControleurPrincipal
     * @see VueAjouterMine#controleur
     */
    public void setControleur(controler.ControleurPrincipal controleur) {
        this.controleur = controleur;
    }
}
