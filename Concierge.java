

public class Concierge implements PapotageListener{
    protected String nom;
    protected Batiment batiment;
    //protected List<Bavard> listBavards;

    
    public String getNom() {
        return nom;
    }

    public Concierge(String nom, Batiment batiment) {
        this.nom = nom;
        this.batiment = batiment;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public void sendPotin(String unSujet, String unCorps) {
        PapotageEvent potin = new PapotageEvent(this, unSujet, unCorps);
        for (PapotageListener listener : batiment.listBavards) {
            listener.papotageEventReceived(potin);
        }
    }
    

    @Override
    public void papotageEventReceived(PapotageEvent event) {
        throw new UnsupportedOperationException("Unimplemented method 'papotageEventReceived'");
    }

    

}
