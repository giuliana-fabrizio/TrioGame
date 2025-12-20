package view;

import java.util.List;
import java.awt.*;
import javax.swing.*;
import controller.GameController;
import models.*;

public class GameView extends JPanel {

    private final int sideWidth = 450;
    private Game game;
    private GameController gameController;


    public GameView(Game game) {
        this.game = game;
        this.gameController = new GameController(this.game, this);

        setLayout(new BorderLayout(5, 5));
        setOpaque(false);

        init();
    }


    public void init() {
        TableView tableView = new TableView(game, game.getTable());
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


        JPanel westPanel  = new JPanel(new FlowLayout());
        westPanel.setOpaque(false);
        JPanel westWrapper = new JPanel(new GridBagLayout());
        westWrapper.setPreferredSize(new Dimension(sideWidth, 0));
        westWrapper.setOpaque(false);
        westWrapper.add(westPanel);
        add(westWrapper, BorderLayout.WEST);


        JPanel eastPanel  = new JPanel(new FlowLayout());
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
        int size = game.getPlayers().size();
        int index = 0;

        if (size >= 3) {
            north.add(new PlayerView(game, game.getPlayers().get(index++), "top-bottom"));
            south.add(new PlayerView(game, game.getPlayers().get(index++), "top-bottom"));
            west.add(new PlayerView(game, game.getPlayers().get(index++), "onside"));
        }

        if (size >= 4) {
            east.add(new PlayerView(game, game.getPlayers().get(index++), "onside"));

            gameController.setPlayersPriority(List.of(1, 3, 4, 2));
        }

        if (size >= 5) {
            south.add(new PlayerView(game, game.getPlayers().get(index++), "top-bottom"));

            gameController.setPlayersPriority(List.of(1, 4, 5, 2, 3));
        }

        if (size >= 6) {
            north.add(new PlayerView(game, game.getPlayers().get(index++), "top-bottom"));
            gameController.setPlayersPriority(List.of(1, 5, 6, 3, 4, 2));
        }

        gameController.confirmPlayersArrangement();
    }
}
