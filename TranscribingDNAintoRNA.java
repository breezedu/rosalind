package rosalind;

import java.util.Scanner;

/*************
 * http://rosalind.info/problems/rna/
 * 
 * Given: A DNA string t having length at most 1000 nt.
 * Return: The transcribed RNA string of t.
 * Sample Dataset
 * GATGGAACTTGACTACGTAAATT
 * Sample Output
 * GAUGGAACUUGACUACGUAAAUU
 * 
 * @author Frog
 *
 */
public class TranscribingDNAintoRNA {

	public static void main(String[] args){
		
		System.out.println("This is Transcribing DNA into RNA program.");
		
		//1st, ask user to input the original DNA string 
		System.out.println("Please input the DNA string:");
		Scanner input = new Scanner(System.in);
		System.out.print("DNA: ");
		String dna = input.next();
		input.close();
		
		
		//2nd, transfer dna into rna
		String rna = "";
		int Len = dna.length();
		for(int i=0; i<Len; i++){
			if(dna.charAt(i)=='T') rna += 'U';
			else rna += dna.charAt(i);
			
		}
		
		//printout the rna string;
		System.out.println("RNA: " + rna);
	}//end main()
	
}//end of everything in TranscribingDNAintoRNA class
