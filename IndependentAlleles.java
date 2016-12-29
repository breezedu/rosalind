package rosalind;

import java.util.Scanner;

/**********************************
 * Two events AA and BB are independent if Pr(A and B)Pr(A and B) is equal to Pr(A)×Pr(B)Pr(A)×Pr(B). 
 * In other words, the events do not influence each other, 
 * so that we may simply calculate each of the individual probabilities separately and then multiply.
 * 
 * More generally, 
 * random variables XX and YY are independent if whenever AA and BB are respective events for XX and YY, 
 * AA and BB are independent (i.e., Pr(A and B)=Pr(A)×Pr(B)Pr(A and B)=Pr(A)×Pr(B)).
 * 
 * 
 * Given: Two positive integers k (k ≤ 7) and N (N ≤ 2^k). 
 * In this problem, we begin with Tom, who in the 0th generation has genotype Aa Bb. 
 * Tom has two children in the 1st generation, each of whom has two children, and so on. 
 * Each organism always mates with an organism having genotype Aa Bb.
 * 
 * Return: The probability that at least NN Aa Bb organisms will belong to the kk-th generation of Tom's family tree 
 * (don't count the Aa Bb mates at each level). Assume that Mendel's second law holds for the factors.
 * 
 * 
 * @author Jeff
 * 
 * Very SAD that the code would give correct answers, however, the rosalind website would not accept them ==!
 * 
 * Take k = 5, n=9, as one example:
 * My python would give:      0.406488348497
 * While my Java printed out: 0.4064883484973305
 * 
 * Believe it or not, the java result would be rejected. # ==!
 *  *
 */

public class IndependentAlleles {
	
	public static void main(String[] args){
		
		/***************************
		 * Begin with AaBb, mate with AaBb; 
		 * Following Mendel's 2nd law, 
		 * 	Ab and Bb are independent, so 1/4 of offsprings will have AA, 1/4 will have aa, 1/2 will have Aa; 
		 * 	The same pattern will happen to Genotype B, 1/4 offsprings will have BB, 1/4 will have bb, 1/2 will have Bb; 
		 * Since A and B are indenpendent, the P(Aa, Bb) = P(Aa) * P(Bb) = 1/2 * 1/2 = 1/4;
		 * 
		 * It is interesting that since the 2nd generation, the proportion of AaBb in the offsprings would always be 1/4.
		 * 
		 * For any body in the family tree, the probability to have AaBb is 0.25, this is a binomial distribution. 
		 * * * */	
		
		
		//1st, input k and n
		Scanner input = new Scanner(System.in);
		
		System.out.print("Please input k (k <= 7) = ");
		int k = input.nextInt(); 
		
		System.out.print("Please input n (n < 2^k) = ");
		int n = input.nextInt(); 
		
		input.close(); 
		
		
		//2nd initial sum=0 and sample size = 2^k;
		double sum = 0; 
		double size = Math.pow(2, k);
		
		
		// sum over all possibilities when genotypes less than n, then 1 - sum would give the probability AT LEAST n genotypes. 
		for(int i=0; i<n; i++){			
			
			//call combin() method to get the parameter-of-combination(size choose i)
			sum += combin(size, i) * Math.pow(0.25, i) * Math.pow(0.75, size-i); 
			
		}
		
		
		//3rd, print out the results. 
		System.out.println("result: " + (1-sum ) );
		
	}// end main(); 

	
	/***************
	 * pass by two values size and n, return the combination of size choose n:
	 * 
	 * @param size
	 * @param n
	 * @return
	 */
	private static double combin(double size, int n) {
		// TODO Auto-generated method stub
		
		double comb = 1;
		
		for(int i=1; i<=n; i++){
			
			comb *= (double)(size - i + 1)/(n - i + 1);
		}
		System.out.println("size: " + size + " n: " + n + " comb: " + comb); 
		return comb;
	}//end of combin() method; 
	
}//ee
