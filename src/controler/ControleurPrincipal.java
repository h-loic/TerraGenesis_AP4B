package controler;


//import com.sun.org.apache.bcel.internal.generic.POP;
import modele.*;
import vue.*;

import java.util.ArrayList;

/**
 * <b>Le ControleurPrincipal est un module qui traite les actions de l'utilisateur, modifie les données du modèle et de la vue </b>
 * <p>
 *     <ul>
 *         <li>
 *             Il intéragit avec le navigateur des vues pour permettre au joueur de naviguer entre les vues
 *         </li>
 *         <li>
 *             Il possède une instance de chaque vue pour pouvoir les manipuler
 *         </li>
 *         <li>
 *             il possède une instance de la classe Planete (qui centralise les autres modèles) pour pouvoir les manipuler
 *         </li>
 *     </ul>
 * </p>
 *
 * @see Planete
 * @see NavigateurDesVues
 * @author Zapolatero - lpascuzzi
 */

public class ControleurPrincipal {

    /**
     * Navigateur des vues permettant de naviguer entre les différentes vues
     *
     * @see ControleurPrincipal#ControleurPrincipal()
     * @see NavigateurDesVues
     */
    private NavigateurDesVues navigateur;

    /**
     * Instance de VueMenuPrincipal
     *
     * @see ControleurPrincipal#ControleurPrincipal()
     * @see VueMenuPrincipal
     */
    private VueMenuPrincipal vueMenuPrincipal = null;

    /**
     * Instance de vueMenuStatistiques
     *
     * @see ControleurPrincipal#ControleurPrincipal()
     * @see VueMenuStatistiques
     */
    private VueMenuStatistiques vueMenuStatistiques = null;

    /**
     * Instance de vueMenuRecherche
     *
     * @see ControleurPrincipal#ControleurPrincipal()
     * @see VueMenuRecherche
     */
    private VueMenuRecherche vueMenuRecherche = null;

    /**
     * Instance de VueMenuCulture
     *
     * @see ControleurPrincipal#ControleurPrincipal()
     * @see VueMenuCulture
     */
    private VueMenuCulture vueMenuCulture = null;

    /**
     * Instance de VueMenuPopulation
     *
     * @see ControleurPrincipal#ControleurPrincipal()
     * @see VueMenuPopulation
     */
    private VueMenuPopulation vueMenuPopulation = null;

    /**
     * Instance de VueMenuCarte
     *
     * @see ControleurPrincipal#ControleurPrincipal()
     * @see VueMenuCarte
     */
    private VueMenuCarte vueMenuCarte = null;

    /**
     * Instance de VueMenuGouverneurs
     *
     * @see ControleurPrincipal#ControleurPrincipal()
     * @see VueMenuGouverneurs
     */
    private VueMenuGouverneurs vueMenuGouverneurs = null;

    /**
     * Instance de VueAvantPoste
     *
     * @see ControleurPrincipal#ControleurPrincipal()
     * @see VueAvantPoste
     */
    private VueAvantPoste vueAvantPoste = null;

    /**
     * Instance de VueGouverneur
     *
     * @see ControleurPrincipal#ControleurPrincipal()
     * @see VueGouverneur
     */
    private VueGouverneur vueGouverneur = null;

    /**
     * Instance de VueAffecterGouverneur
     *
     * @see ControleurPrincipal#ControleurPrincipal()
     * @see VueAffecterGouverneur
     */
    private VueAffecterGouverneur vueAffecterGouverneur = null;

    /**
     * Instance de VueAjouterAvantPoste
     *
     * @see ControleurPrincipal#ControleurPrincipal()
     * @see VueAjouterAvantPoste
     */
    private VueAjouterAvantPoste vueAjouterAvantPoste = null;

    /**
     * Instance de VueAjouterMine
     *
     * @see ControleurPrincipal#ControleurPrincipal()
     * @see VueAjouterMine
     */
    private VueAjouterMine vueAjouterMine = null;

    /**
     * Instance de VueVille
     *
     * @see ControleurPrincipal#ControleurPrincipal()
     * @see VueVille
     */
    private VueVille vueVille = null;

    /**
     * Instance de VueAjouterVille
     *
     * @see ControleurPrincipal#ControleurPrincipal()
     * @see VueAjouterVille
     */
    private VueAjouterVille vueAjouterVille = null;


    private VueAjouterBatiment vueAjouterBatiment = null;

    private ControleurTemps controleurTemps;

    private Planete planete;


