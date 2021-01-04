package vue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import modele.Batiment;
import modele.TypeBatiment;
import modele.TypeDonnee;

import java.util.ArrayList;
import java.util.Map;

/**
 * <p>
 *     Vue permettant d'ajouter un batiment a une ville
 * </p>
 *
 * @see modele.Batiment
 *
 * @author Antoine RICHARD - Antoine68
 * */
public class VueAjouterBatiment extends Scene {

    public static final String STYLE_BOUTONS = "-fx-background-color: #25467F; -fx-text-fill: white; -fx-font-size: 12; -fx-font-weight: bold;-fx-min-width: 50px";

    /**
     * Le controleur de l'application, permet a la vue d'intéragir avec les modèles ou avec le navigateur des vues
     */
    private controler.ControleurPrincipal controleur = null;

    /**
     * La grille sur laquelle sont ajoutés les différents éléments de la vue
     */
    protected GridPane grillePrincipale;

    /**
     * La grille sur laquelle sont ajoutés les différents éléments du formulaire
     */
    protected GridPane grilleForm;

    /**
     * La grille sur laquelle sont ajoutés les différents boutons
     */
    protected GridPane grilleBoutons;

    /**
     * L'id de la ville dans laquelle on ajoute le batiment.
     */
    private int idVille;

    /**
     * Afficher "Type de batiment :".
     */
    private Label labelTypeBatiment;

    /**
     * Afficher les erreurs du formulaires.
     */
    private Label labelErreurs;

    /**
     * Afficher la description du type sélectionné.
     */
    private Label labelDescription;

    /**
     * Afficher le prix du type sélectionné.
     */
    private Label labelPrix;

    /**
     * Afficher "Effets:" .
     */
    private Label labelEffet;

    /**
     * Permet de lister tous les effets du type de batiments sélectionner.
     */
    private TextFlow textFlowEffet;

    /**
     * Bouton permetant de revenir a la vue de la ville sans valider le formulaire.
     */
    private Button btnRetourMenuVille;

    /**
     * Bouton permetant de valider le formulaire d'ajout.
     */
    private Button btnAjouterBatiment;

    /**
     * Liste déroulante permettant de choisir le type de batiment parmi ceux débloqués.
     */
    private ComboBox comboBoxTypeBatiment;

    /**
     * Le type de batiment choisi.
     */
    private TypeBatiment typeBatimentDuBatiment;

    /**
     *
     * Constructeur VueAjouterBatiment. Créer les différents éléments de la vue.
     *
     */
    public VueAjouterBatiment() {
        super(new GridPane(), 400,400);

        this.btnRetourMenuVille = new Button("Annuler");
        this.btnRetourMenuVille.setStyle(STYLE_BOUTONS);
        this.btnAjouterBatiment = new Button("Ajouter");
        this.btnAjouterBatiment.setStyle(STYLE_BOUTONS);
        this.labelTypeBatiment = new Label("Type de batiment : ");
        this.labelPrix = new Label("Prix :");
        this.labelDescription = new Label("Description :");
        this.labelEffet = new Label("Effets :");
        this.textFlowEffet = new TextFlow();
        this.labelErreurs = new Label("");
        this.comboBoxTypeBatiment = new ComboBox();

        //affecte un listener écoutant le changement de valeur de la combobox
        this.comboBoxTypeBatiment.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null) return;
            TypeBatiment typeBatimentSelectionne = (TypeBatiment) newVal;
            labelDescription.setText("Description : " +  typeBatimentSelectionne.getDescription());
            labelPrix.setText("Prix : " + typeBatimentSelectionne.getCoutConstructionParDefaut() + "" + TypeDonnee.FINANCES.getUnite());
            textFlowEffet.getChildren().clear();
            for (Map.Entry effet : typeBatimentSelectionne.getEffetsParDefaut().entrySet()){
                Text text =  new Text("    |> " + effet.getKey() + ": " + effet.getValue() + "\n");
                if ((double) effet.getValue() < 0) text.setFill(Color.BLUE);
                else text.setFill(Color.RED);
                textFlowEffet.getChildren().add(text);
            }

        });


        this.grillePrincipale = (GridPane) this.getRoot();
        this.grilleForm = new GridPane();
        this.grilleBoutons = new GridPane();
    }

    /**
     * Initialise la vue.
     * @param idVille id de la ville dans laquelle on veut ajouter le batiment
     * @param typeBatimentDebloque la liste des types de batiment débloqués
     */
    public void initialiserVueAjouterBatiment(int idVille, ArrayList<TypeBatiment> typeBatimentDebloque) {
        this.idVille = idVille;
        this.grillePrincipale.getChildren().clear();
        this.grilleForm.getChildren().clear();
        this.grilleBoutons.getChildren().clear();

        labelErreurs.setText("");

        // créer et affecter les options de la combobox.
        ObservableList<TypeBatiment> options = FXCollections.observableArrayList();
        for (TypeBatiment typeBatiment : typeBatimentDebloque) {
            options.add(typeBatiment);
        }
        this.comboBoxTypeBatiment.setItems(options);
        this.comboBoxTypeBatiment.setValue(options.get(0));

        //placer les éléments
        grilleForm.add(this.labelTypeBatiment,0,0);
        grilleForm.add(this.comboBoxTypeBatiment, 0, 1);
        grilleForm.add(this.labelDescription,0,2);
        grilleForm.add(this.labelPrix, 0,3);
        grilleForm.add(this.labelEffet,0,4);
        grilleForm.add(this.textFlowEffet,0,5);
        grilleForm.add(this.labelErreurs, 0, 6);


        // assigner une action a chaque bouton

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

    /**
     * Valider les données du formulaire rentrées par l'utilisateur.
     * Puis notifier le controleur de créer le batiment.
     *
     * @throws Exception
     */
    private void validerDonnees() throws Exception {
        boolean erreur = false;
        String messageErreurs = "";
        try{
            //verifie si un type de batiment est selectionné
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

    /**
     * Créer un batiment avec les données entrées par l'utilisateur et le retourne.
     * @return le batiment créé
     */
    public Batiment getBatiment()
    {
        Batiment batiment = new Batiment(typeBatimentDuBatiment);
        return batiment;
    }

    /**
     * Affecter le controleur a la vue.
     * @param controleur le controleur affecté
     */
    public void setControleur(controler.ControleurPrincipal controleur) {
        this.controleur = controleur;
    }
}
