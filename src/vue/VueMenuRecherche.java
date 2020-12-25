package vue;

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


public class VueMenuRecherche extends Scene {

    protected GridPane grillePrincipale;
    protected GridPane grilleBatiments;

    private ScrollPane scrollPaneBatiments;

    private controler.ControleurPrincipal controleur = null;
    private Label labelRecherche;
    private Label labelErreur;
    private Button boutonRetour;

    public VueMenuRecherche() {
        super(new GridPane(), 400,400);
        grillePrincipale = (GridPane) this.getRoot();
        grilleBatiments = new GridPane();
        this.labelRecherche = new Label("Recherche");
        this.labelErreur = new Label("");
        this.boutonRetour = new Button("retour");
        this.scrollPaneBatiments = new ScrollPane();
    }

    public void initialiserMenuRecherche(ArrayList<TypeBatiment> typeBatimentsNonDebloques) {
        this.grillePrincipale.getChildren().clear();
        this.grilleBatiments.getChildren().clear();
        this.scrollPaneBatiments.setMinWidth(400);

        labelErreur.setVisible(false);

        int rowindex = 1;

        for (TypeBatiment typeBatiment : typeBatimentsNonDebloques){
            GridPane grillDescription = new GridPane();
            GridPane grilleBouton = new GridPane();
            Label labelBatiment = new Label(typeBatiment.getNom() + ", prix : "+typeBatiment.getCoutRecherche());

            TextFlow textFlowEffet = new TextFlow();

            for (Map.Entry effet : typeBatiment.getEffetsParDefaut().entrySet()){
                Text text =  new Text("    |> " + effet.getKey() + ": " + effet.getValue() + "\n");
                if ((double) effet.getValue() < 0) text.setFill(Color.BLUE);
                else text.setFill(Color.RED);
                textFlowEffet.getChildren().add(text);
            }

            grillDescription.add(labelBatiment, 0, 0);
            grillDescription.add(textFlowEffet, 0, 1);

            Button btnRechercher = new Button("Rechercher");

            btnRechercher.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    labelErreur.setVisible(false);
                    try{
                        controleur.rechercherTypeBatiment(typeBatiment);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                        labelErreur.setText(e.getMessage());
                        labelErreur.setVisible(true);
                    }
                }
            });

            grilleBouton.add(btnRechercher, 0, 0);

            grilleBatiments.add(grillDescription, 0, rowindex);
            grilleBatiments.add(grilleBouton, 1, rowindex);

            rowindex++;
        }

        this.boutonRetour.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.notifierNaviguerMenuPrincipal();
            }
        });

        this.scrollPaneBatiments.setContent(grilleBatiments);
        this.grillePrincipale.add(this.labelRecherche, 0, 0);
        this.grillePrincipale.add(this.scrollPaneBatiments, 0, 1);
        this.grillePrincipale.add(this.labelErreur, 0, 2);
        this.grillePrincipale.add(this.boutonRetour, 0, 3);
    }

    public void setControleur(controler.ControleurPrincipal controleur) {
        this.controleur = controleur;
    }
}
