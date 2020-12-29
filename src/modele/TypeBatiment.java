package modele;

import java.util.HashMap;

/**
 * <b>TypeBatiment est l'énumération représentant les différentes type de batiment constructible dans le jeu</b>
 * <p>
 * Un type de batiment est caractérisé par les informations suivantes :
 * <ul>
 * <li>Un identifiant unique attribué définitivement.</li>
 * <li>Un nom</li>
 * <li>Une description</li>
 * <li>Un niveau maximum</li>
 * <li>Un cout de recherche</li>
 * <li>Un temps de recherche en minute</li>
 * <li>Un cout de construction</li>
 * <li>Un temps de construction en minutes</li>
 * <li>Un liste associative d'effets permettant d'initialiser les batiments</li>
 * <li>Un type parent permettant de gérer les dépendances de recherches</li>
 * <li>Un booléen permettant de signaler si ce type est débloquer par défaut dans le jeu</li>
 * </ul>
 * </p>
 *
 *
 * @author Antoine RICHARD - Antoine68
 */
public enum TypeBatiment {

    /* TEMPERATURE */
    /**
     * Type de batiment : Usine de refroidissement
     */
    USINE_DE_REFROIDISSEMENT("Usine de refroidissement", "description", 10,0., 0,500000., 5, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.TEMPERATURE, -4.0);
    }},null, true),

    /**
     * Type de batiment : Plateforme aérostat
     */
    PLATEFORME_AEROSTAT("Plateforme aérostat", "description", 10,2000000.,20, 1000000.,10, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.TEMPERATURE, -40.0);
        put(TypeDonnee.FINANCES, 3000.0);
    }}, USINE_DE_REFROIDISSEMENT, false),

    /**
     * Type de batiment : Toile solaire
     */
    TOILE_SOLAIRE("Toile solaire", "description", 10,4000000.,40, 2000000.,20,new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.TEMPERATURE, -100.0);
        put(TypeDonnee.FINANCES, -5000.0);
    }}, PLATEFORME_AEROSTAT, false),

    /**
     * Type de batiment : Pole de réchauffement
     */
    POLE_DE_RECHAUFFEMENT("Pole de réchauffement", "description", 10,0., 0,500000., 5,new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.TEMPERATURE, 4.0);
    }}, null, true),

    /**
     * Type de batiment : Puits de forage
     */
    PUITS_DE_FORAGE("Puits de forage", "description", 10,2000000.,20, 1000000.,10, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.TEMPERATURE, 60.0);
        put(TypeDonnee.PRESSION, 10.0);
    }}, POLE_DE_RECHAUFFEMENT, false),

    /**
     * Type de batiment : Miroir orbital
     */
    MIROIR_ORBITAL("Miroir orbital", "description", 10,4000000.,40, 2000000.,20, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.TEMPERATURE, 120.0);
        put(TypeDonnee.EAU, -10.0);
    }}, PUITS_DE_FORAGE, false),


    /* PRESSION */
    /**
     * Type de batiment : Usine de séquestration
     */
    USINE_DE_SEQUESTRATION("Usine de séquestration", "description", 10,1000000., 10,500000., 5, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.PRESSION, -4.);
    }}, null,false),

    /**
     * Type de batiment : Laboratoire de biodivision
     */
    LABORATOIRE_DE_BIODIVISION("Laboratoire de biodivision", "description", 10,2000000.,20, 1000000.,10, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.PRESSION, -40.);
    }}, USINE_DE_SEQUESTRATION,false),

    /**
     * Type de batiment : Transformateur d'hydrogène
     */
    TRANSFORMATEUR_HYDROGENE("Transformateur d'hydrogène", "description", 10,4000000.,40, 2000000.,20, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.PRESSION, -100.);
        put(TypeDonnee.EAU, 20.);
        put(TypeDonnee.TEMPERATURE, 10.);
    }}, LABORATOIRE_DE_BIODIVISION,false),

    /**
     * Type de batiment : Poussière thermique
     */
    POUSSIERE_THERMIQUE("Poussière thermique", "description", 10,2000000.,20, 1000000.,10, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.PRESSION, 4.);
    }}, null,false),

    /**
     * Type de batiment : Ensemble atmogen
     */
    ENSEMBLE_ATMOGEN("Ensemble atmogen", "description", 10,2000000.,20, 1000000.,10, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.PRESSION, 60.);
        put(TypeDonnee.OXYGENE, 10.);
    }}, POUSSIERE_THERMIQUE,false),

    /**
     * Type de batiment : Exploitation de poches
     */
    EXPLOITATION_DE_POCHES("Exploitation de poches", "description", 10,4000000.,40, 2000000.,20, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.PRESSION, 100.);
        put(TypeDonnee.OXYGENE, 20.);
        put(TypeDonnee.FINANCES, 2000.);
    }}, ENSEMBLE_ATMOGEN,false),


    /* OXYGENE */
    /**
     * Type de batiment : Filtre O2
     */
    FILTRE_O2("Filtre O2", "description", 10,1000000., 10,500000., 5, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.OXYGENE, -4.);
    }}, null,false),

    /**
     * Type de batiment : Fixateur de carbone
     */
    FIXATEUR_DE_CARBONE("Fixateur de carbone", "description", 10,2000000.,20, 1000000.,10, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.OXYGENE, -40.);
        put(TypeDonnee.PRESSION, 10.);
    }}, FILTRE_O2,false),

    /**
     * Type de batiment : Générateur hydro
     */
    GENERATEUR_HYDRO("Générateur hydro", "description", 10,4000000.,40, 2000000.,20, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.OXYGENE, -80.);
        put(TypeDonnee.EAU, 20.);
    }}, FIXATEUR_DE_CARBONE,false),

    /**
     * Type de batiment : Usine d'oxygène
     */
    USINE_OXYGENE("Usine d'oxygène", "description", 10,1000000., 10,500000., 5, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.OXYGENE, 4.);
    }}, null,false),

    /**
     * Type de batiment : Cyanocuves
     */
    CYANOCUVES("Cyanocuves", "description", 10,2000000.,20, 1000000.,10, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.OXYGENE, 60.);
        put(TypeDonnee.PRESSION, -10.);
    }}, USINE_OXYGENE,false),

    /**
     * Type de batiment : Ferme de kelps
     */
    FERME_DE_KELPS("Ferme de kelps", "description", 10,2000000.,20, 1000000.,10, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.OXYGENE, 120.);
        put(TypeDonnee.PRESSION, -10.);
    }}, CYANOCUVES,false),


    /* EAU */
    /**
     * Type de batiment : Géociterne
     */
    GEOCITERNE("Géociterne", "description", 10,2000000.,20, 1000000.,10, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.EAU, 4.);
    }}, null,false),

    /**
     * Type de batiment : Usine d'électrolyse
     */
    USINE_ELECTROLYSE("Usine d'électrolyse", "description", 10,2000000.,20, 1000000.,10, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.EAU, -40.);
        put(TypeDonnee.OXYGENE, 10.);
    }}, GEOCITERNE,false),

    /**
     * Type de batiment : Lance-glace
     */
    LANCE_GLACE("Lance-glace", "description", 10,4000000.,40, 2000000.,20, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.EAU, -120.);
        put(TypeDonnee.PRESSION, -10.);
        put(TypeDonnee.FINANCES, 3000.);
    }}, USINE_ELECTROLYSE,false),

    /**
     * Type de batiment : Semeur de nuages
     */
    SEMEUR_DE_NUAGES("Semeur de nuages", "description", 10,1000000., 10,500000., 5, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.EAU, 4.);
    }}, null,false),

    /**
     * Type de batiment : Réseau aquifère
     */
    RESEAU_AQUIFERE("Réseau aquifère", "description", 10,2000000.,20, 1000000.,10, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.EAU, 40.);
        put(TypeDonnee.PRESSION, 10.);
    }}, SEMEUR_DE_NUAGES,false),

    /**
     * Type de batiment : Redirection de comètes
     */
    REDIRECTION_DE_COMETES("Redirection de comètes", "description", 10,4000000.,40, 2000000.,20,new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.EAU, 120.);
        put(TypeDonnee.FINANCES, 8000.);
    }}, RESEAU_AQUIFERE,false),


    /* POPULATION */
    /**
     * Type de batiment : Unité d'habitation
     */
    UNITE_HABITATION("Unité d'habitation", "description", 10,0., 0,500000., 5, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.HABITATION, 66.);
    }}, null,true),

    /**
     * Type de batiment : Complexe d'habitation
     */
    COMPLEXE_HABITATION("Complexe d'habitation", "description", 10,2000000.,20, 1000000.,10, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.HABITATION, 2400.);
        put(TypeDonnee.OXYGENE, 10.);
    }}, UNITE_HABITATION,false),

    /**
     * Type de batiment : Dôme d'habitation
     */
    DOME_HABITATION("Dôme d'habitation", "description", 10,4000000.,40, 2000000.,20,new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.HABITATION, 42000.);
    }}, COMPLEXE_HABITATION,false),

    /**
     * Type de batiment : Crèche pour enfant
     */
    CRECHE("Crèche pour enfant", "description", 10,1000000., 10,500000., 5,new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.POPULATION, 10.);
    }}, null,false),

    /**
     * Type de batiment : Réseau de transport
     */
    RESEAU_TRANSPORT("Réseau de transport", "description", 10,2000000.,20, 1000000.,10, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.POPULATION, 88.);
    }}, CRECHE,false),

    /**
     * Type de batiment : Port spatial
     */
    PORT_SPATIAL("Port spatial", "description", 10,4000000.,40, 2000000.,20, new HashMap<TypeDonnee, Double>(){{
        put(TypeDonnee.POPULATION, 140.);
        put(TypeDonnee.PRESSION, 10.);
    }}, RESEAU_TRANSPORT,false);


    /**
     * Le nom du type de batiment.
     */
    private String nom ;

    /**
     * La description du type de batiment
     */
    private String description;

    /**
     * Le niveau maximum que pourra atteindre un batiment de ce type
     */
    private int niveauMax;

    /**
     * Le cout de recherche du type de batiment
     */
    private double coutRecherche;

    /**
     * Le temps de recherche du type de batiment
     */
    private int tempsRecherche;

    /**
     * Le cout de construction par défaut d'un batiment de ce type
     */
    private double coutConstructionParDefaut;

    /**
     * Le temps de construction par défaut d'un batiment de ce type
     */
    private int tempsConstructionParDefaut;

    /**
     * Les effets sur les données par défaut d'un batiment de ce type
     */
    private HashMap<TypeDonnee, Double> effetsParDefaut;

    /**
     * Le type parent. Permet de gérer les dépendances de recherches
     */
    private TypeBatiment parent;

    /**
     * Indique si le type est débloqué au début de la partie
     */
    private boolean estDebloqueParDefaut;

    /**
     *
     * Constructeur TypeBatiment.
     *
     * @param nom le nom du type de batiment
     * @param description la description
     * @param niveauMax le niveau maximum
     * @param coutRecherche le cout de recherche
     * @param tempsRecherche le temps de recherche
     * @param coutConstructionParDefaut le cout de construction par défaut
     * @param tempsConstructionParDefaut le temps de construction par défaut
     * @param effetsParDefaut les effets par défaut
     * @param parent le type parent
     * @param estDebloqueParDefaut l'état du type au début de la partie
     *
     *
     */
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

    /**
     * Accesseur du nom.
     * @return le nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Accesseur du niveau maximum.
     * @return le niveau maximum
     */
    public int getNiveauMax() {
        return niveauMax;
    }

    /**
     * Accesseur de la description.
     * @return la description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Accesseur du cout de recherche.
     * @return le cout de recherche
     */
    public double getCoutRecherche() {
        return coutRecherche;
    }

    /**
     * Accesseur du cout de construction.
     * @return le cout de construction
     */
    public double getCoutConstructionParDefaut() {
        return coutConstructionParDefaut;
    }

    /**
     * Accesseur des effets sur les données par défaut.
     * @return une copie des effets par défaut, afin que la liste associative de base ne soit pas modifiable
     */
    public HashMap<TypeDonnee, Double> getEffetsParDefaut() {
        return new HashMap<TypeDonnee, Double>(effetsParDefaut);
    }

    /**
     * Accesseur du type parent.
     * @return le type parent
     */
    public TypeBatiment getParent() {
        return parent;
    }

    /**
     * Accesseur du booléen indiquant si le type est débloqué par défaut.
     * @return <code>true</code> si le type de batiment est débloqué par défaut sinon <code>flase</code>
     */
    public boolean estDebloqueParDefaut() {
        return estDebloqueParDefaut;
    }

    /**
     * Accesseur du temps de recherche.
     * @return le temps de recherche
     */
    public int getTempsRecherche() {
        return tempsRecherche;
    }

    /**
     * Accesseur du temps de construction par défaut.
     * @return le temps de construction par défaut
     */
    public int getTempsConstructionParDefaut() {
        return tempsConstructionParDefaut;
    }

    /**
     * Permet l'affichage du nom du type de batiment
     * @return le nom du batiment
     */
    @Override
    public String toString() {
        return this.nom;
    }
}
