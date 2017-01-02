package rosalind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/*****************************
 * Problem
 * 
 * Either strand of a DNA double helix can serve as the coding strand for RNA transcription. 
 * Hence, a given DNA string implies six total reading frames, 
 * or ways in which the same region of DNA can be translated into amino acids: 
 * three reading frames result from reading the string itself, whereas three more result from reading its reverse complement.
 * 
 * An open reading frame (ORF) is one which starts from the start codon and ends by stop codon, 
 * without any other stop codons in between. 
 * Thus, a candidate protein string is derived by translating an open reading frame into amino acids until a stop codon is reached.
 * 
 * Given: A DNA string ss of length at most 1 kbp in FASTA format.
 * Return: Every distinct candidate protein string that can be translated from ORFs of ss. Strings can be returned in any order.
 * 
 * @author Jeff
 *
 */

public class OpenReadingFrames {
	
	public static void main(String[] args){
		
		//1st, input a sting of DNA sequence
		Scanner input = new Scanner(System.in); 
		
		System.out.println("Please input the original DNA sequence:"); 
		
		String dna = input.next(); 
		input.close(); 
		
		//2nd, transfer from DNA to RNA, two RNA strands. 
		String rna1 = ""; 
		String rna2 = ""; 
		
		for(int i=0; i<dna.length(); i++){
			
			char temp = 'N'; 
			char temp2 = 'M';
			
			switch( dna.charAt(i) ){
			
			case 'A': temp = 'U'; temp2 = 'A'; break; 
			case 'T': temp = 'A'; temp2 = 'U'; break; 
			case 'G': temp = 'C'; temp2 = 'G'; break; 
			case 'C': temp = 'G'; temp2 = 'C'; break; 
									
			}
			
			rna1 += temp2; 
			rna2 = temp + rna2; 
			
		} //end for i<dna.length() loop; 
		
		System.out.println("Strand #1: " + rna1); 
		System.out.println("Strand #2: " + rna2); 
		
		
		//3rd, get a HashMap of RNA to Protein table;
		HashMap<String, String> rna2Protein_table = get_RNA2ProteinTable(); 
		
		
		
		
		//4th, get start codon and first stop codon after that start codon, return a sub-string
		ArrayList<String> ProteinCandidates = new ArrayList<String>(); 
		
		//get sub-protein coding sub-sequences from rna strand #1
		ProteinCandidates = get_PotentialProteinCandidates(ProteinCandidates, rna1, rna2Protein_table);

		
		
		//get sub-protein coding sub-sequences from rna strand #2
		ProteinCandidates = get_PotentialProteinCandidates(ProteinCandidates, rna2, rna2Protein_table);		
		
		
		
		//5th, printout all proteins in the ArrayList<>
		System.out.println("Protein from both strands:"); 
		for(int i=0; i<ProteinCandidates.size(); i++){
			
			System.out.println(ProteinCandidates.get(i));
		}
		
	}//end main()
	
	
	
	/**************************
	 * return a hashmap of RNA codon to protein amino acid
	 * @return
	 */
	private static HashMap<String, String> get_RNA2ProteinTable() {
		// TODO Auto-generated method stub
		String codonandamino = 
				  "UUU F CUU L AUU I GUU V UUC F CUC L AUC I GUC V UUA L "
				+ "CUA L AUA I GUA V UUG L CUG L AUG M GUG V UCU S CCU P "
				+ "ACU T GCU A UCC S CCC P ACC T GCC A UCA S CCA P ACA T "
				+ "GCA A UCG S CCG P ACG T GCG A UAU Y CAU H AAU N GAU D "
				+ "UAC Y CAC H AAC N GAC D UAA Stop CAA Q AAA K GAA E UAG Stop CAG Q "
				+ "AAG K GAG E UGU C CGU R AGU S GGU G UGC C CGC R AGC S "
				+ "GGC G UGA Stop CGA R AGA R GGA G UGG W CGG R AGG R GGG G";
		
		//split the string into string array; 
		String[] codon2amino = codonandamino.split(" "); 
		
		
		System.out.println("There are " + codon2amino.length + " codon and aminos"); 
		for(int i=0; i<10; i++){
			System.out.print(codon2amino[i] + "\t"); 
		} 
		System.out.println(); 
		
		
		//create a hashmap; 
		HashMap<String, String> codon2amino_table = new HashMap<String, String>(); 
		
		for(int i=0; i<codon2amino.length; i+=2){
			
			codon2amino_table.put(codon2amino[i], codon2amino[i+1]);
			
		} //enf foe i<codon2amino.length loop; 
		
		//return the hashmap; 
		return codon2amino_table;
		
	}//end get_RNA2ProteinTable() method; 


	private static ArrayList<String> get_PotentialProteinCandidates(ArrayList<String> proteinCandidates, String rna, HashMap<String, String> rna2Protein_table) {
		// TODO Auto-generated method stub
		//The start codon could be AUG, while the stop codons could be UAA, UAG, or UGA
		
		//1st, traverse through the rna sequence, check every 2-letter codon, see if it could be a start codon;
		for(int i=0; i<rna.length()-3; i++){
			
			if( rna.substring(i, i+3).equals("AUG") ){
				
				//pass the substring after index i to a method of finding the first stop codon
				proteinCandidates = findingFirstStopCodon(proteinCandidates, rna.substring(i), rna2Protein_table );
				
			}
			
		} //end for i<rna.length()-3 loop; 
		
		return proteinCandidates;
	}


	
	/**********
	 * find the first stop codon in the string, BUT, have to follow the 3-codon frame;
	 * @param proteinCandidates 
	 * @param substring
	 * @param rna2Protein_table
	 * @return
	 */
	private static ArrayList<String> findingFirstStopCodon(ArrayList<String> proteinCandidates, String rna,
			HashMap<String, String> rna2Protein_table) {
		// TODO Auto-generated method stub
		
		String ribRNA = ""; 
		String currProtein = ""; 
		
		for(int i=3; i<rna.length()-3; i+=3){
			
			String currCodon = rna.substring(i, i+3); 
			if( rna2Protein_table.get(currCodon).equals("Stop")){
								
				ribRNA = rna.substring(0, i+3); 
				i = rna.length(); 
			}
		}
		
		if(ribRNA.length() > 0){
			
			
			for(int i=0; i<ribRNA.length()-3; i+=3){
			
				currProtein += rna2Protein_table.get( ribRNA.substring(i, i+3) ); 
			
			}
		
			// put the new protein into the arrayList only there's no duplicate sequence
			if( !proteinCandidates.contains(currProtein) )
						proteinCandidates.add(currProtein); 
		}


		
		return proteinCandidates;
	}//end findingFirstStopCodon() method; 

}//ee
