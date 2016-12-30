package rosalind;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**********************************
 *  * 
 * @author Jeff
 * 
 * Problem
 * A common substring of a collection of strings is a substring of every member of the collection. 
 * We say that a common substring is a longest common substring if there does not exist a longer common substring. 
 * For example, "CG" is a common substring of "ACGTACGT" and "AACCGTATA", but it is not as long as possible; 
 * in this case, "CGTA" is a longest common substring of "ACGTACGT" and "AACCGTATA".
 * 
 * Note that the longest common substring is not necessarily unique; for a simple example, 
 * "AA" and "CC" are both longest common substrings of "AACC" and "CCAA".
 * 
 * Given: A collection of kk (k≤100k≤100) DNA strings of length at most 1 kbp each in FASTA format.
 * 
 * Return: A longest common substring of the collection. (If multiple solutions exist, you may return any single solution.)
 * 
 * 
 */
public class FindingaSharedMotif {
	
	/******************
	 * main() 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException{
		
		//0, read in all DNA strings:
		System.out.println("Step 0: read in all DNA sequences."); 
		
		String routine = "D:/GitHubRepositories/rosalind/TXT/rosalind_lcsm (1).txt"; 
		
		ArrayList<String> dnaStrs = get_all_DNA_string( routine ); 
		
		
		
		//1st, pass by two strings, return all common shared sub-strings
		System.out.println("Step 1: get all common shared sub-strings between the first two DNA sequences."); 
		
		String strA = dnaStrs.get(0); 
		String strB = dnaStrs.get(1); 
		
		//call findAllCommonSubstrings() method to get all substrings in common
		ArrayList<String> comsubStrs = findAllCommonSubstrings(strA, strB);
		
		
		//2nd, check if any substrings are also 'common' in the other strings
		// if any sub-strings in the common-arrayList is not contained in any other DNA strings, remove it;
		System.out.println("Step 2: check if any substrings in the arraylist are also 'common' among other DNA strings.");
		
		for(int i=2; i<dnaStrs.size(); i++){
			
			String strC = dnaStrs.get(i); 
		
			for(int j= comsubStrs.size()-1; j>=0; j--){
								
				if( !strC.contains(comsubStrs.get(j) ) ){
					
					comsubStrs.remove(j); 
				}
				
			} //end for i<comsubStrs.size
		}

		
		
		//3rd, get the longest sub-string in the common-substrings arrayList;
		System.out.println("Step 3: find out the longest String in common."); 
		String LCStr = ""; 
		for(int i=0; i<comsubStrs.size(); i++){
			
			if(comsubStrs.get(i).length() > LCStr.length()){
				
				LCStr = comsubStrs.get(i); 
			}
		}
		
		System.out.println("The LCS is: " + LCStr); 
	}
	
	
	
	
	/********************************
	 * get all DNA strings from a local txt document
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
			
			} else {
				
				seq += temp; 
			}
			
		}//end while scanner has next line loop; 
		
		//add the last seq
		dnaStrs.add(seq); 
		
		scanSeqs.close(); 
		System.out.println("\t there are " + dnaStrs.size() + " DNA strings."); 
		
		return dnaStrs;
		
	} //end get-all-DNA-string() method; 







	/****************************
	 * 
	 * @param strA
	 * @param strB
	 * @return
	 */
	private static ArrayList<String> findAllCommonSubstrings(String strA, String strB) {
		// TODO use DP programming to build a match-matrix for stringA and stringB;
		//initial an array-list to store all sub-strings in common
		ArrayList<String> retStrs = new ArrayList<String>();
		
		//initial row and cols of a matrix; 
		int row = strA.length() +1; 
		int col = strB.length() +1;
		
		int[][] matchMatrix = new int[row][col]; 
		
		//first row and first col are '0'
		for(int i=0; i<col; i++){
			matchMatrix[0][i] = 0;
		
		}
		
		for(int i=0; i<row; i++){
			matchMatrix[i][0] = 0; 
			
		}
		
	//	print_matrix_with2strings(matchMatrix, strA, strB); 
		//check all other elements in the matrix: 
		//if strA.charAt(i) == strB.charAt(j), then matchMatrix = 1 + matchMatrix[i-1][j-1]
		for(int i=1; i<row; i++){
			
			for(int j=1; j<col; j++){
				
				if(strA.charAt(i-1) == strB.charAt(j-1)){
					
					matchMatrix[i][j] = 1 + matchMatrix[i-1][j-1]; 
				}
				
			} //end for j<col loop;
		} //end for i<row loop; 
		
		// print_matrix_with2strings(matchMatrix, strA, strB); 
		
		
		//after we built the match-matrix, get all substrings in common with length > than 1;
		for(int i=1; i<row; i++){
			
			int max = 1;
			for(int j=1; j<col; j++){
				
				if(matchMatrix[i][j] > max){
					
					max = matchMatrix[i][j]; 
				}
				
			}
			
			String tempStr = strA.substring(i - max, i);
			
			
			if(tempStr.length() > 1 && !retStrs.contains(tempStr)){
				
			//	System.out.println(tempStr); 
				retStrs.add(tempStr); 
			}
		}
		
		System.out.println("\t there are " + retStrs.size() + " substrings in common between the first two strings.");
		
		return retStrs;
		
	}//end findAllCommonSubstrings() method;



	/***********
	 * Print out the match-matrix, to make sure each step is correct.
	 * @param matchMatrix
	 * @param strA
	 * @param strB
	 */
	private static void print_matrix_with2strings(int[][] matchMatrix, String strA, String strB) {
		// TODO Auto-generated method stub
		int row = strA.length() +1; 
		int col = strB.length() +1; 
		
		//print col titles
		System.out.print(" " + "\t" + " " + "\t");
		for(int i=0; i<strB.length(); i++){
			System.out.print(strB.charAt(i) + "\t"); 
		}
		
		System.out.println(); 
		
		
		//print the first line of matrix
		System.out.print(" " + "\t"); 
		for(int i=0; i<col; i++){
			
			System.out.print(matchMatrix[0][i] + "\t");
		}
		System.out.println();
		
		//print row tile and all other rows
		for(int i=1; i<row; i++){
			
			System.out.print(strA.charAt(i-1) + "\t"); 
			
			for(int j=0; j<col; j++){
				
				System.out.print(matchMatrix[i][j] + "\t");
				
			}
			
			System.out.println(); 
			
		}//end for i<strA
	}
	
	

} //ee
