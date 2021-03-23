package marblesolitaure.model;

/**
 * This class represents an invalid space. It cannot have a marble and is by definition empty.
 */
public class InvalidSpace extends AbstractSpace {

  /**
   * Returns string representation of space. " " empty space for invalidSpace.
   * @return - A String for each space.
   */
  @Override
  public String toString() {
    return " ";
  }
}
