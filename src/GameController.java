/**
 * Author: Michael Hammal
 * Description: Game Controller module
 */

package src;

import java.util.Scanner;

public class GameController {
    private Board model;
    private UserInterface view;
    private static GameController controller = null;

    // Define environment variable
    private Scanner keyboard = new Scanner(System.in);

    /**
     * @brief constructor
     * @param model - model module (Board)
     * @param view - view module (UseInterface)
     */
    private GameController(Board model, UserInterface view){
        this.model = model;
        this.view = view;
    }

    /**
     * @brief public static method for obtaining a single instance
     * @return the single GameController object
     */
    public static GameController getInstance(Board model, UserInterface view) {
        if (controller == null)
            controller = new GameController(model, view);
        return controller;
    }

    /**
     * @brief initialize the game
     */
    public void initializeGame(int size) {
        this.model = new Board(size);
    }

    /**
     * @brief updates the view module to display a welcome message
     */
    public void displayWelcomeMessage(){
        view.printWelcomeMessage();
    }

    /**
     * @brief updates the view module to display an invalid input message
     */
    public void displayInvalidInputMessage(){
        view.printInvalidInputMessage();
    }


    /**
     * @brief updates the view module to display a prompt asking the user to enter the size of the board
     */
    public void displayBoardSizePrompt(){
        view.printBoardSizePrompt();
    }

    /**
     * @brief updates the view module to display a prompt asking the user if they would like to restart the game
     */
    public void displayRestartPrompt(){
        view.printRestartPrompt();
    }

    /**
     * @brief updates the view module to display a prompt asking the user if they would like to quit the game
     */
    public void displayQuitPrompt(){
        view.printQuitPrompt();
    }

    /**
     * @brief updates the view module to display the board
     */
    public void displayBoard(){
        view.printBoard(model);
    }

    /**
     * @brief updates the view module to display an ending message
     */
    public void displayEndingMessage(){
        view.printEndingMessage();
    }

    /**
     * @brief updates the view module to display a prompt asking the user to enter a move
     */
    public void displayMovePrompt(){
        view.printMovePrompt();
    }

    /**
     * @brief get input input from the user
     * @return the input
     */
    public String readInput(){
        String input = "";
        input = keyboard.nextLine();
        return input.toLowerCase();
    }

    /**
     * @brief runs the game
     */
    public void runGame() {
        String input = "";
        displayWelcomeMessage();
        input = readInput();
        if (input.equals("no") || input.equals("No") || input.equals("NO")) {
            displayEndingMessage();
            System.exit(0);
        } else {
            displayBoardSizePrompt();
            input = readInput();
            int size = 0;
            try {
                size = Integer.parseInt(input);
            } catch (NumberFormatException e) { }
            if (size >= 4) {
                initializeGame(size);
            } else {
                displayInvalidInputMessage();
                System.out.println("Default board size is 4");
                initializeGame(4);
            }
            while (!Board.isGameOver()) {
                displayBoard();
                displayMovePrompt();
                input = readInput();
                if (input.equals("up")) {
                    model.moveUp();
                } else if (input.equals("down")) {
                    model.moveDown();
                } else if (input.equals("left")) {
                    model.moveLeft();
                } else if (input.equals("right")) {
                    model.moveRight();
                } else if (input.equals("restart")) {
                    displayRestartPrompt();
                    input = readInput();
                    if (input.equals("yes") || input.equals("Yes") || input.equals("YES")) {
                        displayBoardSizePrompt();
                        input = readInput();
                        try {
                            size = Integer.parseInt(input);
                        } catch (NumberFormatException e) {
                            displayInvalidInputMessage();
                            System.out.println("Default board size is 4");
                            model.restart(4);
                        }
                        if (size >= 4) {
                            model.restart(size);
                        } else {
                            displayInvalidInputMessage();
                            System.out.println("Default board size is 4");
                            model.restart(4);
                        }
                    }
                } else if (input.equals("quit") || input.equals("stop")) {
                    displayQuitPrompt();
                    input = readInput();
                    if (input.equals("yes") || input.equals("Yes") || input.equals("YES")) {
                        displayEndingMessage();
                        System.exit(0);
                    }
                } else {
                    displayInvalidInputMessage();
                    System.out.println("Please try again:");
                }
            }
            displayEndingMessage();
            System.exit(0);
        }
    }
}
