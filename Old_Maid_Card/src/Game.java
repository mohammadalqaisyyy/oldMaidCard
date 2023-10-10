import java.util.ArrayList;

public class Game implements GameRules {
    SuitCards cards;
    ArrayList<Player> players;
    ArrayList<Integer> winners;
    int numberOfPlayers;
    int level;

    public Game(int numberOFPlayers) {
        players = new ArrayList<>();
        cards = new SuitCards();
        winners = new ArrayList<>();
        this.numberOfPlayers = numberOFPlayers;
        level = 1;
    }

    @Override
    public void startGame() throws IllegalAccessException {
        ArrayList<ArrayList<Card>> playerCards = cards.splitCards(numberOfPlayers);
        for (int i = 0; i < numberOfPlayers; i++) {
            Player player = new Player(playerCards.get(i), i);
            players.add(player);
        }
    }

    public String turn(int id) {
        String out;
        if (level == 1) {
            out = players.get(id).discard();
            checkLevel();
        } else {
            try {
                int nextID = nextPlayerID(id);
                Card card = players.get(nextID).give();
                players.get(id).pick(card);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            out = players.get(id).discard();
        }
        check();
        return out;
    }

    @Override
    public void checkLevel() {
        for (int i = 0; i < numberOfPlayers; i++) {
            if (players.get(i) == null)
                continue;
            if (players.get(i).getLevel() == 1)
                return;
        }
        this.level = 2;
    }

    public int nextPlayerID(int id) {
        int nextID = id + 1;
        if (nextID == numberOfPlayers)
            nextID = 0;
        while (players.get(nextID) == null) {
            nextID++;
            if (nextID == numberOfPlayers)
                nextID = 0;
            if (nextID == id)
                break;
        }
        return nextID;
    }

    public void check() {
        for (int i = 0; i < numberOfPlayers; i++) {
            if (players.get(i) == null)
                continue;
            if (players.get(i).win()) {
                players.remove(i);
                players.add(i, null);
                winners.add(i + 1);
            }
        }
    }

    @Override
    public boolean endGame() {
        int loser = 0, loserID = -1;
        for (Player p : players) {
            if (p != null) {
                loser++;
                loserID = p.getId();
            }
        }
        boolean end = loser == 1;
        if (end && players.get(loserID) != null) {
            players.remove(loserID);
            players.add(loserID, null);
            winners.add(loserID + 1);
        }
        return end;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    @Override
    public String toString() {
        return "Game{" + "level=" + level + '}';
    }

    public ArrayList<Integer> getWinners() {
        return winners;
    }

    public int getLevel() {
        return level;
    }
}
