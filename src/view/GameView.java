package view;

import java.util.*;
import java.util.List;
import java.awt.*;
import javax.swing.*;
import controller.GameController;
import models.*;

public class GameView extends JPanel {

    private final int sideWidth = 450;
    private Game game;
    private GameController gameController;
    private ArrayList<PlayerView> playerViews;

    public GameView(Game game) {
        this.game = game;
        this.gameController = new GameController(this.game, this);
        playerViews = new ArrayList<PlayerView>();

        setLayout(new BorderLayout(5, 5));
        setOpaque(false);

        init();
    }


    public void init() {
        TableView tableView = new TableView(game, gameController.getGameTable(), this);
        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setOpaque(false);
        centerWrapper.add(tableView);
        add(centerWrapper, BorderLayout.CENTER);


        JPanel northPanel = new JPanel(new GridLayout(1, 2));
        northPanel.setPreferredSize(new Dimension(0, CardView.height * 2));
        northPanel.setOpaque(false);
        add(northPanel, BorderLayout.NORTH);


        JPanel southPanel = new JPanel(new GridLayout(1, 2));
        southPanel.setPreferredSize(new Dimension(0, CardView.height * 2));
        southPanel.setOpaque(false);
        add(southPanel, BorderLayout.SOUTH);

        JPanel westPanel = new JPanel(new FlowLayout());
        westPanel.setOpaque(false);
        JPanel westWrapper = new JPanel(new GridBagLayout());
        westWrapper.setPreferredSize(new Dimension(sideWidth, 0));
        westWrapper.setOpaque(false);
        westWrapper.add(westPanel);
        add(westWrapper, BorderLayout.WEST);

        JPanel eastPanel = new JPanel(new FlowLayout());
        eastPanel.setOpaque(false);
        JPanel eastWrapper = new JPanel(new GridBagLayout());
        eastWrapper.setPreferredSize(new Dimension(sideWidth, 0));
        eastWrapper.setOpaque(false);
        eastWrapper.add(eastPanel);
        add(eastWrapper, BorderLayout.EAST);

        placePlayers(northPanel, southPanel, westPanel, eastPanel);
    }

    private void placePlayers(
        JPanel north,
        JPanel south,
        JPanel west,
        JPanel east
    ) {
        int size = gameController.getGamePlayers().size();
        int index = 0;

        if (size >= 3) {
            PlayerView pv1 = new PlayerView(game, gameController.getGamePlayers().get(index++), "top-bottom");
            PlayerView pv2 = new PlayerView(game, gameController.getGamePlayers().get(index++), "top-bottom");
            PlayerView pv3 = new PlayerView(game, gameController.getGamePlayers().get(index++), "onside");
            north.add(pv1);
            south.add(pv2);
            west.add(pv3);
            playerViews.add(pv1);
            playerViews.add(pv2);
            playerViews.add(pv3);
        }

        if (size >= 4) {
            PlayerView pv4 = new PlayerView(game, gameController.getGamePlayers().get(index++), "onside");
            east.add(pv4);
            playerViews.add(pv4);

            gameController.setPlayersPriority(List.of(1, 3, 4, 2));
        }

        if (size >= 5) {
            PlayerView pv5 = new PlayerView(game, gameController.getGamePlayers().get(index++), "top-bottom");
            south.add(pv5);
            playerViews.add(pv5);

            gameController.setPlayersPriority(List.of(1, 4, 5, 2, 3));
        }

        if (size >= 6) {
            PlayerView pv6 = new PlayerView(game, gameController.getGamePlayers().get(index++), "top-bottom");
            north.add(pv6);
            playerViews.add(pv6);
            gameController.setPlayersPriority(List.of(1, 5, 6, 3, 4, 2));
        }

        gameController.confirmPlayersArrangement();
    }

    public void refreshPlayerName(int posPlayer) {
        for (int i = 0; i < playerViews.size(); i++) {
            PlayerView pv = playerViews.get(i);

            if (i == posPlayer) {
                pv.setCurrentPlayer(true);
            } else {
                pv.setCurrentPlayer(false);
            }
        }
    }

    public void sortPlayerViews() {
        playerViews.sort(Comparator.comparingInt(pv -> pv.getPlayer().getPriority()));
    }

    public ArrayList<PlayerView> getPlayerViews() {
        return playerViews;
    }
}
