package rosalind;

import java.util.Scanner;


/**********************************************************
 * http://rosalind.info/problems/ba10d/
 * 
 * Given: A string x, followed by the alphabet Σ from which x was constructed, 
 * followed by the states States, transition matrix Transition, and emission matrix Emission of an HMM (Σ, States, Transition, Emission).
 * 
 * Return: The probability Pr(x) that the HMM emits x.
 * 
 * @author Jeff
 *
 */
public class ComputetheProbabilityofaStringEmittedbyanHMM {
	
	public static void main(String[] args){
		
		//step 1: input string/hidden path and the transition matrix:
		Scanner input = new Scanner(System.in); 
				
		System.out.print("Please input the string X:" );
		String strX = input.nextLine(); 
				
		
		System.out.print("Please input the transition matrix: (AB * AB)"); 
		
		double transition[][] = new double[2][2];
		for(int i=0; i<"AB".length(); i++){
			
			for(int j=0; j<"AB".length(); j++){
				
				transition[i][j] = input.nextDouble();
			}
			
		}//end i<hmm.length(); 
		
		
		//emission matrix
		System.out.println("Now input the emission matrix:"); 
		
		String Row = "AB";			//"AB" in this case; 
		String Col = "xyz"; 		//"xyz" in this case; 
		
		//create a matrix for probability column given row
		double emission[][] = new double [Row.length()][Col.length()]; 
		for(int i=0; i<Row.length(); i++){
			
			for(int j=0; j<Col.length(); j++){
				
				emission[i][j] = input.nextDouble(); 				

			}
			
		}//end for i<Row.length(); 
	
		
		//close the input scanner
		input.close(); 
		
		
		//Step 2, traverse through the string-X, check each character, 
		double prob = 1.0; 
		
		//the probability of the very first char
		double prob_max = emission[0][0];
		String hmm = ""; 
		
		for(int i=1; i<Row.length(); i++){
			if(emission[i][0] > prob_max) prob_max = emission[i][0]; 
			//initial the first char of hmm;
			hmm = Row.charAt(i) + ""; 
		}
		
		prob *= prob_max; 
		
		
		//1st, get HMM probability of AB/AA/BA/BB, whichever possbile at current character; 
		//2nd, get emission probability
		//3rd, use emission probability*HMM, check the maximum probability; 
		for(int i=1; i<strX.length(); i++){
			
			prob_max = 0.0; 
			
			//the row index of transition matrix; 
			int row_transition = Row.indexOf( hmm.charAt(i-1) ); 
			
			for( int j=0; j<Col.length(); j++){
				
				
			}
		}
		
		
		
		System.out.println("The final probability is: " + prob);
		
	}//end main(); 

}//ee
