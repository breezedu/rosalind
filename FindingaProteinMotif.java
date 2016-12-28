package rosalind;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

/*************************************
 * Problem
 * 
 * To allow for the presence of its varying forms, 
 * a protein motif is represented by a shorthand as follows: [XY] means "either X or Y" and {X} means "any amino acid except X." 
 * For example, the N-glycosylation motif is written as N{P}[ST]{P}.
 * 
 * You can see the complete description and features of a particular protein by its access ID "uniprot_id" in the UniProt database, 
 * by inserting the ID number into
 * http://www.uniprot.org/uniprot/uniprot_id
 * Alternatively, you can obtain a protein sequence in FASTA format by following
 * 
 * http://www.uniprot.org/uniprot/uniprot_id.fasta
 * 
 * For example, the data for protein B5ZC00 can be found at http://www.uniprot.org/uniprot/B5ZC00.
 * 
 * Given: At most 15 UniProt Protein Database access IDs.
 * Return: For each protein possessing the N-glycosylation motif, 
 * output its given access ID followed by a list of locations in the protein string where the motif can be found.
 * 
 * @author Jeff
 *
 */
public class FindingaProteinMotif {
	
	/*************
	 * main() 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException{
		
				
		//step 1, input all protein IDs, by hand.
		String[] protein_id = {"Q32LI2", "P72173", "P04141_CSF2_HUMAN", "P09136", "Q9D9T0", "P01047_KNL2_BOVIN", "P21809_PGS1_BOVIN", "A7Y3H6", 
				"P11279_LMP1_HUMAN", "P24592_IBP6_HUMAN", "P81447_MPP3_CAPHI", "Q28409", "Q60960"
				}; 
		
		
		for(int i=0; i<protein_id.length; i++){
			
			// step 2, , pass a Protein ID to method get_protein_seq(), get the sequence of the protein
			String protein_seq = get_Protein_Seq(protein_id[i]); 
			
			System.out.println( protein_id[i] );
			
			// step 3, for each protein sequence, get all 4-chars sub-strings
			for(int index = 0; index <protein_seq.length()-4; index++){
				
				String subStr = protein_seq.substring(index, index+4); 
				
				//step 4, check if any sub-string meets the N-glycosylation motif definition: N{P}[ST]{P}: 
				if(subStr.charAt(0) == 'N' && subStr.charAt(1) != 'P' && subStr.charAt(2) == 'S' && subStr.charAt(3)!= 'P'){
					
					System.out.print( index+1 + "\t");
					
				}
				
				if(subStr.charAt(0) == 'N' && subStr.charAt(1) != 'P' && subStr.charAt(2) == 'T' && subStr.charAt(3)!= 'P'){
					
					System.out.print( index+1 + "\t");
					
				}
				
			} //end inner for index<protein_seq.length()
			
			System.out.println(); 
			
		} //end for i < protein_id.length; loop; 
				
		
	} //ee
	

	/*****************************
	 * get protein sequence based on the protein ID
	 * pass the id to an url type, then pull down the sequence from internet
	 * 
	 * @param proteinID
	 * @return
	 * @throws IOException
	 */
	private static String get_Protein_Seq(String proteinID) throws IOException {
		// TODO Auto-generated method stub
		
		String routine = "http://www.uniprot.org/uniprot/" + proteinID + ".fasta"; 
		
		URL url = new URL(routine); 
		
		Scanner scanSeqs = new Scanner( url.openStream() ); 
		
		String proteinStr = "";
		
		//ignore the first line, title line: 
		scanSeqs.nextLine(); 
		while( scanSeqs.hasNextLine()){
			
			String temp = scanSeqs.nextLine(); 
			
			proteinStr += temp; 
			
		//	System.out.println( temp ); 
			
		}
		
		scanSeqs.close();
		
		return proteinStr;
	}//end get_Protein_Seq() method; 

} //ee
