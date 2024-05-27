import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class MyFrame extends JFrame {
    protected Batiment baraque;
    protected JComboBox<String> connexionListeBavardsComboBox;
    protected JList<String> bavardSubjectList;
    protected JList<String> conciergeSubjectList;
    protected JComboBox<String> listeBavardsConnecteComboBox;
    protected JList<String> logConnexionList;
    protected JList<String> logDeconnexionList;
    protected JComboBox<String> bavardListeBavardsComboBox;
    protected JToggleButton blockSwitchButton;

    protected JToggleButton themeSwitchButton;
    protected JComboBox<String> bavardListeThemeEnvoieComboBox;
    protected JComboBox<String> bavardListeThemesABloquerComboBox;

    // Constructeur
    public MyFrame(Batiment uneBaraque) {
        this.baraque = uneBaraque;
        initComponents();
    }

    private void initComponents() {
        JTabbedPane menuTabbedPane = new JTabbedPane();
    
        // Créer des panneaux pour chaque onglet
        JPanel creation = new JPanel();
        JPanel connexion = new JPanel();
        JPanel bavard = new JPanel();
        JPanel concierge = new JPanel();
        

    //----------------------------------------------------------------------------------//
    //                                                                                   //
    //                                                                                  //
    //                                                                                   //
    //                                INITIALISATION                                    //
    //                                                                                   //
    //                                                                                  //
    //                                                                                   //
    //----------------------------------------------------------------------------------//

    // Init des listes dynamiques
        conciergeSubjectList = new JList<>();
        connexionListeBavardsComboBox = new JComboBox<>();
        bavardSubjectList = new JList<>();
        logConnexionList = new JList<>();
        logDeconnexionList = new JList<>();
        bavardListeBavardsComboBox = new JComboBox<>();
        bavardListeThemeEnvoieComboBox = new JComboBox<>();
        bavardListeThemesABloquerComboBox = new JComboBox<>();

    // Liste des bavards connectés dans onglet bavard
        ArrayList<String> listeBavardsConnecte = new ArrayList<>();
        for (Bavard unBavard : baraque.listBavards) {
            if (unBavard.connecte == true) {
                listeBavardsConnecte.add(unBavard.getNom());
            }
        }

    // Liste avec tous les bavards de l'onglet connexion
        ArrayList<String> bavardsListe = new ArrayList<>();
        for (Bavard personne : baraque.listBavards) {
            bavardsListe.add(personne.getNom());
        }

    // Liste des messages que le concierge reçoit dans l'onglet concierge
        ArrayList<String> conciergeSubjects = new ArrayList<>();
        for (PapotageEvent potin : baraque.concierge.listPapotages) {
            conciergeSubjects.add(potin.sujet);
        }

    // Liste avec tous les themes
        ArrayList<String> themesListe = new ArrayList<>();
        for (String unTheme : baraque.listThemes) {
            themesListe.add(unTheme);
        }

    // Charger les icônes pour les états enfoncé et relâché du bouton switch
        ImageIcon onIcon = new ImageIcon("switchBlocked.png");
        ImageIcon offIcon = new ImageIcon("switchUnblocked.png");

    // Créer le bouton switch
        JToggleButton blockSwitchButton = new JToggleButton(offIcon);
        JToggleButton themeSwitchButton = new JToggleButton(offIcon);
//----------------------------------------------------------------------------------//
//                                                                                 //
//                                                                                  //
//                                                                                 //
//                                 ONGLET CREATION                                  //
//                                                                                 //
//                                                                                  //
//                                                                                 //
//----------------------------------------------------------------------------------//
// Ajout composants pour onglet creation
        creation.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
// Partie creation bavard
    // Placement JLabel("Nom du bavard")
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.EAST;
        creation.add(new JLabel("Nom du bavard : "), gbc);

    // Placement JTextField pour entrer le nom du bavard à créer
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        JTextField creationNomBavard = new JTextField(10);
        creation.add(creationNomBavard, gbc);

    // Placement JLabel("Interet")
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        creation.add(new JLabel("Interet :"), gbc);

    //  Création et ajout des deux JRadioButton OUI/NON dans un groupe
        JRadioButton creationOuiButton = new JRadioButton("Oui");
        creationOuiButton.setSelected(true); // Sélectionner le JRadioButton "Oui" par défaut

        JRadioButton creationNonButton = new JRadioButton("Non");
        ButtonGroup creationRadioBtnGroup = new ButtonGroup();
        creationRadioBtnGroup.add(creationOuiButton);
        creationRadioBtnGroup.add(creationNonButton);
        // Créer un JPanel séparé pour les JRadioButtons
        JPanel creationRadioPanel = new JPanel();
        creationRadioPanel.setLayout(new BoxLayout(creationRadioPanel, BoxLayout.X_AXIS));
        creationRadioPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        creationOuiButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        creationNonButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        creationRadioPanel.add(creationOuiButton);
        creationRadioPanel.add(creationNonButton);

        // Placement du JPanel des JRadioButtons dans la frame
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        gbc.anchor = GridBagConstraints.WEST;
        creation.add(creationRadioPanel, gbc);

        // Placement du bouton de création du bavard
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        gbc.anchor = GridBagConstraints.WEST;
        JButton creationBavardBtn = new JButton("Créer bavard");
        creation.add(creationBavardBtn, gbc);



    // Ajouter un écouteur d'événements au bouton de création des bavards
        creationBavardBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent arg0) {
                // Récup nom du bavard du JTextField
                String nomBavard = creationNomBavard.getText(); 
                // On verifie que le nom du bavard n'est pas deja pris
                boolean existeDeja = false;
                for (Bavard unBavard : baraque.listBavards) {
                    if (nomBavard.equals(unBavard.getNom()))
                    existeDeja = true;
                }
                // On vérifie que le JTextField contenant le nom du bavard n'est pas vide ou déjà existant
                if (!creationNomBavard.getText().isEmpty() && !existeDeja) {
                    // Récup interet du bavard du JRadioButton (si rien n'est selectionné, interet est true)
                    boolean interetChoisi = true;
                    if (creationNonButton.isSelected()) {
                        interetChoisi = false;
                    } 
                    
            // MAJ des entités liés à la création d'un bavard
                // Création et ajout du bavard dans la liste de bavards du batiment
                    baraque.addBavard(new Bavard(nomBavard, baraque, interetChoisi));
                    JOptionPane.showMessageDialog(null, nomBavard + " créé avec succès");

                // MAJ dans la JComboBox contenant la liste de tous les bavards de l'onglet connexion
                    // Créer une nouvelle liste des bavards 
                    ArrayList<String> nouvelleListeBavards = new ArrayList<>();
                    for (Bavard unBavard : baraque.listBavards) {
                        nouvelleListeBavards.add(unBavard.getNom());
                    }
                    // Créer un nouveau DefaultComboBoxModel avec la nouvelle liste des bavards 
                    DefaultComboBoxModel<String> nouveauListeBavardsComboBoxModel = new DefaultComboBoxModel<>(nouvelleListeBavards.toArray(new String[0]));
                    // Définir le nouveau DefaultComboBoxModel sur la JComboBox
                    connexionListeBavardsComboBox.setModel(nouveauListeBavardsComboBoxModel);
                    bavardListeBavardsComboBox.setModel(nouveauListeBavardsComboBoxModel);
                } else {
                    if (creationNomBavard.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null,"Le nom du bavard ne peut pas être vide");
                    }
                    if (existeDeja) {
                        JOptionPane.showMessageDialog(null,"Le nom du bavard est déjà pris");
                    }
                }
            }
        });

