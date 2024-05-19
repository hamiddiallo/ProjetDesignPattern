class Fichier extends Composant{
    public Fichier(String nom){
        super(nom);
    }

    public void afficher(){
        System.out.println("├── " + this.nom);
    }
}
