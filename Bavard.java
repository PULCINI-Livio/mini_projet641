public class Bavard implements PapotageListener{
    protected String nom;
    protected Batiment batiment;
    protected boolean interet;
    protected boolean connecte;

    // Constructeurs
    public Bavard(String nom, Batiment unBatiment) {
        this.nom = nom;
        this.batiment = unBatiment;
        this.interet = true;
    }

    public Bavard(String nom, Batiment unBatiment, boolean unInteret) {
        this.nom = nom;
        this.batiment = unBatiment;
        this.interet = unInteret;
        this.connecte = true;
    }

    public Bavard(String nom, Batiment unBatiment, boolean unInteret, boolean connecte) {
        this.nom = nom;
        this.batiment = unBatiment;
        this.interet = unInteret;
        this.connecte = connecte;
    }

    public void sendPotin(String unSujet, String unCorps) {
        PapotageEvent potin = new PapotageEvent(this, unSujet, unCorps);
        batiment.concierge.papotageEventReceived(potin);
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

    @Override
    public void papotageEventReceived(PapotageEvent event) {
        System.out.println(nom + " a reçu un message : " + event.corps);
    }

    
}
