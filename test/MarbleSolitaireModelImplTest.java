import marblesolitaure.model.MarbleSolitaireModelImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A test suite for the MarbleSolitaireModelImpl class.
 */
public class MarbleSolitaireModelImplTest {
  public MarbleSolitaireModelImpl board1;
  public MarbleSolitaireModelImpl board2;
  public MarbleSolitaireModelImpl board3;
  public MarbleSolitaireModelImpl board4;

  @Before
  public void setUp() {
    board1 = new MarbleSolitaireModelImpl();
    board2 = new MarbleSolitaireModelImpl(5);
    board3 = new MarbleSolitaireModelImpl(3, 0);
    board4 = new MarbleSolitaireModelImpl(5, 4, 1);
  }

  /**
   * Tests the four constructors as well as the getGameState method.
   */
  @Test
  public void constructorTest() {
    String expectedString1 = "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O";
    assertEquals(expectedString1, board1.getGameState());
    String expectedString2 = "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O _ O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O";
    assertEquals(expectedString2, board2.getGameState());
    String expectedString3 = "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "_ O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O";
    assertEquals(expectedString3, board3.getGameState());
    String expectedString4 = "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "O _ O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O";
    assertEquals(expectedString4, board4.getGameState());
  }

  /**
   * Various tests for argument errors in the constructors.
   * This first one tests a starting space out of bounds.
   */
  @Test(expected = IllegalArgumentException.class)
  public void outsideBoundsErrorTest() {
    new MarbleSolitaireModelImpl(7, 7);
  }

  /**
   * Tests a starting space in an invalid space.
   */
  @Test(expected = IllegalArgumentException.class)
  public void invalidStartingSpotTest() {
    new MarbleSolitaireModelImpl(0, 0);
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
    assertEquals(false, board1.isGameOver());
    assertEquals(32, board1.getScore());
    board1.move(1,3,3,3);
    board1.move(4,3, 2, 3);
    assertEquals(30, board1.getScore());
    board1.move(6, 3, 4, 3);
    board1.move(3, 1, 3, 3);
    assertEquals(28, board1.getScore());
    board1.move(3,4, 3, 2);
    assertEquals(false, board1.isGameOver());
    board1.move(3, 6, 3, 4);
    assertEquals(true, board1.isGameOver());
    assertEquals(26, board1.getScore());
    String expectedString1 = "    O O O\n"
            + "    O _ O\n"
            + "O O O O O O O\n"
            + "O _ O _ O _ _\n"
            + "O O O O O O O\n"
            + "    O _ O\n"
            + "    O _ O";
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


