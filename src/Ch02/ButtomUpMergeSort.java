/**
 * 
 */
package Ch02;

/**
 * 自底向上归并排序
 * @author HP
 * create time: 2020年5月17日下午6:24:51
 */
public class ButtomUpMergeSort {
	
	private static char[] aux;

	/**
	 * 对左右半边都已经排好序的子数组进行归并
	 * @param a 整个数组
	 * @param lo 该次归并的子数组的最小索引在原数组中的位置
	 * @param mid
	 * @param hi
	 */
	public static void merge(char[] a, int lo, int mid, int hi) {
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
	
	
	/**
	 * 自底向上归并，这样每次归并时能确保子数组的左右半边都是有序的
	 * @param a
	 */
	public static void sort(char[] a) {
		int N = a.length;
		aux = new char[N];
		for(int i = 1; i < N; i = i + i) { //当前一个子数组大小
			System.out.println(i+"-----------------");
			for(int lo = 0; lo < N-i; lo += i + i) { //lo是要归并的子数组的索引，要归并的子数组由两个大小为i的子数组组成
				System.out.print(lo + " " + (lo + i - 1) + " " + Math.min(lo+ i + i -1, N-1));
				merge(a, lo, lo + i - 1, Math.min(lo+ i + i -1, N-1));
				for(int m = 0; m < N; m++) {
					System.out.print(a[m]);
				}
				System.out.println();
			}
		}
	}
	
	public static void main(String[] args) {
		char[] test = new char[16];
		String a = "MERGESORTEXAMPLE";
		for(int i = 0; i < a.length(); i++) {
			test[i] = a.charAt(i);
		}
		sort(test);
		for(int i = 0; i < a.length(); i++) {
			System.out.println(test[i]);
		}
	}
}
