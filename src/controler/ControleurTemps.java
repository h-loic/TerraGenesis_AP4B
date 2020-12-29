package controler;

import modele.*;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class ControleurTemps extends Thread{
    private AtomicBoolean estEnMarche = new AtomicBoolean();
    private Planete planete;
    private ControleurPrincipal controleurPrincipal;

    public ControleurTemps(Planete planete, ControleurPrincipal controleurPrincipal){
        super("temps");
        this.planete = planete;
        this.controleurPrincipal = controleurPrincipal;
    }

    public void run(){
        this.estEnMarche.set(true);
        while (estEnMarche.get()){
            majAvantPostes();
            majVilles();
            majRecherche();
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                arreter();
            }
        }
    }

    public void arreter() {
        this.estEnMarche.set(false);
        interrupt();
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
    }

    private void majVilles() {
        Date dateCourante = new Date();
        for (Ville ville : this.planete.getVilles()) {
            ville.majDonnees();
            for (Map.Entry<Batiment, Date> batimentEnConstruction : ville.getBatimentsEnConstruction().entrySet()) {
                if (dateCourante.after(batimentEnConstruction.getValue())) ville.finirConstructionBatiment(batimentEnConstruction.getKey());
            }
            for (Batiment batiment : ville.getBatiments()) {
                if (batiment.estEnCoursAmelioration() && dateCourante.after(batiment.getDateFinAmelioration())) batiment.finirAmelioration();
            }
        }
    }
}
