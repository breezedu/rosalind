package rosalind;

import java.util.Scanner;

/**************
 * http://rosalind.info/problems/fib/
 * Given: Positive integers n¡Ü40 and k¡Ü5.
 * Return: The total number of rabbit pairs that will be present after n months
 * if we begin with 1 pair and in each generation, 
 * every pair of reproduction-age rabbits produces a litter of k rabbit pairs 
 * (instead of only 1 pair).
 * 
 * Sample Dataset:  5 3
 * Sample Output:   19
 * 
 * @author Frog
 *
 */
public class RabbitsandRecurrenceRelations {
	
	public static void main(String[] args){
		
		System.out.println("This is Rabbits and RecurrenceRelations program.");
		
		//1st, ask user to input n and k;
		Scanner input = new Scanner(System.in);
		System.out.print(" n = ");
		int n = input.nextInt(); 	//n>1;
		
		System.out.print(" k = ");
		int k = input.nextInt();
		input.close();
		
		
		//2nd, call recurrently calculate how many rabbit pairs at month n;
		int[] F = new int[n+1];
		F[1] = 1;
		F[2] = 1;
		
		//only rabbits older than 1 month could give birth to next generation;
		for(int i=3; i<=n; i++){
			F[i] = F[i-2]*k + F[i-1];
		}
		
		System.out.println("The " + n + "th months will have " + F[n] + " rabbit pairs.");
		
	}//end main();

}//end of everything in RabbitsandRecurrenceRelations class
