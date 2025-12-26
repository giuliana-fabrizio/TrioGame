package controller;

import java.util.*;
import models.*;
import view.*;

public class AppController {

    private final GlobalView view;


    public AppController() {
        view = new GlobalView("Trio");
    }


    public void confirmConfiguration(ArrayList<String> playerNameList) {
        Game game = new Game();
        game.initParty(playerNameList);


        // Create GameView
        GameView gameView = new GameView();
        GameController gameController = new GameController(game, gameView);
        gameView.setController(gameController);


        // Create TableView
        TableView tableView = new TableView();
        TableController tableController = new TableController(tableView, game, gameView);
        tableController.createCardView();
        tableView.setTableController(tableController);

        gameView.setTableView(tableView);
        gameView.displayTableView();


        // Create PlayerView
        int currentPlayer = game.getCurrentPlayerPosition();
        ArrayList<PlayerView> playerViews = new ArrayList<>();
        List<String> directions = List.of("top-bottom", "top-bottom", "onside", "onside", "top-bottom", "top-bottom");

        for (int i = 0; i < game.getPlayers().size(); i++) {
            Player player = game.getPlayers().get(i);
            PlayerView playerView = new PlayerView(directions.get(i));

            PlayerController playerController = new PlayerController(player, playerView, game, gameView);
            playerView.setController(playerController);
            playerController.createCardView(i == currentPlayer);
            playerViews.add(playerView);
        }

        gameView.setPlayerViews(playerViews);
        gameView.placePlayers();


        // Display view
        view.displayGame(gameView);
    }

    public void start() {
        GameBuilderView builderView = new GameBuilderView(this);

        view.buildGame(builderView);
        view.display();
    }
}
