package leetcode.程序员面试金典;

import java.util.Arrays;

//动态规划
public class 堆箱子 {
    public int pileBox(int[][] box) {
       int dp[]=new int[box.length];
       //根据箱子的长宽高排序（长降序，宽高降序）
        Arrays.sort(box,(a, b) -> a[0] == b[0] ? a[1] == b[1] ? b[2] - a[2] : b[1] - a[1] : a[0] - b[0]);
        dp[0]=box[0][2];
        int res=dp[0]; //记录dp数组中最大的值
        for (int i=1;i<box.length;i++){
            int maxvel=0; int baseweight=box[i][1]; int basehight=box[i][2];
            //从0到i-1找可以与i结合的产生最大高度的结果
            for (int j=0;j<i;j++){
                //能放在i上面的箱子
                if (box[j][1]<baseweight&&box[j][2]<basehight){
                    maxvel=Math.max(maxvel,dp[j]);
                }
            }
            dp[i]=basehight+maxvel;
            res=Math.max(res,dp[i]);
        }
        return res;
    }
}
