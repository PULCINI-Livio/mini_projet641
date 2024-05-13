import javax.swing.*;
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
    
        // Ajout composant pour onglet creation
        creation.add(new JLabel("Nom du bavard : "));
        creation.add(new JLabel("Batiment : "));
        creation.add(new JLabel("Interet :"));
        creation.add(new JTextField(10));
        creation.add(new JButton("Créer"));
        
        JRadioButton creationOuiButton = new JRadioButton("Oui");
        JRadioButton creationNonButton = new JRadioButton("Non");
        ButtonGroup creationGroup = new ButtonGroup();
        creationGroup.add(creationOuiButton);
        creationGroup.add(creationNonButton);
        creation.add(creationOuiButton);
        creation.add(creationNonButton);

        String[] listeBatiments = {"Élément 1", "Élément 2", "Élément 3"};
        JComboBox<String> listeBatimentsComboBox = new JComboBox<>(listeBatiments);
        creation.add(listeBatimentsComboBox);



        // Ajout composant pour onglet connexion
        connexion.add(new JLabel("Liste des bavards : "));
        connexion.add(new JLabel("Connecté : "));

        String[] listeBavards = {"Élément 1", "Élément 2", "Élément 3"};
        JComboBox<String> listeBavardsComboBox = new JComboBox<>(listeBavards);
        connexion.add(listeBavardsComboBox);

        JRadioButton connexionOuiButton = new JRadioButton("Oui");
        JRadioButton connexionNonButton = new JRadioButton("Non");
        ButtonGroup connexionGroup = new ButtonGroup();
        connexionGroup.add(connexionOuiButton);
        connexionGroup.add(connexionNonButton);
        connexion.add(connexionOuiButton);
        connexion.add(connexionNonButton);
        connexion.add(new JButton("Mettre à jour"));


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
        JScrollPane bavardSscrollPane = new JScrollPane(bavardSubjectList);
        bavard.add(bavardSscrollPane, BorderLayout.CENTER);
        

        // Ajout composant pour onglet concierge
        concierge.add(new JLabel("Messages reçus : "));

        String[] conciergeSubjects = {"Sujet 1", "Sujet 2", "Sujet 3", "Sujet 4", "Sujet 5", "Sujet 6", "Sujet 7", "Sujet 8", "Sujet 9", "Sujet 10"};
        JList<String> conciergeSubjectList = new JList<>(conciergeSubjects);
        conciergeSubjectList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
