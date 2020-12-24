package modele;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Batiment {

    private static final AtomicInteger sequence = new AtomicInteger();
    private int id;
    private TypeBatiment typeBatiment;
    private int niveau;
    private boolean estDesactive;
    private HashMap<TypeDonnee, Double> effets;
    private Date dateFinConstruction;

    public Batiment(TypeBatiment typeBatiment) {
        this.id = genererId();
        this.typeBatiment = typeBatiment;
        this.niveau = 1;
        this.estDesactive = false;
        this.effets = typeBatiment.getEffetsParDefaut();

    }

    private int genererId() {
        return sequence.incrementAndGet();
    }

    public int getId() {
        return this.id;
    }

    public TypeBatiment getTypeBatiment() {
        return typeBatiment;
    }

    public int getNiveau() {
        return niveau;
    }

    public boolean isEstDesactive() {
        return estDesactive;
    }

    public HashMap<TypeDonnee, Double> getEffets() {
        return effets;
    }

    public Date getDateFinConstruction() {
        return dateFinConstruction;
    }

    public void ameliorer() {
        this.niveau++;
        TypeDonnee typeDonnee;
        double valeur;
        for (Map.Entry variation : this.typeBatiment.getVariations().entrySet()) {
            typeDonnee = (TypeDonnee) variation.getKey();
            valeur = (double) variation.getValue();
            effets.put(typeDonnee, effets.getOrDefault(typeDonnee, 0.0) + valeur);
        }
    }

    public double getPrixAmelioration() {
        return this.typeBatiment.getCoutConstructionParDefaut() * this.niveau;
    }


}
