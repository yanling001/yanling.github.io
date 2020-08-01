package leetcode;

import java.util.Random;

//rand7 实现 rand10
public class LeetCode_407 {
    public int rand10() {
        int row, col, idx;
        do {
            row = rand7();
            col = rand7();
            idx = col + (row - 1) * 7;
        } while (idx > 40);
        return 1 + (idx - 1) % 10;
    }
    public int rand7(){
        Random random=new Random();
       return random.nextInt(7);
    }

    public static void main(String[] args) {
        int a=1;
        System.out.println(++a + ++a);
    }
}
