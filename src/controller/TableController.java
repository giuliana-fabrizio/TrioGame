package controller;

import javax.swing.Timer;
import java.util.*;
import models.*;
import view.*;

public class TableController {

    private Game game;
    private GameView gameView;
    private TableView tableView;

    public TableController(Game game, GameView gameView, TableView tableView) {
        this.game = game;
        this.gameView = gameView;
        this.tableView = tableView;
    }

    public void revertCard(Card card) {
        game.getTable().revertCard(card);
        boolean result = game.addCardReturn(card);

        tableView.refresh();

        if (result) {
            Timer timer = new Timer(2000, e -> {
                if (!game.verifyReturnedCards()) {
                    game.hideCardReturn();
                    int posPlayer = game.next();
                    gameView.refreshPlayerName(posPlayer);
                }
                tableView.refresh();
            });

            timer.setRepeats(false);
            timer.start();
        }
    }
}
