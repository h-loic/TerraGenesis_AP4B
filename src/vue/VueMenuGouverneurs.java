package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import modele.Gouverneur;

import java.util.ArrayList;


public class VueMenuGouverneurs extends Scene {

    protected GridPane grillePrincipale;
    protected GridPane grilleGouverneur;

    private controler.ControleurPrincipal controleur = null;

    private Label labelGouverneurs;
    private Button boutonRetour;
    private Button boutonTrierParNom;
    private Button boutonTrierParDebloque;

    public VueMenuGouverneurs() {
        super(new GridPane(), 400,400);
        grillePrincipale = (GridPane) this.getRoot();

        this.labelGouverneurs = new Label("Gouverneurs");
        this.boutonRetour = new Button("retour");
        this.boutonTrierParNom = new Button("Trier par nom");
        this.boutonTrierParDebloque = new Button("Trier par debloquer");
    }

    public void initialiserMenuGouverneurs(ArrayList<Gouverneur> listeGouverneur) {
        grilleGouverneur = new GridPane();
        int lignesGouverneur = 1;
        this.grillePrincipale.getChildren().clear();

        for (Gouverneur gouverneur : listeGouverneur){
            Label labelNom = new Label(gouverneur.getNom());
            lignesGouverneur+=2;
            this.grilleGouverneur.add(labelNom, 0, lignesGouverneur);
            if (gouverneur.estDebloque()){
                Button btnAfficher = new Button("afficher");
                btnAfficher.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        controleur.notifierNaviguerAfficherGouverneur(gouverneur);
                    }
                });
                this.grilleGouverneur.add(btnAfficher, 4, lignesGouverneur);
            }else{
                Button btnDebloque = new Button("debloquer");
                btnDebloque.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        controleur.notifierDebloquerGouverneur(gouverneur);
                        controleur.notifierNaviguerMenuGouverneurs();
                    }
                });
                this.grilleGouverneur.add(btnDebloque, 4, lignesGouverneur);
            }

        }
        lignesGouverneur+=2;
        this.grillePrincipale.add(this.labelGouverneurs, 0, 0);
        this.grillePrincipale.add(this.boutonTrierParNom, 1, 0);
        this.grillePrincipale.add(this.boutonTrierParDebloque, 2, 0);
        this.grillePrincipale.add(this.boutonRetour, 1, lignesGouverneur);
        this.grillePrincipale.add(this.grilleGouverneur, 0, 1);

        this.boutonRetour.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.notifierNaviguerMenuPrincipal();
            }
        });

        boutonTrierParNom.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.notifierTrierParNomListeGouverneur();
                controleur.notifierNaviguerMenuGouverneurs();
            }
        });

        boutonTrierParDebloque.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.notifierTrierParDebloqueListeGouverneur();
                controleur.notifierNaviguerMenuGouverneurs();
            }
        });
    }

    public void setControleur(controler.ControleurPrincipal controleur) {
        this.controleur = controleur;
    }
}
