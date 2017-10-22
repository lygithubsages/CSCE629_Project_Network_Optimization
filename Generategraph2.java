package Graph;
import java.util.*;
// 4612ms
public class Generategraph2 {
	
    int vertiesNum = 5000;
    int vertex[];
    int graph[][];
    public Generategraph2(){
    	int vertex[] = new int [5001];
    	int[][] graph = new int[5001][5001];
    	this.graph = graph;
    	this.vertex = vertex;
    	
    }
	
	public int[][] buildGraph(Generategraph2 g){
		
		for(int i=1;i<5001;i++){		
			for(int j=1;j<5001;j++)
			{
				g.graph[i][j]=g.graph[j][i] = g.randomWeight();
				g.graph[i][i]=-1;
			}
		}
		return g.graph;
		
	}
	 public int randomWeight(){
		 int w = 0;
		 Map<String, Integer> keyChanceMap = new HashMap<String, Integer>();  
	     keyChanceMap.put("with_weight", 20);  
	     keyChanceMap.put("without_weight", 80);
	     String key = RandomChance.chanceSelect(keyChanceMap);
	     if (key=="with_weight"){
	    	w = new Random().nextInt(100000);
	     }
	     if(key=="without_weight"){
	    	w = -1;
	    }
	     return w;
	     }
	     
     //first method to print graph
	 public void printMatrix(int[][] m){
	     try{
	         int rows = m.length;
	         int columns = m[0].length;
	         String str = "|\t";

	         for(int i=0;i<rows;i++){
	             for(int j=0;j<columns;j++){
	                 str += m[i][j] + "\t";
	             }

	             System.out.println(str + "|");
	             str = "|\t";
	         }

	     }catch(Exception e){System.out.println("Matrix is empty!!");}
	 }
	// second method to print graph
    public void printGraph(Generategraph2 g)  
    {   
        System.out.print("        ");  
        for(int i=1;i<5001;i++)  
        {  
            System.out.print(i+"     ");  
        }  
         
        for(int i=1;i<5001;i++)  
        {  
            System.out.println();     
            System.out.print(i+"      ");  
            for(int j=1;j<5001;j++)  
            {  
                System.out.print(g.graph[i][j]+"     ");  
            } 
            //System.out.print(g.graph[i][0]+"     ");  test code
              
        } 
    }  

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long start = System.currentTimeMillis();
		Generategraph2 g = new Generategraph2();
		/*for(int i = 1; i<=100 ;i++){
			int a = g.randomWeight();
			System.out.println(a);
		}*/
		g.randomWeight();
		g.buildGraph(g);
		long end = System.currentTimeMillis();
		g.printGraph(g);
		System.out.println(end-start);

	}
}

class RandomChance{
	
	  public static String chanceSelect (Map<String,Integer> keyChanceMap) { 
		 
       if(keyChanceMap == null || keyChanceMap.size() == 0)  
            return null;  
         
       Integer sum = 0;  
       for (Integer value : keyChanceMap.values()) {  
            sum += value;  
       }  
       // start from 1  
       Integer rand = new Random().nextInt(sum) + 1;  
         
       for (Map.Entry<String, Integer> entry : keyChanceMap.entrySet()) {  
            rand -= entry.getValue();  
         
            if(rand <= 0) {  
                 return entry.getKey();  
            }  
       }  
         
       return null;  
  }
  }
