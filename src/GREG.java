import java.io.File;
import java.util.Random;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
public class GREG
{
	/**
	 * The int which determines if basic stats for the working char are displayed every cycle, 0 is none, 1 is minimal, 2 is large, 3 is all data
	 */
	static int displayingWorkingChar;
	/**
	 * The String containing the entire raw save file
	 */
	static String rough = "";
	/**
	 * A string that temporarily stores data before it's passes elsewhere
	 */
	static String temp = "";
	/**
	 * The holder of String data coming from the Scanner named input
	 */
	static String strInput;
	/**
	 * All the characters from the raw save file
	 */
	static char[] roughArr;
	/**
	 * The Strings representing individual Characters
	 */
	static ArrayList<String> roughChars = new ArrayList<String>();
	/**
	 * ALl the Character objec
	 */
	static ArrayList<Character> characters = new ArrayList<Character>();
	/**
	 * A boolean to show whether the program is still looking for user input
	 */
	static boolean checking = false;
	/**
	 * The boolean to show if the load screen is running
	 */
	static boolean runningLoadScreen;
	/**
	 * The boolean to show if the action screen is running
	 */
	static boolean runningActionScreen;
	
	/**
	 * The scanner that reads the save file
	 */
	static Scanner scan;
	/**
	 * The scanner that looks for user input
	 */
	static Scanner input = new Scanner(System.in);
	/**
	 * Once identified, the character the user is modifying is stored here
	 */
	static Character workingChar;
	/**
	 * The random object used to roll any and all dice
	 */
	static Random die = new Random();
	/**
	 * This method loads all the characters in the save file and constructs the objects in the arraylist then begins checking for input
	 * @param args filename of the save file
	 */
	public static void main(String[] args) 
	{
		//Porcess to read the save file and put all the characters in an array
		System.out.println("Loading...");
		// printDesc("Dwarf", true);
		try 
		{
			scan = new Scanner(new File(args[0]));
			while(scan.hasNextLine())
			{
				rough += scan.nextLine();
			}
			//read whole file
			roughArr = rough.toCharArray();
			
			for(int i = 0; i < roughArr.length; i++)
			{
				//start checking at open bracket
				if(roughArr[i] == '[')
					checking = true;
				else if(roughArr[i] == ']')//stop at closed
				{
					checking = false;
					roughChars.add(temp);// at the end put the string into the array of character strings
					temp = "";
				}
				else if(checking)//while checking put everyhting into a temp string
					temp += roughArr[i];	
				
			}
			//System.out.println(roughChars.size());
			try
			{
				//populate the character array by constructing objects for each string
				for(String s : roughChars)
				{
					characters.add(new Character(s));
				}
				System.out.println("Done!");
				System.out.println("Welcome to G.R.E.G. The Greatest Rpg Essence Generator. Greg is used to create profiles for rpg characters, \ncurrently it is optimized for dungeons and dragons");
				runningLoadScreen = true;
				runningActionScreen = false;
				while(runningLoadScreen) //load screen protocol
				{
					System.out.println("If you'd like to load up a previously made character, type 'load'");
					System.out.println("or if you'd like to create a new character, type 'new'");
					strInput = input.nextLine();
					if(strInput.toLowerCase().trim().equals("quit")) // take commands. if quit then exit
						runningLoadScreen = false;
					else if(strInput.toLowerCase().trim().equals("load"))// if load then run load protocol
					{
						 
						do
						{
							checking = false;
							System.out.println("Which character would you like to load? (type the number that preceeds it, or 'quit')");
							for(int i = 0; i < characters.size(); i++) // show all characters in the list
							{
							//	System.out.println(characters.get(i));
								System.out.println((i + 1) + " - " + characters.get(i).getName());
							}
							strInput = input.nextLine();
							if(!strInput.trim().toLowerCase().equals("quit")) //unless they quit
							{
								int num = getInt(strInput);
								if(num > 0 && num <= characters.size())
								{
									Character chosen = characters.get(num - 1); //ID object they chose
									System.out.println("Load " + chosen.getName() + "?"); 
									if(yes()) // check to be sure
									{
										System.out.println("Loading " + chosen.getName() + "...");
										load(chosen); //go to action screen loding chosen char
									}
									else
									{
										checking = true;
									}
								}
								else
								{
									System.out.println("Error. Invalid entry. Choose a positive integer no larger than " + characters.size());
									checking = true;
								}
							}
						}while(checking == true);
					}
					else if(strInput.toLowerCase().trim().equals("new")) // if new run new protocol
					{
						characters.add(new Character(""));
						System.out.println("Blank character created! would you like a ");
					}
					else
					{
						System.out.println("Error. Invalid command. Type 'load', 'new', or 'quit'");
					}
					
				}
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			
		} 
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	/**
	 * loads a character for the user to work with.
	 * @param active the character that is being loaded.
	 */
	public static void load(Character active)//move to action screen with a given character
	{
		workingChar = active;
		runningLoadScreen = false;
		runningActionScreen = true;
		while(runningActionScreen)
		{
			printCurrentCharacter(displayingWorkingChar);
			strInput = input.nextLine().toLowerCase();
			String[] parInput = strInput.split(" ");
			try {
				if(parInput[0].equals("see"))
				{
					boolean checktwice = true;
					for(Characteristic c : workingChar.findAllOfType(parInput[1]))
					{
						System.out.print(c + "    ");
						checktwice = false;
					}
					if(checktwice)
					{
						for(Characteristic c : workingChar.findAll(parInput[1]))
						{
							System.out.println("test");
							System.out.println(c);
						}
					}
					else
					{
						System.out.println();
					}
				}
				else if(parInput[0].equals("help"))
				{
					try 
					{
						help(parInput[1]);
					}
					catch(IndexOutOfBoundsException oob)
					{
						help("general");
					}
				}
				else if(parInput[0].equals("display"))
				{
					displayingWorkingChar = Integer.parseInt(parInput[1]);
				}
				else
				{
					System.out.println("'" + strInput + "' is not a known command. type 'help' to see a list of commands");
				}
			}
			catch(Exception e)
			{
				System.out.println("Improper usage of the '" + parInput[0] + "' command. Type 'help " + parInput[0] + "' to see proper usage");
			}
		}
	}
	/**
	 * Writes all character data to the save file.
	 * @param filename the file destination for the save
	 * @throws FileNotFoundException
	 */
	public static void save(String filename) throws FileNotFoundException
	{
		
	}
	/**
	 * Prompts the user with a question 
	 * @param question
	 * @param possibles
	 * @return
	 */
	public static boolean prompt(String question, String[] possibles)//returns true if they dont quit after being prompted by a question. strInput becomes their answer.
	{
		do
		{
			checking = false;
			System.out.println(question + " or type 'quit'");
			strInput = input.nextLine();
			if(strInput.trim().toLowerCase().equals("quit"))
				return false;
			for(String s : possibles)
			{
				if(strInput.trim().toLowerCase().equals(s.toLowerCase()))
					return true;
			}
			System.out.print("Error. Invalid command. Type ");
			for(String s : possibles)
			{
				System.out.print("'" + s + "', ");
			}
			System.out.println("or 'quit'");
			checking = true;
		}while(checking = true);
		System.out.println("Critical Error");
		return false;
	}
	/**
	 * Prints the name of the working character
	 */
	public static void printCurrentCharacter(int level)//if there is a working character, print it to the console
	{
		System.out.println("Character Name : " + workingChar.getSingleData("name"));
	}
	/*public static void  
	{
		for(int i = 1000; i > 0; i--)
		{
			System.out.println();
		}
	}*/
	/**
	 * parses an int from a string
	 * @param s String containing only an int.
	 * @return the int if any, -1 otherwise.
	 */
	public static int getInt(String s)//parses an int from a string, returns -1 if unable to do so.
	{
		try
		{
			System.out.println(Integer.parseInt(s));
			return Integer.parseInt(s);
		}
		catch(Exception e)
		{
			System.out.println("ruh-roh");
			return -1;
		}
	}
	/**
	 * Asks user a yes or no
	 * @return true if yes, false otherwise
	 */
	public static boolean yes()
	{
		while(true)
		{
			System.out.println("Yes or no? (type 'y' or 'n')");
			strInput = input.nextLine();
			if(strInput.equals("y"))
				return true;
			else if(strInput.equals("n"))
				return false;
		}
	}
	/**
	 * Rolls a dice of specified size
	 * @param max the maximum value of the die
	 * @return result of the die roll
	 */
	public static int rolld(int max)
	{
		return die.nextInt((max - 1) + 1 ) + 1;
	}
	/**
	 * Finds an Ability score by rolling four six sided dice then taking the lowest one out
	 * @return The calculated ability score
	 */
	public static int rollAbility()
	{
		int output = 0;
		int[] rolls = new int[4];
		for(int i = 0; i < 4; i++)
		{
			rolls[i] = rolld(6);
		}
		int lowest = 0;
		for(int i = 0; i < rolls.length; i++)
		{
			if(rolls[i] < rolls[lowest])
				lowest = i;
		}
		for(int i : rolls)
		{
			System.out.print(i + " ");
			output += i;
		}
		System.out.print("= ");
			return output - rolls[lowest];
	}
	/**
	 * Reads the descriptions file and prints the specified descrption
	 * @param query the name of the description
	 * @param longer a boolean referring to whether to print a long or short description
	 */
	public static void printDesc(String query, boolean longer)
	{
		String roughwords = "";
		try
		{
			File f = new File("Descriptions.txt");
			Scanner scan = new Scanner(f);
			while(scan.hasNextLine())
			{
				String current = scan.nextLine().toLowerCase();
				if(current.contains("#") && current.contains(query.toLowerCase()))
				{
					if(longer)
						scan.nextLine();
					roughwords = scan.nextLine();
				}
			}
			String[] words = roughwords.split(" ");
			for(int i = 0; i < words.length; i++)
			{
				System.out.print(words[i] + " ");
				if(i % 8 == 0)
					System.out.println();
			}
			System.out.println();
			scan.close();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	public static void help(String command)
	{
		if(command.equals("general"))
		{
			
		}
		else if(command.equals("see"))
		{
			System.out.println("Usage: see <");
		}
		else
		{
			System.out.println("There is no help entry for '" + command + "'");
		}
	}
} 
