package controler;


//import com.sun.org.apache.bcel.internal.generic.POP;
import javafx.application.Platform;
import modele.*;
import vue.*;

import java.util.ArrayList;

public class ControleurPrincipal {

    private NavigateurDesVues navigateur;
    private VueMenuPrincipal vueMenuPrincipal = null;
    private VueMenuStatistiques vueMenuStatistiques = null;
    private VueMenuRecherche vueMenuRecherche = null;
    private VueMenuCulture vueMenuCulture = null;
    private VueMenuPopulation vueMenuPopulation = null;
    private VueMenuCarte vueMenuCarte = null;
    private VueMenuGouverneurs vueMenuGouverneurs = null;
    private VueAvantPoste vueAvantPoste = null;
    private VueGouverneur vueGouverneur = null;
    private VueAjouterAvantPoste vueAjouterAvantPoste = null;
    private VueAjouterMine vueAjouterMine = null;
    private VueVille vueVille = null;
    private VueAjouterVille vueAjouterVille = null;
    private VueAjouterBatiment vueAjouterBatiment = null;

    private Planete planete;


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
        ControleurTemps controleurTemps = new ControleurTemps(planete);
        controleurTemps.start();
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
        this.vueMenuCarte = navigateur.getVueMenuCarte();
        this.vueAvantPoste = navigateur.getVueAvantPoste();
        this.vueGouverneur = navigateur.getVueGouverneur();
        this.vueAjouterAvantPoste = navigateur.getVueAjouterAvantPoste();
        this.vueAjouterMine = navigateur.getVueAjouterMine();
        this.vueVille = navigateur.getVueVille();
        this.vueAjouterVille = navigateur.getVueAjouterVille();
        this.vueAjouterBatiment = navigateur.getVueAjouterBatiment() ;
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
        this.vueMenuStatistiques.initialiserMenuStatistiques(this.planete.getDonnee(TypeDonnee.PRESSION).getValeurActuelle(), this.planete.getDonnee(TypeDonnee.OXYGENE).getValeurActuelle(), this.planete.getDonnee(TypeDonnee.EAU).getValeurActuelle(),
        this.planete.getDonnee(TypeDonnee.TEMPERATURE).getValeurActuelle(), this.planete.getDonnee(TypeDonnee.POPULATION).getValeurActuelle(), this.planete.getDonnee(TypeDonnee.FINANCES).getValeurActuelle());
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
        this.vueMenuPopulation.initialiserMenuPopulation(planete.getVilles(), planete.getAvantPostes());
        this.navigateur.naviguerVersMenuPopulation();
    }

    public void notifierNaviguerMenuCulture()
    {
        this.vueMenuCulture.initialiserMenuCulture();
        this.navigateur.naviguerVersMenuCulture();
    }

    public void notifierNaviguerMenuCarte()
    {
        this.planete.getCarte().dessiner();
        this.vueMenuCarte.initialiserMenuCarte(planete.getCanvasCarte());
        this.navigateur.naviguerVersMenuCarte();
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
        this.vueAjouterAvantPoste.initialiserVueAjouterAvantPoste(planete.getCanvasCarte());
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

    private static int idVilleCourante = 0;
    public void notifierNaviguerAfficherVille(int id)  {
        this.vueVille.initialiserVueVille(planete.getVille(id));
        idVilleCourante = id;
        this.navigateur.naviguerVersVueVille();
    }

    public void notifierDetruireVille(int idVille) {
        planete.detruireVille(idVille);
        notifierNaviguerMenuPopulation();
    }

    public void notifierNaviguerAjouterVille() throws Exception{
        if (!planete.peutPayer((planete.getVilles().size()+1)*Ville.PRIX_BASE_VILLE)){
            throw new Exception("Fonds insuffisants : " + (planete.getVilles().size()+1)*Ville.PRIX_BASE_VILLE + " requis, disponibles : "+planete.getFinances());
        }
        this.vueAjouterVille.initialiserVueAjouterVille(planete.getCanvasCarte());
        this.navigateur.naviguerVersVueAjouterVille();
    }

    public void notifierAjouterVille() {
        this.planete.payer((planete.getVilles().size()+1)*Ville.PRIX_BASE_VILLE);
        this.planete.ajouterVille(this.vueAjouterVille.getVille());
        this.notifierNaviguerMenuPopulation();
    }

    public void notifierNaviguerAjouterBatiment(int idVille) throws Exception{
        if (!planete.peutConstruire(planete.getVille(idVille))){
            throw new Exception("Plus de places disponible pour construire");
        }
        this.vueAjouterBatiment.initialiserVueAjouterBatiment(idVille, planete.getTypeBatimentDebloque());
        this.navigateur.naviguerVersVueAjouterBatiement();
    }

    public void notifierAjouterBatiment(int idVille) throws Exception {
        Batiment batiment;
        batiment = this.vueAjouterBatiment.getBatiment();
        if (!planete.peutPayer((int) batiment.getTypeBatiment().getCoutConstructionParDefaut())){
            throw new Exception("Fonds insuffisants : " + (batiment.getTypeBatiment().getCoutConstructionParDefaut()) + " requis, disponibles : "+planete.getFinances());
        }
        planete.payer((int) batiment.getTypeBatiment().getCoutConstructionParDefaut());
        this.planete.ajouterBatiment(idVille, batiment);
        this.notifierNaviguerAfficherVille(idVille);

    }

    public void notifierAmeliorerBatiment(int idBatiment, int idVille) throws Exception{
        Batiment batiment = planete.getVille(idVille).getBatiment(idBatiment);
        if (batiment.getNiveau() >= batiment.getTypeBatiment().getNiveauMax()){
            Exception exception = new Exception("Amélioration impossible : le batiment est au niveau maximal");
            throw exception;
        }else if (!this.planete.peutPayer((int) batiment.getPrixAmelioration())){
            throw new Exception("Fonds insuffisants : " + (batiment.getPrixAmelioration()) + " requis, disponibles : "+planete.getFinances());
        }
        this.planete.payer((int) batiment.getPrixAmelioration());
        batiment.ameliorer();
        this.notifierNaviguerAfficherVille(idVille);
    }

    public void notifierDetruireBatiment(int id) {
        this.planete.detruireBatiment(idVilleCourante, id);
        this.notifierNaviguerAfficherVille(idVilleCourante);
    }

    public void notifierActiverDesactiverBatiment(int id) {
        this.planete.activerDesactiverBatiment(idVilleCourante, id);
        this.notifierNaviguerAfficherVille(idVilleCourante);
    }

    public void notifierNaviguerAfficherGouverneur(Gouverneur gouverneur) {
        this.vueGouverneur.initialiserVueGouverneur(gouverneur);
        this.navigateur.naviguerVersGouverneur();
    }



    public void notifierAjouterMine(int idAvantPoste) {
        Mine mine;
        this.planete.payer(this.planete.getAvantPoste(idAvantPoste).getPrixNouvMine());
        mine = this.vueAjouterMine.getMine();
        mine.setNom("Mine n° "+Integer.toString(this.planete.getAvantPoste(idAvantPoste).getMines().size()+1));
        this.planete.ajouterMine(idAvantPoste, mine);
        this.notifierNaviguerAfficherAvPoste(idAvantPoste);
    }

    public void notifierNaviguerAjouterMine(int idAvantPoste) throws Exception{
        if (!planete.peutPayer(planete.getAvantPoste(idAvantPoste).getPrixNouvMine())){
            throw new Exception("Fonds insuffisants : requis : " + planete.getAvantPoste(idAvantPoste).getPrixNouvMine() + ", disponibles : " + this.planete.getFinances());
        }
        this.vueAjouterMine.initialiserVueAjouterMine(idAvantPoste, planete.getAvantPoste(idAvantPoste).getMines());
        this.navigateur.naviguerVersVueAjouterMine();
    }

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

    public void notifierDetruireMine(int id) {
        this.planete.detruireMine(idAvantPosteCourant, id);
        this.notifierNaviguerAfficherAvPoste(idAvantPosteCourant);
    }

    public void notifierAmeliorerGouverneur(Gouverneur gouverneur) {
        if (gouverneur.getNiveau() < 5 && this.planete.peutPayer(gouverneur.getPrixAmelioration())){
            this.planete.payer(gouverneur.getPrixAmelioration());
            gouverneur.ameliorer();
        } else if (gouverneur.getNiveau() >= 5){
            System.out.println("IMPOSSIBLE > 5");
        } else {
            System.out.println("pas assez d'argent frr");
        }
    }

    public void majDonneesVues(){
        vueMenuStatistiques.majStatistiques(this.planete.getFinances());
    }

    public void notifierTrierParNomListeGouverneur() {
        this.planete.trierGouverneurParNom();
    }

    public void notifierTrierParDebloqueListeGouverneur() {
        this.planete.trierGouverneurParDebloque();
    }

    public void notifierDebloquerGouverneur(Gouverneur gouverneur) {
        if (!planete.peutPayer(gouverneur.getPrixBaseGouverneur())){
            System.out.println("pas assez d'argent");
        }else{
            planete.payer(gouverneur.getPrixBaseGouverneur());
            gouverneur.setEstDebloque();
        }
    }


    public boolean verifierCoordonnees(double x, double y){
        return planete.getCarte().verifierCoordonnees(x, y);
    }

}
