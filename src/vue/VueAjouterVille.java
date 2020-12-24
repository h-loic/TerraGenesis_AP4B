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
import modele.Coordonnee;
import modele.Ressource;
import modele.Ville;

import java.util.ArrayList;
import java.util.Random;

public class VueAjouterVille extends Scene {

    private controler.ControleurPrincipal controleur = null;

    protected GridPane grillePrincipale;
    protected GridPane grilleForm;
    protected GridPane grilleBoutons;

    private Label labelNom;
    private Label labelX;
    private Label labelY;
    private Label labelZ;
    private Label labelErreurs;

    private TextField textFieldNomVille;

    private Canvas canvasCoords;
    private GraphicsContext gcCanva;

    private Label labelXville;
    private Label labelYville;
    private Label labelZville;

    private Button btnRetourMenuPopulation;
    private Button btnAjouterVille;

    private String nomVille;
    private double xVille;
    private double yVille;
    private double zVille;

    public VueAjouterVille() {
        super(new GridPane(), 500,400);

        this.btnRetourMenuPopulation = new Button("Annuler");
        this.btnAjouterVille = new Button("Ajouter");

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



    public void initialiserVueAjouterVille(Canvas carte) {
        this.grillePrincipale.getChildren().clear();
        this.grilleForm.getChildren().clear();
        this.grilleBoutons.getChildren().clear();
        this.canvasCoords = carte;
        this.canvasCoords.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println(event.getX()+" "+event.getY()+" "+event.getZ());
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
        double z = -1 + (50 - (-1)) * random.nextDouble();
        labelXville.setText("");
        labelYville.setText("");
        labelZville.setText("");
        labelErreurs.setText("");
        System.out.println(x+" "+y);
        if (this.controleur.verifierCoordonnees(x, y)){
            labelXville.setText(Double.toString(x));
            labelYville.setText(Double.toString(y));
            labelZville.setText(Double.toString(z));
        }else{
            labelErreurs.setText("Position trop proche d'un avant-poste ou d'une ville");
        }
    }

    private void validerDonnees() {
        boolean erreur = false;
        String messageErreurs = "";
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
            xVille = Double.parseDouble(labelXville.getText());
            yVille = Double.parseDouble(labelYville.getText());
            zVille = Double.parseDouble(labelZville.getText());
        }catch (Exception e){
            System.out.println(e.getMessage());
            messageErreurs+=" Veuillez entrer des coordonnées valides";
            labelErreurs.setText(messageErreurs);
            erreur = true;
        }

        if (!erreur){
            System.out.println("c'est bon");
            this.controleur.notifierAjouterVille();
        }
    }

    public Ville getVille()
    {
        Ville ville = new Ville(nomVille,  new Coordonnee(xVille, yVille, zVille));
        return ville;
    }

    public void setControleur(controler.ControleurPrincipal controleur) {
        this.controleur = controleur;
    }
}
