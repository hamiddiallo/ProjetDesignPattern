public class BruteForceLocalCracker extends BruteForce{
    private static long index = 0;
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

    public void crack(String mdp) {
        int k = 1;
        
        while (k <= nombreDeCaractereMax) {
            index = 0; 
            String nextWord = genererMotDePasse(k);
            while (nextWord != null) {
                System.out.println("essai avec :" + nextWord);
                if (Hash.md5(nextWord).equals(mdp)) {
                    System.out.println("le mot de passe est "+nextWord);
                    return;
                }
                nextWord = genererMotDePasse(k);
            }
            k++;
        }
        System.out.println("Mot de passe non trouv�");
    }
}