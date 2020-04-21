/**
 * 
 */
package Ch01;

import java.util.Iterator;

/**
 * ͨ������ʵ�ֶ���
 * @author HP
 * create time: 2020��3��31������5:15:36
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
		//�ڱ�β������
		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		//��һ���ǰ�oldlast����last��������ô������Ҫ���ǵ�
		if (isEmpty()) {  //�����ʱ������û��Ԫ��
			first = last;   //����֮��oldlast�ͳ���ͷ��㣬��������item��null����Ȼ������
		}									
		else {
			oldlast.next = last;	
		}
		N++;
	}

	public Item dequeue() {
		//�ӱ�ͷɾ��Ԫ��
		Item item = first.item;
		first = first.next;
		//��ֹ��������Ƕ�first�������Ǻ����
		//�������ɾ��֮ǰֻ��һ��Ԫ�أ���first��last��ָ��һ�����
		//��ô����ɾ����ͷ�Ĳ���ʵ���ϲ�û���ͷű�ͷ����Դ����Ϊ����һ��lastָ������
		//Ҳ���ǳ����˶������룬�����Ҫ��null�����Ƕ������Դ�����ã���last��
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
