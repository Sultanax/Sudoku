# Sudoku

![sudoku copy](https://github.com/user-attachments/assets/ff43f65a-d0b8-475c-aed7-8189c064bce1)

This is the code for our AP CSA command line sudoku project. 

The following is an explanation of each function utilized in our program:

1. writeMatrix(int[][] arr)
    Displays the Sudoku game board as a grid with the numbers placed in rows and columns.
2. different(int random, int row, int col)
    Checks if inserting a number at a specific position in the Sudoku grid meets the game's rules of being different in rows, columns, and each 3x3 grid.
3. letter(char A)
    Converts the input character for the column (A-I) into a numeric index (0-8).
4. check(char A)
    Validates if the input character for row or column is within the valid range (A-I).
5. randomHide()
    Randomly hides a certain number of elements in the Sudoku grid to create a puzzle for the player.
6. gridNum(int i, int j, int rand)
    Assigns a number to the specified cell in the 3x3 grid section.
7. softlock(int row, int col)
    Implements backtracking to solve the Sudoku puzzle and ensure the generated board is solvable.
8. winner()
    Checks if the player has filled in all the numbers correctly, confirming a win condition.
9. main(String[] args)
    The main function that orchestrates the entire Sudoku game, including generating the board, managing player input and game outcomes, and prompting the player for a rematch.

We hope you enjoy our game! Feel free to include submit any questions or issues!
