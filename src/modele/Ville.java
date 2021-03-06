package modele;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <b>Batiment est la classe representant les differentes villes de la planete</b>
 * <p>
 * Une ville est caracterise par les informations suivantes :
 * <ul>
 * <li>Un identifiant unique attribue definitivement.</li>
 * <li>Un nom</li>
 * <li>Une liste de batiments</li>
 * <li>Une liste associative de batiments en construction (cle) avec la date de fin de construction (valeur)</li>
 * <li>Des Coordonnees sur la carte</li>
 * <li>Un nombre de place de batiment</li>
 * <li>Un gouverneur</li>
 * <li>Un nombre d'habitations</li>
 * <li>Une population</li>
 * </ul>
 * </p>
 *
 *
 * @author Antoine RICHARD - Antoine68
 */
public class Ville {

    private static final AtomicInteger sequence = new AtomicInteger();

    public static final double PRIX_BASE_VILLE = 1000000.;
    public static final double PALIER_POPULATION_NB_BATIMENT = 200.;

    /**
     * L'ID de la ville. Cet ID n'est pas modifiable.
     *
     * @see Ville#Ville(String, Coordonnee)
     * @see Ville#getId()
     */
    private int id;

    /**
     * Le nom de la ville
     *
     * @see Ville#Ville(String, Coordonnee)
     * @see Ville#getNom()
     */
    private String nom;

    /**
     * La liste de batiments construits dans la ville
     *
     * @see Ville#getBatiments()
     */
    private ArrayList<Batiment> batiments;

    /**
     * La liste de batiments actuellement en construction dans la ville
     *
     * @see Ville#getBatimentsEnConstruction()
     * @see Ville#demarrerConstructionBatiment(Batiment)
     */
    private HashMap<Batiment, Date> batimentsEnConstruction;

    /**
     * Coordonnees x, y, z permettant de placer la ville dans la carte
     *
     * @see Ville#Ville(String, Coordonnee)
     * @see Ville#getCoordonnee()
     */
    private Coordonnee coordonnee;

    /**
     * Le nombre de places totales de batiment actuellement disponible dans la ville
     *
     * @see Ville#getNombrePlaceBatiment()
     * @see Ville#getNombrePlaceRestante()
     */
    private int nombrePlaceBatiment;

    /**
     * Le gouverneur actuellement affecter a la ville
     *
     * @see Ville#affecterGouverneur(Gouverneur)
     * @see Ville#revoquerGouverneur()
     */
    private Gouverneur gouverneur;

    /**
     * Population actuelle de la ville
     *
     * @see Ville#getPopulation()
     */
    private Donnee population;

    /**
     * Nombre d'habitations disponible dans la ville (limite la population)
     *
     * @see Ville#getHabitation()
     */
    private Donnee habitation;

    /**
     * Constructeur Ville.
     * <p>
     * A la construction d'un objet Ville, le nom et les coordonnees sont initialises avec les valeurs passees en
     * parametres. On ajoute un premier batiment dans la liste et on defini a 3 le nombre de place de batiment dans
     * la ville.
     * </p>
     *
     * @param nom le nom de la Ville.
     * @param coordonnee les coordonnees de la Ville.
     *
     * @see Ville#id
     * @see Ville#nom
     * @see Ville#coordonnee
     * @see Ville#batiments
     * @see Ville#batimentsEnConstruction
     * @see Ville#nombrePlaceBatiment
     * @see Ville#gouverneur
     * @see Ville#population
     * @see Ville#habitation
     *
     */
    public Ville(String nom, Coordonnee coordonnee) {
        this.id = genererId();
        this.nom = nom;
        this.coordonnee = coordonnee;
        this.batiments = new ArrayList<>();
        this.batiments.add(new Batiment(TypeBatiment.UNITE_HABITATION));
        this.batimentsEnConstruction = new HashMap<>();
        this.nombrePlaceBatiment = 3;
        this.gouverneur = null;
        this.population = new Donnee(TypeDonnee.POPULATION);
        this.habitation = new Donnee(TypeDonnee.HABITATION);
    }

    /**
     * Genere l'id unique de la Ville.
     */
    synchronized private int genererId() {
        return sequence.incrementAndGet();
    }


    /**
     * Accesseur de l'id de la Ville.
     *
     * @return  l'id de la Ville
     */
    synchronized public int getId() {
        return this.id;
    }


    /**
     * Accesseur du nom de la Ville.
     *
     * @return  le nom de la Ville
     */
    synchronized public String getNom() {
        return nom;
    }

    /**
     * Accesseur des batiments de la Ville.
     *
     * @return  les batiments de la Ville
     */
    synchronized public ArrayList<Batiment> getBatiments() {
        return batiments;
    }

    /**
     * Accesseur des batiments en construction de la Ville.
     *
     * @return  les batiments en construction et leur date de fin de construction de la Ville
     */
    synchronized public HashMap<Batiment, Date> getBatimentsEnConstruction() {
        return this.batimentsEnConstruction;
    }

    /**
     * Accesseur des coordonnees de la Ville.
     *
     * @return  les coordonnees de la Ville
     */
    synchronized public Coordonnee getCoordonnee() {
        return coordonnee;
    }

