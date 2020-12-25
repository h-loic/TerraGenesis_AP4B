package modele;

import java.util.HashMap;

public enum TypeBatiment {

    // TEMPERATURE
    USINE_DE_REFROIDISSEMENT("usine de refroidissement", "description", 5,0.,200000., new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.TEMPERATURE, -4.0);
    }},null, true),

    PLATEFORME_AEROSTAT("plateforme aérostat", "description", 5,2000000.,50000, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.TEMPERATURE, -40.0);
        put(TypeDonnee.FINANCES, 3000.0);
    }}, USINE_DE_REFROIDISSEMENT, false),

    TOILE_SOLAIRE("Toile solaire", "description", 5,4000000., 50000,new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.TEMPERATURE, -100.0);
        put(TypeDonnee.FINANCES, -5000.0);
    }}, PLATEFORME_AEROSTAT, false),

    POLE_DE_RECHAUFFEMENT("pole de réchauffement", "description", 5,0., 50000,new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.TEMPERATURE, 4.0);
    }}, null, true),

    PUITS_DE_FORAGE("puits de forage", "description", 5,2000000.,50000, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.TEMPERATURE, 60.0);
        put(TypeDonnee.PRESSION, 10.0);
    }}, POLE_DE_RECHAUFFEMENT, false),

    MIROIR_ORBITAL("miroir orbital", "description", 5,4000000., 50000, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.TEMPERATURE, 120.0);
        put(TypeDonnee.EAU, -10.0);
    }}, PUITS_DE_FORAGE, false),

    // PRESSION
    USINE_DE_SEQUESTRATION("usine de séquestration", "description", 5,1000000., 500000, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.PRESSION, -4.);
    }}, null,false),

    LABORATOIRE_DE_BIODIVISION("laboratoire de biodivision", "description", 5,2000000., 500000, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.PRESSION, -40.);
    }}, USINE_DE_SEQUESTRATION,false),

    TRANSFORMATEUR_HYDROGENE("transformateur d'hydrogène", "description", 5,4000000., 500000, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.PRESSION, -100.);
        put(TypeDonnee.EAU, 20.);
        put(TypeDonnee.TEMPERATURE, 10.);
    }}, LABORATOIRE_DE_BIODIVISION,false),

    POUSSIERE_THERMIQUE("poussière thermique", "description", 5,1000000., 500000, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.PRESSION, 4.);
    }}, null,false),

    ENSEMBLE_ATMOGEN("poussière thermique", "description", 5,2000000., 500000, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.PRESSION, 60.);
        put(TypeDonnee.OXYGENE, 10.);
    }}, POUSSIERE_THERMIQUE,false),

    EXPLOITATION_DE_POCHES("exploitation de poches", "description", 5,4000000., 500000, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.PRESSION, 100.);
        put(TypeDonnee.OXYGENE, 20.);
        put(TypeDonnee.FINANCES, 2000.);
    }}, ENSEMBLE_ATMOGEN,false),

    //OXYGENE
    FILTRE_O2("filtre O2", "description", 5,1000000., 500000, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.OXYGENE, -4.);
    }}, null,false),

    FIXATEUR_DE_CARBONE("fixateur de carbone", "description", 5,2000000., 500000, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.OXYGENE, -40.);
        put(TypeDonnee.PRESSION, 10.);
    }}, FILTRE_O2,false),

    GENERATEUR_HYDRO("générateur hydro", "description", 5,4000000., 500000, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.OXYGENE, -80.);
        put(TypeDonnee.EAU, 20.);
    }}, FIXATEUR_DE_CARBONE,false),

    USINE_OXYGENE("usine d'oxygène", "description", 5,1000000., 500000, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.OXYGENE, 4.);
    }}, null,false),

    CYANOCUVES("cyanocuves", "description", 5,2000000., 500000, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.OXYGENE, 60.);
        put(TypeDonnee.PRESSION, -10.);
    }}, USINE_OXYGENE,false),

    FERME_DE_KELPS("ferme de kelps", "description", 5,2000000., 500000, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.OXYGENE, 120.);
        put(TypeDonnee.PRESSION, -10.);
    }}, CYANOCUVES,false),

    //EAU
    GEOCITERNE("géociterne", "description", 5,1000000., 500000, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.EAU, 4.);
    }}, null,false),

    USINE_ELECTROLYSE("usine d'électrolyse", "description", 5,2000000., 500000, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.EAU, -40.);
        put(TypeDonnee.OXYGENE, 10.);
    }}, GEOCITERNE,false),

    LANCE_GLACE("lance-glace", "description", 5,4000000., 500000, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.EAU, -120.);
        put(TypeDonnee.PRESSION, -10.);
        put(TypeDonnee.FINANCES, 3000.);
    }}, USINE_ELECTROLYSE,false),

    SEMEUR_DE_NUAGES("semeur de nuages", "description", 5,1000000., 500000, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.EAU, 4.);
    }}, null,false),

    RESEAU_AQUIFERE("réseau aquifère", "description", 5,2000000., 500000, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.EAU, 40.);
        put(TypeDonnee.PRESSION, 10.);
    }}, SEMEUR_DE_NUAGES,false),

    REDIRECTION_DE_COMETES("redirection de comètes", "description", 5,4000000., 500000, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.EAU, 120.);
        put(TypeDonnee.FINANCES, 8000.);
    }}, RESEAU_AQUIFERE,false),

    //POPULATION
    UNITE_HABITATION("unité d'habitation", "description", 5,0., 500000, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.HABITATION, 66.);
    }}, null,true),

    COMPLEXE_HABITATION("complexe d'habitation", "description", 5,2000000., 500000, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.HABITATION, 2400.);
        put(TypeDonnee.OXYGENE, 10.);
    }}, UNITE_HABITATION,false),

    DOME_HABITATION("dôme d'habitation", "description", 5,4000000., 500000, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.HABITATION, 42000.);
    }}, COMPLEXE_HABITATION,false),

    CRECHE("crèche pour enfant", "description", 5,1000000., 500000, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.POPULATION, 10.);
    }}, null,false),

    RESEAU_TRANSPORT("réseau de transport", "description", 5,2000000., 500000, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.POPULATION, 88.);
    }}, CRECHE,false),

    PORT_SPATIAL("dôme d'habitation", "description", 5,4000000., 500000, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.POPULATION, 140.);
        put(TypeDonnee.PRESSION, 10.);
    }}, RESEAU_TRANSPORT,false);


    private String nom ;
    private String description;
    private int niveauMax;
    private double coutRecherche;
    private double coutConstructionParDefaut;
    private HashMap<TypeDonnee, Double> effetsParDefaut;
    private TypeBatiment parent;
    private boolean estDebloqueParDefaut;

    private TypeBatiment(String nom, String description, int niveauMax, double coutRecherche, double coutConstructionParDefaut, HashMap<TypeDonnee, Double> effetsParDefaut, TypeBatiment parent, boolean estDebloqueParDefaut){
        this.nom = nom;
        this.description = description;
        this.niveauMax = niveauMax;
        this.coutRecherche = coutRecherche;
        this.coutConstructionParDefaut = coutConstructionParDefaut;
        this.effetsParDefaut = effetsParDefaut;
        this.parent = parent;
        this.estDebloqueParDefaut = estDebloqueParDefaut;
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

    public TypeBatiment getParent() {
        return parent;
    }

    public boolean estDebloqueParDefaut() {
        return estDebloqueParDefaut;
    }


    @Override
    public String toString() {
        return this.nom;
    }
}
