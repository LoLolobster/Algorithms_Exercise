/**
 * 
 */
package Ch02;

/**
 * @author HP
 * create time: 2020年5月18日下午9:04:05
 */
public class QuickSort {
	/**
	 * 对一个数组（从lo到hi）进行切分：以首位的元素为基准，将所有小于该元素的元素放到它左边，将所有大于它的元素放到右边
	 * @param a 数组
	 * @param lo 数组首位
	 * @param hi 数组末尾
	 * @return 切分完成后基准元素的索引
	 */
	private static int partition(char[] a, int lo, int hi) {
		int i = lo; 
		int j = hi + 1;
		char base = a[lo];
		while(true) {
			while(a[++i] < base) { //从左向右一直检查a[i]是否小于base
				if(i == hi) {break;} //如果a[i]大于base或者已经到了hi(所有元素都小于base)，那么就将a[i]和a[j]交换
			}
			while(base < a[--j]) { //从右向左一直检查a[j]是否大于base 
				if(j == lo) {break;} //如果a[j]小于base或者已经到了lo（所有元素都大于base），那么就将a[i]和a[j]交换
			}
			if(i >= j) {break;} //直到i，j相遇时退出循环
			
			//交换a[i]与a[j]
			char temp = a[i];
			a[i] = a[j];
			a[j] = temp;
		}
		
		//交换a[lo]和a[j]，交换后a[lo..j-1] <= a[j] < a[j+1..hi
		char temp1 = a[lo];
		a[lo] = a[j];
		a[j] = temp1;
		return j;
	}

	public static void sort(char a[]) {
		sort(a, 0, a.length-1);
	}
	
	//递归调用：先切分，然后再对左右半边分别排序
	public static void sort(char[] a, int lo, int hi) {
		//结束条件
		if(lo >= hi) {return;}
		int j = partition(a, lo, hi);
		sort(a, lo, j);
		sort(a, j + 1, hi);
	}
	
	public static void main(String[] args) {
		char[] test = new char[16];
		String a = "QUICKSORTEXAMPLE";
		for(int i = 0; i < a.length(); i++) {
			test[i] = a.charAt(i);
		}
		sort(test);
	}
}
