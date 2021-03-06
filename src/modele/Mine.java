package modele;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <b>Mine est la classe representant les mines des differents avant-postes de la planete</b>
 * <p>
 * Une mine est caracterisee par les informations suivantes :
 * <ul>
 * <li>Un identifiant unique attribue definitivement.</li>
 * <li>Une Ressource exrtraite</li>
 * <li>Un rendement en kg/min.</li>
 * <li>Un benefice en c/min.</li>
 * <li>Une date d'epuisment : lorsque la date d'epuisement est atteinte, la mine cesse de fonctionner.</li>
 * <li>Des coordonnees x, y, z.</li>
 * <li>Un niveau, le niveau maximal etant 5</li>
 * <li>Un nom</li>
 * <li>Un booleen isfonctionnelle indiquant si la mine fonctionne</li>
 * </ul>
 * </p>
 *
 *
 * @author Zapolatero - lpascuzzi
 */

public class Mine {


    /**
     * Permet de generer des identifiants uniques
     *
     * @see Mine#Mine(Ressource, double, Coordonnee)
     * @see Mine#id
     */
    private static final AtomicInteger sequence = new AtomicInteger();

    /**
     * Le prix de base d'une mine (sert a calculer le prix d'une nouvelle Mine en fonction du nombre
     * de Mines deja construites dans l'avant-poste)
     */
    public static final int PRIX_BASE_MINE = 25000;

    /**
     * Le prix de base d'amelioration d'une mine (sert a calculer le prix d'une amelioration de Mine en fonction de son niveau)
     */
    public static final int PRIX_BASE_AMELIORATION   = 1500;

    /**
     * L'ID de la Mine. Cet ID n'est pas modifiable.
     *
     * @see Mine#Mine(Ressource, double, Coordonnee)
     * @see Mine#getId()
     */
    private int id;

    /**
     * La Ressource puisee par la mine.
     *
     * @see Mine#Mine(Ressource, double, Coordonnee)
     * @see Mine#getRessource()
     */
    private Ressource ressource;

    /**
     * Rendement de la mine (kg de ressources puises par minute).
     *
     * @see Mine#Mine(Ressource, double, Coordonnee)
     * @see Mine#getRendement()
     */
    private double rendement;

    /**
     * Le benefice de la mine : rendement de la mine * valeur de la ressource.
     *
     * @see Mine#getBenefice()
     */
    private double benefice;

    /**
     * La date a laquelle la mine cessera de fonctionner.
     *
     * @see Mine#getDateEpuissement()
     */
    private Date dateEpuissement;

    /**
     * Les coordonnees de la position de la mine.
     *
     * @see Mine#Mine(Ressource, double, Coordonnee)
     * @see Mine#getCoordonnee()
     */
    private Coordonnee coordonnee;

    /**
     * Le niveau de la mine.
     *
     * @see Mine#getNiveau()
     */
    private int niveau;

    /**
     * Le nom de la mine, donnee par l'avant-poste.
     *
     * @see Mine#getNom()
     */
    private String nom;

    /**
     * Le booleen indiquant si la mine fonctionne. S'il est false, la mine ne genere plus de benefice est doit être detruite par l'utilisateur.
     *
     * @see Mine#getBenefice()
     */
    private boolean isFonctionnelle; // quand la mine depasse sa date d epuisement, elle n est plus fonctionnelle


    /**
     * Constructeur Mine.
     * <p>
     * A la construction d'un objet Mine, le niveau est fixe a 1. On lui assigne un id aleatoire, un nom aleatoire, qui sera remplace par l'avant-poste auquel la mine est assignee
     * on lui assigne une ressource a puiser et des coordonnees.
     * On lui assigne comme date d'epuisement la date courante a laquelle on ajoute deux jours.
     * </p>
     *
     * @param ressource
     *            ressource puisee par la mine
     * @param rendement
     *            rendement de la mine.
     * @param coordonnee
     *            position de la mine
     *
     * @see Mine#id
     * @see Mine#nom
     * @see Mine#ressource
     * @see Mine#coordonnee
     * @see Mine#dateEpuissement
     * @see Mine#isFonctionnelle
     * @see Mine#benefice
     * @see Mine#niveau
     */
    public Mine(Ressource ressource, double rendement, Coordonnee coordonnee) {
        Random random = new Random();
        this.id = sequence.incrementAndGet();//generation d'un id unique
        this.nom = "Mine" + Integer.toString(random.nextInt()); // generation d'un nom temporaire, son nom sera attribue par le contrôleur
        this.ressource = ressource;
        this.coordonnee = coordonnee;
        this.dateEpuissement = new Date();
        this.isFonctionnelle = true;

        Date currentDate = new Date(); // recuperation de la date courante
        
        //ajout de deux jours a la date courante
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, 2);
        dateEpuissement = c.getTime(); // la date d'epuisement de la mine est deux jours apres la creation de la mine

