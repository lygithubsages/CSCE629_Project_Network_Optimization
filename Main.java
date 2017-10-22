package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

public class Main {
	 public static void main(String[] args) {
		//graph1----------------------------------------------------------------------
		 GenerateGraph1 g1 = new GenerateGraph1();
		 Map<Integer,LinkedList<Edge>> finalGraph = g1.buildGraph();
			int s = 666 ; //start vertex name
			int start = s;
			int t = 1888; //target vertex name
			int temp = t;
			int tempKru = t;
			int end = t;
			

			
		 //max bandwidth without heap for graph1-----------------------------------------------------------------------
		   long starttimewithoutheap = System.currentTimeMillis();
	       MaxBandwidthWithoutHeap mbwNoHeap = new MaxBandwidthWithoutHeap();
	       ArrayList<Integer> pathwithout = new ArrayList<Integer>();
	       
			int[] Dadwithoutheap = mbwNoHeap.getMaxBw(finalGraph, s, temp);
			System.out.println("Dijkstra’s algorithm without using a heap "+"maximum bandwidth is "+mbwNoHeap.bandWidth[temp]);
			while(Dadwithoutheap[temp]!= s){  
				int pre = Dadwithoutheap[temp];
				pathwithout.add(pre);
				temp = pre;	
			}
			mbwNoHeap.printReversedList(pathwithout,start,end);
			long endtimewithoutheap = System.currentTimeMillis();
			System.out.println(endtimewithoutheap-starttimewithoutheap+" ms");  
			System.out.println();
			
			// max bandwidth with heap for graph1----------------------------------------------------------------------
		    long starttime = System.currentTimeMillis();
	        
	        ArrayList<Integer> path = new ArrayList<Integer>();
	   
			MaxBandwidthDijkstra mbw = new MaxBandwidthDijkstra();
			
			int[] Dad = mbw.getMaxBw(finalGraph, s, t);
			System.out.println("Dijkstra’s algorithm using a heap "+"maximum bandwidth is "+mbw.bandWidth[t]);
			while(Dad[t]!= s){  
				int pre = Dad[t];
				path.add(pre);
				t = pre;	
			}
			mbw.printReversedList(path, start, end);
			long endtime = System.currentTimeMillis();
			System.out.println(endtime-starttime+" ms");
			System.out.println();
			
			
			//max bandwidth kruskal for graph1----------------------------------------------------------------------
			long starttimekruskal = System.currentTimeMillis();
			KruskalForG1 obj2 = new KruskalForG1();
			obj2.MakeSet(finalGraph);//make set after that build MAxST
			ArrayList<Edge2> d = obj2.orederEdge(finalGraph);
		    //do BFS on MaxST
		    obj2.BFS(obj2.MaxST(d),1);
		    //find path from BFS
		    System.out.println("kruscal's algorithm using a heap ");
		    obj2.printReversedList(obj2.findPath(obj2.BFS(obj2.MaxST(d),s ), s , tempKru),s,tempKru); 
			long endtimekruskal = System.currentTimeMillis();
			System.out.println(endtimekruskal-starttimekruskal+" ms");  
			System.out.println();
			
			//graph2----------------------------------------------------------------------
			
			Generategraph2 g2 = new Generategraph2();
			int[][] finalGraph1 = g2.buildGraph(g2);
			int s1 = 268;   //start vertex name 
			int start1 = s1;
			int t1 = 1688;  //target vertex name
			int temp1 =t1;
			int tempKru1 = t1;
			int end1 = t1;
			
			// max bandwidth without heap for graph 2----------------------------------------------------------------------
			long starttimewithout = System.currentTimeMillis();
			MaxBandwidthWithoutHeapG2 mbwNoHeap1 = new MaxBandwidthWithoutHeapG2();
			ArrayList<Integer> pathwithout1 = new ArrayList<Integer>();
			int[] Dadwithout = mbwNoHeap1.getMaxBw(finalGraph1, s1, temp1);
			System.out.println("Dijkstra’s algorithm without using a heap for graph2"+" maximum bandwidth is "+mbwNoHeap1.bandWidth[t1]);
			while(Dadwithout[temp1]!= s1){  
				int pre = Dadwithout[temp1];
				pathwithout1.add(pre);
				temp1 = pre;	
			}
			mbwNoHeap1.printReversedList(pathwithout1, start1, end1);	
		    long endtimewithout = System.currentTimeMillis();
			System.out.println(endtimewithout-starttimewithout+ " ms");
			System.out.println();
			
			// max bandwidth with heap for graph 2----------------------------------------------------------------------
			long starttime1 = System.currentTimeMillis();
			
			ArrayList<Integer> path1 = new ArrayList<Integer>();
			
			MaxBandwidthHeapG2 mbw1 = new MaxBandwidthHeapG2();
			int[] Dad1 = mbw1.getMaxBw(finalGraph1, s1, t1);
			System.out.println("Dijkstra’s algorithm using a heap for graph2 "+" maximum bandwidth is "+mbw1.bandWidth[t1]);
			while(Dad1[t1]!= s1){  
				int pre = Dad1[t1];
				path1.add(pre);
				t1 = pre;	
			}
			mbw1.printReversedList(path1, start1, end1);
			long endtime1 = System.currentTimeMillis();
			System.out.println(endtime1-starttime1+ " ms");
			System.out.println();
			

			//max bandwidth kruskal for graph 2----------------------------------------------------------------------
			long starttimekruskal1 = System.currentTimeMillis();
		    KruskalForG2 obj21 = new KruskalForG2();
			obj21.MakeSet(finalGraph1);
			ArrayList<Edge2> d1 = obj21.orederEdge(finalGraph1);
			System.out.println("kruscal's algorithm using a heap for Graph2 ");
			obj21.printReversedList(obj21.findPath(obj21.BFS(obj21.MaxST(d1),s1), s1, tempKru1),s1,tempKru1); //end is t(temp)
		    long endtimekruskal1 = System.currentTimeMillis();
			System.out.println(endtimekruskal1-starttimekruskal1+ " ms");
			System.out.println();

	 }
}
