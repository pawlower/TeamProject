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

    public GroupOfCards(int size) {
        this.cards = this.generateCards(size);
    }
    
    // Will generate a 52 card deck iteratively and then shuffle deck. 
    // Will only function if running at initialization
    private ArrayList<Card> generateCards(int size) {
        ArrayList<Card> cards = new ArrayList<Card>(size);
        for (int i = 0; i < size/13; i++) { // For each suit in deck size (e.g 52/13 = 4 iterations)
            Suits suit = Suits.values()[i];
            for (int j = 0; j < size/4; j++) { // For each value (e.g 13)
                Values val = Values.values()[j];
                Card card = new Card(val, suit);
                cards.add(card);
            }
        }
        return this.shuffle(cards);
    }

    public ArrayList<Card> shuffle(ArrayList<Card> cards) {
        ArrayList<Card> shuffled = new ArrayList<Card>();
        do {
            // Random value simulating picking up random card, add to shuffled, remove from generated
            int random = (int) Math.floor((Math.random() * ((cards.size() - 1) + 1)));
            shuffled.add(cards.get(random));
            cards.remove(random);
        } while (cards.size() > 0);
        // Can print all cards to ensure deck integrity
//        int i = 0;
//        for (Card card : shuffled) {
//            System.out.println(i++);
//            System.out.println(card);
//        }
        return shuffled;
    }
    // Will get all cards from group of cards
    public ArrayList<Card> getCards() {
        return cards;
    }

    public Card getCard(int v) {
        return this.getCards().get(v);
    }
    
    // Removes a specific card from the deck. Most likely because it is being given to another player
    public void removeCard(int v) {
        this.cards.remove(v);
    }
    public void shuffle() {
        Collections.shuffle(cards);
    }

    // Get current size of deck
    public int getSize() {
        return this.getCards().size();
    }
    
    // Checks the integrity of the deck
    public void printAllCards() {
        for (Card card : this.cards) {
            System.out.println(card);
        }
        System.out.println("There are " + this.cards.size() + " cards in this deck");
    }

}//end class
