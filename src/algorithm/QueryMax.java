package algorithm;

/**
 * 在O(n)时间复杂度内求无序数组中的第K大元素
 * @author xull
 * @desc 递推公式：query(q...r, k) = k < p + 1? query(q...p - 1, k): query(p + 1...r, k);
 * 		中止条件(k == p + 1)
 */
public class QueryMax {

	public static void main(String[] args) {
		
		int[] a = {4, 2, 5, 12, 3};
		System.out.println(queryMax(a, 2, 0, a.length - 1));
		
	}
	
	public static int queryMax(int[] a, int k, int start, int end) {
		
		int value = a[end];
		int i = start;
		for(int j = start; j < end; j++) {
			
			if(a[j] > value) {
				
				int temp = a[j];
				a[j] = a[i];
				a[i] = temp;
				i++;
				
			}
			
		}
		a[end] = a[i];
		a[i] = value;
		
		if(k - 1 == i) {
			return a[i];
		}
		
		if(k < i + 1) {
			return queryMax(a, k, start, i - 1);
		} else {
			return queryMax(a, k, i + 1, end);
		}
		
	}
	
}
