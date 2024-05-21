import java.util.*;

public class Batiment {
    protected List<Bavard> listBavards;
    protected Concierge concierge;
    protected String nom;
    // Constructeur
    public Batiment(String unNom) {
        this.listBavards = new ArrayList<>();
        this.concierge = null;
        this.nom = unNom;
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
