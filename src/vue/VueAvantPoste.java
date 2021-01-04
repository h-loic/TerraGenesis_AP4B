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
import modele.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * <b>
 *     Vue affichant les informations d'un avant-poste et de le manipuler:
 *     <ul>
 *         <li>elle permet de detruire l'avant-poste</li>
 *         <li>de visualiser les donnees de ses mines</li>
 *         <li>de les ameliorer</li>
 *         <li>de les supprimer</li>
 *         <li>de creer une nouvelle mine</li>
 *     </ul>
 *
 *
 * </p>
 *
 * @see modele.Mine
 * @see modele.AvantPoste
 * @author Zapolatero - Louis Pascuzzi
 * */

public class VueAvantPoste extends Scene {
    /**
     *  Constante definissant le style des scrollpanes de la vue
     *
     * @see VueAvantPoste#VueAvantPoste()
     * @see VueAvantPoste#initialiserVueAvantPoste(AvantPoste)
     */
    public static final String STYLE_SCROLLPANE = "-fx-background-color:transparent;";

    /**
     *  Constante definissant le style permettant de mettre du texte en valeur en le mettant en gras
     *
     * @see VueAvantPoste#VueAvantPoste()
     * @see VueAvantPoste#initialiserVueAvantPoste(AvantPoste)
     */
    public static final String STYLE_GRAS = "-fx-font-size: 13; -fx-font-weight: bold;";

    /**
     *  Constante definissant le style des titres de la vue
     *
     * @see VueAvantPoste#VueAvantPoste()
     * @see VueAvantPoste#initialiserVueAvantPoste(AvantPoste)
     */
    public static final String STYLE_TITRE = " -fx-font-size: 18; -fx-font-weight: bold; -fx-padding: 15px";

    /**
     *  Constante definissant le style du label des erreurs de la vue
     *
     * @see VueAvantPoste#VueAvantPoste()
     * @see VueAvantPoste#initialiserVueAvantPoste(AvantPoste)
     */
    public static final String STYLE_ERREUR = "-fx-text-fill: red; -fx-font-size: 13; -fx-font-weight: bold";

    /**
     *  Constante definissant le style des boutons de la vue
     *
     * @see VueAvantPoste#VueAvantPoste()
     * @see VueAvantPoste#initialiserVueAvantPoste(AvantPoste)
     */
    public static final String STYLE_BOUTONS = "-fx-background-color: #25467F; -fx-text-fill: white; -fx-font-size: 12; -fx-font-weight: bold;-fx-min-width: 80px";

    /**
     * La grille sur laquelle sont ajoutes les differents elements de la vue
     *
     * @see VueAvantPoste#VueAvantPoste()
     * @see VueAvantPoste#initialiserVueAvantPoste(AvantPoste)
     */
    private GridPane grillePrincipale;

    /**
     * La grille dans laquelle seront affichees les informations concerant l'avant-poste
     *
     * @see VueAvantPoste#VueAvantPoste()
     * @see VueAvantPoste#initialiserVueAvantPoste(AvantPoste)
     */
    private GridPane grilleAvantPoste;

    /**
     * La grille sur laquelle sont listees les differentes mines de l'avant-poste
     *
     * @see VueAvantPoste#VueAvantPoste()
     * @see VueAvantPoste#initialiserVueAvantPoste(AvantPoste)
     */
    private GridPane grilleMines;

    /**
     * Scrollpane dans laquelle sera affichee la grille des mines
     *
     * @see VueAvantPoste#VueAvantPoste()
     * @see VueAvantPoste#initialiserVueAvantPoste(AvantPoste)
     */
    private ScrollPane scrollPaneMines;

    /**
     * Le controleur de l'application, permet a la vue d'interagir avec les modeles ou avec le navigateur des vues
     *
     * @see VueAvantPoste#VueAvantPoste()
     * @see VueAvantPoste#initialiserVueAvantPoste(AvantPoste)
     */
    private controler.ControleurPrincipal controleur = null;

    /**
     * Label affichant le nom de l'avant-poste affiche
     *
     * @see VueAvantPoste#VueAvantPoste()
     * @see VueAvantPoste#initialiserVueAvantPoste(AvantPoste)
     */
    private Label labelNom;

    /**
     * Label affichant les coordonnees de l'avant-poste affiche
     *
     * @see VueAvantPoste#VueAvantPoste()
     * @see VueAvantPoste#initialiserVueAvantPoste(AvantPoste)
     */
    private Label labelCoordonnees;

