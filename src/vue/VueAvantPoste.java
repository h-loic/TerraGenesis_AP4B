package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import modele.AvantPoste;


public class VueAvantPoste extends Scene {

    protected GridPane grillePrincipale;
    private controler.ControleurPrincipal controleur = null;
    private Label labelNom;
    private Label labelCoordonnees;
    private AvantPoste avantPoste;
    private Button btnRetour;

    public VueAvantPoste() {
        super(new GridPane(), 400,400);
        grillePrincipale = (GridPane) this.getRoot();
        btnRetour = new Button("Retour");
    }

    public void initialiserVueAvantPoste(AvantPoste avantPoste) {
        grillePrincipale.getChildren().clear();
        this.avantPoste = avantPoste;
        labelNom = new Label(avantPoste.getNom());
        labelCoordonnees = new Label("("+Double.toString(avantPoste.getCoordonnee().getX())+", "+Double.toString(avantPoste.getCoordonnee().getY())+", "+Double.toString(avantPoste.getCoordonnee().getZ())+")");
        btnRetour.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.notifierNaviguerMenuPopulation();
            }
        });
        grillePrincipale.add(labelNom,0,0);
        grillePrincipale.add(labelCoordonnees,1,0);
        grillePrincipale.add(btnRetour,0,1);
    }

    public void setControleur(controler.ControleurPrincipal controleur) {
        this.controleur = controleur;
    }
}
