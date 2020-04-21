/**
 * 
 */
package Ch01;

import java.util.Iterator;
import java.util.Queue;

/**
 * @author HP
 * create time: 2020��3��31������4:52:25
 * ͨ������ʵ��ջ
 */
public class NodeStack<Item> implements Iterable<Item>{	
	private Node first; //ͷ��㣨�൱�ڿ���һ��ջ�ռ䣩
	private int N;		//ջ��Ԫ�صĸ���
	private class Node{ //��������
		Item item;
		Node next;
	}

	
	public boolean isEmpty() { return N == 0; }
	
	public int size() { return N; }
	
	public void push(Item item) {
		//��ջ�����Ԫ��
		Node oldFirst = first;
		first = new Node();
		first.item = item;
		first.next = oldFirst;
		//������N++
		N++;
	}
	
	public Item pop() {
		//��ջ��ɾ��Ԫ��
		Item item = first.item;
		first = first.next;
		//������N--
		N--;
		return item;
	}
	
	public Item peek() {
		//��ջ����ȡԪ�ض���ɾ��
		return first.item;
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

		private Node current = first;
		
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
