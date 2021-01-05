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

    synchronized public void setCroissance(double croissance) {
        this.croissance = croissance;
    }

    synchronized public void majValeur(){
        this.valeurActuelle+=croissance;
    }

    synchronized public double getValeurActuelle(){
        return valeurActuelle;
    }

    synchronized public void setValeurActuelle(double valeur){
        this.valeurActuelle = valeur;
    }

    synchronized public double getCroissance() {
        return croissance;
    }

    synchronized public TypeDonnee getTypeDonnee() {
        return typeDonnee;
    }
}
