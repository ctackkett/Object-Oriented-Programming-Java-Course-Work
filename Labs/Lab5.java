import java.util.*;

/** Solves the prozor problem on open kattis
 * */
public class Prozor {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        final int R = in.nextInt();   // number of rows in the input board
        final int S = in.nextInt();   // number of columns in the input board
        final int K = in.nextInt();   // size of paddle (width or height)

        final char[][] board = new char[R][S];  // will hold input board for problem

        // fill board
        in.nextLine(); // waste the end of the first line
        for(var row = 0; row < R; row++) {
            var line = in.nextLine();
            for(var col=0; col < S; col++) {
                board[row][col] = line.charAt(col);
            }
        }

        // kill flies in every way possible
        var maxFlies = 0;
        var outputBoard = board;
        for(var row = 0; row <= R-K; row++) {
            for(var col = 0; col <= S-K; col++) {
                var newBoard = copyBoard(board);  // copy the original board
                placeRacket(newBoard, row, col, K);       // smack the racket on to the flies
                var flies = deadFlies(newBoard, row, col, K);     // count how many you got
                if (flies > maxFlies) { // maximize dead flies, remember the placement
                    outputBoard = newBoard;
                    maxFlies = flies;
                }
            }
        }

        // print the results for maximum extermination
        System.out.println(maxFlies);
        printBoard(outputBoard);
    }

    /** prints out a character matrix representing a board for the problem */
    public static void printBoard(char[][] board) {
        for(int row=0; row<board.length; row++) {
            for(int col=0; col<board[row].length; col++) {
                System.out.print(board[row][col]);
            }
            System.out.println("");
        }
    }

    /** copies a character matrix and returns the copy */
    public static char[][] copyBoard(char[][] board) {
        char[][] newBoard = new char[board.length][board[0].length];
        for(int row=0; row<board.length; row++) {
            for(int col=0; col<board[row].length; col++) {
                newBoard[row][col] = board[row][col];
            }
        }
        return newBoard;
    }

    /** sets the racket on to the board
     *
     * @param board board to alter with racket image
     * @param row upper left hand corner of the racket (row)
     * @param col upper left hand corner of the racket (column)
     * @param K size (width or height) of racket
     */
    public static void placeRacket(char[][] board, int row, int col, int K) {
        for (int rowpos = row; rowpos < (row + K); rowpos ++) {
            if (rowpos == row) {
                board[rowpos][col] = '+';
                board[rowpos][col + (K - 1)] = '+';
                for (int colpos = col + 1; colpos < col + (K - 1); colpos ++) {
                    board[rowpos][colpos] = '-';
                }
            }
            else if (rowpos == row + (K - 1)) {
                board[rowpos][col] = '+';
                board[rowpos][col + (K - 1)] = '+';
                for (int colpos = col + 1; colpos < col + (K - 1); colpos ++) {
                    board[rowpos][colpos] = '-';
                }
            }
            else {
                board[rowpos][col] = '|';
                board[rowpos][col + (K - 1)] = '|';
            }
        }
    }

    /** counts the number of dead flies on a board with a racket on it
     *
     * @param board board with racket imprinted on it
     * @return number of flies squished by the racket
     */
    public static int deadFlies(char[][] board, int row, int col, int K) {
        int deceasedFlies = 0;
            for (int rowpos = row + 1; rowpos < row + (K - 1); rowpos ++)
                for (int colpos = col + 1; colpos < col + (K - 1); colpos ++)
                    if (board[rowpos][colpos] == '*')
                        deceasedFlies ++;
        return deceasedFlies;
    }
}