// Partie creation theme
    // Placement JLabel("Nom du thème")
    gbc.gridx = 0;
    gbc.gridy = 4;
    gbc.anchor = GridBagConstraints.EAST;
    creation.add(new JLabel("Nom du thème : "), gbc);

    // Placement JTextField pour entrer le nom du theme à créer
    gbc.gridx = 1;
    gbc.gridy = 4;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx = 1.0;
    JTextField creationNomTheme = new JTextField(10);
    creation.add(creationNomTheme, gbc);

    // Placement du bouton de création du theme
    gbc.gridx = 1;
    gbc.gridy = 5;
    gbc.fill = GridBagConstraints.NONE;
    gbc.weightx = 0.0;
    gbc.anchor = GridBagConstraints.WEST;
    JButton creationThemeBtn = new JButton("Créer thème");
    creation.add(creationThemeBtn, gbc);

    // Ajouter un écouteur d'événements au bouton de création des themes
    creationThemeBtn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent arg0) {
            // Récup nom du theme du JTextField
            String nomTheme = creationNomTheme.getText(); 
            // On verifie que le nom du theme n'est pas deja pris
            boolean existeDeja = false;
            for (String unTheme : baraque.listThemes) {
                if (unTheme.equals(nomTheme))
                existeDeja = true;
            }
            // On vérifie que le JTextField contenant le nom du bavard n'est pas vide ou déjà existant
            if (!creationNomTheme.getText().isEmpty() && !existeDeja) {
                
        // MAJ des entités liés à la création d'un theme
            // Création et ajout du theme dans la liste de theme du batiment
                baraque.addTheme(nomTheme);
                JOptionPane.showMessageDialog(null, "Le thème " + nomTheme + " a été créé avec succès");

            // MAJ dans les JComboBox contenant la liste de tous les themes de l'onglet bavard
                // Créer une nouvelle liste des themes à partir de ceux du batiment
                ArrayList<String> nouvelleListeThemes = new ArrayList<>();
                for (String unTheme : baraque.listThemes) {
                    nouvelleListeThemes.add(unTheme);
                }
                // Créer un nouveau DefaultComboBoxModel avec la nouvelle liste des themes 
                DefaultComboBoxModel<String> nouveauListeThemesComboBoxModel = new DefaultComboBoxModel<>(nouvelleListeThemes.toArray(new String[0]));
                // Définir le nouveau DefaultComboBoxModel sur les JComboBox
                bavardListeThemeEnvoieComboBox.setModel(nouveauListeThemesComboBoxModel);
                bavardListeThemesABloquerComboBox.setModel(nouveauListeThemesComboBoxModel);

            } else {
                if (creationNomTheme.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null,"Le nom du theme ne peut pas être vide");
                }
                if (existeDeja) {
                    JOptionPane.showMessageDialog(null,"Le nom du theme est déjà pris");
                }
            }
        }
    });
