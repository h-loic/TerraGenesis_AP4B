package modele;

public enum TypeInfrastructure {
   USINE_DE_REFROIDISSEMENT("usine de refroidissement", "description", 100000.0);

   private String nom;
   private String description;
   private double cout;

   private TypeInfrastructure(String nom, String description, double cout){
       this.nom = nom;
       this.description = description;
       this.cout = cout;
   }
}
