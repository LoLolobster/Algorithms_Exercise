/**
 * 
 */
package Ch02;

/**
 * @author HP
 * create time: 2020年5月19日上午11:38:51
 */
public class LinearProbingHashST<Key, Value> {
	private int N; //符号表中键值对的总数
	private int M; //线性探测表的大小
	private Key[] keys; //键
	private Value[] vals; //值
	
	public LinearProbingHashST() {
		keys = (Key[]) new Object[M];
		vals = (Value[]) new Object[M];
	}
	
	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % M;
	}
	
	public void put(Key key, Value val) {
		if(N >= 2/M) {//将M加倍}
		}
		int i;
		for(i = hash(key); keys[i] !=null; i = (i + 1)%M) { //先到hash(key)的位置去找
			if(keys[i].equals(key)) {vals[i] = val; return;} //找到了（已经存在）就可以直接更新
		}//没找到的话也已经获得了下一个空的位置
		
		keys[i] = key;
		vals[i] = val;
		N++;
	}
	
	public void delete(Key key) {
		int i = hash(key);
		while(!key.equals(keys[i])) { //找到这个key的位置
			i = (i + 1) % M;
		}
		keys[i] = null; //删除它
		vals[i] = null;
		
		i = (i + 1) % M; 
		while(keys[i] != null) {
			Key keyToRedo = keys[i];
			Value valToRedo = vals[i];
			keys[i] = null;
			vals[i] = null;
			N--;
			put(keyToRedo, valToRedo); //重新排列这一簇中删除元素右边的所有键值对
			i = (i + 1) % M;
		}
		N--;
	}
	
	
	
}