        this.rendement =  rendement; //kg/min

        this.benefice = (rendement /*/ 60*/)*this.ressource.getValeur();

        System.out.println(dateEpuissement);

        niveau = 1;
    }

    /**
     * Ameliore la mine
     * incremente son niveau de 1, augmente le rendement et donc le benefice
     * raccourci la duree de vie de la mine
     *
     * @see Mine#niveau
     * @see Mine#benefice
     * @see Mine#rendement
     */
    synchronized public void ameliorerMine(){
        if(this.isFonctionnelle){
            niveau++;
            rendement *= 1.5;
            this.benefice = (this.rendement /*/ 60*/) * this.ressource.getValeur();

            //la date d'epuisement est avancee de deux heures
            Calendar c = Calendar.getInstance();
            c.setTime(dateEpuissement);
            c.add(Calendar.HOUR, -2);
            dateEpuissement = c.getTime();
            System.out.println(dateEpuissement);
        }
    }

    /**
     * Retourne le prix d'amelioration de la Mine
     *
     * @see Mine#PRIX_BASE_AMELIORATION
     * @see Mine#niveau
     *
     * @return prix de base d'une amelioration * niveau de la mine
     */
    synchronized public int  getPrixAmelioration(){
        return  PRIX_BASE_AMELIORATION * this.niveau;
    }

    /**
     * @see Mine#ressource
     * @return la ressrouce puisee par la mine
     */
    synchronized public Ressource getRessource() {
        return ressource;
    }

    /**
     * Retourne le rendement de la mine. Si la mine n'est plus fonctionnelle, cette methode retourne 0
     *
     * @see Mine#rendement
     * @return le rendement de la mine
     */
    synchronized public double getRendement() {
        return rendement;
    }

    /**
     * Retourne le benefice de la mine. Si la mine n'est plus fonctionnelle, cette methode retourne 0
     *
     * @see Mine#benefice
     * @return le benefice de la mine
     */
    synchronized public double getBenefice() {
        if (!isFonctionnelle){
            return 0;
        }
        return benefice;
    }

    /**
     * Retourne le booleen indiquant l'etat de la mine
     *
     * @see Mine#isFonctionnelle
     * @return l'etat de la mine
     */
    synchronized public boolean isFonctionnelle() {
        return isFonctionnelle;
    }

    /**
     * Retourne le rendement de la mine. Si la mine n'est plus fonctionnelle, cette methode retourne 0
     *
     * @see Mine#isFonctionnelle
     * @param  fonctionnelle
     *         l'etat que l'on souhaite affecter a la mine
     */
    synchronized public void setFonctionnelle(boolean fonctionnelle) {
        isFonctionnelle = fonctionnelle;
    }

    /**
     * Retourne la date d'epuisement de la mine
     *
     * @see Mine#dateEpuissement
     * @return la date d'epuisement de la mine
     */
    synchronized public Date getDateEpuissement() {
        return dateEpuissement;
    }

    /**
     * Retourne les coordonnees de la mine
     *
     * @see Mine#coordonnee
     * @return les coordonnees de ma mine.
     */
    synchronized public Coordonnee getCoordonnee() {
        return coordonnee;
    }

    /**
     * Retourne le nom de la mine
     *
     * @see Mine#nom
     * @return le nom de la mine
     */
    synchronized public String getNom() {
        return nom;
    }

    /**
     * Retourne le niveau de la mine
     *
     * @see Mine#niveau
     * @return le niveau de la mine
     */
    synchronized public int getNiveau() {
        return niveau;
    }

    /**
     * Permet de changer le nom de la mine
     *
     * @see Mine#nom
     * @param nom
     * le nouveau nom de la mine
     */
    synchronized public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Retourne l'id de la mine
     *
     * @see Mine#id
     * @return l'identifiant de la mine'
     */
    synchronized public int getId(){
        return this.id;
    }
}
