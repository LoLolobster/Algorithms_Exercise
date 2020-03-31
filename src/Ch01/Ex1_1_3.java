package Ch01;
import java.io.BufferedReader;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author HP
 * create time: 2020年3月17日下午5:09:56
 */
public class Ex1_1_3 {
	public static void main(String args[]) {
		int a = Integer.parseInt(args[0]);
		int b = Integer.parseInt(args[1]);
		int c = Integer.parseInt(args[2]);
		
		if(a==b) {
			if(b==c) {
				System.out.println("equal");
			}
			else {
				System.out.println("not equal");
			}
		}
		else {
			System.out.println("not equal");
		}
	}
}
