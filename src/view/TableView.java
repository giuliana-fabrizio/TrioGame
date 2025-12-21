package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import controller.*;
import models.*;

public class TableView extends JPanel {

    private TableController tableController;
    private Game game;
    private Table table;

    public TableView(Game game, Table table, GameView gameView) {
        this.game = game;
        this.table = table;
        this.tableController = new TableController(this.game, gameView, this);
        init();
    }

    private void init() {
        int size = table.getCardList().size();
        int columns = (int) Math.ceil(Math.sqrt(size));
        int rows = (int) Math.ceil((double) size / columns);

        setLayout(new GridLayout(rows, columns));
        setOpaque(false);

        setPreferredSize(new Dimension(400, rows * (CardView.height + 5)));

        for (Card card : table.getCardList()) {
            JButton tile = new JButton();

            tile.setPreferredSize(new Dimension(CardView.width, CardView.height));
            tile.setFocusable(false);
            tile.setBorder(null);
            tile.setContentAreaFilled(false);

            CardView cardView = new CardView(card);
            tile.setIcon(cardView.getImageIcon());

            tile.addActionListener(e -> tableController.revertCard(card));
            add(tile);
        }
    }

    public void refresh() {
        removeAll();
        init();
        revalidate();
        repaint();
    }
}
