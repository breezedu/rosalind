package rosalind;

import java.util.Scanner;

public class RNAtoProteinTranscription {
	
	public static void main(String[] args){
		
		System.out.println("This is RNA to Protein Transcription program.");
		
		//1st, ask user to input the original RNA string
		System.out.println("Please input the RNA string:");
		Scanner input = new Scanner(System.in);
		System.out.print(" rna: ");
		String rna = input.next();
		input.close();
		
		
		//2nd, check the RNAProteinDic.RNA2Protein hashmap; transcript rna into protein
		RNAProteinDict.addDict();
		
		String protein = "";
		for(int i=0; i<rna.length(); i+=3){
			
			String rnaSub = rna.substring(i, i+3);
		//	System.out.println(" " + rnaSub);
			protein += RNAProteinDict.RNA2Protein.get(rnaSub);
		}
		
		
		//printout the protein sequence
		System.out.println("Protein: " + protein);
		
	} //end main();

}//end of everything in class RNAtoProteinTranscription
