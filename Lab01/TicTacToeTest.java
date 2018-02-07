/*                                                                                                   
 * Author: Kyle Mayer
 * Email: kmayer1@cnm.edu
 * Date: 2/6/2018
 * Course: CSCI-2251 Sec 101
*/


public class TicTacToeTest
{
    public static void main(String[] args)
    {
        //instantiate gameBoard
        TicTacToe gameBoard = new TicTacToe();
        //call printBoard to display board to players
        gameBoard.printBoard();       
        //call play to jump into game loop and play game
        gameBoard.play();
    }
}
