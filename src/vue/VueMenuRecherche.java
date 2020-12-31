package vue;

import controler.ControleurPrincipal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import modele.TypeBatiment;

import java.util.ArrayList;
import java.util.Map;

/**
 * <b>
 *     Vue Permettant de rechercher et debloquer des types de batiments
 * </p>
 *
 * @see modele.Planete
 * @see modele.TypeBatiment
 * @see modele.Recherche
 * @author Zapolatero - lpascuzzi
 * */

public class VueMenuRecherche extends Scene {

    /**
     *  Constante definissant le style des scrollpanes de la vue
     *
     * @see VueMenuRecherche#VueMenuRecherche()
     * @see VueMenuRecherche#initialiserMenuRecherche(ArrayList, boolean, TypeBatiment)
     */
    public static final String STYLE_SCROLLPANE = "-fx-background-color:transparent;";

    /**
     *  Constante definissant le style permettant de mettre du texte en valeur en le mettant en gras
     *
     * @see VueMenuRecherche#VueMenuRecherche()
     * @see VueMenuRecherche#initialiserMenuRecherche(ArrayList, boolean, TypeBatiment)
     */
    public static final String STYLE_GRAS = "-fx-font-size: 13; -fx-font-weight: bold;";

    /**
     *  Constante definissant le style des titres de la vue
     *
     * @see VueMenuRecherche#VueMenuRecherche()
     * @see VueMenuRecherche#initialiserMenuRecherche(ArrayList, boolean, TypeBatiment)
     */
    public static final String STYLE_TITRE = " -fx-font-size: 18; -fx-font-weight: bold; -fx-padding: 15px";

    /**
     *  Constante definissant le style du label des erreurs de la vue
     *
     * @see VueMenuRecherche#VueMenuRecherche()
     * @see VueMenuRecherche#initialiserMenuRecherche(ArrayList, boolean, TypeBatiment)
     */
    public static final String STYLE_ERREUR = "-fx-text-fill: red; -fx-font-size: 13; -fx-font-weight: bold";

    /**
     *  Constante definissant le style des boutons de la vue
     *
     * @see VueMenuRecherche#VueMenuRecherche()
     * @see VueMenuRecherche#initialiserMenuRecherche(ArrayList, boolean, TypeBatiment)
     */
    public static final String STYLE_BOUTONS = "-fx-background-color: #25467F; -fx-text-fill: white; -fx-font-size: 12; -fx-font-weight: bold;-fx-min-width: 80px";

    /**
     * Layout sur lequel seront affiches les boutons Actualiser et Retour
     *
     * @see VueMenuRecherche#VueMenuRecherche()
     * @see VueMenuRecherche#initialiserMenuRecherche(ArrayList, boolean, TypeBatiment)
     * */
    private HBox hBoxBoutons;

    /**
     * La grille sur laquelle sont ajoutes les differents elements de la vue
     *
     * @see VueMenuRecherche#VueMenuRecherche()
     * @see VueMenuRecherche#initialiserMenuRecherche(ArrayList, boolean, TypeBatiment)
     * */
    private GridPane grillePrincipale;

    /**
     * La grille sur laquelle sont affiches les types de batiments à afficher
     *
     * @see VueMenuRecherche#VueMenuRecherche()
     * @see VueMenuRecherche#initialiserMenuRecherche(ArrayList, boolean, TypeBatiment)
     * */
    private GridPane grilleBatiments;

    /**
     * La grille sur laquelle sont ajoutes les informations concernant la recherche en cours, s'il y'en a une
     *
     * @see VueMenuRecherche#VueMenuRecherche()
     * @see VueMenuRecherche#initialiserMenuRecherche(ArrayList, boolean, TypeBatiment)
     * */
    private GridPane grilleRechercheEnCours;

    /**
     * Scrollpane dans laquelle sera ajoute la grille des batiments à rechercher
     *
     * @see VueMenuRecherche#VueMenuRecherche()
     * @see VueMenuRecherche#initialiserMenuRecherche(ArrayList, boolean, TypeBatiment)
     * @see VueMenuRecherche#grilleBatiments
     * */
    private ScrollPane scrollPaneBatiments;

    private controler.ControleurPrincipal controleur = null;

