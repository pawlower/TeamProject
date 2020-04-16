/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syst17796_projectstartercode;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jesse Thompson
 */
import java.util.ArrayList;
public class GameTest {
    
    public GameTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getName method, of class Game.
     */
    @Test
    public void testGoodGetName() {
        System.out.println("getGoodName");
        Player player = new Player("jimbo", 5);
        String expResult = "jimbo";
        String result = player.getName();
        assertEquals(expResult, result);
    }

    @Test
    public void testBadGetName() {
        System.out.println("getBadName");
        Player player = new Player("", 2);
        String expResult = "";
        String result = player.getName();
        assertEquals(expResult, result);
    }

    @Test
    public void testBoundaryGetName() {
        System.out.println("getBoundaryName");
        Player player = new Player("anamenotover26chars", 5);
        String expResult = "anamenotover26chars";
        String result = player.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPlayers method, of class Game.
     */
    @Test
    public void testGoodGetPlayers() {
        System.out.println("getGoodPlayers");
        ArrayList<Player> players = new ArrayList<Player>() {{
            add(new Player("jimbo", 5));
            add(new Player("sammy", 3));
        }};
        Game instance = new Game(players);
        ArrayList<Player> expResult = players;
        ArrayList<Player> result = instance.getPlayers();
        assertEquals(expResult, result);
    }

    @Test
    public void testBadGetPlayers() {
        System.out.println("getBadPlayers");
        ArrayList<Player> players = new ArrayList<Player>() {{
            add(new Player("", 5));
            add(new Player("", 3));
        }};
        int expResult = 2; // retrieve or build list
        int result = players.size(); // retrieve or build other list with same items
        assertEquals(expResult, result);
    }

    @Test
    public void testBoundaryGetPlayers() {
        System.out.println("getBoundaryPlayers");
        ArrayList<Player> players = new ArrayList<Player>() {{
            add(new Player("", 5));
            add(new Player("", 3));
        }};
        Game instance = new Game(players);
        ArrayList<Player> expResult = players;
        ArrayList<Player> result = instance.getPlayers();
        assertEquals(expResult, result);
    }    
}
