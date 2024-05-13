import java.util.*;

public class Batiment {
    protected List<Bavard> listBavards;
    protected Concierge concierge;

    // Constructeur
    public Batiment() {
        this.listBavards = new ArrayList<>();
        this.concierge = null;
    }

    // Methodes
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
