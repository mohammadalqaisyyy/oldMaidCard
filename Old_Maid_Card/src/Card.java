import java.util.Objects;

public class Card {
    static enum Type {HEART, DIAMOND, CLUBS, SPADE, JOKER}

    static enum Color {RED, BLACK, NONE}

    private final Type type;
    private Color color;
    private final char value;

    public Card(Type type, char value) {
        this.type = type;
        this.value = value;
        setColor();
    }

    private void setColor() {
        if (type == Type.CLUBS || type == Type.SPADE)
            this.color = Color.BLACK;
        else if (type == Type.DIAMOND || type == Type.HEART)
            this.color = Color.RED;
        else
            this.color = Color.NONE;
    }

    public char getValue() {
        return value;
    }

    public Color getColor() {
        return color;
    }

    public Type getType() {
        return type;
    }


    @Override
    public String toString() {
        return value + "." + type.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return value == card.value && color == card.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, type);
    }
}
