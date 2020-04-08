/**
 * SYST 17796 Project Winter 2020 Base code.
 * Students can modify and extend to implement their game.
 * Add your name as a modifier and the date!
 */
package syst17796_projectstartercode;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A concrete class that represents any grouping of cards for a Game. HINT, you might want to subclass this more than
 * once. The group of cards has a maximum size attribute which is flexible for reuse.
 *
 * @author dancye
 * @modified Paul Bonenfant Jan 2020
 * @author jesse thompson
 */
public class GroupOfCards {

    //The group of cards, stored in an ArrayList
    private ArrayList<Card> cards;
    private int size;//the size of the grouping

    public GroupOfCards(int size) {
        this.size = size;
        this.cards = this.generateCards();
    }
    
    // Will generate a 52 card deck iteratively
    public ArrayList<Card> generateCards() {
        ArrayList<Card> cards = new ArrayList<Card>();
        for (int i = 0; i < this.getSize()/13; i++) { // For each suit in deck size (e.g 52/13 = 4 iterations)
            Suits suit = Suits.values()[i];
            for (int j = 0; j < this.getSize()/4; j++) { // For each value (e.g 13)
                Values val = Values.values()[j];
                Card card = new Card(val, suit);
                cards.add(card);
            }
        }
        return cards;
    }

    // Will get all cards from group of cards
    public ArrayList<Card> getCards() {
        return cards;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    // Get size
    public int getSize() {
        return size;
    }
    
    // Checks the integrity of the deck
    public void printAllCards() {
        for (Card card : this.cards) {
            System.out.println(card);
        }
        System.out.println("There are " + this.cards.size() + " cards in this deck");
    }

}//end class
