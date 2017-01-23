package rosalind;

import java.util.Scanner;


/**********************************************************
 * https://en.wikipedia.org/wiki/Viterbi_algorithm
 *  
 * Given: A string x, followed by the alphabet Sum from which x was constructed, 
 * followed by the states States, transition matrix Transition, 
 * and emission matrix Emission of an HMM (Sum, States, Transition, Emission)..
 * 
 * Return: A path that maximizes the (unconditional) probability Pr(x, Pai) over all possible paths Pai..
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
		double state2[][] = new double[Row.length()][strX.length()]; 
		
		//create a back track matrix to record the path:
		int path[][] = new int[Row.length()][strX.length()]; 
		
		
		for(int i=0; i<Row.length(); i++){
			
			for(int j=0; j<strX.length(); j++){
				
				//the state matrix will share the same Row name with the transition matrix; 
				int row = i; 
				int col = Col.indexOf( strX.charAt(j) );
				
				state[i][j] = emission[row][col]; 
				state2[i][j] = 0; 
				
			}
		}
		
		System.out.println("Print out original state matrix:"); 
		printMatrix(state); 
		
		/**************************************************************************************************/
		//Step 3, traverse through the string-X, check each character,
		
		//step 3.1, update the first column of the state[][] matrix; 
		//for the first column of the state[][] matrix; 
		// it could be the max of [AAx or BAx ]
		// or could be the max of [ABx or BBx ]
		int row = state.length; 
		int col = state[0].length; 
		
		double ptransition[] = new double[row];
		for(int i= 0; i<row; i++){
			ptransition[i] = 1.0/row; 
		}
		
		
		
		for( int i=0; i<row; i++){
			
			//when i = 0; the state[0][0] would be AAx or BAx, we take the greater value from AB*AB transition matrix first column, 
			// 
			//call maxOfcolumn() method to get the max value of a column.
			state2[i][0] = ptransition[i] * state[i][0]; 
			
		}
		
		
		//printMatrix(state2); 
		
		
		
		//step 4, update all other elements in the state[][] matrix; 
		
		for(int i=1; i<col; i++){
			
			for( int j=0; j<row; j++){
				
				//get row index of current state (x, y, or z) in the transition matrix;
				// this is the col index in the transition matrix; 
				
				int currTransitionCol = j; 
				
				//pass a matrix and the column, return an array of that column
				//get the correspond transition column
				double transitionArray[] = getOneColumnFromMatrix(transition, currTransitionCol); 
				
				
				//get the prior state column; 
				double priorState[] = getOneColumnFromMatrix(state2, i-1); 
				
				//multiple two arrays, (equal length in this case;
				double arrayMulti[] = arrayMultiple(transitionArray, priorState); 
				
				// use current state[j][i] * max_of_the two array multiplied result; 
				state2[j][i] = state[j][i] * arrayMulti[ maxOfArray(arrayMulti) ]; 
				
				//check path
				path[j][i] = maxOfArray(arrayMulti); 

				//	
				//printMatrix(state2); 
				
			}//end for j<row loop; 
			
		}//end for i<col loop; 
		
		System.out.println("\nPrint out path state matrix:"); 
		printMatrix(state2); 
		
		System.out.println("\nPrint out original Path matrix:"); 
		printIntMatrix(path); 
		
		
		
		//step 4, back trace the state[][] matrix, get the HMM hidden path;
		//the last character of the hidden path: 
		double lastCol[] = getOneColumnFromMatrix(state2, col-1);
		String hiddenPath = ""; 
		
		int pivot = maxOfArray(lastCol); 
		for(int i=col-1; i>0; i--){
			
			hiddenPath = Row.charAt(pivot) + hiddenPath; 
			
			pivot = path[pivot][i]; 
			 
		} //end for i<col loop; 
		
		hiddenPath = Row.charAt(pivot) + hiddenPath; 
		
		System.out.println("The finial hidden path: " + hiddenPath); 
		
		
		
	}//end main(); 


	private static void printIntMatrix(int[][] path) {
		// TODO Auto-generated method stub
		int row = path.length; 
		int col = path[0].length; 
		
		for(int i=0; i<row; i++){
			
			for(int j=0; j<col; j++){
				
				System.out.print(path[i][j]); 
			}
			
			System.out.println(); 
		}
	}//end printChrMatrix() ; 


	/***********
	 * Return the maximum value in an array; 
	 * @param array
	 * @return
	 */
	private static int maxOfArray(double[] array) {
		// TODO Auto-generated method stub
		int max_index = 0; 
		double max = 0; 
		for(int i=0; i<array.length; i++){
			
			if(array[i] > max) {
				max = array[i];
				max_index = i; 
			}
		}
		
		return max_index;
	}//end maxOfArray(); 



	/*************************************
	 * 
	 * @param stateArray
	 * @param priorState
	 * @return
	 */
	private static double[] arrayMultiple(double[] array1, double[] array2) {
		// TODO Auto-generated method stub
		
		int length = array1.length; 
		
		double retArray[] = new double[length]; 
		for(int i=0; i<length; i++){
			
			retArray[i] = array1[i] * array2[i]; 
		}
		
		return retArray;
		
	}//end of arrayMultiple()


	/************************
	 * 
	 * @param matrix
	 * @param col
	 * @return
	 */
	private static double[] getOneColumnFromMatrix(double[][] matrix, int col) {
		// TODO Auto-generated method stub
		
		int num = matrix.length; 
		double array[] = new double[num]; 
		
		for(int row = 0; row <num; row++){
			
			array[row] = matrix[row][col]; 
		}
		
		return array;
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
