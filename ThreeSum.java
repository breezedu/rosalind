package rosalind;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/***************
 * http://rosalind.info/problems/3sum/
 * 
 * Given: A positive integer k≤20, a postive integer n≤104, 
 * and k arrays of size n containing integers from −105 to 105.
 * Return: For each array A[1..n], output three different indices 1≤p<q<r≤n 
 * such that A[p]+A[q]+A[r]=0 if exist, and "-1" otherwise.
 * 
 * Sample Dataset
 * 4 5
 * 2 -3 4 10 5
 * 8 -6 4 -2 -8
 * -5 2 3 2 -4
 * 2 4 -5 6 8
 * 
 * Sample Output
 * -1
 * 1 2 4
 * 1 2 3
 * -1
 *  
 * @author Frog
 *
 */
public class ThreeSum {

	public static void main(String[] args) throws FileNotFoundException{
		
		System.out.println("This is Three Sum program.");
		
		//1st, read in matrix from 3Sum.txt document;
		Scanner readin = new Scanner(new File("3Sum.txt"));
		
		int row = readin.nextInt();
		int col = readin.nextInt();
		
		int[][] matrix = new int[row][col];
		for(int i=0; i<row; i++){
			
			for(int j=0; j<col; j++){
				
				matrix[i][j] = readin.nextInt();
			}
		}//end outer for i<row loop;
		
		readin.close();
		
		//2nd, printout the matrix;
		System.out.println("Printout the matrix:");
	//	printMatrix(matrix);
		
		
		//3rd, check 3-sums;
		System.out.println("Printout 3 sums:");
		for(int i=0; i<row; i++){
			
			check3Sum(matrix[i]);
			
		}
		
	}//end main();

	/************
	 * Use a HashMap to store every array element and it's index;
	 * Sort the array;
	 * pick array[i], use two pointers pointing to array[i+1] and array[end];
	 * if array[p1]+array[p2] == -array[i], then we get a triplet set:
	 * 		check out their indices from HahMap, then printout in asdent order;
	 * else, if the sum of array[p1]+array[p2]>-array[i], p2 move left;
	 * else, if array[p1]+array[p2]<-array[i], p1 move right;
	 * when p2==p1, jump this loop;
	 * 
	 * if no such triplet set found, print -1;
	 * 
	 * @param array
	 */
	private static void check3Sum(int[] array) {
		// TODO Check if there is 3-sum in the array sum up to 0;
		int len = array.length;
		HashMap<Integer, Integer> indexMap = new HashMap<Integer, Integer>();
		
		//put every element and it's index into hashMap:
		for(int i=0; i<len; i++){
			indexMap.put(array[i], i);
		}
		
		//sort the array:
		Arrays.sort(array);
				
		for(int i=0; i<len-2; i++){
			
			int p2 = i+1; 
			int p3 = len-1;
			
			while(p2!=p3){
				int sum = array[p2] + array[p3];
				
				if(array[i] == -sum){
					printOrder((indexMap.get(array[i])+1), (indexMap.get(array[p2])+1), (indexMap.get(array[p3]) +1));
					
					return;
					
				} else if(array[i] < -sum){
					p2++;
					
				} else {
					p3--;
				}
			}
			
		}//end for i<len-2 loop;
		
		System.out.println(-1);
	}

	private static void printOrder(int a, int b, int c) {
		// TODO Printout three integers in ascent order:
		int m=0;
		if(a>b) {
			m = a;
			a = b;
			b = m;
		}
		
		if(a>c){
			m = a;
			a = c;
			c = m;
		}
		
		if(b>c){
			m = b;
			b = c;
			c = m;
		}
		
		System.out.println(a + " " + b +" " +c);
	}//end printOrder() method;


	/*******
	private static void printMatrix(int[][] matrix) {
		// TODO Auto-generated method stub
		int row = matrix.length;
		int col = matrix[0].length;
		
		for(int i=0; i<row; i++){
			for(int j=0; j<col; j++){
				
				System.out.print(matrix[i][j] + " ");
			}
			
			System.out.println();
		}
		
	}//end printMatrix() method;
	*/
	
}//end of everything in ThreeSum class;
