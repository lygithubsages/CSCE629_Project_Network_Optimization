package Graph;
import java.util.*;
public class MaxHeap1 {
	
	public static void insert(ArrayList<Integer> heap,int v, int[] weight){
		
		if(heap.size()==0){
			heap.add(0);
		}
		
		heap.add(v);
		pushUp(heap,heap.size()-1,weight);
	}
	
	public static void pushUp(ArrayList<Integer> heap, int i, int[] weight){
		//
		if(i>1){
			int parent = i/2;
			int parentValue = (Integer) weight[heap.get(parent)];
			int iValue = (Integer) weight[heap.get(i)];
			if(iValue > parentValue){ //if i is larger than his parents.
				swap(heap,parent,i);
				pushUp(heap,parent,weight);
			}
		}
	}
	
	public static void swap(ArrayList<Integer> heap ,int a ,int b){
		int temp = (Integer) heap.get(a);
		heap.set(a, heap.get(b));
		heap.set(b, temp);
	}
	
	public static void delete(ArrayList<Integer> heap,int i, int[] weight){
		heap.set(i, heap.get(heap.size()-1));
		pushDown(heap, i, weight);
		heap.remove(heap.size()-1);
	}

	public static void pushDown(ArrayList<Integer> heap , int i , int[] weight){
		int n =heap.size()-2;
		int child = -1;
		if(2*i> n){
			return;
		}else if(2*i<n){
			child = 2* i;
			if((Integer) weight[heap.get(child)]<(Integer) weight[heap.get(child+1)]){
				child++;
			}
		}else if(2*i == n){
			child = 2*i;
		}
		if((Integer) weight[heap.get(child)]>(Integer)weight[heap.get(i)]){
			swap(heap,child,i);
			pushDown(heap,child,weight);
		}
	}
	
	public static int Max(ArrayList<Integer> heap){
		return heap.get(1);
	}
	
	public static ArrayList<Integer> HeapSort (ArrayList<Integer> heap,int[] weight){
		ArrayList<Integer> sorted = new ArrayList<Integer>();
		while(heap.size()!=1){
		MaxHeap1 heapSort = new MaxHeap1();
		Integer temp = heap.get(1);
		sorted.add(temp);
		heapSort.delete(heap, 1, weight);
		}
		return sorted;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MaxHeap1 heapGo = new MaxHeap1();
		ArrayList<Integer> H = new ArrayList<Integer>();
		int[] weight = {-1,5,17,1,3,4,2,3,16,23};
		//heapGo.insert(H, -1, weight);
		heapGo.insert(H, 1, weight);
		heapGo.insert(H, 2, weight);
		heapGo.insert(H, 3, weight);
		heapGo.insert(H, 4, weight);
		heapGo.insert(H, 5, weight);
		heapGo.insert(H, 6, weight);
		heapGo.insert(H, 7, weight);
		heapGo.insert(H, 8, weight);
		heapGo.insert(H, 9, weight);
		//int d = H.indexOf(9);
		//heapGo.delete(H,H.indexOf(2), weight);
		System.out.println(heapGo.Max(H));
		System.out.println(H);
		System.out.println(heapGo.HeapSort(H, weight));
		

	}
	

}
