package modele;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Mine {

    public static final int PRIX_BASE_MINE = 25000;
    public static final int PRIX_BASE_AMELIORATION   = 1500;

    private Ressource ressource;
    private double rendement;
    private double benefice;
    private Date dateEpuissement;
    private Coordonnee coordonnee;
    private int niveau;
    private String nom;
    private int id;
    private boolean isFonctionnelle; // quand la mine dépasse sa date d epuisement, elle n est plus fonctionnelle

    public Mine(Ressource ressource, double rendement, Coordonnee coordonnee) {
        Random random = new Random();
        this.id = random.nextInt();
        this.nom = "Mine" + Integer.toString(random.nextInt());
        this.ressource = ressource;
        this.coordonnee = coordonnee;
        this.dateEpuissement = new Date();
        this.isFonctionnelle = true;

        Date currentDate = new Date(); // recuperation de la date courante
        
        //ajout de deux jours à la date courante
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, 2);
        dateEpuissement = c.getTime(); // la date d'epuisement de la mine est deux jours après la création de la mine

        this.rendement =  rendement; //kg/min

        this.benefice = (rendement /*/ 60*/)*this.ressource.getValeur();

        System.out.println(dateEpuissement);

        niveau = 1;
    }

    public void ameliorerMine(){
        if(this.isFonctionnelle){
            niveau++;
            rendement *= 1.5;
            this.benefice = (this.rendement /*/ 60*/) * this.ressource.getValeur();

            //la date d'epuisement est avancée de deux heures
            Calendar c = Calendar.getInstance();
            c.setTime(dateEpuissement);
            c.add(Calendar.HOUR, -2);
            dateEpuissement = c.getTime();
            System.out.println(dateEpuissement);
        }
    }

    public int  getPrixAmelioration(){
        return  PRIX_BASE_AMELIORATION * this.niveau;
    }

    public Ressource getRessource() {
        return ressource;
    }

    public double getRendement() {
        return rendement;
    }

    public double getBenefice() {
        if (!isFonctionnelle){
            return 0;
        }
        return benefice;
    }

    public boolean isFonctionnelle() {
        return isFonctionnelle;
    }

    public void setFonctionnelle(boolean fonctionnelle) {
        isFonctionnelle = fonctionnelle;
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

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId(){
        return this.id;
    }
}
