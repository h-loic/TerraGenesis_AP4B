package modele;

public enum Rarete {
    TRES_ORDINAIRE(1.0),
    ORDINAIRE(2.0),
    CLASSIQUE(5.0),
    RARE(10.0),
    TRES_RARE(20.0);

    private double valeur;

    private Rarete(double valeur) {
        this.valeur = valeur;
    }
}
