import java.util.Scanner;


public class TicTacToe
{
    private String[][] board = new String[3][3]; 
    private BoardState state;
    //declare instance variables
    private String player;
    private int moveRow;
    private int moveColumn;
    private Boolean valid;
    private Boolean win;
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

    }
    
    public void play()
    {
        printBoard();

        while(!win)
        {
            //ask player for turn
            System.out.printf("%n%s's turn.", this.getPlayer());
            System.out.printf("%n%s: Enter row <0, 1, 2>: ", this.getPlayer());

            //get row from player
            this.setMoveRow(input.nextInt());
            System.out.printf("%s: Enter column: <0, 1, 2>: ", this.getPlayer());

            //get column from player
            this.setMoveColumn(input.nextInt());

            //check for valid move
            this.valid = validMove();

            if(!valid)
            {
                //restart turn -- move is not valid
                System.out.printf("%n%s","Invalid move! Restarting turn.");
                continue;
            }
            else
            {
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
    }

    public String gameStatus()
    {
        /*
         * Method checks board array for game conditions
         * after a move has made. 
         * Conditions:
         *      Win
         *      Draw
         *      Continue
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
        //returns WIN, DRAW, or CONTINUE
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

                //check space at array for EMPTY, x, or o string.                
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
        //check for out of bounds 
        if(moveRow > board.length || moveColumn > board.length)
            return false;
        if(moveRow < 0 || moveColumn < 0)
            return false;

        //check for empty spot for move
        valid = (board[moveRow][moveColumn].equals("EMPTY")) ? true : false;
        return valid;
    }


    public void setPlayer(String player)
    {
        this.player = player;
    }

    public String getPlayer()
    {
        return this.player;
    }

    public void setMoveColumn(int column)
    {
        this.moveColumn = column;
    }

    public int getMoveColumn()
    {
        return this.moveColumn;
    }

    public void setMoveRow(int row)
    {
        this.moveRow = row;
    }

    public int getMoveRow()
    {
        return this.moveRow;
    }

    public void makeMove()
    {
        //after validation use makeMove to set either X or O in 
        //player's chosen row/column
        board[moveRow][moveColumn] = (this.getPlayer().equals("Player X")) ? "x" : "o";
    } 

} //end class TicTacToe

enum BoardState
{
    WIN("win" ), 
    DRAW("draw"), 
    CONTINUE("continue");
    
    private final String state;

    BoardState(String state)
    {
        this.state = state;
    }

    public String getState()
    {
        return state;
    }

} //end enum BoardState

