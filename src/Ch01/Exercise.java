package Ch01;

public class Exercise {

    /**
     * @param 一个二维数组
     * @return 该二维数组的一个局部最小值
     */
    public static int getLocalMinEle(int[][] a) {
        // 获取二维数组的行列数
        int rowSize = a.length;
        int colSize = a[0].length;
        int i, j; // 当前考察的元素坐标
        int p = 0, q = 0;// 迭代方向
        int ans = a[0][0];
        for (i = 0, j = 0; ; ) {
        	System.out.println("" + i + " " + j);
            if ((j < rowSize-1) && a[i][j] > a[i][j+1]) { //右
                j++;
                continue;
            }
            if ((i< rowSize - 1) && a[i][j] > a[i + 1][j]) { //下
                i++;
                continue;
            }
            if ((j > 0) && a[i][j] > a[i][j - 1]) { //左
                j--;
                continue;
            }
            if ((i > 0) && a[i][j] > a[i - 1][j]) { //上
                i--;
                continue;
            }
            ans = a[i][j];
            break;
        }
        return ans;
    }

    /**
     * 获取数组的一个局部最小值
     * 
     * @param a 输入数组
     * @return 该数组的一个局部最小值
     */
    public static int getLocalMinEle(int[] a) {
        return 0;
    }

    public static void main(String[] args) {
        int[][] a = new int[3][3];
        a[0] = new int[] { 5, 2, 3 };
        a[1] = new int[] { 2, 1, 8 };
        a[2] = new int[] { 4, 7, 6 };
        System.out.print(getLocalMinEle(a));
    }

}