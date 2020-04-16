/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syst17796_projectstartercode;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
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
public class PlayerTest {
    
    public PlayerTest() {
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
     * Test of getName method, of class Player.
     */
    @Test
    public void testGoodGetName() {
        System.out.println("getGoodName");
        Player instance = new Player("sam", 5);
        String expResult = "sam";
        String result = instance.getName();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testBadGetName() {
        System.out.println("getBadName");
        Player instance = new Player("", 5);
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testBoundaryGetName() {
        System.out.println("getBoundaryName");
        Player instance = new Player("thisnameismorethan26charsthisnameismorethan26chars", 5);
        String expResult = null;
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getHandSize method, of class Player.
     */
    @Test
    public void testGoodGetHandSize() {
        System.out.println("getGoodHandSize");
        Player player = new Player("sam", 5);
        GroupOfCards cards = new GroupOfCards(52);
        player.recieveCard(cards.getCard(0));
        int expResult = 1;
        int result = player.getHandSize();
        assertEquals(result, result);
    }
    
    @Test
    public void testBadGetHandSize() {
        System.out.println("getBadHandSize");
        GroupOfCards cards = new GroupOfCards(52);
        Player player = new Player("jim", 5);
        Card card = cards.getCard(0);
        player.recieveCard(cards.getCard(1));
        Card expResult = card;
        Card result = player.getHand().get(0);
        assertNotEquals(expResult, result);
        
    }
    
    @Test
    public void testBoundaryGetHandSize() {
        System.out.println("getGoodHandSize");
        Player player = new Player("sam", 5);
        GroupOfCards cards = new GroupOfCards(52);
        for (Card card : cards.getCards()) {
            player.recieveCard(cards.getCard(0));
        }
        int expResult = 52;
        int result = player.getHandSize();
        assertEquals(result, result);
    }
}
