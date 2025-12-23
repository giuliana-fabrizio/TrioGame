package controller;

import javax.swing.Timer;
import java.util.*;
import models.*;
import view.*;

public class PlayerController {

    private Game game;
    private GameView gameView;
    private Player player;
    private PlayerView playerView;

    public PlayerController(Game game, GameView gameView, Player player, PlayerView playerView) {
        this.game = game;
        this.gameView = gameView;
        this.player = player;
        this.playerView = playerView;
    }

    public void revertCard(Card card) {
        player.revertCard(card);
        boolean result = game.addCardReturn(card);

        playerView.refresh(player.getPriority() == game.getCurrentPlayerPosition());

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

    public ArrayList<Card> getCardList() {
        return player.getCardList();
    }
}
