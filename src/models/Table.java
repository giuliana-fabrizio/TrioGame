package models;

public class Table  extends CardHolder {

    public Table() {
        super();
    }

    public void removeCard(int index) {
        if (index >= 0 && index < cardList.size()) {
            Card card = cardList.get(index);
            super.removeCard(card);
        }
    }
}
