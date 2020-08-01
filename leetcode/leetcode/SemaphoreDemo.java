package leetcode;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

//使用Semaphore交替打印100
public class SemaphoreDemo {
    static int a=0;
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);
       ExecutorService executorService = Executors.newFixedThreadPool(2);
       for (int i=0;i<=100;i++){
           try {
               semaphore.acquire();
               System.out.println(a++);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }finally {
               semaphore.release();
           }
       }
    }
}
