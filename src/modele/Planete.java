package modele;

import javafx.scene.canvas.Canvas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import static modele.TypeDonnee.*;

/**
 * <b>Planete est la classe representant la planete geree par le joueur, elle centralise les differents modeles</b>
 * <p>
 * Une planete est caracterisee par les informations suivantes :
 * <ul>
 * <li>Une liste de villes.</li>
 * <li>Une liste d'avant-postes</li>
 * <li>Une liste de donnees contenant les differentes donnees de la planete (temperature, pression, finances, population</li>
 * <li>Une liste associative contenant les differents types de batiments et des booleens indiquant s'ils sont debloques</li>
 * <li>Une liste associative contenant les differents types de ressources et des booleens indiquant si elles sont debloquees</li>
 * <li>Une liste de Gouverneurs</li>
 * <li>Une Carte indiquant la position des avant-postes et des villes de la planete</li>
 * <li>Une Recherche permettant de chercher des types de batiments</li>
 * </ul>
 * </p>
 *
 *
 * @author Zapolatero - Louis Pascuzzi
 */

public class Planete {

    /**
     * Nombre de seconde dans une minute. Utile dans notre cas car les effets des batiments sont par minutes
     * mais nous actualisons les donnees toutes les secondes.
     */
    private static final double SECONDES_DANS_MINUTE = 60.;

    /**
     * Liste des Villes de la planete
     *
     * @see Planete#Planete()
     * @see Ville
     */
    private ArrayList<Ville> villes;

    /**
     * Liste des AvantPoste de la planete
     *
     * @see Planete#Planete()
     * @see AvantPoste
     */
    private ArrayList<AvantPoste> avantPostes;

    /**
     * Liste des donnees de la planete
     *
     * @see Planete#Planete()
     * @see Donnee
     */
    private ArrayList<Donnee> donnees;

    /**
     * Hashmap des differents types de batiments et un booleen indiquant s'ils sont recherches
     *
     * @see Planete#Planete()
     * @see TypeBatiment
     */
    private HashMap<TypeBatiment, Boolean> etatTypesBatiment;

    /**
     * Hashmap des differents types de ressources et un booleen indiquant s'ils sont recherches
     *
     * @see Planete#Planete()
     * @see Ressource
     */
    private HashMap<Ressource, Boolean> etatRessources;

    /**
     * Liste des Gouverneurs de la planete
     *
     * @see Planete#Planete()
     * @see Gouverneur
     */
    private ArrayList<Gouverneur> gouverneurs;

    /**
     * Carte de la planete
     *
     * @see Planete#Planete()
     * @see Carte
     */
    private Carte cartePlanete;

    /**
     * Recherche permettant de chercher des types de batiments
     *
     * @see Planete#Planete()
     * @see Recherche
     */
    private Recherche recherche;

    /**
     * Constructeur de Planete
     * <p>
     *     Cree les differentes listes ainsi que la carte et la recherche de la planete
     * </p>
     *
     * @see Planete#villes
     * @see Planete#avantPostes
     * @see Planete#donnees
     * @see Planete#etatTypesBatiment
     * @see Planete#etatRessources
     * @see Planete#gouverneurs
     * @see Planete#cartePlanete
     * @see Planete#recherche
     */
    public Planete(){
        this.villes = new ArrayList<Ville>();
        this.avantPostes = new ArrayList<AvantPoste>();
        this.donnees = new ArrayList<Donnee>();
        this.etatTypesBatiment = new HashMap<TypeBatiment, Boolean>();
        this.etatRessources = new HashMap<Ressource, Boolean>();
        this.gouverneurs = new ArrayList<Gouverneur>();
        this.cartePlanete = new Carte(new ArrayList<Coordonnee>(),new ArrayList<Coordonnee>());
        this.recherche = new Recherche();
    }

    /**
     * <p>
     *     Ajoute une ville e la liste des villes de la planete et sur la carte
     * </p>
     * 
     * @param ville ville e ajouter
     *
     * @see Planete#villes
     */
    synchronized public void ajouterVille(Ville ville) {
        this.cartePlanete.ajouterVilleCarte(ville.getCoordonnee());
        this.villes.add(ville);
    }

