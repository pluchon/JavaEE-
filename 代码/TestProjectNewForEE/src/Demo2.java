/**
 * @author pluchon
 * @create 2025-11-03-19:41
 * 作者代码水平一般，难免难看，请见谅
 */
class MyRunnable implements Runnable{
    @Override
    public void run() {
        while(true){
            System.out.println("hello Runnable");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

public class Demo2 {
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        //Runnable自身不可以单独执行，我们需要搭配一个线程载体
        Thread thread = new Thread(myRunnable);
        //因此此时我们Thread类无需重写Run方法，这样更加的解耦合
        thread.start();
    }
}
