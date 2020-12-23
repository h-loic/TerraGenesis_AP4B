package controler;

import modele.AvantPoste;
import modele.Mine;
import modele.Planete;
import modele.TypeDonnee;

import java.util.Date;

public class ControleurTemps extends Thread{
    private Planete planete;

    public ControleurTemps(Planete planete){
        super("temps");
        this.planete = planete;
    }

    public void run(){
        while (true){
            majAvantPostes();
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
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
