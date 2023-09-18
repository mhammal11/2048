/**
 * Author: Michael Hammal
 * Description: Board module
 */

package src;

import java.util.ArrayList;
import java.util.List;

/**
 * @brief A module that represents the board in the game.
 * @details Each board is made up of tiles and is the main part of the game.
 */
public class Board {
	private static List<List<Tile>> tiles;
	protected static int emptyTiles;
	private static int size;
	private static int score;
	private static boolean emptyBoard = false; // variable for testing purpose.
	protected static boolean gameOver = false;
	private boolean movingRightOrDown = false;

	/**
	 * @brief Initializes a board object.
	 * @details The constructor creates a new board with the size given and populates the board either
	 * with empty tiles or with two random tiles.
	 * @param size The size of the board.
	 */
	public Board(int size) {
		Board.size = size;
		Board.score = 0;
		emptyTiles = Board.size * Board.size;
		tiles = new ArrayList<>();
		// Initialize the board with tiles
		for (int row = 0; row < Board.size; row++) {
			tiles.add(new ArrayList<>());
			for (int col = 0; col < Board.size; col++) {
				tiles.get(row).add(new Tile());
			}
		}
		// Populate the board
		// if statement for testing purposes
		// If emptyBoard is true, then the board will not have random tiles generated.
		if (!emptyBoard) {
			for (int i = 0; i < 2; i++) {
				newRandomTile();
			}
		}
	}

	// Method for testing purposes to create an empty board
	public static void setEmptyBoard(boolean value) {
		emptyBoard = value;
	}

	/**
	 * @brief Method to get all the tiles in the board.
	 * @return The tiles in the board.
	 */
	public List<List<Tile>> getTiles() {
		return tiles;
	}

	/**
	 * @brief Method to get a specific tile in the board.
	 * @return The tile at the given row and column position.
	 */
	public static Tile getTileAt(int row, int col) {
		return tiles.get(row).get(col);
	}

	/**
	 * @brief Method to set a specific tile in the board at the given row and column position.
	 */
	public void setTileAt(int row, int col, Tile t) {
		tiles.get(row).set(col, t);
	}

	/**
	 * @brief Method to get the score of the game so far.
	 * @return The score of the game.
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @brief Method to get the size of the board.
	 * @return The size of the board.
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @brief Method to determine whether the board is empty or not.
	 * @details This method checks whether the variable emptyTiles is equal to 0. This variable is
	 * changed appropriately every time a new random tile is generated and every time a merge occurs.
	 * @return true if the board is empty and false otherwise.
	 */
	public static boolean isFull() {
		return emptyTiles == 0;
	}

