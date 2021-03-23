package marblesolitaure.model.driver;

import marblesolitaure.controller.MarbleSolitaireControllerImpl;
import marblesolitaure.model.MarbleSolitaireModel;

import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * The main driver function for a game of marble solitaire. Handles user input through
 * the command line and then creates a model and controller and allows the user to play.
 */
public final class MarbleSolitaire {
  /**
   * The main driver function for a game of marble solitaire. Handles user input through
   * the command line and then creates a model and controller and allows the user to play.
   * @param args - the user input.
   */
  public static void main(String[] args) {
    MarbleSolitaireModel model;
    int size = -1;
    int row = -1;
    int col = -1;

    Scanner scanner = new Scanner(System.in);

    String modelType = scanner.next();

    if (scanner.hasNext("-size")) {
      scanner.next();
      size = scanner.nextInt();
    }

    if (scanner.hasNext("-hole")) {
      scanner.next();
      row = scanner.nextInt();
      col = scanner.nextInt();
    }

    if (size == -1 && row == -1 && col == -1) {
      model = SolitaireFactory.createModel(modelType);
    } else if (row == -1 && col == -1) {
      model = SolitaireFactory.createModel(modelType, size);
    } else if (size == -1) {
      model = SolitaireFactory.createModel(modelType, row, col);
    } else {
      model = SolitaireFactory.createModel(modelType, size, row, col);
    }

    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(
            new InputStreamReader(System.in), System.out);

    controller.playGame(model);

  }
}
