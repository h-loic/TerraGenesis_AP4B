package modele;

import java.util.Comparator;
import java.util.HashMap;

/**
 * <b>Gouverneur est la classe représentant les différents Gouverneur de la planète</b>
 * <p>
 * Un gouverneur est caractérisé par les informations suivantes :
 * <ul>
 * <li>Un etat pour savoir s'il est débloqué</li>
 * <li>Un niveau</li>
 * <li>Un nom</li>
 * <li>Un etat pour savoir s'il est affecter</li>
 * <li>Une ville a laquelle il est affecter</li>
 * <li>Une liste associative de Donnee (clé) avec la valeur de l'effet (valeur)</li>
 * </ul>
 * </p>
 *
 *
 * @author Loïc HUG - h-loic
 */

public class Gouverneur {

    private static final int PRIX_BASE_GOUVERNEUR = 25000;
    private static final int PRIX_BASE_AMELIORATION   = 1500;

    /**
     * estDebloque permet de savoir si le gouverneur est debloqué
     *
     * @see Gouverneur#Gouverneur(boolean, int, String, boolean, HashMap)
     * @see Gouverneur#estDebloque()
     */
    private boolean estDebloque;

    /**
     * Le niveau du gouverneur
     *
     * @see Gouverneur#Gouverneur(boolean, int, String, boolean, HashMap)
     * @see Gouverneur#getNiveau()
     */
    private int niveau;

    /**
     * Le nom du gouverneur
     *
     * @see Gouverneur#Gouverneur(boolean, int, String, boolean, HashMap)
     * @see Gouverneur#getNom()
     */
    private String nom;
    private boolean estAffecter;
    private Ville villeAffecter;
    private HashMap<Donnee, Double> effets;

    public Gouverneur(boolean estDebloque, int niveau, String nom, boolean estAffecter, HashMap<Donnee,Double> effets) {
        this.estDebloque = estDebloque;
        this.niveau = niveau;
        this.nom = nom;
        this.estAffecter = estAffecter;
        this.effets = effets;
    }

    public boolean estDebloque(){
        return  this.estDebloque;
    }

    public int getNiveau() {
        return niveau;
    }

    public String getNom() {
        return nom;
    }

    public boolean estAffecter() {
        return estAffecter;
    }

    public HashMap<Donnee, Double> getEffets() {
        return effets;
    }

    public Double getEffetsValeur(int index) {
        return effets.get(index);
    }

    public void setEstDebloque() {
        this.estDebloque = true;
    }

    public int getPrixBaseGouverneur() {
        return PRIX_BASE_GOUVERNEUR;
    }

    public int getPrixAmelioration() {
        return PRIX_BASE_AMELIORATION * this.niveau;
    }

    public void setEstAffecter(boolean estAffecter) {
        this.estAffecter = estAffecter;
    }

    public Ville getVilleAffecter() {
        return villeAffecter;
    }

    public void setVilleAffecter(Ville villeAffecter) {
        this.villeAffecter = villeAffecter;
    }

    public void ameliorer(){
        this.niveau = this.niveau + 1;
        for (Donnee donnee : effets.keySet()) {
            System.out.println(effets.get(donnee));
        }
        for (Donnee donnee : effets.keySet()) {
            this.effets.put(donnee, this.effets.get(donnee)*1.5);
        }
    }

    public static Comparator<Gouverneur> ComparatorNom = new Comparator<Gouverneur>() {
        @Override
        public int compare(Gouverneur gouv1, Gouverneur gouv2) {
            return gouv1.getNom().compareTo(gouv2.getNom());
        }
    };

    public static Comparator<Gouverneur> ComparatorDebloque = new Comparator<Gouverneur>() {
        @Override
        public int compare(Gouverneur gouv1, Gouverneur gouv2) {
            return Boolean.compare(gouv2.estDebloque, gouv1.estDebloque);
        }
    };
}
