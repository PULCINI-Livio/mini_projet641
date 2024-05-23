import java.util.ArrayList;
import java.util.List;

public class Bavard implements PapotageListener, OnLineBavardListener, OffLineBavardListener{
    protected String nom;
    protected Batiment batiment;
    protected boolean interet;
    protected boolean connecte; 
    protected List<PapotageEvent> listPapotages; 
    protected List<OnLineBavardEvent> listOnLine;
    protected List<OffLineBavardEvent> listOffLine;

    // Constructeurs
    public Bavard(String nom, Batiment unBatiment) {
        this.nom = nom;
        this.batiment = unBatiment;
        this.interet = true;
        this.connecte = false; 
        this.listPapotages = new ArrayList<>();
        this.listOnLine = new ArrayList<>();
        this.listOffLine = new ArrayList<>();
    }

    public Bavard(String nom, Batiment unBatiment, boolean unInteret) {
        this.nom = nom;
        this.batiment = unBatiment;
        this.interet = unInteret;
        this.connecte = false; 
        this.listPapotages = new ArrayList<>();
        this.listOnLine = new ArrayList<>();
        this.listOffLine = new ArrayList<>();
    }

    public Bavard(String nom, Batiment unBatiment, boolean unInteret, boolean estConnecte) {
        this.nom = nom;
        this.batiment = unBatiment;
        this.interet = unInteret;
        this.connecte = estConnecte; 
        this.listPapotages = new ArrayList<>();
        this.listOnLine = new ArrayList<>();
        this.listOffLine = new ArrayList<>();
        }

    public void sendPotin(String unSujet, String unCorps) {
        PapotageEvent potin = new PapotageEvent(this, unSujet, unCorps, this);
        batiment.concierge.papotageEventReceived(potin);
    }

    public void signalConnexion(Bavard unBavard) {
        OnLineBavardEvent event = new OnLineBavardEvent(this, unBavard);
        for (Bavard unePersonne : batiment.listBavards) {
            unePersonne.OnLineBavardEventReceived(event);
        }
    }

    public void signalDeconnexion(Bavard unBavard) {
        OffLineBavardEvent event = new OffLineBavardEvent(this, unBavard);
        for (Bavard unePersonne : batiment.listBavards) {
            unePersonne.OffLineBavardEventReceived(event);
        }
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    
    public boolean getInteret() {
        return interet;
    }

    public void setInteret(boolean interet) {
        this.interet = interet;
    }

    public boolean isConnecte() {
        return connecte;
    }

    public void setConnecte(boolean connecte) {
        this.connecte = connecte;
    }

    public void afficheListePapotages() {
        for (PapotageEvent event : listPapotages) {
            System.out.println(event.sujet);
        }
        System.out.println("voila tous les papotages");
    }
    @Override
    public void papotageEventReceived(PapotageEvent event) {
        System.out.println(nom + " a reçu un message : " + event.corps);
        if (interet) {
            listPapotages.add(event);
        }
    }

    @Override
    public void OnLineBavardEventReceived(OnLineBavardEvent event) {
        if (connecte) {
            listOnLine.add(event);
        }
    }

    @Override
    public void OffLineBavardEventReceived(OffLineBavardEvent event) {
        if (connecte) {
            listOffLine.add(event);
        }
    }
    
}
