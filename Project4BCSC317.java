
package project4.b.csc.pkg317;

//Zachary Meadows
//Diane Fulton
//CSC 317
//23 February, 2017
//This program moves a Knight around a chessboard in an attempt
    //to get a "full tour". The knight starts at each position on the board. 

public class Project4BCSC317 {

    //create global vars
    public static final int NUM_OF_ROWS = 8;
    public static final int NUM_OF_COLUMNS = 8;
    public static final int HORIZONTAL[] = {2, 1, -1, -2, -2, -1, 1, 2};
    public static final int VERTICAL[] = {-1, -2, -2, -1, 1, 2, 2, 1};
    public static int fullTours = 0;
    public static int currentRow = 0;
    public static int currentColumn = 0;
    public static int possibleRows[] = {0, 0, 0, 0, 0, 0, 0, 0};
    public static int possibleColumns[] = {0, 0, 0, 0, 0, 0, 0, 0};
    public static int best[] = {0,0};
    public static int numOfPossible = 0;
    public static int chessboard[][] = new int [NUM_OF_ROWS][NUM_OF_COLUMNS];
    public static int accessibilityArray[][] = {{2,3,4,4,4,4,3,2}, {3,4,6,6,6,6,4,3}, 
             {4,6,8,8,8,8,6,4}, {4,6,8,8,8,8,6,4}, {4,6,8,8,8,8,6,4},
             {4,6,8,8,8,8,6,4}, {3,4,6,6,6,6,4,3}, {2,3,4,4,4,4,3,2}};
    
    public static void main(String[] args) {
       
        //loop 64 times for every square on board
        for (int row = 0; row < 8; row++)
        {
            for (int column = 0; column < 8; column++)
            {
        //create chessboard
        clearBoard();
        //increment position on board
        currentRow = row;
        currentColumn = column;
        //places starting position of knight
        chessboard[currentRow][currentColumn] = 1;
       
        //move the knight (if possible)
       moveKnight();
       //display the board
       displayBoard();
       //reset accessibility array for following tours
   accessibilityArray = new int[][] {{2,3,4,4,4,4,3,2}, {3,4,6,6,6,6,4,3}, 
             {4,6,8,8,8,8,6,4}, {4,6,8,8,8,8,6,4}, {4,6,8,8,8,8,6,4},
             {4,6,8,8,8,8,6,4}, {3,4,6,6,6,6,4,3}, {2,3,4,4,4,4,3,2}};
            }
        }
        System.out.printf("Number of Full Tours: %d\n", fullTours);
     }
  
   
   public static void clearBoard()
   {
       //create board with all squares initialized to 0
        for (int rows = 0; rows < NUM_OF_ROWS; rows++)
        {
            for (int columns = 0; columns < NUM_OF_COLUMNS; columns++)
            {
                chessboard[rows][columns] = 0;
            }
        }
   }//end createBoard
   
   public static void displayBoard()
   {
       System.out.println();
       System.out.print("  ");
       for (int i = 0; i < 8; i++)
       {
           System.out.printf("  %02d", i);
       }
       System.out.println();
       //loop through squares on board
       for (int rows = 0; rows < NUM_OF_ROWS; rows++)
       {
           System.out.printf("%02d ", rows);
           for (int columns = 0; columns < NUM_OF_COLUMNS; columns++)
           {
               //if position has not been touched, output [ ]
               if (chessboard[rows][columns] == 0)
               {
                   System.out.print("[  ]");
               }
               //if it has been touched, output moveNumber
               else
               {
                   System.out.printf("[%02d]", chessboard[rows][columns]);
               }
           }
           System.out.println();
       }
       System.out.println();
   }//end displayBoard
   
   public static void moveKnight()
   {
       int numOfMoves = 1;
       int i = 2;
       //as long as there are possible moves
       while (ifMovesPossible() == true)
       {
          // count number of moves knight has made on board
           numOfMoves++;
           //find lowest accessibility
          lowestAccessibility();
           
         //move knight to best spot
           currentRow = best[0];
           currentColumn = best[1];
           //mark spot on chessboard
           chessboard[currentRow][currentColumn] = i;
           //counter for full tours
           if (numOfMoves == 64)
           {
               fullTours++;
           }
           //increment moveNumber
           i++;
     }
   }
   public static boolean ifMovesPossible()
   {
       numOfPossible = 0;
       boolean possible = false;
       int j = 0;
       //for up to 8 possible moves
       for (int i = 0; i < 8; i++)
       {
           //move knight
           currentRow += VERTICAL[i];
           currentColumn += HORIZONTAL[i];
           //check if possible
           if (currentRow >= 0 && currentRow < 8 && currentColumn >= 0 && currentColumn < 8 && chessboard[currentRow][currentColumn] < 1)
           {
               possible = true;
               //lower accessibility of possible move squares
              accessibilityArray[currentRow][currentColumn]--;
              //set possible spots in seperate arrays
              possibleRows[numOfPossible] = currentRow;
              possibleColumns[numOfPossible] = currentColumn;
              //increment number possible
              numOfPossible++;
           }
           //move back to original position
           currentRow -= VERTICAL[i];
           currentColumn -= HORIZONTAL[i];
       }
       return possible;
   }
   
   public static void lowestAccessibility()
   {
       int numOfTied = 0;
       int tiedRows[] = new int[8];
       int tiedColumns[] = new int[8];
       boolean tie = false;
       //default to first possible move
      int lowest = accessibilityArray[possibleRows[0]][possibleColumns[0]];
      //default to first possible move
      best[0] = possibleRows[0];
      best[1] = possibleColumns[0];
      //i points to second possible move
       for (int i = 1; i < numOfPossible; i++)
       {
           //if next possible move is less than previous lowest possible move
           if (accessibilityArray[possibleRows[i]][possibleColumns[i]] < lowest)
           {
               //set new lowest
               lowest = accessibilityArray[possibleRows[i]][possibleColumns[i]];
               //set new best
               best[0] = possibleRows[i];
               best[1] = possibleColumns[i];
           }
       }
     }
}

           
       