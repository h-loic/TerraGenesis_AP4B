package modele;

import java.util.ArrayList;
import java.util.HashMap;

public class Planete {
    private ArrayList<Ville> villes;
    private ArrayList<AvantPoste> avantPostes;
    private ArrayList<Donnee> donnees;
    private HashMap<TypeInfrastructure, Boolean> etatTypesInfrastructure;

    public Planete(ArrayList<Ville> villes, ArrayList<AvantPoste> avantPostes, ArrayList<Donnee> donnees, HashMap<TypeInfrastructure, Boolean> etatTypesInfrastructure) {
        this.villes = villes;
        this.avantPostes = avantPostes;
        this.donnees = donnees;
        this.etatTypesInfrastructure = etatTypesInfrastructure;
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
}