    /**
     * Titre de la page Recherche
     *
     * @see VueMenuRecherche#VueMenuRecherche()
     * @see VueMenuRecherche#initialiserMenuRecherche(ArrayList, boolean, TypeBatiment)
     * */
    private Label labelRecherche;

    /**
     * Label affichant les messages d'erreurs
     *
     * @see VueMenuRecherche#VueMenuRecherche()
     * @see VueMenuRecherche#initialiserMenuRecherche(ArrayList, boolean, TypeBatiment)
     * */
    private Label labelErreur;

    /**
     * Label affichant le nom de type batiment actuellement recherche
     *
     * @see VueMenuRecherche#VueMenuRecherche()
     * @see VueMenuRecherche#initialiserMenuRecherche(ArrayList, boolean, TypeBatiment)
     * */
    private Label labelRechercheEnCours;

    /**
     * Bouton permettant de retourner au menu principal
     *
     * @see VueMenuRecherche#VueMenuRecherche()
     * @see VueMenuRecherche#initialiserMenuRecherche(ArrayList, boolean, TypeBatiment)
     * */
    private Button btnRetour;

    /**
     * Bouton permettant d'actualiser les donnees affichees
     *
     * @see VueMenuRecherche#VueMenuRecherche()
     * @see VueMenuRecherche#initialiserMenuRecherche(ArrayList, boolean, TypeBatiment)
     * */
    private Button btnActualiser;

    /**
     *  Constructeur de VueMenuRecherche
     *  Cree differents labels, sliders et boutons de la vue
     *
     * @see VueMenuRecherche#grillePrincipale
     * @see VueMenuRecherche#grilleBatiments
     * @see VueMenuRecherche#grilleRechercheEnCours
     * @see VueMenuRecherche#hBoxBoutons
     * @see VueMenuRecherche#labelRecherche
     * @see VueMenuRecherche#labelRechercheEnCours
     * @see VueMenuRecherche#labelErreur
     * @see VueMenuRecherche#btnRetour
     * @see VueMenuRecherche#scrollPaneBatiments
     * @see VueMenuRecherche#btnActualiser
     *
     */
    public VueMenuRecherche() {
        super(new GridPane(), 500,400);
        this.grillePrincipale = (GridPane) this.getRoot();
        this.grilleBatiments = new GridPane();
        this.grilleRechercheEnCours = new GridPane();
        this.hBoxBoutons = new HBox();
        this.labelRecherche = new Label("Recherche");
        this.labelRechercheEnCours = new Label("");
        this.labelErreur = new Label("");
        this.btnRetour = new Button("Retour");
        this.scrollPaneBatiments = new ScrollPane();
        this.btnActualiser = new Button("Actualiser");
    }