    /**
     * Accesseur du nombre de place de batiment de la Ville.
     *
     * @return  le nombre de place de batiment de la Ville
     */
    synchronized public int getNombrePlaceBatiment() {
        return nombrePlaceBatiment;
    }

    /**
     * Accesseur du gouverneur de la Ville.
     *
     * @return  le gouverneur de la Ville
     */
    synchronized public Gouverneur getGouverneur() {
        return gouverneur;
    }

    /**
     * Accesseur de la donnee de population de la Ville.
     *
     * @return  la donnee de population de la Ville
     */
    synchronized public Donnee getPopulation() {
        return population;
    }

    /**
     * Accesseur de la donnee d'habitation de la Ville.
     *
     * @return  la donnee d'habitation de la Ville
     */
    synchronized public Donnee getHabitation() {
        return habitation;
    }

    /**
     * Cherche un batiment dans la liste de batiments de la ville
     * @param idBatiment l'id du batiment cherche
     * @return le batiment si il est trouve sinon <code>null</code>
     */
    synchronized public Batiment getBatiment(int idBatiment) {
        for (Batiment batiment : batiments) {
            if (batiment.getId() == idBatiment) return batiment;
        }
        return null;
    }

    /**
     *
     * @return le nombre de places de batiments restant dans la ville actuellement
     */
    synchronized public int getNombrePlaceRestante() {
        return this.nombrePlaceBatiment - (batiments.size() + batimentsEnConstruction.size());
    }

    /**
     * Calcul le pallier en nombre de population qui permet de debloquer une nouvelle place de batiments.
     * @return le pallier
     */
    synchronized public double getProchainPallierBatiment() {
        return (this.nombrePlaceBatiment - 2) * PALIER_POPULATION_NB_BATIMENT;
    }

    /**
     * Revoquer le gouverneur actuellment en place.
     */
    synchronized public void revoquerGouverneur() {
        if (this.gouverneur == null) return;
        this.gouverneur.setEstAffecter(false);
        this.gouverneur = null;
    }

    /**
     * Affecter un nouveau gouverneur a la ville.
     * @param gouverneur le gouverneur affecter
     */
    synchronized public void affecterGouverneur(Gouverneur gouverneur) {
        revoquerGouverneur();
        this.gouverneur = gouverneur;
        this.gouverneur.setEstAffecter(true);
    }

    /**
     * Permet de savoir si il y a une place disponible dans la ville pour construire.
     * @return <code>true</code> si il y a assez de place pour construire sinon <code>false</code>
     */
    synchronized public boolean peutConstruire() {
        return this.getNombrePlaceBatiment() > (this.batiments.size() + this.batimentsEnConstruction.size());
    }

    /**
     * Demarre la construction du batiment.
     * @param batiment instance de batiment a construire
     */
    synchronized public void demarrerConstructionBatiment(Batiment batiment) {
        if (!peutConstruire()) return;
        Date dateFinConstruction = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dateFinConstruction);
        c.add(Calendar.MINUTE, +batiment.getTypeBatiment().getTempsConstructionParDefaut());
        dateFinConstruction = c.getTime();
        this.batimentsEnConstruction.put(batiment, dateFinConstruction);
    }

    /**
     * Finaliser la construction du batiment.
     * @param batiment instance de batiment qui finalise sa construction
     */
    synchronized public void finirConstructionBatiment(Batiment batiment) {
        if (!peutConstruire()) return;
        this.batimentsEnConstruction.remove(batiment);
        this.batiments.add(batiment);
    }

    /**
     * Met a jour les donnees de population et d'habitation de la ville.
     */
    synchronized public void majDonnees() {
        double sommeHab = 0.;
        double sommePop = this.population.getValeurActuelle() + 1.;
        for (Batiment batiment : batiments) {
            if (batiment.getEffets().containsKey(TypeDonnee.HABITATION) && !batiment.estDesactive()) sommeHab += batiment.getEffets().get(TypeDonnee.HABITATION);
            if (batiment.getEffets().containsKey(TypeDonnee.POPULATION) && !batiment.estDesactive() ) {
                sommeHab += batiment.getEffets().get(TypeDonnee.POPULATION);
                sommePop += batiment.getEffets().get(TypeDonnee.POPULATION);
            }
        }
        if(sommePop > sommeHab) {
            sommePop = sommeHab;
        }
        this.population.setValeurActuelle(sommePop);
        this.habitation.setValeurActuelle(sommeHab);
        majNbPlace();
    }

    /**
     * Met a jour le nombre de place de la ville.
     */
    synchronized private void majNbPlace() {
        nombrePlaceBatiment = 3 + Math.floorDiv((int)population.getValeurActuelle(), (int) PALIER_POPULATION_NB_BATIMENT);
        if (nombrePlaceBatiment < batiments.size()) nombrePlaceBatiment = batiments.size();

    }

    /**
     * Detruire un batiment.
     * @param idBatiment l'id du batiment a detruire
     */
    synchronized public void detruireBatiment(int idBatiment) {
        this.batiments.remove(getBatiment(idBatiment));
    }


}
