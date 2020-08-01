package leetcode;

import java.util.ArrayList;
import java.util.List;
//插入区间
public class LeetCode_57 {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> list=new ArrayList<>();
        int start,end;
        start=newInterval[0];
        end=newInterval[1];
         int count=0;
         while (count<intervals.length&&intervals[count][1]<start){
             list.add(intervals[count]);
             count++;
         }
         while (count<intervals.length&&intervals[count][0]<=end){
            if (intervals[count][0]<start){
                start=intervals[count][0];
                continue;
            }
            if (intervals[count][1]>end){
                end=intervals[count][1];
                continue;
            }
            count++;
         }
         list.add(new int[]{start,end});
         while (count<intervals.length){
             list.add(intervals[count]);
             count++;
         }
         int ans[][]=new int[list.size()][2];
         int i=0;
         for ( int[] a: list ){
             ans[i]=a;
             i++;
         }
         return ans;
    }
}
