package controler;

import modele.AvantPoste;
import modele.Planete;
import modele.TypeDonnee;

public class ControleurTemps extends Thread{
    private Planete planete;

    public ControleurTemps(Planete planete){
        super("temps");
        this.planete = planete;
    }

    public void majBeneficesMines(){
        int benefices = 0;
        for (AvantPoste avantPoste : planete.getAvantPostes()){
            benefices+=avantPoste.getBeneficesMines()/60;
        }
        planete.getDonnee(TypeDonnee.FINANCES).setCroissance(benefices);
        planete.getDonnee(TypeDonnee.FINANCES).majValeur();
        System.out.println(benefices);
    }

    public void run(){
        while (true){
            majBeneficesMines();
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
