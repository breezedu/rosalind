package rosalind;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**************
 * http://rosalind.info/problems/bins/
 * 
 * Given: Two positive integers n≤10^5 and m≤10^5, 
 * a sorted array A[1..n] of integers from −10^5 to 10^5 
 * and a list of m integers −10^5≤ k1,k2,…,km ≤10^5.
 * 
 * Return: For each ki, output an index 1≤j≤n s.t. A[j]=ki or "-1" if there is no such index.
 * 
 * Sample Dataset
 * 5
 * 6
 * 10 20 30 40 50
 * 40 10 35 15 40 20
 * 
 * @author Frog
 *
 */
public class BinarySearch {

	public static void main(String[] args) throws FileNotFoundException{
		
		System.out.println("This is Binary Search program.");
		
		//1st, readin two array from txt document
		Scanner readScan = new Scanner(new File("Binarysearch.txt"));
		
		int numOfArray = readScan.nextInt();
		int numOfInt = readScan.nextInt();
		
		//ReadIn array[];
		HashMap<Integer, Integer> arraySet = new HashMap<Integer, Integer>();
		
		int[] array = new int[numOfArray];	
		System.out.println("\nReadIn array[]:");
		for(int i=0; i<numOfArray; i++){
			array[i] = readScan.nextInt();
			arraySet.put(array[i], i);
			
		//	System.out.print(" " + array[i]);
		}
		
		//ReadIn k[];
		System.out.println("\nReadIn k[]:");
		int[] k = new int[numOfInt];
		for(int i=0; i<numOfInt; i++){
			k[i] = readScan.nextInt();
		//	System.out.print(" " + k[i]);
		}
		
		readScan.close();
		
		
		//2nd, use divide and conquer method to check if k[i] is in the array[];		
		int[] index = new int[numOfInt];
		for(int i=0; i<numOfInt; i++){
			
			if(arraySet.containsKey(k[i])){
				index[i] = arraySet.get(k[i])+1;
				
			} else {
				index[i] = -1;
				
			}
			//index[i] = divideSearch(k[i], array);
			
		}
		
		
		//3rd, printout the index[] array;
		System.out.println("\nThe indice of k[i] in array[] are:");
		for(int i=0; i<numOfInt; i++){
			System.out.print(index[i] + " ");
		}
		
		
	}//end main();

	private static int divideSearch(int i, int[] array) {
		// TODO This method is useless here, because I used hashMap :)
		return 0;
	}
	
}//end of everything in BinarySearch class
