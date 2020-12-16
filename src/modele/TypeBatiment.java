package modele;

import java.util.HashMap;

public enum TypeBatiment {
    USINE_DE_REFROIDISSEMENT("usine de refroidissement", "description", 5,100000.0,50000, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.TEMPERATURE, -4.0);
    }},new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.TEMPERATURE, -2.0);
    }},null),

    PLATEFORME_AEROSTAT("plateforme aérostat", "description", 5,200000.0,50000, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.TEMPERATURE, -40.0);
        put(TypeDonnee.FINANCES, 2850.0);
    }},new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.TEMPERATURE, -10.0);
        put(TypeDonnee.FINANCES, 100.0);
    }}, USINE_DE_REFROIDISSEMENT),

    TOILE_SOLAIRE("Toile solaire", "description", 5,400000.0, 50000,new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.TEMPERATURE, -100.0);
        put(TypeDonnee.FINANCES, -4750.0);
    }},new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.TEMPERATURE, -10.0);
        put(TypeDonnee.EAU, -100.0);
    }}, PLATEFORME_AEROSTAT),

    POLE_DE_RECHAUFFEMENT("pole de rechauffement", "description", 5,100000.0, 50000,new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.TEMPERATURE, 4.0);
    }},new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.TEMPERATURE, 2.0);
    }}, null),

    PUITS_DE_FORAGE("puits de forage", "description", 5,200000.0,50000, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.TEMPERATURE, 60.0);
        put(TypeDonnee.PRESSION, 10.0);
    }},new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.TEMPERATURE, 5.0);
        put(TypeDonnee.PRESSION, 2.0);
    }}, POLE_DE_RECHAUFFEMENT),

    MIROIR_ORBITAL("pole de rechauffement", "description", 5,100000.0, 50000, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.TEMPERATURE, 120.0);
        put(TypeDonnee.EAU, -10.0);
    }},new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.TEMPERATURE, 10.0);
        put(TypeDonnee.EAU, -5.0);
    }}, PUITS_DE_FORAGE);

    private String nom ;
    private String description;
    private int niveauMax;
    private double coutRecherche;
    private double coutConstructionParDefaut;
    private HashMap<TypeDonnee, Double> effetsParDefaut;
    private HashMap<TypeDonnee, Double> variations;
    private TypeBatiment parent;

    private TypeBatiment(String nom, String description, int niveauMax, double coutRecherche, double coutConstructionParDefaut, HashMap<TypeDonnee, Double> effetsParDefaut, HashMap<TypeDonnee, Double> variations, TypeBatiment parent){
        this.nom = nom;
        this.description = description;
        this.niveauMax = niveauMax;
        this.coutRecherche = coutRecherche;
        this.coutConstructionParDefaut = coutConstructionParDefaut;
        this.effetsParDefaut = effetsParDefaut;
        this.variations = variations;
        this.parent = parent;
    }

    public String getNom() {
        return nom;
    }

    public int getNiveauMax() {
        return niveauMax;
    }

    public String getDescription() {
        return description;
    }

    public double getCoutRecherche() {
        return coutRecherche;
    }

    public double getCoutConstructionParDefaut() {
        return coutConstructionParDefaut;
    }

    public HashMap<TypeDonnee, Double> getEffetsParDefaut() {
        return effetsParDefaut;
    }

    public HashMap<TypeDonnee, Double> getVariations() {
        return variations;
    }

    public TypeBatiment getParent() {
        return parent;
    }
}
