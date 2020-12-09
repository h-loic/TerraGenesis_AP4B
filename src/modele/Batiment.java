package modele;

import java.util.Date;
import java.util.HashMap;

public class Batiment {
    private String nom;
    private boolean estDebloque;
    private int prix;
    private boolean estDesactive;
    private String description;
    private int niveau;
    private HashMap<Donnee, Double> effets;
    private Date dateFinConstruction;

}
