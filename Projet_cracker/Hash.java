import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {
    static  String md5(String input){
        
        StringBuilder hexString = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(input.getBytes());

            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Algorithme de hachage non trouvé: " + e.getMessage());
        }
        return hexString.toString();
    }
}
