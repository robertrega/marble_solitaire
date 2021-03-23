import marblesolitaure.controller.MarbleSolitaireController;
import marblesolitaure.controller.MarbleSolitaireControllerImpl;
import marblesolitaure.model.MarbleSolitaireModel;
import marblesolitaure.model.MarbleSolitaireModelImpl;
import org.junit.Test;

import java.io.StringReader;

import static org.junit.Assert.assertEquals;

/**
 * A Junit test suite for the MarbleSolitaireControllerImpl class. Each test
 * creates a new board and input strings to go with them.
 */
public class MarbleSolitaireControllerImplTest {

  /**
   * Test quitting after one valid move.
   */
  @Test
  public void testOneMoveQuit() {
    String expected = "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O _ O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 31\n";
    Readable input = new StringReader("2 4 4 4 q");
    Appendable output = new StringBuilder();
    MarbleSolitaireModel basic = new MarbleSolitaireModelImpl();
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(input, output);
    controller.playGame(basic);

    String opString = output.toString();
    // Check last output string
    assertEquals(expected,
            opString.substring(opString.length() - expected.length(), opString.length()));
  }

  /**
   * Tests a mull model.
   */
  @Test(expected = IllegalArgumentException.class)
  public void nullModel() {
    Readable input = new StringReader("2 4 4 4 q");
    Appendable output = new StringBuilder();
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(input, output);
    controller.playGame(null);
  }

  /**
   * Tests the exception when the reader runs out of data.
   */
  @Test(expected = IllegalStateException.class)
  public void readableRunsOut() {
    Readable input = new StringReader("2 4 4 4 2 5 7");
    Appendable output = new StringBuilder();
    MarbleSolitaireModel basic = new MarbleSolitaireModelImpl();
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(input, output);
    controller.playGame(basic);
  }

  /**
   * Test a null Readable object.
   */
  @Test(expected = IllegalArgumentException.class)
  public void nullReadable() {
    Appendable output = new StringBuilder();
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(null, output);
  }

  /**
   * Test a null Appendable object.
   */
  @Test(expected = IllegalArgumentException.class)
  public void nullAppendable() {
    Readable input = new StringReader("2 4 4 4");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(input, null);
  }

  /**
   * Test a single move with invalid string inputs.
   */
  @Test
  public void invalidInputs() {
    String expected = "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O _ O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 31\n";
    Readable input = new StringReader("2 4 two 4 4 cow q");
    Appendable output = new StringBuilder();
    MarbleSolitaireModel basic = new MarbleSolitaireModelImpl();
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(input, output);
    controller.playGame(basic);

    String opString = output.toString();
    // Check last output string
    assertEquals(expected,
            opString.substring(opString.length() - expected.length(), opString.length()));
  }

  /**
   * This test makes sure that the scanner excepts line breaks as well as spaces when
   * scanning.
   */
  @Test
  public void testLineBreak() {
    String expected = "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O _ O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 31\n";
    Readable input = new StringReader("2\n4\n4\n4\nq");
    Appendable output = new StringBuilder();
    MarbleSolitaireModel basic = new MarbleSolitaireModelImpl();
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(input, output);
    controller.playGame(basic);

    String opString = output.toString();
    // Check last output string
    assertEquals(expected,
            opString.substring(opString.length() - expected.length(), opString.length()));
  }

  /**
   * Tests a complete game with two marbles left. It has one invalid move thrown in.
   */
  @Test
  public void twoLeft() {
    String expected = "Game over!\n"
            + "    _ _ _\n"
            + "    _ _ _\n"
            + "_ _ _ _ _ _ _\n"
            + "_ O _ _ _ O _\n"
            + "_ _ _ _ _ _ _\n"
            + "    _ _ _\n"
            + "    _ _ _\n"
            + "Score: 2\n";
    Readable input = new StringReader("2 4 4 4 "
            + "3 6 3 4 "
            + "1 5 3 5 "
            + "4 5 2 5 "
            + "6 5 4 5 "
            + "5 7 5 5 "
            + "5 4 5 60 " // invalid move
            + "5 4 5 6 "
            + "3 7 5 7 "
            + "5 7 5 5 "
            + "3 3 3 5 "
            + "3 1 3 3 "
            + "5 2 5 4 "
            + "5 4 5 6 "
            + "5 6 3 6 "
            + "3 6 3 4 "
            + "3 4 3 2 "
            + "7 3 5 3 "
            + "4 3 6 3 "
            + "7 5 7 3 "
            + "7 3 5 3 "
            + "5 1 3 1 "
            + "3 1 3 3 "
            + "1 3 1 5 "
            + "1 5 3 5 "
            + "3 5 5 5 "
            + "2 3 4 3 "
            + "4 3 6 3 "
            + "6 3 6 5 "
            + "6 5 4 5 "
            + "4 4 4 6");
    Appendable output = new StringBuilder();
    MarbleSolitaireModel basic = new MarbleSolitaireModelImpl();
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(input, output);
    controller.playGame(basic);

    String opString = output.toString();
    // Check last output string
    assertEquals(expected,
            opString.substring(opString.length() - expected.length(), opString.length()));
  }

