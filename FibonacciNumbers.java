package rosalind;

import java.util.Scanner;

/***************
 * http://rosalind.info/problems/fibo/
 * 
 * Fn=Fn−1+Fn−2;
 * F1 = 1;
 * F0 = 0;
 * 
 * Given: A positive integer n≤25.
 * Return: The value of Fn.
 * 
 * @author Frog
 *
 */
public class FibonacciNumbers {
	
	public static void main(String[] args){
		
		System.out.println("This is Fibonacci Numbers program.");
		
		//1st, ask user to input the integer n
		System.out.println("Please input the integer: (2~25 in this problem) ");
		System.out.print(" n = ");
		
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		input.close();
		
		//2nd, create an array of n elements:
		int[] Fibo = new int[n];
		Fibo[0] = 0;
		Fibo[1] = 1;
		
		//use DP method to calculate every element in Fibo[] array;
		for(int i=2; i<n; i++){
			
			Fibo[i] = Fibo[i-1] + Fibo[i-2];
		}
		
		//in the end, printout Fibo[n-2]+Fibo[n-1], this is the element n;
		System.out.print("Result: " + (Fibo[n-1]+Fibo[n-2]));

	}//end main();

}//end everything in FibonacciNumbers class
