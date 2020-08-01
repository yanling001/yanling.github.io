package leetcode;


import java.util.Stack;

// 84. 柱状图中最大的矩形
public class LeetCode_84 {
    public int largestRectangleArea(int[] heights)  {
        Stack<Integer> stack =new Stack<>();
        stack.push(-1);
        int max=0;
        for (int i=0;i<heights.length;i++){
            while (stack.peek()!=-1 && heights[i]<=heights[stack.peek()]){
                max=Math.max(max,heights[stack.pop()]*(i-stack.peek()-1));
            }
            stack.push(heights[i]);
        }
        return max;
    }
}
