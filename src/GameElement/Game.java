// Ori Kenigsbuch 206594590
package GameElement;

import GameObjects.Collidable;
import GameObjects.Sprite;
import GameObjects.SpriteCollection;
import Geometry.Point;
import Geometry.Rectangle;
import Listeners.BallRemover;
import Listeners.BlockRemover;
import Listeners.ScoreTrackingListener;

import biuoop.Sleeper;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.Random;

/**
 * The GameElement.GameElement.Game class represents the main class of the game.
 * It manages the game environment, sprites, and runs the game animation loop.
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private biuoop.GUI gui;
    private Counter blockCounter;
    private Counter ballCounter;
    private Counter score;

    /**
     * Constructs a new GameElement.GameElement.Game with an empty GameObjects.SpriteCollection and a new GameElement.GameElement.GameEnvironment.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment =  new GameEnvironment();
        this.blockCounter = new Counter(42);
        this.ballCounter = new Counter(3);
        this.score = new Counter(0);
    }
    /**
     * Adds a collidable object to the game environment.
     *
     * @param c The collidable object to be added.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }
    /**
     * Adds a sprite object to the game sprites.
     *
     * @param s The sprite object to be added.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);

    }

    /**
     * Initializes the game by creating the GUI and adding blocks, balls, and a paddle.
     */
    public void initialize() {
        this.gui = new biuoop.GUI("", 800, 600);
        Random random = new Random();
        double speed = 3;
        double angle =  (0.8) * 360;
        Velocity v = Velocity.fromAngleAndSpeed(angle, speed);


        //balls
        Ball b = new Ball(100, 400, 7, Color.white, environment, this);
        Ball b1 = new Ball(120, 420, 7, Color.white, environment, this);
        Ball b2 = new Ball(130, 350, 7, Color.white, environment, this);
        b.setVelocity(v);
        b1.setVelocity(v);
        b2.setVelocity(v);
        b.addToGame(this);
        b1.addToGame(this);
        b2.addToGame(this);
        //listeners
        BlockRemover blockRemover = new BlockRemover(this, blockCounter);
        BallRemover ballRemover = new BallRemover(this, ballCounter);
        ScoreTrackingListener scoreListener = new ScoreTrackingListener(score);
        ScoreIndicator indicator = new ScoreIndicator(score);
        indicator.addToGame(this);
        double x, y;


        for (int i = 0; i < 6; i++) {
            Color color = generateRandColor();
            for (int j = 0; j < 6 * 2 - i; j++) {
                double xVal = this.environment.getGuiWidth() - ((j + 1) * 50 + 20);
                double yVal = (i + 3) * 20 + 20 + 3;
                Rectangle rectangle = new Rectangle(new Point(xVal, yVal), 50, 20);
                Block block = new Block(rectangle, color, this);
                block.addHitListener(blockRemover);
                block.addHitListener(scoreListener);
                block.addToGame();
            }
        }
        //paddle
        Paddle p = new Paddle(new Block(new Rectangle(new Point(25, 560), 120, 20), Color.blue, this), gui, environment);
        p.addToGame(this);

        //borders:
        //left
        Rectangle left = new Rectangle(new Point(0, 0), 20, 600);
        Block leftB = new Block(left, Color.orange, this);
        leftB.addToGame();
        //down
        Rectangle down = new Rectangle(new Point(0, 580), 800, 20);
        Block downB = new Block(down, Color.orange, this);
        downB.addToGame();

        //deathRegion
        Rectangle deathRegion = new Rectangle(new Point(0, 579), 800, 20);
        Block deathRegionB = new Block(deathRegion, Color.orange, this);
        deathRegionB.addToGame();
        deathRegionB.addHitListener(ballRemover);

        //up
        Rectangle up = new Rectangle(new Point(20, 35), 800, 15);
        Block upB = new Block(up, Color.orange, this);
        upB.addToGame();
        //right
        Rectangle right = new Rectangle(new Point(780, 0), 20, 600);
        Block rightB = new Block(right, Color.orange, this);
        rightB.addToGame();
    }

    /**
     * Generates and adds the game background.
     */
    public void generateBackground() {
        Rectangle rectangle = new Rectangle(new Point(20, 20), 760, 580);
        Block background = new Block(rectangle, Color.blue, this);
        background.addToGame();
    }

    /**
     * Generates and adds the boundary blocks around the game area.
     */
    public void generateBounds() {
        BallRemover ballRemover = new BallRemover(this, ballCounter);
        //left
        createBound(new Point(0, 0), 20, 600);
        //up
        createBound(new Point(0, 0), 800, 20);
        //right
        createBound(new Point(780, 0), 20, 600);
        //down
        createBound(new Point(0, 580), 800, 20);
        //deathRegion
        Rectangle deathRegion = new Rectangle(new Point(0, 579), 800,20);
        Block deathRegionB = new Block(deathRegion, Color.blue, this);
        deathRegionB.addToGame();
        deathRegionB.addHitListener(ballRemover);
    }

    /**
     * Creates and adds a single boundary block to the game.
     *
     * @param position the top-left position of the block
     * @param w the width of the block
     * @param h the height of the block
     */
    private void createBound(Point position, double w, double h) {
        Rectangle rectangle = new Rectangle(position, w, h);
        Block bound = new Block(rectangle, Color.gray, this);
        bound.addToGame();
    }

    /**
     * Generates and adds the paddle to the game.
     *
     * @param boundH the height of the bounds
     * @param paddleW the width of the paddle
     * @param paddleH the height of the paddle
     */
    public void generatePaddle(int boundH, int paddleW, int paddleH) {
        Point topLeft = new Point(400, 565);
        Rectangle paddleRect = new Rectangle(topLeft, paddleW, paddleH);
        Paddle p = new Paddle(new Block(paddleRect, Color.orange, this), this.gui, this.environment);
        p.addToGame(this);
    }


    /**
     * Generates and adds a pattern of blocks to the game.
     *
     * @param numOfRows the number of rows of blocks
     * @param blockW the width of each block
     * @param blockH the height of each block
     * @param boundW the width of the bounds
     * @param boundH the height of the bounds
     */
    public void generateBlocks(int numOfRows, int blockW, int blockH, int boundW, int boundH) {
        BlockRemover blockRemover = new BlockRemover(this, blockCounter);
        ScoreTrackingListener scoreListener = new ScoreTrackingListener(score);
        for (int i = 0; i < numOfRows; i++) {
            Color color = generateRandColor();
            for (int j = 0; j < numOfRows * 2 - i; j++) {
                double xVal = this.environment.getGuiWidth() - ((j + 1) * blockW + 20);
                double yVal = (i + 3) * blockH + blockH + 3;
                Rectangle rectangle = new Rectangle(new Point(xVal, yVal), blockW, blockH);
                Block block = new Block(rectangle, color, this);
                block.addHitListener(blockRemover);
                block.addHitListener(scoreListener);
                block.addToGame();
            }
        }
    }

    /**
     * Generates and adds two balls to the game with random velocities.
     */
    public void generateBalls() {
        Random random = new Random();
        double speed = 4;
        double angle = (random.nextDouble()) * 360;
        Velocity v = Velocity.fromAngleAndSpeed(angle, speed);

        Ball ball1 = new Ball(350, 300, 7, Color.white, environment, this);
        ball1.setGameEnvironment(this.environment);
        ball1.addToGame(this);
        Ball ball2 = new Ball(350, 300, 7, Color.white, environment, this);
        ball2.setGameEnvironment(this.environment);
        ball2.addToGame(this);
        Ball ball3 = new Ball(350, 300, 7, Color.white, environment, this);
        ball3.setGameEnvironment(this.environment);
        ball3.addToGame(this);
    }


    /**
     * Runs the game animation loop.
     */
    public void run() {
        biuoop.Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing
            if (blockCounter.getValue() == 0 || ballCounter.getValue() == 0) {
                gui.close();
                if (blockCounter.getValue() == 0) {
                    score.increase(100);
                }
                return;
            }

            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * Generates a random color.
     *
     * @return a {@link Color} with random RGB values
     */
    public Color generateRandColor() {
        Random rnd = new Random();
        int red = rnd.nextInt(256);
        int green = rnd.nextInt(256);
        int blue = rnd.nextInt(256);
        return new Color(red, green, blue);
    }

    /**
     * remove the given collidable object from the game.
     *
     * @param c The collidable object to be removed the environment.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * remove a sprite from the game.
     *
     * @param s The sprite to be removed.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }


}

