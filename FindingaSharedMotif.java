package rosalind;

import java.util.ArrayList;

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
	
	public static void main(String[] args){
		
		
		//1st, pass by two strings, return all common shared sub-strings
		String strA = "GATTACA";
		String strB = "TAGACCA";
		
		
		ArrayList<String> comsubStrs = findAllCommonSubstrings(strA, strB);
		
		//2nd, check if any substrings are also 'common' in the third string
		String strC = "ATACA";
		String LCStr = "";
		for(int i=0; i<comsubStrs.size(); i++){
			
			if(strC.contains(comsubStrs.get(i) ) && comsubStrs.get(i).length()>LCStr.length()){
				LCStr = comsubStrs.get(i); 
			}
		} //end for i<comsubStrs.size
		
		System.out.println("The LCS is: " + LCStr); 
	}
	
	
	
	
	
	
	

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
		
		print_matrix_with2strings(matchMatrix, strA, strB); 
		
		
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
				System.out.println(tempStr); 
				retStrs.add(tempStr); 
			}
		}
		
		return retStrs;
		
	}//end findAllCommonSubstrings() method;




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
