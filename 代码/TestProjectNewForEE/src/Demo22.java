import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author pluchon
 * @create 2025-11-05-11:43
 * 作者代码水平一般，难免难看，请见谅
 */
public class Demo22 {
    public static void main(String[] args) {
        //普通类型线程池，可以自动扩容
        ExecutorService currentThread1 = Executors.newCachedThreadPool();
        //固定大小的线程池，需要指定大小
        ExecutorService currentThread2 = Executors.newFixedThreadPool(10);
        //定时器类型的线程池，需要指定盒型线程数量
        ExecutorService currentThread3 = Executors.newScheduledThreadPool(10);
        //只包含一个线程的线程池
        ExecutorService currentThread4 = Executors.newSingleThreadExecutor();
        //只包含一个线程并且加入定时器的线程池
        ExecutorService currentThread5 = Executors.newSingleThreadScheduledExecutor();
    }
}
