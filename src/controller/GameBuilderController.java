package controller;

import java.util.*;
import models.*;
import view.*;

public class GameBuilderController {

    private final GameBuilderView builderView;
    private GlobalView view;


    public GameBuilderController(GameBuilderView builderView, GlobalView view) {
        this.builderView = builderView;
        this.view = view;
    }


    public void confirmPlayersNumber(int i) {
        builderView.askPlayerNames(i);
    }


    public void confirmConfiguration(ArrayList<String> playerNameList) {
        Game game = new Game();
        game.initParty(playerNameList);

        view.displayGame(game);
    }
}
