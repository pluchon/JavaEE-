import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author pluchon
 * @create 2025-11-05-14:23
 * 作者代码水平一般，难免难看，请见谅
 */
public class MyThreadPool {
    private BlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>();

    private MyThreadPool(int capacity){//capacity代表线程的数目
        for (int i = 0; i < capacity; i++) {
            Thread t = new Thread(()->{
               while(true){
                   //让每一个线程去任务队列中取一个任务
                   Runnable task = null;
                   try {
                       task = blockingQueue.take();
                   } catch (InterruptedException e) {
                       throw new RuntimeException(e);
                   }
                   task.run();
               }
            });
            //设为后台线程，其他前台线程结束后后台线程也跟着结束
            t.setDaemon(true);
            //启动线程
            t.start();
        }
    }

    public void submit(Runnable task) throws InterruptedException {
        blockingQueue.put(task);
    }

    public static void main(String[] args) throws InterruptedException {
        MyThreadPool myThreadPool = new MyThreadPool(5);
        //我们给予两百个任务
        for (int i = 0; i < 200; i++) {
            int taskId = i;
            myThreadPool.submit(()->{
                Thread current = Thread.currentThread();
                System.out.println(current.getName()+"执行了任务"+taskId);
            });
        }
        //确保线程池中的线程有足够时间去执行
        Thread.sleep(1000);
    }
}
