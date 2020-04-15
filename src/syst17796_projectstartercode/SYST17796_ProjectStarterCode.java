package syst17796_projectstartercode;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Jesse
 */

public class SYST17796_ProjectStarterCode {
    
    // Collects required info from users, initializes game and returns the game instance
    private static Game initializeGame(Scanner in, ArrayList<Player> initPlayers) {
        ArrayList<Player> players;
        Boolean again = false;
        if (initPlayers != null) {
            again = true;
        }
        int playerCount = -1;
        if (again) {
            playerCount = initPlayers.size();
        }
        // Find out how many players are playing
        if (!again) {
            do {
                System.out.println("How many players are playing? (Max 4)");
                playerCount = checkPlayers(in.nextLine());
                if (playerCount < 1) {
                    System.out.println("You can't play a game without a player");
                } else if (playerCount > 4) {
                    System.out.println("Blackjack cannot be played with more than 4 players");
                }
            } while (playerCount < 1 || playerCount > 4);
            if (playerCount > 0 && playerCount < 5) {
                System.out.println("Players playing: " + playerCount);
            } else {
                System.exit(0);
            }
            // Generate arraylist of players, enter name and bet
            players = new ArrayList<Player>();
        } else {
            players = initPlayers;
        }
        for (int i = 0; i < playerCount; i++) {
            String playerName = "";
            if (!again) {
                System.out.println("Enter a name for player " + (i + 1));
                // Check length of 
                do {
                    playerName = in.nextLine();
                    if (playerName.length() > 26 || playerName.length() == 0) {
                        playerName = "";
                        System.out.println("Bad name, please enter a name that is under 26 characters");
                    }
                    if (playerName.equals("Croupier")) {
                        playerName = "";
                        System.out.println("You cannot be the dealer!");
                    }
                } while (playerName.length() < 1);
            }
            if (again) {
                playerName = players.get(i).getName();
            }
            if (!playerName.equals("Croupier")) {
                System.out.println(playerName + ", please enter your bet");
                int bet = 0;
                do {
                    try {
                        bet = Integer.parseInt(in.nextLine());
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    if (bet < 2) {
                        System.out.println("Croupier: Sorry, you cant bet any less than 2 dollars");
                    } else if (bet > 500) {
                        System.out.println("Croupier: No bets over $500");
                    }
                } while (bet < 2 || bet > 500);
                if (!again) {
                    Player p = new Player(playerName, bet);
                    players.add(p);
                } else {
                    players.get(i).setBet(bet);
                }
            }
        }
        // Add dealer to group of players
        if (!again) {
            Player d = new Player("Croupier", true);
            players.add(d);
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
        Game blackJack = initializeGame(in, null);
        // Run game and take players from old game
        ArrayList<Player> newGamePlayers = blackJack.play(in);
        do {
            initializeGame(in, newGamePlayers);
            blackJack.resetDeck(); // Resets deck and reshuffles (check GroupOfCards class for shuffle functionality)
            newGamePlayers = blackJack.play(in);
        } while (newGamePlayers != null);
        System.exit(0);
    }
}
