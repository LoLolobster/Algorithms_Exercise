/**
 * 
 */
package Ch02;

/**
 * 插入排序（假定输入为字符串）
 * @author HP
 * create time: 2020年5月17日下午4:47:31
 */
public class InsertionSort {

	public static void sort(char[] test) {
		int len = test.length;
		for(int i = 1; i < len; i++) {
			for(int j = i; j > 0; j--) {
				if(test[j] < test[j-1]) {
					char temp = test[j];
					test[j] = test[j-1];
					test[j-1] = temp;
				}
			}
			
		}
		for(int i = 0; i < len; i++) {
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
