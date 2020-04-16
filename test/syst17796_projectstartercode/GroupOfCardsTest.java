/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syst17796_projectstartercode;

import java.util.ArrayList;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author kesha
 * @author Jesse Thompson
 */
public class GroupOfCardsTest {
    
    public GroupOfCardsTest() {
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
     * Test of getCard method, of class GroupOfCards.
     */
    @Test
    public void testGoodGetCard() {
        System.out.println("getCard");
        ArrayList<Player> players = new ArrayList<Player>() {{
            add(new Player("jim", 5));
            add(new Player("sam", 3));
        }};
        Game game = new Game(players);
        Card result = game.getDeck().getCard(0);
        assertThat(result, instanceOf(Card.class));
    }
    
    @Test
    public void testGoodGetSize() {
        System.out.println("getGoodSize");
        ArrayList<Player> players = new ArrayList<Player>() {{
            add(new Player("jim", 5));
            add(new Player("sam", 3));
        }};
        int expResult = 2;
        int result = players.size();
        assertEquals(expResult, result);
    }

   @Test
    public void testBadGetSize() {
        System.out.println("getBadSize");
        ArrayList<Player> players = new ArrayList<Player>() {{
            add(new Player("jim", 5));
        }};
        int expResult = 1;
        int result = players.size();
        assertEquals(expResult, result);
    }  
}
