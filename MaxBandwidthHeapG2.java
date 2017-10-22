package Graph;
/*已验证，正确 correct */
//4619ms
//max bandwidth with heap dijstra for graph2
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class MaxBandwidthHeapG2 {
	
	int status [] = new int[5001];
	int dad [] = new int[5001];
	static int bandWidth [] = new int[5001];
	int path [] = new int [5001];
	
	MaxHeap1 heap = new MaxHeap1();
	ArrayList<Integer> H = new ArrayList<Integer>();
	
	public int[] getMaxBw (int[][] graph , int s ,int t){
		
		int v;
		for( int i = 1 ;i<5001;i++){
			status[i] = -1; //unseen  // for every vertices make them unseen
		 }
		
		status[s] = 1; //make start vertex intree
		dad[s] = -1; //root,start point,without dad
		bandWidth[s] = Integer.MAX_VALUE;
		
		for(int i = 1 ; i<5001 ; i++){ 
			if(graph[s][i] != -1){// for all vertices that linked to vertex s;
			status[i] = 0 ; //fringe
			dad[i] = s;
			bandWidth[i] = graph[s][i];
			heap.insert(H, i ,bandWidth);
		 }
		}
		
		while(H.size()!= 1){ //while heap is not empty (do not count the first element with index 0) , means have fringes
		
			v = heap.Max(H); //find vertex that have max weight, return the name of vertex; 
			int indexMaxV = H.indexOf(v);
			heap.delete(H,indexMaxV,bandWidth);// once it becomes the largest delete it from heap ,status = in_tree;
			status[v] = 1; //intree;
	       
	        for(int i = 1 ; i<5001 ; i++){ 
				if(graph[v][i] != -1){// for all vertices that linked to vertex v;
					
					if (status[i] == 0 && bandWidth[i]<Math.min(bandWidth[v], graph[v][i])){ // if status is unseen
				    	 int c = H.indexOf(i);
				    	 heap.delete(H,c, bandWidth);
				    	 dad[i] =  v;
				    	 bandWidth[i] = Math.min(bandWidth[v], graph[v][i]);
				    	 heap.insert(H,i, bandWidth);
				     }else if(status[i] == -1){ //if status is unseen
					    status[i] = 0; // make them to fringe 
					    dad[i] = v; //set their father to v;
					    bandWidth[i] = Math.min(bandWidth[v], graph[v][i]); //e.weight is weight that from e to v;
					    heap.insert(H,i, bandWidth);
				     } 
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
		
		
		// max bandwidth with heap for graph 2
		long starttime = System.currentTimeMillis();
		Generategraph2 g2 = new Generategraph2();
		ArrayList<Integer> path = new ArrayList<Integer>();
		int[][] finalGraph = g2.buildGraph(g2);
		int s = 168;
		int start = s;
		int t = 2676;
		int temp =t;
		int end = t;
		MaxBandwidthHeapG2 mbw = new MaxBandwidthHeapG2();
		int[] Dad = mbw.getMaxBw(finalGraph, s, t);
		System.out.println("maximum bandwidth is "+bandWidth[t]);
		while(Dad[t]!= s){  
			int pre = Dad[t];
			path.add(pre);
			t = pre;	
		}
		mbw.printReversedList(path, start, end);
		long endtime = System.currentTimeMillis();
		System.out.println(endtime-starttime+ " ms");		
	}
}
/*

// max bandwidth without heap for graph 2
long starttimewithout = System.currentTimeMillis();
MaxBandwidthWithoutHeapG2 mbwNoHeap = new MaxBandwidthWithoutHeapG2();
ArrayList<Integer> pathwithout = new ArrayList<Integer>();
int[] Dadwithout = mbwNoHeap.getMaxBw(finalGraph, s, temp);
System.out.println("maximum bandwidth is "+mbwNoHeap.bandWidth[t]);
while(Dadwithout[temp]!= s){  //当t的父节点不是s的时候，再找t的父节点的父节点，直到找到为止。
	int pre = Dadwithout[temp];
	pathwithout.add(pre);
	temp = pre;	
}
mbwNoHeap.printReversedList(pathwithout, start, end);	
long endtimewithout = System.currentTimeMillis();
System.out.println(endtimewithout-starttimewithout+ " ms");

//max bandwidth kruskal for graph 2
long starttimekruskal = System.currentTimeMillis();
KruskalForG2 obj2 = new KruskalForG2();
obj2.MakeSet(finalGraph);//将图变为一个一个的点，然后通过后续再相连成一个最大生成树
ArrayList<Edge2> d = obj2.orederEdge(finalGraph);
printReversedList(obj2.findPath(obj2.BFS(obj2.MaxST(d),168), 168, 2676),168,2676); //起点是1，终点是23
long endtimekruskal = System.currentTimeMillis();
System.out.println(endtimekruskal-starttimekruskal+ " ms");

*/
/*
testGraph2 gtest = new testGraph2();
int[][] finalGraphtest = gtest.buildGraph(gtest);
ArrayList<Integer> pathtest = new ArrayList<Integer>();
int s = 5;
int start = s;
int t = 10;
int temp =t;
int end = t;
  MaxBandwidthHeapG2 mbwtest = new MaxBandwidthHeapG2();
  int[] Dadtest = mbwtest.getMaxBw(finalGraphtest, s, t);
  System.out.println("maximum bandwidth is "+bandWidth[t]);
  while(Dadtest[t]!= s){  //当t的父节点不是s的时候，再找t的父节点的父节点，直到找到为止。
	int pre = Dadtest[t];
	pathtest.add(pre);
	t = pre;	
}
 mbwtest.printReversedList(pathtest, start, end);
 */


//mbw.getMaxBw(finalGraph, s, t);
		/*
		for(int i = 0 ; i<5001;i++){
			System.out.println(mbw.getMaxBw(finalGraph, s, t)[i]);
		}
		*/


/*
 * 		long endtime = System.currentTimeMillis();
		System.out.println(endtime-starttime+ " ms");
		
		// max bandwidth without heap for graph 2
		long starttimewithout = System.currentTimeMillis();
		MaxBandwidthWithoutHeapG2 mbwNoHeap = new MaxBandwidthWithoutHeapG2();
		ArrayList<Integer> pathwithout = new ArrayList<Integer>();
		int[] Dadwithout = mbwNoHeap.getMaxBw(finalGraph, s, temp);
		while(Dadwithout[temp]!= s){  //当t的父节点不是s的时候，再找t的父节点的父节点，直到找到为止。
			int pre = Dadwithout[temp];
			pathwithout.add(pre);
			temp = pre;	
		}
		mbwNoHeap.printReversedList(pathwithout, start, end);	
		long endtimewithout = System.currentTimeMillis();
		System.out.println(endtimewithout-starttimewithout+ " ms");
		
		//max bandwidth kruskal for graph 2
		long starttimekruskal = System.currentTimeMillis();
		KruskalForG2 obj2 = new KruskalForG2();
		obj2.MakeSet(finalGraph);//将图变为一个一个的点，然后通过后续再相连成一个最大生成树
		ArrayList<Edge2> d = obj2.orederEdge(finalGraph);
		printReversedList(obj2.findPath(obj2.BFS(obj2.MaxST(d),1), 1, 8),1,8); //起点是1，终点是23
		long endtimekruskal = System.currentTimeMillis();
		System.out.println(endtimekruskal-starttimekruskal+ " ms");
 */

