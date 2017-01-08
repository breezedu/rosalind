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
				
		
		System.out.println("Please input the transition matrix: (AB * AB)"); 
		
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
		
		int col_first = Col.indexOf(strX.charAt(0)); 
		
		double prob_max = emission[0][col_first];
		String hmm = ""; 
		
		for(int i=0; i<Row.length(); i++){
			
			for(int t = 0; t<Row.length(); t++){
				
				if(emission[i][col_first] * transition[i][t] > prob_max) prob_max = emission[i][0] * transition[i][t]; 
				//initial the first char of hmm;
				hmm = Row.charAt(i) + ""; 
				
			}
			
		}
		
		prob *= prob_max; 
		
		
		//1st, get HMM probability of AB/AA/BA/BB, whichever possbile at current character; 
		//2nd, get emission probability
		//3rd, use emission probability*HMM, check the maximum probability; 
		for(int i=1; i<strX.length(); i++){
			
			System.out.println("hmm: " + hmm); 
			
			prob_max = 0.0; 
			char nextHMM = 'X'; 
			
			//the row index of transition matrix; 
			int row_transition = Row.indexOf( hmm.charAt(i-1) ); 
			
			for( int col_transition=0; col_transition<Row.length(); col_transition++){
				
				System.out.print(" row and col of Transition are: " + row_transition + "-" + col_transition);
				
				double probT = transition[row_transition][col_transition]; 
				
				System.out.print(" " + probT); 
				
				for( int emis = 0; emis <Col.length(); emis++){
					
					double probE = emission[row_transition][emis]; 
					
					System.out.print("\t probE:" + probE); 
					
					if(probT * probE > prob_max)	{
						
						prob_max = probT * probE;
						
						nextHMM = Row.charAt(col_transition); 
					}
					
				} //end for emis < col.length()
				System.out.println(); 
				
			}//end t<transition.length; 
			
			System.out.println(); 
			
			//update hmm
			hmm += nextHMM; 
			
			prob *= prob_max; 
		}
		
		
		
		System.out.println("The final probability is: " + prob);
		
	}//end main(); 

}//ee
