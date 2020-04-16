package algorithm;

/**
 * 二分查找
 * @author xull
 *
 */
public class BinarySearch {

	public static void main(String[] args) {
		
		int[] a = {0, 1, 2, 3, 5, 8, 10, 16, 19};
		for(int i = 0; i < a.length; i++) {
			int target = a[i];
			System.out.println(binarySearch(a, 0, a.length - 1, target));
		}
		
	}
	
	/**
	 * 简单普通版本
	 * @param a
	 * @param target
	 * @return
	 */
	public static boolean binarySearch(int[] a, int target) {
		
		int low = 0;
		int high = a.length - 1;
		int mid;
		
		while(low <= high) {
			
			mid = (high + low) / 2;
			
			if(a[mid] > target) {
				high = mid - 1;
			} else if(a[mid] < target) {
				low = mid + 1;
			} else {
				return true;
			}
		}
		
		return false;
		
	}
	
	/**
	 * 简单递归版本
	 * @param a
	 * @param low
	 * @param high
	 * @param target
	 * @return
	 */
	public static int binarySearch(int[] a, int low, int high, int target) {
		
		if(low > high) {
			return -1;
		}
		
		int mid = low + ((high - low) >> 1); 
		if(a[mid] == target) {
			return mid;
		}
		
		if(a[mid] > target) {
			return binarySearch(a, low, mid - 1, target);
		} else {
			return binarySearch(a, mid + 1, high, target);
		}
		
	}
	
}
