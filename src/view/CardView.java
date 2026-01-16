package view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import controller.CardController;
import models.*;

public class CardView extends JButton {

    private CardController cardController;
    private ImageIcon imageIcon;
    private boolean isHovered = false;

    public static final int height = 130; 
    public static final int width = 90;

    public CardView(boolean canRevert) {
        super();

        setPreferredSize(new Dimension(width, height));
        setFocusable(false);
        setBorder(null);
        setContentAreaFilled(false);

        if (canRevert) {
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    isHovered = true;
                    repaint();
                }

                public void mouseExited(MouseEvent e) {
                    isHovered = false;
                    repaint();
                }
            });
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