	/**
	 * @brief Method to determine whether a move is possible or not.
	 * @details This method checks whether a move is possible based on whether there are two adjacent values
	 * from left to right, right to left, top to bottom, or bottom to top, that are equal.
	 * @return true if a move is possible and false otherwise.
	 */
	public static boolean isMovePossible() {
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size - 1; col++) {
				if (getTileAt(row, col + 1).getValue() == getTileAt(row, col).getValue()) {
					return true;
				}
			}
		}
		for (int row = 0; row < size - 1; row++) {
			for (int col = 0; col < size; col++) {
				if (getTileAt(col, row + 1).getValue() == getTileAt(col, row).getValue()) {
					return true;
				}
			}
		}
		return false;
	}

	// Method to create a new random tile.
	// Inspired by https://stackoverflow.com/questions/23356999/random-numbers-the-number-2-or-4-in-java
	private void newRandomTile() {
		int row = (int) (Math.random() * 4);
		int col = (int) (Math.random() * 4);
		int value = Math.random() > 0.1 ? 2 : 4;
		while (getTileAt(row, col).getValue() != 0) {
			row = (int) (Math.random() * 4);
			col = (int) (Math.random() * 4);
		}
		setTileAt(row, col, new Tile(value, row, col));
		emptyTiles--;

	}

	// Method to merge two equal tiles when a move is made.
	private List<Tile> mergeTiles(List<Tile> tiles) {
		// Check if we're moving right or down so that the merge is inverted and starts from the other side.
		if (!movingRightOrDown) {
			for (int l = 0; l < tiles.size() - 1; l++) {
				// Check if two adjacent values are equal and can be merged
				if (tiles.get(l).getValue() == tiles.get(l + 1).getValue()) {
					int value;
					// Check if game is over and 2048 tile is found
					if ((value = tiles.get(l).merge()) == 2048) {
						gameOver = true;
					}
					// Update the score
					score += value;
					// Remove the tile that is merged
					tiles.remove(l + 1);
					// Increase the number of empty tiles
					emptyTiles++;
				}
			}
		} else {
			for (int l = tiles.size() - 2; l >= 0; l--) {
				// Check if two adjacent values are equal and can be merged
				if (tiles.get(l).getValue() == tiles.get(l + 1).getValue()) {
					int value;
					// Check if game is over and 2048 tile is found
					if ((value = tiles.get(l).merge()) == 2048) {
						gameOver = true;
					}
					// Update the score
					score += value;
					// Remove the tile that is merged
					tiles.remove(l + 1);
					// Increase the number of empty tiles
					emptyTiles++;
				}
			}
		}
		return tiles;
	}

	// Method to facilitate each movement
	// Removes empty tiles and adds them later to update the board
	private List<Tile> removeEmptyTilesFromRow(int row) {
		List<Tile> tiles = new ArrayList<>();
		for (int col = 0; col < size; col++) {
			if (!getTileAt(row, col).isEmpty()) {
				tiles.add(getTileAt(row, col));
			}
		}
		return tiles;
	}

	// Method to facilitate each movement
	// Removes empty tiles and adds them later to update the board
	private List<Tile> removeEmptyTilesFromColumn(int row) {
		List<Tile> tiles = new ArrayList<>();
		for (int col = 0; col < size; col++) {
			if (!getTileAt(col, row).isEmpty()) {
				tiles.add(getTileAt(col, row));
			}
		}
		return tiles;
	}

	// Method to facilitate each movement
	// Adds the empty tiles that wre previously removed
	private List<Tile> addEmptyTilesOnTop(List<Tile> list) {
		for (int k = list.size(); k < size; k++) {
			list.add(0, new Tile());
		}
		return list;
	}

	// Method to facilitate each movement
	// Adds the empty tiles that wre previously removed
	private List<Tile> addEmptyTilesAtBottom(List<Tile> list) {
		for (int k = list.size(); k < size; k++) {
			list.add(k, new Tile());
		}
		return list;
	}

	// Method to facilitate each movement
	// Add the columns with the appropriate tiles back to the board
	private List<Tile> AddColumnToBoard(List<Tile> list, int row) {
		for (int col = 0; col < tiles.size(); col++) {
			setTileAt(col, row, list.get(col));
		}
		return list;
	}

	// Method to facilitate each movement
	// Add the rows with the appropriate tiles back to the board
	private List<Tile> AddRowToBoard(List<Tile> list, int row) {
		for (int col = 0; col < tiles.size(); col++) {
			setTileAt(row, col, list.get(col));
		}
		return list;
	}

	/**
	 * @brief Method to move the tiles up in the board.
	 * @details This method moves the tiles up in the board and merges any appropriate tiles.
	 * It first strips the board apart into rows and columns, completes an appropriate merge, and then
	 * puts the board back together.
	 */
	public void moveUp() {
		if (isMovePossible()) {
			List<Tile> tiles;
			for (int row = 0; row < size; row++) {
				// Separate empty tiles
				tiles = removeEmptyTilesFromColumn(row);
				// Merge appropriate tiles if possible
				tiles = mergeTiles(tiles);
				// Add empty tiles
				tiles = addEmptyTilesAtBottom(tiles);
				// Put the board back together
				tiles = AddColumnToBoard(tiles, row);
			}
			// if statement for testing purposes
			// Only adds a random tile if emptyBoard is false
			if (!emptyBoard) {
				newRandomTile();
			}
		} else {
			System.out.println("Move cannot be made");
		}
	}

	/**
	 * @brief Method to move the tiles down in the board.
	 * @details This method moves the tiles down in the board and merges any appropriate tiles.
	 * It first strips the board apart into rows and columns, completes an appropriate merge, and then
	 * puts the board back together.
	 */
	public void moveDown() {
		if (isMovePossible()) {
			// Indicator that the tiles are moving down to invert the merging
			movingRightOrDown = true;
			List<Tile> tiles;
			for (int row = 0; row < size; row++) {
				// Separate empty tiles
				tiles = removeEmptyTilesFromColumn(row);
				// Merge appropriate tiles if possible
				tiles = mergeTiles(tiles);
				// Add empty tiles
				tiles = addEmptyTilesOnTop(tiles);
				// Put the board back together
				tiles = AddColumnToBoard(tiles, row);
			}
			// if statement for testing purposes
			// Only adds a random tile if emptyBoard is false
			if (!emptyBoard) {
				newRandomTile();
			}
		} else {
			System.out.println("Move cannot be made");
		}
	}

	/**
	 * @brief Method to move the tiles left in the board.
	 * @details This method moves the tiles left in the board and merges any appropriate tiles.
	 * It first strips the board apart into rows and columns, completes an appropriate merge, and then
	 * puts the board back together.
	 */
	public void moveLeft() {
		if (isMovePossible()) {
			List<Tile> tiles;
			for (int row = 0; row < size; row++) {
				// Separate empty tiles
				tiles = removeEmptyTilesFromRow(row);
				// Merge appropriate tiles if possible
				tiles = mergeTiles(tiles);
				// Add empty tiles
				tiles = addEmptyTilesAtBottom(tiles);
				// Put the board back together
				tiles = AddRowToBoard(tiles, row);
			}
			// if statement for testing purposes
			// Only adds a random tile if emptyBoard is false
			if (!emptyBoard) {
				newRandomTile();
			}
		} else {
			System.out.println("Move cannot be made");
		}
	}

	/**
	 * @brief Method to move the tiles right in the board.
	 * @details This method moves the tiles right in the board and merges any appropriate tiles.
	 * It first strips the board apart into rows and columns, completes an appropriate merge, and then
	 * puts the board back together.
	 */
	public void moveRight() {
		if (isMovePossible()) {
			// Indicator that the tiles are moving right to invert the merging
			movingRightOrDown = true;
			List<Tile> tiles;
			for (int row = 0; row < size; row++) {
				// Separate empty tiles
				tiles = removeEmptyTilesFromRow(row);
				// Merge appropriate tiles if possible
				tiles = mergeTiles(tiles);
				// Add empty tiles
				tiles = addEmptyTilesOnTop(tiles);
				// Put the board back together
				tiles = AddRowToBoard(tiles, row);
			}
			// if statement for testing purposes
			// Only adds a random tile if emptyBoard is false
			if (!emptyBoard) {
				newRandomTile();
			}
		} else {
			System.out.println("Move cannot be made");
		}
	}

	/**
	 * @brief Method to determine whether the game is over or not.
	 * @details If the tile 2048 is found, then the game is won. If the board is full, and no moves
	 * are possible, then the game is over. If more moves are possible, then the game is not over yet.
	 * @return true or false, depending whether the game is over or not.
	 */
	public static boolean isGameOver() {
		if (gameOver) {
			System.out.println();
			System.out.println("Game WON!");
			return true;
		} else {
			if (isFull()) {
				if (!isMovePossible()) {
					System.out.println();
					System.out.println("Game over");
					return true;
				}
			} else {
				return false;
			}
		}
		return true;
	}

	/**
	 * @brief Method to restart the game and get a new board .
	 */
	public void restart(int size) {
		new Board(size);
	}
}