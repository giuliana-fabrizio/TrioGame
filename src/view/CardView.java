package view;

import java.awt.*;
import javax.swing.*;
import models.Card;

public class CardView {

    private Card card;
    private ImageIcon imageIcon;

    public static final int height = 130; 
    public static final int width = 90;

    public CardView(Card card) {
        this.card = card;

        setImageIcon();
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon() {
        Image image;

        if (card.isVisible()) {
            image = new ImageIcon(getClass().getResource(card.getType().getImagePath())).getImage();
        } else {
            image = new ImageIcon(getClass().getResource("/assets/Dos.png")).getImage();
        }

        imageIcon = new ImageIcon(image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH));
    }
}
