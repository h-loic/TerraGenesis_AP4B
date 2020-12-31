package modele;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import vue.VueMenuCarte;

import java.util.ArrayList;

/**
 * <b>
 *     Modele permettant de representer et manipuler la Carte de la planete.
 *     Cette carte permet de visualiser les avant-postes et les villes de la planete,
 *     et de les poisitonner lors de l'ajout de ceux-ci
 * </b>
 * <p>
 * Cette vue est caracterisee par :
 * <ul>
 * <li>Un Canvas sur lequel est dessinee la carte</li>
 * <li>Le contexte graphique du canvas permettant d'y dessiner des elements</li>
 * <li>La liste des coordonnees des mines</li>
 * <li>La liste des coordonnees des villes</li>
 * <li>La liste des coordonnees des avant-postes</li>
 * </ul>
 * </p>
 *
 * @see modele.Carte
 *
 * @author Zapolatero - lpascuzzi
 * */

public class Carte {

    /**
     * La grille sur laquelle sont ajoutes les differents elements de la vue
     *
     * @see Carte#Carte(ArrayList, ArrayList, ArrayList)
     * @see Carte#getCanvas()
     * @see Carte#dessiner()
     * @see Carte#dessinerAvPostes()
     * @see Carte#dessinerMines()
     * @see Carte#dessinerVilles()
     * */
    private Canvas canvasCarte;

    /**
     * Contexte graphique du canvas
     *
     * @see Carte#Carte(ArrayList, ArrayList, ArrayList)
     * @see Carte#canvasCarte
     * */
    private GraphicsContext graphicsContextCarte;

    /**
     * Coordonnees des mines de la planete
     *
     * @see Carte#Carte(ArrayList, ArrayList, ArrayList)
     * @see Mine
     * @see Coordonnee
     * */
    private ArrayList<Coordonnee> coordsMines;

    /**
     * Coordonnees des avant-postes de la planete
     *
     * @see Carte#Carte(ArrayList, ArrayList, ArrayList)
     * @see AvantPoste
     * @see Coordonnee
     * */
    private ArrayList<Coordonnee> coordsAvPostes;

    /**
     * Coordonnees des villes de la planete
     *
     * @see Carte#Carte(ArrayList, ArrayList, ArrayList)
     * @see Ville
     * @see Coordonnee
     * */
    private ArrayList<Coordonnee> coordsVilles;



    /**
     * Constante indiquant la distance minimale entre deux villes ou avant-postes
     *
     * @see Carte#Carte(ArrayList, ArrayList, ArrayList)
     * @see Mine
     * @see Coordonnee
     * */
    private static final int DISTANCE_MINI = 30; // distance minimum entre deux avant-postes ou villes

    /**
     * Constructeur Carte
     * <p>
     * A la construction d'un objet Carte, le constructeur cree un canvas sur lequel seront dessinees les coordonnees des avant-postes et des villes de la planetes
     * les coordonnees des avant-postes, mines et villes sont stockees dans des attributs.
     * </p>
     *
     * @param coordsMines coordonnees des differentes mines de la planete
     *
     * @param coordsAvPostes coordonnees des avant-postes de la planete
     *
     * @param coordsVilles coordonnees des villes de la planete
     *
     *
     * @see Carte#coordsMines
     * @see Carte#coordsAvPostes
     * @see Carte#coordsVilles
     * @see Carte#canvasCarte
     * @see Carte#graphicsContextCarte
     * @see Carte#dessiner()
     * */
    public Carte(ArrayList<Coordonnee> coordsMines, ArrayList<Coordonnee> coordsAvPostes, ArrayList<Coordonnee> coordsVilles) {
        this.coordsMines = coordsMines;
        this.coordsAvPostes = coordsAvPostes;
        this.coordsVilles = coordsVilles;
        this.canvasCarte = new Canvas(500, 250);
        this.graphicsContextCarte = canvasCarte.getGraphicsContext2D();
        this.dessiner();
    }

    /**
     * Dessine la carte
     *  <p>
     *      efface ce qui est dessine sur le canvas, dessine un fond orange puis appelle les fonctions permettant
     *      de dessiner la position des avant-postes, mines et villes.
     *  </p>
     *
     * @see Carte#dessinerMines()
     * @see Carte#dessinerVilles()
     * @see Carte#dessinerAvPostes()
     * */
    public void dessiner() {
        //efface ce qui est dessine sur le canvas
        graphicsContextCarte.clearRect(0, 0, canvasCarte.getWidth(), canvasCarte.getHeight());
        //dessine un fond orange sur tout le canvas
        graphicsContextCarte.setFill(Color.ORANGERED);
        graphicsContextCarte.fillRect(0, 0, canvasCarte.getWidth(), canvasCarte.getHeight());
        this.dessinerMines();
        this.dessinerAvPostes();
        this.dessinerVilles();
    }

    /**
     * Dessine la position des villes sur la carte
     *  <p>
     *      parcours la liste de coordonnees des villes pour les dessiner sur la carte.
     *      Les villes sont representees par des carres verts de 10*10px
     *  </p>
     *
     * @see Carte#dessiner()
     * @see Carte#coordsVilles
     * */
    private void dessinerVilles(){
        graphicsContextCarte.setFill(Color.GREEN);//met la couleur du pinceau en vert
        for (Coordonnee coord : coordsVilles){
            graphicsContextCarte.fillRect(coord.getX(), coord.getY(), 10,10);
        }
    }

