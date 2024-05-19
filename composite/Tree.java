import java.io.File;

public class Tree {
    public static Dossier creerDossier(String chemin,String profondeur){
        File directory = new File(chemin);
        Dossier dossier = null;
        if(directory.isDirectory()){
            dossier = new Dossier(directory.getName(),profondeur);
            File[] contenue = directory.listFiles();
            if (contenue != null) {
                for (File fichier : contenue) {
                    if(fichier.isDirectory()){
                        dossier.ajouter(creerDossier(chemin + "\\" + fichier.getName(),profondeur + "│   "));
                    }else{
                        dossier.ajouter(new Fichier(fichier.getName()));
                    }
                }
            }
        }
        return dossier;        
    }
    public static void main(String[] args){
        if(args.length <= 1){
            String chemin;
            if(args.length == 1){
                chemin = args[0];
            }else{
                chemin = System.getProperty("user.dir");
            }
            Dossier dossier = creerDossier(chemin,"    ");
            if(dossier != null){
                dossier.afficher();
            }else{
                System.out.println("Ce chemin n'est pas un répertoire.");
            }
        }else{
            System.err.println("erreur nombre d'argument invalide !!!");
        }
    }
}
