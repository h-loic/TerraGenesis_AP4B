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
import javafx.scene.layout.GridPane;
import javafx.scene.media.MediaException;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import modele.Batiment;
import modele.TypeBatiment;
import modele.TypeDonnee;

import java.util.ArrayList;
import java.util.Map;


public class VueAjouterBatiment extends Scene {

    private controler.ControleurPrincipal controleur = null;

    protected GridPane grillePrincipale;
    protected GridPane grilleForm;
    protected GridPane grilleBoutons;

    private int idVille;

    private Label labelTypeBatiment;
    private Label labelErreurs;
    private Label labelPrix;
    private Label labelEffet;
    private TextFlow textFlowEffet;


    private Button btnRetourMenuVille;
    private Button btnAjouterBatiment;

    private ComboBox comboBoxTypeBatiment;

    private TypeBatiment typeBatimentDuBatiment;

    public VueAjouterBatiment() {
        super(new GridPane(), 400,400);

        this.btnRetourMenuVille = new Button("Annuler");
        this.btnAjouterBatiment = new Button("Ajouter");
        this.labelTypeBatiment = new Label("Type de batiment : ");
        this.labelPrix = new Label("Prix :");
        this.labelEffet = new Label("Effets :");
        this.textFlowEffet = new TextFlow();
        this.labelErreurs = new Label("");


        this.grillePrincipale = (GridPane) this.getRoot();
        this.grilleForm = new GridPane();
        this.grilleBoutons = new GridPane();
    }

    public void initialiserVueAjouterBatiment(int idVille, ArrayList<TypeBatiment> typeBatimentDebloque) {
        this.idVille = idVille;
        this.grillePrincipale.getChildren().clear();
        this.grilleForm.getChildren().clear();
        this.grilleBoutons.getChildren().clear();

        labelErreurs.setText("");

        ObservableList<TypeBatiment> options = FXCollections.observableArrayList();
        for (TypeBatiment typeBatiment : typeBatimentDebloque) {
            options.add(typeBatiment);
        }
        this.comboBoxTypeBatiment = new ComboBox(options);

        this.comboBoxTypeBatiment.valueProperty().addListener((obs, oldVal, newVal) -> {
            TypeBatiment typeBatimentSelectionne = (TypeBatiment) newVal;
            labelPrix.setText("Prix : " + typeBatimentSelectionne.getCoutConstructionParDefaut());
            textFlowEffet.getChildren().clear();
            for (Map.Entry effet : typeBatimentSelectionne.getEffetsParDefaut().entrySet()){
                Text text =  new Text("    |> " + effet.getKey() + ": " + effet.getValue() + "\n");
                if ((double) effet.getValue() < 0) text.setFill(Color.BLUE);
                else text.setFill(Color.RED);
                textFlowEffet.getChildren().add(text);
            }

        });

        this.comboBoxTypeBatiment.setValue(typeBatimentDebloque.get(0));

        grilleForm.add(this.labelTypeBatiment,0,0);
        grilleForm.add(this.comboBoxTypeBatiment, 0, 1);
        grilleForm.add(this.labelPrix, 0,2);
        grilleForm.add(this.labelEffet,0,3);
        grilleForm.add(this.textFlowEffet,0,4);
        grilleForm.add(this.labelErreurs, 0, 5);

        btnRetourMenuVille.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.notifierNaviguerAfficherVille(idVille);
            }
        });

        btnAjouterBatiment.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    validerDonnees();
                } catch (Exception e) {
                    labelErreurs.setText(e.getMessage());
                }
            }
        });

        grilleBoutons.add(this.btnAjouterBatiment, 0, 0);
        grilleBoutons.add(this.btnRetourMenuVille, 1, 0);

        grillePrincipale.add(this.grilleForm, 0, 0);
        grillePrincipale.add(this.grilleBoutons, 0, 1);
    }

    private void validerDonnees() throws Exception {
        boolean erreur = false;
        String messageErreurs = "";
        try{
            this.typeBatimentDuBatiment = (TypeBatiment) comboBoxTypeBatiment.getValue();
        }catch (Exception e){
            messageErreurs+=" Veuillez choisir un type de batiment";
            labelErreurs.setText(messageErreurs);
            erreur = true;
        }

        if (!erreur){
            this.controleur.notifierAjouterBatiment(idVille);
        }
    }

    public Batiment getBatiment()
    {
        Batiment batiment = new Batiment(typeBatimentDuBatiment);
        System.out.println(comboBoxTypeBatiment.getValue());
        return batiment;
    }

    public void setControleur(controler.ControleurPrincipal controleur) {
        this.controleur = controleur;
    }
}
