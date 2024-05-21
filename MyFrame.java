import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import javafx.event.ActionEvent;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MyFrame extends JFrame {
    protected Batiment baraque;
    
    public MyFrame(Batiment uneBaraque) {
        this.baraque = uneBaraque;
        initComponents();
    }

    private void initComponents() {
        JTabbedPane tabbedPane = new JTabbedPane();
    
        // Créer des panneaux pour chaque onglet
        JPanel creation = new JPanel();
        JPanel connexion = new JPanel();
        JPanel bavard = new JPanel();
        JPanel concierge = new JPanel();
    

//----------------------------------------------------------------------------------//
//                                                                                  //
//                                                                                  //
//                                                                                  //
//                                 ONGLET CREATION                                  //
//                                                                                  //
//                                                                                  //
//                                                                                  //
//----------------------------------------------------------------------------------//
        // Ajout composants pour onglet creation
        creation.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.EAST;
        creation.add(new JLabel("Nom du bavard : "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        JTextField creationNomBavard = new JTextField(10);
        creation.add(creationNomBavard, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        creation.add(new JLabel("Interet :"), gbc);

        JRadioButton creationOuiButton = new JRadioButton("Oui");
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

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        gbc.anchor = GridBagConstraints.WEST;
        creation.add(creationRadioPanel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        gbc.anchor = GridBagConstraints.WEST;
        JButton creationBavardBtn = new JButton("Créer");
        creation.add(creationBavardBtn, gbc);

        // Définir la taille maximale des cases du GridBagLayout de creation
        Dimension creationMaxSize = new Dimension(200, 30);
        for (Component component : creation.getComponents()) {
            component.setMaximumSize(creationMaxSize);
        }

        // Ajouter un écouteur d'événements au bouton pour créer des bavards
        creationBavardBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent arg0) {
                // Récup nom du bavard
                String nomBavard = creationNomBavard.getText(); 
                
                // Récup interet du bavard
                boolean interetChoisi = true;
                if (creationNonButton.isSelected()) {
                    interetChoisi = false;
                } 
                
                //System.out.println("Nom du bavard : " + nomBavard + " \nIntéret : " + selectedOption);
                baraque.addBavard(new Bavard(nomBavard, baraque, interetChoisi));
                JOptionPane.showMessageDialog(null, nomBavard + " créé avec succès");
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

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        connexion.add(new JLabel("Liste des bavards : "), gbc);

        //Créer du menu déroulant avec tous les bavards
        ArrayList<String> bavardsListe = new ArrayList<>();
        for (Bavard personne : baraque.listBavards) {
            bavardsListe.add(personne.getNom());
        }
        JComboBox<String> listeBavardsComboBox = new JComboBox<>(bavardsListe.toArray(new String[0]));

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        connexion.add(listeBavardsComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        gbc.anchor = GridBagConstraints.EAST;
        connexion.add(new JLabel("Connecté : "), gbc);

        JRadioButton connexionOuiButton = new JRadioButton("Oui");
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

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        gbc.anchor = GridBagConstraints.WEST;
        connexion.add(connexionRadioPanel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
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
                String nomBavard = (String) listeBavardsComboBox.getSelectedItem(); 
                
                // Récup interet du bavard
                boolean connexionChoisi = true;
                if (connexionNonButton.isSelected()) {
                    connexionChoisi = false;
                } 
                
                for (Bavard personne : baraque.listBavards) {
                    if (personne.getNom() == nomBavard) {
                        personne.setConnecte(connexionChoisi);
                        JOptionPane.showMessageDialog(null, nomBavard + " a changé sa connexion");
                    }
                }
                
            }
        });



        // Définir la taille maximale des cases du GridBagLayout
        Dimension connexionMaxSize = new Dimension(200, 30);
        for (Component component : connexion.getComponents()) { // Utiliser connexion au lieu de creation
            component.setMaximumSize(connexionMaxSize);
        }


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

        // Creer une liste des bavards connectés
        ArrayList<String> listeBavardsConnecte = new ArrayList<>();
        for (Bavard unBavard : baraque.listBavards) {
            if (unBavard.connecte == true) {
                listeBavardsConnecte.add(unBavard.getNom());
            }
        }
        JComboBox<String> listeBavardsConnecteComboBox = new JComboBox<>(listeBavardsConnecte.toArray(new String[0]));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        bavard.add(listeBavardsConnecteComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        bavard.add(new JLabel("Sujet : "), gbc);       

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        bavard.add(new JTextField(10), gbc);

        JPanel bavardContenuEnvoiPanel = new JPanel();
        bavardContenuEnvoiPanel.setLayout(new GridLayout(2,1));
        bavardContenuEnvoiPanel.add(new JLabel("Contenu : "));
        JButton BavardSendBtn = new JButton("Envoyer");
        bavardContenuEnvoiPanel.add(BavardSendBtn);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        bavard.add(bavardContenuEnvoiPanel,gbc);

        JTextArea persoTextArea = new JTextArea(10, 20); // 10 lignes de hauteur et 20 colonnes de largeur
        JScrollPane scrollPane = new JScrollPane(persoTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Occupe 2 colonnes
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0;
        gbc.weighty = 1.0; // Ajoute du poids en hauteur
        bavard.add(scrollPane, gbc);

       

        String[] bavardSubjects = {"Sujet 1", "Sujet 2", "Sujet 3", "Sujet 4", "Sujet 5", "Sujet 6", "Sujet 7", "Sujet 8", "Sujet 9", "Sujet 10"};
        JList<String> bavardSubjectList = new JList<>(bavardSubjects);
        bavardSubjectList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        bavardSubjectList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        bavardSubjectList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String bavardSelectedSubject = bavardSubjectList.getSelectedValue();
                    System.out.println("Sujet sélectionné : " + bavardSelectedSubject);
                }
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1; // Occupe 1 colonnes
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0; // Ajoute du poids en hauteur
        JScrollPane bavardSscrollPane = new JScrollPane(bavardSubjectList);
        bavard.add(bavardSscrollPane, gbc);
        

        String bavardLabelText = "Beaucoup de texte ici...\n"; // Ajoutez autant de texte que vous le souhaitez
        bavardLabelText += "Ce texte peut s'étendre sur plusieurs lignes et nécessiter une barre de défilement.";

        JTextArea bavardReadTextArea = new JTextArea(bavardLabelText);
        bavardReadTextArea.setEditable(false); // Désactive l'édition du texte
        bavardReadTextArea.setLineWrap(true); // Active le retour à la ligne automatique
        bavardReadTextArea.setWrapStyleWord(true); // Active le retour à la ligne au niveau des mots

        JScrollPane bavardReadScrollPane = new JScrollPane(bavardReadTextArea);
        bavardReadScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        bavardReadScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2; // Occupe 2 colonnes
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 5.0;
        gbc.weighty = 1.0; // Ajoute du poids en hauteur
        bavard.add(bavardReadScrollPane, gbc);
        
        // Définir la taille maximale des cases du GridBagLayout
        Dimension bavardMaxSize = new Dimension(200, 30);
        for (Component component : bavard.getComponents()) { // Utiliser connexion au lieu de creation
            component.setMaximumSize(bavardMaxSize);
        }


        // Ajouter un écouteur d'événements au bouton pour envoyer un message
        BavardSendBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent arg0) {
                // Récup nom du bavard
                //String nomBavard = creationNomBavard.getText(); 
                System.out.println("ca marche");
                
                //JOptionPane.showMessageDialog(null," créé avec succès");
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


        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0; 
        gbc.anchor = GridBagConstraints.WEST;
        concierge.add(new JLabel("Messages reçus : "), gbc);  

        // Creer une liste des messages que le concierge reçoit
        ArrayList<String> conciergeSubjects = new ArrayList<>();
        for (PapotageEvent potin : baraque.concierge.listPapotages) {
            conciergeSubjects.add(potin.sujet);
        }

        
        JTextArea conciergeReadTextArea = new JTextArea("initialisation");

        // créer une JList à partir de l'ArrayList
        JList<String> conciergeSubjectList = new JList<>(conciergeSubjects.toArray(new String[0]));
        conciergeSubjectList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        conciergeSubjectList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        conciergeSubjectList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedSubject = conciergeSubjectList.getSelectedValue();
                    System.out.println("Sujet sélectionné : " + selectedSubject);
                    for (PapotageEvent potin : baraque.concierge.listPapotages) {
                        if (selectedSubject == potin.sujet) {
                            conciergeReadTextArea.setText(potin.corps);
                        }
                    }
                    
                }
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1; // Occupe 1 colonnes
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0; // Ajoute du poids en hauteur
        JScrollPane conciergeSscrollPane = new JScrollPane(conciergeSubjectList);
        concierge.add(conciergeSscrollPane, gbc);
        
        

        conciergeReadTextArea.setEditable(false); // Désactive l'édition du texte
        conciergeReadTextArea.setLineWrap(true); // Active le retour à la ligne automatique
        conciergeReadTextArea.setWrapStyleWord(true); // Active le retour à la ligne au niveau des mots

        JScrollPane conciergeReadScrollPane = new JScrollPane(conciergeReadTextArea);
        conciergeReadScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        conciergeReadScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // Occupe 2 colonnes
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 5.0;
        gbc.weighty = 1.0; // Ajoute du poids en hauteur
        concierge.add(conciergeReadScrollPane, gbc);

        // Définir la taille maximale des cases du GridBagLayout
        Dimension conciergeMaxSize = new Dimension(200, 30);
        for (Component component : concierge.getComponents()) { // Utiliser connexion au lieu de creation
            component.setMaximumSize(conciergeMaxSize);
        }















        // Ajouter les onglets au JTabbedPane
        tabbedPane.addTab("Creation", creation);
        tabbedPane.addTab("Connexion", connexion);
        tabbedPane.addTab("Bavard", bavard);
        tabbedPane.addTab("Concierge", concierge);
    

        // Ajouter le JTabbedPane à la JFrame
        add(tabbedPane, BorderLayout.CENTER);
    
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Interface de bavardage");
        setSize(400, 300);
        setLocationRelativeTo(null);
    }
    
}
