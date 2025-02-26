

import java.io.Serializable;
/**
 * The player class
 * @author Jamaine Christian
 * @version 2.0
 */
public class Player implements Serializable, Comparable<Player> {
    private String name;
    private final int score;

    /**
     * Constructor for the player
     * @param name sets the players name
     * @param score sets the players score
     */

    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * method for retrieving name
     * @return name
     */

    public String getName() {
        return name;
    }

    /**
     * method for setting the name
     * @param name sets name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * constructor for getting the score
     * @return score
     */
    public int getScore() {
        return score;
    }

    /**
     * method to turn Player into a String
     * @return string
     */
    @Override
    public String toString() {
        return  name + " Score: " + score;
    }

    /**
     * method to compare high score
     * @param o the object to be compared.
     * @return sorted objects
     */
    public int compareTo(Player o) {
        return -Integer.compare(this.score,o.score);
    }
}
