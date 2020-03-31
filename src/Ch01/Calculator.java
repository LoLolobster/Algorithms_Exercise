/**
 * 
 */
package Ch01;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author HP
 * create time: 2020年3月31日下午1:23:52
 */
public class Calculator {
	
	public static void main(String[] args) {
		Stack<Double> operands = new Stack<Double>();
		Stack<String> operations = new Stack<String>();
		String input = "( 1 - ( ( 2 + 3 ) * ( 4 / 5 ) ) )";
		Scanner s = new Scanner(input);
		
		while(s.hasNext()) {
			String item = s.next();
			//push operations
			if(item.equals("("))							 ;
			else if(item.equals("+"))	operations.push(item);
			else if(item.equals("-"))	operations.push(item);
			else if(item.equals("*"))	operations.push(item);
			else if(item.equals("/"))	operations.push(item);
			
			
			else {
				if(item.equals(")")) {
					double res;
					switch (operations.pop()) {
					case "+":
						res = operands.pop() + operands.pop();
						operands.push(res);
						break;
					case "-":
						res = -operands.pop() + operands.pop();
						operands.push(res);
						break;
					case "*":
						res = operands.pop() * operands.pop();
						operands.push(res);
						break;
					case "/":
						res =  1 / operands.pop() * operands.pop();
						operands.push(res);
						break;
					default:
						break;
					}						
				}
					
				else {
					//push operands
					try {
						double operand = Double.parseDouble(item);
						operands.push(operand);
					}
					catch(Exception e) {
						System.out.println(e.getMessage());
					}		
				}
			}			
		}
		s.close();
		System.out.println(operands.pop());
	}
}
