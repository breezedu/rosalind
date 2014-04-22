package rosalind;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**************
 * http://rosalind.info/problems/ins/
 * 
 * Given: A positive integer n¡Ü103 and an array A[1..n] of integers.
 * Return: The number of swaps performed by insertion sort algorithm on A[1..n].
 * 
 * Sample Dataset
 * 6
 * 6 10 4 5 1 2
 * 
 * Sample Output
 * 12
 * 
 * @author Frog
 *
 */
public class InsertionSort {

	public static void main(String[] args) throws FileNotFoundException{
		
		System.out.println("This is Insertion Sort program.");
		
		//1st, read in the array from insertion.txt document;
		Scanner readScan = new Scanner(new File("Insertion.txt"));
		
		int Len = readScan.nextInt(); 	//the first integer means the number of elements in the array;
		int[] array = new int[Len];
		
		//read in the array one by one;
		for(int i=0; i<Len; i++){
			array[i] = readScan.nextInt();
		}
		
		readScan.close();
		
		
		//2nd, insertion sort
		int swapTimes = 0;
		for(int i=1; i<Len; i++){
			
			int k=i;
			while(k>0 && array[k] < array[k-1]){
				
				swap(array, k, k-1);
				swapTimes++;
				
				k = k-1;	//check ahead;
			}//end while loop;
			
		}//end outer for i<Len loop;
		
		for(int i=0; i<Len; i++){
			System.out.print(array[i] + " ");
		}
		
		System.out.println("Have to swap " + swapTimes +" times.");
		
	}//end main();

	private static void swap(int[] array, int k, int i) {
		// TODO Auto-generated method stub
		int m = array[k];
		array[k] = array[i];
		array[i] = m;
		
	}//end of swap() method;
	
}//end of everything in InsertionSort class
