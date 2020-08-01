package leetcode;

public class LeetCode_238 {
    public int[] productExceptSelf(int[] nums) {
        int length=nums.length;
        int[] l=new int[length];
        int[] r=new int[length];

        int []ans=new int[length];
        l[0]=1;
        r[length-1]=1;
        for (int i=1;i<length;i++){
            l[i]=l[i-1]*nums[i-1];
        }
        for (int i=length-2;i>=0;i--){
            r[i]=r[i+1]*nums[i+1];
        }
        for (int i=0;i<length;i++){
            ans[i]=l[i]*r[i];
        }
        return ans;
    }
}
