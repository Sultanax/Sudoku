import java.util.Scanner;

public class Main {
  private static int[][] gameboard, column, grid, board, hide;

  public static void writeMatrix(int[][] arr) {
    System.out.println("    A B C  D E F  G H I");
    System.out.println("-----------------------");
    for (int i = 0; i < 9; i++) {
      if (i % 3 == 0 && i != 0) {
        System.out.println("------------------------");
      }
      System.out.print((char) ('A' + i) + " "); 
      System.out.print("|");
      for (int j = 0; j < 9; j++) {
        if (j % 3 == 0 && j != 0) {
          System.out.print("|");
        }
        if (arr[i][j] == 0) {
          System.out.print("  ");
        }
        else {
          System.out.print(" " + arr[i][j]);
        }
      }
      System.out.println();
    }
    System.out.println("-----------------------");
  }


  public static boolean different(int random,int rowt, int col){
    for (int i=0;i<gameboard[rowt].length;i++){
      if (random==gameboard[rowt][i])
        return false;
    }
    for (int i=0;i<column[col].length;i++){
      if(random==column[col][i])
        return false;
    }
    for (int i=0;i<grid[rowt/3*3+col/3].length;i++){
      if(random==grid[rowt/3*3+col/3][i])
        return false;
    }
    return true;
  }

  
  public static int letter(char A) { 
    switch (A) {
      case 'A':
        return 0;
      case 'B':
        return 1;
      case 'C':
        return 2;
      case 'D':
        return 3;
      case 'E':
        return 4;
      case 'F':
        return 5;
      case 'G':
        return 6;
      case 'H':
        return 7;
      case 'I':
        return 8;
    }
    return 0;
  }


  public static boolean check(char A) {
    switch (A) {
      case 'A':
      case 'B':
      case 'C':
      case 'D':
      case 'E':
      case 'F':
      case 'G':
      case 'H':
      case 'I':
        return true;
      default:
        System.out.println("Invalid coordinate");
        return false;
    }
  }


  public static void randomHide() { 
    for (int i = 0; i < 9; i++) {
      int hideNum = (int) (Math.random() * 5) + 2;
      int count = 0;
      while (count < hideNum) {
        int randpos = (int) (Math.random() * 9);
        while (grid[i][randpos] == 0)
          randpos = (int) (Math.random() * 9);
        grid[i][randpos] = 0;
        hide[i / 3 * 3 + randpos / 3][i % 3 * 3 + randpos % 3] = 0;
        count++;
      }
    }
  }

  
  public static void gridNum(int i, int j, int rand) {
    grid[i / 3 * 3 + j / 3][(i % 3) * 3 + j % 3] = rand;
  }


  public static boolean softlock(int row, int col) {
    if (row < 8 && col >= 9) {
      row += 1;
      col = 0;
    }
    if (row >= 9)
      return true;
    if (col == row / 3 * 3) {
      if (col == 6) {
        col = 0;
        row++;
        if (row >= 9)
          return true;
      } else {
        col = row / 3 * 3 + 3;
      }
    }
    for (int z = 1; z <= 9; z++) {
      if (different(z, row, col)) {
        gameboard[row][col] = z;
        board[row][col] = z;
        column[col][row] = z;
        gridNum(row, col, z);
        if (softlock(row, col + 1))
          return true; 
        gameboard[row][col] = 0;
        board[row][col] = 0;
        column[col][row] = 0;
        gridNum(row, col, 0);
      }
    }
    return false; 
  }


  public static boolean winner(){
    for (int i=0;i<9;i++){
      for(int j=0;j<9;j++){
        if(board[i][j]!=hide[i][j])
          return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    System.out.println("Play our sodoku game!");
    boolean try_again = true;
    while (try_again) {
      int num_tries = 3;
      System.out.println("Here is your board!");
      System.out.println();
      board = new int[9][9];
      gameboard = new int[9][9];
      column = new int[9][9];
      grid = new int[9][9];
      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
          for (int k = 0; k < 3; k++) { 
            int rand = (int) (Math.random() * 9) + 1;
            while (!different(rand, i * 3 + j, k + i * 3)) {
              rand = (int) (Math.random() * 9) + 1;
            }
            gameboard[i * 3 + j][k + i * 3] = rand;
            board[i * 3 + j][k + i * 3] = rand;
            column[k + i * 3][i * 3 + j] = rand;
            gridNum(i * 3 + j, k + i * 3, rand);
          }
        }
      }
      softlock(0, 3);
      hide = gameboard;
      randomHide();
      Scanner sc = new Scanner(System.in);
      while (num_tries > 0 && !winner()) {
        boolean choose_again = true;
        int row = -1;
        int col = -1;
        writeMatrix(hide);
        while (choose_again) {
          System.out.println("Enter row: ");
          char rowChar = sc.next().charAt(0);
          while (!check(rowChar)) {
            System.out.println("Enter row: ");
            rowChar = sc.next().charAt(0);
          }
          row = letter(rowChar);
          System.out.println("Enter column: ");
          char colChar = sc.next().charAt(0);
          while (!check(colChar)) {
            System.out.println("Enter column: ");
            colChar = sc.next().charAt(0);
          }
          col = letter(colChar);
          if (hide[row][col] != 0)
            System.out.println("This box already has a number. Try again!");
          else
            choose_again = false;
        }
        System.out.println("Enter a number from 1 to 9: ");
        int chooseNum = sc.nextInt();
        while (chooseNum < 1 || chooseNum > 9) {
          System.out.println("Ivalid. Please enter a number from 1 to 9: ");
          chooseNum = sc.nextInt();
        }
        if (board[row][col] == chooseNum) {
          System.out.println("Yayy, you got it!");
          hide[row][col] = chooseNum;
        } else {
          System.out.println("Ehh, maybe you'll get the next one!");
          num_tries--;
          System.out.println("You have " + num_tries + " tries remaining.");
        }
      }
      if (num_tries == 0) {
        System.out.println("Aww, maybe you'll have better luck winning next time.");
        System.out.println("The correct solution is: ");
        writeMatrix(board);
      } else if (winner()) {
        System.out.println("Congratulations, you're a pro!");
        writeMatrix(hide);
      }
      boolean loop = true;
      while (loop) {
        System.out.println("Do you want to play again? (Enter 1 for yes and 2 for no.)");
        int again = sc.nextInt();
        if (again == 1) {
          loop = false;
        } else if (again == 2) {
          try_again = false;
          loop = false;
        } else
          System.out.println("Invalid option. (Please enter 1 for yes and 2 for no.) ");
      }
      sc.close();
    }
  }
}