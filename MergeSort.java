package rosalind;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/***************
 * http://rosalind.info/problems/ms/
 * 
 * Given: A positive integer n≤105 and an array A[1..n] of integers from −105 to 105.
 * Return: A sorted array A[1..n].
 * 
 * Sample Dataset
 * 10
 * 20 19 35 -18 17 -20 20 1 4 4
 * 
 * Sample Output
 * -20 -18 1 4 4 17 19 20 20 35
 * 
 * @author Frog
 *
 */
public class MergeSort {

	public static void main(String[] args) throws FileNotFoundException{
		
		System.out.println("This is Merge Sort program.");
		
		//1st, read in array from mergesort.txt document:
		Scanner readin = new Scanner(new File("mergesort.txt"));
		
		int Len = readin.nextInt();
		int[] array = new int[Len];
		
		for(int i=0; i<Len; i++){
			array[i] = readin.nextInt();
		}
		
		readin.close();
		
		
		//2nd, merge-sort the array:		
		array = divideandcounquer(array);		
		
		// compare with	Arrays.sort(array);
		
		
		//3rd, printout the array after mergeSort();
		for(int i=0; i<Len; i++){
			System.out.print(array[i] + " ");
		}
		
	}//end main();

	private static int[] divideandcounquer(int[] array) {
		// TODO Auto-generated method stub
		
		if(array.length>1){
			
			int mid = array.length/2;
			int[] left = new int[mid];
			int[] right = new int[array.length-mid];
			
			//assign the left half into left[] array;
			for(int i=0; i<mid; i++){
				left[i] = array[i];
			}
			
			//assign the right half into right[] array;
			for(int i=mid; i<array.length; i++){
				right[i-mid] = array[i];
			}
			
			//divideandcounquer(left) and (right) arrays;
			left = divideandcounquer(left);
			right = divideandcounquer(right);
			
			//merge left and right arrays into one array;
			array = mergeTwo(left, right);
			
		}//end if array.length>1 condition;
		

		return array;
		
	}//end divideandcounquer() method;

	private static int[] mergeTwo(int[] arrayOne, int[] arrayTwo) {
		// TODO merge two sorted arrays into one array;
		int Len1 = arrayOne.length;
		int Len2 = arrayTwo.length;
		
		int Len3 = Len1 + Len2;
		int[] arrayThree = new int[Len3];
		int p1 = 0;
		int p2 = 0;
		int p3 = 0;
		
		while(p1<Len1 && p2<Len2){
			if(p1<Len1 && arrayOne[p1] < arrayTwo[p2]){
				
				arrayThree[p3] = arrayOne[p1];
				p1++;
				p3++;
			
			} else {
				
				arrayThree[p3] = arrayTwo[p2];
				p2++;
				p3++;
			}
		}//end while p1<Len1&&p2<Len2 loop;
		
		//right now, p1 or p2 reaches the end of the array;
		//merge the left elements to arrayThree directly:
		while(p1 < Len1){
			arrayThree[p3] = arrayOne[p1];
			p3++;
			p1++;
		}
		
		while(p2 < Len2){
			arrayThree[p3] = arrayTwo[p2];
			p2++;
			p3++;
			
		}//end while loop;
		
		return arrayThree;
		
	}//end mergeTwo() method;
	
}//end of everything in MergeSort class
