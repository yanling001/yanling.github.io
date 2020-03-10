package leetcode;

public class 两数相除_29 {
    public int divide(int dividend, int divisor) {
        if (dividend==0)
            return 0;
        if (dividend==-1&&divisor==Integer.MIN_VALUE){
            return Integer.MAX_VALUE;
        }
        // 通过异或运算来确定正负
        boolean fuhaao=(dividend^divisor)<0;//小于零说明是负
        long t = Math.abs((long) dividend);//转为long型不看符号只看对应的二进制
        long d= Math.abs((long) divisor);
        //位运算
        int result=0;
        for (int i=31;i>=0;i--){
            if ((t>>i)>=d){
                result=result+(1<<i);
                t=t-(d<<i);
            }
        }
        return fuhaao ? -result: result;
    }
}
