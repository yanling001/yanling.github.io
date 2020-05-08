package leetcode;

import java.util.ArrayList;
import java.util.List;
//数组中重复的数据
public class LeetCode_442 {
    public static  List<Integer> findDuplicates(int[] nums) {
     List<Integer> list = new ArrayList<>();
     for (int i=0;i<nums.length;i++){
         int index=Math.abs(nums[i])-1;
         if (nums[index]<0){
             list.add(index+1);
         }
         nums[index]=-nums[index];
     }
     return list;
    }

    public static void main(String[] args) {
        List<Integer> duplicates = findDuplicates(new int[]{4, 3, 2, 7, 8, 2, 3, 1});
        for (Integer integer:duplicates){
            System.out.println(integer);
        }
    }
}
