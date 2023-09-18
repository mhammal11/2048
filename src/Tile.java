/**
 * Author: Michael Hammal
 * Description: Tile module
 */

package src;

/**
 * @brief A module that represents tiles in the game.
 * @details Each board is made up of tiles which are represented here.
 */
public class Tile {

	private int row;
	private int col;
	private int value;

	/**
	 * @brief Initializes a tile object.
	 * @details If no parameters are given, the tile is initialized with value 0.
	 */
	public Tile() {
		new Tile(0);
	}

	/**
	 * @brief Initializes a tile object.
	 * @details If a value is given, the tile is initialized with that value.
	 * @param value The value of the tile.
	 */
	public Tile(int value) {
		this.value = value;
	}

	/**
	 * @brief Initializes a tile object.
	 * @details If a value, row, and column are given, the tile is initialized with that value
	 * in the location provided by the row and column.
	 * @param value The value of the tile.
	 * @param row The row where the tile is located.
	 * @param col The column where the tile is located.
	 */
	public Tile(int value, int row, int col) {
		this.value = value;
		this.row = row;
		this.col = col;
	}

	/**
	 * @brief Method to merge the value of two tiles.
	 * @return Merged value of two tiles.
	 */
	public int merge() {
		return (this.value += this.value);
	}

	/**
	 * @brief Method to get the value of a tile.
	 * @return Value of a tile.
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @brief Method to set the value of a tile.
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * @brief Method to determine whether a tile is empty or not.
	 * @details A tile is considered empty if its value is equal to 0.
	 * @return true is the tile is empty and false otherwise.
	 */
	public boolean isEmpty() {
		return (value == 0);
	}

	/**
	 * @brief Method to output a tile to the console.
	 * @return A value representing the tile.
	 */
	@Override
	public String toString() {
		return "" + value;
	}
	
}