    /**
     * <p>
     *     Ajoute un avantPoste a la liste des avantPostes de la planete et sur la carte
     * </p>
     *
     * @param avantPoste avantposte a ajouter
     *
     * @see Planete#avantPostes
     */
    synchronized public void ajouterAvantPoste(AvantPoste avantPoste) {
        this.avantPostes.add(avantPoste);
        this.cartePlanete.ajouterAvPosteCarte(avantPoste.getCoordonnee());
    }

    /**
     * <p>
     *     Detruit l'avant poste dont l'id est donne en parametre
     * </p>
     *
     * @param idAvantPoste id de l'avantposte a detruire
     *
     * @see Planete#avantPostes
     */
    synchronized public void detruireAvantPoste(int idAvantPoste) {
        //destruction de toutes les mines de l'avant poste
        AvantPoste avantPoste = getAvantPoste(idAvantPoste);
        for(Mine mine : avantPoste.getMines()){
            this.detruireMine(avantPoste.getId(), mine.getId());
        }
        //l'efface de la carte
        this.cartePlanete.effacerAvPosteCarte(avantPoste.getCoordonnee());
        this.avantPostes.remove(avantPoste);
    }

    synchronized private void ajouterGouverneur(Gouverneur gouverneur) {
        this.gouverneurs.add(gouverneur);
    }

    /**
     * <p>
     *     Permet de savoir si la planete peut payer un certain montant
     * </p>
     *
     * @param montant montant dont on veut savoir si la planete peut le payer
     *
     * @return un booleen indiquant si la planete peut payer ou non
     *
     * @see Planete#villes
     */
    synchronized public boolean peutPayer(double montant){
        System.out.println(getDonnee(FINANCES).getValeurActuelle() >= montant);
        System.out.println(getDonnee(FINANCES).getValeurActuelle() - montant);
        return getDonnee(FINANCES).getValeurActuelle() >= montant;
    }

    /**
     * <p>
     *     Fait payer le montant donne en parametres a la planete
     * </p>
     *
     * @param montant montant que le on fait payer a la planete
     *
     * @return un booleen indiquant si la planete peut payer ou non
     *
     * @see Planete#donnees
     */
    synchronized public void payer(double montant){
        if (this.peutPayer(montant)){
            getDonnee(FINANCES).setCroissance(-montant);
            getDonnee(FINANCES).majValeur();
        }
    }

    synchronized public boolean peutRechercher(TypeBatiment typeBatiment) {
        if (typeBatiment.getParent() == null) return true;
        return etatTypesBatiment.get(typeBatiment.getParent());
    }

    /**
     * Accesseur de la liste de villes de la planete.
     * @return la liste de villes de la planete
     */
    synchronized public ArrayList<Ville> getVilles() {
        return villes;
    }

    /**
     * Permet de trouver et retourner une ville dans la liste
     * @param id l'id de la ville
     * @return si trouve : la ville avec l'id, sinon : <code>null</code>
     */
    synchronized public Ville getVille(int id) {
        for (Ville ville : villes) {
            if (ville.getId() == id) return ville;
        }
        return null;
    }

    /**
     * <p>
     *     Accesseur renvoyant la liste des avantPostes de la planete
     * </p>
     *
     * @return la liste des avantPostes de la planete
     *
     * @see Planete#avantPostes
     */
    synchronized public ArrayList<AvantPoste> getAvantPostes() {
        return avantPostes;
    }

    /**
     * Accesseur de la liste des donnees de la ville
     * @return la liste des donnees de la ville
     */
    synchronized public ArrayList<Donnee> getDonnees() {
        return donnees;
    }

    /**
     * Accesseur de la liste d'etat des types de batiment.
     * @return la liste associative : type de batiment, est debolque
     */
    synchronized public HashMap<TypeBatiment, Boolean> getEtatTypesInfrastructure() {
        return etatTypesBatiment;
    }

