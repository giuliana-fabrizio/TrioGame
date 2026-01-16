package models;

public class Card {

    public static int counter = 0;

    private final int id;

    private final TypeStatutUTBM type;
    private boolean visible;
    private boolean visiblePlayer;
    private CardHolder owner;

    public Card(TypeStatutUTBM type){
        this.id = ++counter;
        this.type = type;

        this.visible = false;
        this.visiblePlayer = false;

        this.owner = null;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Card card = (Card) obj;
        return type == card.type && id == card.id;
    }

    @Override
    public String toString() {
        return type.getName() + " (ID: " + id + ")";
    }

    public int getId() {
        return id;
    }

    public TypeStatutUTBM getType() {
        return type;
    }

    public CardHolder getOwner() {
        return owner;
    }

    public boolean isVisible() {
        return visible;
    }

    public boolean isVisiblePlayer() {
        return visiblePlayer;
    }

    public void setOwner(CardHolder owner) {
        this.owner = owner;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setVisiblePlayer(boolean visiblePlayer) {
        this.visiblePlayer = visiblePlayer;
    }

    public static void resetCounter() {
        counter = 0;
    }
}
