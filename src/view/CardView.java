package view;

import java.awt.*;
import javax.swing.*;
import controller.CardController;
import models.*;

public class CardView extends JButton {

    private Card card;
    private CardController cardController;
    private ImageIcon imageIcon;

    public static final int height = 130; 
    public static final int width = 90;

    public CardView(Card card, Game game, GameView gameView) {
        super();

        this.card = card;
        cardController = new CardController(this.card, this, game, gameView);

        setPreferredSize(new Dimension(width, height));
        setFocusable(false);
        setBorder(null);
        setContentAreaFilled(false);

        addActionListener(e -> cardController.revertCard());

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

        setIcon(imageIcon);
    }
}
