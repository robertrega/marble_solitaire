import marblesolitaure.model.ISpace;
import marblesolitaure.model.InvalidSpace;
import marblesolitaure.model.ValidSpace;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A Junit test for implementation of marble solitaire board space.
 */
public class ISpaceTest {
  ISpace invalid;
  ISpace valid;

  /**
   * Sets up a valid and an invalid space.
   */
  @Before
  public void setUp() {
    invalid = new InvalidSpace();
    valid = new ValidSpace(true);
  }

  /**
   * Tests the isValid method.
   */
  @Test
  public void isValid() {
    assertEquals(false, invalid.isValid());
    assertEquals(true, valid.isValid());
  }

  /**
   * Tests the isEmpty method as well as the EmptyToggle. Expect invalid
   * spaces to always be empty by default.
   */
  @Test
  public void isEmptyToggle() {
    assertEquals(true, invalid.isEmpty());
    invalid.toggleIsEmpty();
    assertEquals(true, invalid.isEmpty());
    assertEquals(true, valid.isEmpty());
    valid.toggleIsEmpty();
    assertEquals(false, valid.isEmpty());
  }

  /**
   * Tests the toString method.
   */
  @Test
  public void testToString() {
    assertEquals(" ", invalid.toString());
    assertEquals("_", valid.toString());
    valid.toggleIsEmpty();
    assertEquals("O", valid.toString());
  }
}