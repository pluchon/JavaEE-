import java.util.concurrent.CountDownLatch;

/**
 * @author pluchon
 * @create 2025-11-06-10:58
 * 作者代码水平一般，难免难看，请见谅
 */
public class Demo5 {
    public static void main(String[] args) throws InterruptedException {
        //参数表示任务数量，我们把主线程的大任务拆分成小任务
        CountDownLatch c = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(()->{
                //假设我们每个任务都要执行两秒钟
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                //两秒后完成任务，并且向c提交
                c.countDown();
            });
            t.start();
        }
        //我们主线程要等所有线程的小任务执行完毕
        //可以使用join，我们使用CountDownLatch中的方法
        c.await();
    }
}