//----------------------------------------------------------------------------------//
//                                                                                  //
//                                                                                  //
//                                                                                  //
//                                 ONGLET BAVARD                                    //
//                                                                                  //
//                                                                                  //
//                                                                                  //
//----------------------------------------------------------------------------------//
// Ajout composant pour onglet bavard
        bavard.setLayout(new GridBagLayout());

        // Placement JLabel("Bavards connectés : ")
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        bavard.add(new JLabel("Bavards connectés : "), gbc);  

    // Ajout JCombobox contenant la liste des bavards connectés dans l'onglet bavard
        //Créer un DefaultComboBoxModel avec la liste des bavards connectés initié dans // INITIALISATION //
        DefaultComboBoxModel<String> listeBavardsConnecteComboBoxModel = new DefaultComboBoxModel<>(listeBavardsConnecte.toArray(new String[0]));
        // Créer la JComboBox avec le DefaultComboBoxModel
        listeBavardsConnecteComboBox = new JComboBox<>();
        listeBavardsConnecteComboBox.setModel(listeBavardsConnecteComboBoxModel);
        // Placement de la JCombobox
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 3.0;
        gbc.gridwidth = 2; // Occupe 2 colonnes
        bavard.add(listeBavardsConnecteComboBox, gbc);

    // Placement JLabel("Sujet : ")
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        bavard.add(new JLabel("Sujet : "), gbc);       

    // Placement JTextField contenant le sujet que le bavards souhaite envoyer
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 3.0;
        gbc.gridwidth = 2; // Occupe 2 colonnes
        JTextField bavardSujetEnvoye = new JTextField(10);
        bavard.add(bavardSujetEnvoye, gbc);

    // Création du JPanel qui contient le JLabel("Contenu : ") et le bouton pour envoyer le message
    // Contient également la combobox des themes et le JLabel("Thème : ")
        JPanel bavardContenuEnvoiPanel = new JPanel();
        bavardContenuEnvoiPanel.setLayout(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = 0;
        bavardContenuEnvoiPanel.add(new JLabel("Thème : "), gbc);


    // Ajout JCombobox contenant la liste des themes dans l'onglet bavard
        //Créer un DefaultComboBoxModel avec la liste des themes initié dans // INITIALISATION //
        DefaultComboBoxModel<String> bavardListeThemeEnvoieComboBoxModel = new DefaultComboBoxModel<>(themesListe.toArray(new String[0]));
        // Créer la JComboBox avec le DefaultComboBoxModel
        bavardListeThemeEnvoieComboBox.setModel(bavardListeThemeEnvoieComboBoxModel);
        // Placement de la JCombobox
        gbc.gridx = 0;
        gbc.gridy = 1;
        bavardContenuEnvoiPanel.add(bavardListeThemeEnvoieComboBox, gbc);


        gbc.gridx = 0;
        gbc.gridy = 2;
        bavardContenuEnvoiPanel.add(new JLabel("Contenu : "), gbc);

        JButton BavardSendBtn = new JButton("Envoyer");
        gbc.gridx = 0;
        gbc.gridy = 3;
        bavardContenuEnvoiPanel.add(BavardSendBtn, gbc);
        // Placement du JPanel
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        bavard.add(bavardContenuEnvoiPanel,gbc);

    // Création et placement du JTextArea qui contient le corps du message que le bavard souhaite envoyer
        JTextArea persoTextArea = new JTextArea(10, 20); // 10 lignes de hauteur et 20 colonnes de largeur
        persoTextArea.setLineWrap(true);
        persoTextArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(persoTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Occupe 2 colonnes
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0;
        gbc.weighty = 2.0; // Ajoute du poids en hauteur
        bavard.add(scrollPane, gbc);

    // Creer une liste de sujets des messages que le bavard reçoit
        DefaultListModel<String> bavardSubjectsModel = new DefaultListModel<>();
        String personneCourant = (String) listeBavardsConnecteComboBox.getSelectedItem(); 
        for (Bavard unBavard : baraque.listBavards) {
            if (unBavard.getNom() == personneCourant) { // Si le bavard est le bavard courant, on ajoute dans la liste dynamique des sujets ses sujets
                for (PapotageEvent potin : unBavard.listPapotages) {
                    bavardSubjectsModel.addElement(potin.sujet + " à " + potin.currentTime);
                }
            }
        }
    // Texte de base dans les messages qui seront visualisés
        String bavardLabelText = "Beaucoup de texte ici...\n"; 
        bavardLabelText += "Ce texte peut s'étendre sur plusieurs lignes et nécessiter une barre de défilement.";
        // Ajout de la JTextArea pour visualiser les messages reçu
        JTextArea bavardReadTextArea = new JTextArea(bavardLabelText);
        bavardReadTextArea.setEditable(false); // Désactive l'édition du texte
        bavardReadTextArea.setLineWrap(true); // Active le retour à la ligne automatique
        bavardReadTextArea.setWrapStyleWord(true); // Active le retour à la ligne au niveau des mots
        // Ajout de la JTextArea non editable equipé barre de défilement si le message est trop long
        JScrollPane bavardReadScrollPane = new JScrollPane(bavardReadTextArea);
        bavardReadScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        bavardReadScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        // Placement de la ScrollPane
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2; // Occupe 2 colonnes
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 5.0;
        gbc.weighty = 2.0; // Ajoute du poids en hauteur
        bavard.add(bavardReadScrollPane, gbc);

        
    // Quand on change de bavard courant avec la combobox
        listeBavardsConnecteComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                // MAJ de la liste des sujets
                    // Créer un nouveau DefaultListModel avec la nouvelle liste des sujets du bavard courant
                    DefaultListModel<String> currentNewBavardSubjectsModel = new DefaultListModel<>();
                    String personneCourant = (String) listeBavardsConnecteComboBox.getSelectedItem(); 
                    // Récup le bavard connecté
                    Bavard bavardCourant = null;
                    for (Bavard unBavard : baraque.listBavards) {
                        if (unBavard.getNom().equals(personneCourant)) {
                            bavardCourant = unBavard;
                        }
                    }

                    for (Bavard unBavard : baraque.listBavards) {
                        if (unBavard.getNom().equals(personneCourant)) { // Si le bavard est le bavard courant, on ajoute dans la liste dynamique des sujets ses propres sujets
                            for (PapotageEvent potin : unBavard.listPapotages) {
                                currentNewBavardSubjectsModel.addElement(potin.sujet + " à " + potin.currentTime);
                            }
                        }
                    }
                    // Définir le nouveau DefaultListModel sur la JList
                    bavardSubjectList.setModel(currentNewBavardSubjectsModel);

                // MAJ des logs de connexion
                    // Créer un nouveau DefaultListModel avec la nouvelle liste des logs
                    DefaultListModel<String> newLogConnexionListModel = new DefaultListModel<>();
                    for (Bavard unBavard : baraque.listBavards) {
                        if (unBavard.getNom().equals(personneCourant)) {
                            for (OnLineBavardEvent signalCo : unBavard.listOnLine) {
                                newLogConnexionListModel.addElement(signalCo.envoyeur.getNom() + " : " + signalCo.getCurrentTime());
                                //System.out.println(signalCo.envoyeur.getNom() + " : " + signalCo.getCurrentTime());
                            }
                        }
                    }
                    // Définir le nouveau DefaultListModel sur la JList
                    logConnexionList.setModel(newLogConnexionListModel);
                    

                // MAJ des logs de déconnexion
                    // Créer un nouveau DefaultListModel avec la nouvelle liste des logs
                    DefaultListModel<String> newLogDeconnexionListModel = new DefaultListModel<>();
                    for (Bavard unBavard : baraque.listBavards) {
                        if (unBavard.getNom().equals(personneCourant)) {
                            for (OffLineBavardEvent signalDeco : unBavard.listOffLine) {
                                newLogDeconnexionListModel.addElement(signalDeco.envoyeur.getNom() + " : " + signalDeco.getCurrentTime());
                                //System.out.println(signalDeco.envoyeur.getNom() + " : " + signalDeco.getCurrentTime());
                            }
                        }
                    }
                    // Définir le nouveau DefaultListModel sur la JList
                    logDeconnexionList.setModel(newLogDeconnexionListModel);
                // partie blocage
                    String nomBavardSelected = (String) bavardListeBavardsComboBox.getSelectedItem();
                    System.out.println(bavardCourant.getNom()+" a t il bloqué "+nomBavardSelected+"? ");
                    System.out.println(bavardCourant.isBavardBlocked(nomBavardSelected));
                    // on regarde si le bavard dans la comboBox des bloqués est bloqué par le bavard connecté et on met à jour le switch
                    
                    if (bavardCourant.isBavardBlocked(nomBavardSelected)) {
                        System.out.println("on passe en rouge");
                        blockSwitchButton.setSelected(true);
                        System.out.println("on est passé en rouge");
                    } else {
                        System.out.println("on passe en vert");
                        blockSwitchButton.setSelected(false);
                        System.out.println("on est passé en vert");
                    }

                // partie gestion themes
                    // on regarde si le theme dans la comboBox des themes à gerer est bloqué par le bavard connecté et on met à jour le switch
                    String nomThemeSelected = (String) bavardListeThemesABloquerComboBox.getSelectedItem();
                    System.out.println(bavardCourant.getNom()+" a t il bloqué le theme "+nomThemeSelected+"? ");
                    System.out.println(bavardCourant.isThemeBlocked(nomThemeSelected));
                    // on regarde si le bavard dans la comboBox des bloqués est bloqué par le bavard connecté et on met à jour le switch
                    
                    if (bavardCourant.isThemeBlocked(nomThemeSelected)) {
                        System.out.println("on passe en rouge");
                        themeSwitchButton.setSelected(true);
                        System.out.println("on est passé en rouge");
                    } else {
                        System.out.println("on passe en vert");
                        themeSwitchButton.setSelected(false);
                        System.out.println("on est passé en vert");
                    }
                }
        });

    // Quand on sélectionne un sujet dans la liste des sujets
        bavardSubjectList.setModel(bavardSubjectsModel);
        bavardSubjectList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        bavardSubjectList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Recup bavard courant
                String personneCourant = (String) listeBavardsConnecteComboBox.getSelectedItem(); 
                if (!e.getValueIsAdjusting()) {
                    
                    String bavardSelectedSubject = bavardSubjectList.getSelectedValue();
                    // Affichage du message dans la zone dédiée en fonction du bavard courant
                    for (Bavard unBavard : baraque.listBavards) {
                        if (unBavard.getNom().equals(personneCourant)) {
                            for (PapotageEvent potin : unBavard.listPapotages) {
                                if ((potin.sujet + " à " + potin.currentTime).equals(bavardSelectedSubject)) {
                                    //System.out.println(potin.envoyeur.getNom().equals(personneCourant));
                                    if (potin.envoyeur.getNom().equals(personneCourant)) {// si on regarde notre propre msg
                                        bavardReadTextArea.setText(potin.corps + "\n de moi");
                                    } else {
                                        bavardReadTextArea.setText(potin.corps + "\n de " + potin.envoyeur.getNom());
                                    }
                                    
                                }
                            }
                        }
                    }
                }
            }
        });
        // Placement de la zone 
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1; // Occupe 1 colonnes
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 2.0;
        gbc.weighty = 1.0; // Ajoute du poids en hauteur
        JScrollPane bavardSscrollPane = new JScrollPane(bavardSubjectList);
        bavard.add(bavardSscrollPane, gbc);
     


