package controller;

import java.util.List;
import java.util.*;
import models.*;
import view.*;

public class GameController {

    private final Game game;
    private final GameView gameView;

    public GameController(Game game, GameView gameView) {
        this.game = game;
        this.gameView = gameView;
    }

    public void confirmPlayersArrangement() {
        game.getPlayers().sort(Comparator.comparingInt(p -> p.getPriority()));
    }

    public void setPlayersPriority(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            Player player = game.getPlayers().get(i);
            player.setPriority(list.get(i));
        }
    }
}
