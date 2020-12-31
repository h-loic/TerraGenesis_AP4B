package modele;

import javafx.scene.canvas.Canvas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import static modele.TypeDonnee.*;

public class Planete {
    private ArrayList<Ville> villes;
    private ArrayList<AvantPoste> avantPostes;
    private ArrayList<Donnee> donnees;
    private HashMap<TypeBatiment, Boolean> etatTypesBatiment;
    private HashMap<Ressource, Boolean> etatRessources;
    private ArrayList<Gouverneur> gouverneurs;
    private Carte cartePlanete;
    Recherche recherche;


    public Planete(ArrayList<Ville> villes, ArrayList<AvantPoste> avantPostes, ArrayList<Donnee> donnees, HashMap<TypeBatiment, Boolean> etatTypesInfrastructure, ArrayList<Gouverneur> gouverneurs) {
        this.villes = villes;
        this.avantPostes = avantPostes;
        this.donnees = donnees;
        this.etatTypesBatiment = etatTypesInfrastructure;
        this.gouverneurs = gouverneurs;
        this.recherche = new Recherche();
    }

    public Planete(){
        this.villes = new ArrayList<Ville>();
        this.avantPostes = new ArrayList<AvantPoste>();
        this.donnees = new ArrayList<Donnee>();
        this.etatTypesBatiment = new HashMap<TypeBatiment, Boolean>();
        this.etatRessources = new HashMap<Ressource, Boolean>();
        this.gouverneurs = new ArrayList<Gouverneur>();
        this.cartePlanete = new Carte(new ArrayList<Coordonnee>(),new ArrayList<Coordonnee>(),new ArrayList<Coordonnee>());
        this.recherche = new Recherche();
    }

    public void ajouterVille(Ville ville) {
        this.cartePlanete.ajouterVilleCarte(ville.getCoordonnee());
        this.villes.add(ville);
    }

    public ArrayList<Ville> getVilles() {
        return villes;
    }

    public Ville getVille(int id) {
        for (Ville ville : villes) {
            if (ville.getId() == id) return ville;
        }
        return null;
    }

    public ArrayList<AvantPoste> getAvantPostes() {
        return avantPostes;
    }

    public ArrayList<Donnee> getDonnees() {
        return donnees;
    }

    public void ajouterAvantPoste(AvantPoste avantPoste){
        this.avantPostes.add(avantPoste);
    }

    public HashMap<TypeBatiment, Boolean> getEtatTypesInfrastructure() {
        return etatTypesBatiment;
    }

    public AvantPoste getAvantPoste(int id){
        for (AvantPoste avantPoste : this.avantPostes) {
            if (avantPoste.getId() == id){
                return avantPoste;
            }
        }
        return null;
    }

    public void detruireAvantPoste(int idAvantPoste) {
        //destruction et effacement de la carte de toutes les mines de l'avant poste
        AvantPoste avantPoste = getAvantPoste(idAvantPoste);
        for(Mine mine : avantPoste.getMines()){
            this.detruireMine(avantPoste.getId(), mine.getId());
        }
        this.cartePlanete.effacerAvPosteCarte(avantPoste.getCoordonnee());
        this.avantPostes.remove(avantPoste);
    }

    public void AjouterAvantPoste(AvantPoste avantPoste) {
        this.avantPostes.add(avantPoste);
        this.cartePlanete.ajouterAvPosteCarte(avantPoste.getCoordonnee());
    }

    private void ajouterGouverneur(Gouverneur gouverneur) {
        this.gouverneurs.add(gouverneur);
    }

