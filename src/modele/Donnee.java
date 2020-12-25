package modele;

public class Donnee {
    private TypeDonnee typeDonnee;
    private double valeurActuelle;
    private double croissance;

    public Donnee(TypeDonnee typeDonnee) {
        this.typeDonnee = typeDonnee;
        this.valeurActuelle = 0.;
        this.croissance = 0.;
    }

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

    public void setValeurActuelle(double valeur){
        this.valeurActuelle = valeur;
    }

    public double getCroissance() {
        return croissance;
    }

    public TypeDonnee getTypeDonnee() {
        return typeDonnee;
    }
}
