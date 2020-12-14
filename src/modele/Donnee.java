package modele;

public class Donnee {
    private TypeDonnee typeDonnee;
    private double valeurActuelle;
    private double croissance;

    public Donnee(TypeDonnee typeDonnee, double valeurActuelle, double croissance) {
        this.typeDonnee = typeDonnee;
        this.valeurActuelle = valeurActuelle;
        this.croissance = croissance;
    }

    public void setCroissance(double croissance) {
        this.croissance = croissance;
    }

    public void majValeur(){
        this.valeurActuelle+=croissance;
    }

    public double getValeurActuelle(){
        return valeurActuelle;
    }

    public double getCroissance() {
        return croissance;
    }

    public TypeDonnee getTypeDonnee() {
        return typeDonnee;
    }
}
