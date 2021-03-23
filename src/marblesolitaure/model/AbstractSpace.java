package marblesolitaure.model;

/**
 * An abstract class outlining a space and all of the methods that come with them.
 */
public abstract class AbstractSpace implements ISpace {

  /**
   * Returns whether or not space is valid (in play). False by default.
   * @return boolean - whether or not space is valid.
   */
  @Override
  public boolean isValid() {
    return false;
  }

  /**
   * Returns whether or not space is empty. Invalid spaces are empty by definition.
   * @return boolean - whether or not space is empty.
   */
  @Override
  public boolean isEmpty() {
    return true;
  }

  /**
   * Returns string representation of space.
   * @return - A String for each space.
   */
  @Override
  public String toString() {
    return null;
  }

  /**
   * If the space is empty, marks it as not empty, and vice versa.
   * Not defined in an invalid space - should not do anything for it.
   */
  @Override
  public void toggleIsEmpty() {
    /* Method is empty in the abstract class because by default it should do nothing.
     * This case is carried over for an invalid space but overridden for a valid space.
     */
  }
}
