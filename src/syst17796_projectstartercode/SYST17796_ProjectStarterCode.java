package syst17796_projectstartercode;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Jesse
 */

public class SYST17796_ProjectStarterCode {
    
    private static Game initializeGame(Scanner in) {
        // Find out how many players are playing
        int playerCount = -1;
        do {
            System.out.println("How many players are playing? (Max 4)");
            playerCount = checkPlayers(in.nextLine());
            if (playerCount < 0 || playerCount > 4) {
                System.out.println("Wrong input, please try again");
            }
        } while (playerCount < 0 || playerCount > 4);
        if (playerCount > -1 && playerCount < 5) {
            System.out.println("Players playing: " + playerCount);
        } else {
            System.exit(0);
        }
        // Generate arraylist of players
        ArrayList<Player> players = new ArrayList<Player>();
        for (int i = 0; i < playerCount; i++) {
            System.out.println("Enter a name for player " + (i + 1));
            String playerName = "";
            // Check length of 
            do {
                playerName = in.nextLine();
                if (playerName.length() > 26 || playerName.length() == 0) {
                    playerName = "";
                }
            } while (playerName.length() < 1);
            Player p = new Player(playerName);
            players.add(p);
        }
        // Initialize game and announce whose currently playing
        Game blackJack = new Game(players);
        String blurb = "";
        for (Player player : players) {
            if (players.size() > 1) {
                if (player.getName().equals(players.get(players.size()-1).getName())) {
                    blurb += "and ";
                } 
            }
            blurb += player.getName();
            if (!player.getName().equals(players.get(players.size()-1).getName())) {
                blurb += ", ";
            }
        }
        blurb += " is currently playing";
        System.out.println(blurb);
        // Return game to main method
        return blackJack;
    };
    
    // Checks if user has input a valid amount of players
    private static int checkPlayers(String check) {
        try {
            Integer.parseInt(check);
            return Integer.parseInt(check);
        } catch (NumberFormatException e) {
            System.out.println(e);
            return -1;
        }
    }
    public static void main (String [] args) {
        Scanner in = new Scanner(System.in);
        Game blackJack = initializeGame(in);
        
    }
}
