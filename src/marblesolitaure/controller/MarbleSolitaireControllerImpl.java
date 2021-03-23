package marblesolitaure.controller;

import marblesolitaure.model.MarbleSolitaireModel;

import java.io.IOException;
import java.util.Scanner;

/**
 * This interface represents the controller for one game of marble solitaire. It takes
 * user in put in the form of a string reader (expanded to command line later). A single
 * turn is represented by the user making one valid move. This controller also handles
 * the user winning or losing the game as well as quitting of their own volition.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
  public final Readable rd;
  public final Appendable ap;

  /**
   * A constructor for the implementation class for marble solitaire. Takes in a readable and
   * appendable object.
   *
   * @param rd - a readable object. A stringReader, usually.
   * @param ap - an appendable object. A stringBuilder, usually.
   * @throws IllegalArgumentException if readable or appendable is null.
   */
  public MarbleSolitaireControllerImpl(Readable rd, Appendable ap) throws IllegalArgumentException {
    if (rd == null || ap == null) {
      throw new IllegalArgumentException("Readable or Appendable not found.");
    }
    this.rd = rd;
    this.ap = ap;
  }

  /**
   * Prints out the current game state.
   * @param model the current model to be printed as a string.
   */
  private void printGameState(MarbleSolitaireModel model) {
    try {
      this.ap.append(model.getGameState() + "\n"
              + String.format("Score: %d\n", model.getScore()));
    } catch (IOException e) {
      throw new IllegalStateException("Unable to write to appendable!");
    }
    System.out.print(this.ap);
  }

  /**
   * A method to end the game if the user chooses to end the game. Prints the
   * final game state.
   * @param model the current model to quit.
   */
  private void quitGame(MarbleSolitaireModel model) {
    try {
      this.ap.append("Game quit!\n"
              + "State of game when quit:\n"
              + model.getGameState() + "\n"
              + String.format("Score: %d\n", model.getScore()));
    } catch (IOException e) {
      throw new IllegalStateException("Unable to write to appendable!");
    }
    System.out.print(this.ap);
  }

  /**
   * A helper method to end the game if the isGameOver model method returns true.
   * Prints the final game state.
   * @param model the current model to be checked.
   */
  private void gameOver(MarbleSolitaireModel model) {
    try {
      this.ap.append("Game over!\n"
              + model.getGameState() + "\n"
              + String.format("Score: %d\n", model.getScore()));
    } catch (IOException e) {
      throw new IllegalStateException("Unable to write to appendable!");
    }
    System.out.print(this.ap);
  }

  /**
   * The main driver of the marble solitaire game. Handles input from the user and
   * talks with the model.
   *
   * @param model -  an instance of the marble solitaire model.
   * @throws IllegalArgumentException when a null model is passed to it, or player
   *         attempts to make an invalid move. An attempted move with negative integers
   *         or non integers will not throw an exception and will just prompt the
   *         user for input again.
   * @throws IllegalStateException when unable to write to the appendable,
   *         or readable runs out of input.
   */
  @Override
  public void playGame(MarbleSolitaireModel model) throws IllegalArgumentException,
          IllegalStateException {
    if (model == null) {
      throw new IllegalArgumentException("No board model was found.");
    }

    int fromRow = 0;
    int fromCol = 0;
    int toRow = 0;
    int toCol = 0;
    Scanner input = new Scanner(this.rd);

    /* make this into a list perhaps ?? This works but seems like it could be streamlined.
    / Loop turns of the game until game exits by user typing 'q', or losing or
    / winning the game.
    */
    while (true) {
      printGameState(model);

      // Read in four integers for four required positions for a move.
      // Will prompt the user for whatever input is invalid.
      // Always looking for a q if the user wants to quit.
      int i = 0;
      while (i < 4) {
        if (!input.hasNext()) {
          throw new IllegalStateException("No more data to read. Please give more input.");
        } else if (input.hasNext("q") || input.hasNext("Q")) {
          quitGame(model);
          return;
        } else if (input.hasNextInt()) {
          int num = input.nextInt();
          if (num < 1) {
            System.out.print("Please enter a positive integer for board position.\n");
          } else if (i == 0) {
            fromRow = num - 1;
            i++;
          } else if (i == 1) {
            fromCol = num - 1;
            i++;
          } else if (i == 2) {
            toRow = num - 1;
            i++;
          } else if (i == 3) {
            toCol = num - 1;
            i++;
          }
        } else if (i == 0) {
          System.out.print("Please enter a valid source row.\n");
          input.next();
        } else if (i == 1) {
          System.out.print("Please enter a valid source column.\n");
          input.next();
        } else if (i == 2) {
          System.out.print("Please enter a valid destination row.\n");
          input.next();
        } else if (i == 3) {
          System.out.print("Please enter a valid destination column.\n");
          input.next();
        }
      }
      // After data input and checking, attempt to make the move. If move is
      // invalid, all four data will be forgotten.
      try {
        model.move(fromRow, fromCol, toRow, toCol);
      } catch (IllegalArgumentException e) {
        System.out.print("Invalid move. Play again.\n");
      }

      // Check after each move if game is over. If yes, print message, score, and game state.
      if (model.isGameOver()) {
        gameOver(model);
        return;
      }
    }
  }
}

