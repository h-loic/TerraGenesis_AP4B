package modele;

/**
 * <b>Rarete est l'énumération représentant les différentes raretés de ressource</b>
 * <p>
 * Une Rarete est caractérisée par les informations suivantes :
 * <ul>
 * <li>Une valeur</li>
 * </ul>
 * </p>
 *
 *
 * @author Antoine RICHARD - Antoine68
 */
public enum Rarete {

    /**
     * Rarete : Très ordinaire
     */
    TRES_ORDINAIRE(1.0),

    /**
     * Rarete : Ordinaire
     */
    ORDINAIRE(2.0),

    /**
     * Rarete : Classique
     */
    CLASSIQUE(5.0),

    /**
     * Rarete : Rare
     */
    RARE(10.0),

    /**
     * Rarete : Très rare
     */
    TRES_RARE(20.0);

    /**
     * La valeur
     */
    private double valeur;

    /**
     *
     * Constructeur Rarete. Initialise les attributs avec les valeurs passées en paramètre.
     *
     * @param valeur la valeur
     */
    private Rarete(double valeur) {
        this.valeur = valeur;
    }

    /**
     * Accesseur de la valeur.
     * @return la valeur
     */
    public double getValeur() {
        return valeur;
    }
}
