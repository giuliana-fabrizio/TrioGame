package view;

import java.awt.*;
import javax.swing.*;
import controller.CardController;
import models.*;

public class CardView extends JButton {

    private CardController cardController;
    private ImageIcon imageIcon;

    public static final int height = 130; 
    public static final int width = 90;

    public CardView(boolean canRevert) {
        super();

        setPreferredSize(new Dimension(width, height));
        setFocusable(false);
        setBorder(null);
        setContentAreaFilled(false);

        if (canRevert) {
            addActionListener(e -> cardController.revertCard());
        }
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public void setController(CardController cardController) {
        this.cardController = cardController;
        setImageIcon();
    }

    public void setImageIcon() {
        Image image;

        if (cardController.isCardVisible()) {
            image = new ImageIcon(getClass().getResource(cardController.getCardImagePath())).getImage();
        } else if (cardController.isCardVisiblePlayer()) {
            image = new ImageIcon(getClass().getResource(cardController.getCardImagePath())).getImage(); // TODO image != ?
        } else {
            image = new ImageIcon(getClass().getResource("/assets/Dos.png")).getImage();
        }

        imageIcon = new ImageIcon(image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH));

        setIcon(imageIcon);
    }
}
