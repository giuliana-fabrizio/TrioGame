package view;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import controller.TableController;

public class TableView extends JPanel {

    private TableController tableController;
    private List<CardView> cardViews;

    public TableView() {
        cardViews = new ArrayList<>();
    }

    public void refresh() {
        removeAll();

        int size = cardViews.size();
        int columns = (int) Math.ceil(Math.sqrt(size));
        int rows = (int) Math.ceil((double) size / columns);

        setLayout(new GridLayout(rows, columns));
        setOpaque(false);

        setPreferredSize(new Dimension(400, rows * (CardView.height + 5)));

        for (CardView cardView: cardViews) {
            cardView.setImageIcon();
            add(cardView);
        }

        revalidate();
        repaint();
    }

    public TableController getController() {
        return tableController;
    }

    public void setTableController(TableController tableController) {
        this.tableController = tableController;
    }

    public void setCardView(List<CardView> cardViews) {
        this.cardViews = cardViews;
    }
}
