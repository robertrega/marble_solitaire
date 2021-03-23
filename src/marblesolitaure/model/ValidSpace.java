package marblesolitaure.model;

/**
 * A class representing a valid space. This is a space that is in play and can have a marble or
 * no marble.
 */
public class ValidSpace extends AbstractSpace {
  private boolean empty;

  /**
   * Constructs a valid space.
   * @param empty - whether or not the space is empty (has a marble or not)
   */
  public ValidSpace(boolean empty) {
    this.empty = empty;
  }

  /**
   * Returns whether or not space is valid (in play). False by default.
   * @return boolean - whether or not space is valid.
   */
  @Override
  public boolean isValid() {
    return true;
  }

  /**
   * Returns whether or not space is empty.
   * @return boolean - whether or not space is empty.
   */
  @Override
  public boolean isEmpty() {
    return empty;
  }

  /**
   * Returns string representation of space. "_" for empty and "O" for not.
   * @return - A String for each space.
   */
  @Override
  public String toString() {
    if (this.isEmpty()) {
      return "_";
    } else {
      return "O";
    }
  }

  /**
   * If the space is empty, marks it as not empty, and vice versa.
   */
  @Override
  public void toggleIsEmpty() {
    this.empty = !this.isEmpty();
  }
}
