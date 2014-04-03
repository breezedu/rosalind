package rosalind;

import java.util.Scanner;

/***************
 * http://rosalind.info/problems/subs/
 * 
 * Given: Two DNA strings s and t (each of length at most 1 kbp).
 * Return: All locations of t as a substring of s.
 * 
 * Sample Dataset
 * GATATATGCATATACTT
 * ATAT
 * Sample Output
 * 2 4 10
 * 
 * @author Frog
 *
 */
public class FindingaMotifinDNA {
	
	public static void main(String[] args){
		
		System.out.println("This is Finding a Motif in DNA program.");
		
		//1st, ask user to input both original DNA string and the motif string;
		System.out.println("Please input both DNA and Motif strings:");
		Scanner input = new Scanner(System.in);
		System.out.print("DNA string: ");
		String dna = input.next();
		
		System.out.print("Motif string: ");
		String motif = input.next();
		
		input.close();
		
		
		//2nd, find out the start index of each motif in the DNA string;
		findMotifs(dna, motif);
		
	}//end main();

	private static void findMotifs(String dna, String motif) {
		// TODO traversal through the dna string, check if any substring equals to motif
		
		int LenDNA = dna.length();
		int LenMotif = motif.length();
		
		for(int i=0; i<LenDNA-LenMotif; i++){
			
			String subStr = dna.substring(i, i+LenMotif);
			if(subStr.equals(motif)) System.out.print(" " +(i+1));
			
		}//end for i<LenDNA-LenMotif loop;
		
	}//end findMotifs() method;

}//end of everything in FindingaMotifinDNA class;
