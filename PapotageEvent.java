import java.util.EventObject;

public class PapotageEvent extends EventObject{
    protected String sujet;
    protected String corps;
    protected Bavard envoyeur;

    public PapotageEvent(Object source, String unSujet, String unCorps, Bavard unEnvoyeur) {
        super(source);
        this.sujet = unSujet;
        this.corps = unCorps;
        this.envoyeur = unEnvoyeur;
    }
    

    // Methodes
    public void addPapotageListener() {

    }

    public void removePapotageListener() {
        
    }
}
