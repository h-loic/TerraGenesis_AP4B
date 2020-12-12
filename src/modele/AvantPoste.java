package modele;

import java.util.ArrayList;

public class AvantPoste {
    private String nom;
    private Coordonnee coordonnee;
    private ArrayList<Mine> mines;

    public AvantPoste(String nom, Coordonnee coordonnee, ArrayList<Mine> mines) {
        this.nom = nom;
        this.coordonnee = coordonnee;
        this.mines = mines;
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

    public void setMines(ArrayList<Mine> mines) {
        this.mines = mines;
    }
}