    /**
     * <p>
     *     Constructeur de ControleurPrincipal
     *     <ul>
     *         <li>récupère l'instance du navigateur des vues</li>
     *         <li>créé une planète et initialise ses données</li>
     *         <li>Créé et lance le ControleurTemps</li>
     *     </ul>
     * </p>
     *
     * @see ControleurPrincipal#navigateur
     * @see ControleurPrincipal#planete
     * @see ControleurPrincipal#controleurTemps
     * @see Planete
     * @see NavigateurDesVues
     * @see ControleurTemps
     */
    public ControleurPrincipal()
    {
        System.out.println("Initialisation du controleur");
        this.navigateur = NavigateurDesVues.getInstance();
        this.planete = new Planete();
        this.planete.initialiserEtatTypeBatiment();
        this.planete.initialiserEtatRessource();
        this.planete.ajouterAvantPoste(new AvantPoste("Mogadiscio", new Coordonnee(10,10,10), new ArrayList<Mine>()));
        this.planete.initialiserVilles();
        this.planete.initialiserDonnees();
        this.planete.initialiserGouverneur();
        this.planete.initialiserCarte();
        this.controleurTemps = new ControleurTemps(planete, this);
        this.controleurTemps.start();
    }

    /**
     * <p>
     *     Récupère les instances de chaque vue auprès du navigateur des vues
     *     <ul>
     *         <li>récupère les instances des vues</li>
     *         <li>initialise le menu principal</li>
     *         <li>navigue vers le menu principal</li>
     *     </ul>
     * </p>
     *
     * @param navigateur Unique instance du Navigateur des Vues
     *
     * @see ControleurPrincipal#navigateur
     * @see VueMenuPrincipal
     * @see NavigateurDesVues
     */
    public void activerVues(NavigateurDesVues navigateur)
    {
        this.navigateur = navigateur;
        this.vueMenuStatistiques = navigateur.getVueMenuStatistiques();
        this.vueMenuPrincipal = navigateur.getVueMenuPrincipal();
        this.vueMenuRecherche = navigateur.getVueMenuRecherche();
        this.vueMenuCulture = navigateur.getVueMenuCulture();
        this.vueMenuPopulation = navigateur.getVueMenuPopulation();;
        this.vueMenuGouverneurs = navigateur.getVueMenuGouverneurs();
        this.vueMenuCarte = navigateur.getVueMenuCarte();
        this.vueAvantPoste = navigateur.getVueAvantPoste();
        this.vueGouverneur = navigateur.getVueGouverneur();
        this.vueAffecterGouverneur = navigateur.getVueAffecterGouverneur();
        this.vueAjouterAvantPoste = navigateur.getVueAjouterAvantPoste();
        this.vueAjouterMine = navigateur.getVueAjouterMine();
        this.vueVille = navigateur.getVueVille();
        this.vueAjouterVille = navigateur.getVueAjouterVille();
        this.vueAjouterBatiment = navigateur.getVueAjouterBatiment() ;
        this.vueMenuPrincipal.initialiserMenuPrincipal();
        this.navigateur.naviguerVersVueMenuPrincipal();
    }

    /**
     * Unique instance du ControleurPrincipal
     *      Le controleur principal est un singleton
     *
     * @see ControleurPrincipal#getInstance()
     */
    private static ControleurPrincipal instance = null;



    /**
     * accesseur de l'unique instance de ControleurPrincipal
     *
     * @return l'unique instance de ControleurPrincipal
     *
     * @see ControleurPrincipal#instance
     */
    public static ControleurPrincipal getInstance()
    {
        if(null == instance) instance = new ControleurPrincipal();
        return instance;
    }

    /**
     * Ferme l'application en faisant fermer le stage et en arretant le ControleurTemps
     * @see NavigateurDesVues#fermerFenetre()
     * @see ControleurTemps#arreter()
     */
    public void notifierFermerApplication() {
        this.navigateur.fermerFenetre();
        this.controleurTemps.arreter();
    }

    /**
     * Initialise la VueMenuStatistiques et navigue vers celle-ci
     *
     * @see VueMenuStatistiques
     * @see ControleurPrincipal#vueMenuStatistiques
     * @see NavigateurDesVues#naviguerVersMenuStatistiques()
     */
    public void notifierNaviguerMenuStatistiques()
    {
        this.vueMenuStatistiques.initialiserMenuStatistiques(this.planete.getDonnee(TypeDonnee.PRESSION).getValeurActuelle(), this.planete.getDonnee(TypeDonnee.OXYGENE).getValeurActuelle(), this.planete.getDonnee(TypeDonnee.EAU).getValeurActuelle(),
        this.planete.getDonnee(TypeDonnee.TEMPERATURE).getValeurActuelle(), this.planete.getDonnee(TypeDonnee.POPULATION).getValeurActuelle(), this.planete.getDonnee(TypeDonnee.FINANCES).getValeurActuelle());
        this.navigateur.naviguerVersMenuStatistiques();
    }

