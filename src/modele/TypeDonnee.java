package modele;

public enum TypeDonnee {
    TEMPERATURE("blabla", "°C",0),
    PRESSION("blabla", "Pa",0),
    OXYGENE("blabla", "ppm",0),
    EAU("blabla", "cm",0),
    POPULATION("blabla", "",0),
    HABITATION("blabla", "",0),
    FINANCES("Fonds disponibles", "c", 10000000);

    private String description;
    private String unite;
    private double valeurDefaut;

    private TypeDonnee(String description, String unite, double valeurDefaut) {
        this.description = description;
        this.unite = unite;
        this.valeurDefaut = valeurDefaut;

    }

    public String getDescription() {
        return description;
    }

    public String getUnite() {
        return unite;
    }

    public double getValeurDefaut() {
        return valeurDefaut;
    }
}
