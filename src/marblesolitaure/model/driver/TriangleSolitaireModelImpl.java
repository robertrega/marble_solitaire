package marblesolitaure.model.driver;

import marblesolitaure.model.ISpace;
import marblesolitaure.model.InvalidSpace;
import marblesolitaure.model.ValidSpace;

/**
 * A class representing the implementation of a game of Marble Solitaire with a
 * triangular board. Includes the methods that differ from those of the abstract class.
 * This includes the constructors, the getGameState method, and the methods that need to
 * check valid moves as triangle solitaire introduces a new dimension of valid moves. This
 * changes checkValidMove and isGameOver.
 */
public class TriangleSolitaireModelImpl extends AbstractMarbleSolitaire {

  /**
   * A helper method that builds a new board, an array of ISpaces with the
   * parameters below.
   * @param sideLength - an int
   * @return a two dimensional array of ISpaces, the board of the game instance.
   */
  private ISpace[][] generateNewTriangleBoard(int sideLength) {
    this.board = new ISpace[sideLength][sideLength];
    for (int i = 0; i < sideLength; i++) {
      for (int j = 0; j < sideLength; j++) {
        if (i >= j) {
          this.board[i][j] = new ValidSpace(false);
        } else {
          this.board[i][j] = new InvalidSpace();
        }
      }
    }
    return board;
  }

  /**
   * Default constructor that makes a default board with arm thickness
   * of 3 and side length of seven.
   */
  public TriangleSolitaireModelImpl() {
    sideLength = 5;
    this.board = generateNewTriangleBoard(sideLength);
    this.board[0][0].toggleIsEmpty();
  }

  /**
   * Constructor that makes a board with a user=specified dimension that will
   * become the length of each side of the triangular board.
   * @param dimensions - int - lendth of a side of the triangle.
   */
  public TriangleSolitaireModelImpl(int dimensions) {
    if (dimensions < 1) {
      throw new IllegalArgumentException("Please enter a positive "
              + "number as the dimension of the board.");
    }
    this.sideLength = dimensions;
    this.board = generateNewTriangleBoard(sideLength);
    this.board[0][0].toggleIsEmpty();
  }

  /**
   * Constructor that makes a triangular game board with a user specified open
   * starting space.
   * @param row - int - the row of the open starting space.
   * @param col - int - the column of the open starting space.
   * @throws IllegalArgumentException if user attempts to set a space that is
   *         out of bounds or invalid.
   */
  public TriangleSolitaireModelImpl(int row, int col) throws IllegalArgumentException {
    this.sideLength = 5;
    this.board = generateNewTriangleBoard(sideLength);
    if (isOutsideBounds(row, col) || !board[row][col].isValid()) {
      throw new IllegalArgumentException("Invalid empty cell position ("
              + row + "," + col + ").");
    } else {
      board[row][col].toggleIsEmpty();
    }
  }

  /**
   * The Complete custom board. Allows user to enter custom arm thickness
   * as well as starting empty space.
   * @param dimensions - int - the length of a side of the triangle.
   * @param row - int - the row of the empty space.
   * @param col - int - the column of the empty space.
   * @throws IllegalArgumentException if the user attempts to enter a nonpositive
   *     number or an even number for the side length or chooses an invalid
   *     starting empty space.
   */
  public TriangleSolitaireModelImpl(int dimensions, int row, int col)
          throws IllegalArgumentException {
    if (dimensions < 1) {
      throw new IllegalArgumentException("Please enter a positive "
              + "number as the dimension of the board.");
    }
    this.sideLength = dimensions;
    this.board = generateNewTriangleBoard(sideLength);
    if (isOutsideBounds(row, col) || board[row][col].isEmpty()) {
      throw new IllegalArgumentException("Invalid empty cell position ("
              + row + "," + col + ").");
    } else {
      board[row][col].toggleIsEmpty();
    }
  }

  /**
   * A helper function that checks if a move is invalid.
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow the row number of the position to be moved to
   *              (starts at 0)
   * @param toCol the column number of the position to be moved to
   *              (starts at 0)
   * @return - a boolean stating whether or not that move is possible.
   */
  @Override
  protected boolean checkValidMove(int fromRow, int fromCol, int toRow, int toCol) {
    if (isOutsideBounds(fromRow, fromCol) || isOutsideBounds(toRow, toCol)) {
      return false;
    } else if (board[fromRow][fromCol].isEmpty() || !board[fromRow][fromCol].isValid()) {
      return false;
    } else if (!board[toRow][toCol].isValid()) {
      return false;
    } else if (!board[toRow][toCol].isEmpty()) {
      return false;
      // Base movement arguments still apply.
      // New rules added for allowed diagonal movements.
    } else if (!(Math.abs(fromRow - toRow) == 2 && fromCol - toCol == 0)
            && !(fromRow - toRow == 0 && Math.abs(fromCol - toCol) == 2)
            // Diagonals
            && !(fromRow - toRow == 2 && fromCol - toCol == 2)
            && !(toRow - fromRow == 2 && toCol - fromCol == 2)) {
      return false;
    }

    int delRow = (fromRow + toRow) / 2;
    int delCol = (fromCol + toCol) / 2;

    return !board[delRow][delCol].isEmpty();
  }

  /**
   * Prints the current game state of a Triangle Marble Solitaire Model.
   * @return a String displaying the current game state.
   */
  @Override
  public String getGameState() {
    StringBuilder gameState = new StringBuilder();
    for (int i = 0; i < sideLength; i++) {
      // This loop with s is all that is different.
      for (int s = sideLength - i - 1; s > 0; s--) {
        gameState.append(" ");
      }
      for (int j = 0; j < sideLength; j++) {
        gameState.append(board[i][j].toString());
        gameState.append(" ");
      }
      for (int k = gameState.length() - 1; k >= 0; k--) {
        if (gameState.charAt(k) == ' ') {
          gameState.deleteCharAt(k);
        } else {
          break;
        }
      }
      gameState.append("\n");
    }

    return gameState.toString().substring(0, gameState.length() - 1);
  }

  /**
   * Checks to see if there are any available moves to be made.
   * @return true if there are no more moves, false if there are still some available moves.
   */
  @Override
  public boolean isGameOver() {
    for (int i = 0; i < sideLength; i++) {
      for (int j = 0; j < sideLength; j++) {
        if (!board[i][j].isEmpty()) {
          if (checkValidMove(i, j, i + 2, j)
                  || checkValidMove(i, j, i - 2, j)
                  || checkValidMove(i, j, i, j + 2)
                  || checkValidMove(i, j, i, j - 2)
                  || checkValidMove(i, j, i + 2, j + 2)
                  || checkValidMove(i, j, i - 2, j - 2)) {
            return false;
          }
        }
      }
    }
    return true;
  }
}
