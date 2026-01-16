package view;

import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import controller.PlayerController;

public class PlayerView extends JPanel {

    private PlayerController playerController;
    private ArrayList<CardView> cardViews;
    private String direction;

    private JLabel nameLabel;
    private JPanel cardsPanel;

    private static final Color ACTIVE_COLOR = Color.decode("#FFD700");
    private static final Color NORMAL_COLOR = Color.decode("#F3E3C1");

    public PlayerView(String direction) {
        this.direction = direction;

        if ("top".equals(direction)) {
            System.out.println("TATATAT\n");
            setBorder(BorderFactory.createEmptyBorder(75, 0, 0, 0));
        }

        setLayout(new BorderLayout(5, 5));
        setOpaque(false);

        nameLabel = new JLabel("", SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 30));
        nameLabel.setOpaque(false);
        add(nameLabel, BorderLayout.NORTH);

        cardsPanel = new JPanel();
        cardsPanel.setLayout(new FlowLayout());
        cardsPanel.setOpaque(false);
        add(cardsPanel, BorderLayout.CENTER);
    }

    public void displayCardList() {
        cardsPanel.removeAll();

        int size = cardViews.size();
        int columns = (int) Math.ceil(Math.sqrt(size));
        int rows = (int) Math.ceil((double) size / columns);

        if ("onside".equals(direction)) {
            cardsPanel.setPreferredSize(new Dimension(400, rows * (CardView.height + 5)));
        }

        for (CardView cardView : cardViews) {
            cardView.setImageIcon();
            cardsPanel.add(cardView);
        }

        cardsPanel.revalidate();
        cardsPanel.repaint();
    }

    public void setNameLabel(boolean isActive) {
        if (isActive) {
            nameLabel.setForeground(ACTIVE_COLOR);
            nameLabel.setText(">> " + playerController.displayPlayerInfos() + " <<");
        } else {
            nameLabel.setForeground(NORMAL_COLOR);
            nameLabel.setText(playerController.displayPlayerInfos());
        }

        nameLabel.revalidate();
        nameLabel.repaint();
    }

    public void refresh(boolean isActive) {
        displayCardList();
        setNameLabel(isActive);
    }

    public PlayerController getController() {
        return playerController;
    }

    public void setController(PlayerController playerController) {
        this.playerController = playerController;
    }


    public void setCardView(ArrayList<CardView> cardViews) {
        this.cardViews = cardViews;
    }
}