/**
 * 
 */
package Ch01;

import java.util.Iterator;

/**
 * @author HP
 * create time: 2020年3月31日下午5:15:36
 * 通过链表实现队列
 */
public class NodeQueue<Item> implements Iterable<Item>{
	private Node first;
	private Node last;
	private int N;
	private class Node {
		Item item;
		Node next;
	}
	
	public boolean isEmpty() { return N == 0; }//first == null
	
	public int size() { return N; }
	
	public void enqueue(Item item) {
		//在表尾插入结点
		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		//下一步是把oldlast链接last，但是这么做是需要考虑的
		if (isEmpty())	first = last; //如果此时队列中没有元素，
										  //链接之后oldlast就成了头结点，并且它的item是null，显然不合理
		else 			oldlast.next = last;	
		oldlast.next = last;
		N++;
	}

	public Item dequeue() {
		//从表头删除元素
		Item item = first.item;
		first = first.next;
		//截止到这里，我们对first操作都是合理的
		//但是如果删除之前只有一个元素，即first和last都指向一个结点
		//那么我们删除表头的操作实际上并没有释放表头的资源（因为还有一个last指向它）
		//也就是出现了对象游离，因此需要用null来覆盖对这个资源的引用（即last）
		if (isEmpty())	last = null;
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
	
	private class ListIterator implements Iterator<Item> {

		private Node current = first;
		/* (non-Javadoc)
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return current != null;
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
