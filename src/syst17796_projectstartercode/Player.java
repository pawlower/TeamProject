/**
 * SYST 17796 Project Winter 2020 Base code.
 * Students can modify and extend to implement their game.
 * Add your name as a modifier and the date!
 */
package syst17796_projectstartercode;

import java.util.ArrayList;

/**
 * A class that models each Player in the game. Players have an identifier, which should be unique.
 *
 * @author dancye
 * @modified Paul Bonenfant Jan 2020
 * @author Jesse Thompson April 2020
 */
public class Player {

    private String name; //the unique name for this player
    private boolean playing = false;
    private ArrayList<Card> hand = new ArrayList<Card>();
    private int bet = 0;
    
    // A constructor that allows you to set the player's unique ID

    public Player(String name, int bet) {
        this.name = name;
        this.bet = bet;
        this.playing = true;
    }

    // Player name
    public String getName() {
        return name;
    }

    // Get player name
    public void setName(String name) {
        this.name = name;
    }

    // Get player state
    public Boolean getPlayingState() {
        return this.playing;
    }
    
    public int getBet() {
        return this.bet;
    }
    
    public String toString() {
        if (this.getPlayingState()) {
            return this.getName() + " is currently playing" ;
        } else {
            return this.getName() + " is currently not playing";
        }
    }

}
