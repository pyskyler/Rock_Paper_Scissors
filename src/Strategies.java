import javax.annotation.processing.ProcessingEnvironment;
import java.util.ArrayList;
import java.util.Random;

public class Strategies {
    final static private int ROCK = 0;
    final static private int PAPER = 1;
    final static private int SCISSORS = 2;

    /**
     * Creates ArrayList of all the Strategies that can be used by the computer player.
     */
    public static Strategy returnRandomStrategy() {
        Random random = new Random();
        double randValue = random.nextDouble();
        if (randValue < 0.2) {
            return new leastUsed();
        } else if (randValue < 0.4) {
            return new mostUsed();
        } else if (randValue < 0.6) {
            return new lastUsed();
        } else if (randValue < 0.8) {
            return new randomMove();
        } else {
            return new cheat();
        }
    }

    /**
     * Returns a random strategy with using a given percentage of cheating.
     * @param cheatPercentage the percentage of cheating between 0 and 1
     * @return a random Strategy
     */
    public static Strategy returnRandomStrategy(double cheatPercentage) {
        Random random = new Random();
        double randValue = random.nextDouble();
        double percentageIncrement = (1 - cheatPercentage) / 4;
        if (randValue < percentageIncrement) {
            return new leastUsed();
        } else if (randValue < 2*percentageIncrement) {
            return new mostUsed();
        } else if (randValue < 3*percentageIncrement) {
            return new lastUsed();
        } else if (randValue < 4*percentageIncrement) {
            return new randomMove();
        } else {
            return new cheat();
        }
    }

    /**
     * This Strategy returns the move that has been played the least amount of times.
     */
    public static class leastUsed implements Strategy {

        /**
         * Determines the move that the computer player will make.
         * @param playerMove the move that the player made
         * @param playerMoves the moves that the player has made
         * @return the move that the computer player will make
         */
        @Override
        public int DetermineMove(int playerMove, ArrayList<Integer> playerMoves) {
            if (playerMoves.isEmpty()) {
                Random random = new Random();
                return random.nextInt(3);
            }
            return tallyMoves(playerMoves, true);
        }

        /**
         * Returns the name of the Strategy.
         * @return the name of the Strategy
         */
        @Override
        public String getName() {
            return "Least Used";
        }

    }

    /**
     * This Strategy returns the move that has been played the most amount of times.
     */
    public static class mostUsed implements Strategy {

        /**
         * Determines the move that the computer player will make.
         * @param playerMove the move that the player made
         * @param playerMoves the moves that the player has made
         * @return the move that the computer player will make
         */
        @Override
        public int DetermineMove(int playerMove, ArrayList<Integer> playerMoves) {
            if (playerMoves.isEmpty()) {
                Random random = new Random();
                return random.nextInt(3);
            }
            return tallyMoves(playerMoves, false);
        }

        /**
         * Returns the name of the Strategy.
         * @return the name of the Strategy
         */
        @Override
        public String getName() {
            return "Most Used";
        }

    }

    /**
     * This Strategy returns the move that the player played last.
     */
    public static class lastUsed implements Strategy {

        /**
         * Determines the move that the computer player will make.
         * @param playerMove the move that the player made
         * @param playerMoves the moves that the player has made
         * @return the move that the computer player will make
         */
        @Override
        public int DetermineMove(int playerMove, ArrayList<Integer> playerMoves) {
            if (playerMoves.isEmpty()) {
                Random random = new Random();
                return random.nextInt(3);
            }
            return playerMoves.getLast();
        }

        /**
         * Returns the name of the Strategy.
         * @return the name of the Strategy
         */
        @Override
        public String getName() {
            return "Last Used";
        }

    }

    /**
     * This Strategy returns a random move.
     */
    public static class randomMove implements Strategy {

        /**
         * Determines the move that the computer player will make.
         * @param playerMove the move that the player made
         * @param playerMoves the moves that the player has made
         * @return the move that the computer player will make
         */
        @Override
        public int DetermineMove(int playerMove, ArrayList<Integer> playerMoves) {
            Random random = new Random();
            return random.nextInt(3);
        }

        /**
         * Returns the name of the Strategy.
         * @return the name of the Strategy
         */
        @Override
        public String getName() {
            return "Random Move";
        }

    }

    /**
     * This Strategy returns the move that beats the player's move.
     */
    public static class cheat implements Strategy {

        /**
         * Determines the move that the computer player will make.
         * @param playerMove the move that the player made
         * @param playerMoves the moves that the player has made
         * @return the move that the computer player will make
         */
        @Override
        public int DetermineMove(int playerMove, ArrayList<Integer> playerMoves) {
            if (playerMove == ROCK) {
                return PAPER;
            } else if (playerMove == PAPER) {
                return SCISSORS;
            } else {
                return ROCK;
            }
        }

        /**
         * Returns the name of the Strategy.
         * @return the name of the Strategy
         */
        @Override
        public String getName() {
            return "Cheat";
        }

    }


    private static int tallyMoves(ArrayList<Integer> playerMoves, boolean returnLeastPlayed) {
        int rockCount = 0;
        int paperCount = 0;
        int scissorsCount = 0;

        for (int move : playerMoves) {
            if (move == ROCK) {
                rockCount++;
            } else if (move == PAPER) {
                paperCount++;
            } else {
                scissorsCount++;
            }
        }

        if (returnLeastPlayed) {
            if (rockCount <= paperCount && rockCount <= scissorsCount) {
                return ROCK;
            } else if (paperCount <= rockCount && paperCount <= scissorsCount) {
                return PAPER;
            } else {
                return SCISSORS;
            }
        } else {
            if (rockCount >= paperCount && rockCount >= scissorsCount) {
                return ROCK;
            } else if (paperCount >= rockCount && paperCount >= scissorsCount) {
                return PAPER;
            } else {
                return SCISSORS;
            }
        }
    }
}
