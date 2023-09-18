/**
 * Author: Michael Hammal
 */

package src;

import org.junit.*;
import static org.junit.Assert.*;

public class TestTile {
    private Tile t;
    private Tile t1;
    private Tile t2;

    // Set up method to initialize variables
    @Before
    public void setUp()
    {
        t = new Tile();
        t1 = new Tile(2);
        t2 = new Tile(2, 0, 0);
    }

    // Test if the constructor is initializing the object
    @Test
    public void testTile() {
        assertNotNull(t);
    }

    // Test merge
    @Test
    public void testMerge() {
        assertEquals(4, t1.merge());
    }

    // Test getValue
    @Test
    public void testGetValue() {
        assertEquals(0, t.getValue());
    }

    // Test setValue
    @Test
    public void testSetValue() {
        t2.setValue(4);
        assertEquals(4, t2.getValue());
    }

    // Test isEmpty
    @Test
    public void testIsEmpty() {
        assertTrue(t.isEmpty());
    }

    // Test the output method
    @Test
    public void testToString() {
        assertEquals("2", t2.toString());
    }

    // Tear down method
    @After
    public void tearDown()
    {
        t = null;
        t1 = null;
        t2 = null;
    }

}
