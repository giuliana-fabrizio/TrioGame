package controller;

import java.util.*;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
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
        gameView.getPlayerViews().sort(Comparator.comparingInt(pv -> pv.getController().getPlayerPriority()));

        int posPlayer = game.getCurrentPlayerPosition();
        gameView.refresh(posPlayer);
    }

    public void setPlayersPriority(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            Player player = game.getPlayers().get(i);
            player.setPriority(list.get(i));
        }
    }

    public void restartGame() {
        game.restartGame();
        gameView.refresh(game.getCurrentPlayerPosition());
    }

    public void quitGame() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(gameView);
        if (frame != null) {
            frame.dispose();
        }
    }

    public ArrayList<Player> getGamePlayers() {
        return game.getPlayers();
    }

    public Table getGameTable() {
        return game.getTable();
    }

    public String getWinner() {
        return game.verifyWinner();
    }

    public void setCardCounter() {
        Card.resetCounter();
    }
}
