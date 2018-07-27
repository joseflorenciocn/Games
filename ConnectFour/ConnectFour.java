import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConnectFour {
	
	private static Scanner input = new Scanner(System.in);
	
    private static final char[] players = new char[] { 'R', 'Y' };

    private final int width, height;
    private final char[][] grid;
    private final StringBuilder top;
    private int lastCol = -1, lastTop = -1, lastvalue = -1;

    public ConnectFour(int width, int height) {
        this.width = width;
        this.height = height;
        this.top = new StringBuilder((2*this.width)+1);
        
        for (int a = 1; a <= width; a++) {
        
        	top.append("|");
        	top.append(Integer.toString(a));
       	
        } 
        
        top.append("|");
        		
        this.grid = new char[height][(2*width)+1];
        for (int i = 0; i < height; i++) {
        	for (int j = 0; j < (2*width)+1; j++) {
        		
        		if (j % 2 == 0)	this.grid[i][j] = '|';
        		else this.grid[i][j] = ' ';
        		
        	}
        }
        
    }

    public String toString() {  	
    	   	
        return  "\n" + this.top + "\n" + 
        		Arrays.stream(this.grid)
                      .map(String::new)
                      .collect(Collectors.joining("\n"));
    }
    
    public void insertToken(char color) {
    	
    	int flag = 0;
        do {
        	
        	try 
			{
        	
        		System.out.print("Player " + color + " turn: ");
        		int col = input.nextInt();
        		input.nextLine();

        		if (! (0 < col && col <= this.width)) {
        			System.out.println("Column must be between 1 and " + (this.width));
        		}
            
        		else {
            	
        			for (int h = this.height-1; h >= 0; h--) {
            		
        				if (this.grid[h][2*col-1] == ' ') {
        					this.grid[h][2*col-1] = color;
        					this.lastvalue = col;
        					this.lastCol=2*col-1;
                        	this.lastTop=h;
                        	flag = 1;
                        	break;
        				} 
        			}
            	
        			if (flag == 0) 
        			{
        				System.out.println("Column " + col + " is full.\n");
            		
        			}
        		}
			}
        	catch (InputMismatchException e) 
			{
			System.out.print("Only Numbers Allowed !!\n");
			input.nextLine();
			continue;
			}
            
            
        } while (flag == 0);
    }

    //Win function
    
    public boolean playerWin() {
        if (this.lastCol == -1) {
            throw new IllegalStateException("No move has been made yet");
        }
        char sym = this.grid[this.lastTop][this.lastCol];
        String match = String.format("%c%c%c%c", sym, sym, sym, sym);
        return contains(this.horizontal(), match) ||
               contains(this.vertical(), match) ||
               contains(this.diagonal1(), match) ||
               contains(this.diagonal2(), match);
    }

    //Build String to test Horizontal win
    
    private String horizontal() {
    	   	
    	 StringBuilder horizontalFour = new StringBuilder(this.width);
    	 for (int h = 0; h < (this.width*2)+1; h++) {
             if(h % 2 != 0) horizontalFour.append(this.grid[this.lastTop][h]);
         }
    	 
  //  	 System.out.println("Horizontal");
  //   	System.out.println(horizontalFour.toString());
    	
        return new String(horizontalFour.toString());
    }

  //Build String to test Vertical win
    
    private String vertical() {
    	
        StringBuilder verticalFour = new StringBuilder(this.height);
        for (int h = 0; h < this.height; h++) {
            verticalFour.append(this.grid[h][this.lastCol]);
        }
        
   //     System.out.println("Vertical");
  //      System.out.println(verticalFour.toString());
        return verticalFour.toString();
    }
    

  //Build String to test Diagonal 1 win
    
    private String diagonal1() {
    	
        StringBuilder diagonal1Four = new StringBuilder(this.height);
        for (int h = 0; h < this.height; h++) {       	
            int w = this.lastvalue + this.lastTop - h;
            if (0 < w && w < this.width) {
                diagonal1Four.append(this.grid[h][w*2-1]);
            }
        }
  //      System.out.println("Diagonal1");
 //       System.out.println(diagonal1Four.toString());
        return diagonal1Four.toString();
    }

  //Build String to test Diagonal 2 win
    
    private String diagonal2() {
    	
        StringBuilder diagonal2Four = new StringBuilder(this.height);
        for (int h = 0; h < this.height; h++) {
            int w = this.lastvalue - this.lastTop + h;
            if (0 < w && w < this.width) {
                diagonal2Four.append(this.grid[h][w*2-1]);
            }
        }
  //      System.out.println("Diagonal2");
  //      System.out.println(diagonal2Four.toString());
        return diagonal2Four.toString();
    }
    
    //Function to test if a String has 4 same-token in a row
    
    private static boolean contains(String conect_four, String token) {   	
        return conect_four.indexOf(token) >= 0;
    }

    
    
    public static void main(String[] args) {
       
            int height = 6, width = 8, moves = height * width;
            ConnectFour board = new ConnectFour(width, height);
            System.out.println("Use 1-" + (width) + " to choose a column.");
            System.out.println(board);
            	
              for (int i = 0; i < moves; i++) {
            
                char color = players[i%2];
                board.insertToken(color);
                System.out.println(board);
                if (board.playerWin()) {
                    System.out.println("Player " + color + " wins!");
                    return;
               }
            }
            System.out.println("Game over, no winner.");
            
           
    }
    
}