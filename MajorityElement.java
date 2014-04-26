package rosalind;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**************
 * http://rosalind.info/problems/maj/
 * 
 * Given: A positive integer k¡Ü20, a positive integer n¡Ü104, 
 * and k arrays of size n containing positive integers not exceeding 105.
 * Return: For each array, output an element of this array occurring strictly more than n/2 times 
 * if such element exists, and "-1" otherwise.
 * 
 * Sample DataSet
 * 4 8
 * 5 5 5 5 5 5 5 5
 * 8 7 7 7 1 7 3 7
 * 7 1 6 5 10 100 1000 1
 * 5 1 6 7 1 1 10 1
 * 
 * Sample Output
 * 5 7 -1 -1
 * 
 * @author Frog
 *
 */
public class MajorityElement {

	public static void main(String[] args) throws FileNotFoundException{
		
		System.out.println("This is Majority Element program.");
		
		//1st, create a matrix, and readIn matrix elements from Majorityele.txt document
		Scanner readin = new Scanner(new File("Majorityele.txt"));
		
		int row = readin.nextInt();
		int col = readin.nextInt();
		
		//declare matrix[][]
		int[][] matrix = new int[row][col];
		
		for(int i=0; i<row; i++){
			
			for(int j=0; j<col; j++){
				
				matrix[i][j] = readin.nextInt();
			}
		}//end for i<row loop;
		
		readin.close();
		
		
		//2nd, check the majority element of each row:
		int[] maj = new int[row];
		for(int i=0; i<row; i++){
			maj[i] = majority(matrix[i]);
		}
		
		
		//3rd, printout the majority elements:
		System.out.println("The majority elements:");
		for(int i=0; i<row; i++){
			
			System.out.print(maj[i] + " ");
		}
		
		
	}//end of main();

	/***********
	 * get the majority element in an array, return the element;
	 * if there's no element over 50%, return -1;
	 * Use a HashMap to count the repeat time of each element;
	 * array[i] is the key, repeat times is the value;
	 * when we get an element repeated more than Length/2, return that array[i];
	 * if no element (array[i]) repeated more than Length/2 times, return -1; 
	 * 
	 * @param array
	 * @return
	 */
	private static int majority(int[] array) {
		// TODO Auto-generated method stub
		int Len = array.length;
		HashMap<Integer, Integer> countMap = new HashMap<Integer, Integer>();
		
		for(int i=0; i<Len; i++){
			if(countMap.containsKey(array[i])){
				
				int count = countMap.get(array[i]) + 1;
				
				if(count > Len/2){
					return array[i];
				
				} else {
					
					countMap.put(array[i], count);
				}
			
			} else {
				
				countMap.put(array[i], 1);
			}
		
		}//end for i<Len loop;
		
		return -1;
	}
	
}//end of MajorityElement class
