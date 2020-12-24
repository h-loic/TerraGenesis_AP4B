package modele;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * <b>Mine est la classe représentant les mines des différents avant-postes de la planète</b>
 * <p>
 * Une mine est caractérisée par les informations suivantes :
 * <ul>
 * <li>Un identifiant unique attribué définitivement.</li>
 * <li>Une Ressource exrtraite</li>
 * <li>Un rendement en kg/min.</li>
 * <li>Un benefice en c/min.</li>
 * <li>Une date d'épuisment : lorsque la date d'épuisement est atteinte, la mine cesse de fonctionner.</li>
 * <li>Des coordonnées x, y, z.</li>
 * <li>Un niveau, le niveau maximal étant 5</li>
 * <li>Un nom</li>
 * <li>Un booléen isfonctionnelle indiquant si la mine fonctionne</li>
 * </ul>
 * </p>
 *
 *
 * @author Zapolatero - lpascuzzi
 */

public class Mine {

    public static final int PRIX_BASE_MINE = 25000;
    public static final int PRIX_BASE_AMELIORATION   = 1500;

    /**
     * L'ID de la Mine. Cet ID n'est pas modifiable.
     *
     * @see Mine#Mine(Ressource, double, Coordonnee)
     * @see Mine#getId()
     */
    private int id;

    /**
     * La Ressource puisée par la mine.
     *
     * @see Mine#Mine(Ressource, double, Coordonnee)
     * @see Mine#getRessource()
     */
    private Ressource ressource;

    /**
     * Rendement de la mine (kg de ressources puisés par minute).
     *
     * @see Mine#Mine(Ressource, double, Coordonnee)
     * @see Mine#getRendement()
     */
    private double rendement;

    /**
     * Le bénéfice de la mine : rendement de la mine * valeur de la ressource.
     *
     * @see Mine#getBenefice()
     */
    private double benefice;

    /**
     * La date à laquelle la mine cessera de fonctionner.
     *
     * @see Mine#getDateEpuissement()
     */
    private Date dateEpuissement;

    /**
     * Les coordonnées de la position de la mine.
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
     * Le nom de la mine, donnée par l'avant-poste.
     *
     * @see Mine#getNom()
     */
    private String nom;

    /**
     * Le booléen indiquant si la mine fonctionne. S'il est false, la mine ne génère plus de bénéfice est doit être détruite par l'utilisateur.
     *
     * @see Mine#getBenefice()
     */
    private boolean isFonctionnelle; // quand la mine dépasse sa date d epuisement, elle n est plus fonctionnelle


    /**
     * Constructeur Mine.
     * <p>
     * A la construction d'un objet Mine, le niveau est fixé à 1. On lui assigne un id aléatoire, un nom aléatoire, qui sera remplacé par l'avant-poste auquel la mine est assignée
     * on lui assigne une ressource à puiser et des coordonnées.
     * On lui assigne comme date d'épuisement la date courante à laquelle on ajoute deux jours.
     * </p>
     *
     * @param ressource
     *            ressource puisée par la mine
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
        this.id = random.nextInt();
        this.nom = "Mine" + Integer.toString(random.nextInt());
        this.ressource = ressource;
        this.coordonnee = coordonnee;
        this.dateEpuissement = new Date();
        this.isFonctionnelle = true;

        Date currentDate = new Date(); // recuperation de la date courante
        
        //ajout de deux jours à la date courante
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, 2);
        dateEpuissement = c.getTime(); // la date d'epuisement de la mine est deux jours après la création de la mine

        this.rendement =  rendement; //kg/min

        this.benefice = (rendement /*/ 60*/)*this.ressource.getValeur();

        System.out.println(dateEpuissement);

        niveau = 1;
    }

    /**
     * Améliore la mine
     * incrémente son niveau de 1, augmente le rendement et donc le bénéfice
     * raccourci la durée de vie de la mine
     *
     * @see Mine#niveau
     * @see Mine#benefice
     * @see Mine#rendement
     */
    public void ameliorerMine(){
        if(this.isFonctionnelle){
            niveau++;
            rendement *= 1.5;
            this.benefice = (this.rendement /*/ 60*/) * this.ressource.getValeur();

            //la date d'epuisement est avancée de deux heures
            Calendar c = Calendar.getInstance();
            c.setTime(dateEpuissement);
            c.add(Calendar.HOUR, -2);
            dateEpuissement = c.getTime();
            System.out.println(dateEpuissement);
        }
    }

    /**
     * Retourne le prix d'amélioration de la Mine
     *
     * @see Mine#PRIX_BASE_AMELIORATION
     * @see Mine#niveau
     *
     * @return prix de base d'une amélioration * niveau de la mine
     */
    public int  getPrixAmelioration(){
        return  PRIX_BASE_AMELIORATION * this.niveau;
    }

    /**
     * @see Mine#ressource
     * @return la ressrouce puisée par la mine
     */
    public Ressource getRessource() {
        return ressource;
    }

    /**
     * Retourne le rendement de la mine. Si la mine n'est plus fonctionnelle, cette méthode retourne 0
     *
     * @see Mine#rendement
     * @return le rendement de la mine
     */
    public double getRendement() {
        return rendement;
    }

    /**
     * Retourne le benefice de la mine. Si la mine n'est plus fonctionnelle, cette méthode retourne 0
     *
     * @see Mine#benefice
     * @return le benefice de la mine
     */
    public double getBenefice() {
        if (!isFonctionnelle){
            return 0;
        }
        return benefice;
    }

    /**
     * Retourne le booléen indiquant l'état de la mine
     *
     * @see Mine#isFonctionnelle
     * @return l'état de la mine
     */
    public boolean isFonctionnelle() {
        return isFonctionnelle;
    }

    /**
     * Retourne le rendement de la mine. Si la mine n'est plus fonctionnelle, cette méthode retourne 0
     *
     * @see Mine#isFonctionnelle
     * @param  fonctionnelle
     *         l'état que l'on souhaite affecter à la mine
     */
    public void setFonctionnelle(boolean fonctionnelle) {
        isFonctionnelle = fonctionnelle;
    }

    /**
     * Retourne la date d'épuisement de la mine
     *
     * @see Mine#dateEpuissement
     * @return la date d'épuisement de la mine
     */
    public Date getDateEpuissement() {
        return dateEpuissement;
    }

    /**
     * Retourne les coordonnées de la mine
     *
     * @see Mine#coordonnee
     * @return les coordonnées de ma mine.
     */
    public Coordonnee getCoordonnee() {
        return coordonnee;
    }

    /**
     * Retourne le nom de la mine
     *
     * @see Mine#nom
     * @return le nom de la mine
     */
    public String getNom() {
        return nom;
    }

    /**
     * Retourne le niveau de la mine
     *
     * @see Mine#niveau
     * @return le niveau de la mine
     */
    public int getNiveau() {
        return niveau;
    }

    /**
     * Permet de changer le nom de la mine
     *
     * @see Mine#nom
     * @param nom
     * le nouveau nom de la mine
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Retourne l'id de la mine
     *
     * @see Mine#id
     * @return l'identifiant de la mine'
     */
    public int getId(){
        return this.id;
    }
}
