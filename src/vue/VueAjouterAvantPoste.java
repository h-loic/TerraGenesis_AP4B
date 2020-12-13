package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import modele.AvantPoste;
import modele.Coordonnee;
import modele.Mine;

import java.util.ArrayList;


public class VueAjouterAvantPoste extends Scene {

    private controler.ControleurPrincipal controleur = null;

    protected GridPane grillePrincipale;
    protected GridPane grilleForm;
    protected GridPane grilleBoutons;

    private Label labelNom;
    private Label labelX;
    private Label labelY;
    private Label labelZ;
    private Label labelErreurs;

    private TextField textFieldNom;
    private TextField textFieldX;
    private TextField textFieldY;
    private TextField textFieldZ;

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

        this.labelNom = new Label("Nom : ");
        this.labelX = new Label("X : ");
        this.labelY = new Label("Y : ");
        this.labelZ = new Label("Z : ");
        this.labelErreurs = new Label("");

        this.grillePrincipale = (GridPane) this.getRoot();
        this.grilleForm = new GridPane();
        this.grilleBoutons = new GridPane();
    }

    public void initialiserVueAjouterAvantPoste() {
        this.grillePrincipale.getChildren().clear();
        this.grilleForm.getChildren().clear();
        this.grilleBoutons.getChildren().clear();

        this.textFieldNom = new TextField();
        this.textFieldX = new TextField();
        this.textFieldY = new TextField();
        this.textFieldZ = new TextField();

        grilleForm.add(this.labelNom,0,0);
        grilleForm.add(this.textFieldNom,1,0);

        grilleForm.add(this.labelX,0,1);
        grilleForm.add(this.textFieldX,1,1);

        grilleForm.add(this.labelY,0,2);
        grilleForm.add(this.textFieldY,1,2);

        grilleForm.add(this.labelZ,0,3);
        grilleForm.add(this.textFieldZ,1,3);
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
        grillePrincipale.add(this.grilleBoutons, 0, 1);
    }

    private void validerDonnees() {
        boolean erreur = false;
        String messageErreurs = "";
        if (textFieldNom.getText().isEmpty()){
            erreur = true;
            messageErreurs+="Veuillez entrer un nom";
            labelErreurs.setText(messageErreurs);
        }

        try {
            nomAvanPoste = textFieldNom.getText();
        }catch (Exception e){
            System.out.println(e.getMessage());
            erreur = true;
            messageErreurs+="Veuillez entrer un nom";
            labelErreurs.setText(messageErreurs);
        }

        try {
            xAvanPoste = Double.parseDouble(textFieldX.getText());
            yAvanPoste = Double.parseDouble(textFieldY.getText());
            zAvanPoste = Double.parseDouble(textFieldZ.getText());
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
