/*                                                                                                   
 * Author: Kyle Mayer
 * Email: kmayer1@cnm.edu
 * Date: 2/6/2018
 * Course: CSCI-2251 Sec 101
*/

import java.util.Scanner;

public class TicTacToe
{
    //declare private String array to hold board values "x", "o", or "EMPTY".
    private String[][] board = new String[3][3]; 
    //declare private enum BoardState.
    private BoardState state;
    //declare instance variables.
    private String player;
    private int moveRow;
    private int moveColumn;
    private Boolean valid;
    private Boolean win;
    //instantiate Scanner object to take user input from terminal.
    public Scanner input = new Scanner(System.in);

    public TicTacToe()
    {
        //constructor
        //set player -- start with Player X
        this.setPlayer("Player X");
        //boolean flag for validation of moves
        this.valid = false;
        this.win = false;

        //initialize the 3x3 array to be empty
        for(int row = 0; row < board.length; row++)
        {
            for(int col = 0; col < board[row].length; col++)
            {
                this.board[row][col] = "EMPTY";
            } 
        }
    } //end constructor TicTacToe
    
    public void play()
    {
        /*
         * Method play contains the main game loop.
         *      1. Calls printStatus() to prompt player and set move.
         *      2. Makes move if valid, else player is prompted for a move
         *         that meets the conditions in validMove().
         *      3. Gets game state and displays state if win or draw, else
         *         continue playing.
         *      4. If there is no win or draw the players are swapped and
         *         the loop is continued.
        */

        while(!win)
        {
            printStatus(); //ask player for move and set moves
            
            //check for valid move
            this.valid = validMove();

            if(!valid)
            {
                //restart turn -- move is not valid
                System.out.printf("%n%s%s%s%n","Invalid move! Restarting ", this.getPlayer(), "'s turn.");
                continue;
            }
            else
            {
                //perform move operations
                this.makeMove();
                this.printBoard();
            }

            //check for win condition
            String gameState = gameStatus();

            //if win --> break out and finish game
            if(gameState.equals("win"))
            {
                //print "Player _ wins"    
                System.out.printf("%n----Game Result---");
                System.out.printf("%n%s wins!",getPlayer());
                break;
            }
            else if(gameState.equals("draw"))
            //if draw --> break out and finish game
            {
               //print "DRAW"
                System.out.printf("%n----Game Result---");
                System.out.println("Draw!");
                break; 
            }
            else
            {
                //swap player at end of turn
                this.swapPlayer();
                continue;
            }
         }            
        //loop until the game is over
    } //end method play

    public String gameStatus()
    {
        /*
         * Method checks board array for game conditions
         * after a move has made. 
         * Conditions:
         *      1. Win
         *      2. Draw
         *      3. Continue
         *
         *  Returns current state of the game.
         */

        //check for win on rows
        for(int row = 0; row < board.length; row++)
        {
            if(board[row][0].equals("x") && board[row][1].equals("x") && board[row][2].equals("x"))
                return state.WIN.getState();
            if(board[row][0].equals("o") && board[row][1].equals("o") && board[row][2].equals("o"))
                return state.WIN.getState();
        }
        //check for win on columns
        for(int col = 0; col < board.length; col++)
        {
            if(board[0][col].equals("x") && board[1][col].equals("x") && board[2][col].equals("x"))
                return state.WIN.getState();
            if(board[0][col].equals("o") && board[1][col].equals("o") && board[2][col].equals("o"))
                return state.WIN.getState();
        }
        //check for diagonal win --> \
        if(board[0][0].equals("x") && board[1][1].equals("x") && board[2][2].equals("x"))
            return state.WIN.getState();
        if(board[0][0].equals("x") && board[1][1].equals("x") && board[2][2].equals("x"))
            return state.WIN.getState();
       
        //check for diagonal win --> /
        if(board[2][0].equals("x") && board[1][1].equals("x") && board[0][2].equals("x"))
            return state.WIN.getState();
        if(board[2][0].equals("o") && board[1][1].equals("o") && board[0][2].equals("o"))
            return state.WIN.getState();


        //check for draw -- there are no empty spaces left    
        int emptyCount = 0;
        for(int row = 0; row < board.length; row++)
        {
            for(int col = 0; col < board.length; col++)
            {
               if(board[row][col].equals("EMPTY"))
               {
                   emptyCount++;
               } 
            }
        }
        if(emptyCount == 0)
            return state.DRAW.getState();
        else
            return state.CONTINUE.getState();            

    }

