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

        if (result && game.getCardReturn().size() > 1) {
            Timer timer = new Timer(2000, e -> {
                int posPlayer = game.getCurrentPlayerPosition();

                if (!game.verifyReturnedCards()) {
                    game.hideCardReturn();
                    posPlayer = game.next();
                }
                gameView.refresh(posPlayer);
            });

            timer.setRepeats(false);
            timer.start();
        }
    }
}
