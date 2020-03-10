package leetcode;

import java.util.ArrayList;
import java.util.List;

public class 容器与两个线程 {
   volatile static boolean flog=false;
    public static void main(String[] args) {

       Container container=new Container();
        new Thread(()->{
            while (true) {
                if (container.size()>=5){
                    flog=true;
                    break;
                }
            }

        },"size").start();
            new Thread(()->{
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i=0;i<10;i++){
                    if (!flog)
                    container.add(i);
                }
            },"add").start();

        }

}
class Container{
    private volatile  List list=new ArrayList();
    volatile int size;
    public  void add(Object object){
        list.add(object);
        System.out.println(object);
    }
    public int size(){
        return list.size();
    }
}