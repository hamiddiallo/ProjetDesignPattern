public abstract class PasswordCracker{
    public abstract BruteForce getBruteForce();
    public abstract Dictionnary getDictionnary();
}

class LocalFactory extends PasswordCracker{
    public BruteForce getBruteForce(){
        return new BruteForceLocalCracker();
    }
    public Dictionnary getDictionnary(){
        return new DictionnaryLocalCracker();
    }

}

class OnlineFactory extends PasswordCracker{
    public BruteForce getBruteForce(){
        return new OnlineBruteForce();
    }
    public Dictionnary getDictionnary(){
        return new DictionnaryOnlineCracker();
    }

}