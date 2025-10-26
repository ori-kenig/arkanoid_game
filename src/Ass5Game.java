// Ori Kenigsbuch
import GameElement.Game;

/**
 * The main class for running the Ass5Game.
 * This class initializes and runs the game.
 */
public class Ass5Game {

    /**
     * The main method serves as the entry point for the program.
     * It initializes and starts the game.
     *
     * @param args command-line arguments (not used in this implementation)
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}