    /**
     * <p>
     *     Renvoie l'avantPoste dont l'id est donne en parametre
     * </p>
     *
     * @param id
     *
     * @return l'avantPoste dont l'id est donne en parametre
     *
     * @see Planete#avantPostes
     */
    synchronized public AvantPoste getAvantPoste(int id){
        for (AvantPoste avantPoste : this.avantPostes) {
            if (avantPoste.getId() == id){
                return avantPoste;
            }
        }
        return null;
    }


    /**
     * <p>
     *     Initialise la liste des gouverneurs de la planete
     *     <ul>
     *         <li>ajoute des gouverneur à la liste de la planete</li>
     *     </ul>
     * </p>
     *
     * @see Planete#cartePlanete
     */
    synchronized public void initialiserGouverneur() {
        this.ajouterGouverneur(new Gouverneur(false, 0, "Michou", false,
                new HashMap<Donnee, Double>(){{ put(getDonnee(TEMPERATURE), 30.0);put(getDonnee(FINANCES), 10.0); }})
        );
        this.ajouterGouverneur(new Gouverneur(false, 0, "Sriky", false,
                new HashMap<Donnee, Double>(){{ put(getDonnee(PRESSION), 30.0);put(getDonnee(OXYGENE), 10.0); }})
        );
        this.ajouterGouverneur(new Gouverneur(false, 0, "Alembert", false,
                new HashMap<Donnee, Double>() {{ put(getDonnee(OXYGENE), 20.0); }})
        );
        this.ajouterGouverneur(new Gouverneur(false, 0, "Leon", false,
                new HashMap<Donnee, Double>() {{ put(getDonnee(EAU), 20.0);put(getDonnee(OXYGENE), 20.0); }})
        );
        this.ajouterGouverneur(new Gouverneur(false, 0, "Zora", false,
                new HashMap<Donnee, Double>() {{ put(getDonnee(FINANCES), 20.0); }})
        );
        this.ajouterGouverneur(new Gouverneur(false, 0, "Tomoya", false,
                new HashMap<Donnee, Double>() {{ put(getDonnee(FINANCES), 10.0); put(getDonnee(EAU),10.0); }})
        );
    }


    /**
     * <p>
     *     Initialise la carte de la planete
     *     <ul>
     *         <li>cree les listes de coordonnees des avant postes et des villes</li>
     *         <li>construit la Carte avec les coordonnees des mines et avant-postes</li>
     *     </ul>
     * </p>
     *
     * @see Planete#cartePlanete
     */
    synchronized public void initialiserCarte(){
        ArrayList<Coordonnee> coordsVille = new ArrayList<Coordonnee>();
        ArrayList<Coordonnee> coordsAvPoste = new ArrayList<Coordonnee>();

        for (Ville  ville : villes){
            coordsVille.add(ville.getCoordonnee());
        }

        for (AvantPoste avantPoste : avantPostes){
            coordsAvPoste.add(avantPoste.getCoordonnee());
        }

        this.cartePlanete = new Carte( coordsAvPoste, coordsVille);
    }

    synchronized public ArrayList<Gouverneur> recupererListeGouverneur() {
        return this.gouverneurs;
    }

    /**
     * <p>
     *     Renvoie les finances de la planete
     * </p>
     *
     * @return renvoie les finances de la planete
     *
     * @see Planete#donnees
     */
    synchronized public double getFinances(){
        return getDonnee(FINANCES).getValeurActuelle();
    }

    /**
     * <p>
     *     Initialise les donnees de la planete
     *     <ul>
     *         <li>ajoute chaque type de deonne a la liste des donnees de la planete</li>
     *     </ul>
     * </p>
     *
     * @see Planete#donnees
     */
    synchronized public void initialiserDonnees() {
        for (TypeDonnee typeDonnee : TypeDonnee.values()) {
            donnees.add(new Donnee(typeDonnee, typeDonnee.getValeurDefaut(),0));
        }
    }

