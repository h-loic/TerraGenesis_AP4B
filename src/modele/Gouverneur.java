package modele;

import java.util.Comparator;
import java.util.HashMap;

/**
 * <b>Gouverneur est la classe representant les differents Gouverneur de la planete</b>
 * <p>
 * Un gouverneur est caracterise par les informations suivantes :
 * <ul>
 * <li>Un etat pour savoir s'il est debloque</li>
 * <li>Un niveau</li>
 * <li>Un nom</li>
 * <li>Un etat pour savoir s'il est affecter</li>
 * <li>Une ville a laquelle il est affecter</li>
 * <li>Une liste associative de Donnee (cle) avec la valeur de l'effet (valeur)</li>
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
     * estDebloque permet de savoir si le gouverneur est debloque
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

    /**
     * estAffecter permet de savoir si le gouverneur est affecter
     *
     * @see Gouverneur#Gouverneur(boolean, int, String, boolean, HashMap)
     * @see Gouverneur#estAffecte()
     */
    private boolean estAffecter;

    /**
     * La ville affecter par le gouverneur
     *
     * @see Gouverneur#getVilleAffecter()
     * @see Gouverneur#setVilleAffecter(Ville)
     */
    private Ville villeAffecter;

    /**
     * La liste des effets du gouverneur
     *
     * @see Gouverneur#Gouverneur(boolean, int, String, boolean, HashMap)
     * @see Gouverneur#getEffets()
     */
    private HashMap<Donnee, Double> effets;


    /**
     * Constructeur Gouverneur.
     * <p>
     * A la construction d'un objet Gouverneur, le nom et le niveau l'etat debloque et affecter ainsi que sa liste d'effets
     * sont initialises avec les valeurs passees en
     * parametres.
     * </p>
     *
     * @param estDebloque etat debloque du gouverneur
     * @param niveau le niveau du gouverneur
     * @param nom le nom du gouverneur
     * @param estAffecter etat de l'affectation du gouverneur
     * @param effets la liste des effets du gouverneur
     *
     * @see Gouverneur#estAffecter
     * @see Gouverneur#estDebloque
     * @see Gouverneur#effets
     * @see Gouverneur#niveau
     * @see Gouverneur#nom
     *
     */
    public Gouverneur(boolean estDebloque, int niveau, String nom, boolean estAffecter, HashMap<Donnee,Double> effets) {
        this.estDebloque = estDebloque;
        this.niveau = niveau;
        this.nom = nom;
        this.estAffecter = estAffecter;
        this.effets = effets;
    }

    /**
     * Accesseur de l'etat debloque du gouverneur
     *
     * @return  l'etat debloaue du gouverneur
     */
    synchronized public boolean estDebloque(){
        return  this.estDebloque;
    }

    /**
     * Accesseur du niveau du gouverneur
     *
     * @return  le niveau du gouverneur
     */
    synchronized public int getNiveau() {
        return niveau;
    }

    /**
     * Accesseur du nom du gouverneur
     *
     * @return  le nom du gouverneur
     */
    synchronized public String getNom() {
        return nom;
    }

    /**
     * Accesseur de l'etat d'affectation du gouverneur
     *
     * @return  l'etat d'affectation du gouverneur
     */
    synchronized public boolean estAffecte() {
        return estAffecter;
    }

    /**
     * Accesseur de la liste l'effets du gouverneur
     *
     * @return  la liste d'effets du gouverneur
     */
    synchronized public HashMap<Donnee, Double> getEffets() {
        return effets;
    }

    /**
     * attribut l'etat true a l'attribut estDebloque
     */
    synchronized public void setEstDebloque() {
        this.estDebloque = true;
    }

    /**
     * Accesseur du prix de base du gouverneur
     *
     * @return  le prix de base du gouverneur
     */
    synchronized public int getPrixBaseGouverneur() {
        return PRIX_BASE_GOUVERNEUR;
    }

    /**
     * Retourne le prix de l'amelioration du gouverneur en fonction de son niveau actuel
     *
     * @return  le prix de l'amelioration du gouverneur
     */
    synchronized public int getPrixAmelioration() {
        return PRIX_BASE_AMELIORATION * this.niveau;
    }

    /**
     * Attribut un etat a l'attribut estAffecter
     *
     * @param  estAffecter l'etat que l'on souhaite affecter
     */
    synchronized public void setEstAffecter(boolean estAffecter) {
        this.estAffecter = estAffecter;
    }

    /**
     * Accesseur de la ville affecter par le gouverneur
     *
     * @return  la ville affecter par le gouverneur
     */
    synchronized public Ville getVilleAffecter() {
        return villeAffecter;
    }

    /**
     * Attribut une ville au gouverneur
     *
     * @param  villeAffecter la ville que l'on souhaite affecter au gouverneur
     */
    synchronized public void setVilleAffecter(Ville villeAffecter) {
        this.villeAffecter = villeAffecter;
    }

    /**
     * Ameliore le gouverneur en imcrementant de 1 son niveau
     * et en multipliant ses effets par 1.5
     */
    synchronized public void ameliorer(){
        this.niveau = this.niveau + 1;
        for (Donnee donnee : effets.keySet()) {
            this.effets.put(donnee, this.effets.get(donnee)*1.5);
        }
    }

    /**
     * Compare le gouverneur d'apres son nom
     */
    public static Comparator<Gouverneur> ComparatorNom = new Comparator<Gouverneur>() {
        @Override
        synchronized public int compare(Gouverneur gouv1, Gouverneur gouv2) {
            return gouv1.getNom().compareTo(gouv2.getNom());
        }
    };

    /**
     * Compare le gouverneur d'apres son etat estDebloque
     */
    public static Comparator<Gouverneur> ComparatorDebloque = new Comparator<Gouverneur>() {
        @Override
        synchronized public int compare(Gouverneur gouv1, Gouverneur gouv2) {
            return Boolean.compare(gouv2.estDebloque, gouv1.estDebloque);
        }
    };
}
