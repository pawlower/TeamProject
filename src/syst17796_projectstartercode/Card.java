/**
 * SYST 17796 Project Winter 2020 Base code.
 * Students can modify and extend to implement their game.
 * Add your name as a modifier and the date!
 */
package syst17796_projectstartercode;

/**
 * A class to be used as the base Card class for the project. Must be general enough to be instantiated for any Card
 * game. Students wishing to add to the code should remember to add themselves as a modifier.
 *
 * @author dancye
 * @author jesse thompson
 */
public class Card {
    private Values value;
    private Suits suit;
    
    Card(Values value, Suits suit) {
        this.value = value;
        this.suit = suit;
    }
   
    public Values getValue() {
        return this.value;
    }

    public Suits getSuit() {
        return this.suit;
    }

    public String toString() {
        return this.value.getDispName() + " of " + this.suit.getDispName();
    }

}
