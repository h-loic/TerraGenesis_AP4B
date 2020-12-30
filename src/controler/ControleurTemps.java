package controler;

import javafx.scene.canvas.Canvas;
import modele.*;
import vue.VueAjouterAvantPoste;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <b>Le ControleurTemps est un controleur et un Thread qui gère l'avancée du temps et donc l'évolution des données de la planète, de ses avant-postes, mines et villes </b>
 * <p>
 *     Ce controleur est caractérisé par
 *     <ul>
 *         <li>
 *             Un booléen indiquant s'il est en marche
 *         </li>
 *         <li>
 *             Une planète dont il faut mettre à jour les données
 *         </li>
 *         <li>
 *             Un accès au ControleurPrincipal
 *         </li>
 *     </ul>
 * </p>
 *
 * @see ControleurPrincipal
 * @author Zapolatero - lpascuzzi
 */

public class ControleurTemps extends Thread{

    /**
     * Booléen indiquant si le controleur est en marche
     *
     * @see ControleurTemps#ControleurTemps(Planete, ControleurPrincipal)
     * @see ControleurTemps#arreter()
     */
    private AtomicBoolean estEnMarche = new AtomicBoolean();

    /**
     * Planete dont il faut gérer l'évolution du temps
     *
     * @see ControleurTemps#ControleurTemps(Planete, ControleurPrincipal)
     * @see Planete
     */
    private Planete planete;

    /**
     * Instance du ControleurPrincipal
     *
     * @see ControleurTemps#ControleurTemps(Planete, ControleurPrincipal)
     * @see ControleurPrincipal
     */
    private ControleurPrincipal controleurPrincipal;

    /**
     * Constructeur de ControleurTemps
     *
     * @param planete planete que le controleur temps doit gérer
     * @param controleurPrincipal instance du ControleurPrincipal, permettant d'intéragir avec celui ci
     *
     * @see Planete
     * @see ControleurPrincipal
     * */
    public ControleurTemps(Planete planete, ControleurPrincipal controleurPrincipal){
        super("temps");
        this.planete = planete;
        this.controleurPrincipal = controleurPrincipal;
    }

    /**
     * méthode permettant de lancer le ControleurTemps
     *
     * @see Thread
     *
     * */
    public void run(){
        this.estEnMarche.set(true);
        while (estEnMarche.get()){
            //mise à jour des avant-postes
            majAvantPostes();
            //mise à jour des mines
            majVilles();
            //mise à jour des villes
            majRecherche();
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                arreter();
            }
        }
    }

    /**
     * Méthode arrêtant le Thread et mettant le booléen estEnMarche à false
     *
     * @see ControleurTemps#estEnMarche
     *
     * */
    public void arreter() {
        this.estEnMarche.set(false);
        interrupt();
    }

    /**
     * <p>
     *     Met à jour la recherche : si une recherche est en cours et que le temps de recherche est écoulé,
     *     cette fonction débloque le type de batiment recherché et met fin à la recherche
     * </p>
     *
     * @see Recherche
     * @see Recherche#finDeRecherche()
     * @see Recherche#getTypeBatimentRecherche()
     * @see TypeBatiment
     *
     * */
    public void majRecherche(){
        Recherche recherche = planete.getRecherche();
        if (recherche.isRechercheEnCours()){
            Date dateCourante = new Date();
            if (dateCourante.after(recherche.getDateFinRecherche())){
                this.planete.debloquerTypeBatiment(recherche.getTypeBatimentRecherche());
                recherche.finDeRecherche();
                System.out.println("débloqué");
            }else{
                long diffInMillies = Math.abs(recherche.getDateFinRecherche().getTime() - dateCourante.getTime());
                long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                System.out.println("diff : "+diff+" : "+diffInMillies);
            }
        }
    }

    /**
     * <p>
     *     met à jour les données concernant les avant-postes et leurs mines:
     *     <ul>
     *         <li>
     *              récupère la somme des bénéfices générés par toutes les mines de l'avant-poste par seconde
     *         </li>
     *         <li>
     *             met les Mines ayant atteint leur date d'épuisment à l'arrêt
     *         </li>
     *     </ul>
     * </p>
     *
     * @see Recherche
     * @see AvantPoste#getBeneficesMines()
     * @see Mine#getDateEpuissement()
     *
     * */
    private void majAvantPostes(){
        int benefices = 0;
        for (AvantPoste avantPoste : planete.getAvantPostes()){
            for(Mine mine : avantPoste.getMines()){
                Date dateNow = new Date();
                if (dateNow.after(mine.getDateEpuissement())) {
                    System.out.println(mine.getNom()+ " plus fonctionnelle");
                    mine.setFonctionnelle(false);
                }
            }
            benefices+=avantPoste.getBeneficesMines()/60;
        }
        planete.getDonnee(TypeDonnee.FINANCES).setCroissance(benefices);
        planete.getDonnee(TypeDonnee.FINANCES).majValeur();
    }

    /**
     * <p>
     *     met à jour les données concernant les villes:
     *     <ul>
     *         <li>
     *              fini la construction des batiments quand leur date de fin de construction est atteinte
     *         </li>
     *         <li>
     *             améliore les batiments quand ils ont atteint leur date de fin d'amélioration
     *         </li>
     *     </ul>
     * </p>
     *
     * @see Recherche
     * @see Ville
     * @see Batiment
     *
     * */
    private void majVilles() {
        Date dateCourante = new Date();
        for (Ville ville : this.planete.getVilles()) {
            ville.majDonnees();
            for (Map.Entry<Batiment, Date> batimentEnConstruction : ville.getBatimentsEnConstruction().entrySet()) {
                if (dateCourante.after(batimentEnConstruction.getValue())) ville.finirConstructionBatiment(batimentEnConstruction.getKey());
            }
            for (Batiment batiment : ville.getBatiments()) {
                if (batiment.estEnCoursAmelioration() && dateCourante.after(batiment.getDateFinAmelioration())) batiment.finirAmelioration();
            }
        }
    }
}
