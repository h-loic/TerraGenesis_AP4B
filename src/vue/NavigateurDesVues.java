package vue;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class NavigateurDesVues extends Application{

    private Stage stage;

    private VueMenuPrincipal vueMenuPrincipal = null;
    private VueMenuStatistiques vueMenuStatistiques = null;
    private VueMenuRecherche vueMenuRecherche = null;
    private VueMenuCulture vueMenuCulture = null;
    private VueMenuPopulation vueMenuPopulation = null;
    private VueMenuCarte vueMenuCarte  = null;
    private VueMenuGouverneurs vueMenuGouverneurs = null;
    private VueAvantPoste vueAvantPoste = null;
    private VueGouverneur vueGouverneur = null;
    private VueAffecterGouverneur vueAffecterGouverneur = null;
    private VueAjouterAvantPoste vueAjouterAvantPoste = null;
    private VueAjouterMine vueAjouterMine= null;
    private VueVille vueVille = null;
    private VueAjouterVille vueAjouterVille = null;
    private VueAjouterBatiment vueAjouterBatiment = null;



    private controler.ControleurPrincipal controleur = null;

    static private NavigateurDesVues instance = null;

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

    static public NavigateurDesVues getInstance()
    {
        return instance;
    }

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

    public void fermerFenetre() {
        stage.close();
    }

    public void naviguerVersVueMenuPrincipal()
    {
        stage.setScene(this.vueMenuPrincipal);
        stage.show();
    }

    public void naviguerVersMenuStatistiques(){
        stage.setScene(this.vueMenuStatistiques);
        stage.show();
    }

    public void naviguerVersMenuRecherche(){
        stage.setScene(this.vueMenuRecherche);
        stage.show();
    }

    public void naviguerVersMenuPopulation(){
        stage.setScene(this.vueMenuPopulation);
        stage.show();
    }

    public void naviguerVersMenuCulture(){
        stage.setScene(this.vueMenuCulture);
        stage.show();
    }

    public void naviguerVersMenuCarte(){
        stage.setScene(this.vueMenuCarte);
        stage.show();
    }

    public void naviguerVersMenuGouverneurs(){
        stage.setScene(this.vueMenuGouverneurs);
        stage.show();
    }

    public void naviguerVersAvantPoste(){
        stage.setScene(this.vueAvantPoste);
        stage.show();
    }

    public void naviguerVersGouverneur() {
        stage.setScene(this.vueGouverneur);
        stage.show();
    }

    public void naviguerVersAffecterGouverneur(){
        stage.setScene(this.vueAffecterGouverneur);
        stage.show();
    }

    public void naviguerVersAjouterAvantPoste(){
        stage.setScene(this.vueAjouterAvantPoste);
        stage.show();
    }

    public void naviguerVersVueAjouterMine() {
        stage.setScene(this.vueAjouterMine);
        stage.show();
    }

    public void naviguerVersVueVille() {
        stage.setScene(this.vueVille);
        stage.show();
    }

    public void naviguerVersVueAjouterVille() {
        stage.setScene(this.vueAjouterVille);
        stage.show();
    }

    public void naviguerVersVueAjouterBatiement() {
        stage.setScene(this.vueAjouterBatiment);
        stage.show();
    }

    public VueAvantPoste getVueAvantPoste() {
        return vueAvantPoste;
    }

    public VueGouverneur getVueGouverneur() {
        return vueGouverneur;
    }

    public VueAffecterGouverneur getVueAffecterGouverneur(){ return vueAffecterGouverneur;}

    public VueAjouterAvantPoste getVueAjouterAvantPoste() {
        return vueAjouterAvantPoste;
    }

    public VueMenuPrincipal getVueMenuPrincipal() {
        return vueMenuPrincipal;
    }

    public VueMenuRecherche getVueMenuRecherche() {
        return vueMenuRecherche;
    }

    public VueMenuCulture getVueMenuCulture() {
        return vueMenuCulture;
    }

    public VueMenuPopulation getVueMenuPopulation() {
        return vueMenuPopulation;
    }

    public VueMenuCarte getVueMenuCarte() {
        return vueMenuCarte;
    }

    public VueMenuGouverneurs getVueMenuGouverneurs() {
        return vueMenuGouverneurs;
    }

    public VueMenuStatistiques getVueMenuStatistiques() {
        return vueMenuStatistiques;
    }

    public VueAjouterMine getVueAjouterMine() {
        return this.vueAjouterMine;
    }

    public VueVille getVueVille() {
        return this.vueVille;
    }

    public VueAjouterVille getVueAjouterVille() {return this.vueAjouterVille;}

    public VueAjouterBatiment getVueAjouterBatiment() {return this.vueAjouterBatiment;}
}
