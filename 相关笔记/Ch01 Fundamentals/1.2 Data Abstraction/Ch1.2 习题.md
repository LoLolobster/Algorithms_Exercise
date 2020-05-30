# Ch1.2 习题

### Ex6

> A string s is a *circular rotation* of a string t if it matches when the characters are circularly shifted by any number of positions; e.g., ACTGACG is a circular shift of TGACGAC, and vice versa. Detecting this condition is important in the study of genomic sequences. Write a program that checks whether two given strings s and t are circular shifts of one another.

**代码**

```java
(s.length() == t.length()) && (s.concat(s).indexOf(t) >=0)
```

**分析**

​	首先想到的是把所有回环的情况遍历一遍，暴力匹配；

​	但是观察一下：

​	**ACTGACG**ACTGACG, A**CTGACGA**CTGACG, AC**TGACGAC**TGACG, ......

​	都是在同一个模板字符串中将7位的窗口移动所得；

​	所以只要在这个模板字符串的某个窗口中存在待匹配字符串就可以了；

​	TO BE MORE SPECIFIC,

​	待匹配字符串是模板字符串的一个子串就可以了。



### Ex13

> 典型地实现一个`Transaction`类。

**代码**

```java
import java.util.Arrays;
import java.util.Comparator;

public class Transaction implements Comparable<Transaction>{
    private final String who; //customer
    private final Date when; //date
    private final double amount; //amount
    
//--构造函数--------------------------------------------------    
    public Transaction(String who, Date when, double amount){
        if(Double.isNaN(amount) || Double.isInfinite(amount))
            throw new IllegalArgumentException("Amount can not be NaN or Infinite!");
        this.who = who;
        this.when = when;
    }
    
    public Transaction(String transaction){
        String str = transaction.split(' ');
        double when = Double.parseDouble(str[2]);
        if(Double.isNaN(when) || Double.isInfinite(when))
            throw new IllegalArgumentException("Amount can not be NaN or Infinite!");
        this.who = str[0];
        this.when = Date(str[1]);
        this.amount = when;       
    }
    
//--成员变量get方法--------------------------------------------
    public String who(){
        return this.who;
    }
    
    public Date when(){
        return this.when;
    }
    
    public double amount(){
        return this.amount;
    } 
    
//--重载公共方法------------------------------------------------
    @Override
    public String toString(){
        return String.format("%-10s %10s %8.2f", who, when, amount);
    }
    
    
    @Override
    public int compareTo(Transaction that){
        return Double.compareTo(this.amount, that.amount);
    }
    
    @Override
    public boolean equals(Object other){
        if (this == other) return true; //自反性
        if(other == null) return false; //非空性
        if(this.getClass() != other.getClass()) return false; //类型比较
        Transaction that =  (Transaction) other;
        if(!this.who.equals(that.who)) return false; //值比较
        if(!this.when.equals(that.when)) return false;
        if(this.amount != that.amount) return false;
        /*
         return (this.amount == that.amount) && (this.who.equals(that.who))
                                            && (this.when.equals(that.when));
        */
        return true;
    }

    public int hashCode(){
        int hash = 1;
        hash = 31*hash + who.hashCode();
        hash = 31*hash + when.hashCode();
        hash = 31*hash + ((Double) amount).hashCode();
        return hash;
    }
    
//--实现Comparator接口-----------------------------------------
    public static class WhoOrder implements Comparator<Transaction> {
        @Override
        public int compare(Transaction v, Transaction w){
            return v.who.compareTo(w.who);
        }
    }
    
     public static class WhenOrder implements Comparator<Transaction> {
        @Override
        public int compare(Transaction v, Transaction w) {
            return v.when.compareTo(w.when);
        }
    }
    
     public static class HowMuchOrder implements Comparator<Transaction> {
        @Override
        public int compare(Transaction v, Transaction w) {
            return Double.compare(v.amount, w.amount);
        }
    }

//--测试用例--------------------------------------------------
    public static void main(String[] args) {
        Transaction[] a = new Transaction[4];
        a[0] = new Transaction("Turing   6/17/1990  644.08");
        a[1] = new Transaction("Tarjan   3/26/2002 4121.85");
        a[2] = new Transaction("Knuth    6/14/1999  288.34");
        a[3] = new Transaction("Dijkstra 8/22/2007 2678.40");

        StdOut.println("Unsorted");
        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i]);
        StdOut.println();
        
        StdOut.println("Sort by date");
        Arrays.sort(a, new Transaction.WhenOrder());
        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i]);
        StdOut.println();

        StdOut.println("Sort by customer");
        Arrays.sort(a, new Transaction.WhoOrder());
        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i]);
        StdOut.println();

        StdOut.println("Sort by amount");
        Arrays.sort(a, new Transaction.HowMuchOrder());
        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i]);
        StdOut.println();
    }

}
```

**分析**

* **成员变量的声明**
  * 可变；
  * 不可变；
* **构造函数的定义**
  * 传入的是与成员变量类型匹配的参数；
  * 传入的是待解析的字符串；
  * 进行数据合法性验证；
* **成员变量的`get`与`set`**
  * 取决于成员变量的类型与限定符；
