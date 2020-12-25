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

    private GridPane grilleBoutons;
    private GridPane grillePrincipale;
    private GridPane grilleBatiments;
    private GridPane grilleRechercheEnCours;

    private ScrollPane scrollPaneBatiments;

    private controler.ControleurPrincipal controleur = null;
    private Label labelRecherche;
    private Label labelErreur;
    private Label labelRechercheEnCours;
    private Button btnRetour;
    private Button btnActualiser;

    public VueMenuRecherche() {
        super(new GridPane(), 400,400);
        this.grillePrincipale = (GridPane) this.getRoot();
        this.grilleBatiments = new GridPane();
        this.grilleRechercheEnCours = new GridPane();
        this.grilleBoutons = new GridPane();
        this.labelRecherche = new Label("Recherche");
        this.labelRechercheEnCours = new Label("");
        this.labelErreur = new Label("");
        this.btnRetour = new Button("retour");
        this.scrollPaneBatiments = new ScrollPane();
        this.btnActualiser = new Button("Actualiser");
    }

    public void initialiserMenuRecherche(ArrayList<TypeBatiment> typeBatimentsNonDebloques, boolean rechercheEnCours, TypeBatiment batimentRecherche) {
        this.grillePrincipale.getChildren().clear();
        this.grilleBatiments.getChildren().clear();
        this.grilleRechercheEnCours.getChildren().clear();
        this.grilleBoutons.getChildren().clear();
        this.scrollPaneBatiments.setMinWidth(400);

        labelErreur.setVisible(false);

        int rowindex = 1;

        for (TypeBatiment typeBatiment : typeBatimentsNonDebloques){

            if (typeBatiment == batimentRecherche) continue;//saute cette itération pour ne pas afficher le type de batiment actuellement recherché dans la lsite des batiments recherchés

            GridPane grillDescription = new GridPane();
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

            if (rechercheEnCours){
                btnRechercher.setDisable(true);
            }

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

            grilleBatiments.add(grillDescription, 0, rowindex);
            grilleBatiments.add(btnRechercher, 1, rowindex);

            rowindex++;
        }


        this.btnRetour.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.notifierNaviguerMenuPrincipal();
            }
        });

        if (rechercheEnCours) {
            this.labelErreur.setText("Recherche en cours, impossible de faire une nouvelle recherche");
            this.labelErreur.setVisible(true);
            this.labelRechercheEnCours.setText("Recherche en cours  : "+batimentRecherche.getNom());
            this.grilleRechercheEnCours.add(labelRechercheEnCours, 0, 0);
            this.grillePrincipale.add(this.grilleRechercheEnCours, 0, 3);
        }

        this.btnActualiser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleur.notifierActualiserMenuRecherche();
            }
        });

        this.scrollPaneBatiments.setContent(grilleBatiments);
        this.grillePrincipale.add(this.labelRecherche, 0, 0);
        this.grillePrincipale.add(this.scrollPaneBatiments, 0, 1);
        this.grillePrincipale.add(this.labelErreur, 0, 2);
        this.grilleBoutons.add(this.btnRetour, 0, 0);
        this.grilleBoutons.add(this.btnActualiser, 1, 0);
        this.grillePrincipale.add(this.grilleBoutons, 0, 4);
    }

    public void setControleur(controler.ControleurPrincipal controleur) {
        this.controleur = controleur;
    }
}
