import marblesolitaure.model.MarbleSolitaireModel;
import marblesolitaure.model.MarbleSolitaireModelImpl;

/**
 * Do not modify this file. This file should compile correctly with your code!
 * You DO NOT need to submit this file.
 */
public class TypeChecks {

  /**
   * The contents of this method are meaningless.
   * They are only here to ensure that your code compiles properly.
   */
  public static void main(String[] args) {
    helper(new MarbleSolitaireModelImpl());
    helper(new MarbleSolitaireModelImpl(2, 2));
    helper(new MarbleSolitaireModelImpl(5));
    helper(new MarbleSolitaireModelImpl(3, 0, 4));
  }

  private static void helper(MarbleSolitaireModel model) {
    model.move(1, 3, 3, 3);
  }

}
