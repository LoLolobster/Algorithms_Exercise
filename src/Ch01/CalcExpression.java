/**
 * 
 */
package Ch01;

import java.util.Queue;

/**
 * @author HP
 * create time: 2020年4月3日下午9:38:53
 */
public class CalcExpression {
	
	
	/**
	 * 检查当前输入的字符串中的左右括号是否都正确匹配
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
	 * 计算前序表达式
	 * @param prefix 输入的正确的前序表达式（用空格分隔操作数，操作符）
	 * @return
	 */
	public static double calcPrefix(String prefix) {
		NodeStack<Double> operands = new NodeStack<Double>();
		//分隔得到操作数与操作符
		String[] elements = prefix.split(" ");
		int length = elements.length;
		//倒序检查
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
			//操作数，整数或浮点数
			else 	operands.push(Double.parseDouble(element));			
	}				
		return operands.pop();
	}
	
	/**
	 * 中序转前序
	 * @param infixExp 输入的正确的中序表达式
	 * @return 转换后的前序表达式
	 */
	public static String InfixToPrefix(String infixExp) {
		NodeStack<Character> operations = new NodeStack<Character>();
		String output = "";
		String tempNum = ""; //用于记录当前字符是否是构成某个数字的一部分
		int index = infixExp.length();
		char next;
		while(index > 0) {
			index--;
			next = infixExp.charAt(index);
			//如果是数字打头。获取整个数值
			if(Character.isDigit(next)) {
				while(Character.isDigit(next) || next == '.') {
					tempNum = next + tempNum;
					index--;
					if(index >= 0) {next = infixExp.charAt(index);}
					else {break;}
				}
				index++; //考察完了，回退一个字符
				output = tempNum + " " + output; //从右向左输出
				tempNum = ""; //清空
			}
			//如果是右括号，直接入栈
			else if(next == ')') {
				operations.push(next);
			}
			//如果是左括号，输出栈中元素直至右括号被弹出（右括号将不输出）
			else if(next == '(') {
				char element;
				while((element=operations.pop()) != ')') {
					output = element + " " + output;
				}
			}
			//如果是加减乘除
			else {
				//栈为空时可以直接入栈
				if(operations.isEmpty()) {operations.push(next);}
				else {
					char top = operations.peek();
					//优先级低于栈顶优先级时，输出
					while((next == '+' || next == '-') && (top == '*' || top == '/')) {
						output = operations.pop() + " " + output;
						if(!operations.isEmpty()) {top = operations.peek();}
						//栈为空时入栈
						else	break;
					}
					//不低于栈顶优先级时，入栈
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
	 * 中序转后序（调度场算法，和中序转前序相反）
	 * @param infixExp 正确的中序表达式
	 */
	public static void InfixToPostfix(String infixExp) {
		NodeStack<Character> operations = new NodeStack<Character>();
		NodeQueue<Object> output = new NodeQueue<Object>();
		String tempNum = "";
		char next;
		int length = infixExp.length();
		int i = 0;
		while(i < length) {
			next = infixExp.charAt(i);		
			if(Character.isDigit(next)) {
				while(Character.isDigit(next) || next == '.') {
					tempNum += next;
					i++;
					if(i < length) {next = infixExp.charAt(i);}
				}
				i--;
				output.enqueue(tempNum);
				tempNum = "";
			}		
			else if(next == '(') {
				operations.push(next);
			}	
			else if(next == ')') {
				char top;
				while((top = operations.pop()) != '(') {
					output.enqueue(top);
				}
			}
			else {
				if(operations.isEmpty()) {operations.push(next);}
				else {
					char top = operations.peek();
					//输入的优先级小于等于栈顶的优先级
					while(("+-".indexOf(next)!=-1 && "+-*/".indexOf(top)!=-1) 
							|| ("*/".indexOf(next)!=-1 && "*/".indexOf(top)!=-1)
							) 
					{
						output.enqueue(operations.pop());
						if(operations.isEmpty()) {break;}
						else {top = operations.peek();}
					}
					operations.push(next);
				}
			}
			i++;
		}
		for(char op : operations) {output.enqueue(op);}
		for(Object o : output) {System.out.print(o + " ");}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String prefix = InfixToPrefix("2*3/(2-1)+3*(4-1)");
		System.out.println(prefix);
		double answer =  calcPrefix(prefix);
		System.out.println(answer);
		InfixToPostfix("2*3/(2-1)+3*(4-1)");
	}

}
