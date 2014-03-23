package rosalind;

import java.util.Scanner;

/***************
 * Given: A DNA string s of length at most 1000 nt.
 * Return: Four integers (separated by spaces) 
 * counting the respective number of times that the symbols 'A', 'C', 'G', and 'T' occur in s.
 * 
 * Sample Dataset
 * AGCTTTTCATTCTGACTGCAACGGGCAATATGTCTCTGTGTGGATTAAAAAAAGAGTGTCTGATAGCAGC
 * Sample Output
 * 20 12 17 21
 * 
 * @author Frog
 *
 */
public class CountingDNANucleotides {
	
	public static void main(String[] args){
		
		System.out.println("This is Count DNA nucleotides program.");
		
		//1st, input the DNA string;
		System.out.println("Please input the DNA string:");
		Scanner input = new Scanner(System.in);
		System.out.print("DNA: ");
		String dna = input.next();
		input.close();
		
		//2nd, counting A, G, T and C
		int A = 0;
		int G = 0;
		int T = 0;
		int C = 0;
		int Len = dna.length();
		for(int i=0; i<Len; i++){
			
			if(dna.charAt(i)=='A') A++;
			else if(dna.charAt(i)=='G') G++;
			else if(dna.charAt(i)=='T') T++;
			else C++;
			
		}
		
		System.out.println(" " + A +" "+C+" " +G +" " +T);
		
	}//end main()

}//end of everything in CountingDNANucleotides class
