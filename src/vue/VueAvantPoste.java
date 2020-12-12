package vue;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import modele.AvantPoste;


public class VueAvantPoste extends Scene {

    protected GridPane grillePrincipale;
    private controler.ControleurPrincipal controleur = null;
    private Label labelNom;
    private AvantPoste avantPoste;

    public VueAvantPoste() {
        super(new GridPane(), 400,400);
        grillePrincipale = (GridPane) this.getRoot();
    }

    public void initialiserVueAvantPoste(AvantPoste avantPoste) {
        this.avantPoste = avantPoste;
        labelNom = new Label(avantPoste.getNom());
        grillePrincipale.add(labelNom,0,0);
    }

    public void setControleur(controler.ControleurPrincipal controleur) {
        this.controleur = controleur;
    }
}
