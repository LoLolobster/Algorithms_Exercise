package Ch01;
import java.util.Arrays;

/**
 * 
 */

/**
 * @author HP
 * create time: 2020年3月17日下午4:33:58
 */
public class BinarySearchTest {
	public static int rank(int key, int[] a) {
		int lo = 0;
		int hi = a.length - 1;
		while(lo <= hi) {
			int mid = (lo + hi) / 2;
			if(key < a[mid]) hi = mid - 1;
			else if(key > a[mid]) lo = mid + 1;
			else return mid;
		}
		return -1;
	}
	
	@SuppressWarnings("deprecation")
	public static void main(String args[]) {
		int[] whiteList = In.readInts(args[0]);
		Arrays.sort(whiteList);
		while(!StdIn.isEmpty()) {
			int key = StdIn.readInt();
			if(rank(key, whiteList) < 0) {
				StdOut.println(key);
			}
		}
	}
}
