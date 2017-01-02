package rosalind;

import java.util.HashMap;
import java.util.Scanner;

/**********************************
 * Problem 
 * 
 * In a weighted alphabet, every symbol is assigned a positive real number called a weight. 
 * A string formed from a weighted alphabet is called a weighted string, and its weight is equal to the sum of the weights of its symbols.
 * 
 * The standard weight assigned to each member of the 20-symbol amino acid alphabet is the monoisotopic mass of the corresponding amino acid.
 * 
 * Given: A protein string PP of length at most 1000 aa.
 * 
 * Return: The total weight of PP. Consult the monoisotopic mass table.
 * 
 * @author Jeff
 *
 */
public class CalcultingProteinMass {
	
	public static void main(String[] args){
		
		//1st, input a string of Protein Sequence
		Scanner input = new Scanner(System.in); 
		
		System.out.println("PLease input the protein sequence:"); 
		String protein = input.nextLine(); 
		
		input.close(); 
		
		
		//2nd, get the Protein Mass HashTable, key = Capital letter of an amino acid, Value = weight mass
		HashMap<String, String> protein_Mass = get_ProteinMass_table(); 
		
		
		//3rd, split the Protein String into Amino acids
		double total_weight = 0; 
		
		for(int i=0; i<protein.length(); i++){
			
			String currAmino = protein.charAt(i) + ""; 
			
			Double currMassWeight = Double.parseDouble( protein_Mass.get(currAmino) );
			
			total_weight += currMassWeight; 
		}
		
		
		//4th, print out total weight
		System.out.println("Total weight: " + total_weight); 
		
		
	}//end of main()

	
	
	private static HashMap<String, String> get_ProteinMass_table() {
		// TODO Auto-generated method stub
		String MassStr = 
				  "A 71.03711 C 103.00919 D 115.02694 E 129.04259 F 147.06841 G 57.02146 "
				+ "H 137.05891 I 113.08406 K 128.09496 L 113.08406 M 131.04049 N 114.04293 "
				+ "P 97.05276 Q 128.05858 R 156.10111 S 87.03203 T 101.04768 V 99.06841 "
				+ "W 186.07931 Y 163.06333";
		
		//split the string into string array; 
		String[] AminoMass = MassStr.split(" "); 
		
		
		System.out.println("There are " + AminoMass.length + " aminos"); 
		for(int i=0; i<10; i++){
			System.out.print(AminoMass[i] + "\t"); 
		} 
		System.out.println(); 
		
		
		//create a hashmap; 
		HashMap<String, String> AminoMass_table = new HashMap<String, String>(); 
		
		for(int i=0; i<AminoMass.length; i+=2){
			
			AminoMass_table.put(AminoMass[i], AminoMass[i+1]);
			
		} //enf foe i<codon2amino.length loop; 
		
		//return the hashmap; 
		return AminoMass_table;
	}

}//ee
