import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ChessAI {

    public static final int BOARD_SIZE = 8;

    // Generates an ASCII board
    public static char[][] GetBoard(String fileName)
        throws IOException {
        // Try with resource
        try (BufferedReader in =
                new BufferedReader(new FileReader(fileName))) {
            char[][] board = new char[BOARD_SIZE][BOARD_SIZE];
            String line;
            for (int j = 0; j < BOARD_SIZE; j++) {
                line = in.readLine();
                if (line == null) break;
                char[] row = new char[BOARD_SIZE];
                String[] line_arr = line.split(", ");
                for (int i = 0; i < BOARD_SIZE; i++) {
                    row[i] = line_arr[i].charAt(0);
                }
                board[j] = row;
           }
            return board;
        }
    }

    // Prints board
    public static void printBoard(char[][] board) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Finds Specified piece on board
    public static int[] findPiece(char[][] board, char piece) {
        int[] pos = new int[2];
        for (int i = 0; i < ChessAI.BOARD_SIZE; i++) {
            for (int j = 0; j < ChessAI.BOARD_SIZE; j++) {
                if (board[i][j] == piece) {
                    pos[0] = i;
                    pos[1] = j;
                }
            }
        }
        return pos;
    }

    // Main 
    public static void main(String[] args) throws IOException {
        System.out.println();
        if (args.length != 4) {
            System.out.println("Usage: java Chess <inputFile> <color> <strategy> <max_depth>");
            System.exit(0);
        }
        // Handles input args 
        String filename = args[0];
        String color = args[1];
        String strategy = args[2];
        int maxDepth = Integer.parseInt(args[3]);
        
        // Initializes board
        char[][] board = ChessAI.GetBoard(filename);
        System.out.println("Initial state:\n===============");
        // Prints initial board
        ChessAI.printBoard(board);
        System.out.println("===============");
        System.out.println();

        // Stores positions of kings
        int[] whiteKingPos = findPiece(board, 'k'); 
        int[] blackKingPos = findPiece(board, 'K');

        
        // Creates instance of chess engine
        BoardStateManager bm = new BoardStateManager();
        // Generates initial state given board, positions of kings;
        // Sets score to 0
        State startState = new State(board, 0, color, blackKingPos, whiteKingPos);
        startState.computeScore();
        //int maxDepth = 4;
        int alpha = Integer.MAX_VALUE*-1;
        int beta = Integer.MAX_VALUE;
        // color variable for negamax 
        int negaColor;
        if(color.equals("black")) {
            negaColor = -1;
        }else{
            negaColor = 1;
        }   
        
        // Run negamax w/ alpha-beta pruning on initial state
        NegamaxWrapper bestMove = bm.negamax(startState, maxDepth, alpha, beta, negaColor, strategy);
        State nextState = bestMove.getState();
        System.out.println("State after optimal move:\n===============");
        printBoard(nextState.getBoard());
        System.out.println("===============");
        System.out.println("Nodes visited:" + bm.numStates);
    }
}
