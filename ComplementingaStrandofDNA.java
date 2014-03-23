package rosalind;

import java.util.Scanner;

/***************
 * http://rosalind.info/problems/revc/
 * 
 * Given: A DNA string s of length at most 1000 bp.
 * Return: The reverse complement sc of s.
 * Sample Dataset
 * AAAACCCGGT
 * Sample Output
 * ACCGGGTTTT
 * 
 * @author Frog
 *
 */
public class ComplementingaStrandofDNA {

	public static void main(String[] args){
		
		System.out.println("This is Complementing a Strand of DNA program.");
		
		//1st, ask user to input the original DNA strand;
		System.out.println("Please input the original DNA strand:");
		Scanner input = new Scanner(System.in);
		System.out.print("original DNA: ");
		String oriDNA = input.next();
		input.close();
		
		
		//2nd, reverse the complement of original dna strand;
		String revs = "";
		int Len = oriDNA.length();
		for(int i=0; i<Len; i++){
			
			if(oriDNA.charAt(i)=='A') revs = 'T' + revs;
			else if(oriDNA.charAt(i)=='T') revs = 'A' + revs;
			else if(oriDNA.charAt(i)=='G') revs = 'C' + revs;
			else revs = 'G' + revs;	
		}
		
		//printout the reversed DNA strand;
		System.out.println("Complement Strand: " + revs);
		
	}//end main()
}//end of everything in ComplementingaStrandofDNA
