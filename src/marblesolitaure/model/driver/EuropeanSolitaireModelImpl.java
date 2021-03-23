package marblesolitaure.model.driver;

import marblesolitaure.model.ISpace;
import marblesolitaure.model.InvalidSpace;
import marblesolitaure.model.ValidSpace;

/**
 * A class representing the implementation of a game of Marble Solitaire with a
 * Euroepan style board. Includes the methods that differ from those of the abstract class.
 * This includes only the constructors as the game is played almost identically to the English
 * style. The only difference is the shape of the board.
 */
public class EuropeanSolitaireModelImpl extends AbstractMarbleSolitaire {

  /**
   * A helper method that builds a new board, an array of ISpaces with the
   * parameters below.
   *
   * @param armThickness - int - the thickness of each arm.
   * @param sideLength - int - the length of the side of the board.
   * @return - a 2D array of ISpace objects
   */
  private ISpace[][] generateEuropeanNewBoard(int armThickness, int sideLength) {
    this.board = new ISpace[sideLength][sideLength];
    for (int i = 0; i < sideLength; i++) {
      for (int j = 0; j < sideLength; j++) {
        if ((i + j < armThickness - 1) 
                || (i + j >=  (sideLength + 2 * armThickness - 2))
                || (Math.abs(i - j) >= 2 * armThickness - 1)) {
          this.board[i][j] = new InvalidSpace();
        } else {
          this.board[i][j] = new ValidSpace(false);
        }
      }
    }
    return this.board;
  }

  /**
   * Constructor that makes a default board with arm thickness of 3 and side length of seven.
   */
  public EuropeanSolitaireModelImpl() {
    this.armThickness = 3;
    this.sideLength = 3 * this.armThickness - 2;
    int center = this.sideLength / 2;

    this.board = generateEuropeanNewBoard(this.armThickness, this.sideLength);

    this.board[center][center].toggleIsEmpty();
  }

  /**
   * Constructor that makes a default board but allows the user to input where the beginning empty
   * space is.
   *
   * @param sRow - an int, the row of the empty space
   * @param sCol - an int, the column of the empty space
   * @throws IllegalArgumentException if the user attempts to enter a space that is invalid.
   */
  public EuropeanSolitaireModelImpl(int sRow, int sCol) throws IllegalArgumentException {
    armThickness = 3;
    sideLength = 3 * armThickness - 2;

    this.board = generateEuropeanNewBoard(armThickness, sideLength);

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
   * @param armThickness - int - the length of each side of the octagonal board.
   * @throws IllegalArgumentException - If the user attempts to enter a negative arm thickness or
   *      one that is not odd.
   */
  public EuropeanSolitaireModelImpl(int armThickness) throws IllegalArgumentException {
    if (armThickness % 2 != 1) {
      throw new IllegalArgumentException("Please enter a positive, "
              + "odd number as the arm thickness");
    }
    sideLength = 3 * armThickness - 2;
    int center = sideLength / 2;

    board = generateEuropeanNewBoard(armThickness, sideLength);

    board[center][center].toggleIsEmpty();
  }

  /**
   * The Complete custom board. Allows user to enter custom arm thickness
   * as well as starting empty space.
   * @param armThickness - int - the thickness of a side of the octagon shape.
   * @param sRow - int - the row of the empty space.
   * @param sCol - int - the column of the empty space.
   * @throws IllegalArgumentException if the user attempts to enter a nonpositive
   *     number or an even number for the arm thickness or chooses an invalid
   *     starting empty space.
   */
  public EuropeanSolitaireModelImpl(int armThickness, int sRow, int sCol)
          throws IllegalArgumentException {
    if (armThickness % 2 != 1) {
      throw new IllegalArgumentException("Please enter a positive, "
              + "odd number as the arm thickness");
    }
    sideLength = 3 * armThickness - 2;

    board = generateEuropeanNewBoard(armThickness, sideLength);

    if (isOutsideBounds(sRow, sCol) || board[sRow][sCol].isEmpty()) {
      throw new IllegalArgumentException("Invalid empty cell position ("
              + sRow + "," + sCol + ").");
    } else {
      board[sRow][sCol].toggleIsEmpty();
    }
  }
}
