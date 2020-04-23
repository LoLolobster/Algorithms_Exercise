/**
 * 
 */
package Ch01;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * ʹ������ʵ�ֶ���ʵ�ֶ���
 * @author HP
 * create time: 2020��4��23������8:19:23
 */
public class ResizingArrayQueue<Item> implements Iterable<Item>{
	private Item[] array;
	private int first; // index of first element of queue��ָ��һ��ֵ��
	private int last; // index of next available slot��ָ��null��
	private int N;
	
	/**
	 * ��ʼ���������飬����ֱ��ͨ��new�������������飨�����ǲ���ģ�
	 */
	@SuppressWarnings("unchecked")
	public ResizingArrayQueue() {
		array = (Item[]) new Object[2];
		N = 0;
		first = 0;
		last = 0;
	}
	
	/**
	 * ��ԭ������������;
	 * ͬʱ�������[0]��Ϊfirst;
	 * @param capacity �����������
	 */
	public void resize(int capacity) {
		assert capacity >= N;
		Item[] temp = (Item[]) new Object[capacity];
		for(int i = 0; i < N; i++) {
			temp[i] = array[(first+i) % array.length];
		}
		array = temp;
		first = 0;
		last = N;
	}
	
	public boolean isEmpty() {return N==0;}
	
	public int size() {return N;}
	
	public void enqueue(Item item) {
		//�ȿ��ܲ�������ӣ����ܾ����ݣ�
		if(N == array.length) {resize(2*N);}
		//�Ȳ���last���ģ�ֱ�Ӱ�ֵ����last�����������һ���յ�ֵ��λ�ã�
		array[last++] = item;
		//Ȼ��ÿ���һ�֣��ǲ���Ҫwrap around
		if(last == array.length) {last = 0;}
		N++;
	}
	
	public Item dequeue() {
		//����Ҫ���ܲ����ٳ���
		if(isEmpty()) throw new NoSuchElementException("Queue underflow");
		//�ܵĻ��ٰѶ�ͷ�������
		Item item = array[first];
		array[first] = null; //���ζ�������-���ǲ������˵�ȴû��GC����
		//Ȼ���Ա����ˢ��
		N--;
		first++; //ָ���¸��Ŷ���
		if(first == array.length) {first = 0;}//ͬʱ����wrap around
		//����ٿ���һ��Ҫ��Ҫ����
		if(N > 0 && N == array.length/4) {resize(array.length/2);} //��������ֻ���ķ�֮һʱ����Сһ��
		return item;
	}

	public Item peek() {
		//���ȿ���û��Ԫ����
		if(isEmpty()) throw new NoSuchElementException("Queue underflow");
		//��ִ��
		return array[first];
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new MyIterator<Item>();
	}
	
	/**
	 * �������[first]��[last-1]���б���
	 * @author HP
	 * create time: 2020��4��23������9:08:00
	 * @param <Item>
	 */
	@SuppressWarnings("hiding")
	class MyIterator<Item> implements Iterator<Item> {
		/**
		 * ָʾ���ǵ�ǰ�����ĵڼ���Ԫ��
		 */
		private int  i = 0;
		
		/* (non-Javadoc)
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return i < N;
		}

		/* (non-Javadoc)
		 * @see java.util.Iterator#next()
		 */
		@Override
		public Item next() {
			// TODO Auto-generated method stub
			if (!hasNext()) throw new NoSuchElementException();
			//�ѵ�i��Ԫ�ط���
			Item item = (Item) array[(first+i) % array.length];
			return item;
		}
		
	}

	public static void main(String[] args) {
		//1.3.37 Josephus����
		ResizingArrayQueue<Integer> q = new ResizingArrayQueue<Integer>();
		int N = 7;
		int M = 2;
		for(int i = 0; i < N; i++) {
			q.enqueue(i);
		}
		while(!q.isEmpty()) {
			for(int j = 0; j < M-1; j++) {
				q.enqueue(q.dequeue());
			}
			System.out.println(q.dequeue());
		}
	}
}