package leetcode;

import java.nio.channels.Selector;
import java.util.*;

public class 逃逸分析 {
    public static void main(String[] args) {
        char a[]=new char[]{'1','3'};
        System.out.println(new String(a));

        int i=0;
        List<String> list=new ArrayList<>();
        while (true){
            /*
             //String.intern()是一个Native方法，底层调用C++的
            // StringTable::intern 方法，源码注释：当调用 intern 方法时，
            // 如果常量池中已经该字符串，则返回池中的字符串；
            // 否则将此字符串添加到常量池中，并返回字符串的引用。
            关于intern的介绍：https://www.jianshu.com/p/0d1c003d2ff5
             */
            list.add(String.valueOf(++i).intern());

        }
    }
}
