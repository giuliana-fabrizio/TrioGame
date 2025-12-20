package controller;

import javax.swing.Timer;
import java.util.*;
import models.*;
import view.*;

public class PlayerController {

    private Game game;
    private Player player;
    private PlayerView playerView;

    public PlayerController(Game game, Player player, PlayerView playerView) {
        this.game = game;
        this.player = player;
        this.playerView = playerView;
    }

    public void revertCard(Card card) {
        game.getTable().revertCard(card);
        boolean result = game.addCardReturn(card);

        playerView.refresh();

        if (result) {
            Timer timer = new Timer(2000, e -> {
                if (!game.verifyReturnedCards()) {
                    game.hideCardReturn();
                    game.next();
                }
                playerView.refresh();
            });

            timer.setRepeats(false);
            timer.start();
        }
    }
}
