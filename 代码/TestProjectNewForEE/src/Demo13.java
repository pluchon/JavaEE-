/**
 * @author pluchon
 * @create 2025-11-04-15:46
 * 作者代码水平一般，难免难看，请见谅
 */
class Counters{
    public static int num = 0;

    public static void add(){
        synchronized (Counters.class){
            num++;
        }
    }
}
public class Demo13 {
    public static void main(String[] args) throws InterruptedException {
        Counters counters = new Counters();
        Thread t1 = new Thread(()->{
            for (int i = 0; i < 100; i++) {
                counters.add();
            }
        });
        Thread t2 = new Thread(()->{
            for (int i = 0; i < 100; i++) {
                counters.add();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(Counters.num);
    }
}
