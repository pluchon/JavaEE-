import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author pluchon
 * @create 2025-11-06-00:08
 * 作者代码水平一般，难免难看，请见谅
 */
public class Demo1 {
    private static AtomicInteger count = new AtomicInteger(0);
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            for (int i = 0; i < 100; i++) {
                count.incrementAndGet();//相当于count++
            }
        });
        Thread t2 = new Thread(()->{
            for (int i = 0; i < 100; i++) {
                count.incrementAndGet();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(count);
    }
}
