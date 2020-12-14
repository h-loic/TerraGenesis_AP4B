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
import javafx.scene.paint.Color;
import modele.AvantPoste;
import modele.Coordonnee;
import modele.Mine;

import java.util.ArrayList;
import java.util.Random;


public class VueAjouterAvantPoste extends Scene {

    private controler.ControleurPrincipal controleur = null;

    protected GridPane grillePrincipale;
    protected GridPane grilleForm;
    protected GridPane grilleBoutons;

    private final Canvas canvasCoords;
    private GraphicsContext gcCanva;

    private Label labelNom;
    private Label labelX;
    private Label labelY;
    private Label labelZ;
    private Label labelErreurs;

    private TextField textFieldNomAvantPoste;

    private Label labelXAvPoste;
    private Label labelYAvPoste;
    private Label labelZAvPoste;

    private Button btnRetourMenuAvantPoste;
    private Button btnAjouterAvPoste;

    private String nomAvanPoste;
    private double xAvanPoste;
    private double yAvanPoste;
    private double zAvanPoste;

    public VueAjouterAvantPoste() {
        super(new GridPane(), 400,400);

        this.btnRetourMenuAvantPoste = new Button("Annuler");
        this.btnAjouterAvPoste = new Button("Ajouter");

        this.canvasCoords = new Canvas(250,250);
        this.gcCanva = this.canvasCoords.getGraphicsContext2D();

        this.labelNom = new Label("Nom : ");
        this.labelX = new Label("X : ");
        this.labelY = new Label("Y : ");
        this.labelZ = new Label("Z : ");
        this.labelErreurs = new Label("");

        this.grillePrincipale = (GridPane) this.getRoot();
        this.grilleForm = new GridPane();
        this.grilleBoutons = new GridPane();
    }

    private void initCanvas(ArrayList<AvantPoste> mines){
        gcCanva.clearRect(0, 0, canvasCoords.getWidth(), canvasCoords.getHeight());
        gcCanva.setFill(Color.ORANGERED);
        gcCanva.fillRect(0, 0, canvasCoords.getWidth(), canvasCoords.getHeight());
        gcCanva.setFill(Color.GRAY);
        //dessin des mines de l'avant poste
        for (AvantPoste avPoste : mines){
            dessinePoint(avPoste.getCoordonnee().getX(), avPoste.getCoordonnee().getY());
        }
        gcCanva.setFill(Color.BLUE);
    }

    private void dessinePoint(double x, double y){
        Random random = new Random();
        double z = -1 + (50 - (-1)) * random.nextDouble();
        gcCanva.fillOval(x, y, 10,10);
        labelXAvPoste.setText(Double.toString(x));
        labelYAvPoste.setText(Double.toString(y));
        labelZAvPoste.setText(Double.toString(z));
    }

    public void initialiserVueAjouterAvantPoste(ArrayList<AvantPoste> avantPostes) {
        this.grillePrincipale.getChildren().clear();
        this.grilleForm.getChildren().clear();
        this.grilleBoutons.getChildren().clear();

        this.textFieldNomAvantPoste = new TextField();
        this.labelXAvPoste = new Label();
        this.labelYAvPoste = new Label();
        this.labelZAvPoste = new Label();

        this.initCanvas(avantPostes);
        this.canvasCoords.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println(event.getX()+" "+event.getY()+" "+event.getZ());
                initCanvas(avantPostes);
                dessinePoint(event.getX(), event.getY());
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

        btnRetourMenuAvantPoste.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.notifierNaviguerMenuPopulation();
            }
        });

        btnAjouterAvPoste.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                validerDonnees();
            }
        });

        grilleBoutons.add(this.btnAjouterAvPoste, 0, 0);
        grilleBoutons.add(this.btnRetourMenuAvantPoste, 1, 0);

        grillePrincipale.add(this.grilleForm, 0, 0);
        grillePrincipale.add(this.canvasCoords, 0, 1);
        grillePrincipale.add(this.grilleBoutons, 0, 2);
    }

    private void validerDonnees() {
        boolean erreur = false;
        String messageErreurs = "";
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

        try {
            xAvanPoste = Double.parseDouble(labelXAvPoste.getText());
            yAvanPoste = Double.parseDouble(labelYAvPoste.getText());
            zAvanPoste = Double.parseDouble(labelZAvPoste.getText());
        }catch (Exception e){
            System.out.println(e.getMessage());
            messageErreurs+=" Veuillez entrer des coordonnées valides";
            labelErreurs.setText(messageErreurs);
            erreur = true;
        }

        if (!erreur){
            System.out.println("c'est bon");
            this.controleur.notifierAjouterAvantPoste();
        }
    }

    public AvantPoste getAvantPoste()
    {
        AvantPoste avantPoste = new AvantPoste(nomAvanPoste, new Coordonnee(xAvanPoste, yAvanPoste, zAvanPoste), new ArrayList<Mine>());
        return avantPoste;
    }

    public void setControleur(controler.ControleurPrincipal controleur) {
        this.controleur = controleur;
    }
}
