package modele;

import java.util.Calendar;
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
    private boolean estEnCoursAmelioration;
    private Date dateFinAmelioration;
    private HashMap<TypeDonnee, Double> effets;

    public Batiment(TypeBatiment typeBatiment) {
        this.id = genererId();
        this.typeBatiment = typeBatiment;
        this.niveau = 1;
        this.estDesactive = false;
        this.dateFinAmelioration = new Date();
        this.mettreEnConstruction(typeBatiment.getTempsConstructionParDefaut());
        this.effets = new HashMap<TypeDonnee, Double> (typeBatiment.getEffetsParDefaut());

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

    public boolean estDesactive() {
        return estDesactive;
    }

    public boolean estEnCoursAmelioration() {
        return estEnCoursAmelioration;
    }

    public HashMap<TypeDonnee, Double> getEffets() {
        return effets;
    }

    public void mettreEnConstruction(int temps) {

    }

    public boolean peutAmeliorer() {
        return this.niveau < typeBatiment.getNiveauMax();
    }


    public void activerDesactiver() {
        this.estDesactive = !this.estDesactive;
    }

    public void demarrerAmelioration() {
        if (!peutAmeliorer()) return;
        this.dateFinAmelioration = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(this.dateFinAmelioration);
        c.add(Calendar.MINUTE, + getTempsConstructionAmelioration());
        this.dateFinAmelioration = c.getTime();
        this.estEnCoursAmelioration = true;
    }

    public void finirAmelioration() {
        if (!peutAmeliorer()) return;
        this.dateFinAmelioration = null;
        this.estEnCoursAmelioration = false;
        this.niveau++;
        TypeDonnee typeDonnee;
        double effetParDefaut;
        double resultat;
        for (Map.Entry<TypeDonnee, Double> effet : effets.entrySet()) {
            typeDonnee = effet.getKey();
            effetParDefaut = this.typeBatiment.getEffetsParDefaut().get(typeDonnee);
            resultat = effet.getValue() + (effetParDefaut / 2.);
            effet.setValue(resultat);
        }

    }

    public double getPrixAmelioration() {
        return this.typeBatiment.getCoutConstructionParDefaut() * this.niveau;
    }

    public int getTempsConstructionAmelioration() {
        return this.typeBatiment.getTempsConstructionParDefaut() + ((this.typeBatiment.getTempsConstructionParDefaut()/4) * this.niveau);
    }

    public Date getDateFinAmelioration() {
        return this.dateFinAmelioration;
    }


}
