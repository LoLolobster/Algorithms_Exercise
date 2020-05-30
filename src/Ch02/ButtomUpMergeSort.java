/**
 * 
 */
package Ch02;

/**
 * �Ե����Ϲ鲢����
 * @author HP
 * create time: 2020��5��17������6:24:51
 */
public class ButtomUpMergeSort {
	
	private static char[] aux;

	/**
	 * �����Ұ�߶��Ѿ��ź������������й鲢
	 * @param a ��������
	 * @param lo �ôι鲢�����������С������ԭ�����е�λ��
	 * @param mid
	 * @param hi
	 */
	public static void merge(char[] a, int lo, int mid, int hi) {
		// ������Ҫ����ָ��ֱ�������Ұ��
		int i = lo, j = mid + 1;
		//Ȼ����һ����������
		for(int k = lo; k <= hi; k++) {
			aux[k] = a[k];
		}
		//�����й鲢
		//ÿ�ι鲢��������ȷ��һ��λ��k�ϵ�Ԫ��
		for(int k = lo; k <= hi; k++) {
			if(i > mid) {a[k] = aux[j++];} //����߶��鲢���ˣ���ôʣ�µ����е�Ԫ�ؾͺ��ұߵ�ʣ����ȫһ����
			else if (j > hi) {a[k] = aux[i++];} //���ұ߶��鲢���ˣ���ôʣ�µ����е�Ԫ�ؾͺ���ߵ�ʣ����ȫһ����
			else if (a[j] < a[i]) {a[k] = aux[j++];} //�ұߵĵ�ǰԪ��С����ߵĵ�ǰԪ�أ��ұߵļ����������飬ָ�����һλ
			else {a[k] = aux[i++];}
		}
		
	}
	
	
	/**
	 * �Ե����Ϲ鲢������ÿ�ι鲢ʱ��ȷ������������Ұ�߶��������
	 * @param a
	 */
	public static void sort(char[] a) {
		int N = a.length;
		aux = new char[N];
		for(int i = 1; i < N; i = i + i) { //��ǰһ���������С
			System.out.println(i+"-----------------");
			for(int lo = 0; lo < N-i; lo += i + i) { //lo��Ҫ�鲢���������������Ҫ�鲢����������������СΪi�����������
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
