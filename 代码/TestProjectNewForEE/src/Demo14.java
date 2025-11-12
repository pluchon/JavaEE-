/**
 * @author pluchon
 * @create 2025-11-04-15:57
 * 作者代码水平一般，难免难看，请见谅
 */
class Counterss{
    public static int num = 0;

    public void add(){
        synchronized (this){
            num++;
        }
    }
}
public class Demo14 {
    public static void main(String[] args) throws InterruptedException {
        Counterss counterss = new Counterss();
        Thread t1 = new Thread(()->{
            for (int i = 0; i < 100; i++) {
                synchronized (counterss){
                    counterss.add();
                }
            }
        });
        Thread t2 = new Thread(()->{
            for (int i = 0; i < 100; i++) {
                synchronized (counterss){
                    counterss.add();
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(Counterss.num);
    }
}
