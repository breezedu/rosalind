package rosalind;

import java.util.Scanner;


/**********************************************************
 * http://rosalind.info/problems/ba10c/
 * 
 * Given: A string x, followed by the alphabet Σ from which x was constructed, 
 * followed by the states States, transition matrix Transition, 
 * and emission matrix Emission of an HMM (Σ, States, Transition, Emission)..
 * 
 * Return: A path that maximizes the (unconditional) probability Pr(x, π) over all possible paths π..
 * 
 * @author Jeff
 *
 */
public class ImplementViterbiAlgorithm {
	
	public static void main(String[] args){
		
		
		/**************************************************************************************************/
		//step 1: input string/hidden path and the transition matrix:
		Scanner input = new Scanner(System.in); 
				
		System.out.print("Please input the string X:  " );
		String strX = input.nextLine(); 
				
		System.out.println("Please input the component of transition matrix: "); 
		String transStr = input.nextLine();
		
		System.out.println("Please input the transition matrix: (AB * AB)"); 
		// copy:   0.641   0.359 0.729   0.271     0.117   0.691   0.192 0.097   0.42    0.483
		 
		
		int dim = transStr.length(); 
		
		double transition[][] = new double[dim][dim];
		for(int i=0; i<dim; i++){
			
			for(int j=0; j<dim; j++){
				
				transition[i][j] = input.nextDouble();
			}
			
		}//end i<hmm.length(); 
		
		printMatrix(transition); 
		
		//emission matrix
		System.out.println("Now input the emission matrix:"); 
		//copy:      0.117   0.691   0.192 0.097   0.42    0.483
		
		String Row = transStr;			//"AB" in this case; 
		String Col = "xyz"; 		//"xyz" in this case; 
		
		//create a matrix for probability column given row
		double emission[][] = new double [Row.length()][Col.length()]; 
		for(int i=0; i<Row.length(); i++){
			
			for(int j=0; j<Col.length(); j++){
				
				emission[i][j] = input.nextDouble(); 				

			}
			
		}//end for i<Row.length(); 
		
		printMatrix(emission); 
		
		//close the input scanner
		input.close(); 
		
		
		
		/**************************************************************************************************/
		//step 2, build a state-matrix 
		double state[][] = new double[Row.length()][strX.length()]; 
		
		for(int i=0; i<Row.length(); i++){
			
			for(int j=0; j<strX.length(); j++){
				
				//the state matrix will share the same Row name with the transition matrix; 
				int row = i; 
				int col = Col.indexOf( strX.charAt(j) );
				
				state[i][j] = emission[row][col]; 
				
			}
		}
		
		printMatrix(state); 
		
		/**************************************************************************************************/
		//Step 3, traverse through the string-X, check each character,
		
		//step 3.1, update the first column of the state[][] matrix; 
		//for the first column of the state[][] matrix; 
		// it could be the max of [AAx or BAx ]
		// or could be the max of [ABx or BBx ]
		int row = state.length; 
		int col = state[0].length; 
		
		
		for( int i=0; i<row; i++){
			
			//when i = 0; the state[0][0] would be AAx or BAx, we take the greater value from AB*AB transition matrix first column, 
			// 
			//call maxOfcolumn() method to get the max value of a column.
			state[i][0] = state[i][0] * maxOfColumn(transition, i); 
			
		}
		
		
		printMatrix(state); 
		
		
		
		//step 4, update all other elements in the state[][] matrix; 
		
		for(int i=1; i<col; i++){
			
			for( int j=0; j<row; j++){
				
				state[j][i] = state[j][i] * maxOfColumn(state, i-1); 
				
			}//end for j<row loop; 
			
		}//end for i<col loop; 
		
		printMatrix(state); 
		
		
		//step 4, back trace the state[][] matrix, get the HMM hidden path; 
		String hiddenPath = ""; 
		
		for(int i=0; i<col; i++){
			
			double currMax = 0.0; 
			char currChar = 'X';
			
			for(int j=0; j<row; j++){
				
				if(state[j][i] > currMax){
					
					currMax = state[j][i]; 
					currChar = Row.charAt(j); 
					
				}//end if
				
			} //end for j<row loop 
			
			
			hiddenPath += currChar; 
		} //end for i<col loop; 
		
		System.out.println("The finial hidden path: " + hiddenPath); 
		
		/*****************************************************
		 * 
		 * 
		double prob = 1.0; 
		
		//the probability of the very first char
		
		int col_first = Col.indexOf(strX.charAt(0)); 
		
		double prob_max = 0;
		String hmm = ""; 
		
		for(int i=0; i<Row.length(); i++){
			
			if(emission[i][col_first]  > prob_max) {
			
				hmm = Row.charAt(i) + ""; 
				prob_max = emission[i][col_first]; 
					
			}
				//initial the first char of hmm;			
		}
		
		prob *= prob_max; 
		
		
		//1st, get HMM probability of AB/AA/BA/BB, whichever possbile at current character; 
		//2nd, get emission probability
		//3rd, use emission probability*HMM, check the maximum probability; 
		for(int i=1; i<strX.length(); i++){
			
			System.out.println("hmm: " + hmm); 
			
			prob_max = 0.0; 
			char nextHMM = 'X'; 
			
			// Here we know the prior Letter in the HMM hidden path is 'A'; 
			// the current letter in the String-X is y;
			// so the current pattern could be AAy or ABy; 
			
			//construct the pattern: String pattern = hmm.charAt(i-1) + "A/B" + strX.charAt(i); 
			
			for(int index = 0; index < Row.length(); index++){
				
			//	String pattern = "" + hmm.charAt(i-1) + Row.charAt( index ) + strX.charAt(i); 
				
				int transition_row = Row.indexOf( hmm.charAt(i-1) );
				int transition_col = Row.indexOf( Row.charAt( index) );
				
				int emission_row = transition_col; 
				int emission_col = Col.indexOf( strX.charAt(i) ); 
				
				double currProb = transition[transition_row][transition_col] * emission[emission_row][emission_col];
				
				if( currProb > prob_max){
					
					prob_max = currProb;
					nextHMM = Row.charAt(index); 
					
				}//end if( currProb > prob_max)
				
			}//end index < Row.length(); 
			
			hmm += nextHMM; 
			
			prob *= prob_max; 
			
		} //end for i<strX.length() loop; 
		
		
		System.out.println("The hmm: " + hmm); 
		System.out.println("The final probability is: " + prob);
		
		*/
		
	}//end main(); 

	
	/***********************
	 * get the max of a column from a matrix; 
	 * @param matrix
	 * @param col
	 * @return
	 */
	private static double maxOfColumn(double[][] matrix, int col) {
		// TODO Auto-generated method stub
		double max = 0; 
		for(int i=0; i<matrix.length; i++){
			
			if(matrix[i][col] > max)
				max = matrix[i][col]; 
		}
		
		return max;
	}


	//print a matrix
	private static void printMatrix(double[][] matrix) {
		// TODO Auto-generated method stub
		
		int row = matrix.length; 
		int col = matrix[0].length; 
		
		System.out.println("Print out matrix."); 
		for(int i=0; i< row; i++){
			
			for(int j=0; j< col; j++){
				
				System.out.print(matrix[i][j] + "\t"); 
			}
			System.out.println(); 
		}
		
	}

}//ee
