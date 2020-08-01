package leetcode;

public class LeetCode_1013 {
    public boolean canThreePartsEqualSum(int[] A) {
        int num=0;
        for (int i=0;i<A.length;i++){
            num+=A[i];
        }
        if (num%3!=0) return false;
        int temp =num/3;
        int t=0;
        for (int i=0;i<A.length;i++){
            t+=A[i];
            if (t==temp){
                break;
            }
        }
        if (t!=temp) return false;
        int j=t+1;
        for (int m=j;j<A.length;j++){
           t+=A[m];
           if (t==temp*2) return true;
        }
        return false;
    }
}
