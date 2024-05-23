import java.time.LocalTime;
import java.util.EventObject;

public class OnLineBavardEvent extends EventObject{
    protected LocalTime currentTime;
    protected Bavard envoyeur;

    public OnLineBavardEvent(Object source, Bavard unEnvoyeur) {
        super(source);
        this.currentTime = LocalTime.now();
        this.envoyeur = unEnvoyeur;
    }

}
