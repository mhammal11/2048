/**
 * Author: Michael Hammal
 * Description: Start module
 */

package src;

public class Start {
    public static void main(String[] args) {
        Board board = new Board(4);
        UserInterface UI = UserInterface.getInstance();
        GameController game = GameController.getInstance(board, UI);
        game.runGame();
    }
}
