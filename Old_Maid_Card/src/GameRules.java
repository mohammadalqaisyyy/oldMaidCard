public interface GameRules {
    void startGame() throws IllegalAccessException;

    void checkLevel();

    boolean endGame();
}
