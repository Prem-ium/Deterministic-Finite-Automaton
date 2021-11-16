import java.io.*;
import java.util.*;
/*
 * Name: Prem Patel
 * Purpose: model a DFA (Deterministic Finite Automaton) and use it to accept strings of the associated language.
 */
public class DFA {
	
	/* Command Prompt Instructions:
	 * cd second-workplace\DFA_Project\src
	 * javac DFA.java
	 * java DFA DFA2.txt
	 */
	
	// Declare all static variables
	private static List<String> fileContents = new ArrayList<String>();
	private static List<String> transitionContent = new ArrayList<String>();
	private static String alphabet, acceptState, states, startState, userInput, transitionInfo;
	private static char currentState;
	private static boolean continueLoop = true;
	
	public static void main(String[] args) {
		// If argument is passed in command prompt, execute the DFA
		if (args.length > 0) {
			String result;
			
			// Try-Catch block to deal with possible IO and general exceptions
			try (
					
				// Instantiate Scanner Using Try-Catch w/ Resources
				Scanner reader = new Scanner(new File(args[0]));
				Scanner input = new Scanner(System.in);
			){
				// Invoke a method to populate static variables with appropriate DFA file contents
				fillStaticContents(reader);
				
				// Print out DFA Contents for User
				System.out.printf("%n\t\t\tDFA File Content:%n%n" +
								"Alphabet:\t\t%s%n"+
								"States:\t\t\t%s%n"+
								"Start States:\t\t%s%n" +
								"Accepted State:\t\t%s%n"+
								"%nTransition FN:%s%n", 
								alphabet, states, startState, acceptState, transitionInfo);
				// Start a do-while loop, loop until user enters 'n'
				do {
					// Prompt user for string input
					System.out.printf("%nInsert a string over {0,1} to test DFA: "); 
					userInput = input.nextLine();
					
					// Retrieve DFA outcome based on user input
					result = (testInput()) ? ("was accepted") : ("was not accepted");
					
					// Print output
					System.out.printf("The following string %s by the DFA: '%s' %n%n"
										, result, userInput);
					
					// Prompt for termination or continuity of DFA Testing program
					System.out.print("Continue testing strings? (y/n): ");
					
					// Reset currentState at startState in the event user would like to continue
					if (input.nextLine().charAt(0) != 'n') {
						currentState = startState.charAt(0); 
						System.out.println();
					} else {
						continueLoop = false;
					}
					
				} while (continueLoop);
				
			} catch (FileNotFoundException e) {
				// Inform user of File Not Found Error
				System.out.printf("%s FILE NOT FOUND. Enter a data file.", args[0]);
			} catch (Exception e) {
				// Handle general exception errors
				e.printStackTrace();
			} finally {
				// Inform user the program has been terminated
				System.out.printf("%n%nProgram executed.%n");
			}
		}
	}
	
	public static boolean testInput() {
		int i = 0;
		// Traverse through length of user input to test DFA
		while (i < userInput.length()) { 
			for (int j = 0; j < transitionContent.size() && i < userInput.length(); j++) { 
				// Checks if the current iteration over transition content matches current state and 
				// Advances depending on what the DFA lists is the next position provided a specific
				// User input character (0 or 1)
				if (transitionContent.get(j).contains(String.format("(%s,%s)->"
																	, currentState, userInput.charAt(i)))) {
					// Increment the value of i, once a current state and user input char has been found
					if (i < userInput.length()) i++;	
					// Move current state to new point, depending on DFA language 
					currentState = transitionContent.get(j).charAt(7); 
				}
			}
		}
		return (acceptState.contains(String.valueOf(currentState))) ? (true) : (false);
	}
	
	public static void fillStaticContents(Scanner reader) {
		
		// Gather information from the DFA text file
		while (reader.hasNextLine()) {
			fileContents.add(reader.nextLine());
		}
		
		// Fill startState info line by line, as listed in the DFA txt file
		alphabet = fileContents.get(0);
		states = fileContents.get(1); 
		startState = fileContents.get(2); 
		currentState = startState.charAt(0);
		acceptState = fileContents.get(3); 

		// Fill transition function array
		for (int i = 4; i < fileContents.size(); i++) { 
			transitionContent.add(fileContents.get(i));
		}
	
		for(int i = 0; i < transitionContent.size(); i++) {
			transitionInfo = String.format("%s\t\t\t%s%n"
											, transitionInfo , transitionContent.get(i));
		}
	}
}
