package leetcode;

//下一个排列
public class LeetCode_31 {
    public  static  int [] anstest(int b[]){
        //先从右向左查找到第一组a[i]>a[i-1]]
        int i=b.length-2;
        while (i>=0&&b[i+1]<b[i]){  //获取i-1
            i--;
        }
        if(i>=0){//当i>=0表示不是 3，2，1这中情况 可以找到进行交换 如果是3，2，1这中情况直接让reverce
            //交换 a[i]和a[j]  其中a[i]是第一组a[k]>a[k-1]中的k-1 a[j]是从 a[i]到b.length大于a[i]的最小数
            int j=b.length-1;
            while (j>0&&b[j]<=b[i]){//找到j
                j--;
            }
            //交换 a[i]和a[j]
            swap(b,i,j);
        }



        //将i+1到 b.length-1反转
        reverce(b,i+1);
        return  b;

    }

    private static void reverce(int[] b, int i) {
        int j=b.length-1;
        while (i<j){
            swap(b,i,j);
            i++;
            j--;
        }
    }

    private static void swap(int[] b, int i, int j) {
        int t;
        t=b[i];
        b[i]=b[j];
        b[j]=t;
    }

    public static void main(String[] args) {
      //  int[] a=anstest(new int[]{1,5,8,4,7,6,5,3,1});
        int[] a=anstest(new int[]{5,1,1});
        for (int t: a) {
            System.out.println(t);
        }

    }
}