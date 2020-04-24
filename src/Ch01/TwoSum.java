/**
 * 
 */
package Ch01;

import java.util.Arrays;

/**
 * P189 1.4.5.1 two sum问题
 * 在一个整数数组中输出和为0的整数对的个数
 * 注意和leetcode的TwoSum不同
 * @author HP
 * create time: 2020年4月24日下午5:00:39
 */
public class TwoSum {
	
	public static int count(int[] a) {
		int counter = 0;
		//对输入数组排序
		Arrays.sort(a);
		//使用二分查找寻找-a[i]
		int len = a.length;
		for(int i = 0; i < len; i++) {
			int res = BinarySearch.rank(-a[i], a);
			if(res == -1) {continue;} //无结果
			if(res < i) {continue;} //重复
			if(res > i) {counter++;} 
		}
		return counter;
	}
	
}
