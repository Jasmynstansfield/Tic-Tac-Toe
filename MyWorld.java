import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import javax.swing.JOptionPane;

/**
 * Name: Jasmyn Stansfield
 * Course: CS30S
 * Teacher: Mr. Hardman
 * Lab #1, Program #1
 * Date Last Modified: March 
 */
public class MyWorld extends World
{
    boolean playerOneTurn = true;
    
    boolean messageShown = false;
    
    boolean gameStart = false;
    
    private String playerOneName;
    private String playerTwoName;
    
    private String[][] board = new String[3][3];
    

    /**
     * MyWorld in the constructor for objects of type MyWorld
     * 
     * @param There are no parameters
     * @return objects of type MyWorld
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(720, 720, 1); 
        
        drawLines();
        
        for( int r = 0; r < board.length; r++ )
        {
            for( int c = 0; c < board[r].length; c++ )
            {
                board[r][c] = "" ;
            }
        }
    }
    
    /**
     * drawLines will draw a 3x3 grid for playing Tic Tac Toe
     * 
     * @param There are no parameters
     * @return Nothing will be returned
     */
    private void drawLines()
    {
        getBackground().setColor( Color.BLACK );
        
        for( int i = 240; i < getWidth(); i += 240 )
        {
            //verticle line, x doesn't change
            getBackground().drawLine( i, 0, i, getHeight() );
            getBackground().drawLine( 0, i, getWidth(), i );
            
        }
    }
    
    /**
     * started will ask the players for their names ans store them in variables
     * 
     * @param There are no parameters
     * @return Nothing will be returned
     */
    public void started()
    {
        playerOneName = JOptionPane.showInputDialog( null, "Player One: Please enter your name:", "Player One Name", JOptionPane.QUESTION_MESSAGE );
        playerTwoName = JOptionPane.showInputDialog( null, "Player Two: Please enter your name:", "Player Two Name", JOptionPane.QUESTION_MESSAGE );
    }
    
    /**
     * act is the code that is run on every iteration of the act cycle
     * 
     * @param There are no parameters
     * @return Nothing will be returned
     */
    public void act()
    {
        displayBoard();
        Greenfoot.delay(10);
        
        if( checkPlayerOneWin() == true )
        {
            JOptionPane.showMessageDialog( null, "Congratulations " + playerOneName + "!", "Game End", JOptionPane.PLAIN_MESSAGE );
            Greenfoot.stop();
        }
        else if( checkPlayerTwoWin() == true )
        {
            JOptionPane.showMessageDialog( null, "Congratulations " + playerTwoName + "!" , "Game End", JOptionPane.PLAIN_MESSAGE );
            Greenfoot.stop();
        } 
        else if( checkBoardFilled() == true )
        {
            JOptionPane.showMessageDialog( null, "It is a draw!", "Game End", JOptionPane.PLAIN_MESSAGE );
            Greenfoot.stop();
        }
        else
        {
            showTurn();
            
            messageShown = true;
        }
        
        checkMouseClick();
   
    }
    
    /**
     * showTurn will show to the players, whose turn it currently is
     * 
     * @param There are no parameters
     * @return Nothing will be returned
     */
    private void showTurn()
    {
        if( playerOneTurn == true )
        {
            if( messageShown == false )
            {
                JOptionPane.showMessageDialog( null, playerOneName + ", chose where to place an X", "Player One's Turn", JOptionPane.PLAIN_MESSAGE );
                
                messageShown = true;
            }
        }
        else
        {
            if( messageShown == false )
            {
                JOptionPane.showMessageDialog( null, playerTwoName + ", chose where to place an O", "Player Two's Turn", JOptionPane.PLAIN_MESSAGE );
                
                messageShown = true;
            }
        }
    }
    
    /**
     * checkMouseClick will check where the user clicks and keeps track of who's turn it is
     * 
     * @param There are no parameters
     * @return Nothing will be returned
     */
    private void checkMouseClick()
    {
        int userRow;
        int userColumn;
        
        
        if( Greenfoot.mouseClicked( this ) )
        {
            MouseInfo userClick = Greenfoot.getMouseInfo();
            //store in variable
            
            userColumn = userClick.getX() / ( getWidth() / 3 );
            userRow = userClick.getY() / ( getHeight() / 3 );
            
            if( board[userRow][userColumn] == "" )
            {
                if( playerOneTurn == true )
                {
                    board[userRow][userColumn] = "X";
                    
                    playerOneTurn = false;
                    messageShown = false;
                }
                else
                {
                    board[userRow][userColumn] = "O";
                    
                    playerOneTurn = true;
                    messageShown = false;
                }
           }
           else
           {
               JOptionPane.showMessageDialog( null, "This spot is filled, please choose another one.", "Oops!", JOptionPane.PLAIN_MESSAGE );
           }
        }
    }
    
