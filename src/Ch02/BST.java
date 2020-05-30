/**
 * 
 */
package Ch02;

/**
 * 基于二叉查找树的符号表
 * @author HP
 * create time: 2020年5月18日下午11:32:44
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
	 * 在子树中递归查找目标键
	 * @param x 当前子树的根节点
	 * @param key 目标键
	 * @return
	 */
	private Value get(Node x, Key key) {
		if(x == null) {return null;}
		else if(key < x.key) {return get(x.left, key);} //<需要进行重载
		else if(key > x.key) {return get(x.right,key);}
		else {
			return x.val;
		}
	}
	
	public void put(Key key, Value value) {
		root = put(root, key, value);
	}
	
	/**
	 * 在树中递归put目标键
	 * @param x
	 * @param key
	 * @param val
	 * @return
	 */
	private Node put(Node x, Key key, Value val) {
		if(x == null) {return new Node(key, val, 1);} //如果没查到，那么就新建一个结点
		else if (x.key < key) {x.right = put(x.right, val, 1);}//如果比当前结点的键大，那么就将这个键值对放到以当前节点的右子节点为根节点的树中
		else if (x.key > key) {x.left = put(x.left, val, 1);}
		//否则当前节点就是要put的结点
		else x.val = val;
		//最后我们要更新一下当前节点的大小
		x.N = size(x.right) + size(x.left) + 1;
		return x;
	}
	
	public void deleteMin() {
		root = deleteMin(root);
	}
	
	/**
	 * 递归查找，并在删除后更新每个子树
	 * @param x
	 * @return
	 */
	private Node deleteMin(Node x) {
		if( x.left == null) {return x.right;}
		x.left = deleteMin(x.left); //在左子树中递归
		//结束后更新N
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}
	
	public void delete(Key key) {
		root = delete(root, key);
	}
	
	/**
	 * 递归查找要删除的元素，并在随后更新二叉树
	 * @param x
	 * @param key
	 * @return
	 */
	private Node delete(Node x, Key key) {
		if(x == null) {return null;}
		//先去递归查询
		if(key < x.key) {x.left = delete(x.left, key);}
		else if(key > x.key) {x.right = delete(x.right, key);}
		//现在查到了
		else {
			if(x.right == null) {return x.left;} //如果查到的结点没有右子树，那么就可以直接把它的左子树替换它自己
			if(x.left == null) {return x.right;} //同样
			//如果查到的结点既有左子树又有右子树
			Node t = x;
			x = min(t.right); //先去查到当前节点的右子树的最小结点，取代当前节点的位置
			x.right = deleteMin(t.right); //然后再从右子树删去这个结点
			x.left = t.left; //最后再把x原先的左子树还给他
		}
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}
}