// Ajouter un écouteur d'événements au bouton qui envoit un message
        BavardSendBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent arg0) {
                // On vérifie que le JTextField contenant le nom du bavard n'est pas vide ou déjà existant
                if (!bavardSujetEnvoye.getText().isEmpty()) {
                    // Récup nom du bavard
                    String nomBavardCourant = (String) listeBavardsConnecteComboBox.getSelectedItem();

                    // Envoie du msg par le bavard courant au concierge
                    for (Bavard unBavard : baraque.listBavards) {
                        if (unBavard.getNom() == nomBavardCourant) {
                            unBavard.sendPotin(bavardSujetEnvoye.getText(),persoTextArea.getText(), (String) bavardListeThemeEnvoieComboBox.getSelectedItem());
                            //System.out.println("msg envoyé au concierge");
                        }
                    }

                    
                // MAJ de la liste des sujets de l'onglet bavard
                    // Créer un nouveau DefaultListModel avec la nouvelle liste des sujets
                    DefaultListModel<String> newBavardSubjectsModel = new DefaultListModel<>();
                    for (Bavard unBavard : baraque.listBavards) {
                        if (unBavard.getNom() == nomBavardCourant) {
                            for (PapotageEvent potin : unBavard.listPapotages) {
                                newBavardSubjectsModel.addElement(potin.sujet + " à " + potin.currentTime);
                            }
                        }
                    }

                    // Définir le nouveau DefaultListModel sur la JList
                    bavardSubjectList.setModel(newBavardSubjectsModel);

                // MAJ de la liste des sujets de l'onglet concierge
                    // Créer un nouveau DefaultListModel avec la nouvelle liste des sujets
                    DefaultListModel<String> newConciergeSubjectsModel = new DefaultListModel<>();
                    for (PapotageEvent potin : baraque.concierge.listPapotages) {
                        newConciergeSubjectsModel.addElement(potin.sujet + " à " + potin.currentTime);
                    }
                    
                    // Définir le nouveau DefaultListModel sur la JList
                    conciergeSubjectList.setModel(newConciergeSubjectsModel);
                    //JOptionPane.showMessageDialog(null," créé avec succès");
                } else {
                    JOptionPane.showMessageDialog(null,"Le sujet du message ne peut pas être vide");
                }
            }
        });
        

