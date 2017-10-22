package Graph;
//已验证，正确 correct
//8432 ms
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class KruskalForG2 {

	//KruskalForG1 obj = new KruskalForG1();
	List<Integer> D = new ArrayList<Integer>();
	List<Integer> Rank = new ArrayList<Integer>();
	
	//sort the edges by heap sort;
	//for graph1, edges a which connect i and j is presented by for(Edge e :g.get(i))
	public ArrayList<Edge2> orederEdge(int[][] g){ //g is a graph.  g.[i][j] is all edge that linked to i. 
		maxHeapListWeight H = new maxHeapListWeight(); // create a object of maxHeapListWeight, use this object to use  HeapSort (ArrayList<Integer> heap, int[] weight) function
		ArrayList<Edge2> heap = new ArrayList<Edge2>();  //create a heap to store edge name
		ArrayList<Edge2> edgeInfo = new ArrayList<Edge2>();
		ArrayList<Integer> weight = new ArrayList<Integer>();
		//Map<Integer,LinkedList<Edge>> tree = new HashMap<Integer,LinkedList<Edge>>();
		ArrayList<Edge2> edgepath = new ArrayList<Edge2>();
		int count = 0;
		edgeInfo.add(new Edge2(0,0,0,0));
		int edgenum = 0;
		for(int i=1 ; i<5001;i++){
			for(int j=1 ; j<5001;j++){
			if(j>i && g[i][j]!=-1){
			edgenum++;
			edgeInfo.add(new Edge2(j,g[i][j],i,edgenum));
			  // this store the information of a edge, ei=[ui,vi], including (ui=i), (vi=e.vertex), (weight=e.weight)
			H.insert(heap,edgeInfo.get(edgenum), weight);// insert edge,Edge2 class
			}
			}
		}
		 // this will return a sequence of edge with kind Edge
		int size = g.length;
		while(count < size-2){
		  for(Edge2 e : H.HeapSort(heap, weight)){ //total v-1 edges
			int r1 = Find(e.linkedwith); //find root for vertex e.linkedwith (ui)
			int r2 = Find(e.vertex); ////find root for vertex e.vertex (vi)
			if(r1!=r2){
			 count++;
			 edgepath.add(e);   //store edges
			 edgepath.add(new Edge2(e.linkedwith, e.weight ,e.vertex,e.edgenum+1));
			 union(r1,r2);
			}
		 }
			
		}
		return edgepath;
	}
	
	public void MakeSet(int[][] g){
		D.add(0);
		Rank.add(0);
		for(int v=1 ;v<5001;v++){
			D.add(v, 0);  //makeSet first set all vertices' father 0;
			Rank.add(v, 0);
		}
	}
	
	public List<Integer> union(int a, int b){
		if(Rank.get(a) > Rank.get(b)){
			D.set(b, a);
			//Rank.set(a,Rank.get(a)+1); 
		}else if(Rank.get(a) < Rank.get(b)){
			D.set(a, b);
			//Rank.set(b,Rank.get(b)+1);
		}else{
			D.set(b, a); //b dad is a
			Rank.set(a,Rank.get(a)+1);  //a's rank 2
		}
		return D;
	}
	
	public int Find(int v){
		int w = v;
		//List<Integer> s = new ArrayList<Integer>();
		while(D.get(w)!=0){
			w = D.get(w);
		}
		return w;
	}

	public Map<Integer,ArrayList<Integer>>  MaxST(ArrayList<Edge2> d){
		
		Map<Integer,ArrayList<Integer>> MaxST = new HashMap<Integer,ArrayList<Integer>>();
		for(int i1=0 ; i1<5001; i1++){
			ArrayList<Integer> ELi = new ArrayList<Integer>();
			for (Iterator<Edge2> it = d.iterator(); it.hasNext();) {
				     Edge2 value = it.next();
				      if (value.linkedwith == i1) {
				    	  ELi.add(value.vertex);  // ok
				     }
				}
			MaxST.put(i1, ELi);
		}
		//System.out.println(MaxST);
		return MaxST;
	}
	
	public int []  BFS(Map<Integer,ArrayList<Integer>> g ,int s ){
        // set a dad array to represent all vertices's dad in dfs
    	int [] dad = new int[5001];
        // Mark all the vertices as not visited(By default
        // set as false)
        boolean visited[] = new boolean[5001];
 
        // Create a queue for BFS
        LinkedList<Integer> queue = new LinkedList<Integer>();
 
        // Mark the current node as visited and enqueue it
        visited[s]=true;
        queue.add(s);
        dad[s] = s;
        while (queue.size() != 0)
        {
            // Dequeue a vertex from queue and print it
            s = queue.poll();
            //System.out.print(s+" ");
            // Get all adjacent vertices of the dequeued vertex s
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
            for(Integer e : g.get(s))
             
                if (!visited[e])
                {
                    visited[e] = true;
                    queue.add(e);
                    dad[e] = s;
                }
            }
        /*
        for(int i=0;i<5001;i++){
          System.out.println(dad[i]);
        }
        */
        return dad;
        }
	
	public ArrayList<Integer> findPath(int [] dad ,int s, int t){
		
		ArrayList<Integer> result = new ArrayList<Integer>();
		int temp;
		while(dad[t]!=s){
			temp = dad[t];
			t = temp;
			result.add(temp);
		}
		return result;
	}
	
	public static void printReversedList(List link,int s,int t) {  
	        System.out.println("from "+s+" to "+t+" we need go through follow vertices");  
	       //iterator for list, point to last position  
	        ListIterator li = link.listIterator(link.size());  
	       // judge whether it has previous vertex or not 
	        while (li.hasPrevious()) {  
	        
	            System.out.print(li.previous() + " ");  
	        }  
	        System.out.println();  
	    } 
	
	
	 public static void main(String[] args) {
		 
		long starttime = System.currentTimeMillis();
		Generategraph2 g2 = new Generategraph2();
		KruskalForG2 obj2 = new KruskalForG2();
		testGraph2 g2test = new testGraph2();
		int[][] graph2 = g2.buildGraph(g2);
		int[][] graph2test = g2test.buildGraph(g2test);
		obj2.MakeSet(graph2);
		ArrayList<Edge2> d = obj2.orederEdge(graph2);
		printReversedList(obj2.findPath(obj2.BFS(obj2.MaxST(d),7), 7, 10),7,10);
		long endtime = System.currentTimeMillis();
		System.out.println(endtime-starttime+ " ms");
	} 
	
}

/*
testGraph2 g2test = new testGraph2();
int[][] testGraph2 = g2test.buildGraph(g2test);
obj2.MakeSet(testGraph2);
ArrayList<Edge2> dtest = obj2.orederEdge(testGraph2);
for(int i1=0 ; i1< dtest.size(); i1++){
System.out.println(dtest.get(i1).linkedwith + " 后接 " +dtest.get(i1).vertex);
*/
