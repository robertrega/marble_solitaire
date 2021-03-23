package marblesolitaure.model.driver;

import marblesolitaure.model.MarbleSolitaireModel;
import marblesolitaure.model.MarbleSolitaireModelImpl;

/**
 * A class used to handle constructors of game boards for the main function for a
 * game of Marble Solitaire.
 */
public class SolitaireFactory {

  /**
   * Creates a default marble solitaire model.
   * @param type - a string, english european or triangular.
   * @return - the correct type of default marble solitaire model.
   */
  public static MarbleSolitaireModel createModel(String type) {
    MarbleSolitaireModel model = null;

    switch (type) {
      case "english":
        model = new MarbleSolitaireModelImpl();
        break;
      case "european":
        model = new EuropeanSolitaireModelImpl();
        break;
      case "triangular":
        model = new TriangleSolitaireModelImpl();
        break;
      default:
        break;
    }

    return model;
  }

  /**
   * Creates a marble solitaire model with specified size.
   * @param type - a string, english european or triangular.
   * @param size - an int, the user chosen size of the board (dimension in the case of
   *             a triangular board and arm thickness in the case of european or english).
   * @return - the correct type of default marble solitaire model.
   */
  public static MarbleSolitaireModel createModel(String type, int size) {
    MarbleSolitaireModel model = null;

    switch (type) {
      case "english":
        model = new MarbleSolitaireModelImpl(size);
        break;
      case "european":
        model = new EuropeanSolitaireModelImpl(size);
        break;
      case "triangular":
        model = new TriangleSolitaireModelImpl(size);
        break;
      default:
        break;
    }

    return model;
  }

  /**
   * Creates a default marble solitaire model with user selected empty starting space.
   * @param type - a string, english european or triangular.
   * @param row - an int, the row of the empty space
   * @param col - an int, the column of the empty space
   * @return - the correct type of default marble solitaire model.
   */
  public static MarbleSolitaireModel createModel(String type, int row, int col) {
    MarbleSolitaireModel model = null;

    switch (type) {
      case "english":
        model = new MarbleSolitaireModelImpl(row, col);
        break;
      case "european":
        model = new EuropeanSolitaireModelImpl(row, col);
        break;
      case "triangular":
        model = new TriangleSolitaireModelImpl(row, col);
        break;
      default:
        break;
    }

    return model;
  }

  /**
   * Creates a marble solitaire model with specified size and open starting space.
   * @param type - a string, english european or triangular.
   *
   * @param size - an int, the user chosen size of the board (dimension in the case of
   *             a triangular board and arm thickness in the case of european or english).
   * @param row - an int, the row of the empty space
   * @param col - an int, the column of the empty space
   * @return - the correct type of default marble solitaire model.
   */
  public static MarbleSolitaireModel createModel(String type, int size, int row, int col) {
    MarbleSolitaireModel model = null;

    switch (type) {
      case "english":
        model = new MarbleSolitaireModelImpl(size, row, col);
        break;
      case "european":
        model = new EuropeanSolitaireModelImpl(size, row, col);
        break;
      case "triangular":
        model = new TriangleSolitaireModelImpl(size, row, col);
        break;
      default:
        break;
    }

    return model;
  }
}