// Partie historique des connexions/deconnexions
    JPanel bavardPanelLogs = new JPanel(new GridBagLayout());
    // Placement JLabel("Historique des connexions: ")
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.EAST;
        bavardPanelLogs.add(new JLabel("Historique des connexions: "), gbc); 

    // Placement JLabel("Historique des déconnexions: ")
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.EAST;
        bavardPanelLogs.add(new JLabel("Historique des déconnexions : "), gbc); 

        // Creer une liste des logs de connexion que reçoivent les bavards connectés
        DefaultListModel<String> logConnexionListModel = new DefaultListModel<>();
        // Définir le premier DefaultListModel sur la JList
        logConnexionList.setModel(logConnexionListModel);

        // Creer une liste des logs de déconnexion que reçoivent les bavards connectés
        DefaultListModel<String> logDeconnexionListModel = new DefaultListModel<>();
        // Définir le premier DefaultListModel sur la JList
        logDeconnexionList.setModel(logDeconnexionListModel);
        
    // Placement de la liste de connexion équipé d'une scrollbar
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Occupe 1 colonne
        gbc.gridheight = 1; // Occupe 1 ligne
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 3.0; 
        JScrollPane logConnexionScrollPane = new JScrollPane(logConnexionList);
        bavardPanelLogs.add(logConnexionScrollPane, gbc);

    // Placement de la liste de déconnexion équipé d'une scrollbar
        gbc.gridx = 3;
        gbc.gridy = 4;
        gbc.gridwidth = 1; // Occupe 1 colonnes
        gbc.gridheight = 1; // Occupe 1 ligne
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 3.0; 
        JScrollPane logDeconnexionScrollPane = new JScrollPane(logDeconnexionList);
        bavardPanelLogs.add(logDeconnexionScrollPane, gbc);
    // Placement du JPanel des logs dans l'onglet bavard
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 1; // Occupe 1 colonnes
        gbc.gridheight = 6; // Occupe 1 ligne
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 0; 
        bavard.add(bavardPanelLogs, gbc);



// Partie blocking des bavards
        JPanel bavardPanelBlock = new JPanel(new GridBagLayout());


        // Créer le bouton switch
        blockSwitchButton.setSelectedIcon(onIcon);
        blockSwitchButton.setPreferredSize(new Dimension(80, 30)); // Définir la taille préférée du bouton
        blockSwitchButton.setBorderPainted(false); // Masquer la bordure du bouton
        blockSwitchButton.setFocusPainted(false); // Masquer la bordure de focus du bouton

        // Placement JLabel("Gestionnaire des blocages: ")
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridwidth = 1; // Occupe 1 colonnes
        gbc.gridheight = 1; // Occupe 1 ligne
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        bavardPanelBlock.add(new JLabel("Gestionnaire des blocages : "), gbc); 


        //Créer un DefaultComboBoxModel avec la liste des bavards connectés
        DefaultComboBoxModel<String> bavardListeBavardsComboBoxModel = new DefaultComboBoxModel<>(bavardsListe.toArray(new String[0]));
        // Créer la JComboBox avec le DefaultComboBoxModel
        //bavardListeBavardsComboBox = new JComboBox<>();
        bavardListeBavardsComboBox.setModel(bavardListeBavardsComboBoxModel);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Occupe 1 colonne
        gbc.gridheight = 1; // Occupe 1 ligne
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.weighty = 1; 
        bavardPanelBlock.add(bavardListeBavardsComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1; // Occupe 1 colonne
        gbc.gridheight = 1; // Occupe 1 ligne
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.weighty = 1; 
        bavardPanelBlock.add(blockSwitchButton, gbc);

        // Placement du JPanel du blocking dans l'onglet bavard
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridwidth = 1; // Occupe 1 colonne
        gbc.gridheight = 4; // Occupe 4 lignes
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1;
        gbc.weighty = 1; 
        bavard.add(bavardPanelBlock, gbc);

        // Quand on change de bavard à gérer (bloquer/debloquer) avec la combobox
        bavardListeBavardsComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (listeBavardsConnecteComboBox.getItemCount() != 0){
                    // Récup nom du bavard pour avoir le bavard connecté
                    String nomBavardCourant = (String) listeBavardsConnecteComboBox.getSelectedItem();
                    Bavard bavardCourant = null;
                    for (Bavard unBavard : baraque.listBavards) {
                        if (unBavard.getNom().equals(nomBavardCourant)) {
                            bavardCourant = unBavard;
                        }
                    }
                    String nomBavardSelected = (String) bavardListeBavardsComboBox.getSelectedItem();
                    // on regarde si le bavard est bloqué par le bavard connecté et on met à jour le switch
                    if (bavardCourant.isBavardBlocked(nomBavardSelected)) {
                        blockSwitchButton.setSelected(true);
                    } else {
                        blockSwitchButton.setSelected(false);
                    }
                }
            }
        });

    // quand on clique sur le switch 
        blockSwitchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                // Récup nom du bavard pour avoir le bavard connecté
                if (listeBavardsConnecteComboBox.getItemCount() != 0){    
                    String nomBavardCourant = (String) listeBavardsConnecteComboBox.getSelectedItem();
                    Bavard bavardCourant = null;
                    for (Bavard unBavard : baraque.listBavards) {
                        if (unBavard.getNom().equals(nomBavardCourant)) {
                            bavardCourant = unBavard;
                        }
                    }

                    String nomBavardSelected = (String) bavardListeBavardsComboBox.getSelectedItem();

                    // Détecter les changements d'état du bouton switch
                    if (blockSwitchButton.isSelected()) {
                        bavardCourant.bloquerBavard(nomBavardSelected);
                        System.out.println(nomBavardSelected + "est bloqué");
                    } else {
                        bavardCourant.debloquerBavard(nomBavardSelected);
                        System.out.println(nomBavardSelected + "est débloqué");
                    }
                } else { // si aucun bavard n'est connecté, on repasse le switch à l'etat initial
                    JOptionPane.showMessageDialog(null, "Aucun bavard connecté");
                    blockSwitchButton.setSelected(false);
                }
            }
        });


