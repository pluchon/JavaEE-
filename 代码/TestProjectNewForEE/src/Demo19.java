import java.util.Scanner;

/**
 * @author pluchon
 * @create 2025-11-04-19:54
 * 作者代码水平一般，难免难看，请见谅
 */
public class Demo19 {
    public static void main(String[] args) throws InterruptedException {
        Object locker = new Object();
        Thread t1 = new Thread(()->{
            System.out.println("t1的wait方法之前");
            synchronized (locker){
                try {
                    locker.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("t1的wait方法之后");
            }
        });
        Thread t2 = new Thread(()->{
            synchronized (locker){
                System.out.println("t2的notify方法之前");
                Scanner sc = new Scanner(System.in);
                //我们要让t2线程在此阻塞
                sc.next();
                //此时唤醒t1线程
                locker.notify();
                System.out.println("t2的notify方法之后");
            }
        });
        t1.start();
        //确保t1先运行进入wait状态
        Thread.sleep(10);
        t2.start();
    }
}
