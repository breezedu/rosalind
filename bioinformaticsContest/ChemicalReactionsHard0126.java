package bioinformaticsContest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


/***************
 * This code works fine for easy part on Bioinformatics Contest 2017
 * @author Jeff
 *
 */
public class ChemicalReactionsHard0126 {
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in) );
		
		HashSet<String> inputHash = new HashSet<String>(); 
		HashMap<String, Integer> duplicateReactions = new HashMap<String, Integer>();
		HashSet<String> happendedReactions = new HashSet<String>(); 
		
		//1st. get the first line:
		String firstLine = input.readLine(); 
		String[] inputChem = firstLine.split(" "); 
		
		for(int i=0; i<inputChem.length; i++){
			
			inputHash.add(inputChem[i]); 
						
		} //end for i<inputChem.length; 
		
		
		//creat an arraylist to store all unused reactions;
		ArrayList<Reaction6> AllReactions = new ArrayList<Reaction6>(); 		
		
		//2nd. get all related reactions; 		
		while( input.ready() ){
			
			String inputLine = input.readLine(); 		
			
			if(inputLine.length() < 1) break; 			
				
	//		inputLine = inputLine.replaceAll("\\+", "\t"); 
				
			//get the index of "->";
			int pivot = inputLine.indexOf('-'); 
			
			//get substrances string[] and products String[]; 
			String left = inputLine.substring(0, pivot);
			String right = inputLine.substring(pivot+2);

			
			/*********************************************************************************************************************/
			//check if the reaction has already happened, if so, add all products to the inputHash set;
			if(happendedReactions.contains(left)){
				
				String[] products = right.split("\\+");
				for(int i=0; i<products.length; i++){
					inputHash.add(products[i]);
				}
				
				//reshape Allcollections;
				AllReactions = reshapeReactions(AllReactions, inputHash);
				
			}//end if the reaction has already happened; 
			
			
			/*********************************************************************************************************************/
			//check if the Reactions is already exist in the allReactions arrayList;
			if(duplicateReactions.containsKey(left)){
				//merge two reactions;
				int index = duplicateReactions.get(left);
				
				Reaction6 currReact = new Reaction6(AllReactions.get(index).getSubstrance(), AllReactions.get(index).getProducts()+"+" + right );
				AllReactions.set(index, currReact);
				
			}
			
			
			/*********************************************************************************************************************/
			//reduce the size of the reaction substances;
			String[] substances = left.split("\\+");
	//		String[] products = right.split("\\+"); 			
			
			//create a LinkedList for reaction substances
			ListNode substranceNode = new ListNode( "" );
			ListNode pointer = substranceNode; 
			
			for(int i=0; i<substances.length; i++){
				
				if( !inputHash.contains(substances[i]) ){
					
					pointer.next = new ListNode( substances[i] );
					pointer = pointer.next;					
				}
				
			}//end for i<substrances.length loop; 
			
			substranceNode = substranceNode.next; 
						
			
			//check how many reacts not contained in the inputHash;
			if(substranceNode != null){								
				
				//got a new reaction, add it to the allReaction arraylist, update duplicated hashMap;
				Reaction6 currReact = new Reaction6(substranceNode, right);				
				
				duplicateReactions.put(left, AllReactions.size());
				
				AllReactions.add(currReact);
				
			
			} else {
				
				String[] products = right.split("\\+"); 				
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
	private static ArrayList<Reaction6> reshapeReactions(ArrayList<Reaction6> allReactions, HashSet<String> inputHash) {
		// TODO Auto-generated method stub
		
		if(allReactions.size() == 0){
			return allReactions;
		}
				
		
		for(int i=0; i<allReactions.size(); i++){
			
			Reaction6 oneReaction = allReactions.get(i);
			
			ListNode helper = new ListNode("");
			helper.next = oneReaction.getSubstrance();
			ListNode p = helper;
			
			while(p.next != null){
				
				if(inputHash.contains( p.next.val ) ){
					p.next = p.next.next; 
				
				} else {
					p = p.next;
				
				}				
			}//end while();
			
			
			
			if( helper.next == null ){
				
				//put every products in oneReaction to inputHash; 
				String[] products = oneReaction.getProducts().split("\\+");
				
				for(int pr=0; pr<products.length; pr++){
					inputHash.add(products[pr]);
				}
				
				//reshape unhappenReactions;
				allReactions.remove(i);
				allReactions = reshapeReactions(allReactions, inputHash);
				
			} else {
				
				Reaction6 newReact = new Reaction6(helper.next, oneReaction.getProducts());
				
				allReactions.set(i, newReact);
			}
			
		}//end for i<allReactions.size();
		
		return allReactions;
		
	} //end reshapeReactions() method;
	
	
}//ee


/*******************************************************************************
 * the reaction object
 * 
 * @author Jeff
 *
 */
class Reaction6 {
	
	private ListNode substances; 	//		= new ArrayList<String>();
	private String products; 	//   	= new ArrayList<String>();

	
	public Reaction6(ListNode str1, String str2){
		super();
		
		this.substances = str1;
		this.products = str2; 
		
	} 
	

	
	//get substrances and products; 
	public ListNode getSubstrance(){
		return substances;
	}
	
	public String getProducts(){
		
		return products; 
	}
	
}//end Reaction3 class;

class ListNode{
	String val;
	ListNode next;
	
	ListNode(String x){
		val = x;
		next = null;
	}
} //end of listNode class

