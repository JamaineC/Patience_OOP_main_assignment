/**
 * The card  class
 * @author Jamaine Christian
 * @version 2.0
 */
public class Card {
    public String suit;
    public String value;


    /**
     * Default constructor
     *
     * @param suit  variable for suit of card
     * @param value variable for what card number/value it is
     */
    public Card(String value, String suit) {
        this.suit = suit;
        this.value = value;
    }

    /**
     * method for retrieving suit
     * @return suit
     */
    public String getSuit() {
        return suit;
    }

    /**
     * method for getting value
     * @return value
     */
    public String getValue() {
        return value;
    }


    /**
     * method for returning Card as a string
     * @return string
     */
    @Override
    public String toString() {
        return value + suit;
    }

}
