/**
 * 
 */
package Ch02;

/**
 * �������򣨼ٶ�����Ϊ�ַ�����
 * @author HP
 * create time: 2020��5��17������4:25:36
 */
public class SelectionSort {
	
	public static void sort(char[] test) {
	
		int len = test.length;
		for(int i = 0; i < len; i++) {
			int minIndex = i;
			for(int j = i +1; j < len; j++) {
				if(test[j] < test[minIndex]) {minIndex = j;}
			}
			char temp = test[i];
			test[i] = test[minIndex];
			test[minIndex] = temp;
			System.out.println(test[i]);
		}
	}
	
	
	public static void main(String[] args) {
		char[] test = new char[11];
		String a = "SORTEXAMPLE";
		for(int i = 0; i < a.length(); i++) {
			test[i] = a.charAt(i);
		}
		sort(test);
	}
	
}