* **对父类方法的重载**
  * 如果没有显式声明（`extends`），则考虑实现`Object`类方法：`toString()`，`equals()`，`hashCode()`；
* **对上层接口的实现**
  * 直接重写方法；
  * 构造内部类并重写方法；
* **成员方法的定义**
* **测试用例的编写**
  * 覆盖所有实例方法；



### Ex16

> **Rational numbers.** Implement an immutable data type [Rational.java](https://algs4.cs.princeton.edu/12oop/Rational.java.html) for rational numbers that supports addition, subtraction, multiplication, and division.
>
> ![api for rational numbers](C:\Users\HP\Desktop\Algorithms笔记\mdPics/rational-api.png)
>
> 
>
> You do not have to worry about testing for overflow, but use as instance variables two `long` values that represent the numerator and denominator to limit the possibility of overflow. Use Euclid's algorithm to ensure that the numerator and denominator never have any common factors. Include a test client that exercises all of your methods.

**代码**

```java
class Rational implements Comparable<Rational>{
    private final long num;
    private final long den;

    public Rational(int numerator, int denominator){
        //数据合法性验证
        if (denominator == 0)
            throw ArithmeticException("denominator is zero");
        //去除公因子
        int g = gcd(numerator, denominator);
        num = numerator / g;
        den = denominator / g;
        //如果分母是负数，把负号转换到分子
        if (den < 0) {
            den = -den;
            num = -num;
        }
    }
    
    public long numerator(){return this.num};
    public long denominator(){return this.denominator};
    
    public toDouble(){
        return (double) this.num / this.den;
    }
    
    public String toString(){
        if (den == 1) return num + "";
        else          return num + "/" + den;
    }
    
    public int compareTo(Rational that) {
        long lhs = this.num * that.den;
        long rhs = this.den * that.num;
        if (lhs < rhs) return -1;
        if (lhs > rhs) return +1;
        return 0;
    }
    
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        Rational that = (Rational) other;
        return this.compareTo(that) == 0;
    }
    
    public int hashCode() {
        return this.toString().hashCode();
    }
	
    public static Rational mediant(Rational r, Rational s) {
        return new Rational(r.num + s.num, r.den + s.den);
    }
    
    private static long gcd(long m, long n) {
        if (m < 0) m = -m;
        if (n < 0) n = -n;
        if (0 == n) return m;
        else return gcd(n, m % n);
    }
    
    //最小公倍数
	 private static long lcm(long m, long n) {
        if (m < 0) m = -m;
        if (n < 0) n = -n;
        return m * (n / gcd(m, n));    // parentheses important to avoid overflow
    }
    
    public Rational times(Rational that) {

        // reduce p1/q2 and p2/q1, then multiply, where a = p1/q1 and b = p2/q2
        Rational c = new Rational(this.num, that.den);
        Rational d = new Rational(that.num, this.den);
        return new Rational(c.num * d.num, c.den * d.den);
    }
    
     public Rational plus(Rational that) {

        // special cases
        if (this.compareTo(zero) == 0) return that;
        if (that.compareTo(zero) == 0) return this;

        // Find gcd of numerators and denominators
        long f = gcd(this.num, that.num);
        long g = gcd(this.den, that.den);

        // add cross-product terms for numerator
        Rational s = new Rational((this.num / f) * (that.den / g)
                                + (that.num / f) * (this.den / g),
                                  this.den * (that.den / g));

        // multiply back in
        s.num *= f;
        return s;
    }
    
    public Rational negate() {
        return new Rational(-num, den);
    }

    // return |this|
    public Rational abs() {
        if (num >= 0) return this;
        else return negate();
    }

    // return this - that
    public Rational minus(Rational that) {
        return this.plus(that.negate());
    }

    public Rational reciprocal() { return new Rational(den, num);  }

    // return this / that
    public Rational dividedBy(Rational that) {
        return this.times(that.reciprocal());
    }
    
    // test client
    public static void main(String[] args) {
        Rational x, y, z;

        // 1/2 + 1/3 = 5/6
        x = new Rational(1, 2);
        y = new Rational(1, 3);
        z = x.plus(y);
        StdOut.println(z);

        // 8/9 + 1/9 = 1
        x = new Rational(8, 9);
        y = new Rational(1, 9);
        z = x.plus(y);
        StdOut.println(z);

        // 1/200000000 + 1/300000000 = 1/120000000
        x = new Rational(1, 200000000);
        y = new Rational(1, 300000000);
        z = x.plus(y);
        StdOut.println(z);

        // 1073741789/20 + 1073741789/30 = 1073741789/12
        x = new Rational(1073741789, 20);
        y = new Rational(1073741789, 30);
        z = x.plus(y);
        StdOut.println(z);

        //  4/17 * 17/4 = 1
        x = new Rational(4, 17);
        y = new Rational(17, 4);
        z = x.times(y);
        StdOut.println(z);

        // 3037141/3247033 * 3037547/3246599 = 841/961 
        x = new Rational(3037141, 3247033);
        y = new Rational(3037547, 3246599);
        z = x.times(y);
        StdOut.println(z);

        // 1/6 - -4/-8 = -1/3
        x = new Rational(1, 6);
        y = new Rational(-4, -8);
        z = x.minus(y);
        StdOut.println(z);
    }
}
```

