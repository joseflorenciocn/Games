import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman {

	//Generate word randomly
	public static String genWord(String[] words)
	  {
		  
		  return words[rand.nextInt(words.length)];
	  }
	 
	//Copy the file words in the array
	 public static String[] FileWords()
	 {
		 
		    //String[] arr= null;
		    List<String> words_files = new ArrayList<String>();

		    try 
		    { 
		        FileInputStream fstream_file = new FileInputStream("src/hangman.txt"); 
		        DataInputStream data_input = new DataInputStream(fstream_file); 
		        BufferedReader buffer = new BufferedReader(new InputStreamReader(data_input)); 
		        String str_line; 

		        while ((str_line = buffer.readLine()) != null) 
		        { 
		            str_line = str_line.trim(); 
		            if ((str_line.length()!=0))  
		            { 
		                words_files.add(str_line);
		            } 
		        }

		       return  (String[])words_files.toArray(new String[words_files.size()]);
		        
		    }
		    catch (Exception e)  
		    {
		     // Catch exception if any
		        System.err.println("Error: " + e.getMessage());
		        String[] error = null;
		        return error;
		    }
		 
	 }
	
	 private static Random rand = new Random();
	 private static Scanner input = new Scanner(System.in);

	 public static void main(String[] args) {
		 
		int flag2 = 0;
		do // Loop for the full program
		{	 
	
			int choice = 0, flag = 0, teste = 0;
			
			//String for the option 1
			String[] words_static = {"switch", "oriented", "stupid", "noob", "kids", "spammer", "ok", "done", "fine" , "games", "program", "java", "computer", 
			  "write", "hangman", "that", "random", "generate", "word", "user", "guess", "letter", "time", "shown", "sample", "run", "each", "letter",
			  "display", "asterisk", "correct", "actual", "finish","number", "misse", "ask", "continue", "play", "another", "choose", "data", "structure"};
			
			//String for the option 2
			String[] words_file = FileWords();
			
			//String for the user choice
			String[] words = null;
	  
			System.out.print("Hangman Game \n\n" + 
					"Press 1 to run game with randomly generated word.\n" +
					"Press 2 to run game by reading the word from the text file.\n" +
					"Enter your choice:");
	  
		
			do // Loop for the user input
			{
				try 
				{
				  choice = input.nextInt();
				  input.nextLine();
				  System.out.print("\n");
				  
				  //Test for the choice
				  if (choice == 1 || choice == 2)  flag = 1;
				  else 
				  {
					    System.out.print("Only Numbers 1 or 2 are Allowed !!\n");
						System.out.print("Enter your choice: ");
				  }
				}
				catch (InputMismatchException e) 
				{
				System.out.print("Only Numbers Allowed !!\n");
				System.out.print("Enter your choice: ");
				input.nextLine();
				continue;
				}
			} while (flag == 0); // End Loop for the user input
	  	  
	  
			switch(choice)
			{
			case 1:
				words = words_static;
				break;
			case 2:
				words = words_file;
				break;
			default:
				System.out.println("Choice not available\n" );
				break;
			}
		
		
		
			String word = genWord(words);
			int num = word.length(), count = 0, found = 0;
			
			//String with the word randomly choose
			char[] word_arr = word.toCharArray();
			
		  
			//String with * filled
			char[] find = new char[num];
			for (int i = 0; i < num; i++)
			{
			  find[i] = '*';
			}
		  
		  
		  while (teste == 0) //Loop for the game
		  {
			  System.out.print("(Guess) Enter a letter in word "); 
			  for (int i = 0; i < num; i++) System.out.print(find[i]);
			  System.out.print(" > ");
			  
					try 
					{
						 char letter;

						  letter = input.next(".").charAt(0);
						  input.nextLine();

						  
						  //Test for the letter
						  for (int j = 0; j < num; j++)
						  {
							  if (letter == word_arr[j])
							  {
								  if (find[j] == letter)
								  {
									System.out.print("\t" + letter + " is already in the word\r\n");
									found = 1;
									break;
								  }
								  else 
								  {
									find[j] = letter;
									found = 1;
								  }
							  }
							  
						  }
						  
						  if (found == 1) found = 0;
						  else 
						  {	
							  System.out.print("\t" + letter + " is not in the word\r\n");
							  found = 0;
							  count++;
						  }
						  
						  
						  String test = new String(find);
						  
						  if (test.equals(word)) teste = 1;
						  
						  
					}
					catch (InputMismatchException e) 
					{
						//System.out.print(e.getMessage()); //try to find out specific reason.
						System.out.print("Only one character is Allowed !!\n");
						//System.out.print("Enter your choice: ");
						input.nextLine();
						continue;
					}
			  
		  }
			  
			  System.out.print("\nThe word is " + word + ". You missed " + count + " time\r\n" + 
			  		"Do you want to guess another word? Enter y or n>");

			  
			  flag = 0;
			  do //Loop for the final user input
			  {
					try 
					{
						char exit;
						 exit = input.next(".").charAt(0);
						  input.nextLine();
						  System.out.print("\n");
						  
						  if (exit == 'n' || exit == 'N')
							  {
							  flag2 = 1;
							  flag = 1;
							  }
						  else 
						  {
							  if  (exit == 'y' || exit == 'Y' ) 
								  {
								  flag = 1;
								  flag2 = 0;
								  }
							  else 
							  {
								  	System.out.print("Only 'y' or 'n'!!\n");
									System.out.print("Enter your choice: ");
							  }
							    
						  }
					}
					catch (InputMismatchException e) 
					{
						//System.out.print(e.getMessage()); //try to find out specific reason.
						System.out.print("Only Characters Allowed !!\n");
						System.out.print("Enter your choice: ");
						input.nextLine();
						continue;
					}
			  } while (flag == 0);
		} while (flag2 == 0);
		  		  
	  }
	  
}
