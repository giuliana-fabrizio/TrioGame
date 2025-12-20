import java.util.*;

import controller.*;
import models.*;
import view.*;


public class Main {
    public static void main(String[] args) {
        Game game = new Game();

        ArrayList<String> playerNameList = new ArrayList<String>();
        playerNameList.add("Jean");
        playerNameList.add("Pierre");
        playerNameList.add("Marie");
        playerNameList.add("DeLatour");
        // playerNameList.add("François");
        // playerNameList.add("Girard");

        game.initParty(playerNameList);

        GameView gameView = new GameView(game, "Jeu du Trio");
        gameView.init();
        gameView.display();
    }
}
