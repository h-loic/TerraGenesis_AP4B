package vue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import modele.Mine;
import modele.Coordonnee;
import modele.Ressource;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Random;


public class VueAjouterMine extends Scene {

    private controler.ControleurPrincipal controleur = null;

    protected GridPane grillePrincipale;
    protected GridPane grilleForm;
    protected GridPane grilleBoutons;

    private int idAvantPoste;

    private Label labelNom;
    private Label labelX;
    private Label labelY;
    private Label labelZ;
    private Label labelRendement;
    private Label labelRessource;
    private Label labelErreurs;

    private final Canvas canvasCoords;
    private GraphicsContext gcCanva;

    private Label labelXmine;
    private Label labelYmine;
    private Label labelZmine;
    private Label labelRendementMine;

    private Button btnRetourMenuAvantPoste;
    private Button btnAjouterMine;

    private ComboBox comboBoxRessources;

    private double xMine;
    private double yMine;
    private double zMine;
    private double rendementMine;
    private Ressource ressourceMine;

    public VueAjouterMine() {
        super(new GridPane(), 400,400);

        this.btnRetourMenuAvantPoste = new Button("Annuler");
        this.btnAjouterMine = new Button("Ajouter");

        this.labelX = new Label("X : ");
        this.labelY = new Label("Y : ");
        this.labelZ = new Label("Z : ");
        this.labelRendement = new Label("Rendement (kg/min) : ");
        this.labelRessource = new Label("Resource : ");
        this.labelErreurs = new Label("");

        this.canvasCoords = new Canvas(250,250);

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
        this.grilleBoutons = new GridPane();
    }

    private void initCanvas(ArrayList<Mine> mines){
        gcCanva.clearRect(0, 0, canvasCoords.getWidth(), canvasCoords.getHeight());
        gcCanva.setFill(Color.ORANGERED);
        gcCanva.fillRect(0, 0, canvasCoords.getWidth(), canvasCoords.getHeight());
        gcCanva.setFill(Color.GRAY);
        //dessin des mines de l'avant poste
        for (Mine mine : mines){
            dessinePoint(mine.getCoordonnee().getX(), mine.getCoordonnee().getY());
        }
        gcCanva.setFill(Color.BLUE);
    }

    private void dessinePoint(double x, double y){
        gcCanva.fillOval(x, y, 10,10);
    }

    private void dessineMine(double x, double y, ArrayList<Mine> mines){
        Random random = new Random();
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMinimumFractionDigits(2);
        NumberFormat formatter = new DecimalFormat("#0.00");
        System.out.println(formatter.format(4.0));
        double z = ( -1 + (50 - (-1)) * random.nextDouble());
        double rendement = (100 + (450 - 100) * random.nextDouble());
        labelXmine.setText("");
        labelYmine.setText("");
        labelZmine.setText("");
        labelRendementMine.setText("");
        labelErreurs.setText("");
        System.out.println(x+" "+y);
        for (Mine mine : mines){
            if (Math.abs((mine.getCoordonnee().getX()-x))<=30 && Math.abs((mine.getCoordonnee().getY()-y))<=30){
                System.out.println("trop proches");
                labelXmine.setText("");
                labelYmine.setText("");
                labelZmine.setText("");
                labelRendementMine.setText("");
                labelErreurs.setText("Position trop proche d'une autre mine");
                return;
            }
        }

        gcCanva.fillOval(x, y, 10,10);
        labelXmine.setText(Double.toString(x));
        labelYmine.setText(Double.toString(y));
        labelZmine.setText(Double.toString(z));
        labelRendementMine.setText(Double.toString(rendement));
    }

    public void initialiserVueAjouterMine(int idAvantPoste, ArrayList<Mine> mines) {
        this.idAvantPoste = idAvantPoste;
        this.grillePrincipale.getChildren().clear();
        this.grilleForm.getChildren().clear();
        this.grilleBoutons.getChildren().clear();

        this.gcCanva = this.canvasCoords.getGraphicsContext2D();
        this.initCanvas(mines);
        this.canvasCoords.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println(event.getX()+" "+event.getY()+" "+event.getZ());
                initCanvas(mines);
                dessineMine(event.getX(), event.getY(), mines);
            }
        });

        this.labelXmine = new Label();
        this.labelYmine = new Label();
        this.labelZmine = new Label();
        this.labelRendementMine = new Label();


        this.comboBoxRessources.setValue(Ressource.CARBONE.getSymbole());

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

        grilleBoutons.add(this.btnAjouterMine, 0, 0);
        grilleBoutons.add(this.btnRetourMenuAvantPoste, 1, 0);

        grillePrincipale.add(this.grilleForm, 0, 0);
        grillePrincipale.add(this.canvasCoords, 0, 1);
        grillePrincipale.add(this.grilleBoutons, 0, 2);
    }

    private void validerDonnees() {
        boolean erreur = false;
        String messageErreurs = "";

        try {
            xMine = Double.parseDouble(labelXmine.getText());
            yMine = Double.parseDouble(labelYmine.getText());
            zMine = Double.parseDouble(labelZmine.getText());
            rendementMine = Double.parseDouble(labelRendementMine.getText());
        }catch (Exception e){
            System.out.println(e.getMessage());
            messageErreurs+=" Veuillez entrer des coordonnées valides";
            labelErreurs.setText(messageErreurs);
            erreur = true;
        }

        try{
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

        if (!erreur){
            System.out.println("c'est bon");
            this.controleur.notifierAjouterMine(idAvantPoste);
        }
    }

    public Mine getMine()
    {
        Mine mine = new Mine(ressourceMine, rendementMine, new Coordonnee(xMine, yMine, zMine));
        System.out.println(comboBoxRessources.getValue());
        return mine;
    }

    public void setControleur(controler.ControleurPrincipal controleur) {
        this.controleur = controleur;
    }
}
