package bioinformaticsContest;

import java.util.Scanner;

public class TheSecondaryStructureofRNAEasy {

	public static void main(String[] args){
		
		Scanner input = new Scanner(System.in);
		
		while( input.hasNextLine() ){
			
			String dna = input.nextLine(); 
			
			if( dna.length() < 1) break; 
			
			if(dna.length()%2 == 0){
				
				//go to even method; 
				int missMatch = checkEven(dna); 
				
				if( missMatch < 1) {
					System.out.println("perfect"); 
				} else {
					System.out.println("imperfect"); 
				}
				
				
			} else if (dna.length()%2 != 0){
				
				//go to odd method; 
				int minMissMatch = 10; 
				for(int i=0; i<dna.length(); i++){
					
					String newStr = dna.substring(0, i) + dna.substring(i+1);
					
					int missMatch = checkEven(newStr); 
					
					if(missMatch < 1){
						minMissMatch = 0;
						break; 
					}
				}
				
				if(minMissMatch < 1){
					System.out.println("almost perfect"); 
				} else {
					System.out.println("imperfect"); 
				}
			}
			
		}//end while()
		
		input.close(); 
		
	}//end main()

	private static int checkEven(String dna) {
		// TODO Auto-generated method stub
		
		int len = dna.length()/2;
		int missMatch = 0; 
		
		for(int i=0; i<len; i++){
			char currChar = 'O';
			
			switch( dna.charAt(i)){
			case 'A': currChar = 'U'; break; 
			case 'U': currChar = 'A'; break; 
			case 'G': currChar = 'C'; break; 
			case 'C': currChar = 'G'; break; 
			
			}
			
			if( currChar != dna.charAt(dna.length() - i -1)){
				missMatch ++; 
			}
		}//end for; 
		
		return missMatch;
	}
	
}//ee
