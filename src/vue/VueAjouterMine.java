package vue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import modele.Mine;
import modele.Coordonnee;
import modele.Ressource;

import java.util.ArrayList;


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
    private Label labelRessource;
    private Label labelErreurs;

    private TextField textFieldX;
    private TextField textFieldY;
    private TextField textFieldZ;

    private Button btnRetourMenuAvantPoste;
    private Button btnAjouterMine;

    private ComboBox comboBoxRessources;


    private String nomMine;
    private double xMine;
    private double yMine;
    private double zMine;

    public VueAjouterMine() {
        super(new GridPane(), 400,400);

        this.btnRetourMenuAvantPoste = new Button("Annuler");
        this.btnAjouterMine = new Button("Ajouter");

        this.labelX = new Label("X : ");
        this.labelY = new Label("Y : ");
        this.labelZ = new Label("Z : ");
        this.labelRessource = new Label("Resource : ");
        this.labelErreurs = new Label("");

        ObservableList<String> options =
                FXCollections.observableArrayList(
                        Ressource.CARBONE.getSymbole(),
                        Ressource.FER.getSymbole(),
                        Ressource.ARGENT.getSymbole(),
                        Ressource.PALLADIUM.getSymbole(),
                        Ressource.RHODIUM.getSymbole()
                );
        this.comboBoxRessources = new ComboBox(options);
        this.comboBoxRessources.setValue(Ressource.CARBONE.getSymbole());

        this.grillePrincipale = (GridPane) this.getRoot();
        this.grilleForm = new GridPane();
        this.grilleBoutons = new GridPane();
    }

    public void initialiserVueAjouterMine(int idAvantPoste) {
        this.idAvantPoste = idAvantPoste;
        this.grillePrincipale.getChildren().clear();
        this.grilleForm.getChildren().clear();
        this.grilleBoutons.getChildren().clear();

        this.textFieldX = new TextField();
        this.textFieldY = new TextField();
        this.textFieldZ = new TextField();

        grilleForm.add(this.labelX,0,1);
        grilleForm.add(this.textFieldX,1,1);

        grilleForm.add(this.labelY,0,2);
        grilleForm.add(this.textFieldY,1,2);

        grilleForm.add(this.labelZ,0,3);
        grilleForm.add(this.textFieldZ,1,3);

        grilleForm.add(this.labelRessource, 0, 4);
        grilleForm.add(this.comboBoxRessources, 1, 4);

        grilleForm.add(this.labelErreurs, 0, 5);

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
        grillePrincipale.add(this.grilleBoutons, 0, 1);
    }

    private void validerDonnees() {
        boolean erreur = false;
        String messageErreurs = "";

        try {
            xMine = Double.parseDouble(textFieldX.getText());
            yMine = Double.parseDouble(textFieldY.getText());
            zMine = Double.parseDouble(textFieldZ.getText());
        }catch (Exception e){
            System.out.println(e.getMessage());
            messageErreurs+=" Veuillez entrer des coordonnées valides";
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
        Mine mine = new Mine(Ressource.ARGENT, 265, new Coordonnee(xMine, yMine, zMine));
        return mine;
    }

    public void setControleur(controler.ControleurPrincipal controleur) {
        this.controleur = controleur;
    }
}
