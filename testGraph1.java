package Graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class testGraph1 {

		
    public Map<Integer,LinkedList<Edge>> buildGraph(){
			Map<Integer,LinkedList<Edge>> graph = new HashMap<Integer,LinkedList<Edge>>();
			LinkedList<Edge> edge1 = new LinkedList<Edge>();
			edge1.add(new Edge(2,4));
			edge1.add(new Edge(3,1));
			edge1.add(new Edge(4,4));
			graph.put(1, edge1);
			LinkedList<Edge> edge2 = new LinkedList<Edge>();
			edge2.add(new Edge(1,4));
			edge2.add(new Edge(3,5));
			edge2.add(new Edge(5,9));
			edge2.add(new Edge(6,9));
			edge2.add(new Edge(7,7));
			graph.put(2, edge2);
			LinkedList<Edge> edge3 = new LinkedList<Edge>();
			edge3.add(new Edge(1,1));
			edge3.add(new Edge(2,5));
			edge3.add(new Edge(4,3));
			edge3.add(new Edge(7,9));
			graph.put(3, edge3);
			LinkedList<Edge> edge4 = new LinkedList<Edge>();
			edge4.add(new Edge(1,4));
			edge4.add(new Edge(3,3));
			edge4.add(new Edge(7,10));
			edge4.add(new Edge(9,18));
			graph.put(4, edge4);
			LinkedList<Edge> edge5 = new LinkedList<Edge>();
			edge5.add(new Edge(2,9));
			edge5.add(new Edge(6,2));
			edge5.add(new Edge(8,4));
			edge5.add(new Edge(10,6));
			graph.put(5, edge5);
			LinkedList<Edge> edge6 = new LinkedList<Edge>();
			edge6.add(new Edge(2,9));
			edge6.add(new Edge(5,2));
			edge6.add(new Edge(7,8));
			edge6.add(new Edge(8,2));
			graph.put(6, edge6);
			LinkedList<Edge> edge7 = new LinkedList<Edge>();
			edge7.add(new Edge(2,7));
			edge7.add(new Edge(3,9));
			edge7.add(new Edge(4,10));
			edge7.add(new Edge(6,8));
			edge7.add(new Edge(8,9));
			edge7.add(new Edge(9,8));
			graph.put(7, edge7);
			LinkedList<Edge> edge8 = new LinkedList<Edge>();
			edge8.add(new Edge(5,4));
			edge8.add(new Edge(6,2));
			edge8.add(new Edge(7,9));
			edge8.add(new Edge(9,9));
			edge8.add(new Edge(10,3));
			//edge7.add(new Edge(9,8));
			graph.put(8, edge8);
			LinkedList<Edge> edge9 = new LinkedList<Edge>();
			edge9.add(new Edge(4,18));
			edge9.add(new Edge(7,8));
			edge9.add(new Edge(8,9));
			edge9.add(new Edge(10,9));
			graph.put(9, edge9);
			LinkedList<Edge> edge10 = new LinkedList<Edge>();
			edge10.add(new Edge(5,6));
			edge10.add(new Edge(5,3));
			edge10.add(new Edge(9,9));
			graph.put(10, edge10);
			return graph;
				
		}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testGraph1 graph1 = new testGraph1();
		//graph1.buildGraph();
		System.out.println(graph1.buildGraph());

	}

}

