package rosalind;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/***************
 * http://rosalind.info/problems/mer/
 * 
 * Given: A positive integer n≤105 and a sorted array A[1..n] of integers from −105 to 105, 
 * a positive integer m≤105 and a sorted array B[1..m] of integers from −105 to 105.
 * Return: A sorted array C[1..n+m] containing all the elements of A and B.
 * 
 * Sample Dataset
 * 4
 * 2 4 10 18
 * 3
 * -5 11 12
 * 
 * Sample Output
 * -5 2 4 10 11 12 18
 * 
 * @author Frog
 *
 */
public class MergeTwoSortedArrays {
	
	public static void main(String[] args) throws FileNotFoundException{
		
		System.out.println("This is Merge Two Sorted Arrays program.");
		
		
		//1st, readIn two sorted arrays from MergeTwoArray.txt document;
		Scanner readin = new Scanner(new File("MergeTwoArrays.txt"));
		
		//read in arrayOne;
		int Len1 = readin.nextInt();
		int[] arrayOne = new int[Len1];
		for(int i=0; i<Len1; i++){
			
			arrayOne[i] = readin.nextInt();
		}
		
		//read in arrayTwo;
		int Len2 = readin.nextInt();
		int[] arrayTwo = new int[Len2];
		for(int i=0; i<Len2; i++){
			
			arrayTwo[i] = readin.nextInt();
		}
		
		readin.close();
		
		
		//2nd, Merge Sort two arrays;
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
		}
		
		
		//3rd, printout the new array after merging;
		for(int i=0; i<Len3; i++){
			
			System.out.print(arrayThree[i] + " ");
		}
	}//end main();

}//end of everything in MergeTwoSortedArrays class;
