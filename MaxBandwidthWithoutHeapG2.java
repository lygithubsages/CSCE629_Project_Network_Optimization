package Graph;
//已验证，正确 correct
//5063ms
//max bandwidth without heap dijstra for graph2
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Random;

public class MaxBandwidthWithoutHeapG2 {
	int status [] = new int[5001];
	int dad [] = new int[5001];
	static int bandWidth [] = new int[5001];
	
	ArrayList<Edge> fringe = new ArrayList<Edge>();
	
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
			Edge e = new Edge(i,bandWidth[i]);
			fringe.add(e);
		 }
		}

		while(!fringe.isEmpty()){
		
		//find the vertex with maxBandwidth
            int index = 0;
            int maxValue = 0;
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
            fringe.remove(index);
            // for vertices that linked to largestV
	        for(int i = 1 ; i<5001 ; i++){ 
				if(graph[largestV][i] != -1){// for all vertices that linked to vertex v;
					if(status[i] == -1){ //if status is unseen
					    status[i] = 0; // make them to fringe 
					    dad[i] = largestV; //set their father to v;
					    bandWidth[i] = Math.min(bandWidth[largestV], graph[largestV][i]); //e.weight is weight that from e to v;
					    Edge e = new Edge(i,bandWidth[i]);
					    fringe.add(e);
				     }
					else if (status[i] == 0 && bandWidth[i]<Math.min(bandWidth[largestV], graph[largestV][i])){ // if status is unseen
				    	 Edge e = new Edge(i,bandWidth[i]);
				    	 int deleteIndex=0;
		            	 for(int j=0 ; j<fringe.size();j++){
		            			if(fringe.get(j).vertex == i && fringe.get(j).weight == bandWidth[e.vertex] ){
		            				deleteIndex = j;
		            			}
		            		}
		            	 fringe.remove(deleteIndex);
				    	 //fringe.remove(fringe.indexOf(e));
				    	 dad[e.vertex] = largestV;
		            	 bandWidth[i] = Math.min(bandWidth[largestV], graph[largestV][i]);
		            	 e.weight =  bandWidth[i];
		            	 fringe.add(e);				    	 
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
		// TODO Auto-generated method stub
		Generategraph2 g1 = new Generategraph2();
		long starttime = System.currentTimeMillis();
		testGraph2 gtest = new testGraph2();
		int[][] finalGraphtest = g1.buildGraph(g1);
		ArrayList<Integer> path = new ArrayList<Integer>();
		int s = 1;
		int start = s;
		int t = 8;
		int end = t;
		 
		MaxBandwidthWithoutHeapG2 mbw = new MaxBandwidthWithoutHeapG2();
		int[] Dad = mbw.getMaxBw(finalGraphtest, s, t);
		System.out.println("maximum bandwidth is "+bandWidth[t]);
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

/*
testGraph2 gtest = new testGraph2();
int[][] finalGraphtest = gtest.buildGraph(gtest);
ArrayList<Integer> path = new ArrayList<Integer>();
int s = 1;
int start = s;
int t = 6;
int end = t;
MaxBandwidthWithoutHeapG2 mbw = new MaxBandwidthWithoutHeapG2();
int[] Dad = mbw.getMaxBw(finalGraphtest, s, t);
  
  while(Dad[t]!= s){  //当t的父节点不是s的时候，再找t的父节点的父节点，直到找到为止。
	int pre = Dad[t];
	path.add(pre);
	t = pre;	
}
  mbw.printReversedList(path, start, end);
  */


//mbw.getMaxBw(finalGraph, s, t);
/*
for(int i = 1 ; i<5001;i++){
	System.out.println(mbwNoHeap.getMaxBw(finalGraph, s, t)[i]);
}
*/
