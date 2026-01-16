package view;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import javax.swing.*;
import controller.GameController;
import models.Player;
import models.Game;

public class GameView extends JPanel {

    private final int sideWidth = 450;
    private boolean isGameEnded = false;

    private GameController gameController;
    private ArrayList<PlayerView> playerViews;
    private TableView tableView;

    private JButton rulesButton;


    public GameView() {
        playerViews = new ArrayList<>();
        init();
    }


    public void init() {
        setLayout(new BorderLayout(5, 5));
        setOpaque(false);
    }

    public void displayTableView() {
        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setOpaque(false);
        centerWrapper.add(tableView);
        add(centerWrapper, BorderLayout.CENTER);
    }

    private List<JPanel> createPlayersPanel() {
        JPanel northPanel = new JPanel(new GridLayout(1, 2));
        northPanel.setPreferredSize(new Dimension(0, CardView.height * 2 + 10));
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

        return List.of(northPanel, southPanel, westPanel, eastPanel, southPanel, northPanel);
    }

    public void placePlayers() {
        List<JPanel> panelList = createPlayersPanel();

        int size = playerViews.size();

        for (int i = 0; i < size; i++) {
            PlayerView pv = playerViews.get(i);
            panelList.get(i).add(pv);
        }

        if (size >= 4) {
            gameController.setPlayersPriority(List.of(0, 2, 3, 1));
        }

        if (size >= 5) {
            gameController.setPlayersPriority(List.of(0, 3, 4, 1, 2));
        }

        if (size >= 6) {
            gameController.setPlayersPriority(List.of(0, 4, 5, 2, 3, 1));
        }

        gameController.confirmPlayersArrangement();
    }

    public void refresh(int posPlayer) {
        gameController.setCardCounter();
        tableView.getController().createCardView();

        for (int i = 0; i < playerViews.size(); i++) {
            PlayerView pv = playerViews.get(i);
            pv.getController().createCardView(i == posPlayer);
        }
    }

    private void restartGame() {
        isGameEnded = false;
        gameController.restartGame();
    }

    private void showRulesPopup() {
        String html = "<html><body style='width: 400px; font-family: Arial; font-size: 11px;'>" +
            "<h2 style='text-align: center; color: #8B4513; margin-top: 0;'>=== RÈGLES : MODE SIMPLE ===</h2>" +

            "<div style='text-align: center; margin-bottom: 10px;'>" +
                "<i>Gagnez la course aux trios avant vos adversaires !</i>" +
            "</div>" +

            "<br><b>COMMENT GAGNER ?</b>" +
            "<br>Il y a deux façons de remporter la victoire immédiatement :" +
            "<ul>" +
                "<li><b style='color: #228B22;'>OPTION A :</b> Avoir <b>3 Trios</b> (n'importe lesquels).</li>" +
                "<li><b style='color: #DAA520;'>OPTION B :</b> Trouver le <b>Trio de 7</b> (Trio Doré).</li>" +
            "</ul>" +

            "<br><b>DÉROULEMENT D'UN TOUR :</b>" +
            "<br>Chaque joueur a ses cartes ordonnées par ordre croissant" +

            "<br>Révélez 3 cartes identiques. Pour ce faire, dévoilez des cartes :" +
            "<ul>" +
                "<li>Du <b>centre de la table</b>.</li>" +
                "<li>De la main d'un adversaire (seulement sa carte <b>MIN</b> ou <b>MAX</b>).</li>" +
                "<li>De votre propre main (votre <b>MIN</b> ou <b>MAX</b>).</li>" +
            "</ul>" +

            "<b>VALIDATION :</b>" +
            "<br>Si deux cartes révélées sont identiques, vous continuez.<br>" +
            "<br>Si les 3 numéros sont identiques, vous gagnez le trio et <b>le tour passe au joueur suivant !</b>" +
            "<br>Si les cartes ne correspondent pas, votre tour s'arrête." +
            "<br><br><b>FIN DE PARTIE :</b>" +
            "<br>La partie termine quand tous les trios sont trouvés ou via le 7 doré, ou quand un joueur arrête la partie."+
            "</body></html>";

        JLabel label = new JLabel(html);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        JOptionPane.showMessageDialog(this, label, "Fin de partie", JOptionPane.PLAIN_MESSAGE);
    }

    public void displayEndGame(String text) {
        if (isGameEnded) return;
        isGameEnded = true;

        String htmlMessage = "<html><div style='text-align: center; width: 300px;'>" +
                "<h1 style='color: #DAA520;'>🏆 PARTIE TERMINÉE 🏆</h1>" +
                "<br><span style='font-size: 14px;'>" + text + "</span>" +
                "<br><br><i>Que voulez-vous faire ?</i></div></html>";

        Object[] options = {"Recommencer", "Quitter"};

        // Affiche le popup
        int choix = JOptionPane.showOptionDialog(
                this,
                new JLabel(htmlMessage, SwingConstants.CENTER),
                "Fin de partie",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]
        );

        if (choix == 0) {
            restartGame();
        } else {
            gameController.quitGame();
        }
    }

    public List<PlayerView> getPlayerViews() {
        return playerViews;
    }

    public void setController(GameController gameController) {
        this.gameController = gameController;
    }

    public void setTableView(TableView tableView) {
        this.tableView = tableView;
    }

    public void setPlayerViews(ArrayList<PlayerView> playerViews) {
        this.playerViews = playerViews;
    }
}
