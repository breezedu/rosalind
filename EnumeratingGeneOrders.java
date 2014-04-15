package rosalind;

import java.util.ArrayList;
import java.util.Scanner;

/*************
 * Given: A positive integer n¡Ü7.
 * Return: The total number of permutations of length n, 
 * followed by a list of all such permutations (in any order).
 * 
 * Sample Dataset
 * 3
 * Sample Output
 * 6
 * 1 2 3
 * 1 3 2
 * 2 1 3
 * 2 3 1
 * 3 1 2
 * 3 2 1
 * 
 * @author Frog
 *
 */
public class EnumeratingGeneOrders {
	
	public static void main(String[] args){
		
		System.out.println("This is Enumerating Gene Orders program.");
		
		//1st, ask user to input the integer n:
		System.out.println("Please input the integer:");
		System.out.print(" n = ");
		
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		input.close();
		
		
		//2nd, use 'insert' method to generate all enumerate orders:
		ArrayList<ArrayList<Integer>> Enumerate = generateEnumerates(n);
		
		System.out.println(Enumerate.size());
		printALofAL(Enumerate);
		
	}//end main();

	private static ArrayList<ArrayList<Integer>> generateEnumerates(int n) {
		// TODO Generate all enumerate number orders
		ArrayList<ArrayList<Integer>> retAL = new ArrayList<ArrayList<Integer>>();
		
		ArrayList<Integer> one = new ArrayList<Integer>();
		one.add(1);
		retAL.add(one);
		
		if(n==1) return retAL;
		
		for(int i=2; i<=n; i++){
			ArrayList<ArrayList<Integer>> newAL = new ArrayList<ArrayList<Integer>>();
			int numOfAL = retAL.size();
			
			for(int j=0; j<numOfAL; j++){
				ArrayList<Integer> temp = retAL.get(j);
				
				int Len = temp.size();
				for(int k=0; k<=Len; k++){
					ArrayList<Integer> nextTemp = new ArrayList<Integer>(temp);
					nextTemp.add(k, i);
					
					newAL.add(nextTemp);
				}//end for k<Len loop;
				
			}//end for j<numOfAL loop;
			
			retAL = newAL;
			
		}//end for i<=n loop;
		
		return retAL;
	}

	private static void printALofAL(ArrayList<ArrayList<Integer>> enumerate) {
		// TODO printout ArrayList of ArrayList
		
		for(ArrayList<Integer> e: enumerate){
			printArrayList(e);
		}
		
		System.out.println();
	}//end of printALofAL() method;
	
	private static void printArrayList(ArrayList<Integer> al){
		
		int size = al.size();
		
		for(int i=0; i<size; i++){
			System.out.print(al.get(i) + " ");
		}
		
		System.out.println();
	}//end of printArrayList() method;

}//end of everything in EnumeratingGeneOrders class
