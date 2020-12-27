package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import modele.Donnee;
import modele.Gouverneur;
import modele.Planete;
import modele.Ville;

public class VueAffecterGouverneur extends Scene{

    protected GridPane grillePrincipale;
    private controler.ControleurPrincipal controleur = null;
    private Button btnRetour;

    public VueAffecterGouverneur() {
        super(new GridPane(), 400,400);
        grillePrincipale = (GridPane) this.getRoot();
        btnRetour = new Button("Retour");
    }

    public void initialiserVueAffecterGouverneur(Gouverneur gouverneur, Planete planete) {
        grillePrincipale.getChildren().clear();
        btnRetour.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.notifierNaviguerAfficherGouverneur(gouverneur);
            }
        });
        Label labelTitre = new Label("choisissez la ville dans laquelle affecter " + gouverneur.getNom());
        grillePrincipale.add(labelTitre,1,0);
        int compteurLigne = 1;
        for (Ville ville : planete.getVilles()) {
            compteurLigne++;
            Button btnSelectionnerVille = new Button(ville.getNom());
            grillePrincipale.add(btnSelectionnerVille,1,compteurLigne);
            if (ville.getGouverneur() == null){
                grillePrincipale.add(new Label("pas de gouverneur"),2,compteurLigne);
            }else{
                grillePrincipale.add(new Label(ville.getGouverneur().getNom()),2,compteurLigne);
            }
            btnSelectionnerVille.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    controleur.notifierAffecterGouverneur(gouverneur,ville.getId());
                }
            });
        }
        compteurLigne++;
        grillePrincipale.add(btnRetour,1,compteurLigne);
    }

    public void setControleur(controler.ControleurPrincipal controleur) {
        this.controleur = controleur;
    }
}
