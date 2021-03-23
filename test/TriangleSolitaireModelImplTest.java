import marblesolitaure.model.driver.TriangleSolitaireModelImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * A junit test suite for the European Solitaire Model Implementation class.
 */
public class TriangleSolitaireModelImplTest {
  public TriangleSolitaireModelImpl board1;
  public TriangleSolitaireModelImpl board2;
  public TriangleSolitaireModelImpl board3;
  public TriangleSolitaireModelImpl board4;

  /**
   * Sets up european marble solitaire boards using each of the four constructors.
   */
  @Before
  public void setUp() {
    board1 = new TriangleSolitaireModelImpl();
    board2 = new TriangleSolitaireModelImpl(7);
    board3 = new TriangleSolitaireModelImpl(3, 1);
    board4 = new TriangleSolitaireModelImpl(4,1, 0);
  }

  /**
   * Tests that the four constructor methods work as well as the getGameState method.
   */
  @Test
  public void constructorTest() {
    String expectedString1 = "    _\n"
            + "   O O\n"
            + "  O O O\n"
            + " O O O O\n"
            + "O O O O O";
    assertEquals(expectedString1, board1.getGameState());
    String expectedString2 = "      _\n"
            + "     O O\n"
            + "    O O O\n"
            + "   O O O O\n"
            + "  O O O O O\n"
            + " O O O O O O\n"
            + "O O O O O O O";
    assertEquals(expectedString2, board2.getGameState());
    String expectedString3 = "    O\n"
            + "   O O\n"
            + "  O O O\n"
            + " O _ O O\n"
            + "O O O O O";
    assertEquals(expectedString3, board3.getGameState());
    String expectedString4 = "   O\n"
            + "  _ O\n"
            + " O O O\n"
            + "O O O O";
    assertEquals(expectedString4, board4.getGameState());
  }

  /**
   * Various tests for argument errors in the constructors.
   * This first one tests a starting space out of bounds.
   */
  @Test(expected = IllegalArgumentException.class)
  public void outsideBoundsErrorTest() {
    new TriangleSolitaireModelImpl(7, 7);
  }

  /**
   * Tests a starting space in an invalid space.
   */
  @Test(expected = IllegalArgumentException.class)
  public void invalidStartingSpotTest() {
    new TriangleSolitaireModelImpl(0, 1);
  }

  /**
   * Tests arm thickness of a negative number.
   */
  @Test(expected = IllegalArgumentException.class)
  public void negArmThicknessTest() {
    new TriangleSolitaireModelImpl(-5);
  }

  /**
   * Goes through a normal game and tests input and methods throughout. All input is valid and
   * no exceptions should be thrown.
   */
  @Test
  public void validGameTest() {
    assertFalse(board1.isGameOver());
    assertEquals(14, board1.getScore());
    board1.move(2,0,0,0);
    board1.move(4,0, 2, 0);
    assertEquals(12, board1.getScore());
    board1.move(3, 2, 3, 0);
    board1.move(1, 1, 3, 1);
    assertEquals(10, board1.getScore());
    board1.move(4,1, 2, 1);
    assertFalse(board1.isGameOver());
    board1.move(3, 0, 1, 0);
    board1.move(0,0,2,0);
    assertEquals(7, board1.getScore());
    board1.move(4, 3, 4, 1);
    board1.move(3, 3, 1, 1);
    assertFalse(board1.isGameOver());
    board1.move(1, 1, 3, 1);
    board1.move(2, 0, 4, 2);
    assertEquals(3, board1.getScore());
    board1.move(4, 1, 4, 3);
    board1.move(4, 4, 4, 2);
    assertTrue(board1.isGameOver());
    assertEquals(1, board1.getScore());
    String expectedString1 = "    _\n"
            + "   _ _\n"
            + "  _ _ _\n"
            + " _ _ _ _\n"
            + "_ _ O _ _";
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
    board3.move(3, -1, 3, 1);
  }

  /**
   * Tests case that source space is empty.
   */
  @Test(expected = IllegalArgumentException.class)
  public void moveFromEmptySpace() {
    // Set up a move that would otherwise be valid.
    board1.move(2, 0, 0, 0);
    board1.move(4,0,2, 0);
    // This last move is the error.
    board1.move(3, 0, 1, 0);
  }

  /**
   * Test case that source space is invalid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void moveFromInvalidSpace() {
    // Set up a move that would otherwise be valid.
    board1.move(2, 2, 0, 0);
    board1.move(2,0, 2, 2);
    // This last move is the error.
    board1.move(2, 3, 2, 1);
  }

  /**
   * Tests case that destination space is not empty.
   */
  @Test(expected = IllegalArgumentException.class)
  public void moveToNonemptySpace() {
    board1.move(3, 0, 1, 0);
  }

  /**
   * Tests case that destination space is not valid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void moveToInvalidSpace() {
    board1.move(1, 0, 1, 2);
  }

  /**
   * Tests moving over more than one space.
   */
  @Test(expected = IllegalArgumentException.class)
  public void moveTwoSpaces() {
    board1.move(3, 0, 0, 0);
  }

  /**
   * Tests moving diagonally in the illegal direction "/". The other direction is valid
   * for the triangle version "\".
   */
  @Test(expected = IllegalArgumentException.class)
  public void moveDiagonally() {
    // Set up
    board1.move(2, 2,0, 0);
    // Illegal move.
    board1.move(4, 0, 2, 2);
  }

  /**
   * Tests jumping an empty space.
   */
  @Test(expected = IllegalArgumentException.class)
  public void jumpEmptySpace() {
    // Setup
    board1.move(2, 0,0, 0);
    // Jump empty space
    board1.move(0, 0, 2, 0);
  }
}