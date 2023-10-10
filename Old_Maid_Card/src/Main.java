import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of players : ");
        int N = scanner.nextInt();

        Game game = new Game(N);
        Thread[] threads = new Thread[N];
        game.startGame();

        for (int i = 0; i < N; i++) {
            threads[i] = new Thread(new Play(i, game));
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\n");
        int order = 1;
        for (int winner : game.getWinners()) {
            System.out.println("Winner " + order++ + " --> Player " + winner);
        }
    }
}

class Play implements Runnable {
    private static int currentThreadId = 0;
    private static final Object lock = new Object();
    private final int id;
    static Game game;

    public Play(int id, Game game) {
        this.id = id;
        Play.game = game;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                while (game.getPlayers().get(id) != null && id != currentThreadId) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (game.getPlayers().get(id) != null) {

                    System.out.println("\nPlayer " + (id + 1) + " is playing..");
                    System.out.println(game.turn(id));

                    currentThreadId = game.nextPlayerID(currentThreadId);
                    lock.notifyAll();

                    if (game.endGame()) {
                        break;
                    }

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    break;
                }
            }
        }
    }
}
