package bioinformaticsContest;

import java.util.Scanner;

public class TheSecondaryStructureofRNAHard {

	public static void main(String[] args){
		
		Scanner input = new Scanner(System.in);
		
		while( input.hasNextLine() ){
			
			String rna = input.nextLine(); 
			rna = rna.replaceAll("\\s","");
			rna = rna.toUpperCase();
			
			if( rna.length() < 1) break; 
			
			//get the reverse rna string
			String revStr = reverseRNA( rna ); 
			
			if(rna.length()%2 == 0){
				
				if(rna.equals(revStr)){
					System.out.println("perfect"); 
				
				} else {
					System.out.println("imperfect"); 
					
				}
				
			} else {
				
				int LCS = LongestCommonSequence(rna, revStr); 
				
				if(LCS == rna.length()-1){
					System.out.println("almost perfect");
					
				} else {
					System.out.println("imperfect"); 
					
				}
				
			}//end if-else rna.length()%2 == 0; 
			
		} //end while input.hasNextLine(); 
		
		
		input.close(); 
		
	}//end main()

	private static int LongestCommonSequence(String str1, String str2) {
		// TODO Auto-generated method stub
		int max = 0; 
		
		int len = str1.length();
		
		int[][] matrix = new int[len+1][len+1]; 
		
		for(int i=0; i<len; i++){
			matrix[0][i] = 0; 
			matrix[i][0] = 0; 
		}
		
		for( int i= 1; i<len+1; i++){
			
			for( int j = 1; j<len+1; j++){
				
				if(str1.charAt(i-1) == str2.charAt( j-1)){
					
					matrix[i][j] = matrix[i-1][j-1] + 1; 
				
				} else {
					
					matrix[i][j] = maxOftwo(matrix[i-1][j], matrix[i][j-1]); 
				}
				
				if(matrix[i][j] > max)
					max = matrix[i][j]; 
				
			}//end for j<len loop; 
			
		}//end for i<len loop; 
		
		
	//	printMatrix(matrix); 
		
		return max;
	}//end longestCommonSequence() method; 
	

	private static void printMatrix(int[][] matrix) {
		// TODO Auto-generated method stub
		
		int row = matrix.length; 
		int col = matrix[0].length; 
		
		System.out.println("Print out matrix: "); 
		for(int i=0; i<row; i++){
			
			for(int j=0; j<col; j++){
				
				System.out.print(" " + matrix[i][j]); 
			}
			
			System.out.println(); 
			
		}
		
	}

	private static int maxOftwo(int i, int j) {
		// TODO Auto-generated method stub
		
		if( i> j){
			return i;
		
		} else {
			return j;
		}
	}

	private static String reverseRNA(String rna) {
		// TODO Auto-generated method stub
		
		int len = rna.length();
		String retStr = ""; 
		
		for(int i=0; i<len; i++){
			
			switch( rna.charAt(i)){
			case 'A': retStr = 'U' + retStr; break; 
			case 'U': retStr = 'A' + retStr; break; 
			case 'G': retStr = 'C' + retStr; break; 
			case 'C': retStr = 'G' + retStr; break; 
			
			}

		}//end for; 
		
		return retStr;
	}
	
}//ee
