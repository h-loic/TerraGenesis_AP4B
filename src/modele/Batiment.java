package modele;

import java.util.Date;
import java.util.HashMap;

public class Batiment extends Infrastructure {

    private boolean estDesactive;
    private String description;
    private HashMap<Donnee, Double> effets;
    private Date dateFinConstruction;

}
