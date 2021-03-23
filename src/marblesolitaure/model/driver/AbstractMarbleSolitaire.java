package marblesolitaure.model.driver;

import marblesolitaure.model.ISpace;
import marblesolitaure.model.MarbleSolitaireModel;

/**
 * An abstract class representing the methods and their implementations
 * that are common to all games (so far) of marble solitaire.
 */
public abstract class AbstractMarbleSolitaire implements MarbleSolitaireModel {
  protected ISpace[][] board;
  protected int armThickness;
  protected int sideLength;

  /**
   * A helper method that looks to see if a certain space is outside of the bounds of the game
   * board. Does not consider if the space is empty or valid or not.
   * @param row - int - the row in question.
   * @param col - col - the column in question.
   * @return - a boolean whether the space is in the bounds of the board.
   */
  protected boolean isOutsideBounds(int row, int col) {
    return (0 > row || sideLength <= row) || (0 > col || sideLength <= col);
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
  protected boolean checkValidMove(int fromRow, int fromCol, int toRow, int toCol) {
    if (isOutsideBounds(fromRow, fromCol) || isOutsideBounds(toRow, toCol)) {
      return false;
    } else if (board[fromRow][fromCol].isEmpty() || !board[fromRow][fromCol].isValid()) {
      return false;
    } else if (!board[toRow][toCol].isValid()) {
      return false;
    } else if (!board[toRow][toCol].isEmpty()) {
      return false;
    } else if (!(Math.abs(fromRow - toRow) == 2 && fromCol - toCol == 0)
            && !(fromRow - toRow == 0 && Math.abs(fromCol - toCol) == 2)) {
      return false;
    }

    int delRow = (fromRow + toRow) / 2;
    int delCol = (fromCol + toCol) / 2;

    return !board[delRow][delCol].isEmpty();
  }

  /**
   * Move a marble and update the board to show the new position of the marble as well as
   * the "jumped" marble that is now gone.
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow the row number of the position to be moved to
   *              (starts at 0)
   * @param toCol the column number of the position to be moved to
   *              (starts at 0)
   * @throws IllegalArgumentException if the user attempts to make a move that is invalid.
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    if (!checkValidMove(fromRow, fromCol, toRow, toCol)) {
      throw new IllegalArgumentException("That is not a valid move.");
    }

    int delRow = (fromRow + toRow) / 2;
    int delCol = (fromCol + toCol) / 2;

    board[delRow][delCol].toggleIsEmpty();
    board[fromRow][fromCol].toggleIsEmpty();
    board[toRow][toCol].toggleIsEmpty();
  }

  /**
   * Returns the state of the current board in String form.
   * @return - a String - the current state of the board.
   */
  @Override
  public String getGameState() {
    StringBuilder gameState = new StringBuilder();
    for (int i = 0; i < sideLength; i++) {
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
                  || checkValidMove(i, j, i, j - 2)) {
            return false;
          }
        }
      }
    }
    return true;
  }

  /**
   * Counts the number of "marbles" still on the board and returns that as the score. A lower score
   * means you did better!!
   *
   * @return - the score, an int.
   */
  @Override
  public int getScore() {
    int count = 0;

    for (int i = 0; i < sideLength; i++) {
      for (int j = 0; j < sideLength; j++) {
        if (!board[i][j].isEmpty()) {
          count++;
        }
      }
    }
    return count;
  }


}
