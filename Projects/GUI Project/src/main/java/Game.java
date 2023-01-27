import javafx.scene.control.Toggle;

/**
 * Creates our Game object to be stored in GameHistoryScene
 */
public class Game
{
    private int gameID = 0;
    private String p1ThrowString = "";
    private String p2ThrowString = "";
    private String gameOutcome = "";
    String[][] resultsArray = new String[3][3];

    /**
     * Constructor for a Game Object
     * @param id the ID for the game used for referencing later
     * @param move1 the radio button value for player 1's move
     * @param move2 the radio button value for player 2's move
     */
    public Game(int id, Toggle move1, Toggle move2)
    {
        int playerOneThrow = -1;
        int playerTwoThrow = -1;

        if (move1 == InputScene.rock) {
            playerOneThrow = 0;
            this.p1ThrowString = "Rock";
        }
        else if (move1 == InputScene.paper) {
            playerOneThrow = 1;
            this.p1ThrowString = "Paper";
        }
        else {
            playerOneThrow = 2;
            this.p1ThrowString = "Scissors";
        }

        if (move1 == InputScene.rock2) {
            playerTwoThrow = 0;
            this.p2ThrowString = "Rock";
        }
        else if (move2 == InputScene.paper2) {
            playerTwoThrow = 1;
            this.p2ThrowString = "Paper";
        }
        else {
            playerTwoThrow = 2;
            this.p2ThrowString = "Scissors";
        }

         /*
         * resultsArray is the actual table used to determine based
         * on every possible combination of moves, which player wins
         * each one
         */
        resultsArray [0][0] = "No Winner"; //Draw
        resultsArray [0][1] = "Player Two Wins"; //PLAYER 2 WINS
        resultsArray [0][2] = "Player One Wins"; //PLAYER 1 WINS
        resultsArray [1][0] = "Player One Wins";
        resultsArray [1][1] = "No Winner";
        resultsArray [1][2] = "Player Two Wins";
        resultsArray [2][0] = "Player Two Wins";
        resultsArray [2][1] = "Player One Wins";
        resultsArray [2][2] = "No Winner";

        this.gameOutcome = resultsArray[playerOneThrow][playerTwoThrow];
        this.gameID = id;
    }

    /**
     * Getter for the game outcome, used to populate the games
     * observable array list
     * @return the String stating who won the game
     */
    public String getGame()
    {
        return this.gameOutcome;
    }

    /**
     * Getter for the game ID
     * @return an int gameID
     */
    public int getID()
    {
        return this.gameID;
    }

    /**
     * Getter for player one's throw
     * @return a string containing which move player one chose
     */
    public String getP1ThrowString()
    {
        return this.p1ThrowString;
    }

    /**
     * Getter for player two's throw
     * @return a string containing which move player two chose
     */
    public String getP2ThrowString()
    {
        return this.p2ThrowString;
    }
}
