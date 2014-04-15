package rosalind;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/***************
 * http://rosalind.info/problems/grph/
 * 
 * Given: A collection of DNA strings in FASTA format having total length at most 10 kbp.
 * Return: The adjacency list corresponding to O3. You may return edges in any order.
 * 
 * Sample Dataset * 
 * >Rosalind_0498
 * AAATAAA
 * >Rosalind_2391
 * AAATTTT
 * >Rosalind_2323
 * TTTTCCC
 * >Rosalind_0442
 * AAATCCC
 * >Rosalind_5013
 * GGGTGGG
 * 
 * Sample Output *
 * Rosalind_0498 Rosalind_2391
 * Rosalind_0498 Rosalind_0442
 * Rosalind_2391 Rosalind_2323
 * 
 * @author Frog
 *
 */
public class OverlapGraphs {

	public static void main(String[] args) throws FileNotFoundException{
		
		System.out.println("This is Overlap Graphs program.");
		
		//1st, use Scanner to readin all sequence names and sequences;
		//create two arrayList to store sequence names and sequences;
		ArrayList<String> name = new ArrayList<String>();
		ArrayList<String> sequence = new ArrayList<String>();
		
		Scanner ScanLine = new Scanner(new File("OverlapGraph.txt"));
		
		String seq = "";
		while(ScanLine.hasNextLine()){
			
			String temp = ScanLine.nextLine();
			if(temp.charAt(0) == '>'){
				
				sequence.add(seq);
				name.add(temp.substring(1));
				seq = "";
			
			} else {
				
				seq += temp;
			}//end if-else conditions;
			
		}//end while loop;
		
		ScanLine.close();
		
		sequence.remove(0);
		sequence.add(seq);
		
		
		//2nd, printout the name and the correspond sequence:
		int Len = name.size();
		for(int i=0; i<Len; i++){
			
			System.out.println(name.get(i) +": " + sequence.get(i));
		}
		
		
		//3rd, connect all sequences with O3 overlap, put the linked-names into a new string ArrayList
		ArrayList<String> overLaps = linkOverlaps(name, sequence);
		
		System.out.println("\nPrintout results:");
		for(int i=0; i<overLaps.size(); i++){
			System.out.println(overLaps.get(i));
		}
		
	}//end main();

	private static ArrayList<String> linkOverlaps(ArrayList<String> name, ArrayList<String> sequence) {
		// TODO Auto-generated method stub
		ArrayList<String> overLap = new ArrayList<String>();
		int size = name.size();		
		
		for(int i=0; i<size; i++){
			int Len1 = sequence.get(i).length();
			
			for(int j=0; j<size; j++){
				
				if(i!=j && sequence.get(i).substring(Len1-3).equals(sequence.get(j).substring(0, 3))){
					String linked = name.get(i) + " " + name.get(j);
					overLap.add(linked);
				}
			}
		}
		
		return overLap;
	}
	
}//end of everything in OverlapGraphs class;
