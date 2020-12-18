package modele;

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


    public Planete(ArrayList<Ville> villes, ArrayList<AvantPoste> avantPostes, ArrayList<Donnee> donnees, HashMap<TypeBatiment, Boolean> etatTypesInfrastructure, ArrayList<Gouverneur> gouverneurs) {
        this.villes = villes;
        this.avantPostes = avantPostes;
        this.donnees = donnees;
        this.etatTypesBatiment = etatTypesInfrastructure;
        this.gouverneurs = gouverneurs;
    }

    public Planete(){
        this.villes = new ArrayList<Ville>();
        this.avantPostes = new ArrayList<AvantPoste>();
        this.donnees = new ArrayList<Donnee>();
        this.etatTypesBatiment = new HashMap<TypeBatiment, Boolean>();
        this.etatRessources = new HashMap<Ressource, Boolean>();
        this.gouverneurs = new ArrayList<Gouverneur>();
    }

    public void ajouterVille(Ville ville) {
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

    public void DetruireAvantPoste(int idAvantPoste) {
        this.avantPostes.remove(getAvantPoste(idAvantPoste));
    }

    public void AjouterAvantPoste(AvantPoste avantPoste) {
        this.avantPostes.add(avantPoste);
    }

    private void ajouterGouverneur(Gouverneur gouverneur) {
        this.gouverneurs.add(gouverneur);
    }

    public void initialiserGouverneur() {
        this.ajouterGouverneur(new Gouverneur(false, 0, "Michou", false,
                new HashMap<Donnee, Double>(){{ put(getDonnee(TEMPERATURE), 10.0); }})
        );
        this.ajouterGouverneur(new Gouverneur(false, 0, "Sriky", false,
                new HashMap<Donnee, Double>(){{ put(getDonnee(PRESSION), 10.0); }})
        );
        this.ajouterGouverneur(new Gouverneur(false, 0, "Alembert", false,
                new HashMap<Donnee, Double>() {{ put(getDonnee(OXYGENE), 10.0); }})
        );
    }

    public ArrayList<Gouverneur> recupererListeGouverneur() {
        return this.gouverneurs;
    }

    public boolean peutPayer(int montant){
        System.out.println(getDonnee(FINANCES).getValeurActuelle() >= Double.parseDouble(Integer.toString(montant)));
        System.out.println(getDonnee(FINANCES).getValeurActuelle() - Double.parseDouble(Integer.toString(montant)));
        return getDonnee(FINANCES).getValeurActuelle() >= Double.parseDouble(Integer.toString(montant));
    }

    public void payer(int montant){
        getDonnee(FINANCES).setCroissance(-(Double.parseDouble(Integer.toString(montant))));
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

    public void initialiserEtatTypeBatiment() {
        for (TypeBatiment typeBatiment : TypeBatiment.values()) {
            etatTypesBatiment.put(typeBatiment, (typeBatiment.getParent() == null));
        }
    }

    public void initialiserEtatRessource() {
        for (Ressource ressource : Ressource.values()) {
            etatRessources.put(ressource, (ressource.getParent() == null));
        }
    }

    public Donnee getDonnee(TypeDonnee typeDonnee) {
        for (Donnee donnee : donnees) {
            if (donnee.getTypeDonnee() == typeDonnee) return donnee;
        }
        return null;
    }

    public void trierGouverneurParNom() {
        Collections.sort(gouverneurs, Gouverneur.ComparatorNom);
    }

    public void trierGouverneurParDebloque() {
        Collections.sort(gouverneurs, Gouverneur.ComparatorDebloque);
    }

    public void detruireVille(int idVille) {
        this.villes.remove(getVille(idVille));
    }
}
