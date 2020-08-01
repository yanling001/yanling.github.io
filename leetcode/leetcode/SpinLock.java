package leetcode;

import java.util.concurrent.atomic.AtomicReference;
//自旋锁
public class SpinLock {
    //线程的原子引用对象(默认为当前的值为null)
   private AtomicReference<Thread> atomicReference=new AtomicReference<>();
    //上锁
     public void mylock() {
         //获取当前的线程对象
         Thread thread=Thread.currentThread();

         //CAS保证并发
         //如果当前没有线程进入修改atomicReference 则此线程获取执行权
         //如果当前已有线程进入 则while循环直到atomicReference的预期值被改回null
         while (!atomicReference.compareAndSet(null,thread))
         System.out.println(Thread.currentThread().getName()+"  in   ");
         try {
             Thread.sleep(5000);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }

     }
     //释放锁
     public void unlock(){
         //获取当前的线程对象
         Thread thread=Thread.currentThread();
         while (!atomicReference.compareAndSet(thread,null)){


         }
         System.out.println(Thread.currentThread().getName()+"  out   ");
     }
    public static void main(String[] args) {
        SpinLock spinLock=new SpinLock();
        new Thread(() ->{
            spinLock.mylock();
             spinLock.unlock();
        }).start();
        new Thread(() ->{
            spinLock.mylock();
            spinLock.unlock();
        }).start();
    }
}
