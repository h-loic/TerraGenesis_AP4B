package modele;

import java.util.Calendar;
import java.util.Date;

/**
 * <b>Mine est le modele qui permet de representer et manipuler la recherche des differents types de batiments</b>
 * <p>
 * Une Recherche est caracterisee par les informations suivantes :
 * <ul>
 * <li>Un booleen indiquant si un type de batiment est en train d etre recherche.</li>
 * <li>Un type de batiment recherche (null si aucune recherche en cours)</li>
 * <li>Une date de fin de recherche.</li>
 * </ul>
 * </p>
 *
 *
 * @author Zapolatero - lpascuzzi
 */

public class Recherche {

    /**
     * Booleen indiquant si une recherche est en cours.
     *
     * @see Recherche#Recherche()
     * @see Recherche#isRechercheEnCours()
     */
    boolean rechercheEnCours;

    /**
     * Le Type de batiment recherche
     *
     * @see Recherche#Recherche()
     * @see Recherche#getTypeBatimentRecherche()
     */
    TypeBatiment typeBatimentRecherche = null;

    /**
     * Date de fin de la recherche (lorsqu'elle est atteinte, le type de batiment recherche est debloque)
     *
     * @see Recherche#Recherche()
     * @see Recherche#getDateFinRecherche()
     */
    Date dateFinRecherche;

    /**
     * Constructeur Recherche.
     * <p>
     *     à la creation d'une recherche, on met le booleen rechercheEnCours à false et les autres attributs à null
     * </p>
     *
     * @see Recherche#rechercheEnCours
     * @see Recherche#typeBatimentRecherche
     * @see Recherche#dateFinRecherche
     */
    public Recherche() {
        this.rechercheEnCours = false;
        this.typeBatimentRecherche = null;
        this.dateFinRecherche = null;
    }

    /**
     * Lance la recherche du typeDeBatiment donne en argumant.
     *
     * @param typeBatimentARechercher instance de TypeBatiment à rechercher
     *
     * @see Recherche#rechercheEnCours
     * @see Recherche#typeBatimentRecherche
     * @see Recherche#dateFinRecherche
     */
    public void rechercher(TypeBatiment typeBatimentARechercher){
        if (!rechercheEnCours){
            this.typeBatimentRecherche = typeBatimentARechercher;

            dateFinRecherche = new Date();

            //ajoute le temps de recherche du type de batiment à la date courante pour obtenir la date de fin de recherche
            Calendar c = Calendar.getInstance();
            c.setTime(dateFinRecherche);
            c.add(Calendar.MINUTE, +typeBatimentARechercher.getTempsRecherche());

            dateFinRecherche = c.getTime();
            rechercheEnCours = true;
        }
    }

    /**
     * Met fin a une recherche en mettant le booleen rechercheEnCours à false et les autres attributs à null
     *
     * @see Recherche#rechercheEnCours
     * @see Recherche#typeBatimentRecherche
     * @see Recherche#dateFinRecherche
     */
    public void finDeRecherche(){
        this.typeBatimentRecherche = null;
        this.dateFinRecherche = null;
        this.rechercheEnCours = false;
    }

    /**
     * Retourne la valeur du booleen rechercheEnCours pour savoir si une recherche est dejà en cours ou non
     *
     * @see Recherche#rechercheEnCours
     *
     * @return la valeur du booleen rechercheEnCours
     */
    public boolean isRechercheEnCours() {
        return rechercheEnCours;
    }

    /**
     * Retourne le type de batiment recherche
     *
     * @see Recherche#typeBatimentRecherche
     *
     * @return le type de batiment recherche
     */
    public TypeBatiment getTypeBatimentRecherche() {
        return typeBatimentRecherche;
    }

    /**
     * Retourne la date à laquelle la recherche se termine
     *
     * @see Recherche#rechercheEnCours
     *
     * @return la date de fin de recherche
     */
    public Date getDateFinRecherche() {
        return dateFinRecherche;
    }
}
