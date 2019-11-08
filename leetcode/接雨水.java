package leetcode;

public class 接雨水 {
    public int trap(int[] height) {
        int maxleft,maxright;
        int ans=0;
        for (int i=1;i<height.length-1;i++){
            maxleft=maxright=0;
            for (int j=0;j<i;j++){
                maxleft=Math.max(maxleft,height[i]);
            }
            for (int k=i+1;k<height.length;k++){
                maxright=Math.max(maxright,height[k]);
            }
            ans=Math.min(maxleft,maxright)-height[i];
        }
        return  ans;
    }
}
