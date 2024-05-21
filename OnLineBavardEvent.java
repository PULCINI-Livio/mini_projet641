import java.util.EventObject;

public class OnLineBavardEvent extends EventObject{
    protected Bavard bavard;

    public OnLineBavardEvent(Object source, Bavard unBavard) {
        super(source);
        this.bavard = unBavard;
    }
    

    // Methodes
    public void addOnLineBavardListener() {

    }

    public void removeOnLineBavardListener() {
        
    }
}
