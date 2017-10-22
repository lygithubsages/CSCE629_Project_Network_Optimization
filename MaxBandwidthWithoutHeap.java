package Graph;
/*已验证，正确 correct */
//164ms
//max bandwidth without heap dijstra for graph1
import java.util.*;
public class MaxBandwidthWithoutHeap {

	int status [] = new int[5001];
	int dad [] = new int[5001];
	static int bandWidth [] = new int[5001];
	
	ArrayList<Edge> fringe = new ArrayList<Edge>();
	
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
			fringe.add(e);
		}
		while(!fringe.isEmpty()){
		
		//find the vertex with maxBandwidth
			int maxValue = 0;
            int index = 0;
            for (int i = 0; i < fringe.size() ; i++)
            {
                if (fringe.get(i).weight > maxValue)
                {
                    maxValue = fringe.get(i).weight;
                    index = i;
                }
            }
            status[fringe.get(index).vertex] = 1 ;// intree
            int largestV = fringe.get(index).vertex; //largestV is the vertex that have the max bandwidth connected to s;
            // for vertices that linked to largestV
            fringe.remove(index);
            for(Edge e : graph.get(largestV)){
            	if( status[e.vertex] == -1) {//if unseen
            		status[e.vertex] = 0; //set status to fringe
            		dad[e.vertex] = largestV;
            		bandWidth[e.vertex] = Math.min(bandWidth[largestV], e.weight);
            		e.weight = Math.min(bandWidth[largestV], e.weight);
            		fringe.add(e);
            	}else if (status[e.vertex] == 0 && bandWidth[e.vertex]< Math.min(bandWidth[largestV], e.weight)){// if is fringe
            		Edge e1 = new Edge(e.vertex ,bandWidth[e.vertex]);
            		int deleteIndex=0;
            		for(int i=0 ; i<fringe.size();i++){
            			if(fringe.get(i).vertex == e.vertex && fringe.get(i).weight == bandWidth[e.vertex]){
            				deleteIndex = i;
            			}
            		}
            		fringe.remove(deleteIndex);
            		dad[e.vertex] = largestV;
            		bandWidth[e.vertex] = Math.min(bandWidth[largestV], e.weight);
            		e.weight = bandWidth[e.vertex];
            		fringe.add(e);
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
            // return previous element
            System.out.print(li.previous() + " ");  
        }  
        System.out.println();  
    }  


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long starttime = System.currentTimeMillis();
		ArrayList<Integer> path = new ArrayList<Integer>();
		testGraph1 gtest = new testGraph1();
		Map<Integer,LinkedList<Edge>> finalGraphtest = gtest.buildGraph();
		MaxBandwidthWithoutHeap mbwNoHeap = new MaxBandwidthWithoutHeap();
		int s = 5;
		int start = s;
		int t = 10;
		int end = t;
		int[] Dad = mbwNoHeap.getMaxBw(finalGraphtest, s, t);
		System.out.println("maximum bandwidth is "+ bandWidth[t]);
		while(Dad[t]!= s){  
			int pre = Dad[t];
			
			path.add(pre);
			t = pre;	
		}
		mbwNoHeap.printReversedList(path,start,end);
		long endtime = System.currentTimeMillis();
		System.out.println(endtime-starttime+" ms");	
	}

}

//mbw.getMaxBw(finalGraph, s, t);
/*
for(int i = 1 ; i<5001;i++){
	System.out.println(mbwNoHeap.getMaxBw(finalGraph, s, t)[i]);
}
*/
/*
ArrayList<Integer> path = new ArrayList<Integer>();
testGraph1 gtest = new testGraph1();
Map<Integer,LinkedList<Edge>> finalGraphtest = gtest.buildGraph();
int s = 1;
int start = s;
int t = 7;
int end = t;
int[] Dad = mbwNoHeap.getMaxBw(finalGraphtest, s, t);

while(Dad[t]!= s){  //当t的父节点不是s的时候，再找t的父节点的父节点，直到找到为止。
	int pre = Dad[t];
	
	path.add(pre);
	t = pre;	
}
mbwNoHeap.printReversedList(path,start,end);
*/
/*
GenerateGraph1 g1 = new GenerateGraph1();
ArrayList<Integer> path = new ArrayList<Integer>();
Map<Integer,LinkedList<Edge>> finalGraph = g1.buildGraph();
int s = 1;
int start =s;
int t = 23;
int end =t;
MaxBandwidthWithoutHeap mbwNoHeap = new MaxBandwidthWithoutHeap();
int[] Dad = mbwNoHeap.getMaxBw(finalGraph, s, t);
System.out.println("maximum bandwidth is "+bandWidth[t]);
while(Dad[t]!= s){  //当t的父节点不是s的时候，再找t的父节点的父节点，直到找到为止。
	int pre = Dad[t];
	
	path.add(pre);
	t = pre;	
}
mbwNoHeap.printReversedList(path,start,end);
*/
