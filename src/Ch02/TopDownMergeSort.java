/**
 * 
 */
package Ch02;

/**
 * �Զ����µĹ鲢����
 * @author HP
 * create time: 2020��5��17������6:00:49
 */
public class TopDownMergeSort {

	private static char[] aux; //��Ҫһ����������,ÿ��merge����Ҫ�õ�
	
	/**
	 * ��ں���
	 * @param Ҫ���������
	 */
	public static void sort(char[] a) {
		aux = new char[a.length];
		sort(a, 0, a.length-1);
	}
	
	/**
	 * ��һ����������й鲢����
	 * @param a ������
	 * @param lo �ô���������������С������ԭ�����е�λ��
	 * @param hi 
	 */
	public static void sort(char[] a, int lo, int hi) {
		//�����жϵݹ����
		if(lo >= hi) {return;}
		//Ȼ��Ҫ��a�����Ұ�߶��������
		int mid = lo + (hi - lo)/2;
		sort(a, 0, mid);
		sort(a, mid+1, hi);
		//�����й鲢
		merge(a, lo, mid, hi);
	}

	/**
	 * �����Ұ�߶��Ѿ��ź������������й鲢
	 * @param a ��������
	 * @param lo �ôι鲢�����������С������ԭ�����е�λ��
	 * @param mid
	 * @param hi
	 */
	private static void merge(char[] a, int lo, int mid, int hi) {
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
