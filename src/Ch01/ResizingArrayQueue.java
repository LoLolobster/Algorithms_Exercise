/**
 * 
 */
package Ch01;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 使用数组实现队列实现队列
 * @author HP
 * create time: 2020年4月23日下午8:19:23
 */
public class ResizingArrayQueue<Item> implements Iterable<Item>{
	private Item[] array;
	private int first; // index of first element of queue（指向一个值）
	private int last; // index of next available slot（指向null）
	private int N;
	
	/**
	 * 初始化泛型数组，不能直接通过new来创建泛型数组（泛型是不变的）
	 */
	@SuppressWarnings("unchecked")
	public ResizingArrayQueue() {
		array = (Item[]) new Object[2];
		N = 0;
		first = 0;
		last = 0;
	}
	
	/**
	 * 将原来的数组扩容;
	 * 同时新数组的[0]即为first;
	 * @param capacity 新数组的容量
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
		//先看能不能再入队（不能就扩容）
		if(N == array.length) {resize(2*N);}
		//先不管last在哪，直接把值赋在last（定义就是下一个空的值的位置）
		array[last++] = item;
		//然后得考虑一手：是不是要wrap around
		if(last == array.length) {last = 0;}
		N++;
	}
	
	public Item dequeue() {
		//首先要看能不能再出队
		if(isEmpty()) throw new NoSuchElementException("Queue underflow");
		//能的话再把队头给弹出喽
		Item item = array[first];
		array[first] = null; //防治对象游离-我们不用它了但却没被GC回收
		//然后成员变量刷新
		N--;
		first++; //指向下个排队者
		if(first == array.length) {first = 0;}//同时考虑wrap around
		//最后再考虑一手要不要缩容
		if(N > 0 && N == array.length/4) {resize(array.length/2);} //当利用率只有四分之一时就缩小一半
		return item;
	}

	public Item peek() {
		//首先看有没有元素了
		if(isEmpty()) throw new NoSuchElementException("Queue underflow");
		//再执行
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
	 * 对数组从[first]到[last-1]进行遍历
	 * @author HP
	 * create time: 2020年4月23日下午9:08:00
	 * @param <Item>
	 */
	@SuppressWarnings("hiding")
	class MyIterator<Item> implements Iterator<Item> {
		/**
		 * 指示这是当前遍历的第几个元素
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
			//把第i个元素返回
			Item item = (Item) array[(first+i) % array.length];
			return item;
		}
		
	}

	public static void main(String[] args) {
		//1.3.37 Josephus问题
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