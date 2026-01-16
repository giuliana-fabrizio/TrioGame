package view;

import java.util.List;
import java.awt.*;
import javax.swing.*;

public class GlobalView extends JFrame {

    private final String configurationImage = "assets/Configuration.png";
    private final String gameBoardImage = "assets/Plateau.png";


    public GlobalView(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setResizable(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }


    public void buildGame(GameBuilderView builderView) {
        BackgroundPanel background = new BackgroundPanel(configurationImage);
        background.setLayout(new BorderLayout());

        background.add(builderView, BorderLayout.CENTER);
        setContentPane(background);
    }


    public void displayGame(GameView gameView) {
        BackgroundPanel background = new BackgroundPanel(gameBoardImage);
        background.setLayout(new BorderLayout());

        background.add(gameView, BorderLayout.CENTER);

        setContentPane(background);

        revalidate();
        repaint();
    }


    public void display() {
        setVisible(true);
    }
}
