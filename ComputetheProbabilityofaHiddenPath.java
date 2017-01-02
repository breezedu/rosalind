package rosalind;

import java.util.Scanner;

/***********************************************
 * http://rosalind.info/problems/ba10a/
 * 
 * Probability of a Hidden Path Problem
 * 
 * Given: A hidden path π followed by the states States and transition matrix Transition of an HMM (Σ, States, Transition, Emission).
 * Return: The probability of this path, Pr(π). You may assume that initial probabilities are equal.
 * 
 * 
 * @author Jeff
 *
 */
public class ComputetheProbabilityofaHiddenPath {
	
	public static void main(String[] args){
		
		//step 1: input string/hidden path and the transition matrix:
		Scanner input = new Scanner(System.in); 
		
		System.out.print("Please input the original string:" );
		
		String data_seq = input.next();
		
		System.out.println("Now input the transition matrix:"); 
		System.out.print("AA: ");
		double AA = input.nextDouble();
		
		System.out.print("AB: ");
		double AB = input.nextDouble();
		
		System.out.print("BA: ");
		double BA = input.nextDouble(); 
		
		System.out.print("BB: ");
		double BB = input.nextDouble();
		
		input.close(); 
		
		
		
		//Step 2, calculate the probability of the hidden path:
		double prob = 0.5; 
		
		for(int index =0; index<data_seq.length()-1; index++){
			
			String tempStr = data_seq.substring(index, index+2);
			
			if(tempStr.equals("AA")){
				
				prob *= AA;
				
			} else if( tempStr.equals("AB")) {
				
				prob *= AB; 
				
			} else if( tempStr.equals("BA")) {
				
				prob *= BA; 
				
			} else if( tempStr.equals("BB")) {
				
				prob *= BB; 
			}
			
		} //end for; 
		
		
		System.out.println("The final probability is: " + prob); 
		
	}//end main(); 

}//ee
