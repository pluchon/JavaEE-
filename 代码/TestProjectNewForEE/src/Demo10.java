/**
 * @author pluchon
 * @create 2025-11-04-14:44
 * 作者代码水平一般，难免难看，请见谅
 */
public class Demo10 {
    private static int num = 0;
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            for (int i = 0; i < 5000; i++) {
                num++;
            }
        });
        Thread t2 = new Thread(()->{
            for (int i = 0; i < 5000; i++) {
                num++;
            }
        });
        t1.start();
        t2.start();
        //让main线程等待两个线程去执行完毕
        t1.join();
        t2.join();
        System.out.println(num);
    }
}
