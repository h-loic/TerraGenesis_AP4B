package vue;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * <b>La classe NavigateurDesVues est le point d’entree de l’application, elle herite de la classe Application de la bibliotheque JavaFX </b>
 * <p>
 *     C’est elle qui va initialiser les differentes vues de l’application, et leur affecter un contrôleur.
 *     Elle va egalement permettre au contrôleur d'acceder aux differentes vues  grâce a des accesseurs.
 *     Elle permet de naviguer facilement entre les differentes vues en changeant la scene du stage
 * </p>
 *
 * @see Stage
 * @see controler.ControleurPrincipal
 * @author Zapolatero - lpascuzzi
 */

public class NavigateurDesVues extends Application{

    /**
     * <p>
     *     Stage de l'application
     * </p>
     *
     * @see Stage
     */
    private Stage stage;

    /**
     * <p>
     *     MenuPrincipal de l'application
     * </p>
     *
     * @see VueMenuPrincipal
     */
    private VueMenuPrincipal vueMenuPrincipal = null;

    /**
     * <p>
     *     Menu montrant les donnees de la planete
     * </p>
     *
     * @see VueMenuStatistiques
     */
    private VueMenuStatistiques vueMenuStatistiques = null;

    /**
     * <p>
     *     Menu permettant de rechercher des types de batiments
     * </p>
     *
     * @see VueMenuRecherche
     */
    private VueMenuRecherche vueMenuRecherche = null;

    /**
     * <p>
     *     Menu permettant de gerer la culture de l'application
     * </p>
     *
     * @see VueMenuCulture
     */
    private VueMenuCulture vueMenuCulture = null;

    /**
     * <p>
     *     Vue permettant de gerer la population de la planete : villes et avant-postes
     * </p>
     *
     * @see VueMenuPopulation
     */
    private VueMenuPopulation vueMenuPopulation = null;

    /**
     * <p>
     *     Vue permettant de visualiser la carte de la planete
     * </p>
     *
     * @see VueMenuCarte
     */
    private VueMenuCarte vueMenuCarte  = null;

    /**
     * <p>
     *     Vue permettant de gerer les gouverneurs de la planete
     * </p>
     *
     * @see VueMenuGouverneurs
     */
    private VueMenuGouverneurs vueMenuGouverneurs = null;

    /**
     * <p>
     *     vue permettant de gerer un avant-poste
     * </p>
     *
     * @see VueAvantPoste
     */
    private VueAvantPoste vueAvantPoste = null;

    /**
     * <p>
     *     Vue permettant de gerer un gouverneur
     * </p>
     *
     * @see VueGouverneur
     */
    private VueGouverneur vueGouverneur = null;

    /**
     * <p>
     *     Vue permettant d'affecter un gouverneur a une ville
     * </p>
     *
     * @see VueAffecterGouverneur
     */
    private VueAffecterGouverneur vueAffecterGouverneur = null;

    /**
     * <p>
     *     Vue permettant un avant-poste
     * </p>
     *
     * @see VueAjouterAvantPoste
     */
    private VueAjouterAvantPoste vueAjouterAvantPoste = null;

    /**
     * <p>
     *     Vue permettant d'ajouter une mine
     * </p>
     *
     * @see VueAjouterMine
     */
    private VueAjouterMine vueAjouterMine= null;

    /**
     * <p>
     *     Vue permettant de manipuler une ville
     * </p>
     *
     * @see VueVille
     */
    private VueVille vueVille = null;

    /**
     * <p>
     *     Vue permettant  d'ajouter une ville
     * </p>
     *
     * @see VueAjouterVille
     */
    private VueAjouterVille vueAjouterVille = null;

    /**
     * <p>
     *     Vue permettant  d'ajouter un batiment'
     * </p>
     *
     * @see VueAjouterBatiment
     */
    private VueAjouterBatiment vueAjouterBatiment = null;

    /**
     *<p>
     *     Controleur pricipal de l'application
     *</p>
     * @see controler.ControleurPrincipal
     * */
    private controler.ControleurPrincipal controleur = null;

    /**
     *<p>
     *     Unique instance du navigateur des vue. Le navigateur des vues est un singleton
     *</p>
     *
     * */
    static private NavigateurDesVues instance = null;


