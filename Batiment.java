import java.util.*;

public class Batiment {
    protected List<Bavard> listBavards;
    protected Concierge concierge;


    public Batiment() {
        this.listBavards = null;
        this.concierge = null;
    }

    public void setConcierge(Concierge unConcierge) {
        concierge = unConcierge;
    }

    public Concierge getConcierge() {
        return concierge;
    }

    public void addBavard(Bavard unBavard) {
        listBavards.add(unBavard);
    }

    public void removeBavard(Bavard unBavard) {
        listBavards.remove(unBavard);
    }
}
