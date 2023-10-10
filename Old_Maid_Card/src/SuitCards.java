import java.util.ArrayList;
import java.util.Random;

public class SuitCards {
    private final ArrayList<Card> cards;
    private final Random rand;

    public SuitCards() {
        this.cards = new ArrayList<>();
        rand = new Random(5);
        createSetOfCards();
        shuffleCards();
    }

    void createSetOfCards() {
        for (int i = 0; i < 10; i++)
            cards.add(new Card(Card.Type.CLUBS, Character.forDigit(i, 10)));
        for (int i = 0; i < 10; i++)
            cards.add(new Card(Card.Type.SPADE, Character.forDigit(i, 10)));
        for (int i = 0; i < 10; i++)
            cards.add(new Card(Card.Type.HEART, Character.forDigit(i, 10)));
        for (int i = 0; i < 10; i++)
            cards.add(new Card(Card.Type.DIAMOND, Character.forDigit(i, 10)));

        cards.add(new Card(Card.Type.CLUBS, 'J'));
        cards.add(new Card(Card.Type.SPADE, 'J'));
        cards.add(new Card(Card.Type.HEART, 'J'));
        cards.add(new Card(Card.Type.DIAMOND, 'J'));

        cards.add(new Card(Card.Type.CLUBS, 'Q'));
        cards.add(new Card(Card.Type.SPADE, 'Q'));
        cards.add(new Card(Card.Type.HEART, 'Q'));
        cards.add(new Card(Card.Type.DIAMOND, 'Q'));

        cards.add(new Card(Card.Type.CLUBS, 'K'));
        cards.add(new Card(Card.Type.SPADE, 'K'));
        cards.add(new Card(Card.Type.HEART, 'K'));
        cards.add(new Card(Card.Type.DIAMOND, 'K'));

        cards.add(new Card(Card.Type.JOKER, '0'));
    }

    public ArrayList<ArrayList<Card>> splitCards(int N) throws IllegalAccessException {
        ArrayList<ArrayList<Card>> deck = new ArrayList<>();
        for (int i = 0; i < N; i++)
            deck.add(new ArrayList<>());
        for (int i = 0; !cards.isEmpty(); i++) {
            if (i == N)
                i = 0;
            deck.get(i).add(drawCard());
        }
        return deck;
    }

    public Card drawCard() throws IllegalAccessException {
        if (isEmpty())
            throw new IllegalAccessException("Can not draw card it is empty");
        Card temp = cards.get(0);
        cards.remove(0);
        return temp;
    }

    public boolean isEmpty() {
        return cards.size() == 0;
    }

    public void shuffleCards() {
        for (int i = 0; i < cards.size(); i++) {
            int x = rand.nextInt(cards.size());
            int y = rand.nextInt(cards.size());
            Card temp = cards.get(x);
            cards.set(x, cards.get(y));
            cards.set(y, temp);
        }
    }

    @Override
    public String toString() {
        return Integer.toString(cards.size());
    }
}
