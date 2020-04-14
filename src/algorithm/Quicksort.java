package algorithm;

/**
 * 快速排序算法
 * @author xull
 * @desc 递推公式：
 * quick_sort(p…r) = quick_sort(p…q-1) + quick_sort(q+1… r)
 * p >= r
 */
public class Quicksort {

	public static void main(String[] args) {
		
		//int[] a = {8, 10, 2, 3, 6, 1, 5};
		int[] a = {6, 11, 3, 9, 8, 2, 1, 5};
		
		quickSort(a, 0, a.length - 1);
		
		for (int i : a) {
			System.out.println(i);
		}
		
	}
	
	public static void quickSort(int[] a, int start, int pivot) {
		
		if(start >= pivot) {
			return;
		}
		
		int value = a[pivot];
		
		int i = start;
		for(int j = start; j < pivot; j++) {
			
			if(a[j] < value) {
				
				int temp = a[i];
				a[i++] = a[j];
				a[j] = temp;
				
			}
			
		}
		a[pivot] = a[i];
		a[i] = value;
		
		quickSort(a, start, i - 1);
		quickSort(a, i + 1, pivot);
		
	}
	
}
