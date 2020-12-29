package vue;

import controler.ControleurPrincipal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import modele.*;

import java.util.HashMap;

/**
 * <b>
 *     Vue affichant les informations d'un avant-poste et de le manipuler:
 *     <ul>
 *         <li>elle permet de détruire l'avant-poste</li>
 *         <li>de visualiser les données de ses mines</li>
 *         <li>de les améliorer</li>
 *         <li>de les supprimer</li>
 *         <li>de créer une nouvelle mine</li>
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
     * La grille sur laquelle sont ajoutés les différents éléments de la vue
     *
     * @see VueAvantPoste#VueAvantPoste()
     * @see VueAvantPoste#initialiserVueAvantPoste(AvantPoste)
     */
    private GridPane grillePrincipale;

    /**
     * La grille dans laquelle seront affichées les informations concerant l'avant-poste
     *
     * @see VueAvantPoste#VueAvantPoste()
     * @see VueAvantPoste#initialiserVueAvantPoste(AvantPoste)
     */
    private GridPane grilleAvantPoste;

    /**
     * La grille sur laquelle sont listées les différentes mines de l'avant-poste
     *
     * @see VueAvantPoste#VueAvantPoste()
     * @see VueAvantPoste#initialiserVueAvantPoste(AvantPoste)
     */
    private GridPane grilleMines;

    /**
     * Scrollpane dans laquelle sera affichée la grille des mines
     *
     * @see VueAvantPoste#VueAvantPoste()
     * @see VueAvantPoste#initialiserVueAvantPoste(AvantPoste)
     */
    private ScrollPane scrollPaneMines;

    /**
     * Le controleur de l'application, permet à la vue d'intéragir avec les modèles ou avec le navigateur des vues
     *
     * @see VueAvantPoste#VueAvantPoste()
     * @see VueAvantPoste#initialiserVueAvantPoste(AvantPoste)
     */
    private controler.ControleurPrincipal controleur = null;

    /**
     * Label affichant le nom de l'avant-poste affiché
     *
     * @see VueAvantPoste#VueAvantPoste()
     * @see VueAvantPoste#initialiserVueAvantPoste(AvantPoste)
     */
    private Label labelNom;

    /**
     * Label affichant les coordonnées de l'avant-poste affiché
     *
     * @see VueAvantPoste#VueAvantPoste()
     * @see VueAvantPoste#initialiserVueAvantPoste(AvantPoste)
     */
    private Label labelCoordonnees;

    /**
     * Label affichant la somme des bénéfices des mines de l'avant-postes
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
     * Identifiant de l'avant-poste affiché
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
     * Bouton permettant de détruire l'avant-poste
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
     *  Créé les différents labels, layouts et boutons de la vue
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
        super(new GridPane(), 800,400);
        this.grillePrincipale = (GridPane) this.getRoot();
        this.grilleMines = new GridPane();
        this.grilleAvantPoste = new GridPane();
        this.scrollPaneMines = new ScrollPane();
        btnRetour = new Button("Retour");
        btnDetruire = new Button("Détruire");
        btnAjouterMine = new Button("+ Mine");
        labelMessages = new Label("");
    }

    /**
     *  Initialise la vue
     *      <p>
     *          Affichage des différentes données de l'avant-poste sur les labels
     *          Créeation des EventHandler des boutons
     *          Liste des mines de l'avant-poste
     *      </p>
     * @param avantPoste AvantPoste dont on veut afficher les données
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

        int ligneMine=0;

        //affichage des mines et de leurs données
        for(Mine mine : avantPoste.getMines()){
            Label labelNomMine = new Label(mine.getNom()+" : ");
            Label labelBenefice = new Label(", benefice/min : "+Double.toString(mine.getBenefice()));
            Label labelRendement = new Label("kg/min "+Double.toString(mine.getRendement()));
            Label labelRessource = new Label(", ressource : " + mine.getRessource().getSymbole());

            Button btnAmeliorer = new Button("Améliorer");
            btnAmeliorer.setUserData(mine.getId());
            Button btnDetruire = new Button("Detruire");

            GridPane grilleMine = new GridPane();
            grilleMine.add(labelNomMine, 0, 0);
            grilleMine.add(labelRendement, 1, 0);
            grilleMine.add(labelBenefice, 2, 0);
            grilleMine.add(labelRessource, 3, 0);
            grilleMine.add(btnAmeliorer, 1, 1);
            grilleMine.add(btnDetruire, 2, 1);

            if (mine.getNiveau()>=5 || !mine.isFonctionnelle()){
                //si la mine a déjà atteint le niveau maximum (5) le bouton pour l'améliorer est désactivé
                btnAmeliorer.setDisable(true);
            }

            btnAmeliorer.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("Amélioration Mine");
                    try {
                        //appelle de la fonction du controleur permettant d'améliorer la mine
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

            btnDetruire.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("Destruction Mine");
                    //appelle la fonction du controleur permettant détruire la mine
                    controleur.notifierDetruireMine(mine.getId());
                }
            });

            grilleMines.add(grilleMine, 0, ligneMine);
            ligneMine++;

        }

        this.labelNom = new Label(avantPoste.getNom());
        this.labelCoordonnees = new Label("("+Double.toString(avantPoste.getCoordonnee().getX())+", "+Double.toString(avantPoste.getCoordonnee().getY())+", "+Double.toString(avantPoste.getCoordonnee().getZ())+")");
        this.labelBenefices = new Label(", €/min : " + Double.toString(avantPoste.getBeneficesMines()));

        btnRetour.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.notifierNaviguerMenuPopulation();
            }
        });

        btnDetruire.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //appel de la fonction du controleur permettant de détruire l'avant-poste
                controleur.notifierDetruireAvantPoste(idAvantPoste);
            }
        });

        btnAjouterMine.setOnAction(new EventHandler<ActionEvent>() {
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

        //ajoute la liste des mines à la scrollpane
        scrollPaneMines.setContent(grilleMines);
        scrollPaneMines.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPaneMines.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        grilleAvantPoste.add(labelNom,0,0);
        grilleAvantPoste.add(labelCoordonnees,1,0);
        grilleAvantPoste.add(labelBenefices,2,0);
        grilleAvantPoste.add(btnRetour,0,1);
        grilleAvantPoste.add(btnDetruire,1,1);
        grillePrincipale.add(grilleAvantPoste, 0, 0);
        grillePrincipale.add(scrollPaneMines, 0, 1);
        grillePrincipale.add(btnAjouterMine, 0, 2);
        grillePrincipale.add(labelMessages, 0, 3);
        //cache le label des messages d'erreur
        this.labelMessages.setVisible(false);
    }


    /**
     *  Permet d'affecter un controleur à la vue
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
