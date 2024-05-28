import java.util.*;

public class Batiment {
    protected List<Bavard> listBavards;
    protected Concierge concierge;
    protected String nom;
    protected List<String> listThemes;

    // Constructeur
    public Batiment(String unNom) {
        this.listBavards = new ArrayList<>();
        this.concierge = null;
        this.nom = unNom;
        this.listThemes = new ArrayList<>();

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

    public void addTheme(String unTheme) {
        listThemes.add(unTheme);
    }

    public void removeTheme(String unTheme) {
        listThemes.remove(unTheme);
    }
}
