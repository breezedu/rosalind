package rosalind;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**************
 * 
 * Given: A collection of at most 10 DNA strings of equal length (at most 1 kbp) in FASTA format.
 * Return: A consensus string and profile matrix for the collection. 
 * (If several possible consensus strings exist, then you may return any one of them.)
 * 
 * Sample Dataset
 * 
 * >Rosalind_1
 * ATCCAGCT
 * >Rosalind_2
 * GGGCAACT
 * >Rosalind_3
 * ATGGATCT
 * >Rosalind_4
 * AAGCAACC
 * >Rosalind_5
 * TTGGAACT
 * >Rosalind_6
 * ATGCCATT
 * >Rosalind_7
 * ATGGCACT
 * 
 * Sample Output
 * ATGCAACT
 * A: 5 1 0 0 5 5 0 0
 * C: 0 0 1 4 2 0 6 1
 * G: 1 1 6 3 0 1 0 0
 * T: 1 5 0 0 0 1 1 6
 *  
 * @author Frog
 *
 */

public class ConsensusandProfile {
	
	public static void main(String[] args) throws IOException{
		
		System.out.println("This is Consensus and Profile program.");
		
		
		//1st, read the DNA sequences from TXT document
		Scanner ScanLines = new Scanner(new File("Consensusand.txt"));
		ArrayList<String> dnaList = new ArrayList<String>();
		
		String dnaStr = "";
		while(ScanLines.hasNextLine()){
			
			//readin next line from txt document:
			String temp = ScanLines.nextLine();
			if(temp.charAt(0) == '>'){
				dnaList.add(dnaStr);
				dnaStr = "";
				
			} else {
				dnaStr += temp;				
			}
			
		}//end while() loop;
		
		//add the last dnaStr to the arrayList, and remove the first empty string from it; 
		dnaList.add(dnaStr);
		dnaList.remove(0); //because the first dnaStr "" will also be added to the ArrayList
		
		ScanLines.close();
		
		
		//2nd, printout the arraylist, see if every dna string has been stored into the AL;
		printArrayList(dnaList);
		
		
		//3rd, check the AGTC consensus of dna strings
		//build a matrix base on dna lists
		int[][] consMatrix = buildMatrix(dnaList);
		
		//check the consensus sequence base on the matrix we just got
		String consStr = getConseSequence(consMatrix);
		System.out.println(consStr);
		
		//printout the consMatrix;
		printMatrix(consMatrix);
		
		
	}//end main();

	private static String getConseSequence(int[][] matrix) {
		// TODO get the consensus sequence
		String consStr = "";
		char[] base = {'A', 'C', 'G', 'T'};
		
		int row = matrix.length;
		int col = matrix[0].length;
		
		for(int i=0; i<col; i++){
			
			int index = 0;
			for(int j=1; j<row; j++){
				
				if(matrix[j][i] > matrix[index][i]) index = j;
			}
			
			consStr += base[index];
		}//end outer for i<col loop;
		
		return consStr;
	}//end getConseSequence() method;

	private static void printMatrix(int[][] matrix) {
		// TODO printout an matrix;
		int row = matrix.length;
		int col = matrix[0].length;
		char[] base = {'A', 'C', 'G', 'T'};
		
		for(int i=0; i<row; i++){
			
			System.out.print(base[i] +":");
			for(int j=0; j<col; j++){
				
				System.out.print(" " + matrix[i][j]);
			}
			
			System.out.println();
		}//end outer for i<row loop;
		
		System.out.println();
	}//end printMatrix() method;

	private static int[][] buildMatrix(ArrayList<String> dnaList) {
		// TODO build a matrix base on the dna sequences in the arrayList
		int Len = dnaList.get(0).length();
		int[][] matrix = new int[4][Len];
		
		for(int i=0; i<Len; i++){
			
			for(int j=0; j<dnaList.size(); j++){
				
				switch( dnaList.get(j).charAt(i)){
				case 'A' : matrix[0][i]++; break;
				case 'C' : matrix[1][i]++; break;
				case 'G' : matrix[2][i]++; break;
				case 'T' : matrix[3][i]++; break;
				
				} //end switch
				
			}//end inner for j<dnaList.size() loop;
		}//end outer for i<Len loop;
		
		return matrix;
	}//end buildMatrix() method;

	private static void printArrayList(ArrayList<String> al) {
		// TODO Printout an arrayList
		
		for(String s:al){
			
			System.out.println(s);
		}
		
		System.out.println();
	}//end of printArrayList() method;
	

}//end of everything in ConsensusandProfile class;
