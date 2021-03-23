package marblesolitaure.controller;

import marblesolitaure.model.MarbleSolitaireModel;

/**
 * This interface represents the controller for one game of marble solitaire. It takes
 * user in put in the form of a string reader (expanded to command line later). A single
 * turn is represented by the user making one valid move. This controller also handles
 * the user winning or losing the game as well as quitting of their own volition.
 */
public interface MarbleSolitaireController {
  /**
   * The main driver of the marble solitaire game. Handles input from the user and
   * talks with the model.
   *
   * @param model -  an instance of the marble solitaire model.
   * @throws IllegalArgumentException when a null model is passed to it, or player
   *         attempts to make an invalid move. An attempted move with negative integers
   *         or non integers will not throw an exception and will just prompt the
   *         user for input again.
   * @throws IllegalStateException when unable to write to the appendable,
   *         or readable runs out of input.
   */
  void playGame(MarbleSolitaireModel model) throws IllegalArgumentException, IllegalStateException;
}
