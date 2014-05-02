package rosalind;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/***************
 * http://rosalind.info/problems/dij/
 * http://en.wikipedia.org/wiki/Dijkstra's_algorithm
 * 
 * Given: A simple directed graph with positive edge weights from 1 to 10^3 and n≤10^3 vertices
 * in the edge list format. 
 * Return: An array D[1..n] where D[i] is the length of a shortest path 
 * from the vertex 1 to the vertex i (D[1]=0). If i is not reachable from 1 set D[i] to −1.
 * 
 * Sample DataSet
 * 6 10
 * 3 4 4
 * 1 2 4
 * 1 3 2
 * 2 3 3
 * 6 3 2
 * 3 5 5
 * 5 4 1
 * 3 2 1
 * 2 4 2
 * 2 5 3
 * 
 * Sample Output
 * 0 3 2 5 6 -1
 * 
 * @author Frog
 *
 */

public class DijkstraAlgorithm {
	
	public static void main(String[] args) throws FileNotFoundException{
		
		System.out.println("This is Dijkstra's algorithm program.");
		
		//1st of all, read in the num of vertices and edges
		Scanner readScan = new Scanner(new File("dijk.txt"));
		
		int vertices = readScan.nextInt();
		int edges = readScan.nextInt();
		
		int[][] distance = new int[vertices+1][vertices+1];
		for(int i=0; i<=vertices; i++){
			
			for(int j=0; j<=vertices; j++){
				
				distance[i][j] = 99999999;
			}
		}//end for i<=vertices loop;
		
		
		//declare an array of vert[] to record whether that vertex has been visited or not;
		int[] vert = new int[vertices+1];
		ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> ascent = new ArrayList<ArrayList<Integer>>();
		
		for(int i=0; i<=vertices; i++){
			vert[i] = 0; 				//vert[i]=0 means vertex[i] has not been visited;
			
			ArrayList<Integer> empty = new ArrayList<Integer>();
			graph.add(empty);
			
			ArrayList<Integer> ascentEmpty = new ArrayList<Integer>();
			ascent.add(ascentEmpty);
			
		}//end for i<=vertices loop;
		
		
		//2nd, read in edges;
		for(int i=0; i<edges; i++){
			
			int v1 = readScan.nextInt();
			int v2 = readScan.nextInt();
			int dis = readScan.nextInt();
			
			graph.get(v1).add(v2);
			ascent.get(v2).add(v1);		//add v1 to v2's ascent ArrayList;
			
			//build the distance matrix:
			if(dis < distance[v1][v2]) distance[v1][v2] = dis;
		//	if(dis < distance[v2][v1]) distance[v2][v1] = dis;
			
		}//enf for i<edges loop;
		
		readScan.close(); 	//end readScan, all vertices and edges have been read in;
		
		
		//3rd, printout the distance matrix to check if everything is ok:
	//	printMatrix(distance);
		
		
		//4th, dijkstra() the initial vertex;
		//create an array of distance from initial vertex to vertex[i];
		int[] dist = new int[vertices+1];
		for(int i=1; i<=vertices; i++){
			dist[i] = 999999;
		}
		dist[1] = 0;	//int currDis = 0;
		
		dijkstra(1, vert, dist, distance, graph, ascent);
		//	vert[i] = 1;
		
		
		//5th, printout the distance array;
		System.out.println("The distance from initial vertex to each vertex are: ");
		for(int i=1; i<=vertices; i++){
			if(dist[i] == 999999) {
				System.out.print(-1 + " ");
			
			} else {
				System.out.print(dist[i] + " ");
				
			}
		}//end for i<=vertices loop;
		
	}//end main();

	private static void dijkstra(int n, int[] vert, int[] dist, int[][] distance, 
			ArrayList<ArrayList<Integer>> graph, ArrayList<ArrayList<Integer>> ascent) {
		// TODO Auto-generated method stub
		if(vert[n] == 1) return;
		
		int broad = graph.get(n).size(); 	//this are the num of neighbors to vertex n;
		
		for(int i=0; i<broad; i++){
			int v1 = n;
			int v2 = graph.get(n).get(i);
			
			if(distance[v1][v2] + dist[v1] < dist[v2]) {
				
				dist[v2] = dist[v1]+distance[n][v2];
			
			}
			
			
		}//end for i<board loop;
		
		//mark vert[n] as visited;
		vert[n] = 1;
		
		
		//check all vertex n's ascent to vertex[n]'s distance;
		
		int ascLen = ascent.get(n).size();
		for(int i=0; i<ascLen; i++){
			
			int v1 = ascent.get(n).get(i);
			int v2 = n;
			
			if(distance[v1][v2] + dist[v1] < dist[v2]){
				dist[v2] = distance[v1][v2]+dist[v1];
			}
			
		}//end for i<ascLen loop;
		
		//printout all distances from initial vertex to vert[n]'s neighbors
		
		System.out.println(" n=" + n);
		for(int i=0; i<broad; i++){
			System.out.print("d" +graph.get(n).get(i) +" = " + dist[graph.get(n).get(i)] + "; ");
		}
		System.out.println();
		
		
		//dijkstra() all n's neighbors;
		for(int i=0; i<broad; i++){
			
			dijkstra(graph.get(n).get(i), vert, dist, distance, graph, ascent);
		}

		
	}//end dijkstra() method;

	private static void printMatrix(int[][] matrix) {
		// TODO Printout a matrix
		
		int row = matrix.length;
		int col = matrix[0].length;
		
		System.out.println("The distance matrix: ");
		
		for(int i=0; i<row; i++){
			
			for(int j=0; j<col; j++){
				
				if(matrix[i][j] == 99999999) {
					
					System.out.print("   *");
					
				} else if(matrix[i][j] > 999){
					
					System.out.print(" " + matrix[i][j]);
					
				} else if(matrix[i][j] > 99){
					
					System.out.print("  " + matrix[i][j]);
					
				} else {
					
					System.out.print("   " + matrix[i][j]);
					
				}//end if-else conditions;
			}//end inner for j<col loop;
			
			System.out.println();
		}//end outer for i<row loop;
		
	}//end printMatrix() method;

}//end of everything in DijkstraAlgorithm class;