    /**
     * <p>
     *     Initialise la liste des villes de la planete
     * </p>
     *
     * @see Planete#villes
     */
    synchronized public void initialiserVilles() {
        ajouterVille(new Ville("Niederschaeffolsheim", new Coordonnee(100,100,100)));
    }

    /**
     * Demarrer la construction d'un batiment dans une ville de la planete.
     * @param idVille l'id de la ville
     * @param batiment le batiment à construire
     */
    synchronized public void construireBatiment(int idVille, Batiment batiment) {
        getVille(idVille).demarrerConstructionBatiment(batiment);
        getVille(idVille).majDonnees();
    }

    /**
     * ajoute les effets du batiment au donnees de la planete
     * @param batiment le batiment ameliorer
     */
    synchronized public void terminerConstructionBatiment(Batiment batiment){
        for (TypeDonnee typeDonnee : batiment.getEffets().keySet()){
            for (Donnee donnee : this.donnees){
                if (donnee.getTypeDonnee() == typeDonnee){
                    donnee.setCroissance(donnee.getCroissance() + (batiment.getEffets().get(typeDonnee) / SECONDES_DANS_MINUTE));
                }
            }
        }
    }

    /**
     * retire les effets du batiment au donnees de la planete
     * @param idVille id de la ville possedant le batiment en cours d'amelioration
     * @param idBatiment id du batiment en cours d'amelioration
     */
    synchronized public void batimentEnCoursAmelioration(int idVille, int idBatiment) {
        Batiment batiment = getVille(idVille).getBatiment(idBatiment);
        for (TypeDonnee typeDonnee : batiment.getEffets().keySet()){
            for (Donnee donnee : this.donnees){
                if (donnee.getTypeDonnee() == typeDonnee){
                    donnee.setCroissance(donnee.getCroissance() - (batiment.getEffets().get(typeDonnee) / SECONDES_DANS_MINUTE));
                }
            }
        }
    }

    /**
     * ajoute les effets du batiment au donnees de la planete
     * @param batiment le batiment ameliorer
     */
    synchronized public void terminerAmeliorationBatiment(Batiment batiment) {
        for (TypeDonnee typeDonnee : batiment.getEffets().keySet()){
            for (Donnee donnee : this.donnees){
                if (donnee.getTypeDonnee() == typeDonnee){
                    donnee.setCroissance(donnee.getCroissance() + (batiment.getEffets().get(typeDonnee)/ SECONDES_DANS_MINUTE));
                }
            }
        }
    }

    /**
     * Initialise les etats des types de batiment.
     */
    synchronized public void initialiserEtatTypeBatiment() {
        for (TypeBatiment typeBatiment : TypeBatiment.values()) {
            etatTypesBatiment.put(typeBatiment, typeBatiment.estDebloqueParDefaut());
        }
    }

    /**
     * Permet de savoir si un type de batiment est debloque (recherche) ou pas.
     * @param typeBatiment le type de batiment
     * @return <code>true</code> si le batiment est debloque, sinon <code>false</code>
     */
    synchronized public boolean typeBatimentEstDebloque(TypeBatiment typeBatiment) {
        return etatTypesBatiment.get(typeBatiment);
    }

    /**
     * Permet d'obtenir la liste de tous les types de batiments debloques de la planete.
     * @return la liste de tous les types de batiments debloques de la planete
     */
    synchronized public ArrayList<TypeBatiment> getTypeBatimentDebloque() {
        ArrayList<TypeBatiment> typeBatimentDebloque = new ArrayList<>();
        for (TypeBatiment typeBatiment : TypeBatiment.values()) {
            if (typeBatimentEstDebloque(typeBatiment)) typeBatimentDebloque.add(typeBatiment);
        }
        return  typeBatimentDebloque;
    }

    /**
     * Permet d'obtenir la liste de tous les types de batiments non debloques de la planete.
     * @return la liste de tous les types de batiments non debloques de la planete
     */
    synchronized public ArrayList<TypeBatiment> getTypeBatimentNonDebloque() {
        ArrayList<TypeBatiment> typeBatimentNonDebloque = new ArrayList<>();
        for (TypeBatiment typeBatiment : TypeBatiment.values()) {
            if (!typeBatimentEstDebloque(typeBatiment)) typeBatimentNonDebloque.add(typeBatiment);
        }
        return  typeBatimentNonDebloque;
    }

