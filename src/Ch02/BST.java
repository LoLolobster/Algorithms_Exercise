/**
 * 
 */
package Ch02;

/**
 * ���ڶ���������ķ��ű�
 * @author HP
 * create time: 2020��5��18������11:32:44
 */
public class BST <Key, Value>{
	private Node root;
	private class Node{
		Key key;
		Value val;
		private Node left, right;
		private int N;
		public Node(Key key, Value val, int N) {
			this.key = key;
			this.val = val;
			this.N = N;
		}
	}
	
	public int size() {return size(root);}
	
	private int size(Node x) {
		if(x == null) {return 0;}
		else {return x.N;}
	}
	
	public Value get(Key key) {
		return get(root, key);
	}
	
	/**
	 * �������еݹ����Ŀ���
	 * @param x ��ǰ�����ĸ��ڵ�
	 * @param key Ŀ���
	 * @return
	 */
	private Value get(Node x, Key key) {
		if(x == null) {return null;}
		else if(key < x.key) {return get(x.left, key);} //<��Ҫ��������
		else if(key > x.key) {return get(x.right,key);}
		else {
			return x.val;
		}
	}
	
	public void put(Key key, Value value) {
		root = put(root, key, value);
	}
	
	/**
	 * �����еݹ�putĿ���
	 * @param x
	 * @param key
	 * @param val
	 * @return
	 */
	private Node put(Node x, Key key, Value val) {
		if(x == null) {return new Node(key, val, 1);} //���û�鵽����ô���½�һ�����
		else if (x.key < key) {x.right = put(x.right, val, 1);}//����ȵ�ǰ���ļ�����ô�ͽ������ֵ�Էŵ��Ե�ǰ�ڵ�����ӽڵ�Ϊ���ڵ������
		else if (x.key > key) {x.left = put(x.left, val, 1);}
		//����ǰ�ڵ����Ҫput�Ľ��
		else x.val = val;
		//�������Ҫ����һ�µ�ǰ�ڵ�Ĵ�С
		x.N = size(x.right) + size(x.left) + 1;
		return x;
	}
	
	public void deleteMin() {
		root = deleteMin(root);
	}
	
	/**
	 * �ݹ���ң�����ɾ�������ÿ������
	 * @param x
	 * @return
	 */
	private Node deleteMin(Node x) {
		if( x.left == null) {return x.right;}
		x.left = deleteMin(x.left); //���������еݹ�
		//���������N
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}
	
	public void delete(Key key) {
		root = delete(root, key);
	}
	
	/**
	 * �ݹ����Ҫɾ����Ԫ�أ����������¶�����
	 * @param x
	 * @param key
	 * @return
	 */
	private Node delete(Node x, Key key) {
		if(x == null) {return null;}
		//��ȥ�ݹ��ѯ
		if(key < x.key) {x.left = delete(x.left, key);}
		else if(key > x.key) {x.right = delete(x.right, key);}
		//���ڲ鵽��
		else {
			if(x.right == null) {return x.left;} //����鵽�Ľ��û������������ô�Ϳ���ֱ�Ӱ������������滻���Լ�
			if(x.left == null) {return x.right;} //ͬ��
			//����鵽�Ľ���������������������
			Node t = x;
			x = min(t.right); //��ȥ�鵽��ǰ�ڵ������������С��㣬ȡ����ǰ�ڵ��λ��
			x.right = deleteMin(t.right); //Ȼ���ٴ�������ɾȥ������
			x.left = t.left; //����ٰ�xԭ�ȵ�������������
		}
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}
}