    /**
     * Dessine la position des Avant-Postes sur la carte
     *  <p>
     *      parcours la liste de coordonnees des avant-postes pour les dessiner sur la carte.
     *      Les avant-postes sont representees par des carres bleus de 10*10px
     *  </p>
     *
     * @see Carte#dessiner()
     * @see Carte#coordsAvPostes
     * */
    private void dessinerAvPostes() {
        graphicsContextCarte.setFill(Color.BLUE);//met la couleur du pinceau en bleu
        for (Coordonnee coord : coordsAvPostes){
            graphicsContextCarte.fillRect(coord.getX(), coord.getY(), 10,10);
        }
    }

    /**
     * Dessine la position des mines sur la carte
     *  <p>
     *      parcours la liste de coordonnees des mines pour les dessiner sur la carte.
     *      Les mines sont representees par des cercles gris de 10px de diametre
     *  </p>
     *
     * @see Carte#dessiner()
     * @see Carte#coordsMines
     * */
    private void dessinerMines() {
        graphicsContextCarte.setFill(Color.GRAY);//met la couleur du pinceau en gris
        for (Coordonnee coord : coordsMines){
            graphicsContextCarte.fillOval(coord.getX(), coord.getY(), 10,10);
        }
    }

    /**
     * Accesseur du canvas de la carte
     *
     * @return le canvas de la carte
     *
     * @see Carte#canvasCarte
     * */
    public Canvas getCanvas() {
        return canvasCarte;
    }

    /**
     * Ajoute les coordonnees d'une nouvelle ville à la liste des coordonnees de villes de la carte
     *
     * @see Carte#dessinerVilles() ()
     * @see Carte#coordsVilles
     * */
    public void ajouterVilleCarte(Coordonnee coords){
        this.coordsVilles.add(coords);
        this.dessiner();
    }

    /**
     * Ajoute les coordonnees d'un nouvel avant-poste à la liste des coordonnees d'avant-postes de la carte
     *
     * @see Carte#dessinerAvPostes()
     * @see Carte#coordsAvPostes
     * */
    public void ajouterAvPosteCarte(Coordonnee coords){
        this.coordsAvPostes.add(coords);
        this.dessiner();
    }

    /**
     * Retire les coordonnees d'un avant-poste de la liste des coordonnees d'avant-postes de la carte
     * <p>
     *     retire les coordonnees de l'avant-poste de la liste et redessine la carte
     * </p>
     *
     * @param coords coordonnees de l'avant-poste à effacer
     *
     * @see Carte#dessinerAvPostes()
     * @see Carte#coordsAvPostes
     * */
    public void effacerAvPosteCarte(Coordonnee coords){
        this.coordsAvPostes.remove(coords);
        this.dessiner();
    }

    /**
     * Retire les coordonnees d'une ville de la liste des coordonnees de villes de la carte
     * <p>
     *     Retire les coordonnees de la ville de la liste et redessine la carte
     * </p>
     *
     * @param coords coordonnees de la ville à effacer
     *
     * @see Carte#dessinerVilles()
     * @see Carte#coordsVilles
     * */
    public void effacerVilleCarte(Coordonnee coords){
        this.coordsVilles.remove(coords);
        this.dessiner();
    }



    /**
     * Verifie si les coordonnees donnees en parametre ne sont pas trop proches d'un avant-poste ou d'une ville
     * <p>
     *     Cette fonction est utilisee lors de l'ajout d'un avant-poste ou d'une ville pour savoir si la position entree
     *     par le joueur est valide. Si les donnees sont valides, un point va etre affichee sur la carte pour montrer la position
     *     du futur avant-poste/ville, ce point sera supprime une fois la page d'ajout quittee
     * </p>
     *
     * @param x longitude de la position entree par le joueur
     * @param y latitude de la position entree par le joueur
     *
     * @see Coordonnee
     * @see Carte#DISTANCE_MINI
     * */
    public boolean verifierCoordonnees(double x, double y) {
        this.dessiner();
        for (Coordonnee coords : coordsAvPostes){
            //verifie que les coordonnees entrees respectent la distance minimale avec un avant-poste
            if (Math.abs((coords.getX()-x))<=DISTANCE_MINI && Math.abs((coords.getY()-y))<=DISTANCE_MINI){
                System.out.println("trop proche avant-poste");
                return false;
            }
        }
        for (Coordonnee coords : coordsVilles){
            //verifie que les coordonnees entrees respectent la distance minimale avec une ville
            if (Math.abs((coords.getX()-x))<=DISTANCE_MINI && Math.abs((coords.getY()-y))<=DISTANCE_MINI){
                System.out.println("trop proche ville");
                return false;
            }
        }

        //dessine le point montrant la position entree
        graphicsContextCarte.setFill(Color.CYAN);
        graphicsContextCarte.fillOval(x, y, 10,10);

        return true;
    }

}
