package leetcode;


import java.util.Stack;

//验证二叉树的前序序列
public class LeetCode_331 {
    public static Boolean istrue(String preorder){

        // number of available slots
        int slots = 1;

        for(String node : preorder.split(",")) {
            // one node takes one slot
            --slots;

            // no more slots available
            if (slots < 0) return false;

            // non-empty node creates two children slots
            if (!node.equals("#")) slots += 2;
        }

        // all slots should be used up
        return slots == 0;


    }
    public static void main(String[] args) {
        System.out.println(istrue(
                "9,3,4,#,#,1,#,#,2,#,6,#,#"));
    }
}
