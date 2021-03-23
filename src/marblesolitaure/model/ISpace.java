package marblesolitaure.model;

/**
 * An interface that outlines all of the methods of a space on a game board.
 */
public interface ISpace {
  /**
   * Returns whether or not space is valid (in play).
   * @return boolean - whether or not space is valid.
   */
  boolean isValid();

  /**
   * Returns whether or not space is empty. Invalid spaces are empty by definition.
   * @return boolean - whether or not space is empty.
   */
  boolean isEmpty();

  /**
   * Returns string representation of space.
   * @return - A String for each space.
   */
  String toString();

  /**
   * If the space is empty, marks it as not empty, and vice versa.
   */
  void toggleIsEmpty();
}
