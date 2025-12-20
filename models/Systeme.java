package models;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Collections;

public class Systeme {

    private static final int NUMBER_CARDS = 36;
    private final Card[] cards;
    private final ArrayList<Card> cardReturned;
    private final Table table;
    private final ArrayList<Player> players;
    private int currentPlayerPosition;

    private Player winner;

    public Systeme() {
        this.cards = new Card[NUMBER_CARDS];
        this.cardReturned = new ArrayList<>(3);
        this.table = new Table();
        this.players = new ArrayList<Player>();
    }

    public void initParty(ArrayList<String> playerNameList) {
        players.clear();
        for (String name : playerNameList) {
            players.add(new Player(name));
        }

        this.cardReturned.clear();
        this.currentPlayerPosition = 0;
        this.winner = null;

        createCardPacks();
        distributeCards();
    }

    private void createCardPacks() {
        int idCard = 0;
        TypeStatutUTBM[] types = TypeStatutUTBM.values();

        for (TypeStatutUTBM type : types) {
            for (int j = 0; j < 3; j++) {
                this.cards[idCard] = new Card(type);
                idCard++;
            }
        }
    }

    private void distributeCards() {
        shuffleCards();

        int nbPlayers = players.size();

        if (nbPlayers == 0) return;

        int nbCardsPlayer = NUMBER_CARDS / (nbPlayers + 1);
        int nbCardsTable = NUMBER_CARDS - (nbCardsPlayer * nbPlayers);

        int index = 0;

        for (Player player : players) {
            player.getCardList().clear();
            for (int i = 0; i < nbCardsPlayer; i++) {
                player.addCard(cards[index++]);
            }
            player.sortCardList();
        }

        table.getCardList().clear();
        for (int i = 0; i < nbCardsTable; i++) {
            table.addCard(cards[index++]);
        }
    }

    private void shuffleCards() {
        for (int i = cards.length - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            Card temp = cards[i];
            cards[i] = cards[j];
            cards[j] = temp;
        }
    }

    public void hideCardReturn() {
        for (Card card: cardReturned) {
            card.setVisible(false);
        }
        cardReturned.clear();
    }

    public boolean verifyReturnedCards(){
        for(int i = 0; i < this.cardReturned.size() - 1; i++){
            Card card1 = this.cardReturned.get(i);
            Card card2 = this.cardReturned.get(i+1);
            if (card1.getType() != card2.getType() || card1.getId() == card2.getId()){
                return false;
            }
        }

        if (this.cardReturned.size() == 3){
            Player currentPlayer = this.players.get(this.currentPlayerPosition);

            TypeStatutUTBM typeTrio = this.cardReturned.getFirst().getType();

            if (typeTrio == TypeStatutUTBM.TYPE_7) {
                this.winner = currentPlayer;
                System.out.println("!!! TRIO DORÉ !!! Victoire immédiate de " + currentPlayer.getName());
                return true;
            }

            removeCards();

            currentPlayer.incrementScore();

            System.out.println("Trio VALIDÉ ! Score de " + currentPlayer.getName() + " : " + currentPlayer.getScore());
        }
        return true;
    }

    public String verifyWinner() {
        if (this.winner != null) {
            return "Victoire par TRIO DORÉ : " + this.winner.getName();
        }

        if (players.isEmpty()) return "Aucun player";

        int scoreMax = players.stream()
                .mapToInt(Player::getScore)
                .max()
                .orElse(0);

        List<String> gagnants = players.stream()
                .filter(j -> j.getScore() == scoreMax)
                .map(Player::getName)
                .collect(Collectors.toList());

        if (gagnants.size() > 1) {
            return "Égalité entre : " + String.join(", ", gagnants);
        }
        return gagnants.isEmpty() ? "Aucun gagnant" : gagnants.getFirst();
    }

    public int next() {
        if (players.isEmpty()) return 0;
        return (this.currentPlayerPosition + 1) % players.size();
    }

    public Card[] getCards() {
        return cards;
    }

    public List<Card> getCardReturn() {
        return Collections.unmodifiableList(cardReturned);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int getCurrentPlayerPosition() {
        return currentPlayerPosition;
    }

    public Table getTable() {
        return table;
    }

    public boolean addCardReturn(Card card) {
        if (cardReturned.contains(card)) return false;
        cardReturned.add(card);
        return true;
    }

    public void removeCards() {
        for (Card card : cardReturned) {
            if (card.getOwner() != null){
                card.getOwner().removeCard(card);
            }
        }
        cardReturned.clear();
    }

    public void setCurrentPlayerPosition(int currentPlayerPosition) {
        this.currentPlayerPosition = currentPlayerPosition;
    }
}
