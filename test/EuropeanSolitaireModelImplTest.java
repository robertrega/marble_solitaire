import marblesolitaure.model.MarbleSolitaireModelImpl;
import marblesolitaure.model.driver.EuropeanSolitaireModelImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * A junit test suite for the European Solitaire Model Implementation class.
 */
public class EuropeanSolitaireModelImplTest {
  public EuropeanSolitaireModelImpl board1;
  public EuropeanSolitaireModelImpl board2;
  public EuropeanSolitaireModelImpl board3;
  public EuropeanSolitaireModelImpl board4;

  /**
   * Sets up european marble solitaire boards using each of the four constructors.
   */
  @Before
  public void setUp() {
    board1 = new EuropeanSolitaireModelImpl();
    board2 = new EuropeanSolitaireModelImpl(5);
    board3 = new EuropeanSolitaireModelImpl(3, 0);
    board4 = new EuropeanSolitaireModelImpl(5, 4, 1);
  }

  /**
   * Tests that the four constructor methods work as well as the getGameState method.
   */
  @Test
  public void constructorTest() {
    String expectedString1 = "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O";
    assertEquals(expectedString1, board1.getGameState());
    String expectedString2 = "        O O O O O\n"
            + "      O O O O O O O\n"
            + "    O O O O O O O O O\n"
            + "  O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O _ O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "  O O O O O O O O O O O\n"
            + "    O O O O O O O O O\n"
            + "      O O O O O O O\n"
            + "        O O O O O";
    assertEquals(expectedString2, board2.getGameState());
    String expectedString3 = "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "_ O O O O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O";
    assertEquals(expectedString3, board3.getGameState());
    String expectedString4 = "        O O O O O\n"
            + "      O O O O O O O\n"
            + "    O O O O O O O O O\n"
            + "  O O O O O O O O O O O\n"
            + "O _ O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "  O O O O O O O O O O O\n"
            + "    O O O O O O O O O\n"
            + "      O O O O O O O\n"
            + "        O O O O O";
    assertEquals(expectedString4, board4.getGameState());
  }

  /**
   * Various tests for argument errors in the constructors.
   * This first one tests a starting space out of bounds.
   */
  @Test(expected = IllegalArgumentException.class)
  public void outsideBoundsErrorTest() {
    new EuropeanSolitaireModelImpl(7, 7);
  }

  /**
   * Tests a starting space in an invalid space.
   */
  @Test(expected = IllegalArgumentException.class)
  public void invalidStartingSpotTest() {
    new EuropeanSolitaireModelImpl(0, 0);
  }

  /**
   * Tests arm thickness of a negative number.
   */
  @Test(expected = IllegalArgumentException.class)
  public void negArmThicknessTest() {
    new MarbleSolitaireModelImpl(-5);
  }

  /**
   * Tests arm thickness of an even number.
   */
  @Test(expected = IllegalArgumentException.class)
  public void evenArmThicknessTest() {
    new MarbleSolitaireModelImpl(4);
  }

  /**
   * Goes through a normal game and tests input and methods throughout. All input is valid and
   * no exceptions should be thrown.
   */
  @Test
  public void validTest() {
    assertFalse(board1.isGameOver());
    assertEquals(36, board1.getScore());
    board1.move(1,3,3,3);
    board1.move(4,3, 2, 3);
    assertFalse(board1.isGameOver());
    board1.move(6, 3, 4, 3);
    board1.move(3, 1, 3, 3);
    assertEquals(32, board1.getScore());
    board1.move(3,4, 3, 2);
    board1.move(3, 6, 3, 4);
    assertFalse(board1.isGameOver());
    board1.move(1, 1, 1, 3);
    board1.move(1, 5, 3, 5);
    assertEquals(28, board1.getScore());
    board1.move(3, 4, 3, 6);
    board1.move(5, 1, 3, 1);
    assertFalse(board1.isGameOver());
    board1.move(5, 5, 3,5);
    board1.move(3, 6, 3, 4);
    assertEquals(24, board1.getScore());
    board1.move(4, 3, 4, 1);
    assertFalse(board1.isGameOver());
    board1.move(3, 1, 3, 3);
    board1.move(3, 4, 3, 2);
    assertEquals(21, board1.getScore());
    board1.move(6, 2, 4, 2);
    board1.move(3, 2, 5, 2);
    assertFalse(board1.isGameOver());
    board1.move(5, 4, 3, 4);
    board1.move(2, 4, 4, 4);
    assertEquals(17, board1.getScore());
    board1.move(0, 4, 2, 4);
    board1.move(2, 3, 2, 5);
    assertFalse(board1.isGameOver());
    board1.move(2, 6, 2, 4);
    board1.move(0, 2, 0, 4);
    assertEquals(13, board1.getScore());
    board1.move(2,1, 2, 3);
    board1.move(4,0, 4, 2);
    assertFalse(board1.isGameOver());
    board1.move(2, 0, 4, 0);
    board1.move(5, 2, 3, 2);
    assertEquals(9, board1.getScore());
    board1.move(2, 3, 0, 3);
    board1.move(0, 4, 0, 2);
    assertEquals(7, board1.getScore());
    assertTrue(board1.isGameOver());

    String expectedString1 = "    O _ _\n"
            + "  _ _ _ _ _\n"
            + "_ _ _ _ O _ _\n"
            + "_ _ O _ _ _ _\n"
            + "O _ _ _ O _ O\n"
            + "  _ _ _ _ _\n"
            + "    _ _ O";
    assertEquals(expectedString1, board1.getGameState());
  }

  /**
   * The next few tests test when an invalid move is attempted.
   * This one tests moving a piece outside bounds.
   */
  @Test(expected = IllegalArgumentException.class)
  public void moveToOutsideBounds() {
    board1.move(2, 1, 2, -1);
  }

  /**
   * Tests trying to move a piece from outside bounds.
   */
  @Test(expected = IllegalArgumentException.class)
  public void moveFromOutsideBounds() {
    board1.move(2, -1, 2, 1);
  }

  /**
   * Tests case that source space is empty.
   */
  @Test(expected = IllegalArgumentException.class)
  public void moveFromEmptySpace() {
    // Set up a move that would otherwise be valid.
    board1.move(3, 1, 3, 3);
    board1.move(3,4,3, 2);
    // This last move is the error.
    board1.move(3, 5, 3, 3);
  }

  /**
   * Test case that source space is invalid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void moveFromInvalidSpace() {
    board3.move(1, 0, 3, 0);
  }

  /**
   * Tests case that destination space is not empty.
   */
  @Test(expected = IllegalArgumentException.class)
  public void moveToNonemptySpace() {
    board1.move(3, 0, 3, 2);
  }

  /**
   * Tests case that destination space is not valid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void moveToInvalidSpace() {
    board1.move(3, 0, 1, 0);
  }

  /**
   * Tests moving over more than one space.
   */
  @Test(expected = IllegalArgumentException.class)
  public void moveTwoSpaces() {
    board1.move(3, 0, 3, 3);
  }

  /**
   * Tests moving diagonally.
   */
  @Test(expected = IllegalArgumentException.class)
  public void moveDiagonally() {
    board2.move(4, 4,6, 6);
  }

  /**
   * Tests jumping an empty space.
   */
  @Test(expected = IllegalArgumentException.class)
  public void jumpEmptySpace() {
    // Setup
    board1.move(3, 1,3, 3);
    // Jump empty space
    board1.move(3, 3, 3, 1);
  }
}
