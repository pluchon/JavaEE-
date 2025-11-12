import java.util.Scanner;

/**
 * @author pluchon
 * @create 2025-11-04-09:54
 * 作者代码水平一般，难免难看，请见谅
 */
public class Demo7 {
    public static void main(String[] args) {
        Thread t = new Thread(()->{
            while(Thread.currentThread().isInterrupted()){
                System.out.println("hello");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        t.start();
        //我们想通过用户输入去终止这个线程
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        if(input == 1){
            t.isInterrupted();
        }
    }
}
