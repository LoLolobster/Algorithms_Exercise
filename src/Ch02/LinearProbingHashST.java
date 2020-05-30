/**
 * 
 */
package Ch02;

/**
 * @author HP
 * create time: 2020��5��19������11:38:51
 */
public class LinearProbingHashST<Key, Value> {
	private int N; //���ű��м�ֵ�Ե�����
	private int M; //����̽���Ĵ�С
	private Key[] keys; //��
	private Value[] vals; //ֵ
	
	public LinearProbingHashST() {
		keys = (Key[]) new Object[M];
		vals = (Value[]) new Object[M];
	}
	
	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % M;
	}
	
	public void put(Key key, Value val) {
		if(N >= 2/M) {//��M�ӱ�}
		}
		int i;
		for(i = hash(key); keys[i] !=null; i = (i + 1)%M) { //�ȵ�hash(key)��λ��ȥ��
			if(keys[i].equals(key)) {vals[i] = val; return;} //�ҵ��ˣ��Ѿ����ڣ��Ϳ���ֱ�Ӹ���
		}//û�ҵ��Ļ�Ҳ�Ѿ��������һ���յ�λ��
		
		keys[i] = key;
		vals[i] = val;
		N++;
	}
	
	public void delete(Key key) {
		int i = hash(key);
		while(!key.equals(keys[i])) { //�ҵ����key��λ��
			i = (i + 1) % M;
		}
		keys[i] = null; //ɾ����
		vals[i] = null;
		
		i = (i + 1) % M; 
		while(keys[i] != null) {
			Key keyToRedo = keys[i];
			Value valToRedo = vals[i];
			keys[i] = null;
			vals[i] = null;
			N--;
			put(keyToRedo, valToRedo); //����������һ����ɾ��Ԫ���ұߵ����м�ֵ��
			i = (i + 1) % M;
		}
		N--;
	}
	
	
	
}
