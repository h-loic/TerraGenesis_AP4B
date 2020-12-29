package modele;

/**
 * <b>TypeDonnee est l'énumération représentant les différents type de données présents dans le jeu</b>
 * <p>
 * Un type de donnée est caractérisé par les informations suivantes :
 * <ul>
 * <li>Une description</li>
 * <li>Une unité de mesure</li>
 * <li>Une valeur par défaut</li>
 * </ul>
 * </p>
 *
 *
 * @author Antoine RICHARD - Antoine68
 */
public enum TypeDonnee {

    /**
     * Type de données : Temperature
     */
    TEMPERATURE("blabla", "°C",0),

    /**
     * Type de données : Pression
     */
    PRESSION("blabla", "Pa",0),

    /**
     * Type de données : Oxygène
     */
    OXYGENE("blabla", "ppm",0),

    /**
     * Type de données : Eau
     */
    EAU("blabla", "cm",0),

    /**
     * Type de données : Population
     */
    POPULATION("blabla", "",0),

    /**
     * Type de données : Habitation
     */
    HABITATION("blabla", "",0),

    /**
     * Type de données : Finances
     */
    FINANCES("Fonds disponibles", "c", 10000000);

    /**
     * Description du type de donnée.
     */
    private String description;

    /**
     * Unité de mesure du type de donnée.
     */
    private String unite;

    /**
     * Valeur par défaut du type de données au début du jeu.
     */
    private double valeurDefaut;

    /**
     *
     * Constructeur TypeDonnee. Initialise les attributs avec les valeurs passées en paramètres.
     *
     * @param description la description
     * @param unite l'unité
     * @param valeurDefaut la valeur par défaut
     *
     *
     */
    private TypeDonnee(String description, String unite, double valeurDefaut) {
        this.description = description;
        this.unite = unite;
        this.valeurDefaut = valeurDefaut;

    }

    /**
     * Accesseur de la description.
     * @return la description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Accesseur de l'unité de mesure.
     * @return l'unité de mesure
     */
    public String getUnite() {
        return unite;
    }

    /**
     * Accesseur de la valeur par défaut.
     * @return la valeur par défaut
     */
    public double getValeurDefaut() {
        return valeurDefaut;
    }
}
