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

import java.util.ArrayList;

public class VueAffecterGouverneur extends Scene{

    /**
     *  Constante definissant le style des titres de la vue
     *
     * @see VueMenuGouverneurs#VueMenuGouverneurs()
     * @see VueMenuGouverneurs#initialiserMenuGouverneurs(ArrayList)
     */
    public static final String STYLE_TITRE = " -fx-font-size: 14; -fx-font-weight: bold; -fx-padding: 15px";

    /**
     *  Constante definissant le style des boutons de la vue
     *
     * @see VueMenuGouverneurs#VueMenuGouverneurs()
     * @see VueMenuGouverneurs#initialiserMenuGouverneurs(ArrayList)
     */
    public static final String STYLE_BOUTONS = "-fx-background-color: #25467F; -fx-text-fill: white; -fx-font-size: 12; -fx-font-weight: bold;-fx-min-width: 80;";

    protected GridPane grillePrincipale;
    private controler.ControleurPrincipal controleur = null;
    private Button btnRetour;

    public VueAffecterGouverneur() {
        super(new GridPane(), 500,400);
        grillePrincipale = (GridPane) this.getRoot();
        btnRetour = new Button("Retour");
        this.btnRetour.setStyle(STYLE_BOUTONS);
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
        labelTitre.setStyle(STYLE_TITRE);
        grillePrincipale.add(labelTitre,1,0);
        int compteurLigne = 1;
        for (Ville ville : planete.getVilles()) {
            compteurLigne++;
            Button btnSelectionnerVille = new Button(ville.getNom());
            btnSelectionnerVille.setStyle(STYLE_BOUTONS);
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
        grillePrincipale.add(btnRetour,2,compteurLigne);
    }

    public void setControleur(controler.ControleurPrincipal controleur) {
        this.controleur = controleur;
    }
}
