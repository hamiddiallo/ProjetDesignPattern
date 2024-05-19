import java.util.ArrayList;

public class Dossier extends Composant{
    private ArrayList <Composant> composants;
    private String profondeur;
    public static final String BLUE = "\033[0;34m";
    public static final String RESET = "\033[0m";
    public Dossier(String nom,String profondeur){
        super(nom);
        this.profondeur = profondeur;
        composants = new ArrayList<Composant>();
    }

    public void afficher(){
        System.out.println("├── "+ BLUE + this.nom +"/" + RESET);
        for(Composant composant:composants){
            System.out.print(this.profondeur);
            composant.afficher();    
        }
    }

    public void ajouter(Composant c){
        this.composants.add(c);
    }
}