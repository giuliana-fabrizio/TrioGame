package controller;

import javax.swing.Timer;
import java.util.*;
import models.*;
import view.*;

public class CardController {

    private Card card;
    private CardView cardView;
    private Game game;
    private GameView gameView;

    public CardController(Card card, CardView cardView, Game game, GameView gameView) {
        this.card = card;
        this.cardView = cardView;
        this.game = game;
        this.gameView = gameView;
    }

    public void revertCard() {
        card.getOwner().revertCard(card);
        boolean result = game.addCardReturn(card);

        cardView.setImageIcon();

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