    /**
     * Initialise la VueMenuPrincipal et navigue vers celle-ci
     *
     * @see VueMenuPrincipal
     * @see ControleurPrincipal#vueMenuPrincipal
     * @see NavigateurDesVues#naviguerVersVueMenuPrincipal()
     */
    public void notifierNaviguerMenuPrincipal()
    {
        this.vueMenuPrincipal.initialiserMenuPrincipal();
        this.navigateur.naviguerVersVueMenuPrincipal();
    }

    /**
     * Initialise la VueMenuRecherche et navigue vers celle-ci
     *
     * @see VueMenuRecherche
     * @see ControleurPrincipal#vueMenuRecherche
     * @see NavigateurDesVues#naviguerVersMenuRecherche()
     */
    public void notifierNaviguerMenuRecherche()
    {
        this.vueMenuRecherche.initialiserMenuRecherche(planete.getTypeBatimentNonDebloque(), planete.getRecherche().isRechercheEnCours(), planete.getRecherche().getTypeBatimentRecherche());
        this.navigateur.naviguerVersMenuRecherche();
    }

    /**
     * Initialise la VueMenuPopulation et navigue vers celle-ci
     *
     * @see VueMenuPopulation
     * @see ControleurPrincipal#vueMenuPopulation
     * @see NavigateurDesVues#naviguerVersMenuPopulation()
     */
    public void notifierNaviguerMenuPopulation()
    {
        this.vueMenuPopulation.initialiserMenuPopulation(planete.getVilles(), planete.getAvantPostes());
        this.navigateur.naviguerVersMenuPopulation();
    }

    /**
     * Initialise la VueMenuCulture et navigue vers celle-ci
     *
     * @see VueMenuCulture
     * @see ControleurPrincipal#vueMenuCulture
     * @see NavigateurDesVues#naviguerVersMenuCulture()
     */
    public void notifierNaviguerMenuCulture()
    {
        this.vueMenuCulture.initialiserMenuCulture();
        this.navigateur.naviguerVersMenuCulture();
    }

    /**
     * Initialise la VueMenuCarte et navigue vers celle-ci
     *
     * @see VueMenuCarte
     * @see ControleurPrincipal#vueMenuCarte
     * @see NavigateurDesVues#naviguerVersMenuCarte()
     */
    public void notifierNaviguerMenuCarte()
    {
        this.planete.getCarte().dessiner();
        this.vueMenuCarte.initialiserMenuCarte(planete.getCanvasCarte());
        this.navigateur.naviguerVersMenuCarte();
    }

    /**
     * Initialise la VueMenuGouverneurs et navigue vers celle-ci
     *
     * @see VueMenuGouverneurs
     * @see ControleurPrincipal#vueMenuGouverneurs
     * @see NavigateurDesVues#naviguerVersMenuGouverneurs() ()
     */
    public void notifierNaviguerMenuGouverneurs()
    {
        ArrayList<Gouverneur> listeGouverneur = this.planete.recupererListeGouverneur();
        this.vueMenuGouverneurs.initialiserMenuGouverneurs(listeGouverneur);
        this.navigateur.naviguerVersMenuGouverneurs();
    }

    /**
     * Identifiant de l'avantPoste actuellement manipulé par le controleur,
     * change lorsque l'on affiche la page d'un avantPoste
     *
     * @see AvantPoste
     * @see AvantPoste#getId()
     */
    private static int idAvantPosteCourant = 0;

    /**
     * Initialise la VueAvantPoste, change l'id de l'avantPoste courant et navigue vers la VueAvantPoste
     *
     * @param id id de l'avantPoste que l'on souhaite afficher
     *
     * @see AvantPoste
     * @see AvantPoste#getId()
     * @see VueAvantPoste
     */
    public void notifierNaviguerAfficherAvPoste(int id)  {
        this.vueAvantPoste.initialiserVueAvantPoste(planete.getAvantPoste(id));
        idAvantPosteCourant = id;
        this.navigateur.naviguerVersAvantPoste();
    }

