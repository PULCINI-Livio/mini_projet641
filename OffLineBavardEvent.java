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

}