// Partie gestion des themes
        JPanel bavardPanelTheme = new JPanel(new GridBagLayout());


        // Créer le bouton switch
        themeSwitchButton.setSelectedIcon(onIcon);
        themeSwitchButton.setPreferredSize(new Dimension(80, 30)); // Définir la taille préférée du bouton
        themeSwitchButton.setBorderPainted(false); // Masquer la bordure du bouton
        themeSwitchButton.setFocusPainted(false); // Masquer la bordure de focus du bouton

        // Placement JLabel("Gestionnaire des themes: ")
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridwidth = 1; // Occupe 1 colonnes
        gbc.gridheight = 1; // Occupe 1 ligne
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        bavardPanelTheme.add(new JLabel("Gestionnaire des thèmes : "), gbc); 


        //Créer un DefaultComboBoxModel avec la liste des themes
        DefaultComboBoxModel<String> bavardListeThemesComboBoxModel = new DefaultComboBoxModel<>(themesListe.toArray(new String[0]));
        // Créer la JComboBox avec le DefaultComboBoxModel
        //bavardListeBavardsComboBox = new JComboBox<>();
        bavardListeThemesABloquerComboBox.setModel(bavardListeThemesComboBoxModel);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Occupe 1 colonne
        gbc.gridheight = 1; // Occupe 1 ligne
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.weighty = 1; 
        bavardPanelTheme.add(bavardListeThemesABloquerComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1; // Occupe 1 colonne
        gbc.gridheight = 1; // Occupe 1 ligne
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.weighty = 1; 
        bavardPanelTheme.add(themeSwitchButton, gbc);

        // Placement du JPanel gestion des themes dans l'onglet bavard
        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Occupe 1 colonne
        gbc.gridheight = 4; // Occupe 4 lignes
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1;
        gbc.weighty = 1; 
        bavard.add(bavardPanelTheme, gbc);

        // Quand on change de theme à gérer (bloquer/debloquer) avec la combobox
        bavardListeThemesABloquerComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (listeBavardsConnecteComboBox.getItemCount() != 0){ // si la combobox n'est pas vide
                    // Récup nom du bavard pour avoir le bavard connecté
                    String nomBavardCourant = (String) listeBavardsConnecteComboBox.getSelectedItem();
                    Bavard bavardCourant = null;
                    for (Bavard unBavard : baraque.listBavards) {
                        if (unBavard.getNom().equals(nomBavardCourant)) {
                            bavardCourant = unBavard;
                        }
                    }
                    String nomThemeSelected = (String) bavardListeThemesABloquerComboBox.getSelectedItem();
                    // on regarde si le theme est bloqué par le bavard connecté et on met à jour le switch
                    if (bavardCourant.isThemeBlocked(nomThemeSelected)) {
                        themeSwitchButton.setSelected(true);
                    } else {
                        themeSwitchButton.setSelected(false);
                    }
                }
            }
        });

    // quand on clique sur le switch du theme pour le bloquer/debloquer
        themeSwitchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                // Récup nom du bavard pour avoir le bavard connecté
                if (listeBavardsConnecteComboBox.getItemCount() != 0){    
                    String nomBavardCourant = (String) listeBavardsConnecteComboBox.getSelectedItem();
                    Bavard bavardCourant = null;
                    for (Bavard unBavard : baraque.listBavards) {
                        if (unBavard.getNom().equals(nomBavardCourant)) {
                            bavardCourant = unBavard;
                        }
                    }

                    String nomThemeSelected = (String) bavardListeThemesABloquerComboBox.getSelectedItem();

                    // Détecter les changements d'état du bouton switch
                    if (themeSwitchButton.isSelected()) {
                        bavardCourant.bloquerTheme(nomThemeSelected);
                        System.out.println("Le thème " + nomThemeSelected + "est bloqué");
                    } else {
                        bavardCourant.debloquerTheme(nomThemeSelected);
                        System.out.println("Le thème " + nomThemeSelected + "est débloqué");
                    }
                } else { // si aucun bavard n'est connecté, on repasse le switch à l'etat initial
                    JOptionPane.showMessageDialog(null, "Aucun bavard connecté");
                    themeSwitchButton.setSelected(false);
                }
            }

            
        });
