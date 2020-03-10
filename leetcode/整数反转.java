package leetcode;

public class 整数反转 {
    public static int reverse(int x) {
        StringBuilder stringBuilder=new StringBuilder();
        if (x<0){
            x=-x;
            stringBuilder.append("-");
        }

         while (x/10!=0){
             stringBuilder.append(x%10);
             x=x/10;
         }
         stringBuilder.append(x);
         System.out.println(stringBuilder);
         return Integer.parseInt(stringBuilder.toString());
    }

    public static void main(String[] args) {
        System.out.println(reverse(1534236469));
    }
}
