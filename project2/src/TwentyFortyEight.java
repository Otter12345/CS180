/*
    Project 02    2048

    Program Description:
    This program mimicks the game "2048".

    Mar.05      Amber He
    Lab Sec 18
 */
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class TwentyFortyEight {
    //This instance variable represents the board. Use this to make changes.
    private int[][] board;
    //This variable keeps track of the current score.
    private int score;
    private int boardWidth;
    private int max;
    private Random random;
    private int highestScore;
    ArrayList<Integer> ROW;
    ArrayList<Integer> COL;

    //Constructor
    public TwentyFortyEight(int boardWidth) {
        // TODO
        this.boardWidth = boardWidth;
        score = 0;
        board = new int[boardWidth][boardWidth];
        max = boardWidth*boardWidth;
        ROW = new ArrayList<>(max);
        COL = new ArrayList<>(max);
        random = new Random();
        placeRandom();
        getHighestScore();
    }

    //This function resets the board to its initial state
    public void reset() {
        // TODO
        board = new int[boardWidth][boardWidth];
        score = 0;
        setHighestScore(score);
        placeRandom();
    }

    //This function returns the total number of blank spaces on the board
    public int numBlanks() {
        // TODO
        int k = 0;
        ROW = new ArrayList<>(max);
        COL = new ArrayList<>(max);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    ROW.add(i);
                    COL.add(j);
                    k++;
                }
            }
        }
        return k;
    }

    //This function places a 2 at a random blank space on the board.
    //It does nothing if there are no blank spaces.
    public void placeRandom() {
        // TODO
        if (numBlanks() != 0) {
            int randomIndex;
            randomIndex = ROW.get(random.nextInt(ROW.size()));
            int randomCol = COL.get(random.nextInt(COL.size()));
            board[randomIndex][randomCol] = 2;
        }
    }

    //This function attempts to move a cell at coordinates fromRow,fromCol to
    //the cell toRow,toCol. Refer to the handout for movement rules.
    public boolean moveTo(int fromRow, int fromCol, int toRow, int toCol) {
        // TODO
        int value;
        int[] indexRemoved = new int[2];
        indexRemoved[0] = fromRow;
        indexRemoved[1] = fromCol;

        if (fromRow < 0 || fromRow > board.length - 1 || toRow < 0 || toRow > board.length - 1 || fromCol < 0 || fromCol > board[0].length - 1 || toCol < 0 || toCol > board[0].length - 1) {
            return false;
        }
        else if (board[fromRow][fromCol] == 0){return false;}
        else if ((Math.abs(fromRow-toRow) + Math.abs(fromCol-toCol) != 1)){return false;}
        else if (board[toRow][toCol] == 0) {
            board[toRow][toCol] = board[fromRow][fromCol];
            board[fromRow][fromCol] = 0;
            return true;
        }
        else if (board[fromRow][fromCol] == board[toRow][toCol]) {
            value = board[fromRow][fromCol] * 2;
            if (value < 0)
                return false;
            else {
                board[toRow][toCol] = value;
                score=score+value;
                setHighestScore(score);
                board[fromRow][fromCol] = 0;
                return true;
            }
        }
        return false;
    }

    //The following four functions move the board in a single direction.
    public boolean moveUp() {
        // TODO
        ArrayList<Boolean> result = new ArrayList<>(boardWidth);
        int maxRow =0;
        int toRow=0;
        int row=0;

        for (int j=0;j<board[0].length;j++) {
            maxRow = 0;
            for (int i = board.length-1; i>-1;i--) {
                if (board[i][j] != 0) {
                    maxRow = i;
                    break;
                }
            }

            for (int k = maxRow; k>-1;k--) {
                row=k;
                toRow = --row;
                result.add(moveTo(k, j, toRow, j));
            }
        }

        if (result.contains(true))
            return true;
        else {
            return false;
        }
    }

    public boolean moveDown() {
        // TODO
        ArrayList<Boolean> result = new ArrayList<>(boardWidth);
        int maxRow =0;
        int toRow=0;
        int row=0;

        for (int j=0;j<board[0].length;j++) {
            maxRow = 0;
            for (int i = board.length-1;i>-1;i--) {
                if (board[i][j] != 0) {
                    maxRow = i;
                    break;
                }
            }

            for (int k = maxRow; k>-1;k--) {
                row=k;
                toRow = ++row;
                result.add(moveTo(k, j, toRow, j));
            }
        }

        if (result.contains(true))
            return true;
        else {
            return false;
        }
    }

    public boolean moveRight() {
        // TODO
        ArrayList<Boolean> result = new ArrayList<>(boardWidth);
        int maxCol = 0;
        int toCol = 0;
        int col = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = board[0].length - 1; j > -1; j--) {
                if (board[i][j] != 0) {
                    maxCol = j;
                    break;
                }
            }

            for (int k = maxCol; k > -1; k--) {
                col = k;
                toCol = ++col;
                result.add(moveTo(i, k, i, toCol));
            }
        }

        if (result.contains(true))
            return true;
        else {
            return false;
        }
    }

    public boolean moveLeft() {
        // TODO
        ArrayList<Boolean> result = new ArrayList<>(boardWidth);
        int maxCol = 0;
        int toCol = 0;
        int col = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != 0) {
                    maxCol = j;
                    break;
                }
            }

            for (int k = maxCol; k < board[i].length; k++) {
                col = k;
                toCol = --col;
                result.add(moveTo(i, k, i, toCol));
            }
        }

        if (result.contains(true))
            return true;
        else {
            return false;
        }
    }

    ////////////////////////////////////////////////////////////////////////
    // Do not edit the methods below, they are for testing and running the
    // program.
    ////////////////////////////////////////////////////////////////////////
    public int[][] getBoard() {
        return board;
    }

    public void resetHighestScore(int score){highestScore = score;}

    public void setHighestScore(int score){
        if (score>highestScore)
            highestScore = score;
    }

    public int getHighestScore(){return highestScore;}

    public void setBoard(int[][] newBoard) {
        board = newBoard;
    }

    /**
     * The main will allow you to play the game.
     *
     * @param args
     */
    public static void main(String args[]) {
        TwentyFortyEight tfe = new TwentyFortyEight(4);

        Scanner s = new Scanner(System.in);
        int bestScore = 0;
        boolean resetFlag = false;

        while(true) {
            System.out.println("Current score: " + tfe.getScore() + " | Best score: " + bestScore);
            tfe.showBoard();

            System.out.println("w -- up\ns -- down\na -- left\nd -- right\n");
            System.out.println("Enter w, s, a or d to move in that direction.");
            System.out.println("Enter reset to reset the game or quit to exit.");
            String line = s.nextLine();

            switch(line){
                case "w":
                    while(tfe.moveUp()){
                        // do nothing
                    }
                    break;
                case "s":
                    while(tfe.moveDown()){
                        // do nothing
                    }
                    break;
                case "a":
                    while(tfe.moveLeft()){
                        // do nothing
                    }
                    break;
                case "d":
                    while(tfe.moveRight()){
                        // do nothing
                    }
                    break;
                case "reset":
                    tfe.reset();
                    resetFlag = true;
                    break;
                case "quit":
                    return;
                default:
                    System.out.println("Invalid move, Please try again!!!!\n");
                    continue;

            }

            bestScore = Math.max(bestScore, tfe.getScore());
            if(!resetFlag) {
                tfe.placeRandom();
                resetFlag = false;
            }
        }
    }



    public void showBoard() {
        for(int x = 0; x < boardWidth * 6 + 1; x++){
            System.out.print("-");
        }
        System.out.println();

        for(int x = 0; x < boardWidth; x++) {
            for(int y = 0; y < boardWidth; y++) {
                String square = (board[x][y] == 0)? "":board[x][y] + "";
                System.out.printf("|%5s", square);
            }
            System.out.println("|");

            for(int y = 0; y < boardWidth * 6 + 1; y++){
                System.out.print("-");
            }
            System.out.println();
        }
    }

    public int getScore() {
        return score;
    }

}

