/**
 * 
 */
package Ch01;

/**
 * @author HP
 * create time: 2020年4月1日下午8:18:23
 */
public class Ex_Ch01_3 {

	/**
	 * 
	 */
	public Ex_Ch01_3(String input) {
		// TODO Auto-generated constructor stub
		NodeStack<Character> stack = new NodeStack<Character>();
		boolean correct = false;
		int strLen = input.length();
		
		for(int i =0; i < strLen; i++) {
			char c = input.charAt(i);
			char p;
			switch (c) {
			case '(':
				stack.push(c);
				break;
			case '[':
				stack.push(c);
				break;
			case '{':
				stack.push(c);
				break;
			case ')':
				if(stack.size()==0 || stack.pop() != '(')
					System.out.println("false");
				break;
			case ']':
				if(stack.size()==0 || stack.pop() != '[')
					System.out.println("false");
				break;
			case '}':
				if(stack.size()==0 || stack.pop() != '{') 
					System.out.println("false");
				break;		
			default:
				break;
			}
		}	
		if (stack.size() > 0)
			System.out.println("false");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Ex_Ch01_3 e = new Ex_Ch01_3("[()]]");
	}

}
