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
	
	//1st, pass by two strings, return all common shared sub-strings
	String strA = "GATTACA";
	String strB = "TAGACCA";
	
	ArrayList<String> comsubStrs = findAllCommonSubstrings(strA, strB);
	
	
	

	private ArrayList<String> findAllCommonSubstrings(String strA, String strB) {
		// TODO use DP programming to build a match-matrix for stringA and stringB;
		
		
		
		return null;
	}
	
	

} //ee
