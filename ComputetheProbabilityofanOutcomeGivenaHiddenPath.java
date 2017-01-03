package rosalind;

import java.util.Scanner;

/**************************************************************
 * http://rosalind.info/problems/ba10b/
 * Compute the Probability of an Outcome Given a Hidden Path
 * 
 * Probability of an Outcome Given a Hidden Path Problem
 * 
 * Given: A string x, followed by the alphabet Σ from which x was constructed, followed by a hidden path π, 
 * followed by the states States and emission matrix Emission of an HMM (Σ, States, Transition, Emission).
 * 
 * Return: The conditional probability Pr(x|π) that string x will be emitted by the HMM given the hidden path π.
 * 
 * @author Jeff
 *
 */
public class ComputetheProbabilityofanOutcomeGivenaHiddenPath {

	public static void main(String[] args){
		
		//step 1: input string/hidden path and the transition matrix:
		Scanner input = new Scanner(System.in); 
				
		System.out.print("Please input the original string:" );
				
		String data_str = input.next();
				
		System.out.print("Please input the hidden path:"); 
		String data_hp = input.next(); 
		
		System.out.println("Now input the transition matrix:"); 
		
		String Row = "AB";
		String Col = "xyz"; 
		
		//create a matrix for probability column given row
		double matrix[][] = new double [Row.length()][Col.length()]; 
		for(int i=0; i<Row.length(); i++){
			
			for(int j=0; j<Col.length(); j++){
				
				matrix[i][j] = input.nextDouble(); 
				
			}
			
		}
		
		System.out.println("\t x \t y \t z");
		for(int i=0; i<Row.length(); i++){
			
			System.out.print(Row.charAt(i));
			
			for(int j=0; j<Col.length(); j++){
				
				System.out.print("\t" + matrix[i][j]); 
				
			}
			
			System.out.println(); 
		}
		
		input.close(); 
		
		
		
		//step 2
		//traverse through string, get a pair of letters, one from string x, another from hidden path
		//get the row index and 
		double prob = 1.0; 
		
		for(int index = 0; index < data_str.length(); index++){
			
			//String pair = data_hp.charAt(index) + data_str.charAt(index) + ""; 
			
			int row = Row.indexOf(data_hp.charAt(index));
			int col = Col.indexOf(data_str.charAt(index)); 
			
			prob *= matrix[row][col]; 
			
		}
		
		System.out.println("The final probability: " + prob); 
		
	}//end main(); 
	
}//ee
