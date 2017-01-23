package bioinformaticsContest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;


/***************
 * This code works fine for easy part on Bioinformatics Contest 2017
 * @author Jeff
 *
 */
public class Main {
	
	public static void main(String[] args){
		
		Scanner input = new Scanner(System.in) ;
		
		HashSet<String> inputHash = new HashSet<String>(); 
		
		//1st. get the first line:
		String firstLine = input.nextLine(); 
		String[] inputChem = firstLine.split(" "); 
		
		for(int i=0; i<inputChem.length; i++){
			
			inputHash.add(inputChem[i]); 
						
		} //end for i<inputChem.length; 
		
		
		//2nd. get all related reactions; 
		ArrayList<Reaction> AllReactions = new ArrayList<Reaction>(); 
				
		while( input.hasNextLine() ){
			
			String inputLine = input.nextLine(); 
			
			if(inputLine.length() > 1){
				
				Reaction currReact = getReactionFromString( inputLine ); 
				
				//add current reaction to the ArrayList<>; 
				AllReactions.add(currReact); 
			}	
				
		}//end while; 
		
		//close input
		input.close(); 
		
		
		//check every reaction in the reaction ArrayList, 
		//	if any reaction's substrates are included in the  input-HashSet
		// 	one reaction will happen, then update input-hash with the products (put every products into the input-hashset; 
		// 	delete the current reaction from the react-arraylist; 
		
		boolean reactP = true; 
		
		while(reactP){
			
			int numOfReactions = 0; 
			
			for(int i=0; i<AllReactions.size(); i++){								
				
				if( allSubstratesAvailable( AllReactions.get(i), inputHash ) ){
					
					//add all products of current reaction to the HashSet; 
					
					ArrayList<String> thisProducts = AllReactions.get(i).products; 
					for(int j=0; j<thisProducts.size(); j++){
						
						inputHash.add(thisProducts.get(j)); 
						
					}//end inner for loop; 
					
					
					//remove current reaction from the AllReaction-ArrayList<>; 
					AllReactions.remove(i); 
					
					numOfReactions ++; 
														
					
				}//end if allSubstratesAvailable()
				
			}//end for i<AllReactions.size(); 
			
			
			if(numOfReactions < 1) reactP = false; 
			
		}//end while(reactP); 
		
		for(String s:inputHash){
			System.out.print(s + " ");
		}

		
	}//end main(); 

	
	
	private static boolean allSubstratesAvailable(Reaction reaction, HashSet<String> inputHash) {
		// TODO Auto-generated method stub
		
		ArrayList<String> substrates = reaction.substances; 
		
		for(int i=0; i<substrates.size(); i++){
			
			if(!inputHash.contains( substrates.get(i) ) ) return false; 
			
		}
		
		return true;
		
	} //end allSubstratesAvailable() method; 



	/**********************
	 * Get an reaction from a string 1+2+4->5+7
	 * 
	 * @param strLine
	 * @return
	 */
	private static Reaction getReactionFromString(String strLine) {
		// TODO Auto-generated method stub
		Reaction react = new Reaction(); 
		
		strLine = strLine.replaceAll("\\+", "\t"); 
		
		//break the string by "->"; 
		
		int pivot = strLine.indexOf("->"); 
		
		String substrates = strLine.substring(0, pivot); 
		
		String products = strLine.substring(pivot+2); 
		
		
		
		String[] subs = substrates.split("\t"); 
		
		for(int i=0; i<subs.length; i++){
			react.substances.add( subs[i] ); 
		}
				
		
		String[] prods = products.split("\t"); 
		
		for(int i=0; i<prods.length; i++){
			react.products.add( prods[i] ); 
		}
				
		
		
		
		return react;
		
	} //end reaction method; 
	
	

}//ee


class Reaction {
	
	ArrayList<String> substances = new ArrayList<String>();
	ArrayList<String> products   = new ArrayList<String>();
	
	
}

