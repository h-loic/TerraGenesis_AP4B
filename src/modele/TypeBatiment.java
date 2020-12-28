package modele;

import java.util.HashMap;

public enum TypeBatiment {

    // TEMPERATURE
    USINE_DE_REFROIDISSEMENT("Usine de refroidissement", "description", 10,0., 0,500000., 5, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.TEMPERATURE, -4.0);
    }},null, true),

    PLATEFORME_AEROSTAT("Plateforme aérostat", "description", 10,2000000.,20, 1000000.,10, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.TEMPERATURE, -40.0);
        put(TypeDonnee.FINANCES, 3000.0);
    }}, USINE_DE_REFROIDISSEMENT, false),

    TOILE_SOLAIRE("Toile solaire", "description", 10,4000000.,40, 2000000.,20,new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.TEMPERATURE, -100.0);
        put(TypeDonnee.FINANCES, -5000.0);
    }}, PLATEFORME_AEROSTAT, false),

    POLE_DE_RECHAUFFEMENT("Pole de réchauffement", "description", 10,0., 0,500000., 5,new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.TEMPERATURE, 4.0);
    }}, null, true),

    PUITS_DE_FORAGE("Puits de forage", "description", 10,2000000.,20, 1000000.,10, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.TEMPERATURE, 60.0);
        put(TypeDonnee.PRESSION, 10.0);
    }}, POLE_DE_RECHAUFFEMENT, false),

    MIROIR_ORBITAL("Miroir orbital", "description", 10,4000000.,40, 2000000.,20, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.TEMPERATURE, 120.0);
        put(TypeDonnee.EAU, -10.0);
    }}, PUITS_DE_FORAGE, false),

    // PRESSION
    USINE_DE_SEQUESTRATION("Usine de séquestration", "description", 10,1000000., 10,500000., 5, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.PRESSION, -4.);
    }}, null,false),

    LABORATOIRE_DE_BIODIVISION("Laboratoire de biodivision", "description", 10,2000000.,20, 1000000.,10, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.PRESSION, -40.);
    }}, USINE_DE_SEQUESTRATION,false),

    TRANSFORMATEUR_HYDROGENE("Transformateur d'hydrogène", "description", 10,4000000.,40, 2000000.,20, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.PRESSION, -100.);
        put(TypeDonnee.EAU, 20.);
        put(TypeDonnee.TEMPERATURE, 10.);
    }}, LABORATOIRE_DE_BIODIVISION,false),

    POUSSIERE_THERMIQUE("Poussière thermique", "description", 10,2000000.,20, 1000000.,10, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.PRESSION, 4.);
    }}, null,false),

    ENSEMBLE_ATMOGEN("Ensemble atmogen", "description", 10,2000000.,20, 1000000.,10, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.PRESSION, 60.);
        put(TypeDonnee.OXYGENE, 10.);
    }}, POUSSIERE_THERMIQUE,false),

    EXPLOITATION_DE_POCHES("Exploitation de poches", "description", 10,4000000.,40, 2000000.,20, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.PRESSION, 100.);
        put(TypeDonnee.OXYGENE, 20.);
        put(TypeDonnee.FINANCES, 2000.);
    }}, ENSEMBLE_ATMOGEN,false),

    //OXYGENE
    FILTRE_O2("Filtre O2", "description", 10,1000000., 10,500000., 5, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.OXYGENE, -4.);
    }}, null,false),

    FIXATEUR_DE_CARBONE("Fixateur de carbone", "description", 10,2000000.,20, 1000000.,10, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.OXYGENE, -40.);
        put(TypeDonnee.PRESSION, 10.);
    }}, FILTRE_O2,false),

    GENERATEUR_HYDRO("Générateur hydro", "description", 10,4000000.,40, 2000000.,20, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.OXYGENE, -80.);
        put(TypeDonnee.EAU, 20.);
    }}, FIXATEUR_DE_CARBONE,false),

    USINE_OXYGENE("Usine d'oxygène", "description", 10,1000000., 10,500000., 5, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.OXYGENE, 4.);
    }}, null,false),

    CYANOCUVES("Cyanocuves", "description", 10,2000000.,20, 1000000.,10, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.OXYGENE, 60.);
        put(TypeDonnee.PRESSION, -10.);
    }}, USINE_OXYGENE,false),

    FERME_DE_KELPS("Ferme de kelps", "description", 10,2000000.,20, 1000000.,10, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.OXYGENE, 120.);
        put(TypeDonnee.PRESSION, -10.);
    }}, CYANOCUVES,false),

    //EAU
    GEOCITERNE("Géociterne", "description", 10,2000000.,20, 1000000.,10, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.EAU, 4.);
    }}, null,false),

    USINE_ELECTROLYSE("Usine d'électrolyse", "description", 10,2000000.,20, 1000000.,10, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.EAU, -40.);
        put(TypeDonnee.OXYGENE, 10.);
    }}, GEOCITERNE,false),

    LANCE_GLACE("Lance-glace", "description", 10,4000000.,40, 2000000.,20, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.EAU, -120.);
        put(TypeDonnee.PRESSION, -10.);
        put(TypeDonnee.FINANCES, 3000.);
    }}, USINE_ELECTROLYSE,false),

    SEMEUR_DE_NUAGES("Semeur de nuages", "description", 10,1000000., 10,500000., 5, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.EAU, 4.);
    }}, null,false),

    RESEAU_AQUIFERE("Réseau aquifère", "description", 10,2000000.,20, 1000000.,10, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.EAU, 40.);
        put(TypeDonnee.PRESSION, 10.);
    }}, SEMEUR_DE_NUAGES,false),

    REDIRECTION_DE_COMETES("Redirection de comètes", "description", 10,4000000.,40, 2000000.,20,new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.EAU, 120.);
        put(TypeDonnee.FINANCES, 8000.);
    }}, RESEAU_AQUIFERE,false),

    //POPULATION
    UNITE_HABITATION("Unité d'habitation", "description", 10,0., 0,500000., 5, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.HABITATION, 66.);
    }}, null,true),

    COMPLEXE_HABITATION("Complexe d'habitation", "description", 10,2000000.,20, 1000000.,10, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.HABITATION, 2400.);
        put(TypeDonnee.OXYGENE, 10.);
    }}, UNITE_HABITATION,false),

    DOME_HABITATION("Dôme d'habitation", "description", 10,4000000.,40, 2000000.,20,new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.HABITATION, 42000.);
    }}, COMPLEXE_HABITATION,false),

    CRECHE("Crèche pour enfant", "description", 10,1000000., 10,500000., 5,new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.POPULATION, 10.);
    }}, null,false),

    RESEAU_TRANSPORT("Réseau de transport", "description", 10,2000000.,20, 1000000.,10, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.POPULATION, 88.);
    }}, CRECHE,false),

    PORT_SPATIAL("Port spatial", "description", 10,4000000.,40, 2000000.,20, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.POPULATION, 140.);
        put(TypeDonnee.PRESSION, 10.);
    }}, RESEAU_TRANSPORT,false);


    private String nom ;
    private String description;
    private int niveauMax;
    private double coutRecherche;
    private int tempsRecherche;
    private double coutConstructionParDefaut;
    private int tempsConstructionParDefaut;
    private HashMap<TypeDonnee, Double> effetsParDefaut;
    private TypeBatiment parent;
    private boolean estDebloqueParDefaut;

    private TypeBatiment(String nom, String description, int niveauMax, double coutRecherche, int tempsRecherche, double coutConstructionParDefaut, int tempsConstructionParDefaut, HashMap<TypeDonnee, Double> effetsParDefaut, TypeBatiment parent, boolean estDebloqueParDefaut){
        this.nom = nom;
        this.description = description;
        this.niveauMax = niveauMax;
        this.coutRecherche = coutRecherche;
        this.tempsRecherche = tempsRecherche;
        this.coutConstructionParDefaut = coutConstructionParDefaut;
        this.tempsConstructionParDefaut = tempsConstructionParDefaut;
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

    public int getTempsRecherche() {
        return tempsRecherche;
    }

    public int getTempsConstructionParDefaut() {
        return tempsConstructionParDefaut;
    }

    @Override
    public String toString() {
        return this.nom;
    }
}
