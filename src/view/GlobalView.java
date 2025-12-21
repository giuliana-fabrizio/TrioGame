package view;

import java.util.List;
import java.awt.*;
import javax.swing.*;
import controller.GameBuilderController;
import models.*;

public class GlobalView extends JFrame {

    private final int height = 1080;
    private final int width = 1920;
    private final String configurationImage = "assets/Configuration.png";
    private final String gameBoardImage = "assets/Plateau.png";


    public GlobalView(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setResizable(true);
        setSize(width, height);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }


    public void buildGame() {
        BackgroundPanel background = new BackgroundPanel(configurationImage, height, width);
        background.setLayout(new BorderLayout());

        background.add(new GameBuilderView(this), BorderLayout.CENTER);
        setContentPane(background);
    }


    public void displayGame(Game game) {
        BackgroundPanel background = new BackgroundPanel(gameBoardImage, height, width);
        background.setLayout(new BorderLayout());

        background.add(new GameView(game), BorderLayout.CENTER);

        setContentPane(background);

        revalidate();
        repaint();
    }


    public void display() {
        setVisible(true);
    }
}
