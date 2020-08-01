package leetcode;

import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class 自定义线程池 {
    public static void main(String[] args) {
      //  Executor pool= Executors.newFixedThreadPool(5);    Executors不推荐使用此方法获取线程池
        //自定义
       ExecutorService pool=new ThreadPoolExecutor(2,5,1L,TimeUnit.SECONDS,new LinkedBlockingDeque<>(10),
                Executors.defaultThreadFactory(),new ThreadPoolExecutor.CallerRunsPolicy()) ;
    }
}
