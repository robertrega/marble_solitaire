package marblesolitaure.model;

import marblesolitaure.model.driver.AbstractMarbleSolitaire;

/**
 * The implementation of the Marble Solitaire game board. Extends the abstract class
 * and includes specific implementations of the English Marble Solitaire game board.
 */
public class MarbleSolitaireModelImpl extends AbstractMarbleSolitaire {

  /**
   * A helper method that builds a new board, an array of ISpaces with the
   * parameters below.
   *
   * @param armThickness - int - the thickness of each arm.
   * @param sideLength - int - the length of the side of the board.
   * @return - a 2D array of ISpace objects
   */
  private ISpace[][] generateNewBoard(int armThickness, int sideLength) {
    this.board = new ISpace[sideLength][sideLength];
    for (int i = 0; i < sideLength; i++) {
      for (int j = 0; j < sideLength; j++) {
        if ((i < armThickness - 1 || i > sideLength - armThickness)
                && (j < armThickness - 1 || j > sideLength - armThickness)) {
          this.board[i][j] = new InvalidSpace();
        } else {
          this.board[i][j] = new ValidSpace(false);
        }
      }
    }
    return board;
  }

  /**
   * Constructor that makes a default board with arm thickness of 3 and side length of seven.
   */
  public MarbleSolitaireModelImpl() {
    armThickness = 3;
    sideLength = 3 * armThickness - 2;
    int center = sideLength / 2;

    this.board = generateNewBoard(armThickness, sideLength);

    this.board[center][center].toggleIsEmpty();
  }

  /**
   * Constructor that makes a default board but allows the user to input where the beginning empty
   * space is.
   *
   * @param sRow - an int, the row of the empty space
   * @param sCol - an int, the column of the empty space
   * @throws IllegalArgumentException if the user attempts to enter a space that is invalid or
   *         out of bounds.
   */
  public MarbleSolitaireModelImpl(int sRow, int sCol) throws IllegalArgumentException {
    armThickness = 3;
    sideLength = 3 * armThickness - 2;

    this.board = generateNewBoard(armThickness, sideLength);

    if (isOutsideBounds(sRow, sCol) || !board[sRow][sCol].isValid()) {
      throw new IllegalArgumentException("Invalid empty cell position ("
              + sRow + "," + sCol + ").");
    } else {
      board[sRow][sCol].toggleIsEmpty();
    }
  }

  /**
   * Constructor that makes a board with a user-entered arm thickness.
   *
   * @param armThickness - int - the length of each "arm" of the plus shape that will be the board.
   * @throws IllegalArgumentException - If the user attempts to enter a negative arm thickness or
   *      one that is not odd.
   */
  public MarbleSolitaireModelImpl(int armThickness) throws IllegalArgumentException {
    if (armThickness % 2 != 1) {
      throw new IllegalArgumentException("Please enter a positive, "
              + "odd number as the arm thickness");
    }
    sideLength = 3 * armThickness - 2;
    int center = sideLength / 2;

    board = generateNewBoard(armThickness, sideLength);

    board[center][center].toggleIsEmpty();
  }

  /**
   * The Complete custom board. Allows user to enter custom arm thickness
   * as well as starting empty space.
   * @param armThickness - int - the thickness of the "arm" of the plus shape.
   * @param sRow - int - the row of the empty space.
   * @param sCol - int - the column of the empty space.
   * @throws IllegalArgumentException if the user attempts to enter a nonpositive
   *     number or an even number for the arm thickness or chooses an invalid
   *     starting empty space.
   */
  public MarbleSolitaireModelImpl(int armThickness, int sRow, int sCol)
          throws IllegalArgumentException {
    if (armThickness % 2 != 1) {
      throw new IllegalArgumentException("Please enter a positive, "
              + "odd number as the arm thickness");
    }
    sideLength = 3 * armThickness - 2;

    board = generateNewBoard(armThickness, sideLength);

    if (isOutsideBounds(sRow, sCol) || board[sRow][sCol].isEmpty()) {
      throw new IllegalArgumentException("Invalid empty cell position ("
              + sRow + "," + sCol + ").");
    } else {
      board[sRow][sCol].toggleIsEmpty();
    }
  }
}
