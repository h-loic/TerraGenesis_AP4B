package modele;

import java.util.ArrayList;
import java.util.Random;

/**
 * <b>AvantPoste est la classe représentant les différents avant-postes de la planète</b>
 * <p>
 * Un AvantPoste est caractérisé par les informations suivantes :
 * <ul>
 * <li>Un identifiant unique attribué définitivement.</li>
 * <li>Un nom</li>
 * <li>Des coordonnées</li>
 * <li>Une liste de mines</li>
 * </ul>
 * </p>
 *
 *
 * @author Zapolatero - lpascuzzi
 */

public class AvantPoste {

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
     * Les coordonnées de la position de l'AvantPoste.
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
     * d'AvantPostes déjà construits sur la planète)
     */
    public static final int PRIX_BASE_AVPOSTE = 1000000;

    /**
     * Constructeur Mine.
     * <p>
     * A la construction d'un objet AvantPoste, on lui assigne un identifiant aléatoire,
     * ainsi qu'un nom, des coordonnées et une liste de mines données en paramètres
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
        Random random = new Random();
        this.id = random.nextInt();
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
     * Renvoi la somme des bénéfices (€/min) de chaque mine de l'AvantPoste
     *
     * @see AvantPoste#mines
     * @see Mine#getBenefice()
     * @return somme des bénéfices de chaque mine de l'AvantPoste
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
     * retourne les coordonnées de l'AvantPoste
     *
     * @see AvantPoste#coordonnee
     * @return coordonnées de l'AvantPoste
     */
    public Coordonnee getCoordonnee() {
        return coordonnee;
    }

    /**
     * change les coordonnées de l'AvantPoste
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
     * Retourne une mine de l'AvantPoste en fonction de l'ID donné en paramètre
     *
     * @see AvantPoste#mines
     * @see Mine#getId()
     * @param idMine id de la mine à chercher et retourner
     * @return Mine dont l'identifiant est donné en paramètres. Si aucune mine n'a cette id, retourne null
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
