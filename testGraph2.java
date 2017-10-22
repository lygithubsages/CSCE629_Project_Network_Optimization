package Graph;

public class testGraph2 {
	
	 int vertiesNum = 7;
	    int vertex[];
	    int graph[][];
	    
	public testGraph2(){
	    	int vertex[] = new int [11];
	    	int[][] graph = new int[11][11];
	    	this.graph = graph;
	    	this.vertex = vertex;
	    	
	    }
	
	public int[][] buildGraph(testGraph2 g){
		for(int i = 0 ;i < 10 ;i++){
			for(int j=0 ;j < 10;j++){
				g.graph[i][j] = -1;
			}
		}
		
		g.graph[1][2]=g.graph[2][1] = 4;
		g.graph[1][3]=g.graph[3][1] = 1;
		g.graph[1][4]=g.graph[4][1] = 4;
		g.graph[2][5]=g.graph[5][2] = 9;
		g.graph[2][6]=g.graph[6][2] = 9;
		g.graph[2][7]=g.graph[7][2] = 7;
		g.graph[2][3]=g.graph[3][2] = 5;
		g.graph[3][4]=g.graph[4][3] = 3;
		g.graph[3][7]=g.graph[7][3] = 9;
		g.graph[4][7]=g.graph[7][4] = 10;
		g.graph[4][9]=g.graph[9][4] = 18;
		g.graph[5][6]=g.graph[6][5] = 2;
		g.graph[5][8]=g.graph[8][5] = 4;
		g.graph[5][10]=g.graph[10][5] = 6;
		g.graph[6][7]=g.graph[7][6] = 8;
		g.graph[7][8]=g.graph[8][7] = 9;
		g.graph[7][9]=g.graph[9][7] = 8;
		g.graph[8][9]=g.graph[9][8] = 9;
		g.graph[9][10]=g.graph[10][9] = 9;
		
		return g.graph;
	}
	
	public void printGraph(testGraph2 g)  
    {   
        System.out.print("        ");  
        for(int i=1;i<11;i++)  
        {  
            System.out.print(i+"     ");  
        }  
         
        for(int i=1;i<11;i++)  
        {  
            System.out.println();     
            System.out.print(i+"      ");  
            for(int j=1;j<11;j++)  
            {  
                System.out.print(g.graph[i][j]+"     ");  
            } 
            //System.out.print(g.graph[i][0]+"     ");  test code
              
        } 
    }  
			
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			testGraph2 g = new testGraph2();
			g.buildGraph(g);
			g.printGraph(g);

	}

}
