package view;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {

    private Image backgroundImage;
    private int height;
    private int width;

    public BackgroundPanel(String imagePath, int height, int width) {
        backgroundImage = new ImageIcon(imagePath).getImage();
        setLayout(new BorderLayout(5, 5));

        this.height = height;
        this.width = width;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
