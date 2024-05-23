import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MyFrame extends JFrame {
    protected Batiment baraque;
    protected JComboBox<String> listeBavardsComboBox;
    protected JList<String> bavardSubjectList;
    protected JList<String> conciergeSubjectList;
    protected JComboBox<String> listeBavardsConnecteComboBox;
    protected JList<String> logConnexionList;
    protected JList<String> logDeconnexionList;

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
//                                                                                   //
//                                                                                  //
//                                                                                   //
//                                INITITIALISATION                                  //
//                                                                                   //
//                                                                                  //
//                                                                                   //
//----------------------------------------------------------------------------------//

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

        // Creer une liste des messages que le concierge reçoit dans l'onglet concierge
        ArrayList<String> conciergeSubjects = new ArrayList<>();
        for (PapotageEvent potin : baraque.concierge.listPapotages) {
            conciergeSubjects.add(potin.sujet);
        }
        conciergeSubjectList = new JList<>();
        listeBavardsComboBox = new JComboBox<>();
        bavardSubjectList = new JList<>();
        logConnexionList = new JList<>();
        logDeconnexionList = new JList<>();
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
/////////////////////////////////////////////////////////////////////////////////////////////////////:
        // Ajouter un écouteur d'événements au bouton pour créer des bavards
        creationBavardBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent arg0) {
                // Récup nom du bavard du JTextField
                String nomBavard = creationNomBavard.getText(); 
                
                // Récup interet du bavard du JRadioButton
                boolean interetChoisi = true;
                if (creationNonButton.isSelected()) {
                    interetChoisi = false;
                } 
                
                // MAJ des entités liés à la création d'un bavard
                baraque.addBavard(new Bavard(nomBavard, baraque, interetChoisi));
                JOptionPane.showMessageDialog(null, nomBavard + " créé avec succès");

                // MAJ dans la comboBox de l'onglet connexion

                // Créer une nouvelle liste des bavards 
                ArrayList<String> nouvelleListeBavards = new ArrayList<>();
                for (Bavard unBavard : baraque.listBavards) {
                    nouvelleListeBavards.add(unBavard.getNom());
                }
                //System.out.println(nouvelleListeBavards.get(nouvelleListeBavards.size()-1));;
                // Créer un nouveau DefaultComboBoxModel avec la nouvelle liste des bavards 
                DefaultComboBoxModel<String> nouveauListeBavardsComboBoxModel = new DefaultComboBoxModel<>(nouvelleListeBavards.toArray(new String[0]));
                // Définir le nouveau DefaultComboBoxModel sur la JComboBox
                listeBavardsComboBox.setModel(nouveauListeBavardsComboBoxModel);

                // Redessiner la JComboBox
                //listeBavardsComboBox.repaint();
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

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        bavard.add(new JLabel("Bavards connectés : "), gbc);  

        
        //Créer un DefaultComboBoxModel avec la liste des bavards connectés
        DefaultComboBoxModel<String> listeBavardsConnecteComboBoxModel = new DefaultComboBoxModel<>(listeBavardsConnecte.toArray(new String[0]));
        // Créer la JComboBox avec le DefaultComboBoxModel
        listeBavardsConnecteComboBox = new JComboBox<>();
        listeBavardsConnecteComboBox.setModel(listeBavardsConnecteComboBoxModel);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.gridwidth = 2; // Occupe 2 colonnes
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
        gbc.gridwidth = 2; // Occupe 2 colonnes
        JTextField bavardSujetEnvoye = new JTextField(10);
        bavard.add(bavardSujetEnvoye, gbc);

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

        // Creer une liste des messages que le bavard reçoit
        DefaultListModel<String> bavardSubjectsModel = new DefaultListModel<>();
        String nomBavardConnecte = (String) listeBavardsConnecteComboBox.getSelectedItem(); 
        for (Bavard unBavard : baraque.listBavards) {
            if (unBavard.getNom() == nomBavardConnecte) {
                for (PapotageEvent potin : unBavard.listPapotages) {
                    bavardSubjectsModel.addElement(potin.sujet);
                }
            }
        }

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

        
        // Quand on change de bavard courant
        listeBavardsConnecteComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                // MAJ des sujets
                // Créer un nouveau DefaultListModel avec la nouvelle liste des sujets du bavard courant
                DefaultListModel<String> currentNewBavardSubjectsModel = new DefaultListModel<>();
                String nomBavardConnecte = (String) listeBavardsConnecteComboBox.getSelectedItem(); 
                for (Bavard unBavard : baraque.listBavards) {
                    if (unBavard.getNom().equals(nomBavardConnecte)) {
                        //System.out.println(unBavard.getNom());
                        for (PapotageEvent potin : unBavard.listPapotages) {
                            //System.out.println(potin.sujet);
                            currentNewBavardSubjectsModel.addElement(potin.sujet);
                        }
                    }
                }
                //System.out.println("changement de bavard courant");
                // Définir le nouveau DefaultListModel sur la JList
                bavardSubjectList.setModel(currentNewBavardSubjectsModel);
                //JOptionPane.showMessageDialog(null," créé avec succès");
                bavardSubjectList.repaint();


                
            }
        });

        // Quand on sélectionne un sujet

        // créer une JList à partir de l'ArrayList
        bavardSubjectList.setModel(bavardSubjectsModel);
        bavardSubjectList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        bavardSubjectList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String bavardSelectedSubject = bavardSubjectList.getSelectedValue();
                    System.out.println("Sujet sélectionné : " + bavardSelectedSubject);
                    
                    for (Bavard unBavard : baraque.listBavards) {
                        if (unBavard.getNom() == nomBavardConnecte) {
                            for (PapotageEvent potin : unBavard.listPapotages) {
                                if (potin.sujet == bavardSelectedSubject) {
                                    bavardReadTextArea.setText(potin.corps);
                                }
                            }
                        }
                    }
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
        

        
        
        // Définir la taille maximale des cases du GridBagLayout
        Dimension bavardMaxSize = new Dimension(200, 30);
        for (Component component : bavard.getComponents()) { // Utiliser connexion au lieu de creation
            component.setMaximumSize(bavardMaxSize);
        }

