/**
 * @author pluchon
 * @create 2025-11-04-16:33
 * 作者代码水平一般，难免难看，请见谅
 */
public class Demo15 {
    public static void main(String[] args) {
        Object locker1 = new Object();
        Object locker2 = new Object();
        Thread t1 = new Thread(()->{
           synchronized (locker1){
               System.out.println("拿到了一号锁");
               try {
                   //让t2拿到自己的锁后尝试竞争一号锁
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
               synchronized (locker2){
                   System.out.println("拿到了二号锁");
               }
           }
        });
        Thread t2 = new Thread(()->{
            synchronized (locker2){
                System.out.println("拿到了二号锁");
                try {
                    //让t1拿到自己的锁后尝试竞争二号锁
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (locker1){
                    System.out.println("拿到了一号锁");
                }
            }
        });
        t1.start();
        t2.start();
    }
}