  /**
   * Tests a winning game with some string data thrown in to make sure it is handled correctly.
   */
  @Test
  public void winningGame() {
    String expected = "Score: 2\n"
            + "Game over!\n"
            + "    _ _ _\n"
            + "    _ _ _\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ O _ _ _\n"
            + "_ _ _ _ _ _ _\n"
            + "    _ _ _\n"
            + "    _ _ _\n"
            + "Score: 1\n";
    Readable input = new StringReader("2 4 4 4 "
            + "3 6 3 4 "
            + "1 5 3 cow 5 "
            + "4 5 2 5 cow "
            + "6 5 4 5 "
            + "5 7 5 5 "
            + "5 4 5 6 "
            + "3 whatever this is 7 5 7 "
            + "5 7 5 5 "
            + "3 3 3 5 "
            + "3 1 3 3 "
            + "5 2 5 4 "
            + "5 4 5 6 "
            + "5 6 3 6 "
            + "3 6 3 4 "
            + "3 4 3 2 "
            + "7 3 5 3 "
            + "4 3 6 3 "
            + "7 5 7 3 "
            + "7 3 5 3 "
            + "5 1 3 1 "
            + "3 1 3 3 "
            + "1 3 1 5 "
            + "1 5 3 5 "
            + "3 5 5 5 "
            + "2 3 4 3 "
            + "4 3 6 3 "
            + "6 3 6 5 "
            + "6 5 4 5 "
            + "4 5 4 3 "
            + "4 2 4 4 ");
    Appendable output = new StringBuilder();
    MarbleSolitaireModel basic = new MarbleSolitaireModelImpl();
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(input, output);
    controller.playGame(basic);

    String opString = output.toString();
    // Check last output string
    assertEquals(expected,
            opString.substring(opString.length() - expected.length(), opString.length()));
  }

  /**
   * Tests quitting after a few valid moves even if there are more moves after it in the input.
   */
  @Test
  public void QuitAfterValidMoves() {
    String expected = "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O _\n"
            + "    O _ O\n"
            + "O O O O _ _ O\n"
            + "O O O O O O O\n"
            + "O O O O O _ _\n"
            + "    O O _\n"
            + "    O O O\n"
            + "Score: 26\n";
    Readable input = new StringReader("2 4 4 4 "
            + "3 6 3 4 "
            + "1 5 3 5 "
            + "4 5 2 5 "
            + "6 5 4 5 "
            + "5 7 5 5 q "
            + "5 4 5 6 "
            + "3 7 5 7 "
            + "5 7 5 5 "
            + "3 3 3 5 "
            + "3 1 3 3 "
            + "5 2 5 4 "
            + "5 4 5 6 "
            + "5 6 3 6 "
            + "3 6 3 4 "
            + "3 4 3 2 "
            + "7 3 5 3 "
            + "4 3 6 3 "
            + "7 5 7 3 "
            + "7 3 5 3 "
            + "5 1 3 1 "
            + "3 1 3 3 "
            + "1 3 1 5 "
            + "1 5 3 5 "
            + "3 5 5 5 "
            + "2 3 4 3 "
            + "4 3 6 3 "
            + "6 3 6 5 "
            + "6 5 4 5 "
            + "4 4 4 6");
    Appendable output = new StringBuilder();
    MarbleSolitaireModel basic = new MarbleSolitaireModelImpl();
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(input, output);
    controller.playGame(basic);

    String opString = output.toString();
    assertEquals(expected,
            opString.substring(opString.length() - expected.length(), opString.length()));
  }
}