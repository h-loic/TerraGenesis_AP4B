package modele;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Carte {
    private Canvas canvasCarte;
    private GraphicsContext graphicsContextCarte;

    private ArrayList<Coordonnee> coordsMines;
    private ArrayList<Coordonnee> coordsAvPostes;
    private ArrayList<Coordonnee> coordsVilles;
    private static final int DISTANCE_MINI = 30; // distance minimum entre deux avant-postes ou villes

    public Carte(ArrayList<Coordonnee> coordsMines, ArrayList<Coordonnee> coordsAvPostes, ArrayList<Coordonnee> coordsVilles) {
        this.coordsMines = coordsMines;
        this.coordsAvPostes = coordsAvPostes;
        this.coordsVilles = coordsVilles;
        this.canvasCarte = new Canvas(500, 250);
        this.graphicsContextCarte = canvasCarte.getGraphicsContext2D();
        this.dessiner();
    }

    private void dessiner() {
        graphicsContextCarte.clearRect(0, 0, canvasCarte.getWidth(), canvasCarte.getHeight());
        graphicsContextCarte.setFill(Color.ORANGERED);
        graphicsContextCarte.fillRect(0, 0, canvasCarte.getWidth(), canvasCarte.getHeight());
        this.dessinerMines();
        this.dessinerAvPostes();
        this.dessinerVilles();
    }

    private void dessinerVilles(){
        graphicsContextCarte.setFill(Color.GRAY);
        for (Coordonnee coord : coordsMines){
            graphicsContextCarte.fillOval(coord.getX(), coord.getY(), 10,10);
        }
    }

    private void dessinerAvPostes() {
        graphicsContextCarte.setFill(Color.BLUE);
        for (Coordonnee coord : coordsAvPostes){
            graphicsContextCarte.fillRect(coord.getX(), coord.getY(), 10,10);
        }
    }

    private void dessinerMines() {
        graphicsContextCarte.setFill(Color.GREEN);
        for (Coordonnee coord : coordsVilles){
            graphicsContextCarte.fillRect(coord.getX(), coord.getY(), 10,10);
        }
    }

    public Canvas getCanvas() {
        return canvasCarte;
    }

    public void ajouterVilleCarte(Coordonnee coords){
        this.coordsVilles.add(coords);
        this.dessiner();
    }

    public void ajouterAvPosteCarte(Coordonnee coords){
        this.coordsAvPostes.add(coords);
        this.dessiner();
    }

    public void effacerAvPosteCarte(Coordonnee coords){
        this.coordsAvPostes.remove(coords);
        this.dessiner();
    }

    public void effacerVilleCarte(Coordonnee coords){
        this.coordsVilles.remove(coords);
        this.dessiner();
    }

    public boolean verifierCoordonneesAvPoste(double x, double y) {
        this.dessiner();
        for (Coordonnee coords : coordsAvPostes){
            if (Math.abs((coords.getX()-x))<=DISTANCE_MINI && Math.abs((coords.getY()-y))<=DISTANCE_MINI){
                System.out.println("trop proche avant-poste");
                return false;
            }
        }
        for (Coordonnee coords : coordsVilles){
            if (Math.abs((coords.getX()-x))<=DISTANCE_MINI && Math.abs((coords.getY()-y))<=DISTANCE_MINI){
                System.out.println("trop proche ville");
                return false;
            }
        }

        graphicsContextCarte.setFill(Color.BLUE);
        graphicsContextCarte.fillRect(x, y, 10,10);

        return true;
    }

    public boolean verifierCoordonneesVille(double x, double y) {
        this.dessiner();
        for (Coordonnee coords : coordsAvPostes){
            if (Math.abs((coords.getX()-x))<=DISTANCE_MINI && Math.abs((coords.getY()-y))<=DISTANCE_MINI){
                System.out.println("trop proche avant-poste");
                return false;
            }
        }
        for (Coordonnee coords : coordsVilles){
            if (Math.abs((coords.getX()-x))<=DISTANCE_MINI && Math.abs((coords.getY()-y))<=DISTANCE_MINI){
                System.out.println("trop proche ville");
                return false;
            }
        }

        graphicsContextCarte.setFill(Color.GREEN);
        graphicsContextCarte.fillRect(x, y, 10,10);

        return true;
    }
}