    public void initialiserGouverneur() {
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

    public void initialiserCarte(){
        ArrayList<Coordonnee> coordsMine = new ArrayList<Coordonnee>();
        ArrayList<Coordonnee> coordsVille = new ArrayList<Coordonnee>();
        ArrayList<Coordonnee> coordsAvPoste = new ArrayList<Coordonnee>();

        for (Ville  ville : villes){
            coordsVille.add(ville.getCoordonnee());
        }

        for (AvantPoste avantPoste : avantPostes){
            coordsAvPoste.add(avantPoste.getCoordonnee());
            for (Mine mine : avantPoste.getMines()){
                coordsMine.add(mine.getCoordonnee());
            }
        }

        this.cartePlanete = new Carte(coordsMine, coordsAvPoste, coordsVille);
    }

    public ArrayList<Gouverneur> recupererListeGouverneur() {
        return this.gouverneurs;
    }

    public boolean peutPayer(double montant){
        System.out.println(getDonnee(FINANCES).getValeurActuelle() >= montant);
        System.out.println(getDonnee(FINANCES).getValeurActuelle() - montant);
        return getDonnee(FINANCES).getValeurActuelle() >= montant;
    }


    public boolean peutConstruire(Ville ville) {
        return ville.peutConstruire();
    }

    public boolean peutRechercher(TypeBatiment typeBatiment) {
        if (typeBatiment.getParent() == null) return true;
        return etatTypesBatiment.get(typeBatiment.getParent());
    }

    public void payer(double montant){
        getDonnee(FINANCES).setCroissance(-montant);
        getDonnee(FINANCES).majValeur();
    }

    public double getFinances(){
        return getDonnee(FINANCES).getValeurActuelle();
    }

    public void initialiserDonnees() {
        for (TypeDonnee typeDonnee : TypeDonnee.values()) {
            donnees.add(new Donnee(typeDonnee, typeDonnee.getValeurDefaut(),0));
        }
    }

    public void initialiserVilles() {
        ajouterVille(new Ville("Niederschaeffolsheim", new Coordonnee(100,100,100)));
    }

    public void construireBatiment(int idVille, Batiment batiment) {
        getVille(idVille).demarrerConstructionBatiment(batiment);
        getVille(idVille).majDonnees();
    }

    public void terminerConstructionBatiment(Batiment batiment){
        for (TypeDonnee typeDonnee : batiment.getEffets().keySet()){
            for (Donnee donnee : this.donnees){
                if (donnee.getTypeDonnee() == typeDonnee){
                    donnee.setCroissance(donnee.getCroissance() + batiment.getEffets().get(typeDonnee));
                }
            }
        }
    }

    public void batimentEnCoursAmelioration(int idVille, int idBatiment) {
        Batiment batiment = getVille(idVille).getBatiment(idBatiment);
        for (TypeDonnee typeDonnee : batiment.getEffets().keySet()){
            for (Donnee donnee : this.donnees){
                if (donnee.getTypeDonnee() == typeDonnee){
                    donnee.setCroissance(donnee.getCroissance() - batiment.getEffets().get(typeDonnee));
                }
            }
        }
    }

    public void terminerAmeliorationBatiment(Batiment batiment) {
        for (TypeDonnee typeDonnee : batiment.getEffets().keySet()){
            for (Donnee donnee : this.donnees){
                if (donnee.getTypeDonnee() == typeDonnee){
                    donnee.setCroissance(donnee.getCroissance() + batiment.getEffets().get(typeDonnee));
                }
            }
        }
    }

    public void initialiserEtatTypeBatiment() {
        for (TypeBatiment typeBatiment : TypeBatiment.values()) {
            etatTypesBatiment.put(typeBatiment, typeBatiment.estDebloqueParDefaut());
        }
    }

    public boolean typeBatimentEstDebloque(TypeBatiment typeBatiment) {
        return etatTypesBatiment.get(typeBatiment);
    }

    public ArrayList<TypeBatiment> getTypeBatimentDebloque() {
        ArrayList<TypeBatiment> typeBatimentDebloque = new ArrayList<>();
        for (TypeBatiment typeBatiment : TypeBatiment.values()) {
            if (typeBatimentEstDebloque(typeBatiment)) typeBatimentDebloque.add(typeBatiment);
        }
        return  typeBatimentDebloque;
    }

    public ArrayList<TypeBatiment> getTypeBatimentNonDebloque() {
        ArrayList<TypeBatiment> typeBatimentNonDebloque = new ArrayList<>();
        for (TypeBatiment typeBatiment : TypeBatiment.values()) {
            if (!typeBatimentEstDebloque(typeBatiment)) typeBatimentNonDebloque.add(typeBatiment);
        }
        return  typeBatimentNonDebloque;
    }

    public void initialiserEtatRessource() {
        for (Ressource ressource : Ressource.values()) {
            etatRessources.put(ressource, ressource.getParent() == null);
        }
    }

    public Donnee getDonnee(TypeDonnee typeDonnee) {
        for (Donnee donnee : donnees) {
            if (donnee.getTypeDonnee() == typeDonnee) return donnee;
        }
        return null;
    }

    public void revoquerGouverneur(int idVille) {
        Gouverneur gouverneurRevoquer = this.getVille(idVille).getGouverneur();
        for (Donnee effet : gouverneurRevoquer.getEffets().keySet()){
            for (Donnee donnee : this.donnees){
                if (donnee == effet){
                    donnee.setCroissance(donnee.getCroissance() - gouverneurRevoquer.getEffets().get(effet));
                }
            }
        }
        this.getVille(idVille).revoquerGouverneur();
    }

    public void affecterGouverneur(Gouverneur gouverneur, int idVille){
        this.getVille(idVille).affecterGouverneur(gouverneur);
        gouverneur.setVilleAffecter(this.getVille(idVille));
        for (Donnee effet : gouverneur.getEffets().keySet()){
            for (Donnee donnee : this.donnees){
                if (donnee == effet){
                    donnee.setCroissance(donnee.getCroissance() + gouverneur.getEffets().get(effet));
                }
            }
        }
    }

    public void trierGouverneurParNom() {
        Collections.sort(gouverneurs, Gouverneur.ComparatorNom);
    }

    public void trierGouverneurParDebloque() {
        Collections.sort(gouverneurs, Gouverneur.ComparatorDebloque);
    }

    public void detruireVille(int idVille) {
        Ville ville = getVille(idVille);
        for (Batiment batiment : ville.getBatiments()){
            for (TypeDonnee typeDonnee : batiment.getEffets().keySet()){
                for (Donnee donnee : this.donnees){
                    if (donnee.getTypeDonnee() == typeDonnee){
                        donnee.setCroissance(donnee.getCroissance() - batiment.getEffets().get(typeDonnee));
                    }
                }
            }
        }
        this.cartePlanete.effacerVilleCarte(getVille(idVille).getCoordonnee());
        this.villes.remove(getVille(idVille));
    }

    public void debloquerTypeBatiment(TypeBatiment typeBatiment){
        etatTypesBatiment.replace(typeBatiment, true);
    }

    public Canvas getCanvasCarte() {
        return this.cartePlanete.getCanvas();
    }

    public Carte getCarte(){
        return this.cartePlanete;
    }

    public void ajouterMine(int idAvantPoste, Mine mine) {
        getAvantPoste(idAvantPoste).ajouterMine(mine);
    }

    public void detruireMine(int idAvantPoste, int idMine) {
        getAvantPoste(idAvantPoste).detruireMine(idMine);
    }

    public void detruireBatiment(int idVille, int idBatiment) {
        Batiment batiment = getVille(idVille).getBatiment(idBatiment);
        for (TypeDonnee typeDonnee : batiment.getEffets().keySet()){
            for (Donnee donnee : this.donnees){
                if (donnee.getTypeDonnee() == typeDonnee){
                    donnee.setCroissance(donnee.getCroissance() - batiment.getEffets().get(typeDonnee));
                }
            }
        }
        getVille(idVille).detruireBatiment(idBatiment);
        getVille(idVille).majDonnees();
    }

    public void activerDesactiverBatiment(int idVille, int idBatiment) {
        Batiment batiment = getVille(idVille).getBatiment(idBatiment);
        for (TypeDonnee typeDonnee : batiment.getEffets().keySet()){
            for (Donnee donnee : this.donnees){
                if (donnee.getTypeDonnee() == typeDonnee){
                    if (batiment.estDesactive()){
                        donnee.setCroissance(donnee.getCroissance() + batiment.getEffets().get(typeDonnee));
                    }else{
                        donnee.setCroissance(donnee.getCroissance() - batiment.getEffets().get(typeDonnee));
                    }
                }
            }
        }
        getVille(idVille).getBatiment(idBatiment).activerDesactiver();
        getVille(idVille).majDonnees();
    }

    public Recherche getRecherche(){
        return recherche;
    }

    /**
     * Met à jour la population de la planète
     *
     * @see Planete#donnees
     * @see Ville#getPopulation()
     */
    public void majPopulation() {
        double population = 0;
        for(Ville ville : this.villes){
            population+=ville.getPopulation().getValeurActuelle();
        }
        this.getDonnee(POPULATION).setValeurActuelle(population);
    }
}
