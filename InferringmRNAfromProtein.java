package rosalind;

import java.util.Scanner;

/************
 * URL: http://rosalind.info/problems/mrna/
 * 
 * Given: A protein string of length at most 1000 aa.
 * Return: The total number of different RNA strings from which the protein could have been translated, 
 * modulo 1,000,000. (Don't neglect the importance of the stop codon in protein translation.)
 * 
 * Sample Dataset
 * 	MA
 * Sample Output
 * 	12
 * 
 * @author Frog
 *
 */
public class InferringmRNAfromProtein {
	
	public static void main(String[] args){
		
		System.out.println("This is Inferring mRNA from Protein program.");
		
		//1st, ask user to input the protein sequence
		System.out.println("Please input the protein sequence:");
		Scanner input = new Scanner(System.in);
		System.out.print("protein: ");
		
		String protein = input.next();
		input.close();
		
		
		//2nd, call RNAProteinDict.java, create Protein2RNA hashMap;
		RNAProteinDict.addDict();
		
		
		//3rd, traversal through the protein sequence, calculate possibilities of RNAs
		String P = "1";
		for(int i=0; i<protein.length(); i++){
			
			char temp = protein.charAt(i);
			
			//P *= RNAProteinDict.Protein2RNA.get(temp).size();
			P = multiplyStr(P, RNAProteinDict.Protein2RNA.get(temp).size());
			
			System.out.print(" " + RNAProteinDict.Protein2RNA.get(temp).size() );
		}
		
		//instead of just printout the last 6 digits of the possibilities, I prefer to printout the whole digit string
		System.out.println("\nThere are " + multiplyStr(P, 3) + " possibilities.");
		
	}//end main();

	private static String multiplyStr(String p, int num) {
		// TODO Multiply a string of long-long integer with a single digit
		if(num==1) return p;
		
		int reminder = 0;		//the reminder of each multiply operation;
		String retStr = "";		//the string of digit to return;
		
		for(int i=p.length()-1; i>=0; i--){
			int temp = Integer.parseInt(p.substring(i, i+1));
			int curr = temp*num + reminder;
			
			retStr = curr%10 + retStr;
			reminder = curr/10;
		}
		
		if(reminder > 0) retStr = reminder + retStr;
		return retStr;
	}//end of multiplyStr() method;

}//end of everything in InferringmRNAfromProtein class;
