import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


class DictionnaryLocalCracker extends Dictionnary{
	public void crack(String password){
		String nomFichier = "rockyou.txt";
        boolean find = false; 
        

        try {
            BufferedReader lecteur = new BufferedReader(new FileReader(nomFichier));
            String ligne;
			long start=System.currentTimeMillis();
            while ((ligne = lecteur.readLine()) != null) {
                if (password.equals(Hash.md5(ligne))) {
					lecteur.close();
                   	System.out.println("Le mot de passe est "+ligne);
                    Outils.afficherTemps(System.currentTimeMillis() - start);
                    find = true;
                    break;
                }
                
            }
            lecteur.close();
			
        } catch (IOException e) {
            System.err.println("Erreur de lecture du fichier : " + e.getMessage());
			
        }

        if (!find) {
            System.out.println("Aucun mot de Passe trouve Pour ce Site");
        }
		

	}

}