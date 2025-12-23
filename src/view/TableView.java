package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import models.*;

public class TableView extends JPanel {

    private Game game;
    private Table table;
    private GameView gameView;

    public TableView(Game game, Table table, GameView gameView) {
        this.game = game;
        this.table = table;
        this.gameView = gameView;
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
            CardView cardView = new CardView(card, game, gameView);
            add(cardView);
        }
    }

    public void refresh() {
        removeAll();
        init();
        revalidate();
        repaint();
    }
}
