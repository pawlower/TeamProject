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
    
    public void resetDeck() {
        this.deck = new GroupOfCards(52);
    }
    
    // Begin the game
    public ArrayList<Player> play(Scanner in) {
        // Serve each player two cards from deck
        for (Player player : this.getPlayers()) {
            serveRandomCard(player);
            serveRandomCard(player);
            // Go through each players hand and describe the hand they have
            // If the player is the croupier, only reveal first card.
            for (int i = 0; i < player.getHand().size(); i++) {
                if (player.getName().equals("Croupier") && i == 1) {
                    System.out.println(player.getName() + "'s second card is hidden");
                    // System.out.println("Dev check: " + player.getName() + " has a " + player.getHand().get(i));
                } else {
                    System.out.println(player.getName() + " has a " + player.getHand().get(i));
                }
            }
        }
        
        // A short list of exclamatory phrases to use for speech output
        ArrayList<String> exclamations = new ArrayList<String>() {{
            add("Wow");
            add("Nice");
            add("Not bad");
            add("Well played");
        }};
        // Integrity check of deck size, useful for when doing repeat games
        // System.out.println(this.getDeck().getSize());
        // Check values/who lost after first deal
        for (Player player : this.getPlayers()) {
            if (!player.getName().equals("Croupier")) {
                int value = player.getHandValue();
                int altValue = player.getHandValueAlt();
                // Check if alternate value (counting Aces as 10) is a blackjack
                // If not, do not need to check  if alt value is over 21 as Ace can be a 1.
                // The check if normal value exceeds 21 and then check if normal value is a blackjack.
                if (altValue == 21) {
                    System.out.println("Blackjack! " + player.getName() + " won $" + player.setWon(true));
                } else if (value > 21) {
                    System.out.println("The value of " + player.getName() + "'s hand is " + value + " \n" + 
                    player.getName() + " lost with their bet of $" + player.setLost());
                } else if (value == 21) { // Blackjack
                    // setWon(true) means true blackjack boolean. Player wins 1.5x
                    System.out.println("Blackjack! " + player.getName() + " won $" + player.setWon(true));
                }
            }
        }
        // Loop through to prompt for hit, stay, lose, win functionality
        for (Player player : this.getPlayers()) {
            // If iterated player is not the dealer and is currently playing
            if (!player.getName().equals("Croupier") && player.getPlayingState()) {
                // Print players normal hand value if alternate hand value is over 21
                String blurb = "Croupier: for " + player.getName() + ", ";
                for (int i = 0; i < player.getHand().size(); i++) {
                    blurb += player.getHand().get(i);
                    if (i != player.getHandSize()-1) {
                        blurb += ", ";
                    }
                }
                blurb += ", " + (player.getHandValueAlt() > 21 ? player.getHandValue() : player.getHandValueAlt());
                System.out.println(blurb);
                // player in stay state is set to false until the say stay or hit 21
                boolean stay = false;
                do {
                    System.out.println("Hit? (hit/stay)");
                    String response = in.nextLine();
                    if (response.equals("hit") || response.equals("Hit")) {
                        System.out.println("Croupier: " + this.serveRandomCard(player) + ", " + 
                            (player.getHandValueAlt() > 21 ? player.getHandValue() : player.getHandValueAlt()));
                        if (player.getHandValue() > 21) {
                            System.out.println("The value of " + player.getName() + "'s hand is " + player.getHandValue() + ". " +
                            player.getName() + " lost with their bet of $" + player.setLost());
                        } else if (player.getHandValue() == 21 || player.getHandValueAlt() == 21) {
                            System.out.println(player.getName() + " won $" + player.setWon(false));
                        }
                    } else {
                        stay = true;
                    }
                } while (!stay && player.getPlayingState());
            } else if (player.getName().equals("Croupier")) {
                System.out.println("Dealers turn");
                System.out.println("For the dealer:");
                for (int i = 0; i < player.getHandSize(); i++) {
                    System.out.println(player.getHand().get(i));
                }
                if (player.getHandValue() < 17 && player.getHandValueAlt() < 17) {
                    while (player.getHandValue() < 17 && player.getHandValueAlt() < 17) {
                        System.out.println("Croupier's hand below 17, hit");
                        System.out.println("Drew " + this.serveRandomCard(player) + ", Croupiers hand totals: " + 
                            (player.getHandValueAlt() > 21 ? player.getHandValue() : player.getHandValueAlt()));
                        if (player.getHandValue() > 21) {
                            System.out.println("Croupier: " + player.getHandValue() + " for me (Dealer loses)");
                            player.setLost();
                            for (Player pWon : this.getPlayers()) {
                                if (!pWon.getName().equals("Croupier")) {
                                    if (pWon.getPlayingState()) {
                                        System.out.println(exclamations.get((int) Math.floor((Math.random() * ((exclamations.size() - 1) + 1)))) + ", " +
                                            pWon.getName() + " won $" + pWon.setWon(false));
                                    }
                                }
                            }
                        }
                    }
                } else if (player.getHandValue() == 21 || player.getHandValueAlt() == 21) { // dealer wins
                    System.out.println("Dealer wins, everyone loses");
                    int dealerTake = 0;
                    for (Player pLost : this.getPlayers()) {
                        if (!pLost.getName().equals("Croupier")) {
                            if (pLost.getPlayingState()) {
                                System.out.println(pLost.getName() + " lost $" + pLost.setLost());
                                dealerTake += pLost.getBet();
                            }
                        }
                    }
                    System.out.println("Croupier takes everyones money\n$" + dealerTake);
                } else if (player.getHandValue() >= 17 || player.getHandValueAlt() >= 17) { // dealer stays
                    System.out.println("Croupier stays with " + 
                        (player.getHandValueAlt() < 21 ? player.getHandValueAlt() : player.getHandValue()));
                } else if (player.getHandValue() > 21) {
                    System.out.println("Croupier: " + player.getHandValue() + " for me (Dealer loses)");
                    player.setLost();
                    for (Player pWon : this.getPlayers()) {
                        if (!pWon.getName().equals("Croupier")) {
                            if (pWon.getPlayingState()) {
                                System.out.println(exclamations.get((int) Math.floor((Math.random() * ((exclamations.size() - 1) + 1))))
                                        + ", " + pWon.getName() + " won $" + pWon.setWon(false));
                            }
                        }
                    }
                }
            }
        }
        
        // TODO
        // End of game. Must check dealers handvalue with a loop (Player player : this.getPlayers() ) {
        // if player.getName().equals("Croupier")
        // if players hand > croupiers, player.setWon(), if player hand < croupiers, player.setLost()
        // if player hand equals dealers hand... must check blackjack rules
        
        Player croupier = this.getPlayers().get(this.getPlayers().size()-1);
        int croupierValue = (croupier.getHandValueAlt() > 21 ? croupier.getHandValue() : croupier.getHandValueAlt());
        for (Player player : this.getPlayers()) {
            // If player is still playing and iterated is not the croupier, run win/loss logic at end of game
            if (!player.getName().equals("Croupier") && player.getPlayingState()) {
                int playerValue = (player.getHandValueAlt() > 21 ? player.getHandValue() : player.getHandValueAlt());
                // Player loses if they bust or croupiers hand is greater than theirs
                System.out.println("Croupier: " + player.getName() + ", " + playerValue);
                if (playerValue > 21 || croupierValue > playerValue || playerValue == croupierValue) {
                    System.out.println(player.getName() + " lost their bet of $" + player.setLost());
                } else if (playerValue > croupierValue) {
                    System.out.println(exclamations.get((int) Math.floor((Math.random() * ((exclamations.size() - 1) + 1))))
                            + ", " + player.getName() + " won their bet of $" + player.setWon(false));
                }
            }
        }
        
        System.out.println("Play again?(y/n)");
        String again = in.nextLine();
        if (again.equals("y")) {
            for (Player player : this.getPlayers()) {
                player.reset();
            }
            return this.getPlayers();
        } else {
            return null;
        }
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
