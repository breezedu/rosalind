package rosalind;

import java.util.Scanner;

/*****************
 * Counting Point Mutations
 * Given two strings s and t of equal length, 
 * the Hamming distance between s and t, denoted dH(s,t), 
 * is the number of corresponding symbols that differ in s and t. 
 * 
 * Given: Two DNA strings s and t of equal length (not exceeding 1 kbp).
 * Return: The Hamming distance dH(s,t).
 * 
 * Sample Dataset
 * GAGCCTACTAACGGGAT
 * CATCGTAATGACGGCCT
 * 
 * Sample Output
 * 7
 * 
 * @author Frog
 *
 */
public class CountingPointMutations {

	public static void main(String[] args){
		
		System.out.println("This is Counting Point Mutations program.");
		
		//1st, ask user to input two DNA strings:
		System.out.println("Please input two DNA strings:");
		Scanner input = new Scanner(System.in);
		
		System.out.print(" DNA string one: ");
		String dna1 = input.next();
		System.out.print(" DNA string two: ");
		String dna2 = input.next();
		
		input.close();
		
		
		//2nd, call compareTwoStrings() method to calculate and printout the miss-matches;
		int missMatches = compareTwoStrings(dna1, dna2);
		System.out.println(missMatches);
		
	}//end main();

	private static int compareTwoStrings(String dna1, String dna2) {
		// TODO compare two DNA strings, printout miss-matches
		
		int Len = dna1.length();
		int miss = 0;
		for(int i=0; i<Len; i++){
			if(dna1.charAt(i)!=dna2.charAt(i)) miss++;
		}
		
		return miss;
		
	}//end compareTwoStrings() method;
	
	
}//end of everything in CountingPointMutations class;
