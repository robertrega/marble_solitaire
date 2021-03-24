Please see an explanation of how the game is played here:

https://www.webgamesonline.com/peg-solitaire/

The user plays by running the main function in MarbleSolitaire.java through the IDE and then inputting moves through the command line. 

To start a game, the user can input parameters specifying the board type and size as well as the location of the initial empty space. Use the following arguments:

 - One of 'english', 'european', or 'triangular.' This parameter specifies the board shape. This argument is mandatory.
 - "-size i" followed by a positive integer i. This specifies the size of the board. European and English boards must have an odd integer in order to function properly.
 - "-hole r c" where r and c are the row and column of the initial empty space.

Enter these followed by the word "go". If no parameter is entered, then the game will begin with a default board. Any invalid input will throw errors and alert the user. All functions are documented in the code. Also included is a full test suite.
