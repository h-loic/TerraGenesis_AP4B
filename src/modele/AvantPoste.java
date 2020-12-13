package modele;

import java.util.ArrayList;
import java.util.Random;

public class AvantPoste {
    private int id;//TODO : ajouter la gestion des id
    private String nom;
    private Coordonnee coordonnee;
    private ArrayList<Mine> mines;

    public AvantPoste(String nom, Coordonnee coordonnee, ArrayList<Mine> mines) {
        Random random = new Random();
        this.id = random.nextInt();
        this.nom = nom;
        this.coordonnee = coordonnee;
        this.mines = mines;
    }

    public void ajouterMine(Mine mine){
        this.mines.add(mine);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Coordonnee getCoordonnee() {
        return coordonnee;
    }

    public void setCoordonnee(Coordonnee coordonnee) {
        this.coordonnee = coordonnee;
    }

    public ArrayList<Mine> getMines() {
        return mines;
    }

    public int getId() {
        return id;
    }

    public void setMines(ArrayList<Mine> mines) {
        this.mines = mines;
    }

    public void detruireMine(int idAvantPoste) {
        this.mines.remove(getMine(idAvantPoste));
    }

    public Mine getMine(int idMine) {
        for (Mine mine : this.mines){
            if (mine.getId() == idMine){
                return mine;
            }
        }
        return null;
    }
}
