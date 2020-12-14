package modele;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class Ville {

    private static final AtomicInteger sequence = new AtomicInteger();

    private int id;
    private String nom;
    private ArrayList<Batiment> batiments;
    private Coordonnee coordonnee;
    private int nombrePlaceBatiment;
    private Gouverneur gouverneur;
    private int population;
    private int habitation;

    public Ville(String nom, Coordonnee coordonnee) {
        this.id = genererId();
        this.nom = nom;
        this.coordonnee = coordonnee;
        this.batiments = new ArrayList<>();
        this.nombrePlaceBatiment = 1;
        this.gouverneur = null;
        this.population = 0;
        this.habitation = 0;
    }

    private int genererId() {
        return sequence.incrementAndGet();
    }

    public int getId() {
        return this.id;
    }

    public void assignerGouverneur(Gouverneur gouverneur) {
        gouverneur.setEstAffecter(true);
        this.gouverneur = gouverneur;
    }

    public void revoquerGouverneur() {
        this.gouverneur.setEstAffecter(false);
        this.gouverneur = null;
    }

    public String getNom() {
        return nom;
    }

    public ArrayList<Batiment> getBatiments() {
        return batiments;
    }

    public Coordonnee getCoordonnee() {
        return coordonnee;
    }

    public int getNombrePlaceBatiment() {
        return nombrePlaceBatiment;
    }

    public Gouverneur getGouverneur() {
        return gouverneur;
    }

    public int getPopulation() {
        return population;
    }

    public int getHabitation() {
        return habitation;
    }

    public void setBatiments(ArrayList<Batiment> batiments) {
        this.batiments = batiments;
    }

    public void setNombrePlaceBatiment(int nombrePlaceBatiment) {
        this.nombrePlaceBatiment = nombrePlaceBatiment;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setHabitation(int habitation) {
        this.habitation = habitation;
    }
}
