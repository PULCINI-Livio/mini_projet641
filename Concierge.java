

public class Concierge implements PapotageListener{
    protected String nom;
    protected Batiment batiment;

    
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

    
    public void transferPotin(PapotageEvent potin) {
        for (Bavard bavard : batiment.listBavards) {
            if (bavard.getInteret() == true) {
                bavard.papotageEventReceived(potin);
            }
            
        }
    }

    @Override
    public void papotageEventReceived(PapotageEvent potin) {
        transferPotin(potin);
    }

    

}
