package leetcode.程序员面试金典;

public class url化 {
    public static String replaceSpaces(String S, int length) {
        StringBuilder stringBuilder=new StringBuilder();
        for (int i=0;i<length;i++){
            if (S.charAt(i)==' '){
                stringBuilder.append("%20");
            }
            else stringBuilder.append(S.charAt(i));
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(replaceSpaces("Mr John Smith    ",13));
    }
}