    /**
     * Initialise la VueAjouterAvantPoste, et navigue vers celle ci.
     * Lance une exception si le joueur ne peut pas payer un nouvel avant-poste
     *
     * @see VueAjouterAvantPoste
     * @see ControleurPrincipal#vueAjouterAvantPoste
     * @see NavigateurDesVues#getVueAjouterAvantPoste()
     */
    public void notifierNaviguerAjouterAvPoste() throws Exception{
        //prix d'un nouvel avant poste : nombre d'avPostes dans la planete * prix de base d'un avant-poste
        if (!planete.peutPayer((planete.getAvantPostes().size()+1)*AvantPoste.PRIX_BASE_AVPOSTE)){
            throw new Exception("Fonds insuffisants : " + (planete.getAvantPostes().size()+1)*AvantPoste.PRIX_BASE_AVPOSTE + " requis, disponibles : "+planete.getFinances());
        }
        this.vueAjouterAvantPoste.initialiserVueAjouterAvantPoste(planete.getCanvasCarte());
        this.navigateur.naviguerVersAjouterAvantPoste();
    }

    /**
     * <p>
     *     Ajoute un AvantPoste :
     *     <li>
     *         fait payer l'avant poste à la planète
     *     </li>
     *     <li>
     *         récupére un AvantPoste créé à partir des données entrées dans la VueAjouterAvantPoste et l'ajoute à la planète
     *     </li>
     *     <li>
     *         Navigue vers la VueMenuPopulation
     *     </li>
     * </p>
     *
     * @see VueAjouterAvantPoste
     * @see ControleurPrincipal#planete
     * @see VueAjouterAvantPoste#getAvantPoste()
     */
    public void notifierAjouterAvantPoste(){
        this.planete.payer((planete.getAvantPostes().size()+1)*AvantPoste.PRIX_BASE_AVPOSTE);
        this.planete.AjouterAvantPoste(this.vueAjouterAvantPoste.getAvantPoste());
        this.notifierNaviguerMenuPopulation();
    }

    /**
     * <p>
     *     Détruit l'avantPoste dont l'id est donné en paramètre et navigue vers la VueMenuPopulation
     * </p>
     *
     * @param idAvantPoste id de l'avantPoste à détruire
     *
     * @see Planete#detruireAvantPoste
     * @see ControleurPrincipal#planete
     * @see VueMenuPopulation
     */
    public void notifierDetruireAvantPoste(int idAvantPoste) {
        planete.detruireAvantPoste(idAvantPoste);
        notifierNaviguerMenuPopulation();
    }

    /**
     * Identifiant de la ville actuellement manipulée par le controleur,
     * change lorsque l'on affiche la page d'une ville
     *
     * @see Ville
     * @see Ville#getId()
     */
    private static int idVilleCourante = 0;

    /**
     * Initialise la VueVille, change l'id de la ville courante et navigue vers la VueVille
     *
     * @param id de la ville que l'on souhaite afficher
     *
     * @see Ville
     * @see Ville#getId()
     * @see VueVille
     */
    public void notifierNaviguerAfficherVille(int id)  {
        this.vueVille.initialiserVueVille(planete.getVille(id));
        idVilleCourante = id;
        this.navigateur.naviguerVersVueVille();
    }

    /**
     * <p>
     *     Détruit la ville dont l'id est donné en paramètre et navigue vers la VueMenuPopulation
     * </p>
     *
     * @param idVille id de la ville à détruire
     *
     * @see Planete#detruireVille(int)
     * @see ControleurPrincipal#planete
     * @see VueMenuPopulation
     */
    public void notifierDetruireVille(int idVille) {
        planete.detruireVille(idVille);
        notifierNaviguerMenuPopulation();
    }

    /**
     * Initialise la VueAjouterVille, et navigue vers celle ci.
     * Lance une exception si le joueur ne peut pas payer une nouvelle ville
     *
     * @see VueAjouterVille
     * @see ControleurPrincipal#vueAjouterVille
     * @see NavigateurDesVues#getVueAjouterVille()
     */
    public void notifierNaviguerAjouterVille() throws Exception{
        //prix d'une nouvelle ville : nombre de villes de la planète * prix de base d'une ville
        if (!planete.peutPayer((planete.getVilles().size()+1) * Ville.PRIX_BASE_VILLE)){
            throw new Exception("Fonds insuffisants : " + (planete.getVilles().size()+1)*Ville.PRIX_BASE_VILLE + " requis, disponibles : "+planete.getFinances());
        }
        this.vueAjouterVille.initialiserVueAjouterVille(planete.getCanvasCarte());
        this.navigateur.naviguerVersVueAjouterVille();
    }

