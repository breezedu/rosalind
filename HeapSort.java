package rosalind;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**********
 * http://rosalind.info/problems/hs/
 * 
 * @author Breeze.du
 *
 *Problem
 *The heap sort algorithm first transforms a given array into a max heap 
 *(recall the problem “Building a Heap”). 
 *
 *It then repeats the following two simple steps n−1 times:
 *Swap the last element of the heap with its root and decrease the size of the heap by 1.
 *"Sift-down" the new root element to its proper place.
 *Given: A positive integer n≤105 and an array A[1..n] of integers from −105 to 105.
 *
 *Return: A sorted array A.
 *
 *Sample Dataset
 *9
 *2 6 7 1 3 5 4 8 9
 *
 *Sample Output
 *1 2 3 4 5 6 7 8 9
 *
 */
public class HeapSort {
	
	public static void main(String[] args) throws IOException{
		
		System.out.println("This is HeapSort program.");
		
		//1st, readin an array with random sequence;
		Scanner readin = new Scanner(new File("heapSort.txt"));
		
		int num = readin.nextInt();
		int[] heapArray = new int[num+1];
		heapArray[0] = 9999999;
		
		for(int i=1; i<=num; i++){
			
			int temp = i;
			heapArray[temp] = readin.nextInt();
			
			while(heapArray[temp] > heapArray[temp/2]){
				
				swap(heapArray, temp, temp/2);
				
				temp = temp/2;
			}//end while loop;			
			
		}//end for i<=num loop;
		
		readin.close();
		
		
		//2nd, printout the 'heap' just built:
	//	printHeapArray(heapArray);
		
		
		//3rd, assign the size and the heapArray to a heap object;
		heap newHeap = new heap();
		newHeap.size = num;
		newHeap.array = heapArray;
		
		
		//4th, heap-sort
		
		while(newHeap.size > 1){
			
			//Swap the last element of the heap with its root;
			swap(newHeap.array, 1, newHeap.size);
			
			//Decrease the size of the heap by 1.
			newHeap.size--;
			
			//"Sift-down" the new root element to its proper place.
			reArrangeHeap(newHeap, 1);
			
		//	printHeapArray(newHeap.array);
			
		}//end while newHeap.size>1 loop;
		
		
		
		//5th, printout the array in newHeap;
		
		System.out.println("Printout the heap array:");
		printHeapArray(newHeap.array);
		
	}//end of main()

	
	private static void reArrangeHeap(heap hp, int index) {
		// TODO "Sift-down" the new root element to its proper place.
		
		int child1 = index*2;
		int child2 = child1 + 1;
		
		if(child1 < hp.size) {
			//do nothing;			
		}
		
		if(child2 <= hp.size){
			
			int max = (hp.array[child1] > hp.array[child2]) ? child1: child2;
			
			if(hp.array[max] > hp.array[index]){
				
				swap(hp.array, max, index);
				
				reArrangeHeap(hp, max);
			}
		}//end if child2 < hp.size condition;
		
		if(child1 == hp.size){
			
			if(hp.array[child1] > hp.array[index]){
				
				swap(hp.array, index, child1);
			}
		}
			
	}//end reArrangeHeap() method;

	private static void printHeapArray(int[] array) {
		// TODO printout an array
		
		int leng = array.length;
		for(int i=1; i<leng; i++){
			
			System.out.print(array[i] + " ");
		}
		
		System.out.println();
	}

	private static void swap(int[] array, int m, int n) {
		// TODO swap two elements in an array;
		int temp = array[m];
		array[m] = array[n];
		array[n] = temp;
		
	}//end of swap() method;
	

}//end of everything in HeapSort class

class heap{
	
	int[] array;
	int size;
	
}//end of heap class;
