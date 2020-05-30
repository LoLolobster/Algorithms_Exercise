/**
 * 
 */
package Ch02;

/**
 * @author HP
 * create time: 2020��5��17������4:56:31
 */
public class ShellSort {

	public static void sort(char[] test) {
		int len = test.length;
		int h = 1;
		while(h < len/3) {h = h*3 + 1;} //ѡ��һ�����ʵĳ�ʼh
		//���h > N/2, ��ôһ����N-h�������飻���򣬹���N/h + N mod h ��������
		while (h >= 1) {
			for(int i = h; i < len; i++) { //ѡ��һ�����������һ�β��ţ�Ϊ��ʹ���������鶼��������һ����Ҫ(N-h)�β���
				for(int j = i; j>= h; j -= h) { 
					if(test[j] < test[j-h]) {
						char temp = test[j];
						test[j] = test[j-h];
						test[j-h] = temp;
					}
				}			
			} //��ʱ�����������ڲ��Ѿ�������ɣ������Ѿ����h����
			h /= 3; //����hֱ��������1����
			
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