    /**
     * <p>
     *     Ajoute une Ville :
     *     <li>
     *         fait payer la ville à la planète
     *     </li>
     *     <li>
     *         récupére une Ville créée à partir des données entrées dans la VueAjouterVille et l'ajoute à la planète
     *     </li>
     *     <li>
     *         Navigue vers la VueMenuPopulation
     *     </li>
     * </p>
     *
     * @see VueAjouterVille
     * @see ControleurPrincipal#planete
     * @see VueAjouterVille#getVille()
     */
    public void notifierAjouterVille() {
        this.planete.payer(((planete.getVilles().size()+1)*Ville.PRIX_BASE_VILLE));
        this.planete.ajouterVille(this.vueAjouterVille.getVille());
        this.notifierNaviguerMenuPopulation();
    }

    /**
     * Initialise la VueAjouterBatiment, et navigue vers celle ci.
     * Lance une exception si il n'y a plus de place dans la ville ou l'on veut construire le Batiment
     *
     * @see VueAjouterBatiment
     * @see ControleurPrincipal#vueAjouterBatiment
     * @see NavigateurDesVues#getVueAjouterBatiment()
     */
    public void notifierNaviguerAjouterBatiment(int idVille) throws Exception{
        if (!planete.peutConstruire(planete.getVille(idVille))){
            throw new Exception("Plus de places disponible pour construire");
        }
        this.vueAjouterBatiment.initialiserVueAjouterBatiment(idVille, planete.getTypeBatimentDebloque());
        this.navigateur.naviguerVersVueAjouterBatiement();
    }

    /**
     * <p>
     *     Ajoute une Ville :
     *     <li>
     *         fait payer le batiment à la planète
     *     </li>
     *     <li>
     *         construit le batiment
     *     </li>
     *     <li>
     *         Navigue vers la VueVille
     *     </li>
     * </p>
     *
     * @see VueAjouterBatiment
     * @see ControleurPrincipal#planete
     * @see VueAjouterBatiment#getBatiment()
     */
    public void notifierAjouterBatiment(int idVille) throws Exception {
        Batiment batiment;
        batiment = this.vueAjouterBatiment.getBatiment();
        if (!planete.peutPayer(batiment.getTypeBatiment().getCoutConstructionParDefaut())){
            throw new Exception("Fonds insuffisants : " + (batiment.getTypeBatiment().getCoutConstructionParDefaut()) + " requis, disponibles : "+planete.getFinances());
        }
        planete.payer(batiment.getTypeBatiment().getCoutConstructionParDefaut());
        this.planete.construireBatiment(idVille, batiment);
        this.notifierNaviguerAfficherVille(idVille);

    }

    /**
     * <p>
     *     Améliore une batiment :
     *     <li>
     *         vérifie si le batiment est améliorable
     *     </li>
     *     <li>
     *         fait payer l'amélioration et améliore le batiment
     *     </li>
     *     <li>
     *         Recharge la VueVille
     *     </li>
     * </p>
     *
     * @see VueAjouterBatiment
     * @see ControleurPrincipal#planete
     * @see VueAjouterBatiment#getBatiment()
     */
    public void notifierAmeliorerBatiment(int idBatiment, int idVille) throws Exception{
        Batiment batiment = planete.getVille(idVille).getBatiment(idBatiment);
        if (!batiment.peutAmeliorer()){
            Exception exception = new Exception("Amélioration impossible : le batiment est au niveau maximal");
            throw exception;
        }else if (!this.planete.peutPayer(batiment.getPrixAmelioration())){
            throw new Exception("Fonds insuffisants : " + (batiment.getPrixAmelioration()) + " requis, disponibles : "+planete.getFinances());
        }
        this.planete.payer(batiment.getPrixAmelioration());
        batiment.demarrerAmelioration();
        this.notifierNaviguerAfficherVille(idVille);
    }

    /**
     * <p>
     *     Détruit le Batiment de la ville courante dont l'id est donné en paramètre et actualise la vueVille
     * </p>
     *
     * @param id du batiment à détruire
     *
     * @see Planete#detruireBatiment(int, int)
     * @see ControleurPrincipal#planete
     * @see VueVille
     */
    public void notifierDetruireBatiment(int id) {
        this.planete.detruireBatiment(idVilleCourante, id);
        this.notifierNaviguerAfficherVille(idVilleCourante);
    }

    /**
     * <p>
     *     Active/désactive le batiment dont l'id est donné en paramètre
     * </p>
     *
     * @param id du batiment à détruire
     *
     * @see Planete#detruireBatiment(int, int)
     * @see ControleurPrincipal#planete
     * @see VueVille
     */
    public void notifierActiverDesactiverBatiment(int id) {
        this.planete.activerDesactiverBatiment(idVilleCourante, id);
        this.notifierNaviguerAfficherVille(idVilleCourante);
    }

