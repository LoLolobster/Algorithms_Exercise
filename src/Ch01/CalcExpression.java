/**
 * 
 */
package Ch01;

/**
 * @author HP
 * create time: 2020��4��3������9:38:53
 */
public class CalcExpression {
	
	
	/**
	 * ��鵱ǰ������ַ����е����������Ƿ���ȷƥ��
	 * @param input
	 */
	public void calcInfix(String input) {
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
	 * ����ǰ����ʽ
	 * @param prefix �������ȷ��ǰ����ʽ���ÿո�ָ�����������������
	 * @return
	 */
	public static double calcPrefix(String prefix) {
		NodeStack<Double> operands = new NodeStack<Double>();
		//�ָ��õ��������������
		String[] elements = prefix.split(" ");
		int length = elements.length;
		//������
		for(int i = length-1; i >=0; i--) {
			String element = elements[i];
			//-- + --
			if(element.equals("+")) {
				double sum = operands.pop() + operands.pop();;
				operands.push(sum);
			}
			//-- - --
			else if(element.equals("-")) {
				double sub = operands.pop() - operands.pop();;
				operands.push(sub);
			}
			//-- * --
			else if(element.equals("*")) {
				double mul = operands.pop() * operands.pop();;
				operands.push(mul);
			}
			//-- / --
			else if(element.equals("/")) {
				double div =operands.pop() * operands.pop();;
 				operands.push(div);
			}
			//�������������򸡵���
			else 	operands.push(Double.parseDouble(element));			
	}				
		return operands.pop();
	}
	
	/**
	 * ����תǰ��
	 * @param infixExp �������ȷ��������ʽ
	 * @return ת�����ǰ����ʽ
	 */
	public static String InfixToPrefix(String infixExp) {
		NodeStack<Character> operations = new NodeStack<Character>();
		String output = "";
		String tempNum = ""; //���ڼ�¼��ǰ�ַ��Ƿ��ǹ���ĳ�����ֵ�һ����
		int index = infixExp.length();
		char next;
		while(index > 0) {
			index--;
			next = infixExp.charAt(index);
			//��������ִ�ͷ����ȡ������ֵ
			if(Character.isDigit(next)) {
				while(Character.isDigit(next) || next == '.') {
					tempNum = next + tempNum;
					index--;
					if(index >= 0) {next = infixExp.charAt(index);}
					else {break;}
				}
				index++; //�������ˣ�����һ���ַ�
				output = tempNum + " " + output; //�����������
				tempNum = ""; //���
			}
			//����������ţ�ֱ����ջ
			else if(next == ')') {
				operations.push(next);
			}
			//����������ţ����ջ��Ԫ��ֱ�������ű������������Ž��������
			else if(next == '(') {
				char element;
				while((element=operations.pop()) != ')') {
					output = element + " " + output;
				}
			}
			//����ǼӼ��˳�
			else {
				//ջΪ��ʱ����ֱ����ջ
				if(operations.isEmpty()) {operations.push(next);}
				else {
					char top = operations.peek();
					//���ȼ�����ջ�����ȼ�ʱ�����
					while((next == '+' || next == '-') && (top == '*' || top == '/')) {
						output = operations.pop() + " " + output;
						if(!operations.isEmpty()) {top = operations.peek();}
						//ջΪ��ʱ��ջ
						else {
							operations.push(next);
							top = next;
						}
					}
					//������ջ�����ȼ�ʱ����ջ
					operations.push(next);
				}
			}	
		}
		for(char op : operations) {
			output = op + " " + output;
		}
		return output;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String prefix = InfixToPrefix("2*3/(2-1)+3*(4-1)");
		double answer =  calcPrefix(prefix);
		System.out.println(answer);
	}

}
