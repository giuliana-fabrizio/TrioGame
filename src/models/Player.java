package models;

import java.util.*;

public class Player extends CardHolder
{
    private static int counter = 0;

    private final int id;
    private final String name;
    private int score;
    private int priority;

    public Player(String name) {
        super();
        this.id = ++counter;
        this.name = name;
        this.score = 0;
        this.priority = 0;
    }

    public void displayCardList(boolean isVisible) {
        for (Card card : super.getCardList()) {
            card.setVisiblePlayer(isVisible);
        }
    }

    public void removeCard(boolean isMin) {
        if (super.getCardList().isEmpty()) return;

        try {
            Card card = isMin ? super.getCardList().getFirst() : super.getCardList().getLast();
            super.removeCard(card);
        } catch (NoSuchElementException e) {
            System.err.println(e.getMessage());
        }
    }

    public void sortCardList() {
        cardList.sort(Comparator.comparingInt(c -> c.getType().getNumber()));
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    public int getScore() {
        return score;
    }

    public void incrementScore() {
        this.score++;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
