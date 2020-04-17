package algorithm;

/**
 * 二分查找
 * @author xull
 *
 */
public class BinarySearch {

	public static void main(String[] args) {
		
		int[] a = {0, 0, 1, 2, 5, 5, 8, 10, 16, 16};
		for(int i = 0; i < a.length; i++) {
			int target = a[i];
			System.out.println(searchLast(a, target));
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
	/**
	 * 变体：查找第一个值等于给定值的元素
	 * @param a
	 * @param target
	 * @return
	 */
	public static int searchFirst(int[] a, int target) {
		
		int low = 0;
		int high = a.length - 1;
		int mid = -1;
		
		while(low <= high) {
			
			mid = low + ((high - low) >> 1);
			if(a[mid] > target) {
				high = mid - 1;
			} else if(a[mid] < target) {
				low = mid + 1;
			} else {
				
				if(mid == 0 || a[mid - 1] != target) {
					return mid;
				} else {
					high = mid - 1;
				}
				
			}
			
		}
		
		return -1;
	}
	
	/**
	 * 变体：查找最后一个值等于给定值的元素
	 * @param a
	 * @param target
	 * @return
	 */
	public static int searchLast(int[] a, int target) {
		
		int low = 0;
		int high = a.length - 1;
		int mid = -1;
		
		while(low <= high) {
			
			mid = low + ((high - low) >> 1);
			if(a[mid] > target) {
				high = mid - 1;
			} else if(a[mid] < target) {
				low = mid + 1;
			} else {
				
				if(mid == a.length - 1 || a[mid + 1] != target) {
					return mid;
				} else {
					low = mid + 1;
				}
				
			}
			
		}
		
		return -1;
		
	}
	
	/**
	 * 查找第一个大于等于给定值的元素
	 * @param a
	 * @param target
	 * @return
	 */
	public static int searchFirstGE(int[] a, int target) {
		
		int low = 0;
		int high = a.length - 1;
		int mid = -1;
		
		while(low <= high) {
			
			mid = low + ((high - low) >> 1);
			if(a[mid] >= target) {
				
				if(mid == 0 || a[mid - 1] < target) {
					return mid;
				} else {
					high = mid - 1;
				}
				
			} else {
				low = mid + 1;
			}
			
		}
		
		return -1;
	}
	
	/**
	 * 查找最后一个小于等于给定值的元素
	 * @param a
	 * @param target
	 * @return
	 */
	public static int searchLastLE(int[] a, int target) {
		
		int low = 0;
		int high = a.length - 1;
		int mid;
		
		while(low <= high) {
			
			mid = low + ((high - low) >> 1);
			if(a[mid] <= target) {
				
				if(mid == a.length - 1 || a[mid + 1] > target) {
					return mid;
				} else {
					low = mid + 1;
				}
				
			} else {
				
				high = mid - 1;
				
			}
			
		}
		
		return -1;
		
	}
	
}
