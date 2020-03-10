package leetcode;

public class 成最多水容器_11 {
    public int maxArea(int[] height) {
        int r,l;
        r=0;l=height.length-1;
        int max=0;
      while (r<l){
          if (max<Math.min(height[r],height[l])*(l-r))
              max=Math.min(height[r],height[l])*(l-r);
          if (height[r]>height[l]){
              l--;
          }else r++;
      }
      return max;
    }
}
