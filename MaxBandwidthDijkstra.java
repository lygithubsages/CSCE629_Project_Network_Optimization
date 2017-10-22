package Graph;
/*已验证，正确 correct */
//73 ms
//max bandwidth with heap dijstra for graph1
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class MaxBandwidthDijkstra {

	int status [] = new int[5001];
	int dad [] = new int[5001];
	static int bandWidth [] = new int[5001];
	
	
	MaxHeap1 heap = new MaxHeap1();
	ArrayList<Integer> H = new ArrayList<Integer>();

	public int[] getMaxBw (Map<Integer,LinkedList<Edge>> graph , int s ,int t){
		
		int v;
		for( int w : graph.keySet()){
			status[w] = -1; //unseen  // for every vertices make them unseen
		}
		status[s] = 1; //make start vertex intree
		dad[s] = -1; //root,start point,without dad
		bandWidth[s] = Integer.MAX_VALUE;
		for(Edge e: graph.get(s)){ // for all vertices that linked to vertex s;
			status[e.vertex] = 0 ; //fringe
			dad[e.vertex] = s;
			bandWidth[e.vertex] = e.weight;
			heap.insert(H, e.vertex ,bandWidth);
		}
		
		while(H.size()!= 1){ //while heap is not empty (do not count the first element with index 0) , means have fringes
		   
			v = heap.Max(H); //find vertex that have max weight
		    int indexMaxV = H.indexOf(v);
			heap.delete(H,indexMaxV,bandWidth);// once it becomes the largest delete it from heap ,status = in_tree;
			status[v] = 1; //intree;
	        
		    for(Edge e: graph.get(v)){ //for all vertices that linked to vertex v;
		    	 if(status[e.vertex] == -1){ //if status is unseen
				    status[e.vertex] = 0; // make them to fringe 
				    dad[e.vertex] = v; //set their father to v;
				    bandWidth[e.vertex] = Math.min(bandWidth[v], e.weight); //e.weight is weight that from e to v;
				    heap.insert(H, e.vertex, bandWidth);
			     }else if (status[e.vertex] == 0 && bandWidth[e.vertex]<Math.min(bandWidth[v], e.weight)){ // if status is unseen
			    	 int c = H.indexOf(e.vertex);
			    	 heap.delete(H,c, bandWidth);
			    	 dad[e.vertex] =  v;
			    	 bandWidth[e.vertex] = Math.min(bandWidth[v], e.weight);
			    	 heap.insert(H,e.vertex, bandWidth);
			     }
		     }
	     }
		return dad;
	}

	public static void printReversedList(List link,int a,int b) {  
        System.out.println("from "+a+" to "+b+" we need go through follow vertices");  
        //iterator for list, point to last position 
        ListIterator li = link.listIterator(link.size());  
        // judge whether it has previous vertex or not
        while (li.hasPrevious()) {  
         
            System.out.print(li.previous() + " ");  
        }  
        System.out.println();  
    }  
	
	
	public static void main(String[] args) {

		
		// max bandeidth with heap for graph1
		        long starttime = System.currentTimeMillis();
		        GenerateGraph1 g1 = new GenerateGraph1();
		        ArrayList<Integer> path = new ArrayList<Integer>();
		        Map<Integer,LinkedList<Edge>> finalGraph = g1.buildGraph();
				int s = 88 ;
				int start = s;
				int t = 3888;
				int temp = t;
				int end = t;
				MaxBandwidthDijkstra mbw = new MaxBandwidthDijkstra();
				
				int[] Dad = mbw.getMaxBw(finalGraph, s, t);
				System.out.println("maximum bandwidth is "+mbw.bandWidth[t]);
				while(Dad[t]!= s){  
					int pre = Dad[t];
					path.add(pre);
					t = pre;	
				}
				mbw.printReversedList(path, start, end);
				long endtime = System.currentTimeMillis();
				System.out.println(endtime-starttime+" ms");
	}
}



//test code
/*
		// max bandeidth with heap for graph1
		long starttime = System.currentTimeMillis();
		testGraph1 gtest = new testGraph1();
		Map<Integer,LinkedList<Edge>> finalGraphtest = gtest.buildGraph();
		ArrayList<Integer> path = new ArrayList<Integer>();
		int s = 5;
		int start = s;
		int t = 4;
		int end = t;
		MaxBandwidthDijkstra mbw = new MaxBandwidthDijkstra();
		int[] Dad = mbw.getMaxBw(finalGraphtest, s, t);
		System.out.println("maximum bandwidth is "+ bandWidth[t]);
		while(Dad[t]!= s){  //当t的父节点不是s的时候，再找t的父节点的父节点，直到找到为止。
			int pre = Dad[t];
			
			path.add(pre);
			t = pre;	
		}
		mbw.printReversedList(path, start, end);
		long endtime = System.currentTimeMillis();
		System.out.println(endtime-starttime+" ms");
*/

/*
 		// max bandeidth with heap for graph1
		long starttime = System.currentTimeMillis();
		GenerateGraph1 g1 = new GenerateGraph1();
		ArrayList<Integer> path = new ArrayList<Integer>();
		Map<Integer,LinkedList<Edge>> finalGraph = g1.buildGraph();
		int s = 1;
		int start = s;
		int t = 3000;
		int temp = t;
		int end = t;
		MaxBandwidthDijkstra mbw = new MaxBandwidthDijkstra();
		
		int[] Dad = mbw.getMaxBw(finalGraph, s, t);
		System.out.println("maximum bandwidth is "+bandWidth[t]);
		while(Dad[t]!= s){  //当t的父节点不是s的时候，再找t的父节点的父节点，直到找到为止。
			int pre = Dad[t];
			path.add(pre);
			t = pre;	
		}
		mbw.printReversedList(path, start, end);
		long endtime = System.currentTimeMillis();
		System.out.println(endtime-starttime+" ms");
		
		//max bandweidth without heap for graph1
	   long starttimewithoutheap = System.currentTimeMillis();
       MaxBandwidthWithoutHeap mbwNoHeap = new MaxBandwidthWithoutHeap();
       ArrayList<Integer> pathwithout = new ArrayList<Integer>();
       
		int[] Dadwithoutheap = mbwNoHeap.getMaxBw(finalGraph, s, temp);
		System.out.println("maximum bandwidth is "+mbwNoHeap.bandWidth[t]);
		while(Dadwithoutheap[temp]!= s){  //当t的父节点不是s的时候，再找t的父节点的父节点，直到找到为止。
			int pre = Dadwithoutheap[temp];
			pathwithout.add(pre);
			temp = pre;	
		}
		mbwNoHeap.printReversedList(pathwithout,start,end);
		long endtimewithoutheap = System.currentTimeMillis();
		System.out.println(endtimewithoutheap-starttimewithoutheap+" ms");  
		
		//max bandwidth kruskal for graph1
		long starttimekruskal = System.currentTimeMillis();
		KruskalForG1 obj2 = new KruskalForG1();
		obj2.MakeSet(finalGraph);//将图变为一个一个的点，然后通过后续再相连成一个最大生成树
		ArrayList<Edge2> d = obj2.orederEdge(finalGraph);
	    //do BFS on MaxST
	    obj2.BFS(obj2.MaxST(d),1);
	    //find path from BFS
	    printReversedList(obj2.findPath(obj2.BFS(obj2.MaxST(d),1), 1, 3000),1,3000);
		long endtimekruskal = System.currentTimeMillis();
		System.out.println(endtimekruskal-starttimekruskal+" ms");  
		*/
