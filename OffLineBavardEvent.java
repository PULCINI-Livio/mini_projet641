import java.time.LocalTime;
import java.util.EventObject;

public class OffLineBavardEvent extends EventObject{
    protected LocalTime currentTime;
    protected Bavard envoyeur;

    public OffLineBavardEvent(Object source, Bavard unEnvoyeur) {
        super(source);
        this.currentTime = LocalTime.now();
        this.envoyeur = unEnvoyeur;
    }

    public LocalTime getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(LocalTime currentTime) {
        this.currentTime = currentTime;
    }

    public Bavard getEnvoyeur() {
        return envoyeur;
    }

    public void setEnvoyeur(Bavard envoyeur) {
        this.envoyeur = envoyeur;
    }

}