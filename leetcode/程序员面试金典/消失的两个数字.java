package leetcode.程序员面试金典;

public class 消失的两个数字 {
    public static int[] missingTwo(int[] nums) {
      int n1,n2;
      n1=n2=1;
      for (int i=0;i<nums.length;i++){
          if (Math.abs(nums[i])<=nums.length)
          nums[Math.abs(nums[i])-1]=-nums[Math.abs(nums[i])-1];
          else {
              if (Math.abs(nums[i])==nums.length+1){
                  n1=-1;
              }
              if (Math.abs(nums[i])==nums.length+2){
                  n2=-1;
              }
          }
      }
      int[] ans=new int[2];
      int t=0;
      for (int i=0;i<nums.length;i++){
          if (nums[i]>0){
              ans[t]=i+1;
              t++;
          }
      }
      if (n1==1){
          ans[t]=nums.length+1;
          t++;
      }
        if (n2==1){
            ans[t]=nums.length+2;
            t++;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] ints = missingTwo(new int[]{1});
     //   System.out.println(Math.abs(-10));
       for (int a:ints){
            System.out.println(a);
        }
    }
}
