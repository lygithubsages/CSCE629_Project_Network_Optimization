
package Graph;
import java.util.*;

public class maxHeapListWeight {
	
	//static KruskalForG1 obj = new  KruskalForG1();
	
	public static void insert(ArrayList<Edge2> heap, Edge2 v, ArrayList<Integer> weight){
		if(weight.size() == 0){
			weight.add(0);
		}
		if(heap.size()==0){
			heap.add(new Edge2(0,0,0,0));
		}
		heap.add(v);
		weight.add(v.edgenum,v.weight);
		pushUp(heap,heap.size()-1,weight);
	}
	
	public static void pushUp(ArrayList<Edge2> heap, int i, ArrayList<Integer> weight){
		//
		if(i>1){
			int parent = i/2;
			int parentValue = (Integer) weight.get(heap.get(parent).edgenum);// heap.get(parent) 得到parent edge, parent edge 的值，通过weight得到。 
			                                                                //在weight当中，parent 的weight存储在weight的第parent 处
			int iValue = (Integer) weight.get(heap.get(i).edgenum);
			if(iValue > parentValue){ //if i is larger than his parents.
				swap(heap,parent,i);
				pushUp(heap,parent,weight);
			}
		}
	}
	
	public static void swap(ArrayList<Edge2> heap ,int a ,int b){
		Edge2 temp =  heap.get(a); //将 heap 上第 a 个节点上的 edge 换到 第 b 个节点上
		heap.set(a, heap.get(b));
		heap.set(b, temp);
	}
	
	public static void delete(ArrayList<Edge2> heap, int i, ArrayList<Integer> weight){
		heap.set(i, heap.get(heap.size()-1));
		pushDown(heap, i, weight);
		heap.remove(heap.size()-1);
	}

	public static void pushDown(ArrayList<Edge2> heap , int i , ArrayList<Integer> weight ){
		int n =heap.size()-2;
		int child = -1;
		if(2*i> n){
			return;
		}else if(2*i<n){
			child = 2* i;
			if((Integer) weight.get(heap.get(child).edgenum)< (Integer) weight.get(heap.get(child+1).edgenum)){
				child++;
			}
		}else if(2*i == n){
			child = 2*i;
		}
		if((Integer) weight.get(heap.get(child).edgenum)>(Integer)weight.get(heap.get(i).edgenum)){
			swap(heap,child,i);
			pushDown(heap,child,weight);
		}
	}
	
	public static Edge2 Max(ArrayList<Edge2> heap){
		return heap.get(1);
	}
	
	public static ArrayList<Edge2> HeapSort (ArrayList<Edge2> heap,ArrayList<Integer> weight){
		ArrayList<Edge2> sorted = new ArrayList<Edge2>();
		while(heap.size()!=1){
		maxHeapListWeight heapSort = new maxHeapListWeight();
		Edge2 temp = heap.get(1);
		sorted.add(temp);
		heapSort.delete(heap, 1, weight);
		}
		return sorted;
	}
	
	public static void main(String[] args) {
		
		//following are basic test codes.
		
		// TODO Auto-generated method stub
		maxHeapListWeight heapGo = new maxHeapListWeight();
		ArrayList<Edge2> H = new ArrayList<Edge2>();
		//int[] weight1 = {-1,5,17,1,33,4,2,3,16,23};
		//List l = Arrays.asList(weight1); 
		ArrayList<Integer> weight = new ArrayList<Integer>();
		//weight.add(-1);//weight.add(5);weight.add(17);weight.add(1);weight.add(3);weight.add(4);weight.add(2);weight.add(3);weight.add(16);weight.add(23);
		/*
		heapGo.insert(H, new Edge2(1,5,6,1), weight);
		heapGo.insert(H, new Edge2(2,17,6,2), weight);
		heapGo.insert(H, new Edge2(4,18,2,3), weight);
		heapGo.insert(H, new Edge2(3,29,7,4), weight);
		heapGo.insert(H, new Edge2(4,4,7,5), weight);
		heapGo.insert(H, new Edge2(7,2,9,6), weight);
	    heapGo.insert(H, new Edge2(7,3,7,7), weight);
		heapGo.insert(H, new Edge2(8,16,6,8), weight);
		heapGo.insert(H, new Edge2(6,23,5,9), weight);
		heapGo.insert(H, new Edge2(9,2,7,10), weight);
		heapGo.insert(H, new Edge2(8,13,6,11), weight);
		heapGo.insert(H, new Edge2(3,23,5,12), weight);
		*/
		ArrayList<Edge2> edgeInfo = new ArrayList<Edge2>();
		edgeInfo.add(new Edge2(0,0,0,0));
		testGraph1 gtest = new testGraph1();
		Map<Integer,LinkedList<Edge>> g2 = gtest.buildGraph();
		ArrayList<Edge2> heap = new ArrayList<Edge2>(); 
		int edgenum = 0;
		for(int i=1 ; i<11;i++){
			for(Edge e: g2.get(i)){
				if(e.vertex>i){
					edgenum++;
					edgeInfo.add(new Edge2(e.vertex,e.weight,i,edgenum));
					  // this store the information of a edge, ei=[ui,vi], including (ui=i), (vi=e.vertex), (weight=e.weight)
				 	
					insert(heap,edgeInfo.get(edgenum), weight);// insert 插得是一个edge,包含Edge2中的三个信息，
				}
			}
			
		}

		System.out.println(heapGo.Max(heap).vertex);
		System.out.println(edgenum);
		System.out.println(weight);

		for(Edge2 e : heapGo.HeapSort(heap, weight)){
			System.out.println(e.vertex+" " + "linked with" +" "+e.linkedwith +" "+ "with weight" +" "+e.weight);
		}
	}	
}
