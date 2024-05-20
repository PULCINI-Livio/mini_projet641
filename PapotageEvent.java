import java.util.EventObject;

public class PapotageEvent extends EventObject{
    protected String sujet;
    protected String corps;

    public PapotageEvent(Object source, String unSujet, String unCorps) {
        super(source);
        this.sujet = unSujet;
        this.corps = unCorps;
    }
    

    // Methodes
    public void addPapotageListener() {

    }

    public void removePapotageListener() {
        
    }
}
