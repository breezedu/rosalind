package rosalind;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/***************
 * http://rosalind.info/problems/bfs/
 * 
 * Given: A simple directed graph with n≤103 vertices in the edge list format.
 * Return: An array D[1..n] where D[i] is the length of a shortest path from the vertex 1 
 * to the vertex i (D[1]=0). If i is not reachable from 1 set D[i] to −1.
 * 
 * Sample Dataset
 * 6 6
 * 4 6
 * 6 5
 * 4 3
 * 3 5
 * 2 1
 * 1 4
 * 
 * Sample Output
 * 0 -1 2 1 3 2
 * 
 * @author Frog
 *
 */
public class BreadthFirstSearch {
	
	public static void main(String[] args) throws FileNotFoundException{
		
		System.out.println("This is Breadth-First Search program.");
		
		//1st, read-in graph from bfs.txt document;
		Scanner readin = new Scanner(new File("bfs.txt"));
		
		int vertices = readin.nextInt();
		int edges = readin.nextInt();
		
		System.out.println("There are " + vertices + " vertices and " + edges + " edges.");
		
		ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
		//create vertices arrayLists, each AL represents the neighbors of vertices[i];
		for(int i=0; i<=vertices; i++){
			ArrayList<Integer> neighbors = new ArrayList<Integer>();
			graph.add(neighbors);
		}
		
		for(int i=0; i<edges; i++){
			int v1 = readin.nextInt();
			int v2 = readin.nextInt();
			
			graph.get(v1).add(v2);
		}
		
		readin.close();
		
		
		//2nd, bfs the graph;
		int level = 0;
		int[] length = new int[vertices+1];
		for(int i=0; i<=vertices; i++){
			length[i] = -1;
		}
		
		ArrayList<Integer> currLevel = new ArrayList<Integer>();
		currLevel.add(1);
		
		bfsGraph(currLevel, level, graph, length);		
		
		//3rd, printout the length[] array;
		System.out.println("Printout the depthes: ");
		for(int i=1; i<=vertices; i++){
			
			System.out.print(length[i] + " ");
		}
		
	}//end main();
	
	private static void bfsGraph(ArrayList<Integer> curr, int level, ArrayList<ArrayList<Integer>> graph, int[] length) {
		// TODO Auto-generated method stub
		if(level > graph.size()) return;
		
		int size = curr.size();
		ArrayList<Integer> nextLevel = new ArrayList<Integer>();
		
		for(int i=0; i<size; i++){
			int v = curr.get(i);
			if(length[v] == -1 || level<length[v]){
				length[v] = level;
				
			}//end if conditions;
			
			ArrayList<Integer> neighbors = graph.get(v);
			for(int e:neighbors){
				
				if(length[e]<0) nextLevel.add(e);
			}
//			nextLevel.addAll(graph.get(v));
		}
		
		bfsGraph(nextLevel, level+1, graph, length);
				
	}//end of bfsGraph() method;

}//end of everything in BreadthFirstSearch class;
