package rosalind;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/***************
 * http://rosalind.info/problems/gc/
 * 
 * Given: At most 10 DNA strings in FASTA format (of length at most 1 kbp each).
 * Return: The ID of the string having the highest GC-content, 
 * followed by the GC-content of that string. 
 * Rosalind allows for a default error of 0.001 in all decimal answers 
 * unless otherwise stated; please see the note on absolute error below.
 * 
 * Sample Dataset
 * >Rosalind_6404
 * CCTGCGGAAGATCGGCACTAGAATAGCCAGAACCGTTTCTCTGAGGCTTCCGGCCTTCCCTCCCACTAATAATTCTGAGG
 * >Rosalind_5959
 * CCATCGGTAGCGCATCCTTAGTCCAATTAAGTCCCTATCCAGGCGCTCCGCCGAAGGTCTATATCCATTTGTCAGCAGACACGC
 * >Rosalind_0808
 * CCACCCTCGTGGTATGGCTAGGCATTCAGGAACCGGAGAACGCTTCAGACCAGCCCGGACTGGGAACCTGCGGGCAGTAGGTGGAAT
 * 
 * Sample Output
 * Rosalind_0808
 * 60.919540
 * 
 * @author Frog
 *
 */
public class ComputingGCContent {
	
	public static void main(String[] args) throws IOException{
		
		System.out.println("This is Computing GC Content program.");
		
		//1st, readin DNA sequence and the name
		//create two ArrayLists, one store names, the other store dna sequences;
		Scanner ScanLines = new Scanner(new File("GCContent.txt"));
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<String> sequences = new ArrayList<String>();
		
		String seq = "";
		while(ScanLines.hasNextLine()){
			
			//readin next line from txt document:
			String temp = ScanLines.nextLine();
			
			if(temp.charAt(0)=='>') {
				names.add(temp);
				sequences.add(seq);
				seq = "";
				
			} else {
				
				seq += temp;
			}			
			
		}//end while loop;
		
		//the first element in the sequences.arrayList is ""; we have to remove it;
		//also, the last sequence has not been added to the ArrayList;
		sequences.add(seq);
		sequences.remove(0);
		
		ScanLines.close();		//close the ScanLines;
		
		for(int i=0; i<names.size(); i++){
			System.out.println("names: " + names.get(i) +", sequences: " +sequences.get(i));
			
		}
		
		
		//2nd, check all sequences in the sequences-arrayList, calculate there GC contents;
		//update maxName and maxContent when we get a dna string with higher GC contents;
		int size = names.size();
		String maxName = "";
		double maxContent = 0;
		for(int i=0; i<size; i++){
			
			String temp = sequences.get(i);
			double GCContent = calContent(temp);
			
			if(GCContent > maxContent){
				
				maxContent = GCContent;
				maxName = names.get(i);
			}//end if-condition;
			
		}//end for i<size loop;
		
		
		//3rd, printout the maxName and maxContent;
		System.out.println();
		System.out.println(maxName.substring(1));
		System.out.println(maxContent*100);
		
	}//end main();

	private static double calContent(String dna) {
		// TODO calculate the GC content in a DNA string
		
		int Len = dna.length();
		double GCCount = 0;
		for(int i=0; i<Len; i++){
			if(dna.charAt(i)== 'G' || dna.charAt(i)=='C') GCCount++;
		}
		
		System.out.println("count " + GCCount +" total " +Len);
		return GCCount/Len;
	}
	

}//end of everything in ComputingGCContent class
