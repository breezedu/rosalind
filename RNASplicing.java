package rosalind;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/***************************
 * 
 * Problem 
 * After identifying the exons and introns of an RNA string, 
 * we only need to delete the introns and concatenate the exons to form a new string ready for translation.
 * 
 * Given: A DNA string ss (of length at most 1 kbp) and a collection of substrings of ss acting as introns. 
 * All strings are given in FASTA format.
 * 
 * Return: A protein string resulting from transcribing and translating the exons of ss. 
 * (Note: Only one solution will exist for the dataset provided.)
 * 
 * 
 * Sample Dataset
 * *
 * >Rosalind_10
 * ATGGTCTACATAGCTGACAAACAGCACGTAGCAATCGGTCGAATCTCGAGAGGCATATGGTCACATGATCGGTCGAGCGTGTTTCAAAGTTTGCGCCTAG
 * >Rosalind_12
 * ATCGGTCGAA
 * >Rosalind_15
 * ATCGGTCGAGCGTGT
 * 
 * Sample Output
 * * 
 * MVYIADKQHVASREAYGHMFKVCA
 * 
 * @author Jeff
 *
 */
public class RNASplicing {
	
	public static void main(String[] args) throws FileNotFoundException{
		
		//1st, readin DNA and intron sequences
		System.out.println("Step 1: read in all DNA sequences."); 
		
		String routine = "D:/GitHubRepositories/rosalind/TXT/rosalind_rnasplicing.txt"; 
		
		ArrayList<String> dnaStrs = get_all_DNA_string( routine ); 
		
		for(int i=0; i<dnaStrs.size(); i++){
			System.out.println(dnaStrs.get(i)); 
		}
		
		
		//2nd, remove introns
		String DNA = dnaStrs.get(0); 
		
		for(int i=1; i<dnaStrs.size(); i++){
			
			DNA = remove_Introns(DNA, dnaStrs.get(i)); 
		
		}
		
		System.out.println("DNA: " + DNA); 
		
		//3rd, translate DNA sequence to protein sequence
		
		///3.1, get a HashMap with codon as the key and amino seq as the value, 
		HashMap<String, String> rna2protein = get_RNA2Protein_table(); 
		
		
		//3.2, transfer DNA sequence to RNA sequence
		String RNA = from_DNA_to_RNA(DNA); 
		
		///3.3 traverse through the RNA sequence by codon frame, transfer each codon to a amino according to the HashTable;
		
		//get the first start codon:
		int start_index = RNA.indexOf("AUG"); 
		System.out.println("The start codon starts at: " + start_index);
		
		String protein = ""; 
		
		for(int i=start_index; i<RNA.length()-3; i+=3){
			
			String codon = RNA.substring(i, i+3); 
			
			protein += rna2protein.get(codon); 
			
		}
		
		//4th, printout protein sequence 
		System.out.println(protein);
		
	} //end of main(); 
	

	/*******************
	 * transfer DNA sequence to RNA sequence
	 * A - A
	 * G - G
	 * C - C
	 * T - U
	 * 
	 * @param dna
	 * @return
	 */
	private static String from_DNA_to_RNA(String dna) {
		// TODO Auto-generated method stub
		
		String RNA = "";
		for(int i=0; i<dna.length(); i++){
			
			if(dna.charAt(i) == 'T'){
				RNA += 'U';
				
			} else {
				
				RNA += dna.charAt(i); 
			}
		}
		
		return RNA;
	} //end from_DNA_to_RNA() method; 


	/**************************
	 * base on RNA codon to amino table,
	 * get a hashmap
	 * key: codon
	 * value: amino first capital letter
	 * 
	 * @return
	 */
	private static HashMap<String, String> get_RNA2Protein_table() {
		// TODO Auto-generated method stub
		String codonandamino = 
				"UUU F CUU L AUU I GUU V UUC F CUC L AUC I GUC V UUA L "
				+ "CUA L AUA I GUA V UUG L CUG L AUG M "
				+ "GUG V UCU S CCU P ACU T GCU A UCC S CCC P "
				+ "ACC T GCC A UCA S CCA P ACA T GCA A UCG S CCG P ACG T "
				+ "GCG A UAU Y CAU H AAU N GAU D UAC Y CAC H AAC N GAC D "
				+ "UAA Stop CAA Q AAA K GAA E UAG Stop CAG Q "
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
		
	} //end get_DNA2Protein_table() method; 



	/*******
	 * given a dna string, and an intron string
	 * remove all intron sub-sequences from the dna sequence;
	 * 
	 * @param dna
	 * @param intron
	 * @return
	 */
	private static String remove_Introns(String dna, String intron) {
		// TODO Auto-generated method stub
		int index = 0; 
		
		while(index < dna.length()-intron.length()){
			
			if(dna.substring(index, index + intron.length()).equals(intron)){
				
				dna = dna.substring(0, index) + dna.substring(index + intron.length()); 
				
			}
			
			index ++; 
			
		} //end while loop; 
		
		
		return dna;
	} //end remove_Introns() method;
	

	/**********************************
	 * given a document with one dna string and several introns string, each title line starts with '<'
	 * read in each sequence, put them into an arraylist, 
	 * return the arraylist
	 * 
	 * @param routine
	 * @return
	 * @throws FileNotFoundException
	 */
	private static ArrayList<String> get_all_DNA_string(String routine) throws FileNotFoundException {
		// TODO Auto-generated method stub
		ArrayList<String> dnaStrs = new ArrayList<String>(); 
		
		Scanner scanSeqs = new Scanner(new File( routine )); 
		
		String seq = ""; 
		while( scanSeqs.hasNextLine() ){
			
			String temp = scanSeqs.nextLine();
			
			if(temp.charAt(0) == '>' && seq.length() > 1){
				
				dnaStrs.add(seq); 
				seq = ""; 
			
			} else if(temp.charAt(0) != '>') {
				
				seq += temp; 
			}
			
		}//end while scanner has next line loop; 
		
		//add the last seq to the arrayList
		dnaStrs.add(seq); 
		
		scanSeqs.close(); 
		System.out.println("\t there are " + dnaStrs.size() + " DNA strings."); 
		
		return dnaStrs;
		
	} //end get_all_DNA_string() method; 
	

} //ee
