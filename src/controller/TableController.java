package controller;

import java.util.ArrayList;

import models.Card;
import models.Game;
import view.CardView;
import view.GameView;
import view.TableView;


public class TableController {

    private final TableView tableView;
    private Game game;
    private GameView gameView;


    public TableController(TableView tableView, Game game, GameView gameView) {
        this.tableView = tableView;
        this.game = game;
        this.gameView = gameView;
    }


    public void createCardView() {
        ArrayList<CardView> cardViews = new ArrayList<>();

        for (Card card : game.getTable().getCardList()) {
            CardView cardView = new CardView(true);

            CardController cardController = new CardController(card, cardView, game, gameView);
            cardView.setController(cardController);

            cardViews.add(cardView);
        }

        tableView.setCardView(cardViews);
        tableView.refresh();
    }
}
