/**
 * SYST 17796 Project Winter 2020 Base code.
 * Students can modify and extend to implement their game.
 * Add your name as a modifier and the date!
 */
package syst17796_projectstartercode;

import java.util.Scanner;
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
        this.deck = new GroupOfCards(52);
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
    public void play(Scanner in) {
        // Serve each player two cards from deck
        for (Player player : this.getPlayers()) {
            serveRandomCard(player);
            serveRandomCard(player);
            for (int i = 0; i < player.getHand().size(); i++) {
                if (player.getName().equals("Croupier") && i == 1) {
                    System.out.println(player.getName() + "'s second card is hidden");
                    System.out.println("Dev check: " + player.getName() + " has a " + player.getHand().get(i));
                } else {
                    System.out.println(player.getName() + " has a " + player.getHand().get(i));
                }
            }
        }
        // Integrity check of deck size
        System.out.println(this.getDeck().getSize());
        // Check values/who lost after first deal
        for (Player player : this.getPlayers()) {
            if (!player.getName().equals("Croupier")) {
                int value = player.getHandValue();
                if (value > 21) {
                    System.out.println("The value of " + player.getName() + "'s hand is " + value + " \n" + 
                    player.getName() + " lost with their bet of $" + player.setLost());
                }
                if (value == 21) { // Blackjack
                    // setWon(true) means true blackjack boolean. Player wins 1.5x
                    System.out.println("Blackjack! " + player.getName() + " won $" + player.setWon(true));
                }
            }
        }
        // Loop through to prompt for hit, stay, lose, win functionality
        for (Player player : this.getPlayers()) {
            // If iterated player is not the dealer and is currently playing
            if (!player.getName().equals("Croupier") && player.getPlayingState()) {
                System.out.println("Croupier: for " + player.getName() + " " + player.getHandValue());
                // player in stay state is set to false until the say stay or hit 21
                boolean stay = false;
                do {
                    System.out.println("Hit? (hit/stay)");
                    String response = in.nextLine();
                    if (response.equals("hit") || response.equals("Hit")) {
                        System.out.println("Croupier: " + this.serveRandomCard(player) + ", " + player.getHandValue());
                        if (player.getHandValue() > 21) {
                            System.out.println("The value of " + player.getName() + "'s hand is " + player.getHandValue() + " " +
                            player.getName() + " lost with their bet of $" + player.setLost());
                        } else if (player.getHandValue() == 21) {
                            System.out.println(player.getName() + " won $" + player.setWon(false));
                        }
                    } else {
                        stay = true;
                    }
                } while (!stay && player.getPlayingState());
            } else if (player.getName().equals("Croupier")) {
                System.out.println("Dealers turn");
                if (player.getHandValue() < 17) {
                    while (player.getHandValue() < 17) {
                        System.out.println("Croupier's hand below 17, hit");
                        System.out.println("Drew " + this.serveRandomCard(player) + ", Croupiers hand totals: " + player.getHandValue());
                        if (player.getHandValue() > 21) {
                            System.out.println("Croupier: " + player.getHandValue() + " for me (Dealer loses)");
                            for (Player pWon : this.getPlayers()) {
                                if (!pWon.getName().equals("Croupier")) {
                                    if (pWon.getPlayingState()) {
                                        System.out.println(pWon.getName() + " won " + pWon.setWon(false));
                                    }
                                }
                            }
                        }
                    }
                } else if (player.getHandValue() == 21) { // dealer wins
                    System.out.println("Dealer wins, everyone loses");
                    int dealerTake = 0;
                    for (Player pLost : this.getPlayers()) {
                        if (!pLost.getName().equals("Croupier")) {
                            if (pLost.getPlayingState()) {
                                System.out.println(pLost.getName() + " lost " + pLost.setLost());
                                dealerTake += pLost.getBet();
                            }
                        }
                    }
                    System.out.println("Croupier takes everyones money\n$" + dealerTake);
                } else if (player.getHandValue() >= 17) { // dealer stays
                    System.out.println("Croupier stays with " + player.getHandValue());
                }
            }
        }
        
        // TODO
        // End of game. Must check dealers handvalue with a loop (Player player : this.getPlayers() ) {
        // if player.getName().equals("Croupier")
        // if players hand > croupiers, player.setWon(), if player hand < croupiers, player.setLost()
        // if player hand equals dealers hand... must check blackjack rules
    }

    // serves the passed player a card. Reduces size of deck by 1 and increases player hand size by 1
    public Card serveRandomCard(Player player) {
        // Retrieve a random card based on the current deck size
        int random = (int) Math.floor((Math.random() * ((this.deck.getSize() - 1) + 1)));
        Card card = this.deck.getCard(random);
        player.recieveCard(this.deck.getCard(random));
        this.deck.removeCard(random);
        return card;
    }
    
    /**
     * When the game is over, use this method to declare and display a winning player.
     */
    public void declareWinner() {
        
    }

}//end class