    /**
     * Label affichant la somme des benefices des mines de l'avant-postes
     *
     * @see VueAvantPoste#VueAvantPoste()
     * @see VueAvantPoste#initialiserVueAvantPoste(AvantPoste)
     */
    private Label labelBenefices;

    /**
     * Label affichant les messages d'erreur
     *
     * @see VueAvantPoste#VueAvantPoste()
     * @see VueAvantPoste#initialiserVueAvantPoste(AvantPoste)
     */
    private Label labelMessages;

    /**
     * Identifiant de l'avant-poste affiche
     *
     * @see VueAvantPoste#VueAvantPoste()
     * @see VueAvantPoste#initialiserVueAvantPoste(AvantPoste)
     * @see AvantPoste#getId()
     */
    private int idAvantPoste;

    /**
     * Bouton permettant de retourner au MenuPopulation
     *
     * @see VueAvantPoste#VueAvantPoste()
     * @see VueAvantPoste#initialiserVueAvantPoste(AvantPoste)
     * @see VueMenuPopulation
     */
    private Button btnRetour;

    /**
     * Bouton permettant de detruire l'avant-poste
     *
     * @see VueAvantPoste#VueAvantPoste()
     * @see VueAvantPoste#initialiserVueAvantPoste(AvantPoste)
     */
    private Button btnDetruire;

    /**
     * Bouton permettant d'ajouter une mine en allant sur la vue AjouterMine
     *
     * @see VueAvantPoste#VueAvantPoste()
     * @see VueAvantPoste#initialiserVueAvantPoste(AvantPoste)
     * @see VueAjouterMine
     */
    private Button btnAjouterMine;

    /**
     *  Constructeur de VueAvantPoste
     *  Cree les differents labels, layouts et boutons de la vue
     *
     * @see VueAvantPoste#grillePrincipale
     * @see VueAvantPoste#grilleMines
     * @see VueAvantPoste#grilleAvantPoste
     * @see VueAvantPoste#scrollPaneMines
     * @see VueAvantPoste#grillePrincipale
     * @see VueAvantPoste#btnRetour
     * @see VueAvantPoste#btnAjouterMine
     * @see VueAvantPoste#labelMessages
     */
    public VueAvantPoste() {
        super(new GridPane(), 600,400);
        this.grillePrincipale = (GridPane) this.getRoot();
        this.grilleMines = new GridPane();
        this.grilleAvantPoste = new GridPane();
        this.scrollPaneMines = new ScrollPane();
        btnRetour = new Button("Retour");
        btnDetruire = new Button("Detruire");
        btnAjouterMine = new Button("+ Mine");
        labelMessages = new Label("");
    }

