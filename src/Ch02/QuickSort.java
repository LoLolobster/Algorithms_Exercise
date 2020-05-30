/**
 * 
 */
package Ch02;

/**
 * @author HP
 * create time: 2020��5��18������9:04:05
 */
public class QuickSort {
	/**
	 * ��һ�����飨��lo��hi�������з֣�����λ��Ԫ��Ϊ��׼��������С�ڸ�Ԫ�ص�Ԫ�طŵ�����ߣ������д�������Ԫ�طŵ��ұ�
	 * @param a ����
	 * @param lo ������λ
	 * @param hi ����ĩβ
	 * @return �з���ɺ��׼Ԫ�ص�����
	 */
	private static int partition(char[] a, int lo, int hi) {
		int i = lo; 
		int j = hi + 1;
		char base = a[lo];
		while(true) {
			while(a[++i] < base) { //��������һֱ���a[i]�Ƿ�С��base
				if(i == hi) {break;} //���a[i]����base�����Ѿ�����hi(����Ԫ�ض�С��base)����ô�ͽ�a[i]��a[j]����
			}
			while(base < a[--j]) { //��������һֱ���a[j]�Ƿ����base 
				if(j == lo) {break;} //���a[j]С��base�����Ѿ�����lo������Ԫ�ض�����base������ô�ͽ�a[i]��a[j]����
			}
			if(i >= j) {break;} //ֱ��i��j����ʱ�˳�ѭ��
			
			//����a[i]��a[j]
			char temp = a[i];
			a[i] = a[j];
			a[j] = temp;
		}
		
		//����a[lo]��a[j]��������a[lo..j-1] <= a[j] < a[j+1..hi
		char temp1 = a[lo];
		a[lo] = a[j];
		a[j] = temp1;
		return j;
	}

	public static void sort(char a[]) {
		sort(a, 0, a.length-1);
	}
	
	//�ݹ���ã����з֣�Ȼ���ٶ����Ұ�߷ֱ�����
	public static void sort(char[] a, int lo, int hi) {
		//��������
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
