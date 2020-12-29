package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import modele.Donnee;
import modele.Gouverneur;

public class VueGouverneur extends Scene{

    protected GridPane grillePrincipale;
    private controler.ControleurPrincipal controleur = null;
    private Label labelNom;
    private Label labelNiveau;
    private Label labelAffecter;
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
                controleur.notifierNaviguerAfficherGouverneur(gouverneur);
            }
        });
        labelNom = new Label(gouverneur.getNom());
        labelNiveau = new Label(Integer.toString(gouverneur.getNiveau()));
        if (!gouverneur.estAffecter()){
            labelAffecter = new Label("non affecter");
            btnAffecter = new Button("affecter");
            btnAffecter.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    controleur.notifierNaviguerAffecterGouverneur(gouverneur);
                }
            });
        }else{
            System.out.println(gouverneur.getVilleAffecter());
            labelAffecter = new Label(gouverneur.getVilleAffecter().getNom());
            btnAffecter = new Button("révoquer");
            btnAffecter.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    controleur.notifierRevoquerGouverneurDepuisGouverneur(gouverneur.getVilleAffecter().getId(),gouverneur);
                }
            });
        }
        grillePrincipale.add(labelNom,1,0);
        grillePrincipale.add(new Label("niveau : "),0,1);
        grillePrincipale.add(labelNiveau,1,1);
        grillePrincipale.add(new Label("affecter : "),0,2);
        grillePrincipale.add(labelAffecter,1,2);
        grillePrincipale.add(new Label("effets : "),0,3);
        int compteurLigne = 3;
        for (Donnee donnee : gouverneur.getEffets().keySet()) {
            compteurLigne++;
            grillePrincipale.add(new Label(donnee.getTypeDonnee().name()),1,compteurLigne);
            grillePrincipale.add(new Label(Double.toString(gouverneur.getEffets().get(donnee))),2,compteurLigne);
        }
        grillePrincipale.add(btnAmeliorer,2,1);
        grillePrincipale.add(btnAffecter,1,compteurLigne+1);
        grillePrincipale.add(btnRetour,1,compteurLigne+2);
    }
    public void setControleur(controler.ControleurPrincipal controleur) {
        this.controleur = controleur;
    }
}
