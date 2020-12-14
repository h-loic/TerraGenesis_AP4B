package modele;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import static modele.TypeDonne.*;

public class Planete {
    private ArrayList<Ville> villes;
    private ArrayList<AvantPoste> avantPostes;
    private ArrayList<Donnee> donnees;
    private HashMap<TypeInfrastructure, Boolean> etatTypesInfrastructure;
    private ArrayList<Gouverneur> gouverneurs;

    private final static int POS_TEMPERATURE = 0;
    private final static int POS_PRESSION = 1;
    private final static int POS_OXYGENE = 2;
    private final static int POS_EAU = 3;
    private final static int POS_POPULATION = 4;
    private final static int POS_FINANCES = 5;

    public Planete(ArrayList<Ville> villes, ArrayList<AvantPoste> avantPostes, ArrayList<Donnee> donnees, HashMap<TypeInfrastructure, Boolean> etatTypesInfrastructure,ArrayList<Gouverneur> gouverneurs) {
        this.villes = villes;
        this.avantPostes = avantPostes;
        this.donnees = donnees;
        this.etatTypesInfrastructure = etatTypesInfrastructure;
        this.gouverneurs = gouverneurs;
    }

    public Planete(){
        this.villes = new ArrayList<Ville>();
        this.avantPostes = new ArrayList<AvantPoste>();
        this.donnees = new ArrayList<Donnee>();
        this.etatTypesInfrastructure = new HashMap<TypeInfrastructure, Boolean>();
        this.gouverneurs = new ArrayList<Gouverneur>();
    }

    public ArrayList<Ville> getVilles() {
        return villes;
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

    public HashMap<TypeInfrastructure, Boolean> getEtatTypesInfrastructure() {
        return etatTypesInfrastructure;
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
        HashMap<Donnee, Double> effets = new HashMap<Donnee, Double>();
        effets.put(this.donnees.get(0), 10.0);
        this.ajouterGouverneur(new Gouverneur(false, 0, "Michou", false,effets));
        effets.clear();
        effets.put(this.donnees.get(1), 10.0);
        this.ajouterGouverneur(new Gouverneur(false, 0, "Sriky", false,effets));
        effets.clear();
        effets.put(this.donnees.get(2), 10.0);
        this.ajouterGouverneur(new Gouverneur(false, 0, "Alembert", false,effets));
    }

    public ArrayList<Gouverneur> recupererListeGouverneur() {
        return this.gouverneurs;
    }

    public boolean peutPayer(int montant){
        System.out.println(this.donnees.get(POS_FINANCES).getValeurActuelle() >= Double.parseDouble(Integer.toString(montant)));
        System.out.println(this.donnees.get(POS_FINANCES).getValeurActuelle() - Double.parseDouble(Integer.toString(montant)));
        return this.donnees.get(POS_FINANCES).getValeurActuelle() >= Double.parseDouble(Integer.toString(montant));
    }

    public void payer(int montant){
        this.donnees.get(POS_FINANCES).setCroissance(-(Double.parseDouble(Integer.toString(montant))));
        this.donnees.get(POS_FINANCES).majValeur();
    }

    public double getFinances(){
        return this.donnees.get(POS_FINANCES).getValeurActuelle();
    }

    public void initialiserDonnees() {
        ArrayList<Donnee> donneesPlanete = new ArrayList<Donnee>();
        Donnee temperature = new Donnee(TEMPERATURE,0,0);
        donneesPlanete.add(temperature);
        Donnee pression = new Donnee(PRESSION,0,0);
        donneesPlanete.add(pression);
        Donnee oxygene = new Donnee(OXYGENE,0,0);
        donneesPlanete.add(oxygene);
        Donnee eau = new Donnee(EAU,0,0);
        donneesPlanete.add(eau);
        Donnee population = new Donnee(POPULATION,0,0);
        donneesPlanete.add(population);
        Donnee finance = new Donnee(FINANCES,100000,0);
        donneesPlanete.add(finance);
        this.donnees = donneesPlanete;
    }

    public void trierGouverneurParNom() {
        Collections.sort(gouverneurs, Gouverneur.ComparatorNom);
    }

    public void trierGouverneurParDebloque() {
        Collections.sort(gouverneurs, Gouverneur.ComparatorDebloque);
    }
}
