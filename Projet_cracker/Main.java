import java.util.Scanner;
public class Main {
    public static void nettoyerConsole() {
        try {
            String systemeExploitation = System.getProperty("os.name").toLowerCase();
            if (systemeExploitation.contains("windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        int choix,nombreEssai;
        PasswordCracker pc = null;
        BruteForce bruteForce;
        Dictionnary dictionnary;
        String question,chaine,status,reponse;
        question = chaine = status = reponse = "";
        Scanner sc =new Scanner(System.in);
        do{
            nombreEssai = 0;
            do {
                nombreEssai++;
                System.out.println("*---------------------------------------*");
                System.out.println("|    choisir le type de cracker         |");
                System.out.println("*---------------------------------------*");
                System.out.println("|1- local cracker                       |"); 
                System.out.println("|2- online cracker                      |");
                System.out.println("*---------------------------------------*");
                System.out.println("faites votre choix :");
                choix = sc.nextInt();
                nettoyerConsole();
                if(choix != 1 && choix != 2){
                    if(nombreEssai == 3){
                        System.err.println("trop de tentative incorrect");
                        System.exit(0);
                    }
                    System.err.println("vous devez imperativement choisir parmi les options");
                }
            } while (choix != 1 && choix != 2);
    
            switch(choix){
                case 1:
                pc = new LocalFactory();
                question = "saisir un mot de passe: ";
                status = "local";
                break;
                case 2:
                pc = new OnlineFactory();
                question = "saisir une url: ";
                status = "ligne";
            }
    
            do{
                nombreEssai = 0;
                do {
                    nombreEssai++;     
                    System.out.println("*---------------------------------------*");
                    System.out.println("|    choisir le cracker                 |");
                    System.out.println("*---------------------------------------*");
                    System.out.println("|1- brute force                         |"); 
                    System.out.println("|2- dictionnaire                        |");
                    System.out.println("*---------------------------------------*");
                    System.out.println("faites votre choix :");
                    choix = sc.nextInt();
                    nettoyerConsole();
                    if(choix != 1 && choix != 2){
                        if(nombreEssai == 3){
                            System.err.println("trop de tentative incorrect");
                            System.exit(0);
                        }
                        System.err.println("vous devez imperativement choisir parmi les options");
                    }
                } while (choix != 1 && choix != 2);
                
                switch(choix){
                    case 1:
                    bruteForce = pc.getBruteForce();
                    System.out.println(question);
                    chaine = sc.next();
                    sc.nextLine();
                    bruteForce.crack(chaine);
                    break;
                    case 2:
                    dictionnary = pc.getDictionnary();
                    System.out.println(question);
                    chaine = sc.next();
                    sc.nextLine();
                    dictionnary.crack(chaine);
                }
                nombreEssai = 0;
                do{
                    nombreEssai++;
                    System.out.println("voulez-vous faire un autre cracke en " + status + "(O/n)");
                    reponse = sc.next();
                    sc.nextLine();
                    nettoyerConsole();
                    if(!reponse.equalsIgnoreCase("O") && !reponse.equalsIgnoreCase("n")){
                        if(nombreEssai == 3){
                            System.err.println("trop de tentative incorrect");
                            System.exit(0);
                        }
                        System.err.println("vous devez impérativement saisir O ou n");
                    }
                }while(!reponse.equalsIgnoreCase("O") && !reponse.equalsIgnoreCase("n"));
            }while(reponse.equalsIgnoreCase("O"));
            nombreEssai = 0;
            do{
                nombreEssai++;
                System.out.println("voulez-vous cracker un mot de passe (O/n)");
                reponse = sc.next();
                sc.nextLine();
                nettoyerConsole();
                if(!reponse.equalsIgnoreCase("O") && !reponse.equalsIgnoreCase("n")){
                    if(nombreEssai == 3){
                        System.err.println("trop de tentative incorrect");
                        System.exit(0);
                    }
                    System.err.println("vous devez impérativement saisir O ou n");
                }
            }while(!reponse.equalsIgnoreCase("O") && !reponse.equalsIgnoreCase("n"));
        }while(reponse.equalsIgnoreCase("O"));
        
        
    }
}
