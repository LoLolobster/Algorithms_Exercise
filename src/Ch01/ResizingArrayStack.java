/**
 * 
 */
package Ch01;

import java.util.Iterator;

/**
 * @author HP
 * create time: 2020年3月31日下午2:42:15
 */
public class ResizingArrayStack<Item> implements Iterable<Item>{
	
	private Item[] a;	//栈元素
	private int N;		//元素数量
	
	@SuppressWarnings("unchecked")
	//初始化大小为1
	public ResizingArrayStack() { a = (Item[]) new Object[1]; }
	
	public boolean isEmpty() { return N == 0; }
	
	public int size() { return N; }
	
	@SuppressWarnings("unchecked")
	public void resize(int max) {
		//将当前栈移动到一个新的大小为max的栈
		Item[] temp = (Item[]) new Object[max];
		for(int i = 0; i < N; i++)
			temp[i] = a[i];
		a = temp;		
	}
	
	public void push(Item item) {
		//N++: N始终指向末尾元素的下一格
		a[N++] = item;
		if(N == a.length) { resize(2*N); }
	}
	
	public Item pop() {
		//--N： 先让N指向末尾元素
		Item item = a[--N];
		a[N] = null; //避免对象游离
		if(N > 0 && N == a.length / 4) { resize(a.length / 2); }
		return item;
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new ReverseArrayIterator();
	}
	
	
	private class ReverseArrayIterator implements Iterator<Item> {

		private int i = N;
		/* (non-Javadoc)
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return i > 0;
		}

		/* (non-Javadoc)
		 * @see java.util.Iterator#next()
		 */
		@Override
		public Item next() {
			// TODO Auto-generated method stub
			return a[--i];
		}
		
	}
	
	public static void main(String[] args) {
		ResizingArrayStack<String> ras = new ResizingArrayStack<String>();
		ras.push("a");
		ras.push("b");
		ras.push("c");
		for(String s : ras) {
			System.out.println(s);
		}
	}
	
}
