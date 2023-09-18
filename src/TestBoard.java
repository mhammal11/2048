/**
 * Author: Michael Hammal
 */

package src;

import org.junit.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestBoard {
    private Board b;

    // Set up method to initialize variables
    @Before
    public void setUp()
    {
        Board.setEmptyBoard(true);
        b = new Board(4);
    }

    // Test if the constructor is initializing the object
    @Test
    public void testBoard() {
        assertNotNull(b);
    }

    @Test
    public void testGetTiles() {
        List<List<Tile>> tiles;
        tiles = new ArrayList<>();
        for (int row = 0; row < 4; row++) {
            tiles.add(new ArrayList<>());
            for (int col = 0; col < 4; col++) {
                tiles.get(row).add(new Tile());
            }
        }
        for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
                assertEquals(tiles.get(row).get(col).getValue(), b.getTiles().get(row).get(col).getValue());
			}
		}
    }

    // Test getTiles
    /* Equate two arraylists of tiles
       One list is from the board.
       One list is manually generated.
     */
    @Test
    public void testGetTiles2() {
        List<List<Tile>> tiles;
        tiles = new ArrayList<>();
        for (int row = 0; row < 4; row++) {
            tiles.add(new ArrayList<>());
            for (int col = 0; col < 4; col++) {
                tiles.get(row).add(new Tile(2));
            }
        }
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                b.setTileAt(row,col,new Tile(2));
                assertEquals(tiles.get(row).get(col).getValue(), b.getTiles().get(row).get(col).getValue());
            }
        }
    }

    // Test getTileAt
    // Testing intial tiles
    @Test
    public void testGetTileAt() {
        assertFalse(Board.getTileAt(0, 0).getValue() == 4);
    }

    // Test getTileAt
    @Test
    public void testGetTileAt2() {
        b.setTileAt(0,0,new Tile(4));
        assertEquals(4, Board.getTileAt(0, 0).getValue());
    }

    // Test getTileAt
    @Test
    public void testSetTileAt() {
        b.setTileAt(1,1,new Tile(4));
        assertEquals(4, Board.getTileAt(1, 1).getValue());
    }

    // Test getScore
    // Testing initial score
    @Test
    public void testGetScore() {
        assertEquals(0, b.getScore());
    }

    // Test getScore
    // Testing score after a move and a merge occur
    @Test
    public void testGetScore2() {
        b.setTileAt(0,0,new Tile(2));
        b.setTileAt(0,1,new Tile(2));
        b.moveRight();
        assertEquals(4, b.getScore());
    }

    // Test getSize
    @Test
    public void testGetSize() {
        assertEquals(4, b.getSize());
    }

    // Test getSize
    @Test
    public void testGetSize2() {
        Board b2 = new Board(5);
        assertEquals(5, b2.getSize());
    }

    // Test isFull
    // Set the value of emptyTiles to 0, indicating a full board
    @Test
    public void testIsFull() {
        b.emptyTiles = 0;
        assertTrue(Board.isFull());
    }

    // Test isFull
    @Test
    public void testIsFull2() {
        assertFalse(Board.isFull());
    }

    // Test isMovePossible
    @Test
    public void testIsMovePossible() {
        assertTrue(Board.isMovePossible());
    }

    // Test isMovePossible
    // Test if a move is possible when the board is full and no merge can occur
    @Test
    public void testIsMovePossible2() {
        b.setTileAt(0,0,new Tile(2));
        b.setTileAt(0,1,new Tile(4));
        b.setTileAt(0,2,new Tile(2));
        b.setTileAt(0,3,new Tile(4));
        b.setTileAt(1,0,new Tile(4));
        b.setTileAt(1,1,new Tile(2));
        b.setTileAt(1,2,new Tile(4));
        b.setTileAt(1,3,new Tile(2));
        b.setTileAt(2,0,new Tile(2));
        b.setTileAt(2,1,new Tile(4));
        b.setTileAt(2,2,new Tile(2));
        b.setTileAt(2,3,new Tile(4));
        b.setTileAt(3,0,new Tile(4));
        b.setTileAt(3,1,new Tile(2));
        b.setTileAt(3,2,new Tile(4));
        b.setTileAt(3,3,new Tile(2));
        assertFalse(Board.isMovePossible());
    }

    // Test moveUp
    // Tiles are moved up and merged
    @Test
    public void testMoveUp() {
        b.setTileAt(0,0, new Tile(2));
        b.setTileAt(1,0,new Tile(2));
        b.moveUp();
        assertEquals(4, Board.getTileAt(0, 0).getValue());
    }

    // Test moveUp
    // Tiles are moved up but no merging occurs
    @Test
    public void testMoveUp2() {
        b.setTileAt(3,0, new Tile(2));
        b.setTileAt(3,1,new Tile(2));
        b.moveUp();
        assertTrue(Board.getTileAt(0,0).getValue() == 2 && Board.getTileAt(0,1).getValue() == 2);
    }

    // Test moveUp
    // Adjacent tiles with different values are moved up
    @Test
    public void testMoveUp3() {
        b.setTileAt(0,0, new Tile(4));
        b.setTileAt(1,0,new Tile(2));
        b.moveUp();
        assertTrue(Board.getTileAt(0,0).getValue() == 4 && Board.getTileAt(1,0).getValue() == 2);
    }

    // Test moveUp
    // Test if the first possible merge occurs
    @Test
    public void testMoveUp4() {
        b.setTileAt(0,0, new Tile(2));
        b.setTileAt(1,0,new Tile(2));
        b.setTileAt(2,0,new Tile(2));
        b.moveUp();
        assertTrue(Board.getTileAt(0,0).getValue() == 4 && Board.getTileAt(1,0).getValue() == 2);
    }

    // Test moveDown
    // Tiles are moved down and merged
    @Test
    public void testMoveDown() {
        b.setTileAt(0,0, new Tile(2));
        b.setTileAt(1,0,new Tile(2));
        b.moveDown();
        assertEquals(4, Board.getTileAt(3, 0).getValue());
    }

    // Test moveDown
    // Tiles are moved down but no merging occurs
    @Test
    public void testMoveDown2() {
        b.setTileAt(3,0, new Tile(2));
        b.setTileAt(3,1,new Tile(2));
        b.moveDown();
        assertTrue(Board.getTileAt(3,0).getValue() == 2 && Board.getTileAt(3,1).getValue() == 2);
    }

    // Test moveDown
    // Adjacent tiles with different values are moved down
    @Test
    public void testMoveDown3() {
        b.setTileAt(0,0, new Tile(4));
        b.setTileAt(1,0,new Tile(2));
        b.moveDown();
        assertTrue(Board.getTileAt(2,0).getValue() == 4 && Board.getTileAt(3,0).getValue() == 2);
    }

    // Test moveDown
    // Test if the first possible merge occurs
    @Test
    public void testMoveDown4() {
        b.setTileAt(0,0, new Tile(2));
        b.setTileAt(1,0,new Tile(2));
        b.setTileAt(2,0,new Tile(2));
        b.moveDown();
        assertTrue(Board.getTileAt(3,0).getValue() == 4 && Board.getTileAt(2,0).getValue() == 2);
    }

    // Test moveRight
    // Tiles are moved right and merged
    @Test
    public void testMoveRight() {
        b.setTileAt(0,0, new Tile(2));
        b.setTileAt(0,1,new Tile(2));
        b.moveRight();
        assertEquals(4, Board.getTileAt(0, 3).getValue());
    }

    // Test moveRight
    // Tiles are moved down but no merging occurs
    @Test
    public void testMoveRight2() {
        b.setTileAt(0,0, new Tile(2));
        b.setTileAt(1,0,new Tile(4));
        b.moveRight();
        assertTrue(Board.getTileAt(0,3).getValue() == 2 && Board.getTileAt(1,3).getValue() == 4);
    }

    // Test moveRight
    // Adjacent tiles with different values are moved right
    @Test
    public void testMoveRight3() {
        b.setTileAt(0,0, new Tile(4));
        b.setTileAt(0,1,new Tile(2));
        b.moveRight();
        assertTrue(Board.getTileAt(0,2).getValue() == 4 && Board.getTileAt(0,3).getValue() == 2);
    }

    // Test moveRight
    // Test if the first possible merge occurs
    @Test
    public void testMoveRight4() {
        b.setTileAt(0,0, new Tile(2));
        b.setTileAt(0,1,new Tile(2));
        b.setTileAt(0,2,new Tile(2));
        b.moveRight();
        assertTrue(Board.getTileAt(0,3).getValue() == 4 && Board.getTileAt(0,2).getValue() == 2);
    }

    // Test moveLeft
    // Tiles are moved left and merged
    @Test
    public void testMoveLeft() {
        b.setTileAt(0,0, new Tile(2));
        b.setTileAt(0,1,new Tile(2));
        b.moveLeft();
        assertEquals(4, Board.getTileAt(0, 0).getValue());
    }

    // Test moveLeft
    // Tiles are moved down but no merging occurs
    @Test
    public void testMoveLeft2() {
        b.setTileAt(0,0, new Tile(2));
        b.setTileAt(1,0,new Tile(4));
        b.moveLeft();
        assertTrue(Board.getTileAt(0,0).getValue() == 2 && Board.getTileAt(1,0).getValue() == 4);
    }

    // Test moveLeft
    // Adjacent tiles with different values are moved left
    @Test
    public void testMoveLeft3() {
        b.setTileAt(0,0, new Tile(4));
        b.setTileAt(0,1,new Tile(2));
        b.moveLeft();
        assertTrue(Board.getTileAt(0,0).getValue() == 4 && Board.getTileAt(0,1).getValue() == 2);
    }

    // Test moveLeft
    // Test if the first possible merge occurs
    @Test
    public void testMoveLeft4() {
        b.setTileAt(0,0, new Tile(2));
        b.setTileAt(0,1,new Tile(2));
        b.setTileAt(0,2,new Tile(2));
        b.moveLeft();
        assertTrue(Board.getTileAt(0,0).getValue() == 4 && Board.getTileAt(0,1).getValue() == 2);
    }

    // Test restart
    /* Create a new board and set a non-zero value for a tile.
       Ensure that the value is changed.
       Restart the game.
       Ensure that the new board created is empty by checking if the value of that tile is 0.
     */
    @Test
    public void testRestart() {
        b.setTileAt(0,0,new Tile(2));
        assertEquals(2, Board.getTileAt(0, 0).getValue());
        b.restart(4);
        assertEquals(0, Board.getTileAt(0, 0).getValue());
    }

    // Test restart
    /* Create a new board and set non-zero values for each tile.
       Ensure that the value is changed.
       Restart the game.
       Ensure that the new board created is empty by checking if the value of each tile is 0.
     */
    @Test
    public void testRestart2() {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                b.setTileAt(row,col,new Tile(2));
                assertEquals(2, Board.getTileAt(0, 0).getValue());
            }
        }
        b.restart(4);
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                assertEquals(0, Board.getTileAt(0, 0).getValue());
            }
        }
    }

    // Test isGameOver
    // More moves are still possible in this scenario
    @Test
    public void testIsGameOver() {
        b.setTileAt(0,0,new Tile(2));
        b.setTileAt(1,0,new Tile(2));
        b.setTileAt(2,0,new Tile(4));
        assertFalse(Board.isGameOver());
    }

    // Test isGameOver
    // The variable gameOver is set to true, indicating that the game is won and 2048 tile is found.
    @Test
    public void testIsGameOver2() {
        Board.gameOver = true;
        assertTrue(Board.isGameOver());
    }

    // Test isGameOver
    // The board is full and no more moves are possible, indicating that the game is over.
    @Test
    public void testIsGameOver3() {
        Board.gameOver = false;
        b.setTileAt(0,0,new Tile(2));
        b.setTileAt(0,1,new Tile(4));
        b.setTileAt(0,2,new Tile(2));
        b.setTileAt(0,3,new Tile(4));
        b.setTileAt(1,0,new Tile(4));
        b.setTileAt(1,1,new Tile(2));
        b.setTileAt(1,2,new Tile(4));
        b.setTileAt(1,3,new Tile(2));
        b.setTileAt(2,0,new Tile(2));
        b.setTileAt(2,1,new Tile(4));
        b.setTileAt(2,2,new Tile(2));
        b.setTileAt(2,3,new Tile(4));
        b.setTileAt(3,0,new Tile(4));
        b.setTileAt(3,1,new Tile(2));
        b.setTileAt(3,2,new Tile(4));
        b.setTileAt(3,3,new Tile(2));
        Board.emptyTiles = 0;
        assertTrue(Board.isGameOver());
    }
    // Tear down method
    @After
    public void tearDown()
    {
        b = null;
    }

}