    /**
     * <p>
     *     Initialise et affiche la VueGouverneur
     * </p>
     *
     * @param gouverneur gouverneur dont on veut afficher les améliorations
     *
     * @see Planete
     * @see ControleurPrincipal#planete
     * @see VueGouverneur
     */
    public void notifierNaviguerAfficherGouverneur(Gouverneur gouverneur) {
        this.vueGouverneur.initialiserVueGouverneur(gouverneur);
        this.navigateur.naviguerVersGouverneur();
    }

    /**
     * <p>
     *     Initialise et affiche la VueAffecterGouverneur
     * </p>
     *
     * @param gouverneur gouverneur auquel on veut affecter une ville
     *
     * @see Planete
     * @see ControleurPrincipal#planete
     * @see VueGouverneur
     */
    public void notifierNaviguerAffecterGouverneur(Gouverneur gouverneur) {
        this.vueAffecterGouverneur.initialiserVueAffecterGouverneur(gouverneur,this.planete);
        this.navigateur.naviguerVersAffecterGouverneur();
    }

    /**
     * <p>
     *     Ajoute une mine à l'avant-poste dont l'id est donné en paramètre :
     *     <li>
     *         fait payer la mine à la planète
     *     </li>
     *     <li>
     *         recupère une mine construite avec les informations entrées dans la page AjouterMine,
     *         lui affecte un nom et construit la mine.
     *     </li>
     *     <li>
     *         Navigue vers la VueAvantPoste
     *     </li>
     * </p>
     *
     * @param idAvantPoste id de l'avantPoste auquel on veut ajouter une mine
     *
     * @see VueAjouterMine
     * @see ControleurPrincipal#planete
     * @see VueAjouterMine#getMine()
     */
    public void notifierAjouterMine(int idAvantPoste) {
        Mine mine;
        this.planete.payer(this.planete.getAvantPoste(idAvantPoste).getPrixNouvMine());
        mine = this.vueAjouterMine.getMine();
        //nom de la mine : "mine" + nb de mines construites dans l'avant-poste +1
        mine.setNom("Mine n° "+Integer.toString(this.planete.getAvantPoste(idAvantPoste).getMines().size()+1));
        this.planete.ajouterMine(idAvantPoste, mine);
        this.notifierNaviguerAfficherAvPoste(idAvantPoste);
    }

    /**
     * <p>
     *     Initialise et affiche la VueAjouterMine.
     *     Lance une exception si le joueur n'a pas assez d'argent pour payer une nouvelle mine
     * </p>
     *
     * @param idAvantPoste id de l'avantPoste auquel on veut ajouter une mine
     *
     * @see VueAjouterMine
     * @see ControleurPrincipal#planete
     */
    public void notifierNaviguerAjouterMine(int idAvantPoste) throws Exception{
        if (!planete.peutPayer(planete.getAvantPoste(idAvantPoste).getPrixNouvMine())){
            throw new Exception("Fonds insuffisants : requis : " + planete.getAvantPoste(idAvantPoste).getPrixNouvMine() + ", disponibles : " + this.planete.getFinances());
        }
        this.vueAjouterMine.initialiserVueAjouterMine(idAvantPoste, planete.getAvantPoste(idAvantPoste).getMines());
        this.navigateur.naviguerVersVueAjouterMine();
    }

    /**
     * <p>
     *     Améliore une mine
     *     <ul>
     *         <li>lance une exception si la mine ne peut pas être améliorée (pas assez d'argent, niveau maximal ou non fonctionnelle)</li>
     *         <li>fait payer l'amélioration à la mine</li>
     *         <li>améliore la mine</li>
     *         <li>recharge la page VueAvantPoste</li>
     *     </ul>
     * </p>
     *
     * @param idAvantPoste id de l'avantPoste contenant la mine que l'on veut améliorer
     * @param idMine id de la mine à améliorer
     *
     * @see VueAvantPoste
     * @see ControleurPrincipal#planete
     * @see Mine#ameliorerMine()
     */
    public void notifierAmeliorerMine(int idMine, int idAvantPoste) throws Exception{
        Mine mine = planete.getAvantPoste(idAvantPoste).getMine(idMine);
        if (mine.getNiveau()>= 5){
            Exception exception = new Exception("Amélioration impossible : la mine est au niveau maximal");
            throw exception;
        }else if (!this.planete.peutPayer(mine.getPrixAmelioration())){
            Exception exception = new Exception("Amélioration impossible : fonds insuffisants");
            throw exception;
        }else if (!mine.isFonctionnelle()){
            Exception exception = new Exception("Amélioration impossible : La mine est épuisée");
            throw exception;
        }
        this.planete.payer(mine.getPrixAmelioration());
        mine.ameliorerMine();
        this.notifierNaviguerAfficherAvPoste(idAvantPoste);
    }

