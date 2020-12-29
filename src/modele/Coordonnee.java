package modele;

/**
 * <b>Coordonnee est la classe permettant de positionner les éléments sur la carte</b>
 * <p>
 * Une Coordonnee est caractérisée par les informations suivantes :
 * <ul>
 * <li>Une longitude</li>
 * <li>Une latitude</li>
 * <li>Une altitude</li>
 * </ul>
 * </p>
 *
 *
 * @author Antoine RICHARD - Antoine68
 */
public class Coordonnee {

    /**
     * La longitude
     */
    private double x;

    /**
     * La latitude
     */
    private double y;

    /**
     * L'altitude
     */
    private double z;

    /**
     *
     * Constructeur Coordonnee. Initialise les attributs avec les valeurs passées en paramètre.
     *
     * @param x la longitude
     * @param y la latitude
     * @param z l'altitude
     */
    public Coordonnee(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Accesseur de la longitude.
     * @return la longitude
     */
    public double getX() {
        return x;
    }

    /**
     * Mutateur de la longitude
     * @param x la nouvelle longitude
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Accesseur de la latitude.
     * @return la latitude
     */
    public double getY() {
        return y;
    }

    /**
     * Mutateur de la latitude
     * @param y la nouvelle latitude
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Accesseur de l'altitude.
     * @return l'altitude
     */
    public double getZ() {
        return z;
    }

    /**
     * Mutateur de l'altitude.
     * @param z la nouvelle altitude
     */
    public void setZ(double z) {
        this.z = z;
    }
}
