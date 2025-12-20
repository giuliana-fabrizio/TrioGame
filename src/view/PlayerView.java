package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import controller.*;
import models.*;

public class PlayerView extends JPanel {

    private PlayerController playerController;
    private Game game;
    private Player player;

    private String direction;
    private JLabel nameLabel;
    private JPanel cardsPanel;


    public PlayerView(Game game, Player player, String direction) {
        this.game = game;
        this.player = player;
        this.playerController = new PlayerController(this.game, this.player, this);
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
        nameLabel.setForeground(Color.decode("#F3E3C1"));
        nameLabel.setOpaque(false);

        add(nameLabel, BorderLayout.NORTH);

        cardsPanel = new JPanel();
        cardsPanel.setLayout(new FlowLayout());
        cardsPanel.setOpaque(false);

        displayCardList();
    }


    public void displayCardList() {
        cardsPanel.removeAll();

        int size = player.getCardList().size();
        int columns = (int) Math.ceil(Math.sqrt(size));
        int rows = (int) Math.ceil((double) size / columns);

        if ("onside".equals(direction)) {
            cardsPanel.setPreferredSize(new Dimension(400, rows * (CardView.height + 5)));
        }

        for (Card card : player.getCardList()) {
            JButton tile = new JButton();

            tile.setPreferredSize(new Dimension(CardView.width, CardView.height));
            tile.setFocusable(false);
            tile.setBorder(null);
            tile.setContentAreaFilled(false);

            CardView cardView = new CardView(card);
            tile.setIcon(cardView.getImageIcon());

            tile.addActionListener(e -> System.out.println("Card : " + card.getId()));
            cardsPanel.add(tile);
        }

        add(cardsPanel, BorderLayout.CENTER);

        cardsPanel.revalidate();
        cardsPanel.repaint();
    }


    public void setNameLabel() {
        nameLabel.setText(
            player.getName() + " - " + String.valueOf(player.getScore())
        );
    }


    public void refresh() {
        displayCardList();
    }
}
