package modele;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <b>Batiment est la classe représentant les batiments des différentes villes de la planète</b>
 * <p>
 * Un batiment est caractérisé par les informations suivantes :
 * <ul>
 * <li>Un identifiant unique attribué définitivement.</li>
 * <li>Un Type</li>
 * <li>Un niveau, le niveau maximal étant 5</li>
 * <li>Un booléen estDesactive indiquant si le batiment est desactivé</li>
 * <li>Un booléen estEnCoursAmelioration indiquant si le batiment est en cours d'amélioration</li>
 * <li>Une Date de fin d'amélioration</li>
 * <li>Une liste associative d'effets</li>
 * </ul>
 * </p>
 *
 *
 * @author Antoine RICHARD - Antoine68
 */
public class Batiment {

    private static final AtomicInteger sequence = new AtomicInteger();

    /**
     * L'ID du Batiment. Cet ID n'est pas modifiable.
     *
     * @see Batiment#Batiment(TypeBatiment)
     * @see Batiment#getId()
     */
    private int id;

    /**
     * Le type de batiment. Ce type n'est pas modifiable.
     *
     * @see Batiment#Batiment(TypeBatiment)
     * @see Batiment#getTypeBatiment()
     */
    private TypeBatiment typeBatiment;

    /**
     * Le niveau du batiment.
     *
     * @see Batiment#getNiveau()
     * @see Batiment#demarrerAmelioration()
     * @see Batiment#finirAmelioration()
     */
    private int niveau;

    /**
     * Un booléen indiquant si le batiment est désactivé ou non.
     *
     * @see Batiment#activerDesactiver()
     * @see Batiment#estDesactive()
     */
    private boolean estDesactive;

    /**
     * Un booléen indiquant si le batiment est en cours d'amélioration ou non.
     *
     * @see Batiment#estEnCoursAmelioration()
     * @see Batiment#demarrerAmelioration()
     * @see Batiment#finirAmelioration()
     */
    private boolean estEnCoursAmelioration;

    /**
     * Indique la date à laquelle l'amélioration en cours sera finie. Vaut null si aucune amélioration n'est en cours.
     *
     * @see Batiment#getDateFinAmelioration()
     * @see Batiment#demarrerAmelioration()
     * @see Batiment#finirAmelioration()
     */
    private Date dateFinAmelioration;

    /**
     * Liste associative représentant les effets du batiment sur les données de la planète. La clé est le type de la
     * donnée et la valeur l'effet sur ce type.
     *
     * @see Batiment#getEffets()
     * @see Batiment#finirAmelioration()
     */
    private HashMap<TypeDonnee, Double> effets;

    /**
     * Constructeur Batiment.
     * <p>
     * A la construction d'un objet Batiment, le niveau est fixé à 1. On lui assigne un id, le type du batiment est
     * initailisé avec la valeur passée en paramètre,
     * Les état désactivé et en cours d'amélioration sont initialisés à false.
     * Les effets sont initialiser avec la liste d'effets par défaut du type du batiment.
     * </p>
     *
     * @param typeBatiment le type du batiment.
     *
     * @see Batiment#id
     * @see Batiment#typeBatiment
     * @see Batiment#niveau
     * @see Batiment#estDesactive
     * @see Batiment#estEnCoursAmelioration
     * @see Batiment#dateFinAmelioration
     * @see Batiment#effets
     *
     */
    public Batiment(TypeBatiment typeBatiment) {
        this.id = genererId();
        this.typeBatiment = typeBatiment;
        this.niveau = 1;
        this.estDesactive = false;
        this.estEnCoursAmelioration = false;
        this.dateFinAmelioration = null;
        this.effets = new HashMap<TypeDonnee, Double> (typeBatiment.getEffetsParDefaut());
    }

    /**
     * Génère l'id unique du Batiment.
     */
    private int genererId() {
        return sequence.incrementAndGet();
    }

    /**
     * Accesseur de l'id du Batiment.
     *
     * @return  l'id du Batiment
     */
    public int getId() {
        return this.id;
    }

    /**
     * Accesseur du type du Batiment.
     *
     * @return le type du Batiment
     */
    public TypeBatiment getTypeBatiment() {
        return this.typeBatiment;
    }

    /**
     * Accesseur du niveau du Batiment.
     *
     * @return le niveau actuel du Batiment
     */
    public int getNiveau() {
        return this.niveau;
    }

    /**
     * Accesseur de l'état d'activation du Batiment.
     *
     * @return <code>true</code> si le Batiment est désactivé sinon <code>false</code>
     */
    public boolean estDesactive() {
        return this.estDesactive;
    }

    /**
     * Accesseur de l'état d'amélioration du Batiment.
     *
     * @return <code>true</code> si le Batiment est en cours d'amélioration sinon <code>false</code>
     */
    public boolean estEnCoursAmelioration() {
        return this.estEnCoursAmelioration;
    }

    /**
     * Accesseur des effets du Batiment.
     *
     * @return les effets du Batiment
     */
    public HashMap<TypeDonnee, Double> getEffets() {
        return effets;
    }

    /**
     * Accesseur de la date de fin d'amélioration du Batiment.
     *
     * @return la date de fin d'amélioration du Batiment
     */
    public Date getDateFinAmelioration() {
        return this.dateFinAmelioration;
    }

    /**
     * Calcul le prix de l'amélioration.
     *
     * @return le prix de l'amélioration
     */
    public double getPrixAmelioration() {
        return this.typeBatiment.getCoutConstructionParDefaut() * this.niveau;
    }

    /**
     * Calcul le temps de construction de l'amélioration
     *
     * @return le temps de construction de l'amélioration
     */
    public int getTempsConstructionAmelioration() {
        return this.typeBatiment.getTempsConstructionParDefaut() + ((this.typeBatiment.getTempsConstructionParDefaut()/4) * this.niveau);
    }


    /**
     * Permet de vérifier si le Batiment peut être amélioré.
     *
     * @return <code>true</code> si le Batiment peut être amélioré sinon <code>false</code>
     */
    public boolean peutAmeliorer() {
        return ((this.niveau < typeBatiment.getNiveauMax()) && !this.estEnCoursAmelioration);
    }

    /**
     * Active ou désactive le batiment selon son état actuel
     */
    public void activerDesactiver() {
        this.estDesactive = !this.estDesactive;
    }

    /**
     * Demarre l'amélioration du Batiment en initialisant la date de fin d'amélioration
     * à la date actuelle + le temps de construction de l'amélioration en minute retourné
     * par la fonction <code>getTempsConstructionAmelioration()</code>.
     *
     * @see Batiment#getTempsConstructionAmelioration()
     */
    public void demarrerAmelioration() {
        if (!peutAmeliorer()) return;
        this.dateFinAmelioration = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(this.dateFinAmelioration);
        c.add(Calendar.MINUTE, + getTempsConstructionAmelioration());
        this.dateFinAmelioration = c.getTime();
        this.estEnCoursAmelioration = true;
    }

    /**
     * Fini l'amélioration en cours en augmentant le niveau et les effets.
     *
     * @see Batiment#ameliorerEffets()
     */
    public void finirAmelioration() {
        if (!this.estEnCoursAmelioration) return;
        this.dateFinAmelioration = null;
        this.estEnCoursAmelioration = false;
        this.niveau++;
        this.ameliorerEffets();
    }

    /**
     * Améliore les effets
     */
    private void ameliorerEffets() {
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

}
