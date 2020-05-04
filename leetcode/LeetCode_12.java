package leetcode;
//整数转罗马数 贪心(类似分硬币的问题)
public class LeetCode_12 {
    int []nums=new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    public String intToRoman(int num) {
        StringBuilder stringBuilder=new StringBuilder();
    for (int index=0;index<13;index++){
        while (num>=nums[index]){
            stringBuilder.append(romans[index]);
            num=num-nums[index];
        }
    }
    return stringBuilder.toString();
    }
}
