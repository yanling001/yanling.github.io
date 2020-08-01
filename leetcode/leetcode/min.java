package leetcode;

import java.util.HashMap;
import java.util.HashSet;

public class min {
    public static int test(int h[],int w[]){
        int dp[]=new int[h.length];
        dp[0]=0;
        for(int i=1;i<h.length;i++){
            if(h[i]==h[i-1]){
                if(w[i]<w[i-1]){
                    dp[i]=dp[i-1]+w[i];
                    h[i]=h[i]+1;
                }else{
                    int var=w[i-1];
                    for(int j=i-1;j>=0;j--){
                        if(var>=w[i]) {
                            dp[i]=dp[i-1]+w[i];
                            h[i]=h[i]+1;
                            break;
                        }
                        if(j==0||h[j]+1!=h[j-1]){
                            dp[i]=dp[i-1]+var;
                            for(int k=j;k<i;k++){
                                h[k]++;
                            }
                        }
                         else    if(h[j]+1==h[j-1]){
                            var=var+w[j-1];
                        }


                    }
                }
            }else{
                dp[i]=dp[i-1];
            }
        }

        return dp[h.length-1];
    }

    public static void main(String[] args) {
      //  int a[]=new int[]{1,1,1,1,1};
       // int b[]=new int[]{2,3,1,4,5};

        int a[]=new int[]{4,3,2,1,1};
        int b[]=new int[]{2,3,1,4,30};

       // int a[]=new int[]{2,3,4,5,6};
        //int b[]=new int[]{2,3,1,4,5};

        //int a[]=new int[]{1,1,1,1,1};
         //int b[]=new int[]{1,1,1,1,1};

       // int a[]=new int[]{1,1,1,1,1};
        //int b[]=new int[]{10,5,9,35,9};
        System.out.println(test(a,b));
    }
}
