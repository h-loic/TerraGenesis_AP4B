package modele;

public enum Ressource {
    CARBONE("C", Rarete.TRES_ORDINAIRE, 1.0,0, null),
    FER("Fe", Rarete.ORDINAIRE, 2.0, 200000.0, CARBONE),
    ARGENT("Ag", Rarete.CLASSIQUE, 5.0,300000.0, FER),
    PALLADIUM("Pd", Rarete.RARE, 10.0, 400000.0, ARGENT),
    RHODIUM("Rh", Rarete.TRES_RARE, 20.0, 500000.0, PALLADIUM);

    private String symbole;
    private Rarete rarete;
    private double valeur;
    private double coutRecherche;
    private Ressource parent;

    private Ressource(String symbole, Rarete rarete, double valeur, double coutRecherche, Ressource parent) {
        this.symbole = symbole;
        this.rarete = rarete;
        this.valeur = valeur;
        this.coutRecherche = coutRecherche;
        this.parent = parent;
    }

    public String getSymbole() {
        return symbole;
    }

    public Rarete getRarete() {
        return rarete;
    }

    public double getValeur() {
        return valeur;
    }

    public double getCoutRecherche() {
        return coutRecherche;
    }

    public Ressource getParent() {
        return parent;
    }
}
