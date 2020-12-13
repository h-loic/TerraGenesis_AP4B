package modele;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Mine extends Infrastructure {

    private Ressource ressource;
    private double rendement;
    private double benefice;
    private Date dateEpuissement;
    private Coordonnee coordonnee;
    private int niveau;
    private String nom;

    public Mine(Ressource ressource, double rendement, Coordonnee coordonnee) {
        Random random = new Random();
        this.nom = "Mine" + Integer.toString(random.nextInt());
        this.ressource = ressource;
        this.coordonnee = coordonnee;
        this.dateEpuissement = new Date();

        Date currentDate = new Date(); // recuperation de la date courante
        
        //ajout de deux jours à la date courante
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, 2);
        dateEpuissement = c.getTime(); // la date d'epuisement de la mine est deux jours après la création de la mine

        this.rendement =  rendement; //kg/min

        this.benefice = (rendement / 60)*this.ressource.getValeur(); // benefice par seconde

        System.out.println(dateEpuissement);

        niveau = 1;
    }

    public void AmeliorerMine(){
        niveau++;
        rendement*=1.5;
        this.benefice = (rendement / 60)*this.ressource.getValeur();

        //la date d'epuisement est avancée de deux heures
        Calendar c = Calendar.getInstance();
        c.setTime(dateEpuissement);
        c.add(Calendar.DATE, 2);
        c.add(Calendar.HOUR, -2);
        dateEpuissement = c.getTime();
        System.out.println(dateEpuissement);
    }

    public int  getPrixAmelioration(){
        return  25000 * this.niveau/2;
    }

    public Ressource getRessource() {
        return ressource;
    }

    public double getRendement() {
        return rendement;
    }

    public double getBenefice() {
        return benefice;
    }

    public Date getDateEpuissement() {
        return dateEpuissement;
    }

    public Coordonnee getCoordonnee() {
        return coordonnee;
    }

    public String getNom() {
        return nom;
    }

    public int getNiveau() {
        return niveau;
    }
}
