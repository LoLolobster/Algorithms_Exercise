/**
 * 
 */
package Ch02;

/**
 * @author HP
 * create time: 2020年5月17日下午4:56:31
 */
public class ShellSort {

	public static void sort(char[] test) {
		int len = test.length;
		int h = 1;
		while(h < len/3) {h = h*3 + 1;} //选择一个合适的初始h
		//如果h > N/2, 那么一共有N-h个子数组；否则，共有N/h + N mod h 个子数组
		while (h >= 1) {
			for(int i = h; i < len; i++) { //选择一个子数组进行一次插排，为了使所有子数组都部分有序，一共需要(N-h)次插排
				for(int j = i; j>= h; j -= h) { 
					if(test[j] < test[j-h]) {
						char temp = test[j];
						test[j] = test[j-h];
						test[j-h] = temp;
					}
				}			
			} //此时所有子数组内部已经排序完成，数组已经变得h有序
			h /= 3; //迭代h直至数组变得1有序
			
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