//----------------------------------------------------------------------------------//
//                                                                                  //
//                                                                                  //
//                                                                                  //
//                                 ONGLET CONNEXION                                 //
//                                                                                  //
//                                                                                  //
//                                                                                  //
//----------------------------------------------------------------------------------//
// Ajout composant pour onglet connexion
        connexion.setLayout(new GridBagLayout());
    // Placement JLabel("Liste des bavards : ")
        gbc.gridwidth = 1; // Occupe 1 colonne
        gbc.gridheight = 1; // Occupe 1 ligne
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        connexion.add(new JLabel("Liste des bavards : "), gbc);

        //Créer un DefaultComboBoxModel avec la liste des bavards connectés
        DefaultComboBoxModel<String> listeBavardsComboBoxModel = new DefaultComboBoxModel<>(bavardsListe.toArray(new String[0]));
        // Créer la JComboBox avec le DefaultComboBoxModel
        connexionListeBavardsComboBox = new JComboBox<>();
        connexionListeBavardsComboBox.setModel(listeBavardsComboBoxModel);
    // Placement de la combobox qui contient la liste complète des bavards
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 3.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.EAST;
        connexion.add(connexionListeBavardsComboBox, gbc);

    // Placement JLabel("Connecté : ")
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        gbc.anchor = GridBagConstraints.WEST;
        connexion.add(new JLabel("Connecté : "), gbc);
    // Création et placement des JRadioButton pour choisir si on se connecte ou non
        JRadioButton connexionOuiButton = new JRadioButton("Oui");
        connexionOuiButton.setSelected(true); // Sélectionner le JRadioButton "Oui" par défaut
        JRadioButton connexionNonButton = new JRadioButton("Non");
        ButtonGroup connexionGroup = new ButtonGroup();
        connexionGroup.add(connexionOuiButton);
        connexionGroup.add(connexionNonButton);

        // Créer un JPanel séparé pour les JRadioButtons
        JPanel connexionRadioPanel = new JPanel();
        connexionRadioPanel.setLayout(new BoxLayout(connexionRadioPanel, BoxLayout.X_AXIS));
        connexionRadioPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        connexionOuiButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        connexionNonButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        connexionRadioPanel.add(connexionOuiButton);
        connexionRadioPanel.add(connexionNonButton);
        // Placement des radio button
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        gbc.anchor = GridBagConstraints.WEST;
        connexion.add(connexionRadioPanel, gbc);

        // Placement JLabel("Intérêt : ")
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        gbc.anchor = GridBagConstraints.WEST;
        connexion.add(new JLabel("Intérêt : "), gbc);
    // Création et placement des JRadioButton pour choisir si on est intéressé ou non
        JRadioButton connexionInteretOuiButton = new JRadioButton("Oui");
        connexionInteretOuiButton.setSelected(true); // Sélectionner le JRadioButton "Oui" par défaut
        JRadioButton connexionInteretNonButton = new JRadioButton("Non");
        ButtonGroup connexionInteretGroup = new ButtonGroup();
        connexionInteretGroup.add(connexionInteretOuiButton);
        connexionInteretGroup.add(connexionInteretNonButton);

        // Créer un JPanel séparé pour les JRadioButtons
        JPanel connexionInteretRadioPanel = new JPanel();
        connexionInteretRadioPanel.setLayout(new BoxLayout(connexionInteretRadioPanel, BoxLayout.X_AXIS));
        connexionInteretRadioPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        connexionInteretOuiButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        connexionInteretNonButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        connexionInteretRadioPanel.add(connexionInteretOuiButton);
        connexionInteretRadioPanel.add(connexionInteretNonButton);
        // Placement des radio button
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        gbc.anchor = GridBagConstraints.WEST;
        connexion.add(connexionInteretRadioPanel, gbc);

    // Placement du bouton de validation de la connexion/déconnexion et du choix de l'interet
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        gbc.anchor = GridBagConstraints.WEST;
        JButton connexionActualisationBtn = new JButton("Mettre à jour");
        connexion.add(connexionActualisationBtn, gbc);

