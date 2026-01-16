package models;

import java.util.ArrayList;
import java.util.Comparator;

public class CardHolder {

    protected ArrayList<Card> cardList;


    public CardHolder() {
        cardList = new ArrayList<>();
    }


    public Card revertCard(Card card) {
        card.setVisible(true);
        return card;
    }


    public ArrayList<Card> getCardList() {
        return new ArrayList<>(cardList);
    }


    public ArrayList<Card> getModifiableCardList() {
        return cardList;
    }


    public void addCard(Card card) {
        card.setOwner(this);
        cardList.add(card);
    }


    public void removeCard(Card card) {
        card.setVisible(false);
        cardList.remove(card);
    }
}