    /**
     *<p>
     *     Constructeur de NavigateurDesVues : contruit les differentes vues de l'apllication
     *</p>
     *
     * @see NavigateurDesVues#vueMenuPrincipal
     * @see NavigateurDesVues#vueMenuStatistiques
     * @see NavigateurDesVues#vueMenuRecherche
     * @see NavigateurDesVues#vueMenuCulture
     * @see NavigateurDesVues#vueMenuPopulation
     * @see NavigateurDesVues#vueMenuCarte
     * @see NavigateurDesVues#vueMenuGouverneurs
     * @see NavigateurDesVues#vueAvantPoste
     * @see NavigateurDesVues#vueGouverneur
     * @see NavigateurDesVues#vueAffecterGouverneur
     * @see NavigateurDesVues#vueAjouterAvantPoste
     * @see NavigateurDesVues#vueAjouterMine
     * @see NavigateurDesVues#vueVille
     * @see NavigateurDesVues#vueAjouterVille
     * @see NavigateurDesVues#vueAjouterBatiment
     *
     * */
    public NavigateurDesVues()
    {
        NavigateurDesVues.instance=this;
        this.vueMenuPrincipal = new VueMenuPrincipal();
        this.vueMenuStatistiques = new VueMenuStatistiques();
        this.vueMenuRecherche = new VueMenuRecherche();
        this.vueMenuCulture = new VueMenuCulture();
        this.vueMenuPopulation = new VueMenuPopulation();
        this.vueMenuCarte = new VueMenuCarte();
        this.vueMenuGouverneurs = new VueMenuGouverneurs();
        this.vueAvantPoste = new VueAvantPoste();
        this.vueGouverneur = new VueGouverneur();
        this.vueAffecterGouverneur = new VueAffecterGouverneur();
        this.vueAjouterAvantPoste = new VueAjouterAvantPoste();
        this.vueAjouterMine = new VueAjouterMine();
        this.vueVille = new VueVille();
        this.vueAjouterVille = new VueAjouterVille();
        this.vueAjouterBatiment = new VueAjouterBatiment();
    }

    /**
     *<p>
     *     Retourne l'unique instance de NavigateurDesVues
     *</p>
     *
     * @return L'unique instance de NavigateurDesVues
     *
     * @see NavigateurDesVues#instance
     *
     * */
    static public NavigateurDesVues getInstance()
    {
        return instance;
    }

    /**
     *<p>
     *     Methode start permettant de demarrer l'application : intialise le stage de l'application et affecte le controleur aux differentes vues
     *</p>
     *
     * @param stage stage de l'application
     *
     * @see NavigateurDesVues#instance
     *
     * */
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;

