package modele;

/**
 * <b>Ressource est l'énumération représentant les différentes ressources présentes dans le jeu</b>
 * <p>
 * Une Ressource est caractérisé par les informations suivantes :
 * <ul>
 * <li>Un symbole</li>
 * <li>Une rareté</li>
 * <li>Une valeur</li>
 * <li>Un cout de recherche</li>
 * <li>Une ressource parente</li>
 * </ul>
 * </p>
 *
 *
 * @author Antoine RICHARD - Antoine68
 */
public enum Ressource {

    /**
     * Ressource : Carbone
     */
    CARBONE("C", Rarete.TRES_ORDINAIRE, 1.0,0, null),

    /**
     * Ressource : Fer
     */
    FER("Fe", Rarete.ORDINAIRE, 2.0, 200000.0, CARBONE),

    /**
     * Ressource : Argent
     */
    ARGENT("Ag", Rarete.CLASSIQUE, 5.0,300000.0, FER),

    /**
     * Ressource : Palladium
     */
    PALLADIUM("Pd", Rarete.RARE, 10.0, 400000.0, ARGENT),

    /**
     * Ressource : Rhodium
     */
    RHODIUM("Rh", Rarete.TRES_RARE, 20.0, 500000.0, PALLADIUM);

    /**
     * Le symbole de la ressource
     */
    private String symbole;

    /**
     * La rareté de la ressource
     */
    private Rarete rarete;

    /**
     * La valeur de la ressource
     */
    private double valeur;

    /**
     * Le cout de recherche de la ressource
     */
    private double coutRecherche;

    /**
     * La ressource parente. Permet de gérer les dépendances de recherche.
     */
    private Ressource parent;

    /**
     *
     * Constructeur Ressource. Initialise les attributs avec les valeurs passées en paramètres.
     *
     * @param symbole symbole de la ressource
     * @param rarete rareté de la ressource
     * @param valeur valeur de la ressource
     * @param coutRecherche cout de recherche de la ressource
     * @param parent ressource parente
     */
    private Ressource(String symbole, Rarete rarete, double valeur, double coutRecherche, Ressource parent) {
        this.symbole = symbole;
        this.rarete = rarete;
        this.valeur = valeur;
        this.coutRecherche = coutRecherche;
        this.parent = parent;
    }

    /**
     * Accesseur symbole.
     * @return le symbole de la ressource
     */
    public String getSymbole() {
        return symbole;
    }

    /**
     * Accesseur de la rareté.
     * @return la rareté.
     */
    public Rarete getRarete() {
        return rarete;
    }

    /**
     * Accesseur de la valeur.
     * @return la valeur
     */
    public double getValeur() {
        return valeur;
    }

    /**
     * Accesseur du cout de rechrche
     * @return le cout de recherche
     */
    public double getCoutRecherche() {
        return coutRecherche;
    }

    /**
     * Accesseur de la ressource parente.
     * @return la ressource parente
     */
    public Ressource getParent() {
        return parent;
    }
}
