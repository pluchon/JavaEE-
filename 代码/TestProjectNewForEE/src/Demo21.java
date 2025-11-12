import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author pluchon
 * @create 2025-11-05-10:36
 * 作者代码水平一般，难免难看，请见谅
 */
public class Demo21 {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Long> blockingDeque = new ArrayBlockingQueue<>(1000);
        Thread t1 = new Thread(()->{//生产者慢
           long n = 0;
           while(true){
               try {
                   blockingDeque.put(n);
                   System.out.println(n);
                   n++;
               }catch (InterruptedException e){
                   throw new RuntimeException(e);
               }
           }
        });
        Thread t2 = new Thread(()->{//消费者快
            try {
                Long n = blockingDeque.take();
                System.out.println(n);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t1.start();
        t2.start();
    }
}
