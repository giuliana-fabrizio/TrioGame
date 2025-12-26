package controller;

import java.util.ArrayList;

import models.Card;
import models.Player;
import models.Game;
import view.CardView;
import view.GameView;
import view.PlayerView;


public class PlayerController {

    private final Player player;
    private final PlayerView playerView;
    private Game game;
    private GameView gameView;


    public PlayerController(Player player, PlayerView playerView, Game game, GameView gameView) {
        this.player = player;
        this.playerView = playerView;
        this.game = game;
        this.gameView = gameView;
    }


    public void createCardView(boolean isActive) {
        ArrayList<CardView> cardViews = new ArrayList<>();

        ArrayList<Card> cardList = player.getCardList();
        int size = cardList.size();

        for (Card card : cardList) {
            boolean canRevert = false;
            if(card.equals(cardList.get(0)) || card.equals(cardList.get(size -1))) {
                canRevert = true;
            }

            CardView cardView = new CardView(canRevert);

            CardController cardController = new CardController(card, cardView, game, gameView);
            cardView.setController(cardController);

            cardViews.add(cardView);
        }

        playerView.setCardView(cardViews);
        playerView.refresh(isActive);
    }


    public String displayPlayerInfos() {
        return player.getName() + " - " + player.getScore();
    }


    public int getPlayerPriority() {
        return player.getPriority();
    }
}
