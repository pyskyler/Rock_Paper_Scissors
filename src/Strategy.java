import java.util.ArrayList;

public interface Strategy {
    
    /**
     * Determines the move that the computer player will make.
     * @param playerMove the move that the player made
     * @param playerMoves the moves that the player has made
     * @return the move that the computer player will make
     */
    public int DetermineMove(int playerMove, ArrayList<Integer> playerMoves);

    /**
     * Returns the name of the Strategy.
     * @return the name of the Strategy
     */
    public String getName();
}
