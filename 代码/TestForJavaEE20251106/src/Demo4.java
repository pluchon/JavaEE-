import java.util.concurrent.Semaphore;

/**
 * @author pluchon
 * @create 2025-11-06-10:47
 * 作者代码水平一般，难免难看，请见谅
 */
public class Demo4 {
    public static void main1(String[] args) throws InterruptedException {
        //我们参数决定其初识资源个数
        Semaphore s = new Semaphore(2);
        //进行几次p操作再进行u操作
        s.acquire();
        System.out.println("获取资源");
        s.acquire();
        System.out.println("获取资源");
        s.release();//此时释放了资源，重新进入就绪状态
        System.out.println("添加了资源");
        s.acquire();//到这里因为资源耗尽，陷入阻塞
        System.out.println("再次获取到资源");
    }

    public static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        Semaphore s = new Semaphore(1);
        Thread t1 = new Thread(()->{
            for (int i = 0; i < 500; i++) {
                try {
                    s.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                count++;
                s.release();
            }
        });
        Thread t2 = new Thread(()->{
            for (int i = 0; i < 500; i++) {
                try {
                    s.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                count++;
                s.release();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(count);
    }
}
