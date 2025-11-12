/**
 * @author pluchon
 * @create 2025-11-04-15:38
 * 作者代码水平一般，难免难看，请见谅
 */
class Counter{
    public int num = 0;

    public void add(){
        synchronized (this){
            num++;
        }
    }
}

public class Demo12 {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        Thread t1 = new Thread(()->{
            for (int i = 0; i < 100; i++) {
                counter.add();
            }
        });
        Thread t2 = new Thread(()->{
            for (int i = 0; i < 100; i++) {
                counter.add();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(counter.num);
    }
}
