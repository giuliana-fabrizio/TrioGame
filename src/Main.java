import view.GlobalView;

public class Main {
    public static void main(String[] args) {
        GlobalView view = new GlobalView("Jeu du Trio");
        view.buildGame();
        view.display();
    }
}