    public void printStatus()
    {
        //prompt the turn of a player, winner, or draw
        //ask player for turn
        System.out.printf("%n%s's turn.", this.getPlayer());
        System.out.printf("%n%s: Enter row <0, 1, 2>: ", this.getPlayer());

        //get row from player
        this.setMoveRow(input.nextInt());
        System.out.printf("%s: Enter column: <0, 1, 2>: ", this.getPlayer());

        //get column from player
        this.setMoveColumn(input.nextInt());
    } 

    public void swapPlayer()
    {
        //swap out player at end of turn
        if(this.getPlayer().equals("Player X"))
        {
            setPlayer("Player O");
        }
        else
        {
            setPlayer("Player X");
        }
    } //end method swapPlayer

    public void printBoard()
    {
        /*
         * printBoard displays 3x3 grid as part of the user interface and
         * prints corresponding values for if the 3x3 String array contains
         * values of "x", "o" or "EMPTY".
        */

        System.out.printf("%n%s"," _______________________");

        //outputs the 3x3 board onto screen
        for(int row = 0; row < 3; row++)
        {
            //format and print row
            
            System.out.printf("%n%s%n", "|       |       |       |");
            for(int col = 0; col < 3; col++)
            {
                switch(col)
                {
                case 0:
                    System.out.printf("%s", "|");
                    break;
                case 1:
                    System.out.printf("%2s", "|");
                    break;
                case 2:
                    System.out.printf("%2s", "|");
                    break;
                }

                //check space at array for EMPTY, x, or o string. Print corresponding value.               
                if(this.board[row][col].equals("EMPTY"))
                {
                    System.out.printf("%5s%s", "   ", " ");
                }
                else if(this.board[row][col].equals("x"))
                {
                    System.out.printf("%5s%s", " X ", " ");
                }
                else
                {
                    System.out.printf("%5s%s", " O ", " ");
                }

                if(col == 2)
                {
                    System.out.printf("%2s","|");
                }

            } 
            System.out.printf("%n%s", "|_______|_______|_______|");
        }
    } //end method printBoard

    private boolean validMove()
    {
        /*
         * validMove checks the validity of each proposed player move.
         * False if:
         *      1. Move value for row or column  is greater than bounds of 3x3 board array.
         *      2. Move value for row or column is less than 0.
         *      3. Index value for row and column does not contain String
         *         literal value of "EMPTY".
         *  True if:
         *      1. Move falls on index value of 3x3 board array which contains
         *         String literal value of "EMPTY".
        */
        //check for out of bounds -- greater than board array.
        if(moveRow > board.length - 1 || moveColumn > board.length - 1)
            return false;
        //check for out of bounds -- less than 0.
        if(moveRow < 0 || moveColumn < 0)
            return false;
        //check for empty spot for move
        valid = (board[moveRow][moveColumn].equals("EMPTY")) ? true : false;
        return valid;
    } //end method validMove

    public void setPlayer(String player)
    {
        //mutator method for private instance variable player
        this.player = player;
    } //end method setPlayer

    public String getPlayer()
    {
        //accessor method for private instance variable player
        return this.player;
    } //end method getPlayer

    public void setMoveColumn(int column)
    {
        //mutator method for private instance variable column
        this.moveColumn = column;
    } //end method setMoveColumn

    public int getMoveColumn()
    {
        //accessor method for private instance variable column
        return this.moveColumn;
    } //end method getMoveColumn

    public void setMoveRow(int row)
    {
        //mutator method for private instance variable row
        this.moveRow = row;
    } //end method setMoveRow 

    public int getMoveRow()
    {
        //accessor method for private instance variable row
        return this.moveRow;
    } //end method getMoveRow

    public void makeMove()
    {
        //after validation use makeMove to set either X or O in 
        //player's chosen row/column
        board[moveRow][moveColumn] = (this.getPlayer().equals("Player X")) ? "x" : "o";
    } //end method makeMove

} //end class TicTacToe

enum BoardState
{
    //BoardState holds named constants for game state
    WIN("win" ), 
    DRAW("draw"), 
    CONTINUE("continue");
    
    private final String state;

    BoardState(String state)
    {
        //constructor for enum BoardState
        //initialize private instance variable state
        this.state = state;
    }

    public String getState()
    {
        //accessor method for private variable state;
        return state;
    }

} //end enum BoardState

