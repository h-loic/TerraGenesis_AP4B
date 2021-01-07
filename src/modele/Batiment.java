package modele;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <b>Batiment est la classe representant les batiments des differentes villes de la planete</b>
 * <p>
 * Un batiment est caracterise par les informations suivantes :
 * <ul>
 * <li>Un identifiant unique attribue definitivement.</li>
 * <li>Un Type</li>
 * <li>Un niveau, le niveau maximal etant 5</li>
 * <li>Un booleen estDesactive indiquant si le batiment est desactive</li>
 * <li>Un booleen estEnCoursAmelioration indiquant si le batiment est en cours d'amelioration</li>
 * <li>Une Date de fin d'amelioration</li>
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
     * Un booleen indiquant si le batiment est desactive ou non.
     *
     * @see Batiment#activerDesactiver()
     * @see Batiment#estDesactive()
     */
    private boolean estDesactive;

    /**
     * Un booleen indiquant si le batiment est en cours d'amelioration ou non.
     *
     * @see Batiment#estEnCoursAmelioration()
     * @see Batiment#demarrerAmelioration()
     * @see Batiment#finirAmelioration()
     */
    private boolean estEnCoursAmelioration;

    /**
     * Indique la date a laquelle l'amelioration en cours sera finie. Vaut null si aucune amelioration n'est en cours.
     *
     * @see Batiment#getDateFinAmelioration()
     * @see Batiment#demarrerAmelioration()
     * @see Batiment#finirAmelioration()
     */
    private Date dateFinAmelioration;

    /**
     * Liste associative representant les effets du batiment sur les donnees de la planete. La cle est le type de la
     * donnee et la valeur l'effet sur ce type.
     *
     * @see Batiment#getEffets()
     * @see Batiment#finirAmelioration()
     */
    private HashMap<TypeDonnee, Double> effets;

    /**
     * Constructeur Batiment.
     * <p>
     * A la construction d'un objet Batiment, le niveau est fixe a 1. On lui assigne un id, le type du batiment est
     * initailise avec la valeur passee en parametre,
     * Les etat desactive et en cours d'amelioration sont initialises a false.
     * Les effets sont initialiser avec la liste d'effets par defaut du type du batiment.
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
        this.effets = typeBatiment.getEffetsParDefaut();
    }

    /**
     * Genere l'id unique du Batiment.
     */
    synchronized private int genererId() {
        return sequence.incrementAndGet();
    }

    /**
     * Accesseur de l'id du Batiment.
     *
     * @return  l'id du Batiment
     */
    synchronized public int getId() {
        return this.id;
    }

    /**
     * Accesseur du type du Batiment.
     *
     * @return le type du Batiment
     */
    synchronized public TypeBatiment getTypeBatiment() {
        return this.typeBatiment;
    }

    /**
     * Accesseur du niveau du Batiment.
     *
     * @return le niveau actuel du Batiment
     */
    synchronized public int getNiveau() {
        return this.niveau;
    }

    /**
     * Accesseur de l'etat d'activation du Batiment.
     *
     * @return <code>true</code> si le Batiment est desactive sinon <code>false</code>
     */
    synchronized public boolean estDesactive() {
        return this.estDesactive;
    }

    /**
     * Accesseur de l'etat d'amelioration du Batiment.
     *
     * @return <code>true</code> si le Batiment est en cours d'amelioration sinon <code>false</code>
     */
    synchronized public boolean estEnCoursAmelioration() {
        return this.estEnCoursAmelioration;
    }

    /**
     * Accesseur des effets du Batiment.
     *
     * @return les effets du Batiment
     */
    synchronized public HashMap<TypeDonnee, Double> getEffets() {
        return effets;
    }

    /**
     * Accesseur de la date de fin d'amelioration du Batiment.
     *
     * @return la date de fin d'amelioration du Batiment
     */
    synchronized public Date getDateFinAmelioration() {
        return this.dateFinAmelioration;
    }

    /**
     * Calcul le prix de l'amelioration.
     *
     * @return le prix de l'amelioration
     */
    synchronized public double getPrixAmelioration() {
        return this.typeBatiment.getCoutConstructionParDefaut() * this.niveau;
    }

    /**
     * Calcul le temps de construction de l'amelioration
     *
     * @return le temps de construction de l'amelioration
     */
    synchronized public int getTempsConstructionAmelioration() {
        return this.typeBatiment.getTempsConstructionParDefaut() + ((this.typeBatiment.getTempsConstructionParDefaut()/4) * this.niveau);
    }


    /**
     * Permet de verifier si le Batiment peut être ameliore.
     *
     * @return <code>true</code> si le Batiment peut être ameliore sinon <code>false</code>
     */
    synchronized public boolean peutAmeliorer() {
        return ((this.niveau < typeBatiment.getNiveauMax()) && !this.estEnCoursAmelioration);
    }

    /**
     * Active ou desactive le batiment selon son etat actuel
     */
    synchronized public void activerDesactiver() {
        this.estDesactive = !this.estDesactive;
    }

    /**
     * Demarre l'amelioration du Batiment en initialisant la date de fin d'amelioration
     * a la date actuelle + le temps de construction de l'amelioration en minute retourne
     * par la fonction <code>getTempsConstructionAmelioration()</code>.
     *
     * @see Batiment#getTempsConstructionAmelioration()
     */
    synchronized public void demarrerAmelioration() {
        if (!peutAmeliorer()) return;
        this.dateFinAmelioration = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(this.dateFinAmelioration);
        c.add(Calendar.MINUTE, + getTempsConstructionAmelioration());
        this.dateFinAmelioration = c.getTime();
        this.estEnCoursAmelioration = true;
    }

    /**
     * Fini l'amelioration en cours en augmentant le niveau et les effets.
     *
     * @see Batiment#ameliorerEffets()
     */
    synchronized public void finirAmelioration() {
        if (!this.estEnCoursAmelioration) return;
        this.dateFinAmelioration = null;
        this.estEnCoursAmelioration = false;
        this.niveau++;
        this.ameliorerEffets();
    }

    /**
     * Ameliore les effets
     */
    synchronized private void ameliorerEffets() {
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