/////////////////////////////////////////////////////////////////////////////////////////////////////:
        // Ajouter un écouteur d'événements au bouton pour envoyer un message
        BavardSendBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent arg0) {
                // Récup nom du bavard
                String nomBavardConnecte = (String) listeBavardsConnecteComboBox.getSelectedItem();
                //System.out.println("ca marche");

                // Envoie du msg par le bavard courant au concierge
                for (Bavard unBavard : baraque.listBavards) {
                    if (unBavard.getNom() == nomBavardConnecte) {
                        unBavard.sendPotin(bavardSujetEnvoye.getText(),persoTextArea.getText() );
                        //System.out.println("msg envoyé au concierge");
                    }
                }

                // Créer une nouvelle liste des bavards connecte
                ArrayList<String> nouvelleListeBavardsConnecte = new ArrayList<>();
                for (Bavard unBavard : baraque.listBavards) {
                    if (unBavard.connecte == true) {
                        nouvelleListeBavardsConnecte.add(unBavard.getNom());
                    }
                }

                
                // MAJ des sujets de l'onglet bavard
                // Créer un nouveau DefaultListModel avec la nouvelle liste des sujets
                DefaultListModel<String> newBavardSubjectsModel = new DefaultListModel<>();
                for (Bavard unBavard : baraque.listBavards) {
                    if (unBavard.getNom() == nomBavardConnecte) {
                        for (PapotageEvent potin : unBavard.listPapotages) {
                            newBavardSubjectsModel.addElement(potin.sujet);
                        }
                    }
                }

                // Définir le nouveau DefaultListModel sur la JList
                bavardSubjectList.setModel(newBavardSubjectsModel);
                //JOptionPane.showMessageDialog(null," créé avec succès");


                // MAJ des sujets de l'onglet concierge
                // Créer un nouveau DefaultListModel avec la nouvelle liste des sujets
                DefaultListModel<String> newConciergeSubjectsModel = new DefaultListModel<>();
                for (Bavard unBavard : baraque.listBavards) {
                    if (unBavard.getNom() == nomBavardConnecte) {
                        for (PapotageEvent potin : unBavard.listPapotages) {
                            newConciergeSubjectsModel.addElement(potin.sujet);
                        }
                    }
                }

                // Définir le nouveau DefaultListModel sur la JList
                conciergeSubjectList.setModel(newConciergeSubjectsModel);
                //JOptionPane.showMessageDialog(null," créé avec succès");

            }
        });
        

        // Partie historique des connexions/deconnexions

        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.EAST;
        bavard.add(new JLabel("Historique des connexions: "), gbc); 

        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        bavard.add(new JLabel("Historique des déconnexions : "), gbc); 

        // Creer une liste des logs de co que reçoivent les bavards connectés
        DefaultListModel<String> logConnexionListModel = new DefaultListModel<>();
        // Définir le premier DefaultListModel sur la JList
        logConnexionList.setModel(logConnexionListModel);

        // Creer une liste des logs de deco que reçoivent les bavards connectés
        DefaultListModel<String> logDeconnexionListModel = new DefaultListModel<>();
        // Définir le premier DefaultListModel sur la JList
        logDeconnexionList.setModel(logDeconnexionListModel);
        

        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Occupe 1 colonnes
        gbc.gridheight = 5; // Occupe 3 lignes
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0; // Ajoute du poids en hauteur
        JScrollPane logConnexionScrollPane = new JScrollPane(logConnexionList);
        bavard.add(logConnexionScrollPane, gbc);

        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.gridwidth = 5; // Occupe 1 colonnes
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0; // Ajoute du poids en hauteur
        JScrollPane logDeconnexionScrollPane = new JScrollPane(logDeconnexionList);
        bavard.add(logDeconnexionScrollPane, gbc);

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
        listeBavardsComboBox = new JComboBox<>();
        listeBavardsComboBox.setModel(listeBavardsComboBoxModel);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.EAST;
        connexion.add(listeBavardsComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        gbc.anchor = GridBagConstraints.WEST;
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
        gbc.anchor = GridBagConstraints.EAST;
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
                
                // Changement de la connexion
                for (Bavard personne : baraque.listBavards) {
                    if (personne.getNom().equals(nomBavard)) {
                        personne.setConnecte(connexionChoisi);
                        JOptionPane.showMessageDialog(null, nomBavard + " a changé sa connexion");

                        // Diffusion du signal de connexion à tous les bavards
                        if (connexionChoisi) {
                            for (Bavard unBavard : baraque.listBavards) {
                                personne.signalConnexion(unBavard);
                            }
                        } else {
                            for (Bavard unBavard : baraque.listBavards) {
                                personne.signalDeconnexion(unBavard);
                            }
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


                // MAJ des logs de connexion
                // Créer un nouveau DefaultListModel avec la nouvelle liste des logs
                DefaultListModel<String> newLogConnexionListModel = new DefaultListModel<>();
                for (Bavard unBavard : baraque.listBavards) {
                    if (unBavard.getNom().equals(nomBavardConnecte)) {
                        for (OnLineBavardEvent signalCo : unBavard.listOnLine) {
                            newLogConnexionListModel.addElement(signalCo.envoyeur.getNom() + " : " + signalCo.currentTime);
                        }
                    }
                }

                // Définir le nouveau DefaultListModel sur la JList
                logConnexionList.setModel(newLogConnexionListModel);
            }   
        });
        



        // Définir la taille maximale des cases du GridBagLayout
        Dimension connexionMaxSize = new Dimension(200, 50);
        for (Component component : connexion.getComponents()) { // Utiliser connexion au lieu de creation
            component.setMaximumSize(connexionMaxSize);
        }


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

        

        
        JTextArea conciergeReadTextArea = new JTextArea("initialisation");

        // créer une JList à partir de l'ArrayList
        conciergeSubjectList = new JList<>(conciergeSubjects.toArray(new String[0]));
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
        JScrollPane conciergeScrollPane = new JScrollPane(conciergeSubjectList);
        concierge.add(conciergeScrollPane, gbc);
        
        

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
        tabbedPane.addTab("Bavard", bavard);
        tabbedPane.addTab("Connexion", connexion);
        tabbedPane.addTab("Concierge", concierge);
    

        // Ajouter le JTabbedPane à la JFrame
        add(tabbedPane, BorderLayout.CENTER);
    
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(baraque.nom);
        setSize(600, 500);
        setLocationRelativeTo(null);
    }
    
}
