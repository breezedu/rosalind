package rosalind;

import java.util.ArrayList;
import java.util.HashMap;

/*************
 * Build two hash tables, one represents RNA to protein codes;
 * the other represents Protein to RNA codes;
 * 
 * @author Frog
 *
 */
public class RNAProteinDict {
	
	//public static void main(String[] args){
		
		public static HashMap<String, Character> RNA2Protein = new HashMap<String, Character>();
		public static HashMap<Character, ArrayList<String>> Protein2RNA = new HashMap<Character, ArrayList<String>>();
		
		public static void addDict(){
		String[] dict = {
				"UUU F      CUU L      AUU I      GUU V",
				"UUC F      CUC L      AUC I      GUC V",
				"UUA L      CUA L      AUA I      GUA V",
				"UUG L      CUG L      AUG M      GUG V",
				"UCU S      CCU P      ACU T      GCU A",
				"UCC S      CCC P      ACC T      GCC A",
				"UCA S      CCA P      ACA T      GCA A",
				"UCG S      CCG P      ACG T      GCG A",
				"UAU Y      CAU H      AAU N      GAU D",
				"UAC Y      CAC H      AAC N      GAC D",
				"UAA        CAA Q      AAA K      GAA E",
				"UAG        CAG Q      AAG K      GAG E",
				"UGU C      CGU R      AGU S      GGU G",
				"UGC C      CGC R      AGC S      GGC G",
				"UGA        CGA R      AGA R      GGA G",
				"UGG W      CGG R      AGG R      GGG G"
		};
		
		
		for(int i=0; i<dict.length; i++){
			
			for(int j=0; j<34; j+=11){
				
				//get the RNA section and the protein name
				String rnaSection = dict[i].substring(j, j+3);
				char protein = dict[i].charAt(j+4);
				
				System.out.print(rnaSection + " " + protein +",  ");
				
				//put the RNA and Protein 'pair' into RNA2Protein hash map
				RNA2Protein.put(rnaSection, protein);
				
				if(!Protein2RNA.containsKey(protein)){
					ArrayList<String> rnaStrs = new ArrayList<String>();
					rnaStrs.add(rnaSection);
					
					Protein2RNA.put(protein, rnaStrs);
					
				} else {
					
					Protein2RNA.get(protein).add(rnaSection);
				}//end if-else conditions;
				
			}//end inner for j<34 loop;
			
			System.out.println();
		}//end outer for i<16 loop;
		
	//}//end of main();
}
}//end of everthing in RNAProteinDict() class;