        this.stage.setScene(null);
        this.stage.show();
        this.controleur = controler.ControleurPrincipal.getInstance();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                controleur.notifierFermerApplication();
            }
        });
        this.controleur.activerVues(this);
        this.vueMenuPrincipal.setControleur(controleur);
        this.vueMenuStatistiques.setControleur(controleur);
        this.vueMenuCulture.setControleur(controleur);
        this.vueMenuPopulation.setControleur(controleur);
        this.vueMenuRecherche.setControleur(controleur);
        this.vueMenuCarte.setControleur(controleur);
        this.vueMenuGouverneurs.setControleur(controleur);
        this.vueAvantPoste.setControleur(controleur);
        this.vueGouverneur.setControleur(controleur);
        this.vueAffecterGouverneur.setControleur(controleur);
        this.vueAjouterAvantPoste.setControleur(controleur);
        this.vueAjouterMine.setControleur(controleur);
        this.vueVille.setControleur(controleur);
        this.vueAjouterVille.setControleur(controleur);
        this.vueAjouterBatiment.setControleur(controleur);
    }

    /**
     *<p>
     *     Ferme le stage et donc la fenêtre de l'application
     *</p>
     *
     * @see NavigateurDesVues#stage
     *
     * */
    public void fermerFenetre() {
        stage.close();
    }

    /**
     *<p>
     *     met la vueMenuPrincipal en tant que scene du stage pour l'afficher.
     *</p>
     *
     * @see NavigateurDesVues#stage
     * @see NavigateurDesVues#vueMenuPrincipal
     *
     * */
    public void naviguerVersVueMenuPrincipal()
    {
        stage.setScene(this.vueMenuPrincipal);
        stage.show();
    }

    /**
     *<p>
     *     met la vueMenuStatistiques en tant que scene du stage pour l'afficher.
     *</p>
     *
     * @see NavigateurDesVues#stage
     * @see NavigateurDesVues#vueMenuStatistiques
     *
     * */
    public void naviguerVersMenuStatistiques(){
        stage.setScene(this.vueMenuStatistiques);
        stage.show();
    }

    /**
     *<p>
     *     met la vueMenuRecherche en tant que scene du stage pour l'afficher.
     *</p>
     *
     * @see NavigateurDesVues#stage
     * @see NavigateurDesVues#vueMenuRecherche
     *
     * */
    public void naviguerVersMenuRecherche(){
        stage.setScene(this.vueMenuRecherche);
        stage.show();
    }

    /**
     *<p>
     *     met la vueMenuPopulation en tant que scene du stage pour l'afficher.
     *</p>
     *
     * @see NavigateurDesVues#stage
     * @see NavigateurDesVues#vueMenuPopulation
     *
     * */
    public void naviguerVersMenuPopulation(){
        stage.setScene(this.vueMenuPopulation);
        stage.show();
    }

    /**
     *<p>
     *     met la vueMenuCulture en tant que scene du stage pour l'afficher.
     *</p>
     *
     * @see NavigateurDesVues#stage
     * @see NavigateurDesVues#vueMenuCulture
     *
     * */
    public void naviguerVersMenuCulture(){
        stage.setScene(this.vueMenuCulture);
        stage.show();
    }

    /**
     *<p>
     *     met la vueMenuCarte en tant que scene du stage pour l'afficher.
     *</p>
     *
     * @see NavigateurDesVues#stage
     * @see NavigateurDesVues#vueMenuCarte
     *
     * */
    public void naviguerVersMenuCarte(){
        stage.setScene(this.vueMenuCarte);
        stage.show();
    }

    /**
     *<p>
     *     met la vueMenuGouverneurs en tant que scene du stage pour l'afficher.
     *</p>
     *
     * @see NavigateurDesVues#stage
     * @see NavigateurDesVues#vueMenuGouverneurs
     *
     * */
    public void naviguerVersMenuGouverneurs(){
        stage.setScene(this.vueMenuGouverneurs);
        stage.show();
    }

    /**
     *<p>
     *     met la vueAvantPoste en tant que scene du stage pour l'afficher.
     *</p>
     *
     * @see NavigateurDesVues#stage
     * @see NavigateurDesVues#vueAvantPoste
     *
     * */
    public void naviguerVersAvantPoste(){
        stage.setScene(this.vueAvantPoste);
        stage.show();
    }

    /**
     *<p>
     *     met la vueGouverneur en tant que scene du stage pour l'afficher.
     *</p>
     *
     * @see NavigateurDesVues#stage
     * @see NavigateurDesVues#vueGouverneur
     *
     * */
    public void naviguerVersGouverneur() {
        stage.setScene(this.vueGouverneur);
        stage.show();
    }

    /**
     *<p>
     *     met la vueAffecterGouverneur en tant que scene du stage pour l'afficher.
     *</p>
     *
     * @see NavigateurDesVues#stage
     * @see NavigateurDesVues#vueAffecterGouverneur
     *
     * */
    public void naviguerVersAffecterGouverneur(){
        stage.setScene(this.vueAffecterGouverneur);
        stage.show();
    }

    /**
     *<p>
     *     met la vueAjouterAvantPoste en tant que scene du stage pour l'afficher.
     *</p>
     *
     * @see NavigateurDesVues#stage
     * @see NavigateurDesVues#vueAjouterAvantPoste
     *
     * */
    public void naviguerVersAjouterAvantPoste(){
        stage.setScene(this.vueAjouterAvantPoste);
        stage.show();
    }

    /**
     *<p>
     *     met la vueAjouterMine en tant que scene du stage pour l'afficher.
     *</p>
     *
     * @see NavigateurDesVues#stage
     * @see NavigateurDesVues#vueAjouterMine
     *
     * */
    public void naviguerVersVueAjouterMine() {
        stage.setScene(this.vueAjouterMine);
        stage.show();
    }

    /**
     *<p>
     *     met la vueVille en tant que scene du stage pour l'afficher.
     *</p>
     *
     * @see NavigateurDesVues#stage
     * @see NavigateurDesVues#vueVille
     *
     * */
    public void naviguerVersVueVille() {
        stage.setScene(this.vueVille);
        stage.show();
    }

    /**
     *<p>
     *     met la vueAjouterVille en tant que scene du stage pour l'afficher.
     *</p>
     *
     * @see NavigateurDesVues#stage
     * @see NavigateurDesVues#vueAjouterVille
     *
     * */
    public void naviguerVersVueAjouterVille() {
        stage.setScene(this.vueAjouterVille);
        stage.show();
    }

    /**
     *<p>
     *     met la vueAjouterBatiment en tant que scene du stage pour l'afficher.
     *</p>
     *
     * @see NavigateurDesVues#stage
     * @see NavigateurDesVues#vueAjouterBatiment
     *
     * */
    public void naviguerVersVueAjouterBatiement() {
        stage.setScene(this.vueAjouterBatiment);
        stage.show();
    }

    /**
     *<p>
     *     Retourne la VueAvantPoste
     *</p>
     *
     * @return la VueAvantPoste
     *
     * @see NavigateurDesVues#vueAvantPoste
     *
     * */
    public VueAvantPoste getVueAvantPoste() {
        return vueAvantPoste;
    }

    /**
     *<p>
     *     Retourne la VueAvantPoste
     *</p>
     *
     * @return la VueAvantPoste
     *
     * @see NavigateurDesVues#vueAvantPoste
     *
     * */
    public VueGouverneur getVueGouverneur() {
        return vueGouverneur;
    }

    /**
     *<p>
     *     Retourne la VueAvantPoste
     *</p>
     *
     * @return la VueAvantPoste
     *
     * @see NavigateurDesVues#vueAvantPoste
     *
     * */
    public VueAffecterGouverneur getVueAffecterGouverneur(){ return vueAffecterGouverneur;}

    /**
     *<p>
     *     Retourne la VueAvantPoste
     *</p>
     *
     * @return la VueAvantPoste
     *
     * @see NavigateurDesVues#vueAvantPoste
     *
     * */
    public VueAjouterAvantPoste getVueAjouterAvantPoste() {
        return vueAjouterAvantPoste;
    }

    /**
     *<p>
     *     Retourne la VueAvantPoste
     *</p>
     *
     * @return la VueAvantPoste
     *
     * @see NavigateurDesVues#vueAvantPoste
     *
     * */
    public VueMenuPrincipal getVueMenuPrincipal() {
        return vueMenuPrincipal;
    }

    /**
     *<p>
     *     Retourne la VueAvantPoste
     *</p>
     *
     * @return la VueAvantPoste
     *
     * @see NavigateurDesVues#vueAvantPoste
     *
     * */
    public VueMenuRecherche getVueMenuRecherche() {
        return vueMenuRecherche;
    }

    /**
     *<p>
     *     Retourne la VueAvantPoste
     *</p>
     *
     * @return la VueAvantPoste
     *
     * @see NavigateurDesVues#vueAvantPoste
     *
     * */
    public VueMenuCulture getVueMenuCulture() {
        return vueMenuCulture;
    }

    /**
     *<p>
     *     Retourne la VueAvantPoste
     *</p>
     *
     * @return la VueAvantPoste
     *
     * @see NavigateurDesVues#vueAvantPoste
     *
     * */
    public VueMenuPopulation getVueMenuPopulation() {
        return vueMenuPopulation;
    }

    /**
     *<p>
     *     Retourne la VueAvantPoste
     *</p>
     *
     * @return la VueAvantPoste
     *
     * @see NavigateurDesVues#vueAvantPoste
     *
     * */
    public VueMenuCarte getVueMenuCarte() {
        return vueMenuCarte;
    }

    /**
     *<p>
     *     Retourne la VueMenuGouverneurs
     *</p>
     *
     * @return la VueMenuGouverneurs
     *
     * @see NavigateurDesVues#vueMenuGouverneurs
     *
     * */
    public VueMenuGouverneurs getVueMenuGouverneurs() {
        return vueMenuGouverneurs;
    }

    /**
     *<p>
     *     Retourne la VueMenuStatistiques
     *</p>
     *
     * @return la VueMenuStatistiques
     *
     * @see NavigateurDesVues#vueMenuStatistiques
     *
     * */
    public VueMenuStatistiques getVueMenuStatistiques() {
        return vueMenuStatistiques;
    }

    /**
     *<p>
     *     Retourne la VueAjouterMine
     *</p>
     *
     * @return la VueAjouterMine
     *
     * @see NavigateurDesVues#vueAjouterMine
     *
     * */
    public VueAjouterMine getVueAjouterMine() {
        return this.vueAjouterMine;
    }

    /**
     *<p>
     *     Retourne la VueVille
     *</p>
     *
     * @return la VueVille
     *
     * @see NavigateurDesVues#vueVille
     *
     * */
    public VueVille getVueVille() {
        return this.vueVille;
    }

    /**
     *<p>
     *     Retourne la VueAjouterVille
     *</p>
     *
     * @return la VueAjouterVille
     *
     * @see NavigateurDesVues#vueAjouterVille
     *
     * */
    public VueAjouterVille getVueAjouterVille() {return this.vueAjouterVille;}

    /**
     *<p>
     *     Retourne la VueAjouterBatiment
     *</p>
     *
     * @return la VueAjouterBatiment
     *
     * @see NavigateurDesVues#vueAjouterBatiment
     *
     * */
    public VueAjouterBatiment getVueAjouterBatiment() {return this.vueAjouterBatiment;}
}
