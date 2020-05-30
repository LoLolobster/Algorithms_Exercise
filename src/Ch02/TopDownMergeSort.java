/**
 * 
 */
package Ch02;

/**
 * 自顶向下的归并排序
 * @author HP
 * create time: 2020年5月17日下午6:00:49
 */
public class TopDownMergeSort {

	private static char[] aux; //需要一个辅助数组,每次merge都需要用到
	
	/**
	 * 入口函数
	 * @param 要排序的数组
	 */
	public static void sort(char[] a) {
		aux = new char[a.length];
		sort(a, 0, a.length-1);
	}
	
	/**
	 * 对一个子数组进行归并排序
	 * @param a 子数组
	 * @param lo 该次排序的子数组的最小索引在原数组中的位置
	 * @param hi 
	 */
	public static void sort(char[] a, int lo, int hi) {
		//首先判断递归出口
		if(lo >= hi) {return;}
		//然后要求a的左右半边都是有序的
		int mid = lo + (hi - lo)/2;
		sort(a, 0, mid);
		sort(a, mid+1, hi);
		//最后进行归并
		merge(a, lo, mid, hi);
	}

	/**
	 * 对左右半边都已经排好序的子数组进行归并
	 * @param a 整个数组
	 * @param lo 该次归并的子数组的最小索引在原数组中的位置
	 * @param mid
	 * @param hi
	 */
	private static void merge(char[] a, int lo, int mid, int hi) {
		// 首先需要两个指针分别操作左右半边
		int i = lo, j = mid + 1;
		//然后复制一个辅助数组
		for(int k = lo; k <= hi; k++) {
			aux[k] = a[k];
		}
		//最后进行归并
		//每次归并都能最终确定一个位置k上的元素
		for(int k = lo; k <= hi; k++) {
			if(i > mid) {a[k] = aux[j++];} //当左边都归并完了，那么剩下的所有的元素就和右边的剩余完全一样了
			else if (j > hi) {a[k] = aux[i++];} //当右边都归并完了，那么剩下的所有的元素就和左边的剩余完全一样了
			else if (a[j] < a[i]) {a[k] = aux[j++];} //右边的当前元素小于左边的当前元素，右边的加入最终数组，指针向后一位
			else {a[k] = aux[i++];}
		}
		
	}
	
	public static void main(String[] args) {
		char[] test = new char[11];
		String a = "SORTEXAMPLE";
		for(int i = 0; i < a.length(); i++) {
			test[i] = a.charAt(i);
		}
		sort(test);
		for(int i = 0; i < a.length(); i++) {
			System.out.println(test[i]);
		}
	}
	
	
}
