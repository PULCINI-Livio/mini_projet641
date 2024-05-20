public class Main {
    public static void main(String[] args)  {
    // Création du batiment
    Batiment polytech = new Batiment();
    
    // Création du concierge
    Concierge axelle = new Concierge("axelle", polytech);
    polytech.setConcierge(axelle);

    // Création des Bavards
    Bavard charlotte = new Bavard("charlotte", polytech);
    polytech.addBavard(charlotte);
    Bavard emma = new Bavard("emma",polytech); 
    polytech.addBavard(emma);
    Bavard louna = new Bavard("louna", polytech);
    polytech.addBavard(louna);
    Bavard cyprien = new Bavard("cyprien", polytech);
    polytech.addBavard(cyprien);
    Bavard livio = new Bavard("livio", polytech, false);
    polytech.addBavard(livio);
    Bavard mathieu = new Bavard("mathieu", polytech, false);
    polytech.addBavard(mathieu);
    //System.out.println(polytech.listBavards);

    charlotte.sendPotin("je suis un sujet", "salut la mif");
    charlotte.sendPotin("oksour", "j'ai besoin d'aide");
    charlotte.sendPotin("aie", "je fais une depression");
    charlotte.sendPotin("rip", "jvais pas finir l'annee");
    // un paramètre dans la generation de la fenetre qui sera le batiment associé
    // donc une fenetre par batiment 
    MyFrame frame = new MyFrame(polytech);
    frame.setVisible(true);





    } 
}

