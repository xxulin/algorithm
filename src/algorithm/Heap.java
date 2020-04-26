package algorithm;

import java.util.Arrays;

/**
 * 堆是一个完全二叉树，且堆中每一个节点的值都大于等于（或都小于等于）其子树中的每个节点的值
 * 这里实现一个大顶堆
 * @author xull
 *
 */
public class Heap {

	int[] heap;
	//堆可以存储的最大数据个数
	int n;
	//堆中已存储的数据
	int count;
	
	public static void main(String[] args) {
		Heap heap = new Heap(20);
		//int[] test = {0, 27, 16, 21, 64, 13, 15, 19, 5, 7, 6, 8, 1, 2};
		int[] test = {0, 8, 9, 1, 3, 12, 21, 17, 24, 4, 6, 16, 27, 5};
		for (int i : test) {
			heap.insert(i);
		}
		
		for (int i = 0; i < 5; i++) {
			heap.deleteTop();
		}
		
		//heap.sort(test);
		
		StringBuilder sb = new StringBuilder();
		for (int i : Arrays.copyOfRange(heap.heap, 0, heap.heap.length)) {
			sb.append(", ").append(i);
		}
		System.out.println(sb.substring(2));
	}
	
	/**
	 * 堆化
	 * @param a 要堆化的二叉树
	 * @param capacity 二叉树中的元素个数
	 * @param maxPos 开始堆化的节点
	 */
	private void heapify(int[] a,int capacity, int maxPos) {
		
		while(true) {
			
			int left = maxPos * 2;
			int right = left + 1;
			int swapIndex = maxPos;
			
			if(left <= capacity && a[left] > a[maxPos]) {
				swapIndex = left;
			}
			if(right <= capacity && a[right] > a[swapIndex]) {
				swapIndex = right;
			}
			if(swapIndex == maxPos) {
				break;
			}
			swap(a, maxPos, swapIndex);
			maxPos = swapIndex;
			
		}
		
	}
	
	/**
	 * 利用堆排序：
	 * 1.建堆
	 * 2.排序
	 */
	public void sort(int[] a) {

		//对于完全二叉树来说，从n/2+1到n都是叶子节点
		int index = (a.length - 1)/2;
		
		for(int i = index; i >= 1; i--) {
			//从上往下堆化：当前节点与其子节点比较并交换
			heapify(a, a.length - 1, i);
		}
		
		for(int i = a.length - 1; i > 0; i--) {
			
			//堆顶的数据移到尾部，尾部数据放到堆顶重新堆化
			int temp = a[1];
			a[1] = a[i];
			a[i] = temp;
			heapify(a, i - 1, 1);
			
		}
		
	}
	
	/**
	 * 删除堆顶元素
	 * 堆化：从上往下（当前节点与父节点对比并交换）
	 */
	public void deleteTop() {
		
		if(count <= 0) {
			return;
		}
			
		//堆顶元素下标就是1
		int index = 1;
		heap[index] = heap[count];
		
		while(true) {
			
			int left = index * 2;
			int right = left + 1;
			int swapIndex = index;
			
			if(left <= count && heap[left] > heap[index]) {
				swapIndex = left;
			}
			if(right <= count && heap[right] > heap[swapIndex]) {
				swapIndex = right;
			}
			if(index == swapIndex) {
				break;
			}
			swap(heap, index, swapIndex);
			index = swapIndex;
			
		}
		count--;
	}
	
	private void swap(int[] a, int index, int index2) {
		int temp = a[index];
		a[index] = a[index2];
		a[index2] = temp; 
	}
	
	/**
	 * 往堆中插入一个元素
	 * 堆化：从下往上堆化
	 * @param data
	 */
	public void insert(int data) {
		
		if(count + 1 > n) {
			return;
		}
		
		int index = ++count;
		heap[index] = data;
		//完全二叉树：子节点index = 父节点index * 2 || 父节点index * 2 + 1
		int fIndex = index / 2;
		
		while(fIndex > 0 && heap[index] > heap[fIndex]) {
			
			int temp = heap[index];
			heap[index] = heap[fIndex];
			heap[fIndex] = temp;
			index = fIndex;
			fIndex = index / 2; 
			
		}
		
	}
	
	public Heap(int size) {
		heap = new int[(n = size) + 1];
		count = 0;
	}
	
}
