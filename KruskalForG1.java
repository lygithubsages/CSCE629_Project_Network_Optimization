package Graph;
import java.util.*;
// 已验证，正确 correct
// 1031 ms
class Edge2{
	int vertex;
    int weight;
    int linkedwith;
    int edgenum;
  
    public Edge2(int vertex,int weight,int linkedwith,int edgenum){
    	this.vertex = vertex;
    	this.weight = weight;
    	this.linkedwith = linkedwith;
    	this.edgenum = edgenum;
    	
    }
}

public class KruskalForG1 {
	
	//KruskalForG1 obj = new KruskalForG1();
	List<Integer> D = new ArrayList<Integer>();
	List<Integer> Rank = new ArrayList<Integer>();
	
	//sort the edges by heap sort;
	//for graph1, edges a which connect i and j is presented by for(Edge e :g.get(i))
	public ArrayList<Edge2> orederEdge(Map<Integer,LinkedList<Edge>> g){ //g is a graph.  g.get(i) is all edge that linked to i. 
		
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
			for(Edge e: g.get(i)){
			if(e.vertex>i){
			edgenum++;
			edgeInfo.add(new Edge2(e.vertex,e.weight,i,edgenum));
		
			H.insert(heap,edgeInfo.get(edgenum), weight);
			}
			}
		}
		 // this will return a sequence of edge with kind Edge
		int size = g.keySet().size();
		while(count < size-1){
		  for(Edge2 e : H.HeapSort(heap, weight)){ 
	
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
	
	public void MakeSet(Map<Integer,LinkedList<Edge>> g){
		D.add(0);
		Rank.add(0);
		for(Integer v :g.keySet()){
			D.add(v, 0);  //makeSet first set all vertices' father 0;
			Rank.add(v, 0);
		}
	}
	
	public List<Integer> union(int a, int b){
		if(Rank.get(a) > Rank.get(b)){
			D.set(b, a);
			
		}else if(Rank.get(a) < Rank.get(b)){
			D.set(a, b);
			
		}else{
			D.set(b, a); 
			Rank.set(a,Rank.get(a)+1);  
		}
		return D;
	}
	
	public int Find(int v){
		int w = v;
		
		while(D.get(w)!=0){
			w = D.get(w);
		}
		return w;
	}

	public Map<Integer,ArrayList<Integer>> MaxST(ArrayList<Edge2> d){
		
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
		return MaxST;
		//System.out.println(MaxST);
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

        return dad;
        }
	
	public static void printReversedList(List link,int s,int t) {  
        System.out.println("from "+ s +" to " +t);  
      //iterator for list, point to last position  
        ListIterator li = link.listIterator(link.size());  
      // judge whether it has previous vertex or not  
        while (li.hasPrevious()) {  
         
            System.out.print(li.previous() + " ");  
        }  
        System.out.println();  
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long starttime = System.currentTimeMillis();
		GenerateGraph1 g = new GenerateGraph1();
		//testGraph1 gtest = new testGraph1();
		KruskalForG1 obj2 = new KruskalForG1();
		Map<Integer,LinkedList<Edge>> g1 = g.buildGraph();
		//Map<Integer,LinkedList<Edge>> g2 = gtest.buildGraph();
		obj2.MakeSet(g1);
		ArrayList<Edge2> d = obj2.orederEdge(g1);
	    //do BFS on MaxST
	    obj2.BFS(obj2.MaxST(d),7);
	    //find path from BFS
	    printReversedList(obj2.findPath(obj2.BFS(obj2.MaxST(d),7), 7, 10),7,10);	
	    long endtime = System.currentTimeMillis();
		System.out.println(endtime-starttime+" ms");
        
		}

	}