// Ajouter un écouteur d'événements au bouton pour mettre à jour le fait d'être connecté
        connexionActualisationBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent arg0) {
            // Récup nom du bavard
                String nomBavardAConnecter = (String) connexionListeBavardsComboBox.getSelectedItem(); 
                
            // Récup choix connexion/deconnexion du bavard
                boolean connexionChoisi = true;
                if (connexionNonButton.isSelected()) {
                    connexionChoisi = false;
                } 
                
            // Récup choix interet du bavard
            boolean ineretChoisi = true;
            if (connexionInteretNonButton.isSelected()) {
                ineretChoisi = false;
            } 

            // Changement de la connexion
                for (Bavard personneConnexion : baraque.listBavards) {
                    if (personneConnexion.getNom().equals(nomBavardAConnecter)) {
                        personneConnexion.setConnecte(connexionChoisi);
                        personneConnexion.setInteret(ineretChoisi);
                        JOptionPane.showMessageDialog(null, nomBavardAConnecter + " a changé sa connexion");

                    // Diffusion du signal de connexion ou deconnexion à tous les bavards
                        if (connexionChoisi) {
                            //System.out.println(personneConnexion.getNom() +" signal sa connexion à "+unBavard.getNom());
                            personneConnexion.signalConnexion();
                        } else {
                            personneConnexion.signalDeconnexion();
                            
                        }
                    }
                }

            // Créer une nouvelle liste des bavards connectés
                ArrayList<String> nouvelleListeBavardsConnecte = new ArrayList<>();
                for (Bavard unBavard : baraque.listBavards) {
                    if (unBavard.isConnecte()) {
                        nouvelleListeBavardsConnecte.add(unBavard.getNom());
                    }
                }
                // Créer un nouveau DefaultComboBoxModel avec la nouvelle liste des bavards connectés
                DefaultComboBoxModel<String> nouveauComboBoxModel = new DefaultComboBoxModel<>(nouvelleListeBavardsConnecte.toArray(new String[0]));
                // Définir le nouveau DefaultComboBoxModel sur la JComboBox
                listeBavardsConnecteComboBox.setModel(nouveauComboBoxModel);

                String nomBavardCourant = (String) listeBavardsConnecteComboBox.getSelectedItem(); 

            // MAJ des logs de connexion
                // Créer un nouveau DefaultListModel avec la nouvelle liste des logs
                DefaultListModel<String> newLogConnexionListModel = new DefaultListModel<>();
                for (Bavard unBavard : baraque.listBavards) {
                    if (unBavard.getNom().equals(nomBavardCourant)) {
                        for (OnLineBavardEvent signalCo : unBavard.listOnLine) {
                            newLogConnexionListModel.addElement(signalCo.envoyeur.getNom() + " : " + signalCo.getCurrentTime());
                            System.out.println(signalCo.envoyeur.getNom() + " : " + signalCo.getCurrentTime());
                        }
                    }
                }

                // Définir le nouveau DefaultListModel sur la JList
                logConnexionList.setModel(newLogConnexionListModel);


            // MAJ des logs de déconnexion
                // Créer un nouveau DefaultListModel avec la nouvelle liste des logs
                DefaultListModel<String> newLogDeconnexionListModel = new DefaultListModel<>();
                for (Bavard unBavard : baraque.listBavards) {
                    if (unBavard.getNom().equals(nomBavardCourant)) {
                        for (OffLineBavardEvent signalDeco : unBavard.listOffLine) {
                            newLogDeconnexionListModel.addElement(signalDeco.envoyeur.getNom() + " : " + signalDeco.getCurrentTime());
                            System.out.println(signalDeco.envoyeur.getNom() + " : " + signalDeco.getCurrentTime());
                        }
                    }
                }

                // Définir le nouveau DefaultListModel sur la JList
                logDeconnexionList.setModel(newLogDeconnexionListModel);

            }   
        });
        

//----------------------------------------------------------------------------------//
//                                                                                  //
//                                                                                  //
//                                                                                  //
//                                 ONGLET CONCIERGE                                 //
//                                                                                  //
//                                                                                  //
//                                                                                  //
//----------------------------------------------------------------------------------//
// Ajout composant pour onglet concierge
        concierge.setLayout(new GridBagLayout());

    // Placement JLabel("Messages reçus : ")
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0; 
        gbc.anchor = GridBagConstraints.WEST;
        concierge.add(new JLabel("Messages reçus : "), gbc);  


    // Creation de la zone de pour visualiser les messages    
        JTextArea conciergeReadTextArea = new JTextArea("initialisation");

        // créer une JList à partir de l'ArrayList
        conciergeSubjectList = new JList<>(conciergeSubjects.toArray(new String[0]));
        conciergeSubjectList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        conciergeSubjectList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        conciergeSubjectList.addListSelectionListener(new ListSelectionListener() {
            @Override
            // Quand on clique sur un sujet dans la liste, on met met à jour la zone de visu 
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String conciergeSelectedSubject = conciergeSubjectList.getSelectedValue();
                    System.out.println("Sujet sélectionné : " + conciergeSelectedSubject);
                    for (PapotageEvent potin : baraque.concierge.listPapotages) {
                        if ((potin.sujet + " à " + potin.currentTime).equals(conciergeSelectedSubject)) {
                            conciergeReadTextArea.setText(potin.corps + "\n de " + potin.envoyeur.getNom());
                        }
                    } 
                }
            }
        });
        // On ajoute une scrollbar à la zone de visu 
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1; // Occupe 1 colonnes
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0; // Ajoute du poids en hauteur
        JScrollPane conciergeScrollPane = new JScrollPane(conciergeSubjectList);
        concierge.add(conciergeScrollPane, gbc);
        
        

        conciergeReadTextArea.setEditable(false); // Désactive l'édition du texte
        conciergeReadTextArea.setLineWrap(true); // Active le retour à la ligne automatique
        conciergeReadTextArea.setWrapStyleWord(true); // Active le retour à la ligne au niveau des mots

        JScrollPane conciergeReadScrollPane = new JScrollPane(conciergeReadTextArea);
        conciergeReadScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        conciergeReadScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        // Placement de la zone de visu
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // Occupe 2 colonnes
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 5.0;
        gbc.weighty = 1.0; // Ajoute du poids en hauteur
        concierge.add(conciergeReadScrollPane, gbc);


//----------------------------------------------------------------------------------//
//                                                                                  //
//                                                                                  //
//                                                                                  //
//                             PARAMETRAGE FRAME                                    //
//                                                                                  //
//                                                                                  //
//                                                                                  //
//----------------------------------------------------------------------------------//

    // Ajoute des onglets au JmenuTabbedPane
        menuTabbedPane.addTab("Creation", creation);
        menuTabbedPane.addTab("Bavard", bavard);
        menuTabbedPane.addTab("Connexion", connexion);
        menuTabbedPane.addTab("Concierge", concierge);
    

        // Ajouter le JTabbedPane à la JFrame
        add(menuTabbedPane, BorderLayout.CENTER);
    
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(baraque.nom);
        setSize(1000, 500);
        //setResizable(false);
        setLocationRelativeTo(null);
    }
    
}
