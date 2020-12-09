package vue;
import javafx.application.Application;
import javafx.stage.Stage;

public class NavigateurDesVues extends Application{

    private Stage stage;

    private VueMenuPrincipal vueMenuPrincipal = null;
    private VueMenuStatistiques vueMenuStatistiques = null;
    private VueMenuRecherche vueMenuRecherche = null;
    private VueMenuCulture vueMenuCulture = null;
    private VueMenuPopulation vueMenuPopulation = null;
    private VueMenuSatellites vueMenuSatellites  = null;
    private VueMenuGouverneurs vueMenuGouverneurs = null;

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
        this.vueMenuSatellites = new VueMenuSatellites();
        this.vueMenuGouverneurs = new VueMenuGouverneurs();
    }

    static public NavigateurDesVues getInstance()
    {
        return instance;
    }

    @Override
    public void start(Stage stade) throws Exception {
        this.stage = stade;

        this.stage.setScene(null);
        this.stage.show();

        this.controleur = controler.ControleurPrincipal.getInstance();
        this.controleur.activerVues(this);
        this.vueMenuPrincipal.setControleur(controleur);
        this.vueMenuStatistiques.setControleur(controleur);
        this.vueMenuCulture.setControleur(controleur);
        this.vueMenuPopulation.setControleur(controleur);
        this.vueMenuRecherche.setControleur(controleur);
        this.vueMenuSatellites.setControleur(controleur);
        this.vueMenuGouverneurs.setControleur(controleur);
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

    public void naviguerVersMenuSatellites(){
        stage.setScene(this.vueMenuSatellites);
        stage.show();
    }

    public void naviguerVersMenuGouverneurs(){
        stage.setScene(this.vueMenuGouverneurs);
        stage.show();
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

    public VueMenuSatellites getVueMenuSatellites() {
        return vueMenuSatellites;
    }

    public VueMenuGouverneurs getVueMenuGouverneurs() {
        return vueMenuGouverneurs;
    }

    public VueMenuStatistiques getVueMenuStatistiques() {
        return vueMenuStatistiques;
    }
}