    /**
     * displayBoard displays an X where player one clicks, and a Y where player two clicks
     * 
     * @param There are no parameters
     * @return Nothing will be returned
     */
    private void displayBoard()
    {
        GreenfootImage toDisplay;
        
        for( int r = 0; r < board.length; r ++ )
        {
            for( int c = 0; c < board[r].length; c ++ )
            {
                toDisplay = new GreenfootImage( board[r][c], 100, Color.BLACK, new Color( 0, 0, 0, 0 ) );
                getBackground().drawImage(toDisplay, c * getWidth()/3 + (getWidth()/3 - toDisplay.getWidth())/2, r * getHeight()/3 + (getHeight()/3 - toDisplay.getHeight())/2);
                
            }
        }
    }
    
    /**
     * checkPlayerOneWin will check whether player one has placed 3 X's in a row
     * and won the game
     * 
     * @param There are no parameters
     * @return an boolean variable that indicates wheather player one has won or not
     */
    private boolean checkPlayerOneWin()
    {
        boolean playerOneWin = false;
        
        //horizontal
        if( board[0][0] == "X" && board[0][1] == "X" && board[0][2] == "X")
        {
            playerOneWin = true;
        }
        else if( board[1][0] == "X" && board[1][1] == "X" && board[1][2] == "X")
        {
            playerOneWin = true;
        }
        else if( board[2][0] == "X" && board[2][1] == "X" && board[2][2] == "X")
        {
            playerOneWin = true;
        }
        //vertical
        else if( board[0][0] == "X" && board[1][0] == "X" && board[2][0] == "X")
        {
            playerOneWin = true;
        }
        else if( board[0][1] == "X" && board[1][1] == "X" && board[2][1] == "X")
        {
            playerOneWin = true;
        }
        else if( board[0][2] == "X" && board[1][2] == "X" && board[2][2] == "X")
        {
            playerOneWin = true;
        }
        //diagonal
        else if( board[0][0] == "X" && board[1][1] == "X" && board[2][2] == "X")
        {
            playerOneWin = true;
        }
        else if( board[2][0] == "X" && board[1][1] == "X" && board[0][2] == "X")
        {
            playerOneWin = true;
        }
        
        return playerOneWin;
    }
    
    /**
     * checkPlayerTwoWin will check whether player two has placed 3 O's in a row
     * amd won the game
     * 
     * @param There are no parameters
     * @return an boolean variable that indicates wheather player two has won or not
     */
    private boolean checkPlayerTwoWin()
    {
        boolean playerTwoWin = false;
        //horizontal
        if( board[0][0] == "O" && board[0][1] == "O" && board[0][2] == "O")
        {
            playerTwoWin = true;
        }
        else if( board[1][0] == "O" && board[1][1] == "O" && board[1][2] == "O")
        {
            playerTwoWin = true;
        }
        else if( board[2][0] == "O" && board[2][1] == "O" && board[2][2] == "O")
        {
            playerTwoWin = true;
        }
        //vertical
        else if( board[0][0] == "O" && board[1][0] == "O" && board[2][0] == "O")
        {
            playerTwoWin = true;
        }
        else if( board[0][1] == "O" && board[1][1] == "O" && board[2][1] == "O")
        {
            playerTwoWin = true;
        }
        else if( board[0][2] == "O" && board[1][2] == "O" && board[2][2] == "O")
        {
            playerTwoWin = true;
        }
        //diagonal
        else if( board[0][0] == "O" && board[1][1] == "O" && board[2][2] == "O")
        {
            playerTwoWin = true;
        }
        else if( board[2][0] == "O" && board[1][1] == "O" && board[0][2] == "O")
        {
            playerTwoWin = true;
        }
        
        return playerTwoWin;
    }
    
    /**
     * checkBoardFilled will check if the board has been filled to see if the game is over
     * 
     * @param There are no parameters
     * @return an boolean variable that indicates wheather the board has been filled
     */
    private boolean checkBoardFilled()
    {
        boolean boardFilled = true;
        
        for( int r = 0; boardFilled == true && r < board.length; r++ )
        {
            for( int c = 0; boardFilled == true && c < board[r].length; c++ )
            {
                if( board[r][c] == "" )
                {
                    boardFilled = false;
                }
            }
        }
        
        return boardFilled;
    }
}