    /**
     *  Initialise la vue
     *      Affichage des differents types de batiments à rechercher et de la recherche en cours
     * @param typeBatimentsNonDebloques liste contenant les types de batiments qui ne sont pas encore debloques
     * @param rechercheEnCours booleen indiquant si une recherche est en cours
     * @param batimentRecherche type de batiment actuellement recherche si une recherche est en cours
     *
     * @see TypeBatiment
     * @see VueMenuRecherche#grillePrincipale
     * @see VueMenuRecherche#grilleBatiments
     * @see VueMenuRecherche#grilleRechercheEnCours
     * @see VueMenuRecherche#hBoxBoutons
     * @see VueMenuRecherche#labelRecherche
     * @see VueMenuRecherche#labelRechercheEnCours
     * @see VueMenuRecherche#labelErreur
     * @see VueMenuRecherche#btnRetour
     * @see VueMenuRecherche#scrollPaneBatiments
     * @see VueMenuRecherche#btnActualiser
     */
    public void initialiserMenuRecherche(ArrayList<TypeBatiment> typeBatimentsNonDebloques, boolean rechercheEnCours, TypeBatiment batimentRecherche) {
        this.grillePrincipale.getChildren().clear();
        this.grilleBatiments.getChildren().clear();
        this.grilleRechercheEnCours.getChildren().clear();
        this.hBoxBoutons.getChildren().clear();
        this.scrollPaneBatiments.setMinWidth(500);

        this.labelErreur.setVisible(false);
        this.labelErreur.setStyle(STYLE_ERREUR);

        this.labelRecherche.setStyle(STYLE_TITRE);

        this.labelRechercheEnCours.setStyle(STYLE_GRAS);

        int rowindex = 1;

        //affichage des types de batiments pas encore debloque
        for (TypeBatiment typeBatiment : typeBatimentsNonDebloques){

            if (typeBatiment == batimentRecherche) continue;//saute cette iteration pour ne pas afficher le type de batiment actuellement recherche dans la lsite des batiments recherches

            GridPane grillDescription = new GridPane();
            Label labelBatiment = new Label(typeBatiment.getNom() + ", prix : "+typeBatiment.getCoutRecherche() + ", duree : " + typeBatiment.getTempsRecherche() + " min."
                    + (typeBatiment.getParent() != null ? ("\nnecessite : " + typeBatiment.getParent() + " (" + (typeBatimentsNonDebloques.contains(typeBatiment.getParent()) ? "pas debloque)" : "debloque)")) : ""));
            labelBatiment.setStyle(STYLE_GRAS);
            TextFlow textFlowEffet = new TextFlow();

            //affichage des effets du type de batiment
            for (Map.Entry effet : typeBatiment.getEffetsParDefaut().entrySet()){
                Text text =  new Text("    |> " + effet.getKey() + ": " + effet.getValue() + "\n");
                if ((double) effet.getValue() < 0) text.setFill(Color.BLUE);
                else text.setFill(Color.RED);
                textFlowEffet.getChildren().add(text);
            }

            grillDescription.add(labelBatiment, 0, 0);
            grillDescription.add(textFlowEffet, 0, 1);

            Button btnRechercher = new Button("Rechercher");
            btnRechercher.setStyle(STYLE_BOUTONS);

            //desactive le bouton de recherche si une recherche est deja en cours
            if (rechercheEnCours || typeBatimentsNonDebloques.contains(typeBatiment.getParent())){
                btnRechercher.setDisable(true);
            }

            btnRechercher.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    labelErreur.setVisible(false);
                    try{
                        controleur.rechercherTypeBatiment(typeBatiment);
                    }catch(Exception e){
                        //gestion des erreurs si la recherche est impossible
                        System.out.println(e.getMessage());
                        labelErreur.setText(e.getMessage());
                        labelErreur.setVisible(true);
                    }
                }
            });

            grilleBatiments.add(grillDescription, 0, rowindex);
            grilleBatiments.add(btnRechercher, 1, rowindex);

            rowindex++;
        }


        this.btnRetour.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.notifierNaviguerMenuPrincipal();
            }
        });
        this.btnRetour.setStyle(STYLE_BOUTONS);

        //affichage des donnees de la recherche en cours s'il y en a une
        if (rechercheEnCours) {
            this.labelErreur.setText("Recherche en cours, impossible de faire une nouvelle recherche");
            this.labelErreur.setVisible(true);
            this.labelRechercheEnCours.setText("Recherche en cours  : "+batimentRecherche.getNom());
            this.grilleRechercheEnCours.add(labelRechercheEnCours, 0, 0);
            this.grillePrincipale.add(this.grilleRechercheEnCours, 0, 3);
        }

        this.btnActualiser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.notifierActualiserMenuRecherche();
            }
        });
        this.btnActualiser.setStyle(STYLE_BOUTONS);

        scrollPaneBatiments.setStyle(STYLE_SCROLLPANE);

        this.hBoxBoutons.getChildren().addAll(this.btnRetour, this.btnActualiser);
        this.hBoxBoutons.setSpacing(15);
        this.hBoxBoutons.setPadding(new Insets(15,15,15,15));

        this.scrollPaneBatiments.setContent(grilleBatiments);

        this.grillePrincipale.add(this.labelRecherche, 0, 0);
        this.grillePrincipale.add(this.scrollPaneBatiments, 0, 1);
        this.grillePrincipale.add(this.labelErreur, 0, 2);
        this.grillePrincipale.add(this.hBoxBoutons, 0, 4);
    }

    /**
     *  Permet d'affecter un controleur à la vue
     *
     * @param controleur
     *
     * @see ControleurPrincipal
     * @see VueMenuRecherche#controleur
     */
    public void setControleur(controler.ControleurPrincipal controleur) {
        this.controleur = controleur;
    }
}
