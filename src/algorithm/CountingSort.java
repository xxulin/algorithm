package algorithm;

/**
 * 计数排序
 * @author xull
 * @desc 特殊的桶排序，数据分布范围较小时使用
 * @desc 利用另外一组数据做计数
 */
public class CountingSort {

	public static void main(String[] args) {
		
		//int[] a = {2, 5, 3, 0, 2, 3, 0, 3};
		int[] a = {2, 5, 3, 1, 2, 3, 3, 1, 4, 2, 5};
		int[] finalInt = countingSort(a);
		for (int i : finalInt) {
			System.out.println(i);
		}
		System.out.println(a.length + " == " + finalInt.length);
		
	}
	
	public static int[] countingSort(int[] a) {
		
		int max = a[0];
		for(int i = 0;i < a.length; i++) {
			if(max < a[i]) {
				max = a[i];
			}
		}
		
		int[] finalInt = new int[a.length];
		//创建一个计数桶，存放需排序数据范围内各数据的统计值，并顺序求和
		//int[] bucket = new int[max - min + 1];
		int[] bucket = new int[max + 1];//数组下标总是从0开始的
		//统计排序数据范围内各数据值的个数
		for(int j = 0; j < a.length; j++) {
			bucket[a[j]]++;
		}
		//将桶内的数据做顺序求和
		for(int x = 1; x < bucket.length; x++) {
			bucket[x] = bucket[x - 1] + bucket[x];
		}
		
//		for(int i = 0; i < a.length; i++) {
//			//待排序数组a中的数据，对应桶bucket的下标
//			//桶bucket中实际存放该数据从前到后位于第几位
//			//按照位数存放于最终数组中，并将个数减1
//			finalInt[--bucket[a[i]]] = a[i];
//			
//		}
		
		//靠，这里为了保证排序的稳定性需从后往前遍历，细节=  =
		for(int i = a.length - 1; i >= 0; i--) {
			finalInt[--bucket[a[i]]] = a[i];
		}
		
		return finalInt;
	}
	
}
