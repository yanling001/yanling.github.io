package leetcode;

public class STR {


    public String longestCommonPrefix(String[] strs) {
          int sh=strs[0].length();
          for(int i=1;i<strs.length;i++){
              if(sh>strs[i].length())
                  sh=strs[i].length();
          }
        String string= findsame(strs[0],strs[1],sh);
          if (strs.length>2);
        for(int i=2;i<strs.length;i++){
            string=findsame(strs[i],string,sh);
        }
        return string;
    }

    private String findsame(String str, String str1,int sh) {
        StringBuilder ans=new StringBuilder();
        for(int i=0;i<sh;i++){
            if(str.charAt(i)==str1.charAt(i)){
                ans.append(str.charAt(i));
            }else
                break;
        }
                if(sh>ans.length())
                    sh=ans.length();
        return ans.toString();
    }
}
