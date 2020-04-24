/**
 * 
 */
package Ch01;

/**
 * P210 1.4.18 在O(lgN)下求数组的局部最小元素
 * @author HP
 * create time: 2020年4月24日下午8:21:47
 */
public class LocalMin {
	
	/**
	 * 前置条件：输入的数组元素必须distinct；
	 * @param a
	 * @param start
	 * @param end
	 * @return
	 */
	public static int find(int[] a, int start, int end) {
		int mid = (start + end) / 2;
		if(mid < 0 || mid >= a.length - 1) 					{return -1;}
		if(a[mid-1] > a[mid] && a[mid] < a[mid+1]) 			{return a[mid];}
		else if(a[mid] > a[mid-1]) 							{return find(a,start, mid);}
		else 												{return find(a, mid, end);}
	}
	
	public static void main(String[] args) {
		int res = find(new int[] {9,6,2,14,5,7,4}, 0, 6);
		System.out.println(res);
	}
}
