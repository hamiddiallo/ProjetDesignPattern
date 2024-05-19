import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Outils {
    
    static void afficherTemps(Long timer){
		if (timer<1000) {
			System.out.println("Temps de recherche: " + timer + " ms(Millisecondess)");			
		}else{
			long milliseconds = timer;
			long minutes = (milliseconds / 1000) / 60;
			long seconds = (milliseconds / 1000) % 60;
	 
			 
			System.out.println("Temps de recherche: "+minutes+"mn "+seconds+"s ");

		}
	}

    public static String getServeur(String urlConnexion){
        try {
    
            URL url = new URL(urlConnexion);
        
            
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            
            connection.setRequestMethod("GET");

            
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            
            String html = response.toString();
            int actionStartIndex = html.indexOf("action=\"") + 8; 
            int actionEndIndex = html.indexOf("\"", actionStartIndex); 
            String action = html.substring(actionStartIndex, actionEndIndex); 
            String serveur = urlConnexion.toString().substring(0, urlConnexion.lastIndexOf('/'));
            
            connection.disconnect();
            return serveur+'/'+action.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        
    }
}
