package modele;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <b>AvantPoste est la classe representant les differents avant-postes de la planete</b>
 * <p>
 * Un AvantPoste est caracterise par les informations suivantes :
 * <ul>
 * <li>Un identifiant unique attribue definitivement.</li>
 * <li>Un nom</li>
 * <li>Des coordonnees</li>
 * <li>Une liste de mines</li>
 * </ul>
 * </p>
 *
 *
 * @author Zapolatero - lpascuzzi
 */

public class AvantPoste {


    /**
     * Permet de générer des identifiants uniques
     *
     * @see AvantPoste#AvantPoste(String, Coordonnee, ArrayList)
     * @see AvantPoste#id
     */
    private static final AtomicInteger sequence = new AtomicInteger();

    /**
     * L'ID de l'avant-poste. Cet avant-poste n'est pas modifiable.
     *
     * @see AvantPoste#AvantPoste(String, Coordonnee, ArrayList)
     * @see AvantPoste#getId()
     */
    private int id;

    /**
     * Le nom de l'AvantPoste.
     *
     * @see AvantPoste#AvantPoste(String, Coordonnee, ArrayList)
     * @see AvantPoste#getNom()
     */
    private String nom;

    /**
     * Les coordonnees de la position de l'AvantPoste.
     *
     * @see AvantPoste#AvantPoste(String, Coordonnee, ArrayList)
     * @see AvantPoste#getCoordonnee()
     */
    private Coordonnee coordonnee;

    /**
     * La liste des mines de l'AvantPoste
     *
     * @see AvantPoste#AvantPoste(String, Coordonnee, ArrayList)
     * @see AvantPoste#getMines()
     */
    private ArrayList<Mine> mines;

    /**
     * Le prix de base d'un AvantPoste (sert à calculer le prix d'un nouvel AvantPoste en fonction du nombre
     * d'AvantPostes dejà construits sur la planete)
     */
    public static final double PRIX_BASE_AVPOSTE = 1000000.;

    /**
     * Constructeur Mine.
     * <p>
     * A la construction d'un objet AvantPoste, on lui assigne un identifiant unique,
     * ainsi qu'un nom, des coordonnees et une liste de mines donnees en parametres
     * </p>
     *
     * @param nom
     *            nom de l'AvantPoste
     * @param coordonnee
     *            position de l'AvantPoste
     * @param mines
     *            liste des mines de l'AvantPoste
     *
     * @see AvantPoste#id
     * @see AvantPoste#nom
     * @see AvantPoste#coordonnee
     * @see AvantPoste#mines
     */
    public AvantPoste(String nom, Coordonnee coordonnee, ArrayList<Mine> mines) {
        this.id = sequence.incrementAndGet(); // génération d'un id unique
        this.nom = nom;
        this.coordonnee = coordonnee;
        this.mines = mines;
    }

    /**
     * Ajoute une mine à la liste des mines de l'AvantPoste
     *
     * @see AvantPoste#mines
     */
    public void ajouterMine(Mine mine){
        this.mines.add(mine);
    }

    /**
     * Renvoi la somme des benefices (€/min) de chaque mine de l'AvantPoste
     *
     * @see AvantPoste#mines
     * @see Mine#getBenefice()
     * @return somme des benefices de chaque mine de l'AvantPoste
     */
    public double getBeneficesMines(){
        double benefices = 0;
        for(Mine mine : this.mines){
            benefices+=mine.getBenefice();
        }
        return benefices;
    }

    /**
     * retourne le nom de l'AvantPoste.
     *
     * @see AvantPoste#nom
     * @return nom de l'AvantPoste
     */
    public String getNom() {
        return nom;
    }

    /**
     * Change le nom de l'AvantPoste
     *
     * @see AvantPoste#nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * retourne les coordonnees de l'AvantPoste
     *
     * @see AvantPoste#coordonnee
     * @return coordonnees de l'AvantPoste
     */
    public Coordonnee getCoordonnee() {
        return coordonnee;
    }

    /**
     * change les coordonnees de l'AvantPoste
     *
     * @see AvantPoste#coordonnee
     */
    public void setCoordonnee(Coordonnee coordonnee) {
        this.coordonnee = coordonnee;
    }

    /**
     * retourne la liste des mines de l'AvantPoste
     *
     * @see AvantPoste#mines
     * @return liste des mines de l'AvantPoste
     */
    public ArrayList<Mine> getMines() {
        return mines;
    }

    /**
     * Retourne l'identifiant de l'AvantPoste
     *
     * @see AvantPoste#id
     * @return l'id de l'AvantPoste
     */
    public int getId() {
        return id;
    }

    /**
     * Retourne le prix de la construction d'une nouvelle Mine dans l'AvantPoste
     *
     * @see Mine#PRIX_BASE_MINE
     * @return prix de construction d'une nouvelle mine
     */
    public int getPrixNouvMine(){
        return (this.mines.size()+1)*Mine.PRIX_BASE_MINE;
    }

    /**
     * Retire une mine de la liste des mines de l'AvantPoste
     *
     * @see AvantPoste#mines
     */
    public void detruireMine(int idAvantPoste) {
        this.mines.remove(getMine(idAvantPoste));
    }


    /**
     * Retourne une mine de l'AvantPoste en fonction de l'ID donne en parametre
     *
     * @see AvantPoste#mines
     * @see Mine#getId()
     * @param idMine id de la mine à chercher et retourner
     * @return Mine dont l'identifiant est donne en parametres. Si aucune mine n'a cette id, retourne null.
     */
    public Mine getMine(int idMine) {
        for (Mine mine : this.mines){
            if (mine.getId() == idMine){
                return mine;
            }
        }
        return null;
    }
}
