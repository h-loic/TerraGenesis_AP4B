package modele;

import java.util.ArrayList;
import java.util.HashMap;

import static modele.TypeDonne.*;

public class Planete {
    private ArrayList<Ville> villes;
    private ArrayList<AvantPoste> avantPostes;
    private ArrayList<Donnee> donnees;
    private HashMap<TypeInfrastructure, Boolean> etatTypesInfrastructure;
    private ArrayList<Gouverneur> gouverneurs;

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

    public void addAvantPoste(AvantPoste avantPoste){
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

    public void initialiserGouverneur() {
        HashMap<Donnee, Double> effets = new HashMap<Donnee, Double>();
        //effets.put()
        //Gouverneur(false, 0, "Miguel", false, HashMap<Donnee, Double> effets);
    }


    public void initialiserDonnees() {
        Donnee temperature = new Donnee(TEMPERATURE,0,0);
        Donnee pression = new Donnee(PRESSION,0,0);
        Donnee oxygene = new Donnee(OXYGENE,0,0);
        Donnee eau = new Donnee(EAU,0,0);
        Donnee population = new Donnee(POPULATION,0,0);
        Donnee finance = new Donnee(FINANCES,0,0);
    }
}
