package leetcode.Threads;

public class LeetCode_1114 {
    private   volatile  int state =1;
    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        state=2;
            synchronized (this){
                notifyAll();
            }



    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (state!=2){
            synchronized (this){
               wait();
            }
        }
        printSecond.run();
        state=3;
        // printSecond.run() outputs "second". Do not change or remove this line.
            synchronized (this){

                notifyAll();
            }

    }

    public void third(Runnable printThird) throws InterruptedException {
              while (state!=3){ synchronized (this){
                  wait();
              }}
        printThird.run();

    }


    public static void main(String[] args) throws InterruptedException {
        LeetCode_1114 leetCode1114 =new LeetCode_1114();
        new Thread(()->{
            try {
                leetCode1114.second(()->{
                    System.out.println("second");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                leetCode1114.first(()->{
                    System.out.println("first");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();




        new Thread(()->{
            try {
                leetCode1114.third(()->{
                    System.out.println("third");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
