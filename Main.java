public class Main {
    public static void main(String[] args)  {
    // Création du batiment
    Batiment polytech = new Batiment("polytech");
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
    Bavard mathieu = new Bavard("mathieu", polytech, false, false);
    polytech.addBavard(mathieu);

    charlotte.bloquer(mathieu);
    //System.out.println(polytech.listBavards);

    //charlotte.sendPotin("je suis un sujet", "salut la mif");
    //charlotte.sendPotin("oksour", "j'ai besoin d'aide");
    //charlotte.sendPotin("aie", "je fais une depression");
    //charlotte.sendPotin("rip", "jvais pas finir l'annee");
    // un paramètre dans la generation de la fenetre qui sera le batiment associé
    // donc une fenetre par batiment 
    MyFrame frame = new MyFrame(polytech);
    frame.setVisible(true);

    //livio.afficheListePapotages();

// Création du batiment
    Batiment iut = new Batiment("iut");
    // Création du concierge
    Concierge paul = new Concierge("paul", iut);
    iut.setConcierge(paul);

    // Création des Bavards
    Bavard charlott = new Bavard("charlott", iut);
    iut.addBavard(charlott);
    Bavard emm = new Bavard("emm",iut); 
    iut.addBavard(emm);
    Bavard loun = new Bavard("loun", iut);
    iut.addBavard(loun);
    Bavard cyprie = new Bavard("cyprie", iut);
    iut.addBavard(cyprie);
    Bavard livi = new Bavard("livi", iut, false);
    iut.addBavard(livi);
    Bavard mathie = new Bavard("mathie", iut, false, false);
    iut.addBavard(mathie);
    //System.out.println(iut.listBavards);

    //charlott.sendPotin("je sus un sujet", "ut la mif");
    //charlott.sendPotin("oksur", "j'ai besoi'aide");
    //charlott.sendPotin("ae", "je fs une ");
    //charlott.sendPotin("rp", "jvais pasinir annee");
    // un paramètre dans la generation de la fenetre qui sera le batiment associé
    // donc une fenetre par batiment 
    //MyFrame autreFrame = new MyFrame(iut);
    //autreFrame.setVisible(true);

    } 
}

