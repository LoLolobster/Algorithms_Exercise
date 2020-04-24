/**
 * 
 */
package Ch01;

import java.util.Arrays;

/**
 * P189 1.4.5.1 two sum����
 * ��һ�����������������Ϊ0�������Եĸ���
 * ע���leetcode��TwoSum��ͬ
 * @author HP
 * create time: 2020��4��24������5:00:39
 */
public class TwoSum {
	
	public static int count(int[] a) {
		int counter = 0;
		//��������������
		Arrays.sort(a);
		//ʹ�ö��ֲ���Ѱ��-a[i]
		int len = a.length;
		for(int i = 0; i < len; i++) {
			int res = BinarySearch.rank(-a[i], a);
			if(res == -1) {continue;} //�޽��
			if(res < i) {continue;} //�ظ�
			if(res > i) {counter++;} 
		}
		return counter;
	}
	
}