    /**
     * <p>
     *     Détruit une mine
     *     <ul>
     *         <li>Détruit la mine dont l'id est donné en paramètre</li>
     *         <li>Recharge la VueAvantPoste</li>
     *     </ul>
     * </p>
     *
     * @param id id de la mine à détruire
     *
     * @see VueAvantPoste
     * @see ControleurPrincipal#planete
     */
    public void notifierDetruireMine(int id) {
        this.planete.detruireMine(idAvantPosteCourant, id);
        this.notifierNaviguerAfficherAvPoste(idAvantPosteCourant);
    }


    /**
     * <p>
     *     affecte un gouverneur à une ville
     *     <ul>
     *         <li>révoque le grouverneur de la ville si elle en a déjà un</li>
     *         <li>affecte le gouvrneur à la ville</li>
     *         <li>affiche la page du gouverneur</li>
     * </p>
     *
     * @param idVille id de la mine à laquelle affecter le gouverneur
     * @param gouverneur gouverneur que l'on vuet affecter
     *
     * @see VueAffecterGouverneur
     * @see Gouverneur
     */
    public void notifierAffecterGouverneur(Gouverneur gouverneur, int idVille) {
        if (this.planete.getVille(idVille).getGouverneur() != null){
            this.planete.getVille(idVille).revoquerGouverneur();
        }
        this.planete.affecterGouverneur(gouverneur,idVille);
        this.notifierNaviguerAfficherGouverneur(gouverneur);
    }

    /**
     * <p>
     *     Revoque le Gouverneur de la ville donnée en paramètre
     * </p>
     *
     * @param idVille id de la ville dont on veut revoquer le gouverneur
     *
     * @see VueAffecterGouverneur
     * @see Gouverneur
     */
    public void notifierRevoquerGouverneur(int idVille){
        this.planete.revoquerGouverneur(idVille);
        this.notifierNaviguerAfficherVille(idVille);
    }


    /**
     * <p>
        révoque le gouveneur de la ville dont l'id est donné en paramètre et affiche la Vue du gouverneur donné en paramètre
     * </p>
     *
     * @param idVille id de la mine à laquelle affecter le gouverneur
     * @param gouverneur gouverneur que l'on vuet affecter
     *
     * @see VueAffecterGouverneur
     * @see Gouverneur
     */
    public void notifierRevoquerGouverneurDepuisGouverneur(int idVille,Gouverneur gouverneur) {
        this.planete.revoquerGouverneur(idVille);
        this.notifierNaviguerAfficherGouverneur(gouverneur);
    }

    /**
     * <p>
     *     Améliore un Gouverneur
     *     <ul>
     *         <li>Vérifie si il est améliorable</li>
     *         <li>fait payer l'amélioration à la planète</li>
     *         <li>améliore le gouverneur</li>
     *     </ul>
     * </p>
     *
     * @param gouverneur gouvenreur que l'on souhaite améliorer
     *
     * @see VueGouverneur
     * @see ControleurPrincipal#planete
     * @see Gouverneur#ameliorer()
     */
    public void notifierAmeliorerGouverneur(Gouverneur gouverneur) {
        if (gouverneur.getNiveau() < 5 && this.planete.peutPayer(gouverneur.getPrixAmelioration())){
            for (Donnee effet : gouverneur.getEffets().keySet()){
                for (Donnee donnee : this.planete.getDonnees()){
                    if (donnee == effet){
                        donnee.setCroissance(donnee.getCroissance() - gouverneur.getEffets().get(effet));
                    }
                }
            }
            for (Donnee effet : gouverneur.getEffets().keySet()){
                for (Donnee donnee : this.planete.getDonnees()){
                    if (donnee == effet){
                        donnee.setCroissance(donnee.getCroissance() + gouverneur.getEffets().get(effet));
                    }
                }
            }
            this.planete.payer(gouverneur.getPrixAmelioration());
            gouverneur.ameliorer();
        } else if (gouverneur.getNiveau() >= 5){
            System.out.println("IMPOSSIBLE > 5");
        } else {
            System.out.println("pas assez d'argent");
        }
    }

