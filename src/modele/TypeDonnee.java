package modele;

/**
 * <b>TypeDonnee est l'enumeration representant les differents type de donnees presents dans le jeu</b>
 * <p>
 * Un type de donnee est caracterise par les informations suivantes :
 * <ul>
 * <li>Une description</li>
 * <li>Une unite de mesure</li>
 * <li>Une valeur par defaut</li>
 * </ul>
 * </p>
 *
 *
 * @author Antoine RICHARD - Antoine68
 */
public enum TypeDonnee {

    /**
     * Type de donnees : Temperature
     */
    TEMPERATURE("blabla", "°C",0),

    /**
     * Type de donnees : Pression
     */
    PRESSION("blabla", "Pa",0),

    /**
     * Type de donnees : Oxygene
     */
    OXYGENE("blabla", "ppm",0),

    /**
     * Type de donnees : Eau
     */
    EAU("blabla", "cm",0),

    /**
     * Type de donnees : Population
     */
    POPULATION("blabla", "",0),

    /**
     * Type de donnees : Habitation
     */
    HABITATION("blabla", "",0),

    /**
     * Type de donnees : Finances
     */
    FINANCES("Fonds disponibles", "c", 10000000);

    /**
     * Description du type de donnee.
     */
    private String description;

    /**
     * Unite de mesure du type de donnee.
     */
    private String unite;

    /**
     * Valeur par defaut du type de donnees au debut du jeu.
     */
    private double valeurDefaut;

    /**
     *
     * Constructeur TypeDonnee. Initialise les attributs avec les valeurs passees en parametres.
     *
     * @param description la description
     * @param unite l'unite
     * @param valeurDefaut la valeur par defaut
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
     * Accesseur de l'unite de mesure.
     * @return l'unite de mesure
     */
    public String getUnite() {
        return unite;
    }

    /**
     * Accesseur de la valeur par defaut.
     * @return la valeur par defaut
     */
    public double getValeurDefaut() {
        return valeurDefaut;
    }
}
