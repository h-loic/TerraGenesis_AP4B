package modele;

import javafx.scene.media.MediaException;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class Ville {

    private static final AtomicInteger sequence = new AtomicInteger();

    public static final double PRIX_BASE_VILLE = 500000;
    public static final double PALIER_POPULATION_NB_BATIMENT = 200.;

    private int id;
    private String nom;
    private ArrayList<Batiment> batiments;
    private Coordonnee coordonnee;
    private int nombrePlaceBatiment;
    private Gouverneur gouverneur;
    private Donnee population;
    private Donnee habitation;

    public Ville(String nom, Coordonnee coordonnee) {
        this.id = genererId();
        this.nom = nom;
        this.coordonnee = coordonnee;
        this.batiments = new ArrayList<>();
        this.batiments.add(new Batiment(TypeBatiment.UNITE_HABITATION));
        this.nombrePlaceBatiment = 3;
        this.gouverneur = null;
        this.population = new Donnee(TypeDonnee.POPULATION);
        this.habitation = new Donnee(TypeDonnee.HABITATION);
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

    public void affecterGouverneur(Gouverneur gouverneur) {
        this.gouverneur = gouverneur;
        this.gouverneur.setEstAffecter(true);
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

    public Donnee getPopulation() {
        return population;
    }

    public Donnee getHabitation() {
        return habitation;
    }

    public void setBatiments(ArrayList<Batiment> batiments) {
        this.batiments = batiments;
    }

    public void setNombrePlaceBatiment(int nombrePlaceBatiment) {
        this.nombrePlaceBatiment = nombrePlaceBatiment;
    }

    public void setPopulation(Donnee population) {
        this.population = population;
    }

    public void setHabitation(Donnee habitation) {
        this.habitation = habitation;
    }

    public boolean peutConstruire() {
        return this.getNombrePlaceBatiment() > this.batiments.size();
    }

    public void ajouterBatiment(Batiment batiment) {
        if (!peutConstruire()) return;
        this.batiments.add(batiment);
    }

    public Batiment getBatiment(int idBatiment) {
        for (Batiment batiment : batiments) {
            if (batiment.getId() == idBatiment) return batiment;
        }
        return null;
    }

    public void majVille() {
        double sommeHab = 0.;
        double sommePop = this.population.getValeurActuelle() + 1.;
        for (Batiment batiment : batiments) {
            if (batiment.getEffets().containsKey(TypeDonnee.HABITATION) && !batiment.estDesactive()) sommeHab += batiment.getEffets().get(TypeDonnee.HABITATION);
            if (batiment.getEffets().containsKey(TypeDonnee.POPULATION) && !batiment.estDesactive() ) {
                sommeHab += batiment.getEffets().get(TypeDonnee.POPULATION);
                sommePop += batiment.getEffets().get(TypeDonnee.POPULATION);
            }
        }
        if(sommePop > sommeHab) {
            sommePop = sommeHab;
        }
        this.population.setValeurActuelle(sommePop);
        this.habitation.setValeurActuelle(sommeHab);
        majNbPlace();
    }

    private void majNbPlace() {
        nombrePlaceBatiment = 3 + Math.floorDiv((int)population.getValeurActuelle(), (int) PALIER_POPULATION_NB_BATIMENT);
        if (nombrePlaceBatiment < batiments.size()) nombrePlaceBatiment = batiments.size();

    }
    public int getNbPlaceRestante() {
        return this.nombrePlaceBatiment - batiments.size();
    }
    public double getProchainPallierBatiment() {
        return (this.nombrePlaceBatiment - 2) * PALIER_POPULATION_NB_BATIMENT;
    }

    public void detruireBatiment(int idBatiment) {
        this.batiments.remove(getBatiment(idBatiment));
    }
}
