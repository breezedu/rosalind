
import java.util.Scanner;
/***********************
 * Solve the RNA secondary structure problem with Nussinov Algorithm
 * 
 * @author Jeff
 *
 */
public class TheSecondaryStructureofRNANussinov {

	public static void main(String[] args){
		
		Scanner input = new Scanner(System.in);
		
		while( input.hasNextLine() ){
			
			String rna = input.nextLine(); 
			rna = rna.replaceAll("\\s","");
			rna = rna.toUpperCase();
			
			if( rna.length() < 1) break; 
			
			
			int len = rna.length(); 
			//create nussinov Matrix
			int[][] Nussinov = new int[len][len];			
			
			int[][] match = createMatchMatrix(rna); 
			
			for(int i=0; i<len; i++){
				for(int j=0; j<len; j++){
					Nussinov[i][j] = match[i][j];
				}
			}						
			
			//for all length = 2
			
			for(int length = 2; length < len; length++){
				
				for(int i=0; i<len-length; i++){
					
					int k = i + length; 
						Nussinov[i][k] = nussinov(Nussinov, i, k, match);
													
				}
				
				printMatrix(Nussinov); 
			}
			
			
			int max = Nussinov[0][len-1] *2;
			
			if(len - max == 0){
				System.out.println("perfect");
				
			} else if(len - max == 1){
				
				System.out.println("almost perfect");
			
			} else {
				System.out.println("imperfect");
			}
			
		} //end while input.hasNextLine; 
		
		
		
		input.close(); 
		
	}//end main()

	
	
	private static int[][] createMatchMatrix(String rna) {
		// TODO Auto-generated method stub
		
		int len = rna.length();
		int[][] matrix = new int[len][len];
		
		for(int i=0; i<len; i++){
			
			for(int j=0; j<len; j++){
				
				String pair = "" + rna.charAt(i) + rna.charAt(j);
				
				if( "GC-CG-AU-UA".contains(pair) ){
					matrix[i][j] = 1;
				} else {
					matrix[i][j] = 0; 
				}
				
			}//end for j<len; 
		}//end for i<len; 
		
		return matrix;
	}



	private static int nussinov(int[][] matrix, int i, int j, int[][] match) {
		// TODO Auto-generated method stub
		
		if( j- i < 2) return 0; 
		
		int m1 = matrix[i+1][j]; 					//nussinov(matrix, i+1, j, match); 
		int m2 = matrix[i][j-1]; 					//nussinov(matrix, i, j-1, match);
		int m3 = matrix[i+1][j-1] + match[i][j]; 	//nussinov(matrix, i+1, j-1, match) + match[i][j]; 
		
		int m4 = 0; 
		
		for(int k=i+1; k<j; k++){
			
			int currM4 = matrix[i][k] + matrix[k+1][j]; 	// + nussinov(matrix, k+1, j, match);
			
			if(currM4 > m4)
				m4 = currM4 ;
			
		}
		
		int max = m1;
		if(m2 > max) max = m2;
		if(m3 > max) max = m3; 
		if(m4 > max) max = m4; 
		
		return max;
	}

	
	
	
	

	/*******
	 * Print out matrix; 
	 * @param matrix
	 */
	private static void printMatrix(int[][] matrix) {
		// TODO Auto-generated method stub
		
		int row = matrix.length; 
		int col = matrix[0].length; 
		
		System.out.println("Print out matrix: "); 
		for(int i=0; i<row; i++){
			
			for(int j=0; j<col; j++){
				if(matrix[i][j] > -1){
					
					System.out.print(" " + matrix[i][j]); 
					
				} else {
					System.out.print("  ");
					
				}
				
			}
			
			System.out.println(); 
			
		}
		
	}

	
}//ee
