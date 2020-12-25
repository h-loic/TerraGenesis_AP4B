package controler;

import modele.*;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ControleurTemps extends Thread{
    private Planete planete;
    private ControleurPrincipal controleurPrincipal;

    public ControleurTemps(Planete planete, ControleurPrincipal controleurPrincipal){
        super("temps");
        this.planete = planete;
        this.controleurPrincipal = controleurPrincipal;
    }

    public void run(){
        while (true){
            majAvantPostes();
            majRecherche();
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void majRecherche(){
        Recherche recherche = planete.getRecherche();
        if (recherche.isRechercheEnCours()){
            Date dateCourante = new Date();
            if (dateCourante.after(recherche.getDateFinRecherche())){
                planete.debloquerTypeBatiment(recherche.getTypeBatimentRecherche());
                recherche.finDeRecherche();
                System.out.println("débloqué");
            }else{
                long diffInMillies = Math.abs(recherche.getDateFinRecherche().getTime() - dateCourante.getTime());
                long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                System.out.println("diff : "+diff+" : "+diffInMillies);
            }
        }
    }

    private void majAvantPostes(){
        int benefices = 0;
        for (AvantPoste avantPoste : planete.getAvantPostes()){
            for(Mine mine : avantPoste.getMines()){
                Date dateNow = new Date();
                if (dateNow.after(mine.getDateEpuissement())) {
                    System.out.println("destruction");
                    mine.setFonctionnelle(false);
                }
            }
            benefices+=avantPoste.getBeneficesMines()/60;
        }
        planete.getDonnee(TypeDonnee.FINANCES).setCroissance(benefices);
        planete.getDonnee(TypeDonnee.FINANCES).majValeur();
        System.out.println(benefices);
    }
}
