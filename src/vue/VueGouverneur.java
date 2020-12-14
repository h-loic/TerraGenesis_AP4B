package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import modele.AvantPoste;
import modele.Donnee;
import modele.Gouverneur;

public class VueGouverneur extends Scene{

    protected GridPane grillePrincipale;
    private controler.ControleurPrincipal controleur = null;
    private Label labelNom;
    private Label labelNiveau;
    private Button btnAmeliorer;
    private Button btnRetour;
    private Button btnAffecter;

    public VueGouverneur() {
        super(new GridPane(), 400,400);
        grillePrincipale = (GridPane) this.getRoot();
        btnRetour = new Button("Retour");
    }

    public void initialiserVueGouverneur(Gouverneur gouverneur) {
        grillePrincipale.getChildren().clear();
        btnRetour.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.notifierNaviguerMenuGouverneurs();
            }
        });
        btnAmeliorer = new Button("ameliorer");
        btnAmeliorer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.notifierAmeliorerGouverneur(gouverneur);
                controleur.notifierNaviguerAffichergouverneur(gouverneur);
            }
        });
        btnAffecter = new Button("affecter");
        labelNom = new Label(gouverneur.getNom());
        labelNiveau = new Label(Integer.toString(gouverneur.getNiveau()));
        grillePrincipale.add(labelNom,1,0);
        grillePrincipale.add(new Label("niveau : "),0,1);
        grillePrincipale.add(labelNiveau,1,1);
        grillePrincipale.add(new Label("effets : "),0,2);
        int compteurLigne = 2;
        for (Donnee donnee : gouverneur.getEffets().keySet()) {
            compteurLigne++;
            grillePrincipale.add(new Label(donnee.getTypeDonne()),1,compteurLigne);
            grillePrincipale.add(new Label(Double.toString(gouverneur.getEffets().get(donnee))),2,compteurLigne);
        }
        grillePrincipale.add(btnAmeliorer,2,1);
        grillePrincipale.add(btnAffecter,1,5);
        grillePrincipale.add(btnRetour,1,6);
    }

    public void setControleur(controler.ControleurPrincipal controleur) {
        this.controleur = controleur;
    }
}
