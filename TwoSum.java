package rosalind;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**************
 * http://rosalind.info/problems/2sum/
 * 
 * Given: A positive integer k≤20, a postive integer n≤104, 
 * and k arrays of size n containing integers from −105 to 105.
 * Return: For each array A[1..n], output two different indices 1≤p<q≤n 
 * such that A[p]=−A[q] if exist, and "-1" otherwise.
 * 
 * Sample Dataset
 * 4 5
 * 2 -3 4 10 5
 * 8 2 4 -2 -8
 * -5 2 3 2 -4
 * 5 4 -5 6 8
 * 
 * Sample Output
 * -1
 * 2 4
 * -1
 * 1 3
 * 
 * @author Frog
 *
 */
public class TwoSum {
	
	public static void main(String[] args) throws FileNotFoundException{
		
		System.out.println("This is Two Sum problem.");
		
		
		//1st, read in matrix from 2Sum.txt document;
		Scanner readin = new Scanner(new File("2Sum.txt"));
		
		int row = readin.nextInt();
		int col = readin.nextInt();
		
		int[][] matrix = new int[row][col];
		for(int i=0; i<row; i++){
			
			for(int j=0; j<col; j++){
				
				matrix[i][j] = readin.nextInt();
			}
		}//end outer for i<row loop;
		
		readin.close();
		
		
		//2nd, printout the matrix:
		System.out.println("\nPrintout the matrix: ");		
		printMatrix(matrix);
		
		
		//3rd, check 2Sum for each row:
		for(int i=0; i<row; i++){
			
			check2Sum(matrix[i]);
		}
		
	}//end main();

	private static void check2Sum(int[] array) {
		// TODO Check if there are pair of two elements in the array, sum up to 0;
		
		int len = array.length;
		HashMap<Integer, Integer> twoSumMap = new HashMap<Integer, Integer>();
		boolean getZero = false;
		
		for(int i=0; i<len; i++){

			if(twoSumMap.containsKey(-array[i])){
				System.out.print((twoSumMap.get(-array[i])+1) + " " + (i+1));
				getZero = true;
				i=len;
			
			} else {
				
				twoSumMap.put(array[i], i);
			}
			
		}//end for i<len loop;
		
		if(getZero == false) System.out.print(-1);
		
		System.out.println();
		
	}//end check2Sum() method;

	private static void printMatrix(int[][] matrix) {
		// TODO PrintOut a matrix
		
		int row = matrix.length;
		int col = matrix[0].length;
		
		for(int i=0; i<row; i++){
			
			for(int j=0; j<col; j++){
				
				System.out.print(matrix[i][j] + " ");
			}
			
			System.out.println();
		}//end outer for i<row loop;
		
		System.out.println();
	}//end printMatrix() method;

}//end of everything in TwoSum class;
