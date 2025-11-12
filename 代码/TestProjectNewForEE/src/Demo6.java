import java.util.Scanner;

/**
 * @author pluchon
 * @create 2025-11-03-20:22
 * 作者代码水平一般，难免难看，请见谅
 */
public class Demo6 {
    private static boolean running = true;
    public static void main(String[] args) {
        Thread t = new Thread(()->{
            while(running){
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
            running = false;
        }
    }
}
