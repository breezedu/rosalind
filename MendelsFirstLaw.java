package rosalind;

import java.util.Scanner;

/***************
 * http://rosalind.info/problems/iprb/
 * 
 * Given: Three positive integers k, m, and n, representing a population containing k+m+n organisms: 
 * k individuals are homozygous dominant for a factor, m are heterozygous, and n are homozygous recessive.
 * 
 * Return: The probability that two randomly selected mating organisms will produce 
 * an individual possessing a dominant allele (and thus displaying the dominant phenotype). 
 * Assume that any two organisms can mate.
 * 
 * Sample Dataset
 * 2 2 2
 * 
 * Sample Output
 * 0.78333
 * 
 * @author Frog
 *
 */
public class MendelsFirstLaw {

	public static void main(String[] args){
		
		System.out.println("This is Mendel's First Law program.");
		
		
		//1st, ask user to input k, m, and n;
		System.out.println("Please input homozygous dominant, heterozygous, and homozygous recessive numbers:");
		
		Scanner input = new Scanner(System.in);
		System.out.print("homozygous dominant: k = ");
		int k = input.nextInt();
		System.out.print("heterozygous: m = ");
		int m = input.nextInt();
		System.out.print("homozygous recessive: n = ");
		int n = input.nextInt();
		
		input.close();
		
		
		//2nd, calculate the probabilities of  dominant allele
		
		double da = probDominAllele(k, m, n);
		
		//printout the result:
		System.out.println("The probabilities of dominant allele is: " + da);
		
		
	}//end main()

	private static double probDominAllele(int k, int m, int n) {
		// TODO Calculate the probabilities of dominant allele from three different types of parents;
		// k for AA, m for AB, n for BB, only AB+AB/AB+BB/AA+AA three combinations could produce non-DA;
		
		int sum = k+m+n;
		int total = sum*(sum-1)/2;
		
		//1st, AB+AB, chance is 25% to get BB;
		double abab = m*(m-1)/2.0 * 0.25;
		
		//2nd, AB+BB, chance is 50% to get BB;
		double abbb = (m*n) * 0.5;
		
		//3rd, BB+BB, chance is 100% to get AB;
		double bbbb = n*(n-1) *0.5;		
				
		return 1-(abab + abbb + bbbb)/total;
		
	}//end probDominAllele() method;
	
}//end of everything in MedelsFirstLaw class
