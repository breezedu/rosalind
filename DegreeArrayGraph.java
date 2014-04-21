package rosalind;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**************
 * http://rosalind.info/problems/deg/
 * 
 * In an undirected graph, the degree d(u) of a vertex u is the number of neighbors u has, 
 * or equivalently, the number of edges incident upon it.
 * 
 * Given: A simple graph with n¡Ü103 vertices in the edge list format.
 * Return: An array D[1..n] where D[i] is the degree of vertex i.
 * 
 * Sample Dataset
 * 
 * 6 7
 * 1 2
 * 2 3
 * 6 3
 * 5 6
 * 2 5
 * 2 4
 * 4 1
 * 
 * Sample Output
 * 2 4 2 2 2 2
 * 
 * @author Frog
 *
 */

/**********
 * Actually, this Vertices class is useless here in this program.
 * @author Frog
 *
 */
class Vertic{
	int val;
	ArrayList<Integer> neighbors = new ArrayList<Integer>();
}

public class DegreeArrayGraph {
	
	public static void main(String[] args) throws FileNotFoundException{
		
		System.out.println("This is Degree Array Graph program.");
		
		ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
		
		//1st, create a graph, readin edges from txt document;
		Scanner readScan = new Scanner(new File("DegreeArray.txt"));
		
		int vertic = readScan.nextInt();
		int edges = readScan.nextInt();
		
		System.out.println("There are " + vertic +" vertic and " + edges + " edges.");
		for(int i=0; i<=vertic; i++){
			ArrayList<Integer> temp = new ArrayList<Integer>();
			graph.add(temp);
		}
		
		while(readScan.hasNext()){
			//readIn two integers at one time;
			
			int v1 = readScan.nextInt();
			int v2 = readScan.nextInt();
			
			graph.get(v1).add(v2);
			graph.get(v2).add(v1);
		}//end while loop, ReadIn all lines;
		
		readScan.close();
		
		
		//2nd, printout neighbor number each vertex has:
		System.out.println();
		for(int i=1; i<=vertic; i++){
			System.out.print(graph.get(i).size() +" ");
		}
		
	}//end main();

}//end of everything in DegreeArrayGraph class
