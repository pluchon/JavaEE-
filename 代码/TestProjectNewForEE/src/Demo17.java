import java.util.Scanner;

/**
 * @author pluchon
 * @create 2025-11-04-19:04
 * 作者代码水平一般，难免难看，请见谅
 */
public class Demo17 {
    private static int num = 0;
    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            while (num == 0) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("t1线程结束");
        });
        Thread t2 = new Thread(()->{
            Scanner scanner = new Scanner(System.in);
            num = scanner.nextInt();
            System.out.println("t2线程结束");
        });
        t1.start();
        t2.start();
    }
}
