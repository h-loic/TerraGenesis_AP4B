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
    private VueGouverneur vueGouverneur = null;
    private VueAjouterAvantPoste vueAjouterAvantPoste = null;
    private VueAjouterMine vueAjouterMine = null;

    private Planete planete;


    public ControleurPrincipal()
    {
        System.out.println("Initialisation du controleur");
        this.navigateur = NavigateurDesVues.getInstance();
        this.planete = new Planete();
        this.planete.ajouterAvantPoste(new AvantPoste("Mogadiscio", new Coordonnee(10,10,10), new ArrayList<Mine>()));
        this.planete.initialiserDonnees();
        this.planete.initialiserGouverneur();
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
        this.vueGouverneur = navigateur.getVueGouverneur();
        this.vueAjouterAvantPoste = navigateur.getVueAjouterAvantPoste();
        this.vueAjouterMine = navigateur.getVueAjouterMine();
        this.vueMenuPrincipal.initialiserMenuPrincipal();
        this.navigateur.naviguerVersVueMenuPrincipal();
    }

    private static ControleurPrincipal instance = null;
    public static ControleurPrincipal getInstance()
    {
        if(null == instance) instance = new ControleurPrincipal();
        return instance;
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
        ArrayList<Gouverneur> listeGouverneur = this.planete.recupererListeGouverneur();
        this.vueMenuGouverneurs.initialiserMenuGouverneurs(listeGouverneur);
        this.navigateur.naviguerVersMenuGouverneurs();
    }

    private static int idAvantPosteCourant = 0;
    public void notifierNaviguerAfficherAvPoste(int id)  {
        this.vueAvantPoste.initialiserVueAvantPoste(planete.getAvantPoste(id));
        idAvantPosteCourant = id;
        this.navigateur.naviguerVersAvantPoste();
    }

    public void notifierNaviguerAjouterAvPoste() throws Exception{
        if (!planete.peutPayer((planete.getAvantPostes().size()+1)*AvantPoste.PRIX_BASE_AVPOSTE)){
            throw new Exception("Fonds insuffisants : " + (planete.getAvantPostes().size()+1)*AvantPoste.PRIX_BASE_AVPOSTE + " requis, disponibles : "+planete.getFinances());
        }
        this.vueAjouterAvantPoste.initialiserVueAjouterAvantPoste(planete.getAvantPostes());
        this.navigateur.naviguerVersAjouterAvantPoste();
    }

    public void notifierAjouterAvantPoste(){
        this.planete.payer((planete.getAvantPostes().size()+1)*AvantPoste.PRIX_BASE_AVPOSTE);
        this.planete.AjouterAvantPoste(this.vueAjouterAvantPoste.getAvantPoste());
        this.notifierNaviguerMenuPopulation();
    }

    public void notifierDetruireAvantPoste(int idAvantPoste) {
        planete.DetruireAvantPoste(idAvantPoste);
        notifierNaviguerMenuPopulation();
    }

    public void notifierNaviguerAffichergouverneur(Gouverneur gouverneur) {
        this.vueGouverneur.initialiserVueGouverneur(gouverneur);
        this.navigateur.naviguerVersGouverneur();
    }

    public void notifierAjouterMine(int idAvantPoste) {
        Mine mine;
        mine = this.vueAjouterMine.getMine();
        mine.setNom("Mine n° "+Integer.toString(this.planete.getAvantPoste(idAvantPoste).getMines().size()+1));
        this.planete.getAvantPoste(idAvantPoste).ajouterMine(mine);
        this.notifierNaviguerAfficherAvPoste(idAvantPoste);
    }

    public void notifierNaviguerAjouterMine(int idAvantPoste) {
        this.vueAjouterMine.initialiserVueAjouterMine(idAvantPoste, planete.getAvantPoste(idAvantPoste).getMines());
        this.navigateur.naviguerVersVueAjouterMine();
    }

    public void notifierAmeliorerMine(int idMine, int idAvantPoste) throws Exception{
        Mine mine = planete.getAvantPoste(idAvantPoste).getMine(idMine);
        if (mine.getNiveau()>= 5){
            Exception exception = new Exception("Amélioration impossible : la mine est au niveau maximal");
            throw exception;
        }
        mine.ameliorerMine();
        this.notifierNaviguerAfficherAvPoste(idAvantPoste);
    }

    public void notifierDetruireMine(int id) {
        this.planete.getAvantPoste(idAvantPosteCourant).detruireMine(id);
        this.notifierNaviguerAfficherAvPoste(idAvantPosteCourant);
    }
}
