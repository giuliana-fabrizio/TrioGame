package models;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Collections;



public class Game {

    private static final int NUMBER_CARDS = 36;
    private final Card[] cards;
    private final ArrayList<Card> cardReturned;
    private boolean canSelectCard = true;
    private final Table table;
    private final ArrayList<Player> players;
    private int currentPlayerPosition;
    private Player winner;

    public Game() {
        cards = new Card[NUMBER_CARDS];
        cardReturned = new ArrayList<>(3);
        table = new Table();
        players = new ArrayList<Player>();
    }

    public void initParty(ArrayList<String> playerNameList) {
        players.clear();
        for (String name : playerNameList) {
            players.add(new Player(name));
        }

        cardReturned.clear();
        currentPlayerPosition = 0;
        winner = null;

        createCardPacks();
        distributeCards();

        players.get(currentPlayerPosition).displayCardList(true);
    }

    private void createCardPacks() {
        int idCard = 0;
        TypeStatutUTBM[] types = TypeStatutUTBM.values();

        for (TypeStatutUTBM type : types) {
            for (int j = 0; j < 3; j++) {
                cards[idCard] = new Card(type);
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
            player.getModifiableCardList().clear();
            for (int i = 0; i < nbCardsPlayer; i++) {
                player.addCard(cards[index++]);
            }
            player.sortCardList();
        }

        table.getModifiableCardList().clear();
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

    public TurnResult verifyReturnedCards() {
        for (int i = 0; i < cardReturned.size() - 1; i++) {
            Card card1 = cardReturned.get(i);
            Card card2 = cardReturned.get(i + 1);
            if (card1.getType() != card2.getType() || card1.getId() == card2.getId()) {
                return TurnResult.MISMATCH;
            }
        }

        if (cardReturned.size() == 3) {
            Player currentPlayer = players.get(currentPlayerPosition);

            TypeStatutUTBM typeTrio = cardReturned.getFirst().getType();

            removeCards();
            currentPlayer.incrementScore();

            System.out.println("Trio VALIDÉ ! Score de " + currentPlayer.getName() + " : " + currentPlayer.getScore());

            if (typeTrio == TypeStatutUTBM.TYPE_7) {
                winner = currentPlayer;
                System.out.println("!!! TRIO DORÉ !!! Victoire immédiate de " + currentPlayer.getName());
            } else if (currentPlayer.getScore() >= 3) {
                winner = currentPlayer;
            }

            return TurnResult.SCORE_GAINED;
        }
        return TurnResult.MATCH;
    }

    public boolean isTrioRemained() {
        Map<TypeStatutUTBM, Integer> cardAvailables = new EnumMap<>(TypeStatutUTBM.class);

        for (Player player : players) {
            List<Card> playerCards = player.getCardList();
            if (playerCards.isEmpty()) continue;

            cardAvailables.merge(playerCards.getFirst().getType(), 1, Integer::sum);

            if (playerCards.size() > 1) {
                cardAvailables.merge(playerCards.getLast().getType(), 1, Integer::sum);
            }
        }

        for (Card card : table.getCardList()) {
            cardAvailables.merge(card.getType(), 1, Integer::sum);
        }

        for (int count : cardAvailables.values()) {
            if (count >= 3) {
                return true;
            }
        }
        return false;
    }

    public String verifyWinner() {
        if (winner != null) {
            return "Victoire par TRIO DORÉ : " + winner.getName();
        }

        if (players.isEmpty()) return "Aucun player";

        int scoreMax = players.stream()
                .mapToInt(Player::getScore)
                .max()
                .orElse(0);

        List<String> winners = players.stream()
                .filter(j -> j.getScore() == scoreMax)
                .map(Player::getName)
                .collect(Collectors.toList());

        if (winners.size() > 1) {
            return "Égalité entre : " + String.join(", ", winners);
        }

        return winners.isEmpty() ? "Aucun gagnant" : winners.getFirst();
    }

    public int next() {
        if (players.isEmpty()) return 0;
        currentPlayerPosition = (currentPlayerPosition + 1) % players.size();
        return currentPlayerPosition;
    }

    public void restartGame() {
        Card.resetCounter();
        cardReturned.clear();
        currentPlayerPosition = 0;
        winner = null;

        for (Player player : players) {
            player.resetScore();
        }

        createCardPacks();
        distributeCards();

        players.get(currentPlayerPosition).displayCardList(true);
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

    public String getWinnerMessage() {
        if (winner == null) return null;

        if (winner.getScore() >= 3) {
            return "VICTOIRE AUX POINTS !<br>" + winner.getName() + " a validé 3 Trios.";
        } else {
            return "VICTOIRE LÉGENDAIRE !<br>" + winner.getName() + " a trouvé le Trio Doré (7).";
        }
    }

    public boolean isSelectionCardLocked() {
        return canSelectCard;
    }

    public boolean addCardReturn(Card card) {
        if (cardReturned.contains(card)) return false;
        cardReturned.add(card);
        return true;
    }

    public void removeCards() {
        for (Card card : cardReturned) {
            if (card.getOwner() != null) {
                card.getOwner().removeCard(card);
            }
        }
        cardReturned.clear();
    }

    public void setCurrentPlayerPosition(int currentPlayerPosition) {
        this.currentPlayerPosition = currentPlayerPosition;
    }

    public void lockCardSelection() {
        canSelectCard = false;
    }

    public void unlockCardSelection() {
        canSelectCard = true;
    }
}
