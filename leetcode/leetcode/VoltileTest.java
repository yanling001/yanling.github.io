package leetcode;

import java.util.concurrent.atomic.AtomicInteger;

public class VoltileTest {

  // static AtomicInteger atomicInteger=new AtomicInteger(1);
//    public static void main(String[] args) {
//       new Thread(()->{
//           while (atomicInteger.intValue()<100){
//               System.out.println(Thread.currentThread().getName()+"  "+atomicInteger.getAndIncrement());
//           }
//
//        }).start();
//       new Thread(()->{
//           while (atomicInteger.intValue()<100)
//           System.out.println(Thread.currentThread().getName()+"  "+atomicInteger.getAndIncrement());
//       }).start();
//    }
  static int a=1;
  public static void main(String[] args) {

      new Thread(()->{
          while (a<100){
              try {
                  Thread.sleep(100);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              System.out.println(Thread.currentThread().getName()+"  "+a++);

          }

      }).start();
      new Thread(()->{
          while (a<100)
              System.out.println(Thread.currentThread().getName()+"  "+a++);
      }).start();
  }
}
