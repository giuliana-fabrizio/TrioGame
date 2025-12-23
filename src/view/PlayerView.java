package view;

import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import models.*;

public class PlayerView extends JPanel {

    private Game game;
    private Player player;
    private GameView gameView;

    private String direction;
    private JLabel nameLabel;
    private JPanel cardsPanel;
    private boolean isCurrentPlayer = false;

    private static final Color ACTIVE_COLOR = Color.decode("#FFD700");
    private static final Color NORMAL_COLOR = Color.decode("#F3E3C1");

    public PlayerView(Game game, GameView gameView, Player player, String direction) {
        this.game = game;
        this.gameView = gameView;
        this.player = player;
        this.direction = direction;
        init();
    }

    private void init() {
        if ("top-bottom".equals(direction)) {
            setBorder(BorderFactory.createEmptyBorder(75, 0, 0, 0));
        }

        setLayout(new BorderLayout(5, 5));
        setOpaque(false);

        nameLabel = new JLabel(
                player.getName() + " - " + String.valueOf(player.getScore()),
                SwingConstants.CENTER
        );
        nameLabel.setFont(new Font("Arial", Font.BOLD, 30));
        nameLabel.setForeground(NORMAL_COLOR);
        nameLabel.setOpaque(false);

        add(nameLabel, BorderLayout.NORTH);

        cardsPanel = new JPanel();
        cardsPanel.setLayout(new FlowLayout());
        cardsPanel.setOpaque(false);

        displayCardList();
    }

    public void displayCardList() {
        cardsPanel.removeAll();
        ArrayList<Card> cardList = player.getCardList();

        int size = cardList.size();
        int columns = (int) Math.ceil(Math.sqrt(size));
        int rows = (int) Math.ceil((double) size / columns);

        if ("onside".equals(direction)) {
            cardsPanel.setPreferredSize(new Dimension(400, rows * (CardView.height + 5)));
        }


        for (Card card : cardList) {
            CardView cardView = new CardView(card, game, gameView);
            cardsPanel.add(cardView);
        }

        add(cardsPanel, BorderLayout.CENTER);

        cardsPanel.revalidate();
        cardsPanel.repaint();
    }

    public void setCurrentPlayer(boolean isActive) {
        this.isCurrentPlayer = isActive;

        if (isActive) {
            nameLabel.setForeground(ACTIVE_COLOR);
            nameLabel.setText(">> " + player.getName() + " - " + player.getScore() + " <<");
        } else {
            nameLabel.setForeground(NORMAL_COLOR);
            nameLabel.setText(player.getName() + " - " + player.getScore());
        }

        nameLabel.revalidate();
        nameLabel.repaint();
    }

    public void refresh(boolean isActive) {
        displayCardList();
        setCurrentPlayer(isActive);
    }

    public Player getPlayer() {
        return player;
    }
}