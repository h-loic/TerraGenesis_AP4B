package modele;

import java.util.HashMap;

public class Gouverneur {
    private boolean estDebloque;
    private int niveau;
    private String nom;
    private boolean estAffecter;
    private HashMap<Donnee, Double> effets;

    public Gouverneur(boolean estDebloque, int niveau, String nom, boolean estAffecter, HashMap<Donnee,Double> effets) {
        this.estDebloque = estDebloque;
        this.niveau = niveau;
        this.nom = nom;
        this.estAffecter = estAffecter;
        this.effets = effets;
    }

    public int getNiveau() {
        return niveau;
    }

    public String getNom() {
        return nom;
    }

    public boolean isEstAffecter() {
        return estAffecter;
    }

    public HashMap<Donnee, Double> getEffets() {
        return effets;
    }

    public Double getEffetsValeur(int index) {
        return effets.get(index);
    }

    public void setEstDebloque(boolean estDebloque) {
        this.estDebloque = estDebloque;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public void setEstAffecter(boolean estAffecter) {
        this.estAffecter = estAffecter;
    }

    public void ameliorer(){
        this.niveau = this.niveau + 1;
        for (Donnee donnee : effets.keySet()) {
            System.out.println(effets.get(donnee));
        }
        /*
        for (Donnee donnee : effets.keySet()) {
            donnee.setCroissance(donnee.getCroissance() - effets.get(donnee));
        }
        for (Donnee donnee : effets.keySet()) {
            effets.get(donnee) = 2.2;
        }
        */
    }


}
