package Graph;
import java.util.*;
//37 ms
class Edge{
	int vertex;
    int weight;
    
    public Edge(int vertex, int weight){
    	this.vertex = vertex;
    	this.weight = weight;
    }
    @Override
	public String toString(){
		return "("+vertex+","+weight+")";
	}
}

	public class GenerateGraph1 {
	
	public Map<Integer,LinkedList<Edge>> buildGraph(){
		
		int[] shuffleReule = randomArray(1,5000,5000);
		Map<Integer,LinkedList<Edge>> graph = new HashMap<Integer,LinkedList<Edge>>();
			int i;
			for( i=1 ; i<=5000 ; i++){
		
				LinkedList<Edge> neighbor = new LinkedList<Edge>();
		
		// 1 connect to 2，3，4，10，9，8 ；2 connect to 3，4，5，1，10，9； 3 connect to 4，5，6，2，1，10；9连 10，1，2，8，7，6 
					for( int j = 1; j<=3 ;j++){
						int a = i+j, b= i-j;
						if(a>5000)
							a=a-5000;
						if(b<=0)
							b=5000+b;
						neighbor.add(0,new Edge(shuffleReule[a-1],new Random().nextInt(100)));//add vertex 2 as i's neighbor with weight 3
						neighbor.add(0,new Edge(shuffleReule[b-1],new Random().nextInt(100)));
		}
		graph.put(shuffleReule[i-1], neighbor);
	}
	// let connected vertices have same weight
	for(Integer a= 1 ;a<=5000 ;a++){
		for(Edge n : graph.get(shuffleReule[a-1])){
			Integer b =n.vertex;
			for(Edge v : graph.get(b)){
				if (v.vertex == shuffleReule[a-1]){
					v.weight = n.weight;
					break;
				}
			}
		}
	}	  
	return graph;
	}

	//generate shuffle Reule
	public static int[] randomArray(int min,int max,int n){  
	    int len = max-min+1;  
	      
	    if(max < min || n > len){  
	        return null;  
	    }  
	      
	    //initialize array's scope  
	    int[] source = new int[len];  
	       for (int i = min; i < min+len; i++){  
	        source[i-min] = i;  
	       }  
	         
	       int[] result = new int[n];  
	       Random rd = new Random();  
	       int index = 0;  
	       for (int i = 0; i < result.length; i++) {  
	         
	           index = Math.abs(rd.nextInt() % len--);  
	            
	           result[i] = source[index];  
	          
	           source[index] = source[len];  
	       }  
	       return result;  
	}  
		
	
	public static void main(String[] args) {
    long start = System.currentTimeMillis();	
     GenerateGraph1 g = new GenerateGraph1();
     //DO NOT JUST USE G.BUILDGRAPH!!!!!!!!! 
     Map<Integer,LinkedList<Edge>> finalGraph = g.buildGraph();

    long end = System.currentTimeMillis();
   //print result of graph
	for (Iterator<Integer> it = finalGraph.keySet().iterator(); it.hasNext();) {  
	            Integer key = (Integer) it.next();  
	            System.out.println(key + "==>" + finalGraph.get(key));  
	        } 
	//print cost time
	System.out.println(end-start +" ms");
	      
        } 
	}

