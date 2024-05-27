import java.util.ArrayList;
import java.util.List;

public class Bavard implements PapotageListener, OnLineBavardListener, OffLineBavardListener {
// Attributs
    protected String nom;
    protected Batiment batiment;
    protected boolean interet;
    protected boolean connecte; 
    protected List<PapotageEvent> listPapotages; 
    protected List<OnLineBavardEvent> listOnLine;
    protected List<OffLineBavardEvent> listOffLine;
    protected List<Bavard> listBavardBlocked;
    protected List<String> listThemeBlocked;

// Constructeurs
    public Bavard(String nom, Batiment unBatiment) {
        this.nom = nom;
        this.batiment = unBatiment;
        this.interet = true;
        this.connecte = false; 
        this.listPapotages = new ArrayList<>();
        this.listOnLine = new ArrayList<>();
        this.listOffLine = new ArrayList<>();
        this.listBavardBlocked = new ArrayList<>();
        this.listThemeBlocked = new ArrayList<>();
    }

    public Bavard(String nom, Batiment unBatiment, boolean unInteret) {
        this.nom = nom;
        this.batiment = unBatiment;
        this.interet = unInteret;
        this.connecte = false; 
        this.listPapotages = new ArrayList<>();
        this.listOnLine = new ArrayList<>();
        this.listOffLine = new ArrayList<>();
        this.listBavardBlocked = new ArrayList<>();
        this.listThemeBlocked = new ArrayList<>();
    }

    public Bavard(String nom, Batiment unBatiment, boolean unInteret, boolean estConnecte) {
        this.nom = nom;
        this.batiment = unBatiment;
        this.interet = unInteret;
        this.connecte = estConnecte; 
        this.listPapotages = new ArrayList<>();
        this.listOnLine = new ArrayList<>();
        this.listOffLine = new ArrayList<>();
        this.listBavardBlocked = new ArrayList<>();
        this.listThemeBlocked = new ArrayList<>();
        }

// Méthodes pour générer des Events
    public void sendPotin(String unSujet, String unCorps, String unTheme) {
        PapotageEvent potin = new PapotageEvent(this, unSujet, unCorps, this, unTheme);
        batiment.concierge.papotageEventReceived(potin);
    }

    public void signalConnexion() {
        OnLineBavardEvent event = new OnLineBavardEvent(this, this);
        System.out.println("Connexion de "+event.envoyeur.getNom()+" à "+event.getCurrentTime() );
        for (Bavard unePersonne : batiment.listBavards) {
            if (unePersonne.getNom() != this.getNom() && unePersonne.isConnecte()) {
                unePersonne.OnLineBavardEventReceived(event);
            }
        }
    }

    public void signalDeconnexion() {
        OffLineBavardEvent event = new OffLineBavardEvent(this, this);
        System.out.println("Déconnexion de "+event.envoyeur.getNom()+" à "+event.getCurrentTime() );
        for (Bavard unePersonne : batiment.listBavards) {
            if (unePersonne.getNom() != this.getNom() && unePersonne.isConnecte()) {
                unePersonne.OffLineBavardEventReceived(event);
            }
        }
    }

// Methode quand on recois des Event
    @Override
    public void papotageEventReceived(PapotageEvent event) {
        System.out.println(nom + " a reçu un message : " + event.corps);
        if (interet && !this.isBavardBlocked(event.envoyeur.getNom()) && !this.isThemeBlocked(event.getTheme())) {
            listPapotages.add(event);
        }
    }

    @Override
    public void OnLineBavardEventReceived(OnLineBavardEvent event) {
        listOnLine.add(event);
        System.out.println(this.getNom()+" a vu la connexion de "+ event.envoyeur.getNom());
        
    }

    @Override
    public void OffLineBavardEventReceived(OffLineBavardEvent event) {
        listOffLine.add(event);
        System.out.println(this.getNom()+" a vu la déconnexion de "+ event.envoyeur.getNom());

    }
// Getters and setters
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

// Methodes pour gerer le blocage des bavards
    public boolean isBavardBlocked(String nomBavard) {
        boolean res = false;
        for (Bavard unBavardBlocked : listBavardBlocked) {
            if (unBavardBlocked.getNom().equals(nomBavard)) {
                res = true;
            }
        }
        return res;
    }

    public void bloquerBavard(String nomBavard) {
        for (Bavard unBavard : batiment.listBavards) {
            if (unBavard.getNom().equals(nomBavard)) {
                listBavardBlocked.add(unBavard);
            }
        }
    }

    public void bloquerBavard(Bavard unBavard) {
        listBavardBlocked.add(unBavard);
    }

    public void debloquerBavard(Bavard unBavard) {
        listBavardBlocked.remove(unBavard);
    }

    public void debloquerBavard(String nomBavard) {
        for (Bavard unBavard : batiment.listBavards) {
            if (unBavard.getNom().equals(nomBavard)) {
                listBavardBlocked.remove(unBavard);
            }
        }
    }
// Methodes pour gerer le blocage des themes
    public boolean isThemeBlocked(String nomTheme) {
        boolean res = false;
        for (String unTheme : listThemeBlocked) {
            if (unTheme.equals(nomTheme)) {
                res = true;
            }
        }
        return res;
    }

    public void bloquerTheme(String nomTheme) {
        listThemeBlocked.add(nomTheme);
    }

    public void debloquerTheme(String nomTheme) {
        listThemeBlocked.remove(nomTheme);
    }
}
