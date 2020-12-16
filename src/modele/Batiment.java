package modele;

import java.util.Date;
import java.util.HashMap;

public class Batiment {

    private int id;
    private TypeBatiment typeBatiment;
    private int niveau;
    private boolean estDesactive;
    private HashMap<Donnee, Double> effets;
    private Date dateFinConstruction;

}
