package modele;

import java.util.Calendar;
import java.util.Date;

public class Recherche {
    boolean rechercheEnCours;
    TypeBatiment typeBatimentRecherche = null;
    Date dateFinRecherche;

    public Recherche() {
        this.rechercheEnCours = false;
        this.typeBatimentRecherche = null;
        this.dateFinRecherche = null;
    }

    public void rechercher(TypeBatiment typeBatimentARechercher){
        if (!rechercheEnCours){
            this.typeBatimentRecherche = typeBatimentARechercher;

            dateFinRecherche = new Date();

            Calendar c = Calendar.getInstance();
            c.setTime(dateFinRecherche);
            c.add(Calendar.MINUTE, +typeBatimentARechercher.getTempsRecherche());
            dateFinRecherche = c.getTime();
            rechercheEnCours = true;
        }
    }

    public void finDeRecherche(){
        this.typeBatimentRecherche = null;
        dateFinRecherche = null;
        rechercheEnCours = false;
    }

    public boolean isRechercheEnCours() {
        return rechercheEnCours;
    }

    public TypeBatiment getTypeBatimentRecherche() {
        return typeBatimentRecherche;
    }

    public Date getDateFinRecherche() {
        return dateFinRecherche;
    }
}
