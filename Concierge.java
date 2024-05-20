import java.util.ArrayList;
import java.util.List;

public class Concierge implements PapotageListener{
    protected String nom;
    protected Batiment batiment;
    protected List<PapotageEvent> listPapotages; 
    
    public String getNom() {
        return nom;
    }

    public Concierge(String nom, Batiment batiment) {
        this.nom = nom;
        this.batiment = batiment;
        this.listPapotages = new ArrayList<>();
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
        listPapotages.add(potin);
        transferPotin(potin);
    }

    

}
