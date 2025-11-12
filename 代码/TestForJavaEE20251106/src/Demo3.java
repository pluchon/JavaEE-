import java.util.concurrent.locks.ReentrantLock;

/**
 * @author pluchon
 * @create 2025-11-06-08:41
 * 作者代码水平一般，难免难看，请见谅
 */
public class Demo3 {
    public static int count = 0;
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock locker = new ReentrantLock();
        //由于我们在lock和unlock之间可能存在throw异常或者是return返回
        //因此我们使用try-finally，在try中上锁，为了保证解锁一定会执行
        //我们在finally中解锁
        Thread t1 = new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                try {
                    locker.lock();
                    count++;
                }finally{
                    locker.unlock();
                }
            }
        });
        Thread t2 = new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                try {
                    locker.lock();
                    count++;
                }finally {
                    locker.unlock();
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
