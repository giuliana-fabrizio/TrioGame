package view;

import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import controller.GameBuilderController;

public class GameBuilderView extends JPanel {

    private final int MIN_PLAYER = 3;
    private final int MAX_PLAYER = 6;

    private final String NUMBER_BACKGROUND = "assets/Bouton_nombre.png";
    private final int SIZE_NUMBER = 60;

    private GameBuilderController builderController;
    private JPanel gridPanel;
    private JButton button;

    private ArrayList<JTextField> playerFields;
    private ArrayList<String> playerNameList;


    public GameBuilderView(GlobalView view) {
        this.builderController = new GameBuilderController(this, view);

        playerFields = new ArrayList<>();
        playerNameList = new ArrayList<String>();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(300, 150, 80, 150));

        init();
    }


    public void init() {
        JLabel label = new JLabel("Configuration du jeu", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 30));
        label.setForeground(Color.decode("#F3E3C1"));
        label.setOpaque(false);
        label.setAlignmentX(CENTER_ALIGNMENT);

        JPanel numberPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        numberPanel.setOpaque(false);
        numberPanel.setMaximumSize(new Dimension(600, 80));

        JLabel numberLabel = new JLabel("Nombre de joueur");
        numberLabel.setFont(new Font("Arial", Font.BOLD, 18));
        numberLabel.setForeground(Color.decode("#F3E3C1"));
        numberLabel.setOpaque(false);

        numberPanel.add(numberLabel);


        Image image = new ImageIcon(NUMBER_BACKGROUND).getImage();
        ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(SIZE_NUMBER, SIZE_NUMBER, java.awt.Image.SCALE_SMOOTH));


        for (int i = MIN_PLAYER; i <= MAX_PLAYER; i++) {
            JButton button = drawNumberButton(i, imageIcon);
            numberPanel.add(button);
        }

        gridPanel = new JPanel();
        gridPanel.setLayout(new BoxLayout(gridPanel, BoxLayout.Y_AXIS));
        gridPanel.setOpaque(false);
        gridPanel.setAlignmentX(CENTER_ALIGNMENT);

        button = new JButton("Confirmer");
        // button.setIcon(imageIcon);

        button.setPreferredSize(new Dimension(200, 50));
        button.setMaximumSize(new Dimension(200, 50));
        button.setForeground(Color.decode("#F3E3C1"));
        button.setFocusable(false);
        button.setAlignmentX(CENTER_ALIGNMENT);

        button.setFont(new Font("Arial", Font.BOLD, 24));
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.CENTER);

        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        button.addActionListener(e -> confirmConfiguration());

        button.setVisible(false);

        add(label);
        add(Box.createRigidArea(new Dimension(0, 15)));
        add(numberPanel);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(gridPanel);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(button);
        add(Box.createVerticalGlue()); 
    }


    public void askPlayerNames(int NUMBER_IMAGES) {
        gridPanel.removeAll();
        playerFields.clear();

        for (int i = 0; i < NUMBER_IMAGES; i++) {
            drawPlayerInput(i);
        }

        button.setVisible(true);

        gridPanel.revalidate();
        gridPanel.repaint();
    }


    public JButton drawNumberButton(int i, ImageIcon imageIcon) {
        JButton button = new JButton(String.valueOf(i));
        button.setIcon(imageIcon);

        button.setPreferredSize(new Dimension(SIZE_NUMBER, SIZE_NUMBER));
        button.setForeground(Color.decode("#F3E3C1"));
        button.setFocusable(false);

        button.setFont(new Font("Arial", Font.BOLD, 24));
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.CENTER);

        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        button.addActionListener(e -> builderController.confirmPlayersNumber(i));

        return button;
    }


    public void drawPlayerInput(int i) {
        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 2)); 
        playerPanel.setOpaque(false);
        playerPanel.setMaximumSize(new Dimension(500, 35)); 
        playerPanel.setAlignmentX(CENTER_ALIGNMENT);

        JLabel playerLabel = new JLabel("Nom du " + (i + 1) + "ème joueur");
        playerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        playerLabel.setForeground(Color.decode("#F3E3C1"));
        playerLabel.setOpaque(false);
        playerLabel.setPreferredSize(new Dimension(180, 25));

        JTextField nameField = new JTextField(12);
        nameField.setBackground(Color.decode("#F3E3C1"));
        nameField.setFont(new Font("Arial", Font.PLAIN, 14));
        nameField.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#8B5A2B"), 2),
                new EmptyBorder(3, 8, 3, 8)
            )
        );

        playerPanel.add(playerLabel);
        playerPanel.add(nameField);

        gridPanel.add(playerPanel);

        playerFields.add(nameField);
    }


    public void confirmConfiguration() {
        playerNameList.clear();

        for (JTextField field : playerFields) {
            String text = field.getText().trim();
            if (text.isEmpty()) {
                JOptionPane.showMessageDialog(
                    this,
                    "Tous les champs doivent être remplis !",
                    "Erreur",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }
            playerNameList.add(text);
        }
        builderController.confirmConfiguration(playerNameList);
    }
}
