package bioinformaticsContest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;


/***************
 * This code works fine for easy part on Bioinformatics Contest 2017
 * @author Jeff
 *
 */
public class ChemicalReactionsHard0124 {
	
	public static void main(String[] args){
		
		Scanner input = new Scanner(System.in) ;
		
		HashSet<String> inputHash = new HashSet<String>(); 
		
		//1st. get the first line:
		String firstLine = input.nextLine(); 
		String[] inputChem = firstLine.split(" "); 
		
		for(int i=0; i<inputChem.length; i++){
			
			inputHash.add(inputChem[i]); 
						
		} //end for i<inputChem.length; 
		
		
		//creat an arraylist to store all unused reactions;
		ArrayList<Reaction3> AllReactions = new ArrayList<Reaction3>(); 		
		
		//2nd. get all related reactions; 		
		while( input.hasNextLine() ){
			
			String inputLine = input.nextLine(); 		
			
			if(inputLine.length() < 1) break; 			
				
			inputLine = inputLine.replaceAll("\\+", "\t"); 
				
			//get the index of "->";
			int pivot = inputLine.indexOf("->"); 
			
			//get substrances string[] and products String[]; 
			String[] substrances = inputLine.substring(0, pivot).split("\t");
			String[] products = inputLine.substring(pivot+2).split("\t"); 			
			
			//create an ArrayList of strings to store reacts not contained in the inputHash set; 
			ArrayList<String> leftReacts = new ArrayList<String>(); 
			
			for(int i=0; i<substrances.length; i++){
				
				if(!inputHash.contains(substrances[i])){
					
					leftReacts.add(substrances[i]);
				}
			
			}//end for i>0 loop; 
			
			
			//check how many reacts not contained in the inputHash;
			if(leftReacts.size() > 0){								
				
				Reaction3 currReact = new Reaction3(leftReacts, products);				
				
				AllReactions.add(currReact);
			
			} else {
								
				for(int i=0; i<products.length; i++){
						
					inputHash.add(products[i]);						
				}
				
				AllReactions = reshapeReactions(AllReactions, inputHash);
				
			}// end if-else leftReacts.size() > 0 
			
			
							
		}//end while; 
		
		//close input
		input.close(); 		
		
		
		/******************************************************************************************************************************/
		for(String s:inputHash){
			System.out.print(s + " ");
		}

		
	}//end main(); 

	
	/*************
	 * a recursion method to reshape Reactions arrayList
	 * @param allReactions
	 * @param inputHash
	 * @return
	 */
	private static ArrayList<Reaction3> reshapeReactions(ArrayList<Reaction3> allReactions, HashSet<String> inputHash) {
		// TODO Auto-generated method stub
		
		if(allReactions.size() == 0){
			return allReactions;
		}
		
		
		ArrayList<Reaction3> unhappenReactions = new ArrayList<Reaction3>();
		
		for(int i=0; i<allReactions.size(); i++){
			
			Reaction3 oneReaction = allReactions.get(i);
			
			ArrayList<String> reactsLeft = new ArrayList<String>();
			
			for(int r=0; r<oneReaction.getSubstrance().size(); r++){
				
				if( !inputHash.contains( oneReaction.getSubstrance().get(r)) ){
					reactsLeft.add(oneReaction.getSubstrance().get(r));
				}
				
			} 
			
			if( reactsLeft.size() == 0 ){
				
				//put every products in oneReaction to inputHash; 
				String[] products = oneReaction.getProducts();
				
				for(int p=0; p<products.length; p++){
					inputHash.add(products[p]);
				}
				
				//reshape unhappenReactions;
				
				unhappenReactions = reshapeReactions(unhappenReactions, inputHash);
				
			} else {
				
				Reaction3 newReact = new Reaction3(reactsLeft, oneReaction.getProducts());
				unhappenReactions.add(newReact);
			}
			
		}//end for i<allReactions.size();
		
		return unhappenReactions;
	} //end reshapeReactions() method;
	
	
}//ee


/*******************************************************************************
 * the reaction object
 * 
 * @author Jeff
 *
 */
class Reaction3 {
	
	private ArrayList<String> substances; 	//		= new ArrayList<String>();
	private String[] products; 	//   	= new ArrayList<String>();
	
	public Reaction3(ArrayList<String> str1, String[] str2){
		super();
		
		this.substances = str1;
		this.products = str2; 
		
	} 
	
	//get substrances and products; 
	public ArrayList<String> getSubstrance(){
		return substances;
	}
	
	public String[] getProducts(){
		
		return products; 
	}
	
}//end Reaction3 class;

