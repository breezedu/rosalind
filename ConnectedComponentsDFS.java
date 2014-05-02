package rosalind;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/***************
 * http://rosalind.info/problems/cc/
 * 
 * The task is to use depth-first search to compute the number of connected components
 * in a given undirected graph.
 * Given: A simple graph with n¡Ü103 vertices in the edge list format.
 * Return: The number of connected components in the graph.
 * 
 * Sample Dataset
 * 12 13
 * 1 2
 * 1 5
 * 5 9
 * 5 10
 * 9 10
 * 3 4
 * 3 7
 * 3 8
 * 4 8
 * 7 11
 * 8 11
 * 11 12
 * 8 12
 * 
 * Sample Output
 * 3
 * 
 * @author Frog
 * 
 */
public class ConnectedComponentsDFS {

	public static void main(String[] args) throws FileNotFoundException{
		
		System.out.println("This is Connected Components DFS program.");
		
		
		//1st, readin a graph from ConnectCom.txt document;
		Scanner readin = new Scanner(new File("ConnectCom.txt"));
		
		int vertice = readin.nextInt();
		int edge = readin.nextInt();
		
		//create a graph, which is just an ALofAL indeed;
		ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
		
		
		
		//declare an array vert[] each represents a vertices; 
		//at the beginning, the value of vert[i] = 0;
		int[] vert = new int[vertice+1];
		for(int i=0; i<=vertice; i++){
			vert[i] = 0; 	//when verti[i] ==0 means it has not been visited;
			
			ArrayList<Integer> empty = new ArrayList<Integer>();
			graph.add(empty);
		}
				
		
		//read each edge, weighted-union two vertices on the edge;
		//let the vert[big] points to vert[small], in this way, each vertex will be 'grouped'
		//to a group, the 'lead' of that group would be the vertex with the smallest value;		
		for(int i=0; i<edge; i++){
			
			int v1 = readin.nextInt();
			int v2 = readin.nextInt();
			
			//	It's interesting that when building the graph data structure, 
			//  I have to add both v1 and v2
			//  to their neighbor-arrayList respectively; 
			
			graph.get(v1).add(v2);	//add v1's neighbor
			graph.get(v2).add(v1);	//add v2's neighbor
		
		} //end for i<edge loop;
		
		readin.close();
		
		
		//2nd, check how many vertices still pointing to themselves; then we know how many groups we have.
		int group = 0;
		for(int i=1; i<=vertice; i++){
						
			if(vert[i] == 0) {
				group++;
				
				dfsGroup(i, graph, vert);
				
			}//end if vert[i] == 0 condition;
			
		}//end for i<=vertices loop; groups counted;
		
		
		//3rd, printout the groups in the graph.
		System.out.println("There are " + group + " groups in the graph.");
		
	}//end main();

	/***********
	 * dfsGroup current vertex, mark current vertex as 'read'; then dfs-group 
	 * all current vertex's neighbors, and recurrently dfs-group neighbors'neighbors;
	 * @param n
	 * @param graph
	 * @param vert
	 */
	private static void dfsGroup(int n, ArrayList<ArrayList<Integer>> graph, int[] vert) {
		// TODO group all vertices of n's neighbors and neighbors' neighbors...
		vert[n] = 1;	//this means vert[n] has been visited;
		
		//dfs-group all vert[n]'s neighbors;
		int neighbors = graph.get(n).size();		
		for(int i=0; i<neighbors; i++){
			
			int v = graph.get(n).get(i);
			
			if(vert[v] == 0){
				dfsGroup(v, graph, vert);
			}
			
		}//end for i<neighbors loop;		
		
	} //end dfsGroup() method;
	
}//end of everything in ConnectedComponentsDFS class;
