/**
 * SYST 17796 Project Winter 2020 Base code.
 * Students can modify and extend to implement their game.
 * Add your name as a modifier and the date!
 */
package syst17796_projectstartercode;

import java.util.ArrayList;

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
    private ArrayList<Player> players;// the players of the game

    public Game(ArrayList<Player> players) {
        this.players = players;
    }

    // Return game name
    public String getName() {
        return name;
    }

    // Get all players
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * @param players the players of this game
     */
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void printPlayerState() {
        for (Player player : this.players) {
            System.out.println(player.toString());
        }
    }
    /**
     * Play the game. This might be one method or many method calls depending on your game.
     */
    public void play() {
        
    }

    /**
     * When the game is over, use this method to declare and display a winning player.
     */
    public void declareWinner() {
        
    }

}//end class
