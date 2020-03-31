/**
 * 
 */

/**
 * @author HP
 * create time: 2020年3月17日下午5:35:01
 */
public class Ex_ch01 {
	
	public static void ex1_1_7() {
		double t = 9.0;
		while(Math.abs(t-9.0/t) > 0.001)
			t = (t+9.0/t) /2.0;
		//System.out.printf("%.5f\n", t);
		
		int num = 0;
		for(int i = 1; i<1000;i++) {
			for(int j = 0; j<i; j++) {
				num++;
			}
		}
		//System.out.println(num);
	}
	
	public static void ex1_1_9(int N) {
		String s = "";
		for(int n = N; n>0; n = n/2) {
			s = (n % 2) + s;
		}
		System.out.println(s);
	}
	
	public static String exR1(int n) {
		if (n<=0) return "";
		return exR1(n-3) + n + exR1(n-2) + n;
	}
	
	public static int mystery(int a, int b) {
		if(b==0) return 0;
		if(b % 2 == 0) return mystery(a+a, b/2);
		return mystery(a+a, b/2) + a;
	}
	
	public static int count = 0;
	
	public static int Fib1(int N) {
		count++;
		if (N == 0) return 0;
		if (N == 1) return 1;
		return Fib1(N-1) + Fib1(N-2);
	}
	
	public static int Fib2(int N) {
		int a = 0, b = 1, t = 0;
		if (N == 0) return 0;
		if (N == 1) return 1;
		for(int i = 0; i<N-1; i++) {
			count++;
			t = b;
			b += a;
			a = t;
		}
		return b;
		
	}
	
	//1.1.27
	// slow
    public static double binomial1(int N, int k, double p) {
        if (N == 0 && k == 0) return 1.0;
        if (N < 0 || k < 0) return 0.0;
        return (1.0 - p) *binomial1(N-1, k, p) + p*binomial1(N-1, k-1, p);
    }

    // memoization
    public static double binomial2(int N, int k, double p) {
        double[][] b = new double[N+1][k+1];

        // base cases
        for (int i = 0; i <= N; i++)
            b[i][0] = Math.pow(1.0 - p, i);
        b[0][0] = 1.0;

        // recursive formula
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= k; j++) {
                b[i][j] = p * b[i-1][j-1] + (1.0 - p) *b[i-1][j];
            }
        }
        return b[N][k];
    }
    
    

    public static void main(String[] args) { 
       
    }
}


