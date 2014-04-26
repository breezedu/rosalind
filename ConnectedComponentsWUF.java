package rosalind;

import java.io.File;
import java.io.FileNotFoundException;
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
 * Instead of DFS, I found Weighted-Union works fine too, :)
 */
public class ConnectedComponentsWUF {

	public static void main(String[] args) throws FileNotFoundException{
		
		System.out.println("This is Connected Components DFS program.");
		
		
		//1st, readin a graph from ConnectCom.txt document;
		Scanner readin = new Scanner(new File("ConnectCom.txt"));
		
		int vertice = readin.nextInt();
		int edge = readin.nextInt();
		
		//declare an array vert[] each represents a vertices; 
		//at the beginning, the value of vert[i] = i;
		int[] vert = new int[vertice+1];
		for(int i=1; i<=vertice; i++){
			vert[i] = i;
		}
		
		//read each edge, weighted-union two vertices on the edge;
		//let the vert[big] points to vert[small], in this way, each vertex will be 'grouped'
		//to a group, the 'lead' of that group would be the vertex with the smallest value;
		for(int i=0; i<edge; i++){
			
			int v1 = readin.nextInt();
			int v2 = readin.nextInt();
			
			weightUnion(vert, v1, v2);
		
		} //end for i<edge loop;
		
		readin.close();
		
		
		//2nd, check how many vertices still pointing to themselves; then we know how many groups we have.
		int group = 0;
		for(int i=1; i<=vertice; i++){
			
			if(vert[i] == i) group++;
		}//end for i<=vertices loop; groups counted;
		
		
		//3rd, printout the groups in the graph.
		System.out.println("There are " + group + " groups in the graph.");
		
	}//end main();

	/************
	 * weighted-union method;
	 * 1st get two ancestors of the two vertices;
	 * weighted the one with bigger value to the one with smaller value;
	 * @param vert
	 * @param v1
	 * @param v2
	 */
	private static void weightUnion(int[] vert, int v1, int v2) {
		// TODO group two vertex on a edge;
		//get the ancestor of vert[v1];
		while(vert[v1] != v1){
			v1 = vert[v1];
		}
		
		//get the ancestor of vert[v2];
		while(vert[v2] != v2){
			v2 = vert[v2];
		}
		
		//weighted-union two ancestors; 
		if(v1<v2) {
			vert[v2] = v1;
		
		} else {
			vert[v1] = v2;
			
		}//end if-else conditions;
		
	}//end weightUnion() method;
	
}//end of everything in ConnectedComponentsDFS class;