    /**
     * Initialise l'etat des ressources.
     */
    synchronized public void initialiserEtatRessource() {
        for (Ressource ressource : Ressource.values()) {
            etatRessources.put(ressource, ressource.getParent() == null);
        }
    }

    /**
     * Accesseur à une donnees de la liste selon son type
     * @param typeDonnee le type de la donnees
     * @return si trouvee : la donnee, sinon : <code>null</code>
     */
    synchronized public Donnee getDonnee(TypeDonnee typeDonnee) {
        for (Donnee donnee : donnees) {
            if (donnee.getTypeDonnee() == typeDonnee) return donnee;
        }
        return null;
    }

    /**
     * Supprimer le gouverneur de la ville et retire ses effets aux donnees de la planete
     * @param idVille id de la ville a la laquelle on veut revoquer le gouverneur
     */
    synchronized public void revoquerGouverneur(int idVille) {
        Gouverneur gouverneurRevoquer = this.getVille(idVille).getGouverneur();
        for (Donnee effet : gouverneurRevoquer.getEffets().keySet()){
            for (Donnee donnee : this.donnees){
                if (donnee == effet){
                    donnee.setCroissance(donnee.getCroissance() - (gouverneurRevoquer.getEffets().get(effet) / SECONDES_DANS_MINUTE));
                }
            }
        }
        this.getVille(idVille).revoquerGouverneur();
    }

    /**
     * Ajoute un gouverneur a la ville en parametre et ajoute les effets du gouverneurs aux donnees de la planete
     * @param idVille id de la ville a la laquelle on veut ajouter le gouverneur
     */
    synchronized public void affecterGouverneur(Gouverneur gouverneur, int idVille){
        this.getVille(idVille).affecterGouverneur(gouverneur);
        gouverneur.setVilleAffecter(this.getVille(idVille));
        for (Donnee effet : gouverneur.getEffets().keySet()){
            for (Donnee donnee : this.donnees){
                if (donnee == effet){
                    donnee.setCroissance(donnee.getCroissance() + (gouverneur.getEffets().get(effet)/ SECONDES_DANS_MINUTE));
                }
            }
        }
    }

    /**
     * trier la liste de gouverneurs de la planete en fonction de leurs nom
     */
    synchronized public void trierGouverneurParNom() {
        Collections.sort(gouverneurs, Gouverneur.ComparatorNom);
    }

    /**
     * trier la liste de gouverneurs de la planete en fonction de leurs etats estDebloque
     */
    synchronized public void trierGouverneurParDebloque() {
        Collections.sort(gouverneurs, Gouverneur.ComparatorDebloque);
    }

    /**
     * Permet de detruire une ville de la planete et retire les effets qu'avait cette ville sur la planete
     * @param idVille l'id de la ville à detruire
     */
    synchronized public void detruireVille(int idVille) {
        Ville ville = getVille(idVille);
        for (Batiment batiment : ville.getBatiments()){
            for (TypeDonnee typeDonnee : batiment.getEffets().keySet()){
                for (Donnee donnee : this.donnees){
                    if (donnee.getTypeDonnee() == typeDonnee){
                        donnee.setCroissance(donnee.getCroissance() - (batiment.getEffets().get(typeDonnee)/ SECONDES_DANS_MINUTE));
                    }
                }
            }
        }
        this.cartePlanete.effacerVilleCarte(getVille(idVille).getCoordonnee());
        this.villes.remove(getVille(idVille));
    }

    /**
     * Debloquer un type de batiment.
     * @param typeBatiment le type de batiment
     */
    synchronized public void debloquerTypeBatiment(TypeBatiment typeBatiment){
        etatTypesBatiment.replace(typeBatiment, true);
    }

