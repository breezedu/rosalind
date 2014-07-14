package rosalind;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**************
 * http://rosalind.info/problems/hea/
 * http://en.wikipedia.org/wiki/Heap_(data_structure)
 * 
 * Given: A positive integer n≤105 and array A[1..n] of integers from −105 to 105.
 * Return: A permuted array A satisfying the binary max heap property: for any 2≤i≤n, A[i/2]≥A[i].
 * 
 * Sample DataSet
 * 5
 * 1 3 5 7 2
 * 
 * Sample Output
 * 7 5 1 3 2
 * 
 * @author Breeze.du
 *
 */
public class BuildingaHeap {
	
	public static void main(String[] args) throws FileNotFoundException{
		
		System.out.println("This is Building a Heap program.");
		
		//1st of all, readin the num of elements in the heap
		Scanner readin = new Scanner(new File("heap.txt"));
		
		int num = readin.nextInt();
		int[] oriArray = new int[num];
				
		for(int i=0; i<num; i++){
			oriArray[i] = readin.nextInt();
		}
		
		readin.close();
		
		
		//2nd, build the heap-array base on the original array;
		int[] heapArray = new int[num+1];
		heapArray[0] = 999999;
		
		for(int i=0; i<num; i++){
			addToHeap(oriArray[i], i+1, heapArray);
		}
		
		
		//3rd, printout the heapArray
		System.out.println();
		for(int i=1; i<=num; i++){
			System.out.print(heapArray[i] + " ");
		}
		
	}//end main();

	/*************
	 * add a new element newNode to the heapArray;
	 * @param newNode
	 * @param j
	 * @param heapArray
	 */
	private static void addToHeap(int newNode, int j, int[] heapArray) {
		// TODO add a new node to the heap
		//first, add the newNode to the heapArray[j];
		
		heapArray[j] = newNode;
		
		
		//second, swap if the node is bigger than it's parent
		while(heapArray[j] > heapArray[j/2]){
			swap(heapArray, j, j/2);
			j = j/2;
			
		}//end while() loop;
		
	}//end of addToHeap() method;

	private static void swap(int[] array, int j, int i) {
		// TODO swap two elements in an array
		
		int temp = array[j];
		array[j] = array[i];
		array[i] = temp;
		
	}//end swap() method;

}//end of everything in BuildingaHeap class
