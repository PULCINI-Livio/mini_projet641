import java.util.EventObject;

public class OffLineBavardEvent extends EventObject{
    protected Bavard bavard;

    public OffLineBavardEvent(Object source, Bavard unBavard) {
        super(source);
        this.bavard = unBavard;
    }
    

    // Methodes
    public void addOffLineBavardListener() {

    }

    public void removeOffLineBavardListener() {
        
    }
}

