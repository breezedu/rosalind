package rosalind;

import java.util.Scanner;

/***************
 * http://rosalind.info/problems/fibd/
 * Problem
 * Recall the definition of the Fibonacci numbers from “Rabbits and Recurrence Relations”, 
 * which followed the recurrence relation Fn=Fn−1+Fn−2 and assumed that each pair of rabbits 
 * reaches maturity in one month and produces a single pair of offspring (one male, one female) 
 * each subsequent month.
 * Our aim is to somehow modify this recurrence relation to achieve a dynamic programming 
 * solution in the case that all rabbits die out after a fixed number of months. 
 * See Figure 4 for a depiction of a rabbit tree in which rabbits live for three months 
 * (meaning that they reproduce only twice before dying).
 * 
 * Given: Positive integers n≤100 and m≤20.
 * Return: The total number of pairs of rabbits that will remain after the n-th month 
 * if all rabbits live for m months.
 * Sample Dataset:   6 3
 * Sample Output:    4
 * 
 * @author Frog
 *
 */
public class MortalFibonacciRabbits {

	public static void main(String[] args){
		
		System.out.println("This is Mortal Fibonacci Rabbits program.");
		
		//1st, ask user to input n and m;
		Scanner input = new Scanner(System.in);
		System.out.print(" n = ");
		int n = input.nextInt();
		
		System.out.print(" m = ");
		int m = input.nextInt();
		input.close();
		
		/************
		 * Careful, here we have to use long instead of int;
		 */
		//2nd, calculate the rabbits left at nth month;
		long[] F = new long[n+1];
		long[] rabit = new long[m];
		rabit[0] = 1;
		F[1] = 1;
		
		for(int i=2; i<=n; i++){	
			
			long[] next = new long[m];
			for(int j=1; j<m; j++){
				next[j] = rabit[j-1];
				next[0] += rabit[j];
			}
					
			for(int k=0; k<m; k++){
				rabit[k] = next[k];
				F[i] += rabit[k];
			}
			
			System.out.print(" " +F[i]);
		}
		
		System.out.println("\nAt " + n +"th month, there are " +F[n] +" rabbits.");
		
	}//end main();
}//end of everything in MortalFibonacciRabbits class;
