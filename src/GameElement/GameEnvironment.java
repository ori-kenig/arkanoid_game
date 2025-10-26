// Ori Kenigsbuch
package GameElement;

import GameObjects.Collidable;
import GameObjects.CollisionInfo;
import Geometry.Line;
import Geometry.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * The GameElement.GameEnvironment class represents the environment in which the game objects exist.
 * It manages a collection of collidable objects and provides utilities for detecting collisions
 * and accessing game-related settings such as dimensions and object sizes.
 */
public class GameEnvironment {
        //screen size
        private static final int WIDTH = 800;
        private static final int HEIGHT = 600;
        // Boundaries size
        private static final int BOUND_WIDTH = 10;
        private static final int BOUND_HEIGHT = 10;
        //Blocks size
        private static final int BLOCK_WIDTH = 50;
        private static final int BLOCK_HEIGHT = 20;
        private static final int NUM_OF_ROWS = 6;
        //GameElement.Paddle size
        private static final int PADDLE_WIDTH = 150;
        private static final int PADDLE_HEIGHT = 10;

        private List<Collidable> collidables;

        /**
         * Creates a new GameElement.GameEnvironment with no collidable objects.
         */
        public GameEnvironment() {
                this.collidables = new ArrayList<>();
        }

        /**
         * Adds the given collidable object to the environment.
         *
         * @param c the {@link Collidable} object to add.
         */
        public void addCollidable(Collidable c) {
                collidables.add(c);
        }

        public void removeCollidable(Collidable c) {
                collidables.remove(c);
        }

        /**
         * Retrieves the list of collidable objects in the environment.
         *
         * @return a {@link List} of {@link Collidable} objects.
         */
        public List<Collidable> getCollidables() {
                return collidables;
        }

        /**
         * Retrieves the width of the game GUI.
         *
         * @return the width of the GUI in pixels.
         */
        public int getGuiWidth() {
                return WIDTH;
        }

        /**
         * Retrieves the height of the game GUI.
         *
         * @return the height of the GUI in pixels.
         */
        public int getGuiHeight() {
                return HEIGHT;
        }

        /**
         * Retrieves the width of the game boundaries.
         *
         * @return the width of the boundaries in pixels.
         */
        public int getBoundsWidth() {
                return BOUND_WIDTH;
        }

        /**
         * Retrieves the height of the game boundaries.
         *
         * @return the height of the boundaries in pixels.
         */
        public int getBoundsHeight() {
                return BOUND_HEIGHT;
        }

        /**
         * Retrieves the width of a block in the game.
         *
         * @return the block width in pixels.
         */
        public int getBlockWidth() {
                return BLOCK_WIDTH;
        }

        /**
         * Retrieves the height of a block in the game.
         *
         * @return the block height in pixels.
         */
        public int getBlockHeight() {
                return BLOCK_HEIGHT;
        }

        /**
         * Retrieves the width of the paddle in the game.
         *
         * @return the paddle width in pixels.
         */
        public int getPaddleWidth() {
                return PADDLE_WIDTH;
        }

        /**
         * Retrieves the height of the paddle in the game.
         *
         * @return the paddle height in pixels.
         */
        public int getPaddleHeight() {
                return PADDLE_HEIGHT;
        }

        /**
         * Retrieves the number of rows in the game layout.
         *
         * @return the number of rows.
         */
        public int getNumOfRows() {
                return NUM_OF_ROWS;
        }


        /**
         * Determines the closest collision that will occur along the given trajectory.
         *
         * @param trajectory a {@link Line} representing the trajectory of a moving object.
         * @return a {@link CollisionInfo} object containing information about the closest collision,
         * or {@code null} if no collision occurs.
         */
        public CollisionInfo getClosestCollision(Line trajectory) {
        CollisionInfo collision = null;
        double closest = Double.MAX_VALUE;

        for (Collidable c : collidables) {
        Point intersection = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
                if (intersection != null) {
                      double distance = trajectory.start().distance(intersection);
                      if (distance < closest) {
                              closest = distance;
                              collision = new CollisionInfo(intersection, c);
                      }
                }
        }
        return collision;
        }
}

