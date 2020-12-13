package controler;


import modele.*;
import vue.*;

import java.util.ArrayList;
import java.util.HashMap;

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

    private Planete planete;


    public ControleurPrincipal()
    {
        System.out.println("Initialisation du controleur");
        this.navigateur = NavigateurDesVues.getInstance();

        ArrayList<AvantPoste> listeAvantPostes = new ArrayList<>();
        listeAvantPostes.add(new AvantPoste("Mogadiscio", new Coordonnee(10,10,10), new ArrayList<Mine>()));
        this.planete = new Planete(new ArrayList<Ville>(), listeAvantPostes, new ArrayList<Donnee>(), new HashMap<TypeInfrastructure, Boolean>())

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
        this.vueMenuPopulation.initialiserMenuPopulation(planete.getAvantPostes());
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
        this.planete.AjouterAvantPoste(this.vueAjouterAvantPoste.getAvantPoste());
        this.notifierNaviguerMenuPopulation();
    }

    private static ControleurPrincipal instance = null;
    public static ControleurPrincipal getInstance()
    {
        if(null == instance) instance = new ControleurPrincipal();
        return instance;
    }

    public void notifierNaviguerAfficherAvPoste(int id) {
        this.vueAvantPoste.initialiserVueAvantPoste(planete.getAvantPoste(id));
        this.navigateur.naviguerVersAvantPoste();
    }

    public void notifierNaviguerAjouterAvPoste() {
        this.vueAjouterAvantPoste.initialiserVueAjouterAvantPoste();
        this.navigateur.naviguerVersAjouterAvantPoste();
    }


    public void notifierDetruireAvantPoste(int idAvantPoste) {
        planete.DetruireAvantPoste(idAvantPoste);
        notifierNaviguerMenuPopulation();
    }
}
