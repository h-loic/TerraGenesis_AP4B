package modele;

public enum TypeDonne {
    TEMPERATURE("blabla", "°C"),
    PRESSION("blabla", "Pa"),
    OXYGENE("blabla", "ppm"),
    EAU("blabla", "cm"),
    POPULATION("blabla", ""),
    FINANCES("Fonds disponibles", "c");

    private String description;
    private String unite;

    private TypeDonne(String description, String unite) {
        this.description = description;
        this.unite = unite;
    }
}
