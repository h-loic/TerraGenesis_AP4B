package controler;


import modele.AvantPoste;
import modele.Coordonnee;
import modele.Mine;
import vue.*;

import java.util.ArrayList;

public class ControleurPrincipal {

    private NavigateurDesVues navigateur;
    private VueMenuPrincipal vueMenuPrincipal = null;
    private VueMenuStatistiques vueMenuStatistiques = null;
    private VueMenuRecherche vueMenuRecherche = null;
    private VueMenuCulture vueMenuCulture = null;
    private VueMenuPopulation vueMenuPopulation = null;
    private VueMenuSatellites vueMenuSatellites = null;
    private VueMenuGouverneurs vueMenuGouverneurs = null;
    private VueAvantPoste vueAvantPoste = null;
    private VueAjouterAvantPoste vueAjouterAvantPoste = null;

    //private Planete planete;

    private ArrayList<AvantPoste> listeAvantPostes; //TODO : remplacer par la liste de Planete

    public ControleurPrincipal()
    {
        System.out.println("Initialisation du controleur");
        this.navigateur = NavigateurDesVues.getInstance();
        this.listeAvantPostes = new ArrayList<>();
        listeAvantPostes.add(new AvantPoste("Belfort", new Coordonnee(10,10,10), new ArrayList<Mine>()));
;    }

    public void activerVues(NavigateurDesVues navigateur)
    {
        this.navigateur = navigateur;
        this.vueMenuStatistiques = navigateur.getVueMenuStatistiques();
        this.vueMenuPrincipal = navigateur.getVueMenuPrincipal();
        this.vueMenuRecherche = navigateur.getVueMenuRecherche();
        this.vueMenuCulture = navigateur.getVueMenuCulture();
        this.vueMenuPopulation = navigateur.getVueMenuPopulation();;
        this.vueMenuGouverneurs = navigateur.getVueMenuGouverneurs();
        this.vueMenuSatellites = navigateur.getVueMenuSatellites();
        this.vueAvantPoste = navigateur.getVueAvantPoste();
        this.vueAjouterAvantPoste = navigateur.getVueAjouterAvantPoste();
        this.vueMenuPrincipal.initialiserMenuPrincipal();
        this.navigateur.naviguerVersVueMenuPrincipal();
    }

    public void notifierNaviguerMenuStatistiques()
    {
        this.vueMenuStatistiques.initialiserMenuStatistiques();
        this.navigateur.naviguerVersMenuStatistiques();
    }

    public void notifierNaviguerMenuPrincipal()
    {
        this.vueMenuPrincipal.initialiserMenuPrincipal();
        this.navigateur.naviguerVersVueMenuPrincipal();
    }

    public void notifierNaviguerMenuRecherche()
    {
        this.vueMenuRecherche.initialiserMenuRecherche();
        this.navigateur.naviguerVersMenuRecherche();
    }

    public void notifierNaviguerMenuPopulation()
    {
        this.vueMenuPopulation.initialiserMenuPopulation(listeAvantPostes);
        this.navigateur.naviguerVersMenuPopulation();
    }

    public void notifierNaviguerMenuCulture()
    {
        this.vueMenuCulture.initialiserMenuCulture();
        this.navigateur.naviguerVersMenuCulture();
    }

    public void notifierNaviguerMenuSatellites()
    {
        this.vueMenuSatellites.initialiserMenuSatellites();
        this.navigateur.naviguerVersMenuSatellites();
    }

    public void notifierNaviguerMenuGouverneurs()
    {
        this.vueMenuGouverneurs.initialiserMenuGouverneurs();
        this.navigateur.naviguerVersMenuGouverneurs();
    }

    public void notifierAjouterAvantPoste(){
        this.listeAvantPostes.add(this.vueAjouterAvantPoste.getAvantPoste());
        this.notifierNaviguerMenuPopulation();
    }

    private static ControleurPrincipal instance = null;
    public static ControleurPrincipal getInstance()
    {
        if(null == instance) instance = new ControleurPrincipal();
        return instance;
    }

    public void notifierNaviguerAfficherAvPoste(int id) {
        this.vueAvantPoste.initialiserVueAvantPoste(getAvantPoste(id));
        this.navigateur.naviguerVersAvantPoste();
    }

    public void notifierNaviguerAjouterAvPoste() {
        this.vueAjouterAvantPoste.initialiserVueAjouterAvantPoste();
        this.navigateur.naviguerVersAjouterAvantPoste();
    }

    public AvantPoste getAvantPoste(int id){
        for (AvantPoste avantPoste : listeAvantPostes) {
            if (avantPoste.getId() == id){
                return avantPoste;
            }
        }
        return null;
    }

    public void notifierDetruireAvantPoste(int idAvantPoste) {
        listeAvantPostes.remove(getAvantPoste(idAvantPoste));
        notifierNaviguerMenuPopulation();
    }
}
