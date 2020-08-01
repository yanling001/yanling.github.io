package leetcode;

import java.util.ArrayList;
import java.util.List;

//Z型变换
public class Leetcode_6 {
   public static String convert(String s,int numRows){
     if (numRows<=1) return s;
     List<StringBuilder> list =new ArrayList<>();
     for (int i=0;i<numRows;i++){
         list.add(new StringBuilder());
     }
     boolean isdown =false;
     int nowrow=0;
     for (char t:s.toCharArray()){
         list.get(nowrow).append(t);
         if (nowrow==0||nowrow==numRows-1) isdown=!isdown;
         if (isdown) nowrow=nowrow+1;
         else nowrow=nowrow-1;
     }
     StringBuilder ans=new StringBuilder();
     for (StringBuilder stringBuilder:list){
         ans.append(stringBuilder);
     }
     return ans.toString();
   }

    public static void main(String[] args) {
        System.out.println(convert("LEETCODEISHIRING",3));
    }
}
