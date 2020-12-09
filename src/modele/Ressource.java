package modele;

public enum Ressource {
    CARBONE("C", Rarete.TRES_ORDINAIRE, 1.0),
    FER("Fe", Rarete.ORDINAIRE, 2.0),
    ARGENT("Ag", Rarete.CLASSIQUE, 5.0),
    PALLADIUM("Pd", Rarete.RARE, 10.0),
    RHODIUM("Rh", Rarete.TRES_RARE, 20.0);

    private String symbole;
    private Rarete rarete;
    private double valeur;

    private Ressource(String symbole, Rarete rarete, double valeur) {
        this.symbole = symbole;
        this.rarete = rarete;
        this.valeur = valeur;
    }

}
