package 牛客;

import java.util.Arrays;

public class 等差数列 {
  static   Boolean  method(int n,int a[]){
        if (a.length<=2) return true;
      Arrays.sort(a);
      int temp=a[1]-a[0];
        for (int i=1;i<n-1;i++){
           if (Math.abs(a[i+1]-a[i])==temp) continue;
           else return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(method(3,new int[]{1,3,2}));
    }
}
