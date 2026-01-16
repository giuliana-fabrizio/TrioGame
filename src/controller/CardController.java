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
    private Timer timer;


    public CardController(Card card, CardView cardView, Game game, GameView gameView) {
        this.card = card;
        this.cardView = cardView;
        this.game = game;
        this.gameView = gameView;
    }


    public void revertCard() {
        if (!game.isSelectionCardLocked()) return;

        card.getOwner().revertCard(card);
        boolean result = game.addCardReturn(card);

        cardView.setImageIcon();

        if (result && game.getCardReturn().size() > 1) {
            game.lockCardSelection();

            if (timer != null && timer.isRunning()) {
                timer.stop();
            }

            timer = new Timer(500, e -> {
                int posPlayer = game.getCurrentPlayerPosition();

                TurnResult turnResult = game.verifyReturnedCards();

                if (turnResult == TurnResult.MISMATCH || turnResult == TurnResult.SCORE_GAINED) {
                    game.hideCardReturn();

                    if (game.isTrioRemained()) {
                        posPlayer = game.next();
                        updatePlayersCardsVisibility(posPlayer);
                    } else {
                        this.gameView.displayEndGame(game.verifyWinner());
                    }
                }

                gameView.refresh(posPlayer);

                String winner = game.getWinnerMessage();
                if (winner != null) {
                    this.gameView.displayEndGame(winner);
                }

                game.unlockCardSelection();
            });

            timer.setRepeats(false);
            timer.start();
        }
    }


    private void updatePlayersCardsVisibility(int posPlayer) {
        for (int i = 0; i < game.getPlayers().size(); i++) {
            game.getPlayers().get(i).displayCardList(i == posPlayer);
        }
    }


    public boolean isCardVisible() {
        return card.isVisible();
    }


    public boolean isCardVisiblePlayer() {
        return card.isVisiblePlayer();
    }


    public String getCardImagePath() {
        return card.getType().getImagePath();
    }
}
