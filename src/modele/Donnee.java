package modele;

public class Donnee {
    private TypeDonne typeDonne;
    private double valeurActuelle;
    private double croissance;

    public Donnee(TypeDonne typeDonne, double valeurActuelle, double croissance) {
        this.typeDonne = typeDonne;
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

    public String getTypeDonne() {
        return typeDonne.name();
    }
}
