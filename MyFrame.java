import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;

public class MyFrame extends JFrame {
    public MyFrame() {
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
        creation.add(new JTextField(10), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        creation.add(new JLabel("Batiment : "), gbc);


        String[] listeBatiments = {"Élément 1", "Élément 2", "Élément 3"};
        JComboBox<String> listeBatimentsComboBox = new JComboBox<>(listeBatiments);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        creation.add(listeBatimentsComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        creation.add(new JLabel("Interet :"), gbc);

        JRadioButton creationOuiButton = new JRadioButton("Oui");
        JRadioButton creationNonButton = new JRadioButton("Non");
        ButtonGroup creationGroup = new ButtonGroup();
        creationGroup.add(creationOuiButton);
        creationGroup.add(creationNonButton);

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
        creation.add(new JButton("Créer"), gbc);

        // Définir la taille maximale des cases du GridBagLayout de creation
        Dimension creationMaxSize = new Dimension(200, 30);
        for (Component component : creation.getComponents()) {
            component.setMaximumSize(creationMaxSize);
        }


        



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
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.EAST;
        connexion.add(new JLabel("Liste des bavards : "), gbc);

        String[] listeBavards = {"Élément 1", "Élément 2", "Élément 3"};
        JComboBox<String> listeBavardsComboBox = new JComboBox<>(listeBavards);

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
        connexion.add(new JButton("Mettre à jour"), gbc);

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
        bavard.add(new JLabel("Envoyer un message ! "));
        bavard.add(new JLabel("Sujet : "));
        bavard.add(new JTextField(10));
        bavard.add(new JLabel("Contenu : "));
        bavard.add(new JTextField(30));
        bavard.add(new JButton("Envoyer"));

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

        JScrollPane bavardSscrollPane = new JScrollPane(bavardSubjectList);
        bavard.add(bavardSscrollPane, BorderLayout.CENTER);
        

        
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
        concierge.add(new JLabel("Messages reçus : "));

        String[] conciergeSubjects = {"Sujet 1", "Sujet 2", "Sujet 3", "Sujet 4", "Sujet 5", "Sujet 6", "Sujet 7", "Sujet 8", "Sujet 9", "Sujet 10"};
        JList<String> conciergeSubjectList = new JList<>(conciergeSubjects);
        conciergeSubjectList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        conciergeSubjectList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        conciergeSubjectList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedSubject = conciergeSubjectList.getSelectedValue();
                    System.out.println("Sujet sélectionné : " + selectedSubject);
                }
            }
        });

        JScrollPane conciergeSscrollPane = new JScrollPane(conciergeSubjectList);
        concierge.add(conciergeSscrollPane, BorderLayout.CENTER);

        // Ajouter les onglets au JTabbedPane
        tabbedPane.addTab("Creation", creation);
        tabbedPane.addTab("Connexion", connexion);
        tabbedPane.addTab("Bavard", bavard);
        tabbedPane.addTab("Concierge", concierge);
    

        // Ajouter le JTabbedPane à la JFrame
        add(tabbedPane, BorderLayout.CENTER);
    
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Interface avec onglets");
        setSize(400, 300);
        setLocationRelativeTo(null);
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MyFrame frame = new MyFrame();
            frame.setVisible(true);
        });
    }
}