    /**
     * <p>
     *     Actualise la page VueMenuRecherche en l'initialisant avec les dernières données de Recherche
     * </p>
     *
     * @see VueMenuRecherche
     * @see ControleurPrincipal#planete
     * @see Recherche
     */
    public void notifierActualiserMenuRecherche(){
        vueMenuRecherche.initialiserMenuRecherche(planete.getTypeBatimentNonDebloque(), planete.getRecherche().isRechercheEnCours(), planete.getRecherche().getTypeBatimentRecherche());
    }

    /**
     * <p>
     *     met à jour les données de certaines vues en les réinitialisant
     * </p>
     *
     */
    public void majDonneesVues(){
        this.vueMenuStatistiques.initialiserMenuStatistiques(this.planete.getDonnee(TypeDonnee.PRESSION).getValeurActuelle(), this.planete.getDonnee(TypeDonnee.OXYGENE).getValeurActuelle(), this.planete.getDonnee(TypeDonnee.EAU).getValeurActuelle(),
                this.planete.getDonnee(TypeDonnee.TEMPERATURE).getValeurActuelle(), this.planete.getDonnee(TypeDonnee.POPULATION).getValeurActuelle(), this.planete.getDonnee(TypeDonnee.FINANCES).getValeurActuelle());

    }

    /**
     * <p>
     *     Trie la collection des gouverneurs de la planete par nom
     * </p>
     *
     * @see Planete#trierGouverneurParNom()
     *
     */
    public void notifierTrierParNomListeGouverneur() {
        this.planete.trierGouverneurParNom();
    }

    /**
     * <p>
     *     Trie la collection des gouverneurs de la planete par leur état (débloqué ou non)
     * </p>
     *
     * @see Planete#trierGouverneurParDebloque()
     *
     */
    public void notifierTrierParDebloqueListeGouverneur() {
        this.planete.trierGouverneurParDebloque();
    }

    /**
     * <p>
     *     Débloque un gourverneur
     * </p>
     *
     * @see Gouverneur#setEstDebloque()
     *
     */
    public void notifierDebloquerGouverneur(Gouverneur gouverneur) {
        if (!planete.peutPayer(gouverneur.getPrixBaseGouverneur())){
            System.out.println("pas assez d'argent");
        }else{
            planete.payer(gouverneur.getPrixBaseGouverneur());
            gouverneur.setEstDebloque();
        }
    }


    /**
     * <p>
     *     Lance la recherche d'un type de batiment
     *     <ul>
     *         <li>
     *             vérifie si la recherche est possible
     *         </li>
     *         <li>
     *             fait payer la recherche à la planète
     *         </li>
     *         <li>
     *             lance la recherche
     *         </li>
     *         <li>
     *             actualise la VueMenuRecherche
     *         </li>
     *         <li>
     *             lance une exception si la recherche est impossible
     *         </li>
     *     </ul>
     * </p>
     *
     * @param typeBatiment TypeBatiment à recherche
     *
     * @see Recherche
     *
     */
    public void rechercherTypeBatiment(TypeBatiment typeBatiment) throws Exception{
        boolean rechercheEnCours = planete.getRecherche().isRechercheEnCours();

        if (rechercheEnCours){
            System.out.println("déjà recherche");
            throw new Exception("Recherche impossible : recherche déjà en cours");
        }else if (!planete.peutPayer(typeBatiment.getCoutRecherche())){
            System.out.println("trop pauvre");
            throw new Exception("Recherche impossible : fonds insuffisants");
        }else if(! planete.peutRechercher(typeBatiment)) {
            throw new Exception("Recherche impossible : il faut d'abord rechercher la dépendence (" + typeBatiment.getParent() + ")");
        }else {
            planete.payer( typeBatiment.getCoutRecherche());
            planete.getRecherche().rechercher(typeBatiment);

            this.vueMenuRecherche.initialiserMenuRecherche(planete.getTypeBatimentNonDebloque(), planete.getRecherche().isRechercheEnCours(), planete.getRecherche().getTypeBatimentRecherche());
            navigateur.naviguerVersMenuRecherche();
            System.out.println("recherche");
        }
    }


    /**
     * <p>
     *     vérifie les coordonnées en paramètre, pour savoir si elles sont trop proches d'une avant-poste ou d'une ville
     * </p>
     *
     * @param x longitude à vérifier
     * @param y latitude à vérifier
     *
     * @see Carte#verifierCoordonnees(double, double)
     *
     */
    public boolean verifierCoordonnees(double x, double y){
        return planete.getCarte().verifierCoordonnees(x, y);
    }
}