    /**
     *  Initialise la vue
     *      <p>
     *          Affichage des differentes donnees de l'avant-poste sur les labels
     *          Creeation des EventHandler des boutons
     *          Liste des mines de l'avant-poste
     *      </p>
     * @param avantPoste AvantPoste dont on veut afficher les donnees
     *
     * @see VueAvantPoste#grillePrincipale
     * @see VueAvantPoste#grilleMines
     * @see VueAvantPoste#grilleAvantPoste
     * @see VueAvantPoste#scrollPaneMines
     * @see VueAvantPoste#grillePrincipale
     * @see VueAvantPoste#btnRetour
     * @see VueAvantPoste#btnAjouterMine
     * @see VueAvantPoste#labelMessages
     */
    public void initialiserVueAvantPoste(AvantPoste avantPoste) {
        idAvantPoste = avantPoste.getId();

        this.grilleAvantPoste.getChildren().clear();
        this.grilleMines.getChildren().clear();
        this.grillePrincipale.getChildren().clear();

        labelMessages.setStyle(STYLE_ERREUR);

        int ligneMine=0;

        //affichage des mines et de leurs donnees
        for(Mine mine : avantPoste.getMines()){
            Label labelNomMine = new Label(mine.getNom()+" : ");
            Label labelBenefice = new Label(", benefice/min : "+Double.toString(mine.getBenefice()));
            Label labelRendement = new Label("kg/min "+Double.toString(mine.getRendement()));
            Label labelRessource = new Label(", ressource : " + mine.getRessource().getSymbole());

            Button btnAmeliorer = new Button("Ameliorer");
            btnAmeliorer.setUserData(mine.getId());
            Button btnDetruire = new Button("Detruire");

            GridPane grilleMine = new GridPane();
            HBox infoMines = new HBox();
            infoMines.getChildren().addAll(labelNomMine, labelRendement, labelBenefice, labelRessource);
            grilleMine.add(infoMines, 0, 0);
            HBox hBoxboutons = new HBox();
            hBoxboutons.setSpacing(15);
            hBoxboutons.getChildren().addAll(btnAmeliorer, btnDetruire);
            grilleMine.add(hBoxboutons, 0, 1);
            grilleMine.setPadding(new Insets(7,0,3,0));

            if (mine.getNiveau()>=5 || !mine.isFonctionnelle()){
                //si la mine a deja atteint le niveau maximum (5) le bouton pour l'ameliorer est desactive
                btnAmeliorer.setDisable(true);
            }

            btnAmeliorer.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("Amelioration Mine");
                    try {
                        //appelle de la fonction du controleur permettant d'ameliorer la mine
                        controleur.notifierAmeliorerMine((int)btnAmeliorer.getUserData(), avantPoste.getId());
                        labelMessages.setVisible(false);
                        labelMessages.setText("");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        labelMessages.setText(e.getMessage());
                        labelMessages.setVisible(true);
                    }
                }
            });
            btnAmeliorer.setStyle(STYLE_BOUTONS);

            btnDetruire.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("Destruction Mine");
                    //appelle la fonction du controleur permettant detruire la mine
                    controleur.notifierDetruireMine(mine.getId());
                }
            });
            btnDetruire.setStyle(STYLE_BOUTONS);

            grilleMines.add(grilleMine, 0, ligneMine);
            ligneMine++;

        }

        this.labelNom = new Label(avantPoste.getNom());
        this.labelNom.setStyle(STYLE_TITRE);
        this.labelCoordonnees = new Label("("+Double.toString(avantPoste.getCoordonnee().getX())+", "+Double.toString(avantPoste.getCoordonnee().getY())+", "+Double.toString(avantPoste.getCoordonnee().getZ())+")");
        this.labelCoordonnees.setStyle(STYLE_GRAS);
        this.labelBenefices = new Label(", €/min : " + Double.toString(avantPoste.getBeneficesMines()));
        this.labelBenefices.setStyle(STYLE_GRAS);

        this.btnRetour.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.notifierNaviguerMenuPopulation();
            }
        });
        this.btnRetour.setStyle(STYLE_BOUTONS);

        this.btnDetruire.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //appel de la fonction du controleur permettant de detruire l'avant-poste
                controleur.notifierDetruireAvantPoste(idAvantPoste);
            }
        });
        this.btnDetruire.setStyle(STYLE_BOUTONS);

        this.btnAjouterMine.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Ajouter mine");
                try {
                    //appelle la fonction du controleur permettant de naviguer vers la page d'ajout d'une nouvelle mine
                    controleur.notifierNaviguerAjouterMine(idAvantPoste);
                    labelMessages.setVisible(false);
                    labelMessages.setText("");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    /*affiche les messages d'erreur
                    * ex : si le joueur ne peut pas payer une nouvelle mine dans cet avant-poste
                    * */
                    labelMessages.setText(e.getMessage());
                    labelMessages.setVisible(true);
                }
            }
        });
        this.btnAjouterMine.setStyle(STYLE_BOUTONS);

        //ajoute la liste des mines a la scrollpane
        this.scrollPaneMines.setContent(grilleMines);
        this.scrollPaneMines.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        this.scrollPaneMines.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        this.scrollPaneMines.setStyle(STYLE_SCROLLPANE);
        this.scrollPaneMines.setMinWidth(600);

        this.grilleAvantPoste.add(this.labelNom,0,0);
        this.grilleAvantPoste.add(this.labelCoordonnees,1,0);
        this.grilleAvantPoste.add(this.labelBenefices,2,0);
        this.grilleAvantPoste.add(this.btnRetour,0,1);
        this.grilleAvantPoste.add(this.btnDetruire,1,1);
        this.grillePrincipale.add(this.grilleAvantPoste, 0, 0);
        this.grillePrincipale.add(this.scrollPaneMines, 0, 1);
        this.grillePrincipale.add(this.btnAjouterMine, 0, 2);
        this. grillePrincipale.add(this.labelMessages, 0, 3);
        //cache le label des messages d'erreur
        this.labelMessages.setVisible(false);
    }

    /**
     *  Permet d'affecter un controleur a la vue
     *
     * @param controleur
     *
     * @see ControleurPrincipal
     * @see VueAvantPoste#controleur
     */
    public void setControleur(controler.ControleurPrincipal controleur) {
        this.controleur = controleur;
    }
}
