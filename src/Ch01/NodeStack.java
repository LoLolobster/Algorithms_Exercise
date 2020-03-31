/**
 * 
 */
package Ch01;

import java.util.Iterator;
import java.util.Queue;

/**
 * @author HP
 * create time: 2020年3月31日下午4:52:25
 * 通过链表实现栈
 */
public class NodeStack<Item> implements Iterable<Item>{	
	private Node first; //头结点（相当于开辟一个栈空间）
	private int N;		//栈中元素的个数
	private class Node{ //链表结点类
		Item item;
		Node next;
	}
	private Queue test;
	
	public boolean isEmpty() { return N == 0; }
	
	public int size() { return N; }
	
	public void push(Item item) {
		//向栈顶添加元素
		Node oldFirst = first;
		first = new Node();
		first.item = item;
		first.next = oldFirst.next;
		//别忘了N++
		N++;
	}
	
	public Item pop() {
		//从栈顶删除元素
		Item item = first.item;
		first = first.next;
		//别忘了N--
		N--;
		return item;
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item>{

		private Node current;
		
		/* (non-Javadoc)
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return current.next != null;
		}

		/* (non-Javadoc)
		 * @see java.util.Iterator#next()
		 */
		@Override
		public Item next() {
			// TODO Auto-generated method stub
			Item item = current.item;
			current = current.next;
			return item;
		}
		
	}
	
	
}
