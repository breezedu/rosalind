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
		
		/*********************************************************/
		//1st of all, read in the num of vertices and edges
		Scanner readScan = new Scanner(new File("dijk.txt"));
		
		int vertices = readScan.nextInt();
		int edges = readScan.nextInt();
		
		int[][] distMatrix = new int[vertices+1][vertices+1];
		for(int i=0; i<=vertices; i++){
			
			for(int j=0; j<=vertices; j++){
				
				distMatrix[i][j] = 99999999;
			}
		}//end for i<=vertices loop;
		
		
		//declare an array of vert[] to record whether that vertex has been visited or not;
		int[] vert = new int[vertices+1];
		ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
		
		for(int i=0; i<=vertices; i++){
			vert[i] = 0; 				//vert[i]=0 means vertex[i] has not been visited;
			
			ArrayList<Integer> empty = new ArrayList<Integer>();
			graph.add(empty);
			
			
		}//end for i<=vertices loop;
		
		

		/*********************************************************/
		//2nd, read in edges;
		for(int i=0; i<edges; i++){
			
			int v1 = readScan.nextInt();
			int v2 = readScan.nextInt();
			int dis = readScan.nextInt();
			
			graph.get(v1).add(v2);
			
			//build the distance matrix:
			if(dis < distMatrix[v1][v2]) distMatrix[v1][v2] = dis;
		//	if(dis < distance[v2][v1]) distance[v2][v1] = dis;
			
		}//enf for i<edges loop;
		
		readScan.close(); 	//end readScan, all vertices and edges have been read in;
		
		

		/*********************************************************/
		//3rd, printout the distance matrix to check if everything is ok:
		//	printMatrix(distance);
		
		

		/*********************************************************/
		//4th, dijkstra() the initial vertex;
		//create an array of distance from initial vertex to vertex[i];
		int[] dist = new int[vertices+1];
		for(int i=1; i<=vertices; i++){
			dist[i] = 999999;
		}
		dist[1] = 0;	//int currDis = 0;
		
		dijkstra(1, vert, dist, distMatrix, graph);
		//	vert[i] = 1;
		
		

		/*********************************************************/
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

	/**************
	 * 1 Assign to every node a tentative distance value: 
	 * set it to zero for our initial node and to infinity for all other nodes.
	 * 
	 * 2 Mark all nodes unvisited. Set the initial node as current. 
	 * Create a set of the unvisited nodes called the unvisited set consisting of all the nodes.
	 * 
	 * 3 For the current node, consider all of its unvisited neighbors and 
	 * calculate their tentative distances. Compare the newly calculated tentative distance 
	 * to the current assigned value and assign the smaller one. 
	 * For example, if the current node A is marked with a distance of 6, 
	 * and the edge connecting it with a neighbor B has length 2, 
	 * then the distance to B (through A) will be 6 + 2 = 8. 
	 * If B was previously marked with a distance greater than 8 then change it to 8. 
	 * Otherwise, keep the current value.
	 * 
	 * 4 When we are done considering all of the neighbors of the current node, 
	 * mark the current node as visited and remove it from the unvisited set. 
	 * A visited node will never be checked again.
	 * 
	 * 5 If the destination node has been marked visited 
	 * (when planning a route between two specific nodes) or if the smallest tentative distance 
	 * among the nodes in the unvisited set is infinity (when planning a complete traversal; 
	 * occurs when there is no connection between the initial node and remaining unvisited nodes), 
	 * then stop. The algorithm has finished.
	 * 
	 * 6 Select the unvisited node that is marked with the smallest tentative distance, 
	 * and set it as the new "current node" then go back to step 3.
	 * 
	 * @param n		current vertex;
	 * @param vert	vert[] array, to indicate whether the vertex[i] has been visited or not;
	 * @param dist	the distance from initial vertex to vert[i];
	 * @param distMatrix	distance matrix between two vertices;
	 * @param graph
	 */
	private static void dijkstra(int n, int[] vert, int[] dist, int[][] distMatrix, 
			ArrayList<ArrayList<Integer>> graph) {
		// TODO Auto-generated method stub
		if(vert[n] == 1) return;
		
		int broad = graph.get(n).size(); 	//this are the num of neighbors to vertex n;
		
		for(int i=0; i<broad; i++){
			int v1 = n;
			int v2 = graph.get(n).get(i);
			
			if(distMatrix[v1][v2] + dist[v1] < dist[v2]) {
				
				dist[v2] = dist[v1]+distMatrix[n][v2];
			
			}
			
		}//end for i<board loop;
		
		//mark vert[n] as visited;
		vert[n] = 1;		
		
		
		//printout all distances from initial vertex to vert[n]'s neighbors
		
		System.out.println(" n=" + n);
		for(int i=0; i<broad; i++){
			System.out.print("d" +graph.get(n).get(i) +" = " + dist[graph.get(n).get(i)] + "; ");
		}
		System.out.println();
		
		
		//dijkstra() all n's neighbors;
		
		//Recursively select the smallest tentative distance vertex, set it as current vertex, 
		//dijkstra(currentV, vert, dist, distance, graph);
		int len = vert.length;
		int min = 999999;
		int minIndex = -1;
		for(int i=1; i<len; i++){
			
			if(vert[i]==0 && dist[i]<min) {
				minIndex = i;
				min = dist[i];
				
			}//end if conditions;
			
		}//end for i<len loop; the minimum vertex has been checked out.
		
		//if minIndex = -1 after the upper loop, that means the dijkstra() method will stop;
		if(minIndex!= -1){
			
			dijkstra(minIndex, vert, dist, distMatrix, graph);
		} else {
			
			System.out.println("dijkstra method stop here;");
		}//end if-else conditions;
		
	}//end dijkstra() method;

	
	/************
	 * Printout the distance matrix;
	 * @param matrix
	 *
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
	*/
	
}//end of everything in DijkstraAlgorithm class;
