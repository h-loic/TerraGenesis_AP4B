package vue;

import controler.ControleurPrincipal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import modele.TypeBatiment;

import java.util.ArrayList;
import java.util.Map;

/**
 * <b>
 *     Vue Permettant de rechercher et débloquer des types de batiments
 * </p>
 *
 * @see modele.Planete
 * @see modele.TypeBatiment
 * @see modele.Recherche
 * @author Zapolatero - lpascuzzi
 * */

public class VueMenuRecherche extends Scene {

    /**
     * La grille sur laquelle sont ajoutés les différents boutons de la vue
     *
     * @see VueMenuRecherche#VueMenuRecherche()
     * @see VueMenuRecherche#initialiserMenuRecherche(ArrayList, boolean, TypeBatiment)
     * */
    private GridPane grilleBoutons;

    /**
     * La grille sur laquelle sont ajoutés les différents éléments de la vue
     *
     * @see VueMenuRecherche#VueMenuRecherche()
     * @see VueMenuRecherche#initialiserMenuRecherche(ArrayList, boolean, TypeBatiment)
     * */
    private GridPane grillePrincipale;

    /**
     * La grille sur laquelle sont affichés les types de batiments à afficher
     *
     * @see VueMenuRecherche#VueMenuRecherche()
     * @see VueMenuRecherche#initialiserMenuRecherche(ArrayList, boolean, TypeBatiment)
     * */
    private GridPane grilleBatiments;

    /**
     * La grille sur laquelle sont ajoutés les informations concernant la recherche en cours, s'il y'en a une
     *
     * @see VueMenuRecherche#VueMenuRecherche()
     * @see VueMenuRecherche#initialiserMenuRecherche(ArrayList, boolean, TypeBatiment)
     * */
    private GridPane grilleRechercheEnCours;

    /**
     * Scrollpane dans laquelle sera ajouté la grille des batiments à rechercher
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
     * Label affichant le nom de type batiment actuellement recherché
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
     * Bouton permettant d'actualiser les données affichées
     *
     * @see VueMenuRecherche#VueMenuRecherche()
     * @see VueMenuRecherche#initialiserMenuRecherche(ArrayList, boolean, TypeBatiment)
     * */
    private Button btnActualiser;

    /**
     *  Constructeur de VueMenuRecherche
     *  Créé différents labels, sliders et boutons de la vue
     *
     * @see VueMenuRecherche#grillePrincipale
     * @see VueMenuRecherche#grilleBatiments
     * @see VueMenuRecherche#grilleRechercheEnCours
     * @see VueMenuRecherche#grilleBoutons
     * @see VueMenuRecherche#labelRecherche
     * @see VueMenuRecherche#labelRechercheEnCours
     * @see VueMenuRecherche#labelErreur
     * @see VueMenuRecherche#btnRetour
     * @see VueMenuRecherche#scrollPaneBatiments
     * @see VueMenuRecherche#btnActualiser
     *
     */
    public VueMenuRecherche() {
        super(new GridPane(), 400,400);
        this.grillePrincipale = (GridPane) this.getRoot();
        this.grilleBatiments = new GridPane();
        this.grilleRechercheEnCours = new GridPane();
        this.grilleBoutons = new GridPane();
        this.labelRecherche = new Label("Recherche");
        this.labelRechercheEnCours = new Label("");
        this.labelErreur = new Label("");
        this.btnRetour = new Button("retour");
        this.scrollPaneBatiments = new ScrollPane();
        this.btnActualiser = new Button("Actualiser");
    }

    /**
     *  Initialise la vue
     *      Affichage des différents types de batiments à rechercher et de la recherche en cours
     * @param typeBatimentsNonDebloques liste contenant les types de batiments qui ne sont pas encore débloqués
     * @param rechercheEnCours booléen indiquant si une recherche est en cours
     * @param batimentRecherche type de batiment actuellement recherché si une recherche est en cours
     *
     * @see TypeBatiment
     * @see VueMenuRecherche#grillePrincipale
     * @see VueMenuRecherche#grilleBatiments
     * @see VueMenuRecherche#grilleRechercheEnCours
     * @see VueMenuRecherche#grilleBoutons
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
        this.grilleBoutons.getChildren().clear();
        this.scrollPaneBatiments.setMinWidth(400);

        labelErreur.setVisible(false);

        int rowindex = 1;

        //affichage des types de batiments pas encore débloqué
        for (TypeBatiment typeBatiment : typeBatimentsNonDebloques){

            if (typeBatiment == batimentRecherche) continue;//saute cette itération pour ne pas afficher le type de batiment actuellement recherché dans la lsite des batiments recherchés

            GridPane grillDescription = new GridPane();
            Label labelBatiment = new Label(typeBatiment.getNom() + ", prix : "+typeBatiment.getCoutRecherche() + ", durée : " + typeBatiment.getTempsRecherche() + " min."
                    + (typeBatiment.getParent() != null ? ("\nnécessite : " + typeBatiment.getParent() + " (" + (typeBatimentsNonDebloques.contains(typeBatiment.getParent()) ? "pas débloqué)" : "débloqué)")) : ""));

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

        //affichage des données de la recherche en cours s'il y en a une
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

        this.scrollPaneBatiments.setContent(grilleBatiments);
        this.grillePrincipale.add(this.labelRecherche, 0, 0);
        this.grillePrincipale.add(this.scrollPaneBatiments, 0, 1);
        this.grillePrincipale.add(this.labelErreur, 0, 2);
        this.grilleBoutons.add(this.btnRetour, 0, 0);
        this.grilleBoutons.add(this.btnActualiser, 1, 0);
        this.grillePrincipale.add(this.grilleBoutons, 0, 4);
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
