public abstract class Composant{
    protected String nom;
    public Composant(String nom){
        this.nom = nom;
    }
    public  abstract void afficher();   
    public void ajouter(Composant c){}
}


