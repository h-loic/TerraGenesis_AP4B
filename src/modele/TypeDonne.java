package modele;

public enum TypeDonne {
    TEMPERATURE("Temperature", "°C"),
    PRESSION("Pression", "Pa"),
    OXYGENE("Oxygene", "ppm"),
    EAU("Eau", "cm"),
    POPULATION("Population", ""),
    FINANCES("Fonds disponibles", "c");

    private String description;
    private String unite;

    private TypeDonne(String description, String unite) {
        this.description = description;
        this.unite = unite;
    }
}
