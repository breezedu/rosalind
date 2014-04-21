package rosalind;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/***************
 * http://rosalind.info/problems/ddeg/
 * 
 * Given: A simple graph with n¡Ü103 vertices in the edge list format.
 * Return: An array D[1..n] where D[i] is the sum of the degrees of i's neighbors.
 * 
 * Sample Dataset
 * 5 4
 * 1 2
 * 2 3
 * 4 3
 * 2 4
 * 
 * Sample Output
 * 3 5 5 5 0
 * 
 * @author Frog
 *
 */
public class DoubleDegreeGraph {
	
	public static void main(String[] args) throws FileNotFoundException{
		
		System.out.println("This is Double Degree Graph program.");
		
		//1st, create an ArrayList as the graph data-structure
		ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
		
		
		//2nd, read-in edges from DoubleDegreeArray.txt document;
		Scanner readScan = new Scanner(new File("DoubleDegreeArray.txt"));
		
		int vertic = readScan.nextInt();
		int edges = readScan.nextInt();
		
		System.out.println("There are " + vertic + " vertices and " + edges + " edges n the graph.");
		
		for(int i=0; i<=vertic; i++){
			
			ArrayList<Integer> temp = new ArrayList<Integer>();
			graph.add(temp);
		}
		
		while(readScan.hasNext()){
			
			int v1 = readScan.nextInt();
			int v2 = readScan.nextInt();
			
			graph.get(v1).add(v2);
			graph.get(v2).add(v1);
		}
		
		readScan.close();
		
		
		//2nd, after readIn the graph data-structure from TXT document;
		//calculate the sum of degrees of each vertice's neighbor;
		
		for(int i=1; i<=vertic; i++){
			int degrees = 0;
			
			//first, get all vertices from vertics[i]'s ArrayList, these are the neighbors
			for(int j=0; j<graph.get(i).size(); j++){
				
				//get the degree of each neighbor of vertices[i];
				degrees += graph.get( graph.get(i).get(j) ).size();
				
			}//end inner for j<graph.get(i).size() loop;
			
			System.out.print(degrees + " ");
		}//end out for i<=vertic loop;
		
	}//end main();

}//end of everything in DoubleDegreeGraph class
