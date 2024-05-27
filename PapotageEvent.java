import java.time.LocalTime;
import java.util.EventObject;

public class PapotageEvent extends EventObject{
    protected String sujet;
    protected String corps;
    protected Bavard envoyeur;
    protected LocalTime currentTime;
    protected String theme;

    public PapotageEvent(Object source, String unSujet, String unCorps, Bavard unEnvoyeur, String unTheme) {
        super(source);
        this.sujet = unSujet;
        this.corps = unCorps;
        this.envoyeur = unEnvoyeur;
        this.currentTime = LocalTime.now();
        this.theme = unTheme;
    }
    

    // Methodes
    public void addPapotageListener() {

    }

    public void removePapotageListener() {
        
    }


    public String getSujet() {
        return sujet;
    }


    public String getCorps() {
        return corps;
    }


    public Bavard getEnvoyeur() {
        return envoyeur;
    }


    public LocalTime getCurrentTime() {
        return currentTime;
    }


    public String getTheme() {
        return theme;
    }

    
}
