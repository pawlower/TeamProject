/**
 * SYST 17796 Project Winter 2020 Base code.
 * Students can modify and extend to implement their game.
 * Add your name as a modifier and the date!
 */
package syst17796_projectstartercode;

import java.util.ArrayList;
import java.util.Random;

/**
 * The class that models your game. You should create a more specific child of this class and instantiate the methods
 * given.
 *
 * @author dancye
 * @author jesse thompson https://github.com/lovgrandma
 * @modified Paul Bonenfant Jan 2020
 */
public class Game {

    private final String name = "Blackjack";//the title of the game
    private GroupOfCards deck; // Deck of cards currently playing with
    private ArrayList<Player> players;// the players of the game

    public Game(ArrayList<Player> players) {
        this.players = players;
        GroupOfCards a = new GroupOfCards(52);
        this.deck = a;
    }

    // Return game name
    public String getName() {
        return name;
    }

    // Get all players
    public ArrayList<Player> getPlayers() {
        return players;
    }

    // Set players in game
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    // Print state of all players in game
    public void printPlayerState() {
        for (Player player : this.players) {
            System.out.println(player.toString());
        }
    }
    
    public GroupOfCards getDeck() {
        return this.deck;
    }
    
    // Begin the game
    public void play() {
        // Serve each player two cards from deck
        for (Player player : this.getPlayers()) {
            serveRandomCard(player);
            serveRandomCard(player);
            for (int i = 0; i < player.getHand().size(); i++) {
                System.out.println(player.getName() + " has a " + player.getHand().get(i));
            }
        }
        System.out.println(this.getDeck().getSize());
    }

    // serves the passed player a card. Reduces size of deck by 1 and increases player hand size by 1
    public void serveRandomCard(Player player) {
        // Retrieve a random card based on the current deck size
        int random = (int) Math.floor((Math.random() * ((this.deck.getSize() - 1) + 1)));
        player.recieveCard(this.deck.getCard(random));
        this.deck.removeCard(random);
    }
    
    /**
     * When the game is over, use this method to declare and display a winning player.
     */
    public void declareWinner() {
        
    }

}//end class
