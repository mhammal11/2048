/**
 * Author: Michael Hammal
 * Description: User Interface View module
 */

package src;

public class UserInterface {
    private static UserInterface visual = null;

    /**
     * @brief constructor
     */
    private UserInterface(){}

    /**
     * @brief public static method for obtaining a single instance
     * @return a UserInterface object
     */
    public static UserInterface getInstance(){
        if (visual == null)
            return visual = new UserInterface();
        return visual;
    }

    /**
     * @brief display a welcome message
     */
    public void printWelcomeMessage(){
        System.out.print("\033[0;1m");
        System.out.println();
        System.out.println("-------------------------------------------------");
        System.out.println("                 Welcome to 2048                 ");
        System.out.println("-------------------------------------------------");
        System.out.println();
        System.out.print("\033[0;0m");
        System.out.println("At any point during the game, you can enter \033[0;1m'restart'\033[0;0m to restart the game or \033[0;1m'quit'\033[0;0m to quit the game.");
        System.out.println();
        System.out.println("If you would like to start the game, enter \033[0;1manything\033[0;0m. Otherwise, enter \033[0;1m'no'\033[0;0m.");
    }

    /**
     * @brief display an invalid input message
     */
    public void printInvalidInputMessage(){
        System.out.println();
        System.out.println("Invalid input");
    }

    /**
     * @brief display a prompt asking the user to enter a board size
     */
    public void printBoardSizePrompt(){
        System.out.println();
        System.out.println("Enter desired board \033[0;1msize\033[0;0m (You can enter values of \033[0;1m4\033[0;0m or larger):");
    }

    /**
     * @brief display a prompt asking the user if they would like to restart the game
     */
    public void printRestartPrompt(){
        System.out.println();
        System.out.println("Would you like to \033[0;1mrestart\033[0;0m the game?");
    }

    /**
     * @brief display a prompt asking the user if they would like to quit the game
     */
    public void printQuitPrompt(){
        System.out.println();
        System.out.println("Would you like to \033[0;1mquit\033[0;0m the game?");
    }

    /**
     * @brief display a prompt asking the user to enter a move
     */
    public void printMovePrompt(){
        System.out.println("");
        System.out.println("Enter desired move:");
        System.out.println("Possible moves are: ");
        System.out.print("\033[0;1m");
        System.out.print("up, down, left, right");
        System.out.println("\033[0;0m");
    }

    /**
     * @brief display the game using ASCII text-based graphics
     */
    public void printBoard(Board board) {
        System.out.println();
        System.out.println("\033[0;1mScore: " + board.getScore());
        for (int i = 0; i < board.getTiles().size(); i++) {
            if (i == 0) {
                System.out.println("╔═══════╦═══════╦═══════╦═══════╗");
                System.out.print("║   ");

            } else {
                System.out.println("╠═══════╬═══════╬═══════╬═══════╣");
                System.out.print("║   ");
            }
            for (int j = 0; j < board.getTiles().size(); j++) {
                if (board.getTileAt(i,j).getValue() == 0) {
                    System.out.format("    ");
                    System.out.print("║   ");
                } else {
                    System.out.print("\033[0;0m");
                    System.out.format("%-4d", board.getTileAt(i, j).getValue());
                    System.out.print("║   ");
                }
            }
            System.out.println();
        }
        System.out.println("╚═══════╩═══════╩═══════╩═══════╝");
    }

    /**
     * @brief display an ending message after user choose to exit the game
     */
    public void printEndingMessage(){
        System.out.println();
        System.out.println("-------------------------------------------------");
        System.out.println("             Thank You For Playing !!!           ");
        System.out.println("-------------------------------------------------");
        System.out.println();
        System.out.println("Enter \033[0;1m'make start'\033[0;0m if you would like to play again!");
    }

}
