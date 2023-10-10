import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Player {
    ArrayList<Card> cards;
    private final Random rand;
    int level;
    int id;

    public Player(ArrayList<Card> cards, int id) {
        this.cards = cards;
        rand = new Random(5);
        level = 1;
        this.id = id;
    }

    public String discard() {
        for (int i = 0; i < cards.size(); i++) {
            for (int u = i + 1; u < cards.size(); u++) {
                if (cards.get(i).equals(cards.get(u))) {
                    String s = cards.size() + " --------> level (" + level + ") _ "
                            + cards.get(i).toString() + " " + cards.get(u).toString();
                    cards.remove(u);
                    cards.remove(i);
                    return s;
                }
            }
        }
        level = 2;
        return cards.size() + " --------> level (" + level + ")";
    }

    public void pick(Card card) throws IllegalAccessException {
        if (cards.isEmpty())
            throw new IllegalAccessException("You won!!");
        if (level == 2)
            cards.add(card);
        else throw new IllegalCallerException("Not allow to do this now");
    }

    public Card give() {
        if (cards.isEmpty())
            throw new IllegalCallerException("No cards");
        if (level == 2) {
            int x = rand.nextInt(cards.size());
            Card temp = cards.get(x);
            cards.remove(x);
            return temp;
        } else
            throw new IllegalCallerException("Not allow to do this now");
    }

    public int getCardsSize() {
        return cards.size();
    }

    boolean win() {
        return cards.isEmpty();
    }

    public int getLevel() {
        return level;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Player{" +
                ", id=" + id +
                ", level=" + level +
                "cards=" + cards.size() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return level == player.level && id == player.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(level, id);
    }
}