    /**
     * <p>
     *     Accesseur du canvas de la carte
     * </p>
     *
     * @return canvas de la carte
     *
     * @see Planete#cartePlanete
     */
    synchronized public Canvas getCanvasCarte() {
        return this.cartePlanete.getCanvas();
    }

    /**
     * <p>
     *     Accesseur de la carte de la planete
     * </p>
     *
     * @return carte de la planete
     *
     * @see Planete#cartePlanete
     */
    synchronized public Carte getCarte(){
        return this.cartePlanete;
    }

    /**
     * <p>
     *     Ajoute une mine a un avant poste
     * </p>
     *
     * @param idAvantPoste id de l'avant poste auquel ajouter la mine
     * @param mine mine a jouter a l'avantposte
     *
     * @see Planete#avantPostes
     * @see Mine
     */
    synchronized public void ajouterMine(int idAvantPoste, Mine mine) {
        getAvantPoste(idAvantPoste).ajouterMine(mine);
    }

    /**
     * <p>
     *     detruit une mine d'un avant poste
     * </p>
     *
     * @param idAvantPoste id de l'avant poste duquel on veut detruire une mine
     * @param idMine mine
     *
     * @see Planete#avantPostes
     * @see Mine
     */
    synchronized public void detruireMine(int idAvantPoste, int idMine) {
        getAvantPoste(idAvantPoste).detruireMine(idMine);
    }


    /**
     * <p>
     *     Detruit le bâtiment en parametre et retire ses effets des donnees de la planete
     * </p>
     *
     * @param idVille id de la ville possedant le bâtiment a detruire
     * @param idBatiment id du batiment à detruire
     *
     */
    synchronized public void detruireBatiment(int idVille, int idBatiment) {
        Batiment batiment = getVille(idVille).getBatiment(idBatiment);
        for (TypeDonnee typeDonnee : batiment.getEffets().keySet()){
            for (Donnee donnee : this.donnees){
                if (donnee.getTypeDonnee() == typeDonnee){
                    donnee.setCroissance(donnee.getCroissance() - (batiment.getEffets().get(typeDonnee) / SECONDES_DANS_MINUTE));
                }
            }
        }
        getVille(idVille).detruireBatiment(idBatiment);
        getVille(idVille).majDonnees();
    }

    /**
     * <p>
     *     activer ou desactive un batiment et ajoute ou enleve les effets du batiment des donnees de la planete
     * </p>
     *
     * @param idVille id de la ville possedant le bâtiment a activer/desactiver
     * @param idBatiment id du batiment à activer/desactiver
     *
     */
    synchronized public void activerDesactiverBatiment(int idVille, int idBatiment) {
        Batiment batiment = getVille(idVille).getBatiment(idBatiment);
        for (TypeDonnee typeDonnee : batiment.getEffets().keySet()){
            for (Donnee donnee : this.donnees){
                if (donnee.getTypeDonnee() == typeDonnee){
                    if (batiment.estDesactive()){
                        donnee.setCroissance(donnee.getCroissance() + (batiment.getEffets().get(typeDonnee) / SECONDES_DANS_MINUTE));
                    }else{
                        donnee.setCroissance(donnee.getCroissance() - (batiment.getEffets().get(typeDonnee) / SECONDES_DANS_MINUTE));
                    }
                }
            }
        }
        getVille(idVille).getBatiment(idBatiment).activerDesactiver();
        getVille(idVille).majDonnees();
    }

    /**
     * <p>
     *     Accesseur de la recherche de la planete
     * </p>
     *
     * @return recherche de la planete
     *
     * @see Planete#recherche
     */
    synchronized public Recherche getRecherche(){
        return recherche;
    }

    /**
     * Met a jour la population de la planete
     *
     * @see Planete#donnees
     * @see Ville#getPopulation()
     */
    synchronized public void majPopulation() {
        double population = 0;
        for(Ville ville : this.villes){
            population+=ville.getPopulation().getValeurActuelle();
        }
        this.getDonnee(POPULATION).setValeurActuelle(population);
    }
}
